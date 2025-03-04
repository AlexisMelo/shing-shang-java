package domaine;

/**
 * 
 * Liste les différents type de bushi ainsi que leur puissance et leur déplacement max
 *
 */
enum TypeBushi 
{

  Singe("S", 0, 2,"Singe"),
  Lion("L", 1, 1,"Lion"),
  Dragon("D", 2, 0,"Dragon");

  private String stringSymbole;
  private int intTaille;
  private int intDeplacementMax;
  private String stringNom;
  
  /**
   * 
   * @param stringSymboleP
   * Symbole du bushi
   * @param intTailleP
   * Puissance du bushi
   * @param intDeplacementMaxP
   * Déplacement max du bushi
   * @param stringNomP
   * Nom du bushi
   */
  private TypeBushi(String stringSymboleP, int intTailleP, int intDeplacementMaxP, String stringNomP)
  {
	  stringSymbole = stringSymboleP;
	  intTaille = intTailleP;
	  intDeplacementMax = intDeplacementMaxP;
	  stringNom = stringNomP;
  }

  /**
   * 
   * @return Renvoie le symbole du bushi
   */
  public String getSymbole()
  {
	  return stringSymbole;
  }

  /**
   * 
   * @return Renvoie la puissance du bushi
   */
  public int getTaille()
  {
	  return intTaille;
  }
  
  /**
   * 
   * @return Renvoie le nom du bushi
   */
  public String getNom()
  {
	  return stringNom;
  }
  
  /**
   * 
   * @return Renvoie le déplacement maximum du bushi
   */
  public int getDeplacementMax()
  {
	  return intDeplacementMax;
  }
  
  public String toString()
  {
	  return ("\nType : " + stringNom
			  +"\nTaille : " + intTaille
			  +"\nDeplacement maximal : " + intDeplacementMax + " cases"
			  +"\nSymbole : " + stringSymbole);
  }
}