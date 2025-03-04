package domaine;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 *Liste des actions pouvant �tre effectu�es pendant un tour de jeu
 */
public enum Actions
{
	/**
	 * Indique la fin de l'action
	 */
	FIN("Fin"),
	/**
	 * Permet � un bushi de se d�placer
	 */
	GLISSE("Glisse"),
	/**
	 * Permet � un bushi de sauter un autre bushi
	 */
	SAUTE("Saute"),
	/**
	 * Permet d'annuler l'action pr�cedemment effectu�e
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
	 * Permet de transformer une abr�viation issue de la saisie utilisateur en un objet de type Action
	 * @param stringAbreviationP
	 * Abr�viation de l'action � retourner
	 * @return Renvoi l'action correspondante � l'abr�viation
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
