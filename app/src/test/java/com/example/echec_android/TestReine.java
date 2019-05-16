package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Reine;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe de tests pour les pièces de type reine
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestReine extends TestPiece {
    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Ajout de la reine blanche
        Piece piece = creerPiece(Piece.Couleur.BLANC);
        assertEquals("d", piece.obtenirRepresentation());
        assertEquals(9.0, piece.obtenirPointagePiece(), 0.0);

        // Ajout de la reine noire
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("D", piece2.obtenirRepresentation());
        assertEquals(9.0, piece2.obtenirPointagePiece(), 0.0);
    }

    @Override
    protected Piece creerPiece(Piece.Couleur p_couleur) {
        return new Reine(p_couleur);
    }

    /**
     * Méthode qui test si les déplacements de la reine sont valides ou invalides
     */
    @Test
    @Override
    public void deplacementValide() {
        Reine piece = (Reine) creerPiece(Piece.Couleur.BLANC);

        // deplacement hors echiquier
        assertFalse(piece.estDeplacementValide("d4", "z9"));

        // deplacement colonne
        assertTrue(piece.estDeplacementValide("a1", "a6"));
        assertFalse(piece.estDeplacementValide("a1", "a9"));

        // deplacement rangee
        assertTrue(piece.estDeplacementValide("a1", "f1"));
        assertFalse(piece.estDeplacementValide("a1", "j1"));

        // deplacement diagonale
        assertTrue(piece.estDeplacementValide("a1", "d4"));
        assertTrue(piece.estDeplacementValide("b1", "e4"));
        assertFalse(piece.estDeplacementValide("a1", "g3"));
    }

    @Override
    public void deplacementPossibleSelonCoordonnee() {

    }
}

