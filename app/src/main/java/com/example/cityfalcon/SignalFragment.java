package com.example.cityfalcon;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignalFragment extends Fragment {


    public SignalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signal, container, false);
     //   RecyclerView sellBuyRecyclerView = root.findViewById(R.id.sell_buy_recyclerView);


        LinearLayout sellLinearLayout = root.findViewById(R.id.LinearLayout_sell_signal_fragment);
        LinearLayout buyLinearLayout = root.findViewById(R.id.LinearLayout_buy_signal_fragment);


        sellLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorWhite));
            }
        });

        buyLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                sellLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorWhite));
            }
        });

        return root;
    }

}
