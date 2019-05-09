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
        int numeroBouton = 0;

        for (int i = 0; i < 8; i++) {
            TableRow rangee = new TableRow(this.getActivity());
            p_table.addView(rangee);

            for (int j = 0; j < 8; j++) {
                final Button bouton = new Button(this.getActivity());

                bouton.setTag("" + numeroBouton);


            }
        }
        numeroBouton++;
    }

}
