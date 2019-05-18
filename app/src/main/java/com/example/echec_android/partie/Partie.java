package com.example.echec_android.partie;

import android.support.v4.util.Pair;

import com.example.echec_android.echec.Echiquier;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Classe partie qui constitue un échiquier ainsi que des joueurs
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Partie {
    //Echiquier dasn une partie entre 2 joueurs
    private Echiquier m_echiquier;

    /**
     * Historique de la partie le Pair<Joueur, Echiquier> stock le joueur ayant effectuer le tour et l'echiquier du moment
     */
    private LinkedHashMap<Integer, Pair<Joueur, Echiquier>> m_historique = new LinkedHashMap<>();

    /**
     *
     */
    private Joueur m_joueur1;

    /**
     *
     */
    private Joueur m_joueur2;

    /**
     * La valeur est de 1 si c'est le tour du joueur 1 sinon la valeur est 2
     */
    private int m_tourJoueur = 1;


    public Joueur getJoueur1() {
        return m_joueur1;
    }

    public void setJoueur1(Joueur p_joueur) {
        m_joueur1 = p_joueur;
    }

    public Joueur getJoueur2() {
        return m_joueur2;
    }

    public void setJoueur2(Joueur p_joueur) {
        m_joueur2 = p_joueur;
    }

    /**
     * Getter pour l'historique de la partie
     * @return l'historique de la partie
     */
    public LinkedHashMap<Integer, Pair<Joueur, Echiquier>> getHistoriquePartie() {
        return m_historique;
    }

    /**
     * Restaure la partie
     * @param p_echiquier l'échiquier
     */
    public void restaurerPartie(Echiquier p_echiquier) {
        m_echiquier = p_echiquier;
    }

    /**
     * Tente de jouer le tour, s'il ne réussit pas ret
     *
     * @param p_coordonneeDebut coordonnée de départ de la piece
     * @param p_coordonneFin    coordonnée de destination de la piece
     * @return true le tour réussit sinon false
     */
    public boolean jouerTour(String p_coordonneeDebut, String p_coordonneFin) {
        boolean reussite = m_echiquier.deplacerPiece(p_coordonneeDebut, p_coordonneFin);

        if (reussite) {
            Joueur joueur = m_echiquier.getPiece(p_coordonneeDebut).getCouleur() == m_joueur1.getCouleurPiece() ? m_joueur1 : m_joueur2;
            m_historique.put(m_historique.size() + 1, new Pair<>(joueur, m_echiquier));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restaure l'historique au numéro du tour saisi
     * @param p_numeroTourRestaurer numéro du tour où on veut restaurer
     */
    public void restaurerHistorique(Integer p_numeroTourRestaurer) {
        m_echiquier = Objects.requireNonNull(m_historique.get(p_numeroTourRestaurer)).second;

        for (Integer i = p_numeroTourRestaurer + 1; i < m_historique.size(); i++) {
            m_historique.remove(i);
        }
    }
}