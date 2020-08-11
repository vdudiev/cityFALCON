package com.tradestock.cityfalcom.Fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.tradestock.cityfalcom.Adapters.AvailableSubscriptionsAdapter;
import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.TradeApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SubscriptionFragment extends Fragment implements PurchasesUpdatedListener {


    RecyclerView recyclerView;

    //billing
    private List skuList =  new ArrayList();
    private SkuDetailsParams.Builder skuDetailsParams;
    BillingClient billingClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_subscription, container, false);
        TextView textView_youll_get = root.findViewById(R.id.textView_youll_get);
     //   textView_youll_get.setText(Html.fromHtml(TradeApp.langs.getSubscription()));
        recyclerView = root.findViewById(R.id.recyclerview_list_of_available_subscriptions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageView iv_back = root.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        //Billing
        billingClient = BillingClient.newBuilder(getActivity()).setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                // И здесь надо реализовать метод :) Без него рабоать не буедт )
                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    getQuerySkuDetailsAsync();
                }

            }

            @Override
            public void onBillingServiceDisconnected(){

            }
        });


        //доступные для покупки продукты


        return root;
    }

    //метод для списка доступных подписок
    private void getQuerySkuDetailsAsync() {
        skuDetailsParams = SkuDetailsParams.newBuilder();
        skuDetailsParams.setSkusList(skuList()).setType(BillingClient.SkuType.SUBS);

        billingClient.querySkuDetailsAsync(skuDetailsParams.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> skuDetailsList) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                        && skuDetailsList != null
                        && billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS).getResponseCode() == BillingClient.BillingResponseCode.OK)
                {

                    AvailableSubscriptionsAdapter adapter = new AvailableSubscriptionsAdapter(skuDetailsList, getActivity());
                    recyclerView.setAdapter(adapter);


                   /*for (SkuDetails skuDetails : skuDetailsList) {
                       String asd =  skuDetailsList.get(1).getPrice();
                   }*/


                }
            }
        });
    }

    public List<String> skuList() {
        return new ArrayList<>(Arrays.asList("trade.1minth", "trade.2mont"));
    }


    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && list != null) {
            for (Purchase purchase : list) {
                handlePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
        } else {
            // Handle any other error codes.
        }

    }

    void handlePurchase(Purchase purchase) {
        // Purchase retrieved from BillingClient#queryPurchases or your PurchasesUpdatedListener.

        // Verify the purchase.
        // Ensure entitlement was not already granted for this purchaseToken.
        // Grant entitlement to the user.

        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // Handle the success of the consume operation.
                }
            }
        };

        billingClient.consumeAsync(consumeParams, listener);
    }
}
