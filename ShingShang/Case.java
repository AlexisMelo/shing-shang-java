package domaine;

import java.io.Serializable;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 *
 */
public abstract class Case implements Serializable
{
	private static final long serialVersionUID = -7987414353577478532L;
	private int intX, intY;
	private Bushi bushiBushi;
  
  /**
   * Permet de créer une case
   * @param intXP
   * Coordonnée x
   * @param intYP
   * Coordonnée y
   */
  public Case(int intXP, int intYP)
  {
	  this.intX = intXP;
	  this.intY = intYP;
  }

  /**
   * 
   * @return Coordonnée X
   */
  public int getX()
  {
	  return intX;
  }
  
  /**
   * 
   * @return Coordonnée Y
   */
  public int getY()
  {
	  return intY;
  }
  
  /**
   * 
   * @return Renvoi le bushi contenu dans la case
   */
  public Bushi getBushi()
  {
	  return bushiBushi;
  }
  
  /**
   * 
   * @param intXP
   * Nouvelle coordonnée X
   */
  public void setX(int intXP)
  {
	  intX = intXP;
  }
  
  
  /**
   * 
   * @param intYP
   * Nouvelle coordonnées Y
   */
  public void setY(int intYP)
  {
	  intY = intYP;
  }
  
  /**
   * 
   * @param bushiBushiP
   * Bushi à insérer dans la case
   */
  public void setBushi(Bushi bushiBushiP)
  {
	  bushiBushi = bushiBushiP;
  }
  
  
  /**
   * 
   * @return
   * Retourne le symbole de la case
   * Egal à "." par défaut
   * Egal au glyphe du Bushi contenu dans la case si elle n'est pas vide
   * Egal à "p" ou "P" si la case est un portail
   */
  public String getGlyphe()
  {
	  if(bushiBushi != null)
	  {
		  if(bushiBushi.getCouleur() == Couleur.ROUGE)
		  {
			  return bushiBushi.getGlyphe().toUpperCase();
		  }
		  else
		  {
			  return bushiBushi.getGlyphe().toLowerCase();
		  }
		  
	  }
	  else if(this.getClass() == Portail.class)
	  {
		  Portail portail = (Portail)this;
		  if(portail.getCouleur() == Couleur.ROUGE)
		  {
			  return "P";
		  }
		  else
		  {
			  return "p";
		  }
	  }
	  
	  return ".";
	  
  }
  
  public String toString()
  {
	  return ("\nCoordonnées X : " + intX
			  +"\nCoordonnées Y " + intY
			  +"\n" 
			  + bushiBushi.toString());
  }
}