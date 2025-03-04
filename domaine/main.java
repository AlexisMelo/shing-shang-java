package domaine;

public class main 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Plateau plateauPrincipal = new Plateau();
		//plateauPrincipal.afficher();
		//System.out.println(plateauPrincipal.voisine(plateauPrincipal.plateau[0][1], Direction.O));
		//System.out.println(plateauPrincipal.getBushi(plateauPrincipal.plateau[0][0]));
		//System.out.println(plateauPrincipal.plateau[0][0]);
		//System.out.println(plateauPrincipal.plateau[1][2].getGlyphe());
		//System.out.println(plateauPrincipal.getBushi(plateauPrincipal.plateau[1][2]));
		//plateauPrincipal.getBushi(plateauPrincipal.plateau[1][2]).glisse(plateauPrincipal, plateauPrincipal.plateau[1][2], Direction.SE, 2);
		//plateauPrincipal.getBushi(plateauPrincipal.plateau[1][2]).saute(plateauPrincipal, plateauPrincipal.plateau[1][1], Direction.E);
		
		Joueur joueurJ1 = new Joueur(Couleur.ROUGE, "Ben");
		Joueur joueurJ2 = new Joueur(Couleur.NOIR, "Jerry");
		
		Partie partieNouvellePartie = new Partie(joueurJ1,joueurJ2,plateauPrincipal);

		partieNouvellePartie.jouerUnTour();
		
		//System.out.println(joueurJ1.choisitPiece(plateauPrincipal));
		//System.out.println(joueurJ1.choisitAction(true));
	}

}
