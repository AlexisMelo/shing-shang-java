package domaine;
/**
 *  Alexis Melo da Silva, Valentin Bossard
 * Liste les directions possibles pour un Bushi
 *
 */
public enum Direction {
	/**
	 * Haut
	 */
	H(-1,0,"Haut","h"),
	/**
	 * Haut-Droite
	 */
	HD(-1,1,"Haut-Droite","hd"),
	/**
	 * Droite
	 */
	D(0,1,"Droite","d"),
	/**
	 * Bas-Droite
	 */
	BD(1,1,"Bas-Droite","bd"),
	/**
	 * Bas
	 */
	B(1,0,"Bas","b"),
	/**
	 * Bas-Gauche
	 */
	BG(1,-1,"Bas-Gauche","bg"),
	/**
	 * Gauche
	 */
	G(0,-1,"Gauche","g"),
	/**
	 * Haut-Gauche
	 */
	HG(-1,-1,"Haut-Gauche","hg");
	
	private int intX;
	private int intY;
	private String stringAbreviation;
	private String stringNomDirection;
	
	/**
	 * 
	 * @param intXP
	 * Coordon�e X
	 * @param intYP
	 * Coordonn�e Y
	 * @param stringNomDirectionP
	 * Nom de la direction
	 * @param stringAbreviationP
	 * Abr�viation de la direction
	 */
	Direction(int intXP, int intYP, String stringNomDirectionP, String stringAbreviationP){
		this.intX = intXP;
		this.intY = intYP;
		stringAbreviation = stringAbreviationP;
		stringNomDirection = stringNomDirectionP;
	}
	
	/**
	 * Permet de transformer une abr�viation issue de la saisie utilisateur en un objet de type Direction
	 * @param stringAbreviationP
	 * Abr�viation de la direction � retourner
	 * @return Renvoi la direction correspondante � l'abr�viation
	 */
	public static Direction getDirection(String stringAbreviationP)
	{
		if (stringAbreviationP.equals("h")) return Direction.H;
		if (stringAbreviationP.equals("b")) return Direction.B;
		if (stringAbreviationP.equals("d")) return Direction.D;
		if (stringAbreviationP.equals("g")) return Direction.G;
		if (stringAbreviationP.equals("hd")) return Direction.HD;
		if (stringAbreviationP.equals("hg")) return Direction.HG;
		if (stringAbreviationP.equals("bd")) return Direction.BD;
		if (stringAbreviationP.equals("bg")) return Direction.BG;
		return null;
	}
	
	/**
	 * 
	 * @return Coordonn�e X
	 */
	public int getX()
	{
		return intX;
	}
	
	/**
	 * 
	 * @return Coordonn�e Y
	 */
	public int getY()
	{
		return intY;
	}
	
	public String toString()
	{
		return ("Direction : " + stringNomDirection
				+"\nX : " + intX
				+"\nY : " + intY);
	}
}
