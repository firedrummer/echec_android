package com.example.echec_android;

import com.example.echec_android.echec.Cavalier;
import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.echec.Fou;
import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Pion;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de tests pour l'echiquier
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */
public class TestEchiquier {
    private static final String SAUT_LIGNE = System.lineSeparator();
    private Echiquier m_echiquier = new Echiquier();

    /**
     * methode test pour ajouter des pions et tester leur couleur sur l'échiquier
     */
    @Test
    public void creation() {
        // Test echiquier est vide a la creation
        Assert.assertEquals(0, m_echiquier.getNombrePieces());

        // Ajout de cavalier blanc
        m_echiquier.ajouterPiece("a1", new Cavalier(Piece.Couleur.BLANC));
        Assert.assertEquals(1, m_echiquier.getNombrePieces());
        Assert.assertTrue(m_echiquier.getPiece("a1").estBlanc());
        Assert.assertEquals("CAVALIER BLANC", m_echiquier.getPieceEnTexte("a1"));
        Assert.assertEquals(Piece.Type.CAVALIER, m_echiquier.getPiece("a1").getType());
        Assert.assertEquals(1, m_echiquier.obtenirNombrePieceSelonCouleurEtType(Piece.Type.CAVALIER,
                Piece.Couleur.BLANC));
        Assert.assertEquals(2.5, m_echiquier.compterPointsEchiquier(Piece.Couleur.BLANC), 0.0);

        // Ajout de fou blanc
        m_echiquier.ajouterPiece("h8", new Fou(Piece.Couleur.BLANC));
        Assert.assertEquals(2, m_echiquier.getNombrePieces());
        Assert.assertTrue(m_echiquier.getPiece("h8").estBlanc());
        Assert.assertEquals("FOU BLANC", m_echiquier.getPieceEnTexte("h8"));
        Assert.assertEquals(Piece.Type.FOU, m_echiquier.getPiece("h8").getType());
        Assert.assertEquals(1, m_echiquier.obtenirNombrePieceSelonCouleurEtType(Piece.Type.FOU,
                Piece.Couleur.BLANC));
        Assert.assertEquals(5.5, m_echiquier.compterPointsEchiquier(Piece.Couleur.BLANC), 0.0);

        // Ajout de pion noir
        m_echiquier.ajouterPiece("c4", new Pion(Piece.Couleur.NOIR));
        Assert.assertEquals(3, m_echiquier.getNombrePieces());
        Assert.assertTrue(m_echiquier.getPiece("c4").estNoir());
        Assert.assertEquals("PION NOIRE", m_echiquier.getPieceEnTexte("c4"));
        Assert.assertEquals(Piece.Type.PION, m_echiquier.getPiece("c4").getType());
        Assert.assertEquals(1, m_echiquier.obtenirNombrePieceSelonCouleurEtType(Piece.Type.PION,
                Piece.Couleur.NOIR));
        Assert.assertEquals(1.0, m_echiquier.compterPointsEchiquier(Piece.Couleur.NOIR), 0.0);

        // Test vider et ajout de Cavalier noir
        m_echiquier.viderEchiquier();
        m_echiquier.ajouterPiece("a1", new Cavalier(Piece.Couleur.NOIR));
        Assert.assertEquals(1, m_echiquier.getNombrePieces());
        Assert.assertTrue(m_echiquier.getPiece("a1").estNoir());
        Assert.assertEquals("CAVALIER NOIRE", m_echiquier.getPieceEnTexte("a1"));
        Assert.assertEquals(Piece.Type.CAVALIER, m_echiquier.getPiece("a1").getType());
        Assert.assertEquals(1, m_echiquier.obtenirNombrePieceSelonCouleurEtType(Piece.Type.CAVALIER,
                Piece.Couleur.NOIR));
        Assert.assertEquals(2.5, m_echiquier.compterPointsEchiquier(Piece.Couleur.NOIR), 0.0);

        // Ajout de cavalier noir
        m_echiquier.ajouterPiece("a2", new Cavalier(Piece.Couleur.NOIR));
        Assert.assertEquals(2, m_echiquier.getNombrePieces());
        Assert.assertTrue(m_echiquier.getPiece("a2").estNoir());
        Assert.assertEquals("CAVALIER NOIRE", m_echiquier.getPieceEnTexte("a2"));
        Assert.assertEquals(Piece.Type.CAVALIER, m_echiquier.getPiece("a2").getType());
        Assert.assertEquals(2, m_echiquier.obtenirNombrePieceSelonCouleurEtType(Piece.Type.CAVALIER,
                Piece.Couleur.NOIR));
        Assert.assertEquals(5.0, m_echiquier.compterPointsEchiquier(Piece.Couleur.NOIR), 0.0);
    }

    /**
     * Methode tester l'initialisation et la representation graphique
     */
    @Test
    public void initialisation() {
        m_echiquier.viderEchiquier();
        m_echiquier.initialiser();

        String echiquier = "";

        // ligne 1
        echiquier += "tcfdrfct";
        echiquier += SAUT_LIGNE;

        // ligne 2
        echiquier += "pppppppp";
        echiquier += SAUT_LIGNE;

        // ligne 3
        echiquier += "XXXXXXXX";
        echiquier += SAUT_LIGNE;

        // ligne 4
        echiquier += "XXXXXXXX";
        echiquier += SAUT_LIGNE;

        // ligne 5
        echiquier += "XXXXXXXX";
        echiquier += SAUT_LIGNE;

        // ligne 6
        echiquier += "XXXXXXXX";
        echiquier += SAUT_LIGNE;

        // ligne 7
        echiquier += "PPPPPPPP";
        echiquier += SAUT_LIGNE;

        // ligne 8
        echiquier += "TCFDRFCT";

        Assert.assertEquals(echiquier, m_echiquier.obtenirRepresentation());

        System.out.println(m_echiquier.obtenirRepresentation());
    }
}

