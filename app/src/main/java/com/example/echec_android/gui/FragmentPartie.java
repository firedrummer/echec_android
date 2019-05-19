package com.example.echec_android.gui;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echec_android.R;
import com.example.echec_android.echec.Echiquier;
import com.example.echec_android.echec.Piece;
import com.example.echec_android.partie.Joueur;
import com.example.echec_android.partie.Partie;

import java.util.LinkedHashMap;
import java.util.Objects;

public class FragmentPartie extends Fragment {

    static Partie m_partie = new Partie();
    /**
     * Les 64 cases du tableau d'échec sont représentées par un array de boutons à 2 dimensions
     */
    Button[][] m_boutons = new Button[8][8];
    Button m_dernierBoutonCliquer;

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

    /**
     * Méthode qui set les 2 joueurs
     * @param p_nomJoueurBlanc le joueur blanc
     * @param p_nomJoueurNoir le joueur noir
     */
    public static void setJoueurs(String p_nomJoueurBlanc, String p_nomJoueurNoir) {
        m_partie.setJoueurBlanc(new Joueur(Piece.Couleur.BLANC, p_nomJoueurBlanc));
        m_partie.setJoueurNoir(new Joueur(Piece.Couleur.NOIR, p_nomJoueurNoir));
    }

    /**
     * Méthode qui initialise le tableau
     * @param p_table le tableLayout qui servira pour mettre le tableau
     * @param p_echiquier l'échiquier
     */
    public void InitialiserTableau(TableLayout p_table, Echiquier p_echiquier) {

        if (p_echiquier == null) {
            Echiquier echiquier = new Echiquier();
            echiquier.initialiser();
            m_partie.setEchiquier(echiquier);
        } else {
            m_partie.setEchiquier(p_echiquier);
        }

        Display display = Objects.requireNonNull(getActivity()).getWindow().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int largeur;
        if (size.x > size.y) {
            largeur = size.y / 9;
        } else {
            largeur = size.x / 9;
        }

        int numeroBouton = 1;

        for (int i = 0; i < 9; i++) {
            TableRow rangee = new TableRow(this.getActivity());
            p_table.addView(rangee);

            for (int j = 0; j < 9; j++) {
                if (i == 0 && j == 0) {
                    // ajouter quelchose de vide
                    TextView textView = new TextView(getContext());
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setText(" ");
                    rangee.addView(textView, largeur / 2, largeur / 2);

                } else if (i == 0) {
                    // Les lettre de A - H
                    TextView textView = new TextView(getContext());
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setText(String.format("%s", (char) ('a' + (j - 1))));
                    //textView.setText("test");
                    rangee.addView(textView, 50, 50);
                } else if (j == 0) {
                    // Les chiffres de 1 - 8
                    TextView textView = new TextView(getContext());
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setText(String.format("%s", i));
                    rangee.addView(textView, largeur / 2, largeur / 2);
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

                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            bouton.setBackgroundColor(blanc);
                        } else {
                            bouton.setBackgroundColor(noir);
                        }
                    } else {
                        if (j % 2 == 0) {
                            bouton.setBackgroundColor(noir);
                        } else {
                            bouton.setBackgroundColor(blanc);
                        }
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
                            boolean valide = m_partie.jouerTour(m_dernierBoutonCliquer.getTag().toString(), coordonnee);

                            if (!valide) {
                                Toast.makeText(getContext(),
                                        "Votre coup était invalide veuillez réessayer!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // todo echec

                            }




                            InitialiserTableau(p_table, m_partie.getEchiquier());
                        }
                    });

                    m_boutons[i - 1][j - 1] = bouton;
                    rangee.addView(bouton, largeur, largeur);
                    numeroBouton++;
                }
            }
        }
    }

    /**
     * Méthode de base onCreate qui cree la view dans l'affichage
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.partie_fragment_layout, container, false);

        TableLayout tableLayout = v.findViewById(R.id.tableLayout);
        Button boutonPrecedent = v.findViewById(R.id.precedent);
        Button boutonConfirmer = v.findViewById(R.id.fin);


        InitialiserTableau(tableLayout, m_partie.getEchiquier());

        return v;
    }

    /**
     * Interface de gestion des modes de l'application.
     */
    public interface CallBacks {
        void onChangeMode(Mode mode, String id);
    }
}



