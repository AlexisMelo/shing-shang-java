package domaine;
/**
 * Liste des directions possibles et des coordonnées correspondantes
 * 
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
	 * Coordonnée x
	 * @param intYP
	 * Coordonnée y
	 * @param stringNomDirectionP
	 * Nom de la direction
	 * @param stringAbreviationP
	 * Abréviation de la direction
	 */
	Direction(int intXP, int intYP, String stringNomDirectionP, String stringAbreviationP){
		this.intX = intXP;
		this.intY = intYP;
		stringAbreviation = stringAbreviationP;
		stringNomDirection = stringNomDirectionP;
	}
	
	/**
	 * 
	 * @return Renvoie la coordonnée x
	 */
	public int getX()
	{
		return intX;
	}
	
	/**
	 * 
	 * @return Renvoie la coordonnée y
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
