package domaine;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 *Liste des actions pouvant être effectuées sur le menu principal du jeu
 */
public enum ActionsMenu {
	
	/**
	 * Créer et lancer une nouvelle partie
	 */
	NOUVELLE("Nouvelle"),
	
	/**
	 * Lancer une partie à partir d'une sauvegarde
	 */
	CHARGER("Charger"),
	
	/**
	 * Supprimer une sauvegarde
	 */
	SUPPRIMER("Supprimer"),
	
	/**
	 * Afficher les règles du jeu
	 */
	REGLES("Regles"),
	
	/**
	 * Quitter le jeu
	 */
	QUITTER("Quitter");
	
	String stringNomAction;
	
	/**
	 * 
	 * @param stringNomActionP
	 * Nom de l'action
	 */
	private ActionsMenu(String stringNomActionP)
	{
		stringNomAction = stringNomActionP;
	}
	
	
	/**
	 * Permet de transformer une abréviation issue de la saisie utilisateur en un objet de type ActionsMenu
	 * @param stringAbreviationP
	 * Abréviation de l'actionMenu à retourner
	 * @return Renvoi l'actionMenu correspondante à l'abréviation
	 * 
	 */
	public static ActionsMenu getAction(String stringAbreviationP)
	{
		if(stringAbreviationP.equals("n")) return ActionsMenu.NOUVELLE;
		if(stringAbreviationP.equals("c")) return ActionsMenu.CHARGER;
		if(stringAbreviationP.equals("s")) return ActionsMenu.SUPPRIMER;
		if(stringAbreviationP.equals("r")) return ActionsMenu.REGLES;
		if(stringAbreviationP.equals("q")) return ActionsMenu.QUITTER;
		return null;
	}
	
	public String toString()
	{
		return ("Action menu : " + stringNomAction);
	}
	
}
