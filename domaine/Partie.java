package domaine;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 *
 */
public class Partie implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2653945650741951888L;
	private int intJoueurCourant;
	private Joueur joueur1;
	private Joueur joueur2;
	private Plateau plateauPlateau;
	private String nomSave;

	/**
	 * Permet de créer une partie
	 * 
	 * @param JoueurDebute
	 * Le numéro du joueur qui est censé debuter la partie
	 * @param joueur1P
	 * Le premier joueur1P
	 * @param joueur2P
	 * Le deuxième joueur2P
	 * @param plateauPlateauP
	 * Le plateau de la partie
	 * @param save
	 * Nom de la sauvegarde
	 */
	public Partie(int JoueurDebute, Joueur joueur1P, Joueur joueur2P, Plateau plateauPlateauP, String save) 
	{
		intJoueurCourant = JoueurDebute;
		joueur1 = joueur1P;
		joueur2 = joueur2P;
		this.plateauPlateau  = plateauPlateauP;
		nomSave = save;
		plateauPlateauP.poseArmee(joueur1P.getArmee());
		plateauPlateauP.poseArmee(joueur2P.getArmee());
		plateauPlateau.setPartie(this);
	}

	/**
	 * Lance la partie
	 */
	public void jouerUnTour()
	{
		do
		{
			if(intJoueurCourant == 1)
			{
				tourValide(joueur1);
			}
			else
			{
				tourValide(joueur2);
			}
		}while(!estPartieTermine());
		
		supprimeSauvegarde();
		affichageVictoire();
		
	}
	
	/**
	 * Effectue le tour d'un joueur 
	 * @param joueurTestP
	 * Le joueur jouant le tour
	 */
	public void tourValide(Joueur joueurTestP)
	{
		Actions actionChoisie = null;
		Bushi bushiSelectionne = null;
		Bushi bushiSaute = null;
		Case casePrecedente = null;
		int intDistance = 0;
		Direction directionChoisie = null;
		boolean booleanASaute = false;
		boolean booleanAMange = false;
		
		do
		{
			do
			{
				plateauPlateau .afficher();
				
				if(!booleanASaute || booleanAMange)
				{
					if(booleanAMange)
					{
						casePrecedente = plateauPlateau.getCase(bushiSelectionne);
					}
					
					do
					{
						bushiSelectionne = joueurTestP.choisitBushi(plateauPlateau);
					}while(booleanAMange && casePrecedente == plateauPlateau.getCase(bushiSelectionne));
					
					if(booleanAMange)
					{
						booleanAMange = false;
						booleanASaute = false;
					}
					
				}
				
				actionChoisie = joueurTestP.choisitAction(booleanASaute, bushiSelectionne);
				
				if(actionChoisie != Actions.FIN && actionChoisie != Actions.ANNULE && 
						!(actionChoisie == Actions.GLISSE && bushiSelectionne.getTypeBushi().getSymbole() == "D"))
				{
					directionChoisie = joueurTestP.choisitDirection();
				}
				
				if(actionChoisie == Actions.SAUTE)
				{
					if(bushiSelectionne.sautPossible(plateauPlateau , plateauPlateau .getCase(bushiSelectionne), directionChoisie, casePrecedente))
					{
						casePrecedente = plateauPlateau .getCase(bushiSelectionne);
						
						bushiSaute = bushiSelectionne.saute(plateauPlateau , plateauPlateau .getCase(bushiSelectionne), directionChoisie);
						
						if(booleanASaute && bushiSaute.getCouleur() != bushiSelectionne.getCouleur())
						{
							plateauPlateau .marqueAEffacer(plateauPlateau .getCase(bushiSaute));
							booleanAMange = true;
							joueurTestP.incrementerNbShingShang();
						}
						
						booleanASaute = true;
					}
					else
					{
						System.out.println("Déplacement impossible !");
					}
					
				}
				else if(actionChoisie == Actions.GLISSE)
				{
					if(bushiSelectionne.getTypeBushi().getSymbole() != "D")
					{
						do
						{
							intDistance = joueurTestP.choisitDistance();
						}while(!bushiSelectionne.verifDistance(intDistance));
						
						if(bushiSelectionne.glissePossible(plateauPlateau , plateauPlateau .getCase(bushiSelectionne), directionChoisie, intDistance))
						{
							bushiSelectionne.glisse(plateauPlateau , plateauPlateau .getCase(bushiSelectionne), directionChoisie, intDistance);
							actionChoisie = Actions.FIN;
						}
						else
						{
							System.out.println("Déplacement impossible !");
						}
					}
					else
					{
						System.out.println("Déplacement impossible !");
					}
				}
				
			}while(actionChoisie == Actions.ANNULE);
			
			if(booleanASaute && actionChoisie != Actions.GLISSE)
			{
				joueurTestP.incrementerNbSaut();
			}
			
			if(estPartieTermine())
			{
				actionChoisie = Actions.FIN;
			}
			
		}while(actionChoisie != Actions.FIN);
		
		joueurTestP.incrementerNbTour();
		changerJoueur();	
		sauvegarde();
	}
	
	/**
	 * Sauvegarde la partie 
	 *
	 */
	public void sauvegarde()
	{
		try 
		{
			ObjectOutputStream fichierW = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("save/" + nomSave)));
			Vector<Partie> lesPartie = new Vector<Partie>();
			lesPartie.add(this);
			fichierW.writeObject(lesPartie);
			fichierW.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("--- Partie sauvegardée ! ---");
	}
	
	/**
	 * Suppression du fichier de sauvegarde
	 */
	public void supprimeSauvegarde()
	{
		File fichier = new File("save/" + nomSave);
		fichier.delete();
	}
	
	/**
	 * Change le joueur courant
	 */
	public void changerJoueur()
	{
		if(intJoueurCourant == 1)
		{
			intJoueurCourant = 2;
		}
		else
		{
			intJoueurCourant = 1;
		}
	}

	/**
	 * Vérifie si la partie est terminée
	 * 
	 * @return Renvoie true si la partie est terminée
	 */
	public boolean estPartieTermine() 
	{
		if(estGagnant() != null)
		{
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @return Renvoie le joueur ayant gagné la partie, retourne null si la partie n'est pas finie
	 */
	public Joueur estGagnant() 
	{
		
		if((plateauPlateau .getCase(joueur1.getArmee().get(0)) == null && plateauPlateau .getCase(joueur1.getArmee().get(1)) == null) 
				|| plateauPlateau.getCasePlateau(1,4).getBushi() != null 
				|| plateauPlateau .getCasePlateau(1,5).getBushi() != null)
		{
			return joueur2;
		}
		else if((plateauPlateau .getCase(joueur2.getArmee().get(0)) == null && plateauPlateau .getCase(joueur2.getArmee().get(1)) == null) 
				|| plateauPlateau .getCasePlateau(8,4).getBushi() != null
				|| plateauPlateau .getCasePlateau(8,5).getBushi() != null)
		{
			return joueur1;
		}
		
		return null;
	}
	
	/**
	 * Affiche le gagnant ainsi que des informations sur la partie qui vient de se terminer et sur les
	 * performances des joueurs
	 */
	public void affichageVictoire()
	{
		int intNbTourTotal = joueur1.getNbTour() + joueur2.getNbTour();
		int intNbShingShangTotal = joueur1.getNbShingShang() + joueur2.getNbShingShang();
		int intNbSautTotal = joueur1.getNbSaut() + joueur2.getNbSaut();
		
		System.out.println("\n" + estGagnant().getNom() + " remporte la partie !");
		System.out.println("\n--- Statistiques de la partie ---"
				+ "\nNombre de tours      : " + intNbTourTotal
				+ "\nNombre de sauts      : " + intNbSautTotal
				+ "\nNombre de ShingShang : " + intNbShingShangTotal);
		
		System.out.println("\n--- Statistiques de " + joueur1.getNom() + " ---"
				+ "\nNombre de tours      : " + joueur1.getNbTour()
				+ "\nNombre de sauts      : " + joueur1.getNbSaut()
				+ "\nNombre de ShingShang : " + joueur1.getNbShingShang());
		
		System.out.println("\n--- Statistiques de " + joueur2.getNom() + " ---"
				+ "\nNombre de tours      : " + joueur2.getNbTour()
				+ "\nNombre de sauts      : " + joueur2.getNbSaut()
				+ "\nNombre de ShingShang : " + joueur2.getNbShingShang());
	}
	
	/**
	 * 
	 * @param joueur nouveau joueur courant
	 */
	public void setJoueurCourant(int joueur)
	{
		intJoueurCourant = joueur;
	}
	
	public Joueur getJoueur1()
	{
		return joueur1;
	}
	
	public Joueur getJoueur2()
	{
		return joueur2;
	}
	
	public String toString()
	{
		return ("\nJoueur 1 : " + joueur1.getNom()
				+"\nJoueur 2 : " + joueur2.getNom()
				+"\nEntrain de jouer : Joueur " + intJoueurCourant);
	}
	
	

}
