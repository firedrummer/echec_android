package com.example.echec_android.echec;

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

        return false;
    }
}