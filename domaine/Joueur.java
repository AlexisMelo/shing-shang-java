 package domaine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * 
 *
 */
public class Joueur 
{

	private LinkedList<Bushi> linkedlistArmee;
	private String stringNom;
	
	/**
	 * Créer un joueur
	 * @param couleurCouleurP
	 * La couleur du joueur
	 * @param stringNomP
	 * Le nom du joueur
	 */
	public Joueur(Couleur couleurCouleurP, String stringNomP)
	{
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
	 * Remplace la liste de bushi du joueur
	 * @param linkedlistArmeeP
	 * La liste de bushi
	 */
	public void remplaceArmee(LinkedList<Bushi> linkedlistArmeeP)
	{
		this.linkedlistArmee = linkedlistArmeeP;
	}
	
	/**
	 * 
	 * @param bushiPerduP
	 * bushi a supprimer de la liste
	 */
	public void perdBushi(Bushi bushiPerduP)
	{
		linkedlistArmee.remove(bushiPerduP);
	}

	/**
	 * 
	 * @param booleanASautee
	 * true si le bushi a déjà effectuer un saut
	 * @return renvoie l'action choisit par le joueur
	 */
	public Actions choisitAction(boolean booleanASautee)
	{
		Actions actionFinale = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(stringNom + " : Choisissez une action\n");
		int intChoix;
		
		if(booleanASautee)
		{
			System.out.println("1) Fin\n3) Saute");
			do
			{
				intChoix = sc.nextInt();
			}while(intChoix != 1 && intChoix != 3);
			
		}
		else
		{
			System.out.println("2) Glisse\n3) Saute\n4) Annule");
			do
			{
				intChoix = sc.nextInt();
			}while(intChoix!= 2 && intChoix!= 3 && intChoix!= 4);
		}
		
		switch (intChoix)
		{
			case 1: 
				actionFinale = Actions.FIN;
				break;
			case 2:
				actionFinale = Actions.GLISSE;
				break;
			case 3:
				actionFinale = Actions.SAUTE;
				break;
			case 4:
				actionFinale = Actions.ANNULE;
				break;
			default: actionFinale = null;
			break;
		}
		
		return actionFinale;
	}
	
	/**
	 * 
	 * @param plateauPlateauP
	 * Plateau actuel
	 * @return Renvoie le bushi choisit par le joueur
	 */
	public Bushi choisitBushi(Plateau plateauPlateauP)
	{
		
		Scanner sc = new Scanner(System.in);
		int intX = -1, intY = -1;
		
		do
		{
			System.out.println(stringNom + " : Entrez les coordonnées du bushi");
			
			System.out.print("x: ");
			do
			{
				try 
				{
					intX = sc.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Veuillez entrez un chiffre !");
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
					System.out.println("Veuillez entrez un chiffre !");
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
	 * @return Renvoie la direction choisit par le joueur
	 */
	public Direction choisitDirection()
	{
		Direction directionFinale=null;
		ArrayList<String> arraylistSaisie = new ArrayList<String>();
		String stringDirection;
		
		remplirListeSaisieDirection(arraylistSaisie);
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
		}while(!arraylistSaisie.contains(stringDirection));
			
		switch (stringDirection)
		{
			case "h": 
				directionFinale = Direction.H;
				break;
			case "hd":
				directionFinale = Direction.HD;
				break;
			case "d":
				directionFinale = Direction.D;
				break;
			case "bd":
				directionFinale = Direction.BD;
				break;
			case "b":
				directionFinale = Direction.B;
				break;
			case "bg":
				directionFinale = Direction.BG;
				break;
			case "g":
				directionFinale = Direction.G;
				break;
			case "hg":
				directionFinale = Direction.HG;
				break;
			default: directionFinale = null;
			break;
		}
		return directionFinale;
	}

	/**
	 * 
	 * @param l
	 * Liste des abréviation de direction
	 */
	public void remplirListeSaisieDirection(ArrayList<String> l)
	{
		l.add("h");
		l.add("hd");
		l.add("d");
		l.add("bd");
		l.add("b");
		l.add("bg");
		l.add("g");
		l.add("hg");
	}

	/**
	 * 
	 * @return Renvoie la distance choisit par le joueur
	 */
	public int choisitDistance()
	{
        Scanner sc = new Scanner(System.in);
        System.out.println(stringNom + " : Veuillez saisir une distance :");
        int intLongueur = sc.nextInt();
        return intLongueur;
    }

	
	public String toString()
	{
		return ("\nNom du joueur : " + stringNom
				+"\nSon armée : \n" + linkedlistArmee.toString());
	}
}
