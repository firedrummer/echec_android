package com.example.echec_android;

import com.example.echec_android.echec.Cavalier;
import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Piece.Couleur;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCavalier extends TestPiece {

    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Ajout d'un cavalier blanc
        Piece piece = creerPiece(Piece.Couleur.BLANC);
        assertEquals("c", piece.obtenirRepresentation());
        assertEquals(2.5, piece.obtenirPointagePiece(), 0.0);
        assertEquals(Piece.Type.CAVALIER, piece.getType());

        // Ajout d'un cavalier noir
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("C", piece2.obtenirRepresentation());
        assertEquals(2.5, piece2.obtenirPointagePiece(), 0.0);
    }

    @Override
    protected Piece creerPiece(Couleur p_couleur) {
        return new Cavalier(p_couleur);
    }

    /**
     * Méthode qui test si les déplacements du cavalier sont valides ou invalides
     */
    @Test
    @Override
    public void deplacementValide() {
        Cavalier piece = (Cavalier) creerPiece(Couleur.BLANC);

        // deplacement hors echiquier
        assertFalse(piece.estDeplacementValide("d4", "z9"));

        // deplacement cavalier valide
        assertTrue(piece.estDeplacementValide("d4", "b3"));
        assertTrue(piece.estDeplacementValide("d4", "b5"));

        assertTrue(piece.estDeplacementValide("d4", "c2"));
        assertTrue(piece.estDeplacementValide("d4", "c6"));

        assertTrue(piece.estDeplacementValide("d4", "e2"));
        assertTrue(piece.estDeplacementValide("d4", "e6"));

        assertTrue(piece.estDeplacementValide("d4", "f3"));
        assertTrue(piece.estDeplacementValide("d4", "f5"));

        // deplacement invalide
        assertFalse(piece.estDeplacementValide("d4", "d8"));
        assertFalse(piece.estDeplacementValide("d4", "h4"));
        assertFalse(piece.estDeplacementValide("d4", "h8"));
    }

    @Test
    @Override
    public void deplacementsPossiblesSelonCoordonnee() {
        Cavalier piece = (Cavalier) creerPiece(Couleur.NOIR);

        List<String> listeTest = asList("b4", "b6", "c3", "c7", "e3", "e7", "f4", "f6");
        Collections.sort(listeTest);

        List<String> listeTest2 = asList("b3", "c2");
        Collections.sort(listeTest2);

        ArrayList<String> list = piece.deplacementsPossiblesSelonCoordonnee("d5");
        Collections.sort(list);

        ArrayList<String> liste2 = piece.deplacementsPossiblesSelonCoordonnee("a1");
        Collections.sort(liste2);

        assertArrayEquals(listeTest.toArray(), list.toArray());
        assertArrayEquals(listeTest2.toArray(), liste2.toArray());
    }
}

