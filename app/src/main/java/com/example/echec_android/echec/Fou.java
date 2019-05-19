package com.example.echec_android.echec;

import java.util.ArrayList;

/**
 * Classe fou dans un jeu d'échec
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */
public class Fou extends Piece {
    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     */
    public Fou(Couleur p_couleur) {
        super(p_couleur, Type.FOU);
    }

    /**
     * Méthode qui renvoit le pointage d'un fou
     *
     * @return pointage d'un fou
     */
    public double obtenirPointagePiece() {
        return 3;
    }

    /**
     * obtient la representation d'un fou
     *
     * @return la representation du fou
     */
    public String obtenirRepresentation() {
        if (getCouleur() == Couleur.BLANC)
            return "f";
        else
            return "F";
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
        }

        for (int i = 1; i < 8; i++) {
            if ((coordonneeDepartLettre + i == p_coordonneeFin.charAt(0) &&
                    coordonneeDepartChiffre + i == p_coordonneeFin.charAt(1)) ||
                    (coordonneeDepartLettre - i == p_coordonneeFin.charAt(0) &&
                            coordonneeDepartChiffre - i == p_coordonneeFin.charAt(1)) ||
                    (coordonneeDepartLettre - 1 == p_coordonneeFin.charAt(0) &&
                            coordonneeDepartChiffre + i == p_coordonneeFin.charAt(1)) ||
                    (coordonneeDepartLettre + i == p_coordonneeFin.charAt(0) &&
                            coordonneeDepartChiffre - i == p_coordonneeFin.charAt(1))) {
                return true;
            }
        }

        return false;
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
        String coordonnee;

        for (int i = 1; i < 8; i++) {

            if (((char) (p_coordonnee.charAt(0) + i) <= 'h') && ((char) (p_coordonnee.charAt(1) + i) <= '8')) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) +
                        (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if (((char) (p_coordonnee.charAt(0) - i) >= 'a') && ((char) (p_coordonnee.charAt(1) - i) >= '1')) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) - i) +
                        (char) (p_coordonnee.charAt(1) - i);
                mouvements.add(coordonnee);
            }

            if (((char) (p_coordonnee.charAt(0) - i) >= 'a') && ((char) (p_coordonnee.charAt(1) + i) <= '8')) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) - i) +
                        (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if (((char) (p_coordonnee.charAt(0) + i) <= 'h') && ((char) (p_coordonnee.charAt(1) - i) >= '1')) {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) +
                        (char) (p_coordonnee.charAt(1) - i);
                mouvements.add(coordonnee);
            }
        }

        return mouvements;
    }
}