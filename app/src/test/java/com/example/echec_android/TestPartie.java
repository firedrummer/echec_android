package com.example.echec_android;


import android.support.v4.util.Pair;

import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.partie.Joueur;
import com.example.echec_android.partie.Partie;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

import static com.example.echec_android.echec.Piece.Couleur.BLANC;
import static com.example.echec_android.echec.Piece.Couleur.NOIR;

/**
 * Classe de tests pour une partie d'un jeu d'échec
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class TestPartie {
    private Echiquier m_echiquier = new Echiquier();
    private LinkedHashMap<Integer, Pair<Joueur, Echiquier>> m_historique = new LinkedHashMap<>();
    private Partie partie = new Partie();
    private Joueur j1 = new Joueur(BLANC, "Michel");
    private Joueur j2 = new Joueur(NOIR, "Gaetan");

    /**
     * Méthode création qui va créer une partie
     */
    @Test
    public void creation() {
        //Initialisation echiquier et set de celui-ci sur la partie
        m_echiquier.initialiser();
        partie.setEchiquier(m_echiquier);
        Assert.assertEquals(m_echiquier, partie.getEchiquier());

        //set des joueurs
        partie.setJoueurBlanc(j1);
        partie.setJoueurNoir(j2);

        //Test des couleurs de pièces pour les joueurs
        Assert.assertEquals(j1, partie.getJoueurBlanc());
        Assert.assertEquals(j2, partie.getJoueurNoir());

        //Joueur actuel
        Assert.assertEquals(j1, partie.getJoueurActuel());

        //On joue un tour pour revérifier le joueur actuel
        Echiquier echiquier2 = partie.getEchiquier();
        Assert.assertTrue(partie.jouerTour("a2", "a4"));
        partie.restaurerPartie(echiquier2);
        Assert.assertEquals(echiquier2, partie.getEchiquier());

        partie.changerTour();
        Assert.assertEquals(j2, partie.getJoueurActuel());

        Echiquier echiquier3 = partie.getEchiquier();
        Assert.assertTrue(partie.jouerTour("a2", "a4"));

        partie.restaurerDernierMouvement();
        Assert.assertEquals(echiquier3, partie.getEchiquier());
    }
}
