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
public abstract class DualFragmentActivity extends AppCompatActivity {

    public void setTopFragment (Fragment p_fragment) {
        setFragment(R.id.top_container, p_fragment);
    }

    public void setMainFragment (Fragment p_fragment) {
        setFragment(R.id.main_container, p_fragment);
    }

    private void setFragment(int resId, Fragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(resId);

        if (fragment == null) {
            fragment = newFragment;
            fragmentManager.beginTransaction().add(resId, fragment).commit();
        }
        else {
            fragmentManager.beginTransaction().replace(resId, newFragment).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dual_fragment_activity);
    }
}
