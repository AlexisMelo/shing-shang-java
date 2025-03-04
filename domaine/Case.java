package domaine;

public abstract class Case 
{

  private int intX, intY;
  private Bushi bushiBushi;
  
  /**
   * Créé une case
   * 
   * @param intXP
   * Coordonnée x
   * @param intYP
   * Coordonné y
   */
  public Case(int intXP, int intYP)
  {
	  this.intX = intXP;
	  this.intY = intYP;
  }

  /**
   * 
   * @return Renvoie le bushi présent sur la case
   */
  public Bushi getBushi()
  {
	  return bushiBushi;
  }
  
  /**
   * @param bushiBushiP
   * Définit le bushi sur cette case
   */
  public void setBushi(Bushi bushiBushiP)
  {
	  bushiBushi = bushiBushiP;
  }
  
  /**
   * 
   * @return Renvoie la coordonnée x
   */
  public int getX()
  {
	  return intX;
  }
  
  /**
   * Définit la coordonnée x
   * @param intXP
   * x
   */
  public void setX(int intXP)
  {
	  intX = intXP;
  }
  
  /**
   * 
   * @return Renvoie la coordonnée y
   */
  public int getY()
  {
	  return intY;
  }
  
  /**
   * Définit la coordonnée y
   * @param intYP
   * y
   */
  public void setY(int intYP)
  {
	  intY = intYP;
  }
  
  /**
   * Définit les coordonnées x et y
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
	  return ("\nCoordonnées X : " + intX
			  +"\nCoordonnées Y " + intY
			  +"\n" 
			  + bushiBushi.toString());
  }
}