package com.example.echec_android;

import com.example.echec_android.echec.Cavalier;
import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.echec.Fou;
import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Pion;
import com.example.echec_android.echec.Reine;
import com.example.echec_android.echec.Roi;
import com.example.echec_android.echec.Tour;

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

    /**
     * Méthode test de promotion pour tester un pion qui touche la dernière case
     */
    @Test
    public void testPromotion() {
        m_echiquier.viderEchiquier();
        m_echiquier.initialiser();
        //Tour 1 blanc bouge pion de 2 cases
        m_echiquier.deplacerPiece("a2", "a4");
        //Tour 1 noir bouge pion de 2 cases
        m_echiquier.deplacerPiece("c7", "c5");
        //Tour 2 blanc avance le meme pion de 1
        m_echiquier.deplacerPiece("a4", "a5");
        //Tour 2 noir avance le meme pion de 1
        m_echiquier.deplacerPiece("c5", "c4");
        //Tour 3 blanc avance le meme pion de 1
        m_echiquier.deplacerPiece("a5", "a6");
        //Tour 3 noir avance le meme pion de 1
        m_echiquier.deplacerPiece("c4", "c3");
        //Tour 4 blanc avance le meme pion de 1
        m_echiquier.deplacerPiece("a6", "a7");
        //Tour 4 noir avance le meme pion de 1
        m_echiquier.deplacerPiece("c3", "c2");
        //Tour 5 blanc avance le meme pion de 1
        m_echiquier.deplacerPiece("a7", "a8");
        //Test de la  promotion du pion blanc qui s'est rendu à la dernière rangée
        m_echiquier.promotion("a8", Piece.Type.DAME);

        Assert.assertEquals("DAME", m_echiquier.getPieceEnTexte("a8"));
    }

    @Test
    public void testPartieNulle() {
        // Les 3 pièces nécéssaires pour tester qu'il y a 3 pièces sur l'échiquier afin d'avoir une partie nulle
        Pion pion = new Pion(Piece.Couleur.BLANC);
        Roi roiBlanc = new Roi(Piece.Couleur.BLANC);
        Roi roiNoir = new Roi(Piece.Couleur.NOIR);

        m_echiquier.viderEchiquier();
        m_echiquier.obtenirRepresentation();
        m_echiquier.ajouterPiece("a2", pion);
        m_echiquier.ajouterPiece("d5", roiNoir);
        m_echiquier.ajouterPiece("e7", roiBlanc);
        Assert.assertTrue(m_echiquier.partieNulle());

    }

    @Test
    public void testEchecMat() {
        Roi roiNoir = new Roi(Piece.Couleur.NOIR);
        Roi roiBlanc = new Roi(Piece.Couleur.BLANC);
        Reine reineBlanche = new Reine(Piece.Couleur.BLANC);
        Tour tourBlanche = new Tour(Piece.Couleur.BLANC);

        m_echiquier.viderEchiquier();
        m_echiquier.ajouterPiece("b5", roiBlanc);
        m_echiquier.ajouterPiece("a6", reineBlanche);
        m_echiquier.ajouterPiece("b7", tourBlanche);
        m_echiquier.ajouterPiece("a8", roiNoir);

        Assert.assertTrue(m_echiquier.estEchecEtMathNoir());
    }
}

