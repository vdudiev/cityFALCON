package com.example.cityfalcon;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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



    ArrayList<Button> scrollSearchButtons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        scrollSearchButtons.add(root.findViewById(R.id.button_all__scroll_search_fragment));
        scrollSearchButtons.add(root.findViewById(R.id.button_eu_stocks_scroll_search_fragment));
        scrollSearchButtons.add(root.findViewById(R.id.button_us_stocks_scroll_search_fragment));
        scrollSearchButtons.add(root.findViewById(R.id.button_indices_scroll_search_fragment));
        scrollSearchButtons.add(root.findViewById(R.id.button_forex_scroll_search_fragment));
        scrollSearchButtons.add(root.findViewById(R.id.button_cryptos_scroll_search_fragment));
        scrollSearchButtons.add(root.findViewById(R.id.button_etf_s_scroll_search_fragment));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case  R.id.button_all__scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(0).getText().toString());
                        break;
                    case  R.id.button_eu_stocks_scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(1).getText().toString());
                        break;
                    case  R.id.button_us_stocks_scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(2).getText().toString());
                        break;
                    case  R.id.button_indices_scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(3).getText().toString());
                        break;
                    case  R.id.button_forex_scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(4).getText().toString());
                        break;
                    case  R.id.button_cryptos_scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(5).getText().toString());
                        break;
                    case  R.id.button_etf_s_scroll_search_fragment:
                        searchScrollSelector(scrollSearchButtons.get(6).getText().toString());
                        break;
                }

            }
        };

        for (int i=0; i<scrollSearchButtons.size(); i++) {
            scrollSearchButtons.get(i).setOnClickListener(onClickListener);
        }

        return root;
    }

    private void searchScrollSelector (String text){
        for (int i=0; i<scrollSearchButtons.size(); i++) {
            if (text.equals(scrollSearchButtons.get(i).getText().toString())){
                scrollSearchButtons.get(i).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.outline_background_selected_search_fragment));
                scrollSearchButtons.get(i).setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
            }
            else {
                scrollSearchButtons.get(i).setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
                scrollSearchButtons.get(i).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.outline_background_search_fragment));
            }

            }
        }
    }

