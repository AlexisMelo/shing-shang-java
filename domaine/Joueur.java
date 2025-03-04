 package domaine;

 import java.io.Serializable;
 import java.util.InputMismatchException;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 /**
  * 
  * @author Alexis Melo da Silva, Valentin Bossard
  *
  */
public class Joueur implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -71602216997182264L;
	private LinkedList<Bushi> linkedlistArmee;
	private String stringNom;
	private Couleur couleurJoueur;
	
	private int intNbTour = 0;
	private int intNbSaut = 0;
	private int intNbShingShang = 0;
	
	/**
	 * Créer un joueur
	 * @param couleurCouleurP
	 * La couleur du joueur
	 * @param stringNomP
	 * Le nom du joueur
	 */
	public Joueur(Couleur couleurCouleurP, String stringNomP)
	{
		couleurJoueur = couleurCouleurP;
		linkedlistArmee = new LinkedList<Bushi>();
		this.stringNom = stringNomP;
		
		for(int i = 1; i <=2 ; i++)
		{
			linkedlistArmee.add(new Bushi(TypeBushi.Dragon, couleurCouleurP, i));
		}
		
		for(int i = 1; i <=4 ; i++)
		{
			linkedlistArmee.add(new Bushi(TypeBushi.Lion, couleurCouleurP, i));
		}
		
		for(int i = 1; i <=6 ; i++)
		{
			linkedlistArmee.add(new Bushi(TypeBushi.Singe, couleurCouleurP, i));
		}		
	}
	
	/**
	 * 
	 * @param bushiPerduP
	 * bushi à supprimer de la liste
	 */
	public void perdBushi(Bushi bushiPerduP)
	{
		linkedlistArmee.remove(bushiPerduP);
	}

	/**
	 * 
	 * @param booleanASauteeP
	 * true si le bushi a déjà effectué un saut
	 * @param bushiP
	 * Le bushi impacté par l'action
	 * @return renvoie l'action choisie par le joueur
	 */
	public Actions choisitAction(boolean booleanASauteeP, Bushi bushiP)
	{
		String stringChoix;
		
		Scanner sc = new Scanner(System.in);
		System.out.println(stringNom + " : Choisissez une action\n");
		
		if(booleanASauteeP)
		{
			System.out.println("f) Fin\ns) Saute\n");
			do
			{
				stringChoix = sc.nextLine().toLowerCase();
			}while(!stringChoix.equals("f") && !stringChoix.equals("s"));
		}
		else if(bushiP.getTypeBushi() == TypeBushi.Dragon)
		{
			System.out.println("s) Saute\na) Annule\n");
			do
			{
				stringChoix = sc.nextLine().toLowerCase();
				System.out.println(stringChoix);
			}while(!stringChoix.equals("s") && !stringChoix.equals("a"));
		}
		else
		{
			System.out.println("g) Glisse\ns) Saute\na) Annule\n");
			do
			{
				stringChoix = sc.nextLine().toLowerCase();
				System.out.println(stringChoix);
			}while(!stringChoix.equals("g") && !stringChoix.equals("s") && !stringChoix.equals("a"));
		}
		
		System.out.println(Actions.getAction(stringChoix));
		return Actions.getAction(stringChoix);
	}
	
	/**
	 * 
	 * @param plateauPlateauP
	 * Plateau actuel
	 * @return Renvoie le bushi choisi par le joueur
	 */
	public Bushi choisitBushi(Plateau plateauPlateauP)
	{
		
		Scanner sc = new Scanner(System.in);
		int intX = -1,intY = -1;
		
		do
		{

			System.out.println("\nà " + stringNom + " de jouer !");
			System.out.println("\nCoordonnées du Bushi à déplacer : ");
			System.out.print("x: ");
			do
			{
				try 
				{
					intX = sc.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Veuillez entrer un chiffre !");
					System.out.print("x: ");
					sc.nextLine();
				}
				
			}while(intX == -1);
			
			
			System.out.print("y: ");
			do
			{
				try 
				{
					intY = sc.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Veuillez entrer un chiffre !");
					System.out.print("y: ");
					sc.nextLine();
				}
				
			}while(intY == -1);
				
		}while(intX < 0 
				|| intX > 9 
				|| intY < 0 
				|| intY > 9
				|| !linkedlistArmee.contains(plateauPlateauP.getBushi(plateauPlateauP.getCasePlateau(intX,intY))));
		
		return plateauPlateauP.getBushi(plateauPlateauP.getCasePlateau(intX,intY));
	}
	
	/**
	 * 
	 * @return Renvoie la direction choisie par le joueur
	 */
	public Direction choisitDirection()
	{
		Direction directionFinale=null;
		String stringDirection;
		
		Scanner sc = new Scanner(System.in);
		System.out.println(stringNom + " : Choisissez la direction\n");
		System.out.println("H  - en Haut \n"
				+ "HD - en Haut à Droite \n"
				+ "D  - à  Droite \n"
				+ "BD - en Bas à Droite \n"
				+ "B  - en Bas \n"
				+ "BG - en Bas à Gauche \n"
				+ "G  - à  Gauche \n"
				+ "HG - en Haut à Gauche");
		do
		{
			stringDirection = sc.nextLine().toLowerCase();
		}while((directionFinale = Direction.getDirection(stringDirection)) == null);
			
		return directionFinale;
	}

	/**
	 * 
	 * @return Renvoie la distance choisie par le joueur
	 */
	public int choisitDistance()
	{
        Scanner sc = new Scanner(System.in);
        System.out.println(stringNom + " : Veuillez saisir une distance :");
        int intLongueur = -1;
        
        do
		{
			try 
			{
				intLongueur = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Veuillez saisir un chiffre !");
				sc.nextLine();
			}
			
		}while(intLongueur == -1);
        
        return intLongueur;
    }

	public void incrementerNbTour()
	{
		intNbTour++;
	}
	
	public void incrementerNbSaut()
	{
		intNbSaut++;
	}
	
	public void incrementerNbShingShang()
	{
		intNbShingShang++;
	}
	
	/**
	 * 
	 * @return Renvoie la liste de bushi
	 */
	public LinkedList<Bushi> getArmee()
	{
		return this.linkedlistArmee;
	}
	
	/**
	 * 
	 * @return Renvoie le nom du joueur
	 */
	public String getNom()
	{
		return stringNom;
	}
	
	/**
	 * 
	 * @return Renvoie la couleur du joueur
	 */
	public Couleur getCouleur()
	{
		return this.couleurJoueur;
	}
	
	/**
	 * 
	 * @return Renvoie le nombre de tours joués par le joueur
	 */
	public int getNbTour()
	{
		return intNbTour;
	}
	
	/**
	 * 
	 * @return Renvoie le nombre de sauts effectués par le joueur
	 */
	public int getNbSaut()
	{
		return intNbSaut;
	}
	
	/**
	 * 
	 * @return Renvoie le nombre de ShingShang effectués par le joueur
	 */
	public int getNbShingShang()
	{
		return intNbShingShang;
	}
	
	/**
	 * Remplace la liste de bushi du joueur
	 * @param linkedlistArmeeP
	 * La liste de bushi
	 */
	public void setArmee(LinkedList<Bushi> linkedlistArmeeP)
	{
		this.linkedlistArmee = linkedlistArmeeP;
	}
	
	/**
	 * 
	 * @param nom Nouveau nom du joueur
	 */
	public void setNom(String nom)
	{
		stringNom = nom;
	}
	
	public String toString()
	{
		return ("\nNom du joueur : " + stringNom
				+"\nCouleur armée : " + couleurJoueur
				+"\nSon armée : \n" + linkedlistArmee.toString());
	}
}
