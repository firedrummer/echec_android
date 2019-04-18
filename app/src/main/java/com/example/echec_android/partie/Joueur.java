package com.example.echec_android.partie;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Piece.Couleur;

/**
 * Gère l'état d'un joueur
 */
public class Joueur {

    private Piece.Couleur m_couleurPiece;
    private String m_nom;


    public Joueur(Piece.Couleur m_couleurPiece, String p_nom) {
        this.m_couleurPiece = m_couleurPiece;
        m_nom = p_nom;
    }

    Piece.Couleur getCouleurPiece() {
        return m_couleurPiece;
    }

    void setCouleurPiece(Couleur p_couleur) {
        m_couleurPiece = p_couleur;
    }

    String getNomJoueur() {
        return m_nom;
    }

    void setNomJoueur(String p_nom) {
        m_nom = p_nom;
    }
}