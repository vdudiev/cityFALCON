package com.example.cityfalcon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TermsConditionsBottomSheet extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_terms_conditions_bottom_sheet, container, false);
        Button agreeButton = root.findViewById(R.id.button_i_agree_bottom_sheet);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                LiveTradingResultsBottomSheet liveTradingResultsBottomSheet  = new LiveTradingResultsBottomSheet();
                liveTradingResultsBottomSheet.show(getFragmentManager(),"liveTradingResultsBottomSheet");

            }
        });

        TextView idontagreeTextView = root.findViewById(R.id.texView_i_dont_agree_bottom_sheet);
        idontagreeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                LiveTradingResultsBottomSheet liveTradingResultsBottomSheet  = new LiveTradingResultsBottomSheet();
                liveTradingResultsBottomSheet.show(getFragmentManager(),"liveTradingResultsBottomSheet");

            }
        });
        return root;
    }
}
