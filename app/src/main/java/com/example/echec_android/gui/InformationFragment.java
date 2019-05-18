package com.example.echec_android.gui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echec_android.R;

public class InformationFragment extends Fragment {

    private FragmentPartie.CallBacks mode;

    /**
     * Construit une nouvelle instance de InformationFragment
     *
     * @return retourne une nouvelle instance d'InformationFragment
     */
    public static InformationFragment newInstance() {

        Bundle args = new Bundle();

        InformationFragment fragment = new InformationFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.information_layout, container, false);

        return v;
    }
}
