package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Roi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Classe de tests pour les pièces de type roi
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestRoi extends TestPiece {
    /**
     * Méthode test pour les pièces
     */
    @Test
    public void creation() {
        // Ajout du Roi Blanc
        Piece piece = creerPiece(Piece.Couleur.BLANC);
        assertEquals("r", piece.obtenirRepresentation());
        assertEquals(0.0, piece.obtenirPointagePiece(), 0.0);
        assertEquals(Piece.Type.ROI, piece.getType());

        // Ajout du Roi Noir
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("R", piece2.obtenirRepresentation());
        assertEquals(0.0, piece2.obtenirPointagePiece(), 0.0);
    }

    /**
     * Méthode qui crée la pièce du Roi
     * @param p_couleur couleur du roi choisie
     * @return le roi avec sa couleur choisie
     */
    @Override
    protected Piece creerPiece(Piece.Couleur p_couleur) {
        return new Roi(p_couleur);
    }

    /**
     * Méthode qui test si les déplacements du Roi sont valides ou invalides
     */
    @Test
    @Override
    public void deplacementValide() {
        Roi piece = (Roi) creerPiece(Piece.Couleur.BLANC);

        // deplacement hors echiquier
        assertFalse(piece.estDeplacementValide("d4", "z9"));

        // deplacement colonne
        assertTrue(piece.estDeplacementValide("a1", "a2"));
        assertFalse(piece.estDeplacementValide("a1", "a3"));

        // deplacement rangee
        assertTrue(piece.estDeplacementValide("a1", "b1"));
        assertFalse(piece.estDeplacementValide("a1", "c1"));

        // deplacement diagonale
        assertTrue(piece.estDeplacementValide("a1", "b2"));
        assertFalse(piece.estDeplacementValide("a1", "c3"));
    }

    /**
     * Méthode de test possibles selon la coordonnée
     */
    @Test
    @Override
    public void deplacementsPossiblesSelonCoordonnee() {
        Roi piece = (Roi) creerPiece(Piece.Couleur.NOIR);

        List<String> listeTest = asList("a8", "c8", "a7", "b7", "c7");
        Collections.sort(listeTest);

        List<String> listeTest2 = asList("b3", "d3", "d4", "c4", "b4", "b2", "c2", "d2");
        Collections.sort(listeTest2);

        ArrayList<String> list = piece.deplacementsPossiblesSelonCoordonnee("b8");
        Collections.sort(list);

        ArrayList<String> list2 = piece.deplacementsPossiblesSelonCoordonnee("c3");
        Collections.sort(list2);

        assertArrayEquals(listeTest.toArray(),list.toArray());

        assertArrayEquals(listeTest2.toArray(),list2.toArray());
    }
}