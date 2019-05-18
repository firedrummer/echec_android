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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTopFragment(DepartFragment.newInstance());
        actualMode = Mode.Depart;

        setMainFragment(FragmentPartie.newInstance());
    }

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
