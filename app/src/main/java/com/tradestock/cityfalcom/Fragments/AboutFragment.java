package com.tradestock.cityfalcom.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.tradestock.cityfalcom.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        ImageButton backToMoreImageButton = root.findViewById(R.id.image_button_back_to_more_frag_from_about);
        backToMoreImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransactionMore =((FragmentActivity) Objects.requireNonNull(getContext())).getSupportFragmentManager().beginTransaction();
                MoreFragment moreFragment = new MoreFragment();
                fragmentTransactionMore.replace(R.id.content_fragment,moreFragment);
                fragmentTransactionMore.commit();
            }
        });
        return root;
    }

}
