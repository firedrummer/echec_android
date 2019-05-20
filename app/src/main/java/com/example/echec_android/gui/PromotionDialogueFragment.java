package com.example.echec_android.gui;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PromotionDialogueFragment extends DialogFragment {
    public static final String EXTRA_NOM = "com.cstjean.1738253";
    public static final String EXTRA_DESCRIPTION = "com.cstjean.173825312";
    private static final String ARG_NOM_ID = "nom_id";
    private static final String ARG_DESCRIPTION_ID = "descritpion_id";
    /**
     * EditText pour le nom de l'endroit
     */
    private EditText mNom;
    /**
     * EditText pour la description de l'endroit
     */
    private EditText mDescription;

    /**
     * Crée une nouvelle instance d'AjoutDialogueFragment à partir d'un id.
     *
     * @return retourne un AjoutDialogueFragment
     * @author Yanick Bellavance et Olivier Chan
     */
    public static PromotionDialogueFragment newInstance(String nom, String description) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOM_ID, nom);
        args.putSerializable(ARG_DESCRIPTION_ID, description);

        return new PromotionDialogueFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialogue_ajout_layout, null);

        mNom = v.findViewById(R.id.nom_endroit);
        mDescription = v.findViewById(R.id.description_endroit);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle("Ajouter un nouvel endroit")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String nom = mNom.getText().toString();
                        String description = mDescription.getText().toString();

                        sendResult(Activity.RESULT_OK, nom, description);

                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                }).create();

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int x = 0; x < childCount; x++) {
                    RadioButton btn = (RadioButton) group.getChildAt(x);
                    if (btn.getId() == checkedId) {
                        Log.e("selected RadioButton->", btn.getText().toString());

                    }
                }
            }
        });
    }

    /**
     * Envoit le résultat de la boite de dialogue.
     *
     * @param resultCode  Résultat du dialogue, dépendant si l'utilisateur donne un réponde positive
     *                    ou négative (OK ou Annuler)
     * @param nom         Nom de l'endroit
     * @param description description de l'endroit
     * @author Yanick Bellavance et Olivier Chan
     */
    private void sendResult(int resultCode, String nom, String description) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString(EXTRA_NOM, nom);
        extras.putString(EXTRA_DESCRIPTION, description);
        intent.putExtras(extras);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}