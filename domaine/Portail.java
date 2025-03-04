package domaine;

public class Portail extends Case
{

  private Couleur couleurMaCouleur;

  /**
   * Créé un portail
   * 
   * @param intXP
   * Coordonnée x
   * @param intYP
   * Coordonné y
   * @param couleurMaCouleurP
   * La couleur du portail
   */
  public Portail(int intXP, int intYP, Couleur couleurMaCouleurP) 
  {
	  super(intXP, intYP);
	  couleurMaCouleur = couleurMaCouleurP;
  }
  
  /**
   * 
   * @return Renvoie la couleur du portail
   */
  public Couleur getCouleur()
  {
	  return couleurMaCouleur;
  }
  
  public String toString()
  {
	  return ("Couleur : " + couleurMaCouleur.getNom()
	  			+ super.toString());
  }
}