package com.example.echec_android;

import com.example.echec_android.echec.Fou;
import com.example.echec_android.echec.Piece;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Classe de tests pour les pièces de type fou
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestFou extends TestPiece {
    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Ajout du fou Blanc
        Piece piece = creerPiece(Piece.Couleur.BLANC);
        assertEquals("f", piece.obtenirRepresentation());
        assertEquals(3.0, piece.obtenirPointagePiece(), 0.0);

        // Ajout du fou noir
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("F", piece2.obtenirRepresentation());
        assertEquals(3.0, piece2.obtenirPointagePiece(), 0.0);
    }

    @Override
    protected Piece creerPiece(Piece.Couleur p_couleur) {
        return new Fou(p_couleur);
    }

    /**
     * Méthode qui test si les déplacements du fou sont valides ou invalides
     */
    @Test
    @Override
    public void deplacementValide() {
        Fou piece = (Fou) creerPiece(Piece.Couleur.BLANC);

        // deplacement hors echiquier
        assertFalse(piece.estDeplacementValide("d4", "z9"));

        // deplacement colonne
        assertFalse(piece.estDeplacementValide("a1", "a6"));
        assertFalse(piece.estDeplacementValide("a1", "a9"));

        // deplacement rangee
        assertFalse(piece.estDeplacementValide("a1", "f1"));
        assertFalse(piece.estDeplacementValide("a1", "j1"));

        // deplacement diagonale
        assertTrue(piece.estDeplacementValide("a1", "d4"));
        assertTrue(piece.estDeplacementValide("b1", "e4"));
        assertFalse(piece.estDeplacementValide("a1", "g3"));
    }
}
