package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Reine;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
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

    @Test
    @Override
    public void deplacementsPossiblesSelonCoordonnee() {
        Reine piece = (Reine) creerPiece(Piece.Couleur.NOIR);

        List<String> listeTest = asList("a3", "a2", "a1", "a5", "a6", "a7", "a8", "b3", "c2", "d1",
                "b4", "c4", "d4", "e4", "f4", "g4", "h4");

        List<String> listeTest2 = asList("a5", "b5", "c5", "e5", "f5", "g5", "h5", "d6", "d7", "d8",
                "e6", "f7", "g8", "c6", "b7", "a8", "c4", "b3", "a2", "d4", "d3", "d2", "d1", "e4", "f3", "g2", "h1");

        assertArrayEquals(listeTest.toArray(), piece.deplacementsPossiblesSelonCoordonnee("a4").toArray());
        assertArrayEquals(listeTest2.toArray(), piece.deplacementsPossiblesSelonCoordonnee("d5").toArray());
    }
}

