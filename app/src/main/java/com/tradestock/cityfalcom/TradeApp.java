package com.tradestock.cityfalcom;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tradestock.cityfalcom.Models.InstrumentArticle;
import com.tradestock.cityfalcom.Models.InstrumentsForFilters;
import com.tradestock.cityfalcom.Models.Lang;
import com.tradestock.cityfalcom.Models.Languages;

import java.util.ArrayList;
import java.util.List;

public class TradeApp extends Application {

    public static List<InstrumentsForFilters> instruments = new ArrayList<>();
    public static Lang langs;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("fbinstance", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                    }
                });

    }
}
