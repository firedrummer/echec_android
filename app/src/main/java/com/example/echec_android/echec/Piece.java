package com.example.echec_android.echec;

import java.util.ArrayList;

/**
 * Classe pièce composée de toutes les pièces qui forment le jeu d'échec
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */

public abstract class Piece {
    /**
     * Couleur d'une pièce
     */
    private Couleur m_couleur;

    /**
     * Type d'une pièce
     */
    private Type m_type;

    //Variable privée membre pour voir si une pièce a été déplacée
    private boolean m_deplacer = false;

    /**
     * constructeur privé d'une pièce
     *
     * @param p_couleur couleur de la pièce
     * @param p_type    nom de la pièce
     */
    protected Piece(Couleur p_couleur, Type p_type) {
        m_couleur = p_couleur;
        m_type = p_type;
    }

    /**
     * Méthode qui renvoie si la pièce est noire
     *
     * @return true si la pièce est noire
     */
    public boolean estNoir() {
        return this.m_couleur == Couleur.NOIR;
    }

    /**
     * Méthode qui renvoie si la pièce est blanche
     *
     * @return true si la pièce est blanche
     */
    public boolean estBlanc() {
        return this.m_couleur == Couleur.BLANC;
    }

    /**
     * Méthode qui retourne la variable membre m_deplacer
     *
     * @return true lorsqu'une pièce est déplacée
     */
    public boolean estDeplacer() {
        return m_deplacer;
    }

    /**
     * Met la valeur de m_deplacer à true pour dire que la piece à deja été déplacer
     */
    public void deplacer() {
        m_deplacer = true;
    }

    /**
     * Obtient la representation d'une piece
     *
     * @return la representation de la piece
     */
    public abstract String obtenirRepresentation();

    /**
     * Méthode qui renvoit le pointage d'une piece
     *
     * @return pointage d'une piece
     */
    public abstract double obtenirPointagePiece();

    /**
     * Méthode qui valide si le déplacement de la pièce est possible pour un mouvement standard
     *
     * @param p_coordonneeDepart coordonnée de départ de la piece avant le mouvement
     * @param p_coordonneeFin    coordonnée de fin de la piece après le mouvement
     * @return si le deplacement est valide
     */
    public abstract boolean estDeplacementValide(String p_coordonneeDepart, String p_coordonneeFin);

    /**
     * Méthode qui calcul les coordonnées de base possible selon une piece
     *
     * @param p_coordonnee coordonnée à calculer mouvement possible
     * @return coordonées des mouvement possible
     */
    public abstract ArrayList<String> deplacementsPossiblesSelonCoordonnee(String p_coordonnee);

    /**
     * @return la couleur de la piece
     */
    public Couleur getCouleur() {
        return m_couleur;
    }

    /**
     * @return type de la piece
     */
    public Type getType() {
        return m_type;
    }

    /**
     * couleur de la piece soit noir ou blanc
     */
    public enum Couleur {
        BLANC, NOIR
    }

    /**
     * type des pièces soit tour, cavalier, fou, dame, roi, pion
     */
    public enum Type {
        TOUR, CAVALIER, FOU, DAME, ROI, PION
    }
}
