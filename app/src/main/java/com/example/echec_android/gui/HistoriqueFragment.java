package com.example.echec_android.gui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echec_android.R;

/**
 * Classe fragment de l'historique de la partie
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class HistoriqueFragment extends Fragment {
    /**
     * Callback pour le mode actuel
     */
    private FragmentPartie.CallBacks mode;

    /**
     * Crée une nouvelle instance d'HistoriqueFragment.
     *
     * @return retourne une instance d'HistoriqueFragment
     */
    public static HistoriqueFragment newInstance() {

        Bundle args = new Bundle();

        HistoriqueFragment fragment = new HistoriqueFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Méthode onCreate qui ajoute la vue du fragment
     * @param savedInstanceState l'état d'instance
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Méthode qui cree la vue et la retourne
     * @param inflater l'inflater
     * @param container container
     * @param savedInstanceState l'état d'instance
     * @return v la vue
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.historique_layout, container, false);


        return v;
    }
}
