package domaine;

/**
 * 
 * 
 *
 */
public class Partie 
{
	private int intJoueurCourant;
	private Joueur joueur1;
	private Joueur joueur2;
	private Plateau plateauPlateau;

	/**
	 * Créé une partie
	 * 
	 * @param joueur1P
	 * Le premier joueur1P
	 * @param joueur2P
	 * Le deuxième joueur2P
	 */
	public Partie(Joueur joueur1P, Joueur joueur2P)
	{
		intJoueurCourant = 1;
		joueur1 = joueur1P;
		joueur2 = joueur2P;
		plateauPlateau = new Plateau();
		plateauPlateau.poseArmee(joueur1.getArmee());
		plateauPlateau.poseArmee(joueur2.getArmee());
	}

	/**
	 * Créé une partie
	 * 
	 * @param joueur1P
	 * Le premier joueur1P
	 * @param joueur2P
	 * Le deuxième joueur2P
	 * @param plateauPlateauP
	 * Le plateau de la partie
	 */
	public Partie(Joueur joueur1P, Joueur joueur2P, Plateau plateauPlateauP) 
	{
		intJoueurCourant = 1;
		joueur1 = joueur1P;
		joueur2 = joueur2P;
		this.plateauPlateau  = plateauPlateauP;
		plateauPlateauP.poseArmee(joueur1P.getArmee());
		plateauPlateauP.poseArmee(joueur2P.getArmee());
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
		
		System.out.println("Bravo " + estGagnant().getNom() + " tu as gagné !");
		
	}
	
	/**
	 * Effectue le tour d'un joueur
	 * 
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
				
				actionChoisie = joueurTestP.choisitAction(booleanASaute);
				
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
			
			if(estPartieTermine())
			{
				actionChoisie = Actions.FIN;
			}
			
		}while(actionChoisie != Actions.FIN);
		
		changerJoueur();
		
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
	 * Vérifie si la partie est terminer
	 * 
	 * @return Renvoie true si la partie est terminer
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
	 * @return Renvoie le joueur ayant gagner la partie, retourne null si la partie n'est pas finie
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
	
	public String toString()
	{
		return ("\nJoueur 1 : " + joueur1.getNom()
				+"\nJoueur 2 : " + joueur2.getNom()
				+"\nEntrain de jouer : Joueur " + intJoueurCourant);
	}

}
