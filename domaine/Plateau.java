package domaine;

import java.util.LinkedList;

public class Plateau
{
	/**
	 * @see Case
	 */
	private Case caseCourante;
	/**
	 * @see Case
	 */
	private Case[][] casePlateau;
	
	/**
	 * Cr�� un plateau de cases
	 */
	public Plateau() 
	{
		casePlateau = new Case[10][10];
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if((j == 0 || j == 9) && (i < 4 || i > 5))
				{
					casePlateau[i][j] = null;
				}
				else if(i == 1 && (j == 4 || j == 5))
				{
					casePlateau[i][j] = new Portail(i, j, Couleur.ROUGE);
				}
				else if(i == 8 && (j == 4 || j == 5))
				{
					casePlateau[i][j] = new Portail(i, j, Couleur.NOIR);
				}
				else
				{
					casePlateau[i][j] = new CaseSimple(i, j);
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @return Renvoie la case courante
	 */
	public Case getCaseCourante() 
	{
		return caseCourante;
	}	

	/**
	 * 
	 * @param intXP
	 * Coordonn�e x
	 * @param intYP
	 * Coordonn�e y
	 * @return Renvoie la case ayant les coordonn�e passer en param�tre
	 */
	public Case getCasePlateau(int intXP, int intYP)
	{
		return casePlateau[intXP][intYP];
	}
	
	/**
	 * D�finit la nouvelle case courante
	 * @param caseCouranteP
	 * Case courante
	 */
	public void setCaseCourante(Case caseCouranteP)
	{
		caseCourante = caseCouranteP;
	}
	
	/**
	 * Affiche le plateau
	 */
	public void afficher() 
    {
        System.out.print("  Y ");
        
        for(int i = 0; i < 10; i++)
        {
            System.out.print(i + "  ");
        }

        System.out.println("");
        System.out.println("X");
        for(int i = 0; i < 10; i++)
        {
            System.out.print(i + "   ");
            for(int j = 0; j < 10; j++)
            {
                if(casePlateau[i][j] != null)
                {
                	System.out.print(casePlateau[i][j].getGlyphe());
                    System.out.print("  ");
                }
                else
                {
                    System.out.print("   ");
                }

            }
            System.out.println("");
        }

    }
	
	/**
	 * 
	 * @param caseAncienneP
	 * Case ayant le bushi actuelle
	 * @param directionVoisineP
	 * Direction que doit prendre le bushi
	 * @return Renvoie la case d'arriver
	 */
	public Case voisine(Case caseAncienneP, Direction directionVoisineP) 
	{
		if(caseAncienneP != null 
				&& caseAncienneP.getX()+directionVoisineP.getX() >=0 
				&& caseAncienneP.getX()+directionVoisineP.getX() <= 9 
				&& caseAncienneP.getY()+directionVoisineP.getY() >= 0 
				&& caseAncienneP.getY()+directionVoisineP.getY() <= 9)
		{
			return casePlateau[caseAncienneP.getX() + directionVoisineP.getX()][caseAncienneP.getY() + directionVoisineP.getY()];
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param caseAncienneP
	 * Case ayant le bushi actuelle
	 * @param directionVoisineP
	 * Direction que doit prendre le bushi
	 * @return Renvoie le portail d'arriver
	 */
	public Portail voisinePortail(Case caseAncienneP, Direction directionVoisineP) 
	{
		if(caseAncienneP != null 
				&& caseAncienneP.getX()+directionVoisineP.getX() >=0 
				&& caseAncienneP.getX()+directionVoisineP.getX() <= 9 
				&& caseAncienneP.getY()+directionVoisineP.getY() >= 0 
				&& caseAncienneP.getY()+directionVoisineP.getY() <= 9)
		{
			return (Portail)casePlateau[caseAncienneP.getX() + directionVoisineP.getX()][caseAncienneP.getY() + directionVoisineP.getY()];
		}
		
		return null;
	}

	/**
	 * V�rifie si la case contient un bushi
	 * @param caseTesteeP
	 * Case a tester
	 * @return Renvoie true si la case ne contient pas de bushi
	 */
	public boolean estVide(Case caseTesteeP) 
	{
		return (caseTesteeP != null 
				&& casePlateau[caseTesteeP.getX()][caseTesteeP.getY()].getBushi() == null);
	}

	/**
	 * D�place lu bushi
	 * @param caseNouvelleP
	 * Case ayant le bushi a deplacer
	 */
	public void deplaceBushi(Case caseNouvelleP) 
	{
		caseNouvelleP.setBushi(getCaseCourante().getBushi());
		marqueAEffacer(getCaseCourante());
	}
	
	/**
	 * 
	 * @param caseChoisieP
	 * Case poss�dent le bushi
	 * @return Renvoie le bushi
	 */
	public Bushi getBushi(Case caseChoisieP) 
	{
		if(caseChoisieP != null 
				&& casePlateau[caseChoisieP.getX()][caseChoisieP.getY()] != null)
		{
			return casePlateau[caseChoisieP.getX()][caseChoisieP.getY()].getBushi();
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * @param bushiChercheP
	 * Un bushi
	 * @return Renvoie la case o� se trouve le bushi
	 */
	public Case getCase(Bushi bushiChercheP) 
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(casePlateau[i][j] != null
						&& bushiChercheP != null 
						&& bushiChercheP == casePlateau[i][j].getBushi())
				{
					return casePlateau[i][j];
				}
			}
		}
		
		return null;
	}

	/**
	 * Supprime un bushi
	 * @param caseEffaceP
	 * Case ayant le bushi a supprimer
	 */
	public void marqueAEffacer(Case caseEffaceP) 
	{
		caseEffaceP.setBushi(null);
	}
		
	/**
	 * Place les bushi d'un joueur sur le plateau
	 * @param linkedlistArmee
	 * liste de bushi
	 */
	public void poseArmee(LinkedList<Bushi> linkedlistArmee)
	{
		if(linkedlistArmee.get(0).getCouleur() == Couleur.ROUGE)
		{
			casePlateau[0][1].setBushi(linkedlistArmee.get(0));
			casePlateau[0][8].setBushi(linkedlistArmee.get(1));
			
			casePlateau[1][1].setBushi(linkedlistArmee.get(2));
			casePlateau[0][2].setBushi(linkedlistArmee.get(3));
			casePlateau[1][8].setBushi(linkedlistArmee.get(4));
			casePlateau[0][7].setBushi(linkedlistArmee.get(5));
			
			casePlateau[2][1].setBushi(linkedlistArmee.get(6));
			casePlateau[1][2].setBushi(linkedlistArmee.get(7));
			casePlateau[0][3].setBushi(linkedlistArmee.get(8));
			casePlateau[0][6].setBushi(linkedlistArmee.get(9));
			casePlateau[1][7].setBushi(linkedlistArmee.get(10));
			casePlateau[2][8].setBushi(linkedlistArmee.get(11));
		}
		else
		{
			casePlateau[9][1].setBushi(linkedlistArmee.get(0));
			casePlateau[9][8].setBushi(linkedlistArmee.get(1));
			
			casePlateau[8][1].setBushi(linkedlistArmee.get(2));
			casePlateau[9][2].setBushi(linkedlistArmee.get(3));
			casePlateau[8][8].setBushi(linkedlistArmee.get(4));
			casePlateau[9][7].setBushi(linkedlistArmee.get(5));
			
			casePlateau[7][1].setBushi(linkedlistArmee.get(6));
			casePlateau[8][2].setBushi(linkedlistArmee.get(7));
			casePlateau[9][3].setBushi(linkedlistArmee.get(8));
			casePlateau[9][6].setBushi(linkedlistArmee.get(9));
			casePlateau[8][7].setBushi(linkedlistArmee.get(10));
			casePlateau[7][8].setBushi(linkedlistArmee.get(11));
		}
		
	}
	
	public String toString()
	{
		return ("\nCase courante : " + caseCourante.toString());
	}
}
