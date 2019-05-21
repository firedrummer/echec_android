package com.example.echec_android.gui;

import android.support.v4.app.Fragment;


/**
 * Classe qui gère les fragments et les changements de mode.
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */
public class MainActivity extends SingleFragmentActivity {

    /**
     * Méthode create fragment qui crée le fragment
     *
     * @return la nouvelle instance du fragment
     */
    @Override
    protected Fragment createFragment() {
        return DepartFragment.newInstance();
    }
}
