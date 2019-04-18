package com.example.echec_android.echec;

/**
 * Classe pion dans un jeu d'échec
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */
public class Pion extends Piece {
    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     */
    public Pion(Couleur p_couleur) {
        super(p_couleur, Type.PION);
    }

    /**
     * Méthode qui renvoit le pointage d'une pièce selon son type
     *
     * @return pointage selon le type de la pièce
     */
    public double obtenirPointagePiece() {
        return 1;
    }

    /**
     * obtient la representation d'un pion
     *
     * @return la representation du pion
     */
    public String obtenirRepresentation() {
        if (getCouleur() == Couleur.BLANC)
            return "p";
        else
            return "P";
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
        } else return Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0)) == 0 &&
                Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) == 1;
    }

    /**
     * Méthode qui valide si le premier déplacement d'un pion est possible
     *
     * @param p_coordonneeDepart coordonnée de départ de la piece avant le mouvement
     * @param p_coordonneeFin    coordonnée de fin de la piece après le mouvement
     * @return si le deplacement est valide
     */
    boolean estPremierDeplacementValide(String p_coordonneeDepart, String p_coordonneeFin) {
        if (p_coordonneeDepart.compareTo(p_coordonneeFin) == 0 ||
                p_coordonneeFin.charAt(0) < 'a' || p_coordonneeFin.charAt(0) > 'h' ||
                p_coordonneeFin.charAt(1) < '1' || p_coordonneeFin.charAt(1) > '8') {
            return false;
        } else return Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0)) == 0 &&
                Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) < 2;
    }
}