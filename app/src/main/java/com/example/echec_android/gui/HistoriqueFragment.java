package com.example.echec_android.gui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.echec_android.R;
import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.partie.Joueur;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Classe fragment de l'historique de la partie
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class HistoriqueFragment extends Fragment {

    private RecyclerView m_historiqueRecyclerView;

    private HistoriqueAdapter m_adapter;

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
     *
     * @param savedInstanceState l'état d'instance
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Méthode qui cree la vue et la retourne
     *
     * @param inflater           l'inflater
     * @param container          container
     * @param savedInstanceState l'état d'instance
     * @return v la vue
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.historique_layout, container, false);

        m_historiqueRecyclerView = Objects.requireNonNull(getView()).findViewById(R.id.historique_recycler_view);
        m_historiqueRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    /**
     * Actualise la liste des tours
     */
    public void updateUI() {
        if (m_adapter == null) {
            m_adapter = new HistoriqueAdapter(FragmentPartie.m_partie.getHistoriquePartie());
            m_historiqueRecyclerView.setAdapter(m_adapter);
        } else {
            m_adapter.setHistorique(FragmentPartie.m_partie.getHistoriquePartie());
            m_adapter.setHistorique(FragmentPartie.m_partie.getHistoriquePartie());
            m_adapter.notifyDataSetChanged();
        }
    }

    /**
     * Adapateur de l'historique
     *
     * @author Yanick Bellavance
     * @author William Blackburn
     */
    private class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueHolder> {

        private LinkedHashMap<Integer, Pair<Joueur, Echiquier>> m_historique;

        HistoriqueAdapter(LinkedHashMap<Integer, Pair<Joueur, Echiquier>> p_historique) {
            m_historique = p_historique;
        }

        void setHistorique(LinkedHashMap<Integer, Pair<Joueur, Echiquier>> p_historique) {
            m_historique = p_historique;
        }

        @NonNull
        @Override
        public HistoriqueHolder onCreateViewHolder(@NonNull ViewGroup p_viewGroup, int p_i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new HistoriqueHolder(layoutInflater, p_viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoriqueHolder p_historiqueHolder, int p_i) {
            p_historiqueHolder.Bind(p_i + 1, Objects.requireNonNull(m_historique.
                    get(p_i + 1)).first, Objects.requireNonNull(m_historique.get(p_i + 1)).second);
        }

        @Override
        public int getItemCount() {
            return m_historique.size();
        }
    }

    /**
     * Holder de l'historique
     *
     * @author Yanick Bellavance
     * @author William Blackburn
     */
    private class HistoriqueHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Echiquier m_echiquier;
        private Integer m_numero;
        private Joueur m_joueur;

        private TextView m_numero_tour;
        private TextView m_nom_joueur;
        private TextView m_representation_echiquier;

        HistoriqueHolder(LayoutInflater p_inflater, ViewGroup p_container) {
            super(p_inflater.inflate(R.layout.historique_item, p_container, false));

            itemView.setOnClickListener(this);

            m_numero_tour = itemView.findViewById(R.id.numero_tour);
            m_nom_joueur = itemView.findViewById(R.id.historique_joueur);
            m_representation_echiquier = itemView.findViewById(R.id.representation);
        }

        void Bind(Integer p_numero, Joueur p_joueur, Echiquier p_echiquier) {
            m_numero = p_numero;
            m_joueur = p_joueur;
            m_echiquier = p_echiquier;

            m_numero_tour.setText(String.format("%s", m_numero.toString()));
            m_nom_joueur.setText(m_joueur.getNomJoueur());
            m_representation_echiquier.setText(m_echiquier.obtenirRepresentation());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
