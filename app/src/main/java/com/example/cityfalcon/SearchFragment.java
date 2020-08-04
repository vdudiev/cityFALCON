package com.example.cityfalcon;


import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {


    private  String instrument;
    private String search = "";
    private Context context;

    private ArrayList<Button> scrollSearchButtons = new ArrayList<>();

    public SearchFragment() {
    }

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

        context = getActivity();

        RegistrationResponse registrationResponse = new RegistrationResponse();

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_signals_on_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.button_all__scroll_search_fragment:
                    searchScrollSelector(scrollSearchButtons.get(0).getText().toString());
                    instrument = "";
                    break;
                case R.id.button_eu_stocks_scroll_search_fragment:
                    instrument = scrollSearchButtons.get(1).getText().toString();
                    searchScrollSelector(instrument);
                    break;
                case R.id.button_us_stocks_scroll_search_fragment:
                    instrument = scrollSearchButtons.get(2).getText().toString();
                    searchScrollSelector(instrument);
                    break;
                case R.id.button_indices_scroll_search_fragment:
                    instrument = scrollSearchButtons.get(3).getText().toString();
                    searchScrollSelector(instrument);
                    break;
                case R.id.button_forex_scroll_search_fragment:
                    instrument = scrollSearchButtons.get(4).getText().toString();
                    searchScrollSelector(instrument);
                    break;
                case R.id.button_cryptos_scroll_search_fragment:
                    instrument = scrollSearchButtons.get(5).getText().toString();
                    searchScrollSelector(instrument);
                    break;
                case R.id.button_etf_s_scroll_search_fragment:
                    instrument = scrollSearchButtons.get(6).getText().toString();
                    searchScrollSelector(instrument);
                    break;
            }


            //вызвать сигналы
            RetrofitCreate.getRetrofit().GetSignalsFromSearch(registrationResponse.getAccept(),
                    registrationResponse.getAuthorization(),
                    search,
                    instrument).enqueue(new Callback<SignalsBuySellArticle>() {
                @Override
                public void onResponse(Call<SignalsBuySellArticle> call, Response<SignalsBuySellArticle> response) {
                    SignalsFromSearchAdapter adapter = new SignalsFromSearchAdapter(response.body().getList(), context);
                    adapter.setSellBuyChek(1);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<SignalsBuySellArticle> call, Throwable t) {

                }
            });

        };

        for (int i=0; i<scrollSearchButtons.size(); i++) {
            scrollSearchButtons.get(i).setOnClickListener(onClickListener);
        }

        return root;
    }



    private void searchScrollSelector (String text){
        for (int i=0; i<scrollSearchButtons.size(); i++) {
            if (text.equals(scrollSearchButtons.get(i).getText().toString())){
                scrollSearchButtons.get(i).setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.outline_background_selected_search_fragment));
                scrollSearchButtons.get(i).setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
            }
            else {
                scrollSearchButtons.get(i).setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorBlack));
                scrollSearchButtons.get(i).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.outline_background_search_fragment));
            }
        }
    }
}