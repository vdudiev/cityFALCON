package com.example.cityfalcon;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;


public class SubscriptionFragment extends Fragment {

    Activity activity = getActivity();
    //billing
    //private List skuList =  new ArrayList();
    private SkuDetailsParams.Builder skuDetailsParams;
    BillingClient billingClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_subscription, container, false);

        //Billing

/*        billingClient = BillingClient.newBuilder(activity).setListener((PurchasesUpdatedListener) this).build();
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







        ImageButton backToMoreImageButton = root.findViewById(R.id.image_button_back_to_more_frag_from_subscription);
        backToMoreImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransactionSub =((FragmentActivity) Objects.requireNonNull(getContext())).getSupportFragmentManager().beginTransaction();
                MoreFragment moreFragment = new MoreFragment();
                fragmentTransactionSub.replace(R.id.content_fragment,moreFragment);
                fragmentTransactionSub.commit();
            }
        });
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
                    for (SkuDetails skuDetails : skuDetailsList){

                    }
                }
            }
        });
    }

    public List<String> skuList()
        return new ArrayList<>(Arrays.asList("trade.1minth","trade.2mont"));*/
        return root;
    }

}
