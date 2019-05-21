package com.example.echec_android.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.echec_android.R;

/**
 * Classe qui gère le fragment en lien avec l'obtention du nom des joueurs
 * @author Yanick Bellavance
 * @author William Blackburn
 */
public class DepartFragment extends Fragment {
    /**
     * Crée une nouvelle instance d'AucunModeFragment.
     *
     * @return retourne une instance de DepartFragment
     */
    public static DepartFragment newInstance() {
        Bundle args = new Bundle();

        DepartFragment fragment = new DepartFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Méthode de base on create qui crée le fragment avec un bundle
     * @param savedInstanceState l'état d'instance
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Méthode onCreateView de base qui crée la vue avec un inflater le container etc
     * @param inflater l'inflater
     * @param container le cotainer
     * @param savedInstanceState l'état d'instance
     * @return la vue (v)
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.depart_layout, container, false);

        Button m_confirmerButton = v.findViewById(R.id.confirmer);
        EditText m_nomJoueurBlanc = v.findViewById(R.id.nom_joueur_blanc);
        EditText m_nomJoueurNoire = v.findViewById(R.id.nom_joueur_noir);

        m_confirmerButton.setOnClickListener(v1 -> {
            if (m_nomJoueurBlanc.getText().length() > 3 && m_nomJoueurNoire.getText().length() > 3 &&
                    m_nomJoueurNoire.getText() != m_nomJoueurBlanc.getText()) {

                Intent intent = JeuActivity.newIntent(getActivity(), m_nomJoueurBlanc.getText().toString(), m_nomJoueurNoire.getText().toString());

                startActivity(intent);

            } else {
                Toast.makeText(getActivity(), R.string.toast_info_joueur, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
