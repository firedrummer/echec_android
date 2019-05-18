package com.example.echec_android;

import com.example.echec_android.echec.Piece;
import com.example.echec_android.partie.Joueur;

import org.junit.Assert;
import org.junit.Test;

import static com.example.echec_android.echec.Piece.Couleur.BLANC;
import static com.example.echec_android.echec.Piece.Couleur.NOIR;

/**
 * Classe de tests pour les joueurs
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestJoueur {
    private Joueur joueur = new Joueur(Piece.Couleur.BLANC, "myName");

    @Test
    public void creation() {

        String nouveauNom = "newName";

        //test pour getCouleur au depart
        Assert.assertEquals(BLANC, joueur.getCouleurPiece());

        //test pour setCouleurPiece
        joueur.setCouleurPiece(NOIR);

        //test pour getCouleur lorsqu'on a changé
        Assert.assertEquals(NOIR, joueur.getCouleurPiece());

        //test pour getNomJoueur
        Assert.assertEquals("myName", joueur.getNomJoueur());

        //test pour setNomJoueur
        joueur.setNomJoueur(nouveauNom);

        //test pour setNomJoueur
        Assert.assertEquals("newName", joueur.getNomJoueur());

    }
}
