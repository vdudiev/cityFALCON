package com.tradestock.cityfalcom.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.tradestock.cityfalcom.Adapters.SignalFromBuySellArticleAdapter;
import com.tradestock.cityfalcom.Models.SignalsArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignalsBySectorFragment extends Fragment {

    public static String SECTOR_ID="SECTOR_ID";
    public static String SECTOR_NAME="SECTOR_NAME";



    private RegistrationResponse registrationResponse = new RegistrationResponse();
    private RecyclerView recyclerView;
    private Integer signalsCount;
    private TextView textViewSignalCount, tvSellBuy, tvHeader;
    private String filters = "";
    private SwipeRefreshLayout srl;
    private int shownTab=0;
    private LinearLayout sellLinearLayout;
    private LinearLayout buyLinearLayout;
    private String search="";
    private EditText etSearch;
    private Integer sectorID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signal, container, false);
        tvHeader = root.findViewById(R.id.tv_header);
        if (getArguments()!=null){
            if(getArguments().getInt(SECTOR_ID, -1) != -1 ){
                sectorID = getArguments().getInt(SECTOR_ID);
            }
            if(getArguments().getString(SECTOR_NAME, null)!=null){
                tvHeader.setText(getArguments().getString(SECTOR_NAME));
            }
        }

        Button buttonOnSignalFragmentToGetFilters = root.findViewById(R.id.button_on_signal_fragment_to_get_filters);
        buttonOnSignalFragmentToGetFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltersForSignlsBottomSheet filtersForSignlsBottomSheet = new FiltersForSignlsBottomSheet();
                filtersForSignlsBottomSheet.show(getFragmentManager(),"filtersForSignlsBottomSheet");
            }
        });

        buttonOnSignalFragmentToGetFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltersForSignlsBottomSheet filtersForSignlsBottomSheet = new FiltersForSignlsBottomSheet();
                filtersForSignlsBottomSheet.setOnFilterListener(new FiltersForSignlsBottomSheet.OnFilterListener() {
                    @Override
                    public void onFilter(String filters) {
                        SignalsBySectorFragment.this.filters = filters;
                        if (shownTab == 0){
                            sellLinearLayout.callOnClick();
                        }
                        else {
                            buyLinearLayout.callOnClick();
                        }
                    }
                });
                filtersForSignlsBottomSheet.show(getFragmentManager(),"filtersForSignlsBottomSheet");
            }
        });


        srl = root.findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(shownTab==0){
                    sellLinearLayout.callOnClick();
                } else {
                    buyLinearLayout.callOnClick();
                }
            }
        });
        recyclerView = root.findViewById(R.id.recyclerview_signals_on_signals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Context context = getActivity();
        tvSellBuy = root.findViewById(R.id.tv_sell_buy);
        etSearch = root.findViewById(R.id.et_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    search = v.getText().toString();
                    v.clearFocus();
                    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    if(shownTab==0){
                        sellLinearLayout.callOnClick();
                    } else {
                        buyLinearLayout.callOnClick();
                    }
                    return true;
                }
                return false;
            }
        });

        sellLinearLayout = root.findViewById(R.id.LinearLayout_sell_signal_fragment);
        buyLinearLayout = root.findViewById(R.id.LinearLayout_buy_signal_fragment);


        //first sell signals
        signalsCount = 0;
        RetrofitCreate.getRetrofit().GetSectorsByID(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                sectorID).enqueue(new Callback<SignalsArticle>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);
                signalsCount += adapter.getItemCount();
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
                signalsCount += adapter.getItemCount();
                textViewSignalCount.setText(signalsCount.toString());

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });

        textViewSignalCount = root.findViewById(R.id.textview_number_of_short_terms_signal_fragment);

        sellLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srl.setRefreshing(true);
                shownTab = 0;
                tvSellBuy.setText(R.string.sell_price);
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                RetrofitCreate.getRetrofit().GetSectorsByID(registrationResponse.getAccept(),
                        registrationResponse.getAuthorization(),
                        sectorID).enqueue(new Callback<SignalsArticle>() {
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);

                        recyclerView.setAdapter(adapter);
                        srl.setRefreshing(false);

                    }

                    @Override
                    public void onFailure(Call<SignalsArticle> call, Throwable t) {
                        srl.setRefreshing(false);
                    }
                });
            }
        });

        buyLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srl.setRefreshing(true);
                tvSellBuy.setText(R.string.buy_price);
                shownTab = 1;
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                RetrofitCreate.getRetrofit().GetSectorsByID(registrationResponse.getAccept(),
                        registrationResponse.getAuthorization(),
                        sectorID).enqueue(new Callback<SignalsArticle>() {
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);

                        recyclerView.setAdapter(adapter);
                        srl.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<SignalsArticle> call, Throwable t) {
                        srl.setRefreshing(false);
                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(shownTab==0){
            sellLinearLayout.callOnClick();
        } else {
            buyLinearLayout.callOnClick();
        }
    }
}
