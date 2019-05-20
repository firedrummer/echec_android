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
        Assert.assertFalse(m_pieceBlanche.estDeplacer());
        m_pieceBlanche.deplacer();
        Assert.assertTrue(m_pieceBlanche.estDeplacer());

        // Piece noir
        Assert.assertTrue(m_pieceNoir.estNoir());
        Assert.assertEquals(Piece.Couleur.NOIR, m_pieceNoir.getCouleur());
        Assert.assertFalse(m_pieceNoir.estBlanc());
        Assert.assertFalse(m_pieceNoir.estDeplacer());
        m_pieceNoir.deplacer();
        Assert.assertTrue(m_pieceNoir.estDeplacer());
    }

    /**
     * Méthode abstraite pour la création des pièces dans les classes non-abstraites
     * @param p_couleur coueur choisie pour la pièce en question
     * @return la pièce qui sera créée selon son type avec la couleur passée en param'
     */
    abstract protected Piece creerPiece(Piece.Couleur p_couleur);

    /**
     * Méthode abstraite pour valider les déplacements pour tous les types de pièce
     */
    @Test
    public abstract void deplacementValide();

    /**
     * Méthode abstraite pour les déplacements possibles selon la coordonnée pour tous les types de pièce
     */
    @Test
    public abstract void deplacementsPossiblesSelonCoordonnee();
}