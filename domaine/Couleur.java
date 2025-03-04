package domaine;
/**
 * @author Alexis Melo da Silva, Valentin Bossard
 * Liste des couleurs possibles pour un joueur 
 */
public enum Couleur 
{
	NOIR("Noir"),
	ROUGE("Rouge");
	
	String stringNom;
	
	/**
	 * 
	 * @param stringNomP
	 * Nom de la couleur
	 */
	private Couleur(String stringNomP)
	{
		stringNom = stringNomP;
	}

	/**
	 * Permet de transformer une abréviation issue de la saisie utilisateur en un objet de type Couleur
	 * @param stringAbreviationP
	 * Abréviation de la couleur à retourner
	 * @return Renvoi la couleur correspondante à l'abréviation
	 */
	public static Couleur getCouleur(String stringAbreviationP)
	{
		if(stringAbreviationP.equals("n")) return Couleur.NOIR;
		if(stringAbreviationP.equals("r")) return Couleur.ROUGE;
		return null;
	}
	
	/**
	 * Utile pour la création des joueurs et éviter de redemander la couleur du 2eme joueur
	 * Méthode à modifier en cas d'extension du projet avec d'autres couleurs
	 * @param couleurPremiereP
	 * Abréviation de la couleur que l'on ne souhaite pas récuperer
	 * @return Si 2 couleurs différentes, retourne la couleur ne correspondant pas à l'abréviation
	 */
	public static Couleur getCouleurInverse(Couleur couleurPremiereP)
	{
		if(couleurPremiereP.equals(Couleur.NOIR)) return Couleur.ROUGE;
		if(couleurPremiereP.equals(Couleur.ROUGE)) return Couleur.NOIR;
		return null;
	}

	/**
	 * 
	 * @return Nom de la couleur
	 */
	public String getNom()
	{
		return stringNom;
	}
	
	public String toString()
	{
		return ("Couleur : "+stringNom);
	}
}
