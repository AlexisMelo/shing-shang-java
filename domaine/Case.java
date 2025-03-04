package domaine;

public abstract class Case 
{

  private int intX, intY;
  private Bushi bushiBushi;
  
  /**
   * Cr�� une case
   * 
   * @param intXP
   * Coordonn�e x
   * @param intYP
   * Coordonn� y
   */
  public Case(int intXP, int intYP)
  {
	  this.intX = intXP;
	  this.intY = intYP;
  }

  /**
   * 
   * @return Renvoie le bushi pr�sent sur la case
   */
  public Bushi getBushi()
  {
	  return bushiBushi;
  }
  
  /**
   * @param bushiBushiP
   * D�finit le bushi sur cette case
   */
  public void setBushi(Bushi bushiBushiP)
  {
	  bushiBushi = bushiBushiP;
  }
  
  /**
   * 
   * @return Renvoie la coordonn�e x
   */
  public int getX()
  {
	  return intX;
  }
  
  /**
   * D�finit la coordonn�e x
   * @param intXP
   * x
   */
  public void setX(int intXP)
  {
	  intX = intXP;
  }
  
  /**
   * 
   * @return Renvoie la coordonn�e y
   */
  public int getY()
  {
	  return intY;
  }
  
  /**
   * D�finit la coordonn�e y
   * @param intYP
   * y
   */
  public void setY(int intYP)
  {
	  intY = intYP;
  }
  
  /**
   * D�finit les coordonn�es x et y
   * @param intXP
   * x
   * @param intYP
   * y
   */
  public void setXY(int intXP, int intYP)
  {
	  intX = intXP;
	  intY = intYP;
  }
  
  /**
   * 
   * @return Renvoie le symbole du pion, renvoie un point si aucun pion n'est sur cette case
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