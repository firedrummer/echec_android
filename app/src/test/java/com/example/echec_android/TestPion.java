package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Pion;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * Classe de tests pour les pièces de type pion
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestPion extends TestPiece {
    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Ajout du pion blanc
        Piece piece = creerPiece(Piece.Couleur.BLANC);
        assertEquals("p", piece.obtenirRepresentation());
        assertEquals(1.0, piece.obtenirPointagePiece(), 0.0);

        // Ajout du pion noir
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("P", piece2.obtenirRepresentation());
        assertEquals(1.0, piece2.obtenirPointagePiece(), 0.0);
    }

    @Override
    protected Piece creerPiece(Piece.Couleur p_couleur) {
        return new Pion(p_couleur);
    }

    /**
     * Méthode qui test si les déplacements du pion sont valides ou invalides
     */
    @Test
    @Override
    public void deplacementValide() {
        Pion piece = (Pion) creerPiece(Piece.Couleur.BLANC);

        // deplacement hors echiquier
        assertFalse(piece.estDeplacementValide("d4", "z9"));

        // deplacement colonne
        assertTrue(piece.estDeplacementValide("a1", "a2"));
        assertFalse(piece.estDeplacementValide("a1", "a3"));

        // deplacement rangee
        assertFalse(piece.estDeplacementValide("a1", "b1"));
        assertFalse(piece.estDeplacementValide("a1", "c1"));

        // deplacement diagonale
        assertFalse(piece.estDeplacementValide("a1", "b2"));
        assertFalse(piece.estDeplacementValide("a1", "c3"));
    }

    @Test
    @Override
    public void deplacementPossibleSelonCoordonnee() {

    }
}

