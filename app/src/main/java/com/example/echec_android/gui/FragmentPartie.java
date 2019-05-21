package com.example.echec_android.gui;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class FragmentPartie extends Fragment {

    static Partie m_partie = new Partie();
    /**
     * Les 64 cases du tableau d'échec sont représentées par un array de boutons à 2 dimensions
     */
    Button[][] m_boutons = new Button[8][8];
    Button m_dernierBoutonCliquer;
    Piece.Type promotionType;

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
     *
     * @param p_nomJoueurBlanc le joueur blanc
     * @param p_nomJoueurNoir  le joueur noir
     */
    public static void setJoueurs(String p_nomJoueurBlanc, String p_nomJoueurNoir) {
        m_partie.setJoueurBlanc(new Joueur(Piece.Couleur.BLANC, p_nomJoueurBlanc));
        m_partie.setJoueurNoir(new Joueur(Piece.Couleur.NOIR, p_nomJoueurNoir));
    }

    /**
     * Méthode qui initialise le tableau
     *
     * @param p_table     le tableLayout qui servira pour mettre le tableau
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
                        bouton.setText(" ");
                    }

                    bouton.setOnClickListener(v -> {
                        if (m_dernierBoutonCliquer == null) {
                            m_dernierBoutonCliquer = bouton;

                            reEcrireTableau();

                            LinkedHashMap<String, String> mouvementPossible = m_partie.
                                    getEchiquier().getToursPossibleSelonPiece(m_partie.
                                    getEchiquier().getPiece(coordonnee), coordonnee);

                            int couleurMouvement = Color.GRAY;

                            for (String mouvements : mouvementPossible.values()) {
                                m_boutons[mouvements.charAt(0) - 'a'][mouvements.charAt(1) - '1'].
                                        setBackgroundColor(couleurMouvement);
                            }

                        } else {
                            // Promotion
                            if (m_partie.getEchiquier().estPromotionPossible(m_dernierBoutonCliquer.
                                    getTag().toString(), coordonnee)) {
                                m_partie.jouerTour(m_dernierBoutonCliquer.getTag().toString(), coordonnee);
                                m_partie.getEchiquier().promotion(coordonnee, promotionType);
                                showPromotionDialogueMontrer();
                            } else {

                                boolean valide = m_partie.jouerTour(m_dernierBoutonCliquer.getTag().
                                        toString(), coordonnee);

                                if (!valide) {
                                    Toast.makeText(getContext(),
                                            "Votre coup était invalide veuillez réessayer!",
                                            Toast.LENGTH_SHORT).show();
                                    reEcrireTableau();
                                } else {
                                    if (m_partie.getEchiquier().estEchec(m_partie.getCouleurTour())) {
                                        Toast.makeText(getContext(),
                                                "Echec",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    if (m_partie.getEchiquier().estEchecEtMathNoir()) {
                                        Toast.makeText(getContext(),
                                                "Echec et math! " + m_partie.
                                                        getJoueurBlanc().getNomJoueur() + " gagne!",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    if (m_partie.getEchiquier().estEchecEtMathBlanc()) {
                                        Toast.makeText(getContext(),
                                                "Echec et math! " + m_partie.getJoueurNoir().
                                                        getNomJoueur() + " gagne!",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    Toast.makeText(getContext(),
                                            "Veuillez Confirmer la fin de votre tour " +
                                                    "ou revenez en arrière dans le cas contraire!",
                                            Toast.LENGTH_SHORT).show();

                                    reEcrireTableau();
                                }
                            }
                        }
                    });

                    m_boutons[i - 1][j - 1] = bouton;
                    colorerTableau(i - 1, j - 1);
                    rangee.addView(bouton, largeur, largeur);
                }
            }
        }
    }

    /**
     * Rebatit le tableau soit lechiquier
     */
    void reEcrireTableau() {
        Echiquier echiquier = m_partie.getEchiquier();

        for (int i = 0; i < m_boutons.length; i++) {
            for (int j = 0; j < m_boutons[i].length; j++) {
                if (echiquier.getPiece(String.valueOf((char) ('a' + i)) +
                        (j + 1)) != null) {

                    m_boutons[i][j].setText(echiquier.getPiece(String.valueOf((char) ('a' + i))
                            + (j + 1)).obtenirRepresentation());
                } else {
                    m_boutons[i][j].setText(" ");
                }
                colorerTableau(i, j);
            }
        }
    }

    /**
     * Méthode qui ajoute les couleurs sur les bonnes tuiles
     *
     * @param p_i cases blanches
     * @param p_j cases noires
     */
    void colorerTableau(int p_i, int p_j) {
        int noir = Color.BLACK;
        int blanc = Color.WHITE;

        if (p_i % 2 == 0) {
            if (p_j % 2 == 0) {
                m_boutons[p_i][p_j].setBackgroundColor(blanc);
            } else {
                m_boutons[p_i][p_j].setBackgroundColor(noir);
            }
        } else {
            if (p_j % 2 == 0) {
                m_boutons[p_i][p_j].setBackgroundColor(noir);
            } else {
                m_boutons[p_i][p_j].setBackgroundColor(blanc);
            }
        }
    }

    /**
     * Méthode qui montre le dialogue de promotion
     */
    private void showPromotionDialogueMontrer() {
        final Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_promotion_layout);
        List<String> stringList = asList(Piece.Type.CAVALIER.toString(), Piece.Type.TOUR.toString(),
                Piece.Type.FOU.toString(), Piece.Type.DAME.toString());


        RadioGroup radioGroup = dialog.findViewById(R.id.radio_group);

        for (int i = 0; i < stringList.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(stringList.get(i));
            radioGroup.addView(radioButton);
        }

        Button bouton = dialog.findViewById(R.id.Ok);
        bouton.setOnClickListener(v -> {
            int selectId = radioGroup.getCheckedRadioButtonId();

            RadioButton selectBouton = radioGroup.findViewById(selectId);
            promotionType = Piece.Type.valueOf(selectBouton.getText().toString());
        });

        dialog.show();
    }

    /**
     * Méthode de base oncreate
     *
     * @param savedInstanceState l'état d'instance
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Méthode de base oncreateView
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState l'état d'instance
     * @return la vue (v)
     */
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.partie_fragment_layout, container, false);

        TableLayout tableLayout = v.findViewById(R.id.tableLayout);
        InitialiserTableau(tableLayout, m_partie.getEchiquier());

        TextView tourActuel = v.findViewById(R.id.tour_actuel);
        tourActuel.setText(m_partie.getJoueurActuel().getNomJoueur());

        Button boutonPrecedent = v.findViewById(R.id.precedent);
        boutonPrecedent.setOnClickListener(v1 -> {
            if (m_partie.getHistoriquePartie().size() > 1) {
                m_partie.restaurerDernierMouvement();

                InitialiserTableau(tableLayout, m_partie.getEchiquier());
            } else {
                Toast.makeText(getContext(),
                        "Vous devez avoir jouer un tour pour pouvoir revenir en arrière!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button boutonConfirmer = v.findViewById(R.id.fin);
        boutonConfirmer.setOnClickListener(v1 -> {
            m_partie.changerTour();
            tourActuel.setText("Tour de " + m_partie.getJoueurActuel().getNomJoueur());
        });

        return v;
    }
}



