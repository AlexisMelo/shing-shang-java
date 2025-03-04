package domaine;

public class Bushi
{

	private TypeBushi typebushiMonType;
	private Couleur couleurMaCouleur;
	private int intIndex;

	/**
	 * Cr�� un bushi
	 * 
	 * @param typebushiTypeP
	 * Le type de bushi
	 * @param couleurCouleurP
	 * La couleur du bushi
	 * @param intIndexP
	 * Le num�ro du bushi
	 */
	public Bushi(TypeBushi typebushiTypeP, Couleur couleurCouleurP, int intIndexP)
	{
		typebushiMonType = typebushiTypeP;
		couleurMaCouleur = couleurCouleurP;
		intIndex = intIndexP;
	}

	/**
	 * 
	 * @return Renvoie la couleur du bushi
	 */
	public Couleur getCouleur()
	{
		return couleurMaCouleur;
	}
	
	/**
	 * 
	 * @return Renvoie le type du bushi
	 */
	public TypeBushi getTypeBushi()
	{
		return typebushiMonType;
	}
	
	/**
	 * 
	 * @return Renvoie le symbole du bushi
	 */
	public String getGlyphe()
	{
		return typebushiMonType.getSymbole();
	}

	/**
	 * 
	 * @param bushiBushiP
	 * Le bushi a comparer
	 * @return Renvoie true si le bushi est plus grand que le bushi placer en param�tre
	 */
	public boolean estPlusGrandOuEgalA(Bushi bushiBushiP) 
	{
		return (bushiBushiP != null 
				&& this.typebushiMonType.getTaille() >= bushiBushiP.typebushiMonType.getTaille());
	}

	/**
	 * 
	 * @param plateauPlateau
	 * Le plateau actuelle
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut d�placer
	 * @param directionVersP
	 * La direction vers laquelle ont veux d�placer le bushi
	 * @param intDistanceP
	 * La distance de d�placement
	 */
	public void glisse(Plateau plateauPlateau, Case casePositionP, Direction directionVersP, int intDistanceP) 
	{
		Case caseTemporaire = null;

		if (intDistanceP == 1)
		{
			caseTemporaire = plateauPlateau.voisine(casePositionP, directionVersP);
		}
		else
		{
			caseTemporaire = plateauPlateau.voisine(plateauPlateau.voisine(casePositionP, directionVersP), directionVersP);
		}
		
		if (plateauPlateau.estVide(caseTemporaire)) 
		{
			plateauPlateau.setCaseCourante(casePositionP);
			plateauPlateau.deplaceBushi(caseTemporaire);
		}
	}

	/**
	 * 
	 * @param plateauPlateau
	 * Le plateau actuelle
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut d�placer
	 * @param directionVersP
	 * La direction vers laquelle ont veux d�placer le bushi
	 * 
	 * @return Renvoie le bushi sauter
	 */
	public Bushi saute(Plateau plateauPlateau, Case casePositionP, Direction directionVersP) 
	{
		plateauPlateau.setCaseCourante(casePositionP);
		plateauPlateau.deplaceBushi(plateauPlateau.voisine(plateauPlateau.voisine(casePositionP, directionVersP), directionVersP));
		// plateauPlateauP.marqueAEffacer(plateauPlateauP.voisine(casePositionP, directionVersP));
		return plateauPlateau.voisine(casePositionP, directionVersP).getBushi();
	}

	/**
	 * 
	 * @param plateauPlateau
	 * Le plateau actuelle
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut d�placer
	 * @param directionVersP
	 * La direction vers laquelle ont veux d�placer le bushi
	 * @param intDistanceP
	 * La distance de d�placement
	 * 
	 * @return Renvoie true si le glissement est possible
	 */
	public boolean glissePossible(Plateau plateauPlateau, Case casePositionP, Direction directionVersP, int intDistanceP) {

		Case caseTemporaire = null;

		if (intDistanceP == 1)
		{
			caseTemporaire = plateauPlateau.voisine(casePositionP, directionVersP);
		} 
		else if (plateauPlateau.estVide(plateauPlateau.voisine(casePositionP, directionVersP)) 
				&& plateauPlateau.voisine(casePositionP, directionVersP).getClass() != Portail.class) 
		{
			caseTemporaire = plateauPlateau.voisine(plateauPlateau.voisine(casePositionP, directionVersP), directionVersP);
		}

		if (plateauPlateau.estVide(caseTemporaire) 
				&& caseTemporaire.getClass() != Portail.class)
		{
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param plateauPlateauP
	 * Le plateau actuelle
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut d�placer
	 * @param directionVersP
	 * La direction vers laquelle ont veux d�placer le bushi
	 * @param casePositionPPrecedent
	 * La case du tour pr�c�dent
	 * 
	 * @return Renvoie true si le saut est possible
	 */
	public boolean sautPossible(Plateau plateauPlateauP, Case casePositionP, Direction directionVersP, Case casePositionPPrecedent) 
	{
		Case caseArrive = plateauPlateauP.voisine(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP);
		Portail casePlateau = null;

		if (caseArrive.getClass() == Portail.class) 
		{
			casePlateau = plateauPlateauP.voisinePortail(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP);
		}

		if (casePositionP.getBushi().estPlusGrandOuEgalA(plateauPlateauP.voisine(casePositionP, directionVersP).getBushi())
				&& casePositionPPrecedent != caseArrive
				&& ((caseArrive.getClass() == Portail.class 
				&& typebushiMonType.getSymbole() == "D"
				&& couleurMaCouleur != casePlateau.getCouleur()) || caseArrive.getClass() == CaseSimple.class)) 
		{
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param intDistanceP
	 * La distance de d�placement
	 * 
	 * @return Renvoie true si la distance est correct
	 */
	public boolean verifDistance(int intDistanceP) 
	{
		if (this.typebushiMonType.getDeplacementMax() >= intDistanceP && intDistanceP >= 1) 
		{
			if ((this.typebushiMonType.getSymbole() == "L" && intDistanceP == 1) 
					|| this.typebushiMonType.getSymbole() == "S") 
			{
				return true;
			}
		}

		return false;
	}
	
	public String toString()
	{
		return ("\nType du bushi : " + typebushiMonType.getNom() 
				+"\nCouleur : " + couleurMaCouleur.getNom()
				+"\nIndex : " + intIndex );
	} 
	
}