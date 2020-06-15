package com.example.cityfalcon;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionFragment extends Fragment {


    public SubscriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_subscription, container, false);

        ImageButton backToMoreImageButton = root.findViewById(R.id.image_button_back_to_more_frag_from_subscription);
        backToMoreImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransactionSub =((FragmentActivity) Objects.requireNonNull(getContext())).getSupportFragmentManager().beginTransaction();
                MoreFragment moreFragment = new MoreFragment();
                fragmentTransactionSub.replace(R.id.content_fragment,moreFragment);
                fragmentTransactionSub.commit();
            }
        });
        return root;
    }

}
