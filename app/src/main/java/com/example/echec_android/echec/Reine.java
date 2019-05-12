package com.example.echec_android.echec;

import java.util.ArrayList;

/**
 * Classe reine dans un jeu d'échec
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Reine extends Piece {
    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     */
    public Reine(Couleur p_couleur) {
        super(p_couleur, Type.DAME);
    }

    /**
     * Méthode qui renvoit le pointage d'une reine
     *
     * @return pointage d'un reine
     */
    public double obtenirPointagePiece() {
        return 9;
    }

    /**
     * obtient la representation d'une dame
     *
     * @return la representation de la dame
     */
    public String obtenirRepresentation() {
        if (getCouleur() == Couleur.BLANC)
            return "d";
        else
            return "D";
    }

    /**
     * Méthode qui valide si le déplacement de la pièce est possible pour un mouvement standard
     *
     * @param p_coordonneeDepart coordonnée de départ de la piece avant le mouvement
     * @param p_coordonneeFin    coordonnée de fin de la piece après le mouvement
     * @return si le deplacement est valide
     */
    public boolean estDeplacementValide(String p_coordonneeDepart, String p_coordonneeFin) {
        if (p_coordonneeDepart.compareTo(p_coordonneeFin) == 0 ||
                p_coordonneeFin.charAt(0) < 'a' || p_coordonneeFin.charAt(0) > 'h' ||
                p_coordonneeFin.charAt(1) < '1' || p_coordonneeFin.charAt(1) > '8') {
            return false;
        }

        for (int i = 1; i < 8; i++) {
            if ((p_coordonneeDepart.charAt(0) + i == p_coordonneeFin.charAt(0) &&
                    p_coordonneeDepart.charAt(1) + i == p_coordonneeFin.charAt(1)) ||
                    (p_coordonneeDepart.charAt(0) - i == p_coordonneeFin.charAt(0) &&
                            p_coordonneeDepart.charAt(1) - i == p_coordonneeFin.charAt(1)) ||
                    (p_coordonneeDepart.charAt(0) - 1 == p_coordonneeFin.charAt(0) &&
                            p_coordonneeDepart.charAt(1) + i == p_coordonneeFin.charAt(1)) ||
                    (p_coordonneeDepart.charAt(0) + i == p_coordonneeFin.charAt(0) &&
                            p_coordonneeDepart.charAt(1) - i == p_coordonneeFin.charAt(1))) {
                return true;
            }
        }

        return ((Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0)) <= 7 &&
                Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) == 0) ||
                (Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0)) == 0 &&
                        Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) <= 7));
    }

    /**
     * Méthode qui calcul les coordonnées de base possible selon une piece
     *
     * @param p_coordonnee coordonnée à calculer mouvement possible
     * @return coordonées des mouvement possible
     */
    @Override
    public ArrayList<String> deplacementPossibleSelonCoordoordee(String p_coordonnee) {
        ArrayList<String> mouvements = new ArrayList<>();
        String coordonnee = "";

        for (int i = 1; i < 8; i++) {

            if (p_coordonnee.charAt(0) + i <= 'h') {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) + p_coordonnee.charAt(1);
                mouvements.add(coordonnee);
            }

            if (p_coordonnee.charAt(0) - i >= 'a') {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) + (p_coordonnee.charAt(1));
                mouvements.add(coordonnee);
            }

            if (p_coordonnee.charAt(1) + i <= 8) {
                coordonnee = "" + p_coordonnee.charAt(0) + (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if (p_coordonnee.charAt(1) - i >= 1) {
                coordonnee = "" + p_coordonnee.charAt(0) + (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if ((p_coordonnee.charAt(0) + i <= 'h') && (p_coordonnee.charAt(1) + i <= 8)) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) +
                        (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if ((p_coordonnee.charAt(0) - i >= 'a') && (p_coordonnee.charAt(1) - i >= 1)) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) - i) +
                        (char) (p_coordonnee.charAt(1) - i);
                mouvements.add(coordonnee);
            }

            if ((p_coordonnee.charAt(0) - i >= 'a') && (p_coordonnee.charAt(1) + i <= 8)) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) - i) +
                        (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if ((p_coordonnee.charAt(0) + i <= 'h') && (p_coordonnee.charAt(1) - i >= 1)) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) +
                        (char) (p_coordonnee.charAt(1) - i);
                mouvements.add(coordonnee);
            }
        }

        return mouvements;
    }
}