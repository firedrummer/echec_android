package com.example.echec_android;

import com.example.echec_android.echec.Piece;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests pour les pièces
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public abstract class TestPiece {
    /**
     * Piece noir
     */
    private Piece m_pieceNoir;

    /**
     * Piece blanche
     */
    private Piece m_pieceBlanche;

    /**
     * Méthode de "setup" pour les tests de piece
     */
    @Before
    public void setUp() {
        m_pieceNoir = creerPiece(Piece.Couleur.NOIR);
        m_pieceBlanche = creerPiece(Piece.Couleur.BLANC);
    }

    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Piece blanche
        Assert.assertTrue(m_pieceBlanche.estBlanc());
        Assert.assertEquals(Piece.Couleur.BLANC, m_pieceBlanche.getCouleur());
        Assert.assertFalse(m_pieceBlanche.estNoir());

        // Piece noir
        Assert.assertTrue(m_pieceNoir.estBlanc());
        Assert.assertEquals(Piece.Couleur.NOIR, m_pieceNoir.getCouleur());
        Assert.assertFalse(m_pieceNoir.estBlanc());
    }

    abstract protected Piece creerPiece(Piece.Couleur p_couleur);

    @Test
    public abstract void deplacementValide();
}