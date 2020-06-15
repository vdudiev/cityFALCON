package com.example.cityfalcon;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);

       /* FrameLayout aboutFrameLayout = root.findViewById(R.id.frameLayout_about_more_fragment);
        aboutFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransactionAbout =((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                AboutFragment aboutFragment = new AboutFragment();
                fragmentTransactionAbout.replace(R.id.content_fragment,aboutFragment);
                fragmentTransactionAbout.addToBackStack(null);
                fragmentTransactionAbout.commit();

            }
        });

        FrameLayout termsCondFrameLayout = root.findViewById(R.id.frameLayout_terms_conditions_more_fragment);
        termsCondFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransactionTermsCond =((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                TermsAndConditionsFragment termsAndConditionsFragment = new TermsAndConditionsFragment();
                fragmentTransactionTermsCond.replace(R.id.content_fragment,termsAndConditionsFragment);
                fragmentTransactionTermsCond.addToBackStack(null);
                fragmentTransactionTermsCond.commit();

            }
        });*/
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
