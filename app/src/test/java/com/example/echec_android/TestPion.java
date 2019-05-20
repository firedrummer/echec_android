package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.echec.Pion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
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
        assertEquals(Piece.Type.PION, piece.getType());

        //Ce test normalement serait seulement dans testPièces, mais nous l'avons testé dans testPion aussi
        //pour avoir un meilleur code coverage
        assertTrue(piece.estBlanc());

        // Ajout du pion noir
        Piece piece2 = creerPiece(Piece.Couleur.NOIR);
        assertEquals("P", piece2.obtenirRepresentation());
        assertEquals(1.0, piece2.obtenirPointagePiece(), 0.0);

        //Ce test normalement serait seulement dans testPièces, mais nous l'avons testé dans testPion aussi
        //pour avoir un meilleur code coverage
        assertTrue(piece2.estNoir());
    }

    /**
     * Méthode qui crée la pièce pion
     * @param p_couleur couleur choisie
     * @return la pièce créée avec la couleur choisie
     */
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
        assertFalse(piece.estDeplacementValide("a1", "a4"));

        // deplacement rangee
        assertFalse(piece.estDeplacementValide("a1", "b1"));
        assertFalse(piece.estDeplacementValide("a1", "c1"));

        // deplacement diagonale
        assertFalse(piece.estDeplacementValide("a1", "b2"));
        assertFalse(piece.estDeplacementValide("a1", "c3"));
    }

    /**
     * Méthode de test pour les déplacements possibles selon la coordonnée
     */
    @Test
    @Override
    public void deplacementsPossiblesSelonCoordonnee() {
        Pion piece = (Pion) creerPiece(Piece.Couleur.NOIR);
        Pion piece2 = (Pion) creerPiece(Piece.Couleur.BLANC);

        List<String> listeTest = asList("a5", "a6");
        Collections.sort(listeTest);

        List<String> listeTest2 = asList("b3", "b4");
        Collections.sort(listeTest2);

        ArrayList<String> list = piece.deplacementsPossiblesSelonCoordonnee("a7");
        Collections.sort(list);

        ArrayList<String> list2 = piece2.deplacementsPossiblesSelonCoordonnee("b2");
        Collections.sort(list2);

        //Test selon position de tour 2 soit 3ème rangée
        assertArrayEquals(listeTest.toArray(), list.toArray());

        // Test selon position de base 1ère ligne
        assertArrayEquals(listeTest2.toArray(), list2.toArray());
    }
}


