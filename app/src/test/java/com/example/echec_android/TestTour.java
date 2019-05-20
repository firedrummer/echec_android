package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Tour;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Classe de tests pour les pièces de type tour
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestTour extends TestPiece {
    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Ajout de la tour blanche
        Piece piece = creerPiece(Piece.Couleur.BLANC);
        assertEquals("t", piece.obtenirRepresentation());
        assertEquals(5.0, piece.obtenirPointagePiece(), 0.0);
        assertEquals(Piece.Type.TOUR, piece.getType());

        // Ajout de la tour noire
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("T", piece2.obtenirRepresentation());
        assertEquals(5.0, piece2.obtenirPointagePiece(), 0.0);
    }

    /**
     * Méthode qui crée une pièce soit une tour
     * @param p_couleur couleur de la pièce
     * @return la tour créée avec la couleur spécifiée
     */
    @Override
    protected Piece creerPiece(Piece.Couleur p_couleur) {
        return new Tour(p_couleur);
    }

    /**
     * Méthode qui test si les déplacements de la tour sont valides ou invalides
     */
    @Test
    @Override
    public void deplacementValide() {
        Tour piece = (Tour) creerPiece(Piece.Couleur.BLANC);

        // deplacement hors echiquier
        assertFalse(piece.estDeplacementValide("d4", "z9"));

        // deplacement colonne
        assertTrue(piece.estDeplacementValide("a1", "a6"));
        assertFalse(piece.estDeplacementValide("a1", "a9"));

        // deplacement rangee
        assertTrue(piece.estDeplacementValide("a1", "f1"));
        assertFalse(piece.estDeplacementValide("a1", "j1"));

        // deplacement diagonale
        assertFalse(piece.estDeplacementValide("a1", "d3"));
        assertFalse(piece.estDeplacementValide("b1", "e5"));
        assertFalse(piece.estDeplacementValide("a1", "g3"));
    }

    /**
     * Méthode de test pour les déplacements possibles selon la coordonnée
     */
    @Test
    @Override
    public void deplacementsPossiblesSelonCoordonnee() {
        Tour piece = (Tour) creerPiece(Piece.Couleur.NOIR);

        List<String> listeTest = asList("a2", "b1", "c2", "b3", "d2", "b4", "e2", "b5", "f2", "g2",
                "h2", "b6", "b7", "b8");
        Collections.sort(listeTest);

        List<String> listeTest2 = asList("h7", "h6", "h5", "h4", "h3", "h2", "h1", "g8", "f8", "e8",
                "d8", "c8", "b8", "a8");
        Collections.sort(listeTest2);

        ArrayList<String> list = piece.deplacementsPossiblesSelonCoordonnee("b2");
        Collections.sort(list);

        ArrayList<String> list2 = piece.deplacementsPossiblesSelonCoordonnee("h8");
        Collections.sort(list2);

        assertArrayEquals(listeTest.toArray(), list.toArray());

        assertArrayEquals(listeTest2.toArray(), list2.toArray());
    }
}
