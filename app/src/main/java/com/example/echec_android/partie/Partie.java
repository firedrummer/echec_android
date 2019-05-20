package com.example.echec_android.partie;

import android.support.v4.util.Pair;

import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.echec.Piece;

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
     * joueur blanc
     */
    private Joueur m_joueurBlanc;

    /**
     * joueur noir
     */
    private Joueur m_joueurNoir;

    /**
     * Couleur du joueur actuel, le premier joueur à commencer sont les blancs
     */
    private Piece.Couleur couleurTour = Piece.Couleur.BLANC;

    /**
     * Méthode qui obtient la couleur du tour
     * @return la couleur de à qui c'est le tour
     */
    public Piece.Couleur getCouleurTour() {
        return couleurTour;
    }

    /**
     * Méthode qui fait changer de tour
     */
    public void changerTour() {
        if (couleurTour == Piece.Couleur.BLANC) {
            couleurTour = Piece.Couleur.NOIR;
        } else {
            couleurTour = Piece.Couleur.BLANC;
        }
    }

    /**
     * Getter pour le joueur actuel
     * @return le joueur dont c'est le tour
     */
    public Joueur getJoueurActuel() {
        if (getCouleurTour() == Piece.Couleur.BLANC) {
            return getJoueurBlanc();
        } else {
            return getJoueurNoir();
        }
    }

    /**
     * getter pour joueur
     *
     * @return le joueur 1
     */
    public Joueur getJoueurBlanc() {
        return m_joueurBlanc;
    }

    /**
     * setter pour le joueur 1
     *
     * @param p_joueur le joueur 1 dans la partie
     */
    public void setJoueurBlanc(Joueur p_joueur) {
        m_joueurBlanc = p_joueur;
    }

    /**
     * Getter pour le joueur 2
     *
     * @return le joueur 2
     */
    public Joueur getJoueurNoir() {
        return m_joueurNoir;
    }

    /**
     * Setter pour le joueur 2
     *
     * @param p_joueur joueur à modifier
     */
    public void setJoueurNoir(Joueur p_joueur) {
        m_joueurNoir = p_joueur;
    }

    /**
     * @return obtient l'échiquer actuel
     */
    public Echiquier getEchiquier() {
        return m_echiquier;
    }

    /**
     * Change la valeur de l'échiquer pour un nouveau
     *
     * @param p_echiquier echiquier à mettre la valeur
     */
    public void setEchiquier(Echiquier p_echiquier) {
        m_echiquier = p_echiquier;
    }

    /**
     * Getter pour l'historique de la partie
     *
     * @return l'historique de la partie
     */
    public LinkedHashMap<Integer, Pair<Joueur, Echiquier>> getHistoriquePartie() {
        return m_historique;
    }

    /**
     * Restaure la partie
     *
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
            Joueur joueur = m_echiquier.getPiece(p_coordonneeDebut).getCouleur() == m_joueurBlanc.getCouleurPiece() ? m_joueurBlanc : m_joueurNoir;
            m_historique.put(m_historique.size() + 1, new Pair<>(joueur, m_echiquier));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restaure l'historique au numéro du tour saisi
     *
     * @param p_numeroTourRestaurer numéro du tour où on veut restaurer
     */
    public void restaurerHistorique(Integer p_numeroTourRestaurer) {
        m_echiquier = Objects.requireNonNull(m_historique.get(p_numeroTourRestaurer)).second;

        for (Integer i = p_numeroTourRestaurer + 1; i < m_historique.size(); i++) {
            m_historique.remove(i);
        }
    }

    /**
     * Restaure le dernier mouvement et l'enleve de l'historique
     */
    public void restaurerDernierMouvement() {
        m_echiquier = Objects.requireNonNull(m_historique.get(m_historique.size())).second;

        m_historique.remove(m_historique.size() - 1);
    }
}