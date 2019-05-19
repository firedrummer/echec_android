package com.example.echec_android.echec;

import java.util.ArrayList;

/**
 * Classe roi dans un jeu d'échec
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Roi extends Piece {
    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     */
    public Roi(Couleur p_couleur) {
        super(p_couleur, Type.ROI);
    }

    /**
     * Méthode qui renvoit le pointage du roi
     *
     * @return pointage d'un roi
     */
    public double obtenirPointagePiece() {
        return 0;
    }

    /**
     * obtient la representation d'un roi
     *
     * @return la representation du roi
     */
    public String obtenirRepresentation() {
        if (getCouleur() == Couleur.BLANC)
            return "r";
        else
            return "R";
    }

    /**
     * Méthode qui valide si le déplacement de la pièce est possible pour un mouvement standard
     *
     * @param p_coordonneeDepart coordonnée de départ de la piece avant le mouvement
     * @param p_coordonneeFin    coordonnée de fin de la piece après le mouvement
     * @return si le deplacement est valide
     */
    public boolean estDeplacementValide(String p_coordonneeDepart, String p_coordonneeFin) {
        char coordonneeDepartLettre = p_coordonneeDepart.charAt(0);
        char coordonneeDepartChiffre = p_coordonneeDepart.charAt(1);

        if (p_coordonneeDepart.compareTo(p_coordonneeFin) == 0 ||
                p_coordonneeFin.charAt(0) < 'a' || p_coordonneeFin.charAt(0) > 'h' ||
                p_coordonneeFin.charAt(1) < '1' || p_coordonneeFin.charAt(1) > '8') {
            return false;
        } else if ((Math.abs(p_coordonneeFin.charAt(0) - coordonneeDepartLettre) == 1) &&
                (Math.abs(p_coordonneeFin.charAt(1) - coordonneeDepartChiffre) == 0) ||
                (Math.abs(p_coordonneeFin.charAt(0) - coordonneeDepartLettre) == 0) &&
                        (Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) == 1)) {
            return true;
        } else return (coordonneeDepartLettre + 1 == p_coordonneeFin.charAt(0) &&
                coordonneeDepartChiffre + 1 == p_coordonneeFin.charAt(1)) ||
                (coordonneeDepartLettre - 1 == p_coordonneeFin.charAt(0) &&
                        coordonneeDepartChiffre - 1 == p_coordonneeFin.charAt(1)) ||
                (coordonneeDepartLettre - 1 == p_coordonneeFin.charAt(0) &&
                        coordonneeDepartChiffre + 1 == p_coordonneeFin.charAt(1)) ||
                (coordonneeDepartLettre + 1 == p_coordonneeFin.charAt(0) &&
                        coordonneeDepartChiffre - 1 == p_coordonneeFin.charAt(1));
    }

    /**
     * Méthode qui calcul les coordonnées de base possible selon une piece
     *
     * @param p_coordonnee coordonnée à calculer mouvement possible
     * @return coordonées des mouvement possible
     */
    @Override
    public ArrayList<String> deplacementsPossiblesSelonCoordonnee(String p_coordonnee) {
        ArrayList<String> mouvements = new ArrayList<>();
        ArrayList<String> mouvementsValides = new ArrayList<>();

        mouvements.add("" + p_coordonnee.charAt(0) + (char) (p_coordonnee.charAt(1) + 1));
        mouvements.add("" + p_coordonnee.charAt(0) + (char) (p_coordonnee.charAt(1) - 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 1) + p_coordonnee.charAt(1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 1) + p_coordonnee.charAt(1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) + 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) - 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) + 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) - 1));

        for (String coordonnee : mouvements) {
            if (coordonnee.charAt(0) <= 'h' && coordonnee.charAt(0) >= 'a' &&
                    coordonnee.charAt(1) >= '1' && coordonnee.charAt(1) <= '8') {
                mouvementsValides.add(coordonnee);
            }
        }
        return mouvementsValides;
    }
}
