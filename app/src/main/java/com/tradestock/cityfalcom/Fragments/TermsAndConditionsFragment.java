package com.tradestock.cityfalcom.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.TradeApp;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsAndConditionsFragment extends Fragment {


    public TermsAndConditionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false);

        ImageButton backToMoreImageButton = root.findViewById(R.id.image_button_back_to_more_frag_from_termsCond);
        backToMoreImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        TextView tv_full_terms = root.findViewById(R.id.tv_full_terms);
        tv_full_terms.setText(Html.fromHtml(TradeApp.langs.getTermsConditions()));

        return root;
    }

}
