package com.example.echec_android.gui;

import android.os.Bundle;


/**
 * Classe qui gère les fragments et les changements de mode.
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */
public class MainActivity extends DualFragmentActivity implements FragmentPartie.CallBacks {

    /**
     * Variable qui contient le mode actuel de l'application
     */
    public static Mode actualMode;

    /**
     * Méthode de base onCreate qui ajoute la vue du fragment
     * @param savedInstanceState l'état d'instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopFragment(DepartFragment.newInstance());
        actualMode = Mode.Depart;

        setMainFragment(FragmentPartie.newInstance());
    }

    /**
     * Méthode lorsqu'il y a un changement de mode
     * @param p_mode les différents modes soit départ ou partie
     * @param p_id le id du mode
     */
    @Override
    public void onChangeMode(Mode p_mode, String p_id) {
        switch (p_mode) {
            case Depart:
                actualMode = Mode.Depart;
                setTopFragment(DepartFragment.newInstance());
                break;
            case Partie:
                actualMode = Mode.Partie;
                setTopFragment(HistoriqueFragment.newInstance());
                break;
            default:
                break;
        }
    }
}
