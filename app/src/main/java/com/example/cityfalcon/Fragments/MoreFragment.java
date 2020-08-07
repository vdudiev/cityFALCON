package com.example.cityfalcon.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.cityfalcon.R;


public class MoreFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);


        View.OnClickListener openOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction =((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();

                switch (v.getId()){
                    case R.id.frameLayout_about_more_fragment:
                        AboutFragment aboutFragment = new AboutFragment();
                        fragmentTransaction.replace(R.id.content_fragment,aboutFragment);
                        fragmentTransaction.addToBackStack(null);
                        break;

                    case R.id.frameLayout_terms_conditions_more_fragment:
                        TermsAndConditionsFragment termsAndConditionsFragment = new TermsAndConditionsFragment();
                        fragmentTransaction.replace(R.id.content_fragment,termsAndConditionsFragment);
                        fragmentTransaction.addToBackStack(null);
                        break;

                    case R.id.frameLayout_subscription_more_fragment:
                        SubscriptionFragment subscriptionFragment = new SubscriptionFragment();
                        fragmentTransaction.replace(R.id.content_fragment, subscriptionFragment);
                        fragmentTransaction.addToBackStack(null);
                        break;
                }
                fragmentTransaction.commit();
            }
        };


        FrameLayout aboutFrameLayout = root.findViewById(R.id.frameLayout_about_more_fragment);
        aboutFrameLayout.setOnClickListener(openOnClickListener);

        FrameLayout termsCondFrameLayout = root.findViewById(R.id.frameLayout_terms_conditions_more_fragment);
        termsCondFrameLayout.setOnClickListener(openOnClickListener);

        FrameLayout subsciptionFrameLayout = root.findViewById(R.id.frameLayout_subscription_more_fragment);
        subsciptionFrameLayout.setOnClickListener(openOnClickListener);
        return root;
    }

}
