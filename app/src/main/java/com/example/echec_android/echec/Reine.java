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
        //Variables afin d'éviter le Lint
        char coordonneeDepartLettre = p_coordonneeDepart.charAt(0);
        char coordonneeDepartChiffre = p_coordonneeDepart.charAt(1);
        char coordonneeFinLettre = p_coordonneeFin.charAt(0);
        char coordonneeFinChiffre = p_coordonneeFin.charAt(1);

        if (p_coordonneeDepart.compareTo(p_coordonneeFin) == 0 ||
                coordonneeFinLettre < 'a' || coordonneeFinLettre > 'h' ||
                coordonneeFinChiffre < '1' || coordonneeFinChiffre > '8') {
            return false;
        }

        for (int i = 1; i < 8; i++) {
            if ((coordonneeDepartLettre + i == coordonneeFinLettre &&
                    coordonneeDepartChiffre + i == coordonneeFinChiffre) ||
                    (coordonneeDepartLettre - i == coordonneeFinLettre &&
                            coordonneeDepartChiffre - i == coordonneeFinChiffre) ||
                    (coordonneeDepartLettre - 1 == coordonneeFinLettre &&
                            coordonneeDepartChiffre + i == coordonneeFinChiffre) ||
                    (coordonneeDepartLettre + i == coordonneeFinLettre &&
                            coordonneeDepartChiffre - i == coordonneeFinChiffre)) {
                return true;
            }
        }

        return ((Math.abs(coordonneeFinLettre - coordonneeDepartLettre) <= 7 &&
                Math.abs(coordonneeFinChiffre - coordonneeDepartChiffre) == 0) ||
                (Math.abs(coordonneeFinLettre - coordonneeDepartLettre) == 0 &&
                        Math.abs(coordonneeFinChiffre - coordonneeDepartChiffre) <= 7));
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

            if ((char) (p_coordonnee.charAt(0) + i) <= 'h') {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) + i) + p_coordonnee.charAt(1);
                mouvements.add(coordonnee);
            }

            if ((char) (p_coordonnee.charAt(0) - i) >= 'a') {
                coordonnee = "" + (char) (p_coordonnee.charAt(0) - i) + (p_coordonnee.charAt(1));
                mouvements.add(coordonnee);
            }

            if ((char) (p_coordonnee.charAt(1) + i) <= '8') {
                coordonnee = "" + p_coordonnee.charAt(0) + (char) (p_coordonnee.charAt(1) + i);
                mouvements.add(coordonnee);
            }

            if ((char) (p_coordonnee.charAt(1) - i) >= '1') {
                coordonnee = "" + p_coordonnee.charAt(0) + (char) (p_coordonnee.charAt(1) - i);
                mouvements.add(coordonnee);
            }

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