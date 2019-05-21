package com.example.echec_android.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Classe jeuActivity qui extend le fragment activity simple
 *
 * @author Yanick Bellavance
 */
public class JeuActivity extends SingleFragmentActivity {
    //Le new intent
    public static Intent newIntent(Context p_context, String p_nomJoueurBlanc, String p_nomJoueurNoir) {
        Intent intent = new Intent(p_context, JeuActivity.class);

        FragmentPartie.setJoueurs(p_nomJoueurBlanc, p_nomJoueurNoir);

        return intent;
    }

    /**
     * Méthode qui crée le fragment
     * @return le fragment en nouvelle instance
     */
    @Override
    protected Fragment createFragment() {
        return FragmentPartie.newInstance();
    }
}
