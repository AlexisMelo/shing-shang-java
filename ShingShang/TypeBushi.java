package domaine;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 * Liste des types de bushi existants
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
   * Symbole du type de bushi
   * @param intTailleP
   * Taille ( puissance ) du type de Bushi
   * @param intDeplacementMaxP
   * Nombre de cases jusqu'où le type de bushi peut se déplacer
   * @param stringNomP
   * Nom du type de Bushi
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
   * @return Glyphe du type
   */
  public String getSymbole()
  {
	  return stringSymbole;
  }

  /**
   * 
   * @return Puissance du type
   */
  public int getTaille()
  {
	  return intTaille;
  }
  
  /**
   * 
   * @return Nom du type
   */
  public String getNom()
  {
	  return stringNom;
  }
  
  /**
   * 
   * @return Distance maximale de glissage du type
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