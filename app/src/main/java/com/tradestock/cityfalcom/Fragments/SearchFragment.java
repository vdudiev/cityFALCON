package com.tradestock.cityfalcom.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.tradestock.cityfalcom.Adapters.SignalFromBuySellArticleAdapter;
import com.tradestock.cityfalcom.Models.InstrumentsForFilters;
import com.tradestock.cityfalcom.Models.SignalsArticle;
import com.tradestock.cityfalcom.Models.SignalsBuySellArticle;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.TradeApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {


    private  String instrument;
    private String search = "";
    private RecyclerView recyclerView;
    SignalFromBuySellArticleAdapter adapter;
    List<SignalsBuySellArticle> items = new ArrayList<>();
    Integer instrument_id = -1;
    EditText etSearch;
    SwipeRefreshLayout srl;

    private ArrayList<Button> scrollSearchButtons = new ArrayList<>();

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        srl = root.findViewById(R.id.srl);
        recyclerView = root.findViewById(R.id.recyclerview_signals_on_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        etSearch = root.findViewById(R.id.et_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    search = v.getText().toString();
                    v.clearFocus();
                    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    updateFromServer();
                    return true;
                }
                return false;
            }
        });

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateFromServer();
            }
        });

        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.removeAllTabs();

        TabLayout.Tab tab_all = tabs.newTab();
        tab_all.setText("All");
        tabs.addTab(tab_all);

        for (InstrumentsForFilters inst : TradeApp.instruments) {
            TabLayout.Tab tab = tabs.newTab();
            tab.setText(inst.getTitle());
            tab.setTag(inst);
            tabs.addTab(tab);
        }

        for(int i=0; i < tabs.getTabCount(); i++) {
            View tab = ((ViewGroup) tabs.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, (int) getResources().getDimension(R.dimen.tabs_spacing), 0);
            tab.requestLayout();
        }
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag()!=null){
                    instrument_id = ((InstrumentsForFilters) tab.getTag()).getId();
                } else {
                    instrument_id = -1;
                }
                adapter.setItems(getFiltered());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        updateFromServer();
        return root;
    }

    public void updateFromServer(){
        srl.setRefreshing(true);
        RetrofitCreate.getRetrofit().GetSignalsBuySell(RegistrationResponse.getAccept(),
                RegistrationResponse.getAuthorization(),
                "",
                RegistrationResponse.getLang(),
                search).enqueue(new Callback<SignalsArticle>() {
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                items = new ArrayList<>();
                items.addAll(response.body().getBuy());
                items.addAll(response.body().getSell());
                Collections.sort(items);
                adapter = new SignalFromBuySellArticleAdapter(getFiltered(),getContext());
                recyclerView.setAdapter(adapter);
                srl.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<SignalsBuySellArticle>getFiltered(){

        ArrayList<SignalsBuySellArticle> res = new ArrayList<>();
        for (SignalsBuySellArticle item: items){
            if(item.getInstrument_id().equals(instrument_id)||instrument_id==-1){
                res.add(item);
            }
        }
        return res;
    }

}