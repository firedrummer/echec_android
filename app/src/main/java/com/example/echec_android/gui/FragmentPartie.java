package com.example.echec_android.gui;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

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
                } else if (i == 0) {
                    // ajouter texte view avec comme texte des lettre de A à H
                } else if (j == 0) {
                    // ajouter texte view avec comme texte des chiffre de 1 à 8
                } else {
                    // Ajouter les bouttons a partir de i = 1 et j = 1
                    final Button bouton = new Button(this.getActivity());

                    bouton.setTag("" + numeroBouton);

                    numeroBouton++;
                }




            }
        }
    }

}
