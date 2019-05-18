package com.example.echec_android.gui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.echec_android.R;
import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.echec.Piece;
import com.example.echec_android.partie.Joueur;
import com.example.echec_android.partie.Partie;

import java.util.LinkedHashMap;

public class FragmentPartie extends Fragment {

    /**
     * Les 64 cases du tableau d'échec sont représentées par un array de boutons à 2 dimensions
     */
    Button[][] m_boutons = new Button[8][8];
    Button m_dernierBoutonCliquer;

    static Partie m_partie = new Partie();

    public void InitialiserTableau(TableLayout p_table, Echiquier p_echiquier) {

        if (p_echiquier == null) {
            m_partie.getEchiquier().initialiser();
        } else {
            m_partie.setEchiquier(p_echiquier);
        }

        int numeroBouton = 1;

        for (int i = 0; i < 9; i++) {
            TableRow rangee = new TableRow(this.getActivity());
            p_table.addView(rangee);

            for (int j = 0; j < 9; j++) {
                if (i == 0 && j == 0) {
                    // ajouter quelchose de vide
                    TextView textView = new TextView(getContext());
                    textView.setText(" ");
                    rangee.addView(textView);

                } else if (i == 0) {
                    // Les lettre de A - H
                    TextView textView = new TextView(getContext());
                    textView.setText((char) ('a' + j));
                    rangee.addView(textView);
                } else if (j == 0) {
                    // Les chiffres de 1 - 8
                    TextView textView = new TextView(getContext());
                    textView.setText(i);
                    rangee.addView(textView);
                } else {
                    // Ajouter les bouttons a partir de i = 1 et j = 1
                    final Button bouton = new Button(this.getActivity());

                    String coordonnee = "" + (char) ('a' + (i - 1)) + (char) j;

                    bouton.setTag(coordonnee);

                    Piece piece = m_partie.getEchiquier().getPiece(coordonnee);

                    if (piece != null) {
                        bouton.setText(piece.obtenirRepresentation());
                    } else {
                        bouton.setText("");
                    }

                    int noir = Color.BLACK;
                    int blanc = Color.WHITE;

                    if (i % 2 == 0 && j % 2 == 0) {

                        bouton.setBackgroundColor(noir);
                    } else {
                        bouton.setBackgroundColor(blanc);
                    }

                    bouton.setOnClickListener(v -> {
                        if (m_dernierBoutonCliquer == null) {
                            m_dernierBoutonCliquer = bouton;

                            LinkedHashMap<String, String> mouvementPossible = m_partie.
                                    getEchiquier().getToursPossibleSelonPiece(piece, coordonnee);

                            int couleurMouvement = Color.GRAY;

                            for (String mouvements : mouvementPossible.values()) {
                                m_boutons[mouvements.charAt(0) - 'a'][mouvements.charAt(1) - '1'].setBackgroundColor(couleurMouvement);
                            }
                        } else {
                            m_partie.jouerTour(m_dernierBoutonCliquer.getTag().toString(), coordonnee);
                            InitialiserTableau(p_table, m_partie.getEchiquier());
                        }
                    });

                    m_boutons[i][j] = bouton;
                    rangee.addView(bouton);
                    numeroBouton++;
                }
            }
        }
    }

    /**
     * Crée une nouvelle instance de FragmentPartie
     *
     * @return retourne une nouvelle instance de FragmentPartie
     */
    public static FragmentPartie newInstance() {
        Bundle args = new Bundle();

        FragmentPartie fragment = new FragmentPartie();
        fragment.setArguments(args);

        return fragment;
    }

    public static void setJoueurs(String p_nomJoueurBlanc, String p_nomJoueurNoir) {
        Joueur joueurBlanc = new Joueur(Piece.Couleur.BLANC, p_nomJoueurBlanc);
        Joueur joueurNoir = new Joueur(Piece.Couleur.NOIR, p_nomJoueurNoir);

        m_partie.setJoueurBlanc(joueurBlanc);
        m_partie.setJoueurNoir(joueurNoir);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.partie_fragment_layout, container, false);

        return v;
    }

    /**
     * Interface de gestion des modes de l'application.
     */
    public interface CallBacks {
        void onChangeMode(Mode mode, String id);
    }
}



