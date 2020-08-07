package com.example.cityfalcon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FiltersForSignlsBottomSheet extends BottomSheetDialogFragment {

    private ArrayList<TextView> filterTextViewList = new ArrayList<>();
    private ArrayList<ImageView> filterImageViewList = new ArrayList<>();

    private String filtersId;

    private RegistrationResponse registrationResponse = new RegistrationResponse();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottom_sheet_filters_for_signals, container, false);


        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_0), root.findViewById(R.id.image_filters_0));
        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_1), root.findViewById(R.id.image_filters_1));
        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_2), root.findViewById(R.id.image_filters_2));
        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_3), root.findViewById(R.id.image_filters_3));
        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_4), root.findViewById(R.id.image_filters_4));
        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_5), root.findViewById(R.id.image_filters_5));
        AddButtonsAndImagesInLists(root.findViewById(R.id.textView_filters_6), root.findViewById(R.id.image_filters_6));




        View.OnClickListener onClickListenerForFilters = new View.OnClickListener() {
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
        };

        //проверка
        filterImageViewList.get(1).setOnClickListener(onClickListenerForFilters);

        return root;
    }

    //получить id инструмента
    private Integer instrumentId;
    private Integer GetInstrumentId (String filterOrInstrumentTitle){
        RetrofitCreate.getRetrofit().GetInstrumentId(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                filterOrInstrumentTitle).enqueue(new Callback<InstrumentArticle>() {
            @Override
            public void onResponse(Call<InstrumentArticle> call, Response<InstrumentArticle> response) {
                instrumentId = response.body().getInstrument_id();
            }

            @Override
            public void onFailure(Call<InstrumentArticle> call, Throwable t) {

            }
        });

        return  instrumentId;
    }

    private void AddButtonsAndImagesInLists(TextView textView, ImageView imageView){
        filterTextViewList.add(textView);
        filterImageViewList.add(imageView);
    }

}
