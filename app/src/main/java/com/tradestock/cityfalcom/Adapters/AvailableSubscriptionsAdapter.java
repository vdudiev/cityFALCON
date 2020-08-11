package com.tradestock.cityfalcom.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.SkuDetails;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.R;

import java.util.List;

public class AvailableSubscriptionsAdapter extends RecyclerView.Adapter<AvailableSubscriptionsAdapter.ViewHolder> {

    LayoutInflater inflater;
    private List<SkuDetails> list;
    private Context cont;
    private RegistrationResponse registrationResponse = new RegistrationResponse();


    public AvailableSubscriptionsAdapter(List<SkuDetails> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        cont = context;
    }



    @NonNull
    @Override
    public AvailableSubscriptionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_available_subscriptions,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AvailableSubscriptionsAdapter.ViewHolder holder, int position) {
       SkuDetails currentArticleData = list.get(position);
       holder.period.setText(currentArticleData.getSubscriptionPeriod());
       holder.cost.setText(currentArticleData.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView period;
        final TextView cost;
        private String instrumentId;



        ViewHolder(View view){
            super(view);

            period = view.findViewById(R.id.textView_period_of_subscription);
            cost = view.findViewById(R.id.textView_cost_of_subscription);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public  void onClick(View v) {

                }
            });
        }
    }



}
