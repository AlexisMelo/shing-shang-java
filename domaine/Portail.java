package domaine;

public class Portail extends Case
{

  private Couleur couleurMaCouleur;

  /**
   * Cr�� un portail
   * 
   * @param intXP
   * Coordonn�e x
   * @param intYP
   * Coordonn� y
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