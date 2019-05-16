package com.example.echec_android;

import com.example.echec_android.echec.Fou;
import com.example.echec_android.echec.Piece;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
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

    @Test
    @Override
    public void deplacementPossibleSelonCoordonnee() {
        Fou piece = (Fou) creerPiece(Piece.Couleur.NOIR);

        List<String> listeTest = asList("b2", "c3", "d4", "e5", "f6", "g7", "h8");
        List<String> listeTest2 = asList("c1", "d2", "f4", "g5", "h6", "a7", "b6",
                "c5", "d4", "f2", "g1");

        assertArrayEquals(listeTest.toArray(), piece.deplacementPossibleSelonCoordoordee("a1").toArray());
        assertArrayEquals(listeTest2.toArray(), piece.deplacementPossibleSelonCoordoordee("e3").toArray());
    }
}
