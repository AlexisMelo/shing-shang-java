package domaine;

/**
 *
 * @author Alexis Melo da Silva, Valentin Bossard
 *
 */
public class Portail extends Case
{

  private Couleur couleurMaCouleur;

  /**
   * 
   * @param intXP
   * Coordonnée X
   * @param intYP
   * Coordonnée Y
   * @param couleurMaCouleurP
   * Couleur du portail
   */
  public Portail(int intXP, int intYP, Couleur couleurMaCouleurP) 
  {
	  super(intXP, intYP);
	  couleurMaCouleur = couleurMaCouleurP;
  }
  
  /**
   * 
   * @return couleur du portail
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