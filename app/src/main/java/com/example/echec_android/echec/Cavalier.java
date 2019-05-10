package com.example.echec_android.echec;

import java.util.ArrayList;

/**
 * Classe Cavalier dans un jeu d'échec
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Cavalier extends Piece {
    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     */
    public Cavalier(Couleur p_couleur) {
        super(p_couleur, Type.CAVALIER);
    }

    /**
     * Méthode qui renvoit le pointage d'un cavalier
     *
     * @return pointage d'un cavalier
     */
    public double obtenirPointagePiece() {
        return 2.5;
    }

    /**
     * obtient la representation d'un cavalier
     *
     * @return la representation du cavalier
     */
    public String obtenirRepresentation() {
        if (getCouleur() == Couleur.BLANC)
            return "c";
        else
            return "C";
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

        int differenceLettre = p_coordonneeFin.charAt(0) - p_coordonneeDepart.charAt(0);
        int differenceChiffre = p_coordonneeFin.charAt(1) - p_coordonneeDepart.charAt(1);

        return ((Math.abs(differenceLettre) == 2 &&
                Math.abs(differenceChiffre) == 1) ||
                (Math.abs(differenceChiffre) == 2 &&
                        Math.abs(differenceLettre) == 1));
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

        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 2) + (char) (p_coordonnee.charAt(1) + 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 2) + (char) (p_coordonnee.charAt(1) - 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 2) + (char) (p_coordonnee.charAt(1) + 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 2) + (char) (p_coordonnee.charAt(1) - 1));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) + 2));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) - 2));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) + 2));
        mouvements.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) - 2));

        for (String coordonnee : mouvements) {
            if (coordonnee.charAt(0) > 'h' || coordonnee.charAt(0) < 'a' ||
                    coordonnee.charAt(1) < '1' || coordonnee.charAt(0) > '8') {
                mouvements.remove(coordonnee);
            }
        }

        return mouvements;
    }
}
