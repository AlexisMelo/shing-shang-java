package domaine;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 *Liste des actions pouvant être effectuées pendant un tour de jeu
 */
public enum Actions
{
	/**
	 * Indique la fin de l'action
	 */
	FIN("Fin"),
	/**
	 * Permet à un bushi de se déplacer
	 */
	GLISSE("Glisse"),
	/**
	 * Permet à un bushi de sauter un autre bushi
	 */
	SAUTE("Saute"),
	/**
	 * Permet d'annuler l'action précedemment effectuée
	 */
	ANNULE("Annule");
	
	String stringNomAction;
	
	/**
	 * 
	 * @param stringNomActionP
	 * Nom de l'action
	 */
	private Actions(String stringNomActionP)
	{
		stringNomAction = stringNomActionP;
	}
	
	/**
	 * Permet de transformer une abréviation issue de la saisie utilisateur en un objet de type Action
	 * @param stringAbreviationP
	 * Abréviation de l'action à retourner
	 * @return Renvoi l'action correspondante à l'abréviation
	 * 
	 */
	public static Actions getAction(String stringAbreviationP)
	{
		if (stringAbreviationP.equals("g")) return Actions.GLISSE;
		if (stringAbreviationP.equals("a")) return Actions.ANNULE;
		if (stringAbreviationP.equals("f")) return Actions.FIN;
		if (stringAbreviationP.equals("s")) return Actions.SAUTE;
		return null;		
	}
	
	public String toString()
	{
		return ("Action : " + stringNomAction);
	}
}
