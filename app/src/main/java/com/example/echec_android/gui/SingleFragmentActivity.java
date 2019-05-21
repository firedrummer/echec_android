package com.example.echec_android.gui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.echec_android.R;

/**
 * Classe qui gère les fragments.
 * @author Yanick Bellavance
 * @author William Blackburn
 */
abstract class SingleFragmentActivity extends AppCompatActivity {
    //Declaration de la methode abstraite
    protected abstract Fragment createFragment();

    /**
     * Méthode oncreate de base
     *
     * @param savedInstanceState l'etat d'instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
