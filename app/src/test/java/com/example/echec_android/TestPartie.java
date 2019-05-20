package com.example.echec_android;


import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.echec.Piece;
import com.example.echec_android.partie.Joueur;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de tests pour une partie d'un jeu d'échec
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestPartie {
    private Echiquier m_echiquier;

    /**
     * Méthode création qui va créer une partie
     */
    @Test
    public void creation() {
        Joueur j1 = new Joueur(Piece.Couleur.BLANC, "Michel");
        Joueur j2 = new Joueur(Piece.Couleur.NOIR, "Gaston");

        //Test des couleurs de pièces pour les joueurs
        Assert.assertEquals(Piece.Couleur.BLANC, j1.getCouleurPiece());
        j1.setCouleurPiece(Piece.Couleur.NOIR);
        j2.setCouleurPiece(Piece.Couleur.BLANC);
        Assert.assertEquals(Piece.Couleur.NOIR, j1.getCouleurPiece());

        //Test des noms des joueurs
        Assert.assertEquals("Michel", j1.getNomJoueur());
        j1.setNomJoueur("Gaetan");
        Assert.assertEquals("Gaetan", j1.getNomJoueur());



    }
}
