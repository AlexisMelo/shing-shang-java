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
   * Permet de cr�er une case
   * @param intXP
   * Coordonn�e x
   * @param intYP
   * Coordonn�e y
   */
  public Case(int intXP, int intYP)
  {
	  this.intX = intXP;
	  this.intY = intYP;
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
   * Nouvelle coordonn�e X
   */
  public void setX(int intXP)
  {
	  intX = intXP;
  }
  
  
  /**
   * 
   * @param intYP
   * Nouvelle coordonn�es Y
   */
  public void setY(int intYP)
  {
	  intY = intYP;
  }
  
  /**
   * 
   * @param bushiBushiP
   * Bushi � ins�rer dans la case
   */
  public void setBushi(Bushi bushiBushiP)
  {
	  bushiBushi = bushiBushiP;
  }
  
  
  /**
   * 
   * @return
   * Retourne le symbole de la case
   * Egal � "." par d�faut
   * Egal au glyphe du Bushi contenu dans la case si elle n'est pas vide
   * Egal � "p" ou "P" si la case est un portail
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
	  return ("\nCoordonn�es X : " + intX
			  +"\nCoordonn�es Y " + intY
			  +"\n" 
			  + bushiBushi.toString());
  }
}