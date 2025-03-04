package domaine;
/*
 * Liste des actions qu'il est possible d'effectuer
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
	
	private Actions(String stringNomActionP)
	{
		stringNomAction = stringNomActionP;
	}
	
	public String toString()
	{
		return ("Action :" + stringNomAction);
	}
}
