package com.example.echec_android;

import com.example.echec_android.echec.Fou;
import com.example.echec_android.echec.Piece;

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
        assertEquals(Piece.Type.FOU, piece.getType());

        // Ajout du fou noir
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("F", piece2.obtenirRepresentation());
        assertEquals(3.0, piece2.obtenirPointagePiece(), 0.0);
    }

    /**
     * Méthode qui crée la pièce fou avec la couleur spécifiée
     * @param p_couleur coueur choisie pour la pièce en question
     * @return un nouveau Fou avec la couleur passée en paramètre
     */
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

    /**
     * Méthode de test pour les déplacements possibles selon la coordonnée
     */
    @Test
    @Override
    public void deplacementsPossiblesSelonCoordonnee() {
        Fou piece = (Fou) creerPiece(Piece.Couleur.NOIR);

        List<String> listeTest = asList("b2", "c3", "d4", "e5", "f6", "g7", "h8");
        Collections.sort(listeTest);

        List<String> listeTest2 = asList("c1", "d2", "f4", "g5", "h6", "a7", "b6",
                "c5", "d4", "f2", "g1");
        Collections.sort(listeTest2);

        ArrayList<String> list = piece.deplacementsPossiblesSelonCoordonnee("a1");
        Collections.sort(list);

        ArrayList<String> list2 = piece.deplacementsPossiblesSelonCoordonnee("e3");
        Collections.sort(list2);


        assertArrayEquals(listeTest.toArray(), list.toArray());
        assertArrayEquals(listeTest2.toArray(), list2.toArray());
    }
}
