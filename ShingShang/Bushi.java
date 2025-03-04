package domaine;

import java.io.Serializable;

/**
 * 
 * @author Alexis Melo da Silva, Valentin Bossard
 *
 */
public class Bushi implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7513025696163525330L;
	private TypeBushi typebushiMonType;
	private Couleur couleurMaCouleur;
	private int intIndex;

	/**
	 * Créer un bushi
	 * @param typebushiTypeP
	 * Le type du bushi
	 * @param couleurCouleurP
	 * La couleur du bushi
	 * @param intIndexP
	 * Le numéro du bushi
	 */
	public Bushi(TypeBushi typebushiTypeP, Couleur couleurCouleurP, int intIndexP)
	{
		typebushiMonType = typebushiTypeP;
		couleurMaCouleur = couleurCouleurP;
		intIndex = intIndexP;
	}

	/**
	 * 
	 * @param bushiBushiP
	 * Le bushi à comparer
	 * @return Renvoie true si le bushi est plus grand que le bushi placé en paramètre
	 */
	public boolean estPlusGrandOuEgalA(Bushi bushiBushiP) 
	{
		return (bushiBushiP != null 
				&& this.typebushiMonType.getTaille() >= bushiBushiP.typebushiMonType.getTaille());
	}

	/**
	 * 
	 * @param plateauPlateau
	 * Le plateau actuel
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut déplacer
	 * @param directionVersP
	 * La direction vers laquelle on veut déplacer le bushi
	 * @param intDistanceP
	 * La distance de déplacement
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
			plateauPlateau.deplacerBushi(caseTemporaire);
		}
	}

	/**
	 * 
	 * @param plateauPlateauP
	 * Le plateau actuel
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut déplacer
	 * @param directionVersP
	 * La direction vers laquelle on veut déplacer le bushi
	 * 
	 * @return Renvoie le bushi sauté
	 */
	public Bushi saute(Plateau plateauPlateauP, Case casePositionP, Direction directionVersP) 
	{
		plateauPlateauP.setCaseCourante(casePositionP);
		plateauPlateauP.deplacerBushi(plateauPlateauP.voisine(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP));
		return plateauPlateauP.voisine(casePositionP, directionVersP).getBushi();
	}

	/**
	 * 
	 * @param plateauPlateauP
	 * Le plateau actuel
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut déplacer
	 * @param directionVersP
	 * La direction vers laquelle on veut déplacer le bushi
	 * @param intDistanceP
	 * La distance de déplacement
	 * 
	 * @return Renvoie true si le glissement est possible
	 */
	public boolean glissePossible(Plateau plateauPlateauP, Case casePositionP, Direction directionVersP, int intDistanceP) {

		Case caseTemporaire = null;

		if (intDistanceP == 1)
		{
			caseTemporaire = plateauPlateauP.voisine(casePositionP, directionVersP);
		} 
		else if (plateauPlateauP.estVide(plateauPlateauP.voisine(casePositionP, directionVersP)) 
				&& plateauPlateauP.voisine(casePositionP, directionVersP).getClass() != Portail.class) 
		{
			caseTemporaire = plateauPlateauP.voisine(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP);
		}

		if (plateauPlateauP.estVide(caseTemporaire) 
				&& caseTemporaire.getClass() != Portail.class)
		{
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param plateauPlateauP
	 * Le plateau actuel
	 * @param casePositionP
	 * La case ayant le bushi que l'on veut déplacer
	 * @param directionVersP
	 * La direction vers laquelle on veut déplacer le bushi
	 * @param casePositionPrecedentP
	 * La case du tour précédent
	 * 
	 * @return Renvoie true si le saut est possible
	 */
	public boolean sautPossible(Plateau plateauPlateauP, Case casePositionP, Direction directionVersP, Case casePositionPrecedentP) 
	{
		Case caseArrive = plateauPlateauP.voisine(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP);
		Portail casePlateau = null;

		if (plateauPlateauP.voisine(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP) != null && caseArrive.getClass() == Portail.class) 
		{
			casePlateau = plateauPlateauP.voisinePortail(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP);
		}

		if (plateauPlateauP.voisine(plateauPlateauP.voisine(casePositionP, directionVersP), directionVersP) != null && casePositionP.getBushi().estPlusGrandOuEgalA(plateauPlateauP.voisine(casePositionP, directionVersP).getBushi())
				&& casePositionPrecedentP != caseArrive
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
	 * La distance de déplacement
	 * 
	 * @return Renvoie true si la distance est correcte
	 */
	public boolean verifierDistance(int intDistanceP) 
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

	public String toString()
	{
		return ("\nType du bushi : " + typebushiMonType.getNom() 
				+"\nCouleur : " + couleurMaCouleur.getNom()
				+"\nIndex : " + intIndex );
	} 
	
	@Override
	public boolean equals(Object objetP) {
		if (this == objetP)
			return true;
		if (objetP == null)
			return false;
		if (getClass() != objetP.getClass())
			return false;
		Bushi other = (Bushi) objetP;
		if (couleurMaCouleur != other.couleurMaCouleur)
			return false;
		if (intIndex != other.intIndex)
			return false;
		if (typebushiMonType != other.typebushiMonType)
			return false;
		return true;
	}
}