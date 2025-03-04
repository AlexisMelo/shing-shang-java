package domaine;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 * Permet d'initialiser le jeu et contient toutes les méthodes pour gérer la partie
 * Menu, création de joueurs, lancement de la partie
 *
 */
public class GestionPartie 
{

	private Partie partieEnJeu;
	
	/**
	 * Lancement du jeu
	 * @throws FileNotFoundException
	 * Fichier non trouvé
	 */
	public GestionPartie() throws FileNotFoundException
	{
		ActionsMenu actionsmenuEnCours;
		
		do
		{
			actionsmenuEnCours = choixActionsMenu();

			if (actionsmenuEnCours.equals(ActionsMenu.NOUVELLE))
			{
				partieEnJeu = nouvellePartie();
				partieEnJeu.jouerUnTour();
			}
			else if(actionsmenuEnCours.equals(ActionsMenu.CHARGER))
			{
				partieEnJeu = chargerPartie();
				if(partieEnJeu != null)
				{
					partieEnJeu.jouerUnTour();
				}
				else
				{
					System.out.println("Aucune partie n'a été enregistré !");
				}
			}
			else if(actionsmenuEnCours.equals(ActionsMenu.SUPPRIMER))
			{
				supprimerFichier();
			}
			else if(actionsmenuEnCours.equals(ActionsMenu.REGLES))
			{
				AfficherRegles();
			}
		}while(!actionsmenuEnCours.equals(ActionsMenu.QUITTER));
		
		System.out.println("\nMerci d'avoir joué !\n"
				+ "Jeu réalisé par :\n"
				+ "Alexis Melo da Silva\n"
				+ "Valentin Bossard\n"
				+ "05/2018");
	}
	
	/**
	 * Permet de choisir quel joueur va commencer la partie
	 * Soit par choix, soit au hasard
	 * @param joueur1P
	 * Premier joueur
	 * @param Joueur2P
	 * Deuxième joueur
	 * @return Le numéro du joueur qui doit effectuer le premier tour
	 */
	public int choixPremierJoueur(Joueur joueur1P, Joueur Joueur2P)
	{
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		String stringChoix;
		
		do
		{
			System.out.println("\nQuel joueur doit commencer ?");
			System.out.println("1) " + joueur1P.getNom() +"\n"
								+"2) " + Joueur2P.getNom() +"\n"
								+"3) Au hasard");
			stringChoix = sc.nextLine();
		}while(stringChoix.equals("1") && stringChoix.equals("2") && stringChoix.equals("3"));
		
		if (stringChoix.equals("1")) return 1;
		else if (stringChoix.equals("2")) return 2;
		return random.nextInt(2) + 1;
	}
	
	/**
	 * Permet de créer un nouveau joueur
	 * Si le parametre n'est pas null cela signifie qu'il y a déjà eu un joueur créé avant
	 * dans la fonction appelante
	 * Ainsi on évite de redemander la couleur en prenant la couleur opposée
	 * @param couleurPremierJoueurP
	 * Couleur du joueur créé précedemment
	 * @return Nouveau joueur
	 */
	public Joueur creationJoueur(Couleur couleurPremierJoueurP)
	{
		Scanner sc = new Scanner(System.in);
		String stringNom;
		String stringCouleur;
	
		do
		{
			System.out.println("\nNom du joueur : \n( Moins de 15 caractères )");
			stringNom = sc.nextLine();
		}while(stringNom.length() > 15 || stringNom.length() <= 0);
		
		if(couleurPremierJoueurP  != null)
		{
			return new Joueur(Couleur.getCouleurInverse(couleurPremierJoueurP),stringNom);
		}
		else
		{
			do
			{
				System.out.println("\nChoix de la couleur : \n( N. Noir R. Rouge )");
				stringCouleur = sc.nextLine().toLowerCase();
			}while(!stringCouleur.equals("r") && !stringCouleur.equals("n"));	
		}
		
		return new Joueur(Couleur.getCouleur(stringCouleur), stringNom);
	}
	
	/*
	 * Initialise une nouvelle partie
	 */
	public Partie nouvellePartie()
	{
		Joueur joueur1 = creationJoueur(null);
		Joueur joueur2 = creationJoueur(joueur1.getCouleur());
		return new Partie(choixPremierJoueur(joueur1,joueur2),joueur1,joueur2, new Plateau(), joueur1.getNom() + "_" + joueur2.getNom());
	}
	
	/*
	 * Permet de choisir une action du menu
	 * @return Action choisie
	 */
	public ActionsMenu choixActionsMenu()
	{
		Scanner sc = new Scanner(System.in);
		String stringChoix;
		
		System.out.println("\nQue voulez vous faire ?\n");
		
		System.out.println("N. Nouvelle Partie\n"
				+ "C. Charger Partie\n"
				+ "S. Supprimer Partie\n"
				+ "R. Règles du jeu\n"
				+ "Q. Quitter");
		
		do
		{
			stringChoix = sc.nextLine().toLowerCase();
		}while(!(ActionsMenu.getAction(stringChoix) instanceof ActionsMenu));
		
		return ActionsMenu.getAction(stringChoix);
	}
	
	/**
	 * Affiche les règles du jeu
	 * @throws FileNotFoundException
	 * Fichier non trouvé
	 */
	public void AfficherRegles() throws FileNotFoundException
	{
		BufferedReader br = null;
		FileReader fr = null;			
		String sCurrentLine;
		
		try 
		{
			fr = new FileReader(getClass().getResource("/domaine/Regles").getFile());		
			br = new BufferedReader(fr);

			while ((sCurrentLine = br.readLine()) != null) 
			{
				System.out.println(sCurrentLine);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();	
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void afficheIdentifiantListeFichiers()
	{
		String [] listeFichiers;
		File fichier1 = new File("save/");
		listeFichiers = fichier1.list();
		
		for(int i = 0; i < listeFichiers.length; i++)
		{
			System.out.println(i + " : " + listeFichiers[i]);
		}
		System.out.println("");
	}
	
	public String choixFichier()
	{
		String [] listeFichiers;
		File fichier1 = new File("save/");
		listeFichiers = fichier1.list();
		int numeroFichier = -1;
		
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("Veuillez entrez l'identifiant du fichier");
			try 
			{
				numeroFichier = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Veuillez saisir un chiffre !");
				sc.nextLine();
			}
		}while(numeroFichier == -1 || numeroFichier >= listeFichiers.length);
		
		return listeFichiers[numeroFichier];
	}
	
	/**
	 * Upload d'une sauvegarde
	 * @return la partie chargé
	 */
	public Partie chargerPartie()
	{
		Vector<Partie> partie = null;
		
		if(nombreFichierSauvegarde() > 0)
		{
			System.out.println("Choisissez le fichier de sauvegarde à charger : ");
			afficheIdentifiantListeFichiers();
			
			File fichier = new File("save/" + choixFichier());
			
			if(fichier.exists())
			{
				try 
				{
					ObjectInputStream fichierR = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichier)));
					partie = (Vector) fichierR.readObject();
					fichierR.close();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				
				return partie.get(0);
			}
		}
		
		return null;
	}
	
	public void supprimerFichier()
	{
		if(nombreFichierSauvegarde() > 0)
		{
			System.out.println("Choisissez le fichier de sauvegarde à supprimer : ");
			afficheIdentifiantListeFichiers();
			File fichier = new File("save/" + choixFichier());
			fichier.delete();
		}
		else
		{
			System.out.println("Il n'y a aucun fichier de sauvegarde !");
		}
		
	}
	
	public int nombreFichierSauvegarde()
	{
		String [] listeFichiers;
		File fichier1 = new File("save/");
		listeFichiers = fichier1.list();
		
		return listeFichiers.length;
	}
	
}
