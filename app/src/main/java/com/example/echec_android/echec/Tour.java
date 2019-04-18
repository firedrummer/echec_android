package com.example.echec_android.echec;

/**
 * Classe tour dans un jeu d'échec
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Tour extends Piece {
    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     */
    public Tour(Couleur p_couleur) {
        super(p_couleur, Type.TOUR);
    }

    /**
     * Méthode qui renvoit le pointage d'une tour
     *
     * @return pointage d'une tour
     */
    public double obtenirPointagePiece() {
        return 5;
    }

    /**
     * obtient la representation d'une tour
     *
     * @return la representation d'une tour
     */
    public String obtenirRepresentation() {
        if (getCouleur() == Couleur.BLANC)
            return "t";
        else
            return "T";
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
        } else return ((Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0)) <= 7 &&
                Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) == 0) ||
                (Math.abs(p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0)) == 0 &&
                        Math.abs(p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1)) <= 7));
    }
}
