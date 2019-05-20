package com.example.echec_android.partie;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Piece.Couleur;

/**
 * Gère l'état d'un joueur
 */
public class Joueur {
    //Variables privées membres pour la classe
    private Piece.Couleur m_couleurPiece;
    private String m_nom;

    /**
     * Constructeur d'une pièce
     * @param m_couleurPiece couleur de la pièce
     * @param p_nom nom de la pièce
     */
    public Joueur(Piece.Couleur m_couleurPiece, String p_nom) {
        this.m_couleurPiece = m_couleurPiece;
        m_nom = p_nom;
    }

    /**
     * Getter de la couleur d'une pièce
     * @return la couleur de la pièce
     */
    public Piece.Couleur getCouleurPiece() {
        return m_couleurPiece;
    }

    /**
     * setter de la couleur de la pièce
     * @param p_couleur couleur de la pièce
     */
    public void setCouleurPiece(Couleur p_couleur) {
        m_couleurPiece = p_couleur;
    }

    /**
     * getter pour le nom du joueur
     * @return le nom du joueur
     */
    public String getNomJoueur() {
        return m_nom;
    }

    /**
     * Setter pour le nom du joueur
     * @param p_nom nom du joueur
     */
    public void setNomJoueur(String p_nom) {
        m_nom = p_nom;
    }
}