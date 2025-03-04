package domaine;
/**
 * Liste des couleurs
 */
public enum Couleur 
{
	NOIR("Noir"),
	ROUGE("Rouge");
	
	String stringNom;
	
	/**
	 * 
	 * @param stringNomP
	 * La couleur
	 */
	private Couleur(String stringNomP)
	{
		stringNom = stringNomP;
	}

	public char glyphe(char charGlyphe){
		return charGlyphe;
	}
	
	/**
	 * 
	 * @return renvoie le nom de la couleur
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
