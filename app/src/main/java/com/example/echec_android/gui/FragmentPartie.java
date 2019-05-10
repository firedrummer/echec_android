package com.example.echec_android.gui;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FragmentPartie extends Fragment {

    /**
     * Les 64 cases du tableau d'échec sont représentées par un array de boutons à 2 dimensions
     */
    Button[][] m_boutons = new Button[8][8];

    public void InitialiserTableau(TableLayout p_table, char[] p_matrice) {
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

                    bouton.setTag("" + (char) ('a' + (i - 1)) + (char) j);


                    bouton.setText(" "); // TODO initialiser le jeu d'echec


                    bouton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO
                        }
                    });

                    m_boutons[i][j] = bouton;
                    rangee.addView(bouton);
                    numeroBouton++;
                }

            }
        }
    }
}



