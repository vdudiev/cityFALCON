package com.tradestock.cityfalcom.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tradestock.cityfalcom.Adapters.FiltersAdapter;
import com.tradestock.cityfalcom.Models.FiltersArticle;
import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltersForSignlsBottomSheet extends BottomSheetDialogFragment {


    private String filtersId;

    private RegistrationResponse registrationResponse = new RegistrationResponse();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottom_sheet_filters_for_signals, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_instruments_on_filter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RetrofitCreate.getRetrofit().GetInstrumentsForFilters(registrationResponse.getAccept(),
                registrationResponse.getAuthorization()).enqueue(new Callback<FiltersArticle>() {
            @Override
            public void onResponse(Call<FiltersArticle> call, Response<FiltersArticle> response) {
                FiltersAdapter  adapter;
            }

            @Override
            public void onFailure(Call<FiltersArticle> call, Throwable t) {

            }
        });





       /* View.OnClickListener onClickListenerForFilters = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.image_filters_0:
                        filtersId = "";
                        break;
                    case R.id.image_filters_1:
                        String idToString = GetInstrumentId(filterTextViewList.get(1).getText().toString()).toString();
                        if (filtersId.contains(idToString)){
                            filtersId.replaceAll(idToString+ " ", "");
                            filterImageViewList.get(1).setImageResource(R.drawable.ic_filter_not_checked);
                        }
                        else {
                            filtersId += idToString + " ";
                            filterImageViewList.get(1).setImageResource(R.drawable.ic_filter_checked);
                        }

                }
            }
        };*/


        return root;
    }





}
