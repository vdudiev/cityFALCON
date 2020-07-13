package com.example.cityfalcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SignalFromBuySellArticleAdapter extends RecyclerView.Adapter<SignalFromBuySellArticleAdapter.ViewHolder> {

    LayoutInflater inflater;
    private List<SignalFromSignalsBuySellArticle> list;
    private String titleDetailsToSet;
    private String textDetailsToSet;
    private String imageDetailsToSet;
    private Context cont;

    SignalFromBuySellArticleAdapter(List<SignalFromSignalsBuySellArticle> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        cont = context;
    }

    @NonNull
    @Override
    public SignalFromBuySellArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_signals_onsignals,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override

    public void onBindViewHolder(@NonNull SignalFromBuySellArticleAdapter.ViewHolder holder, int position) {
        SignalFromSignalsBuySellArticle currentArticleData = list.get(position);
       holder.symbol.setText(currentArticleData.getSymbol());
       holder.date_time.setText(currentArticleData.getDate_time());
       holder.price.setText(currentArticleData.getPrice().toString());
       holder.current_price.setText(currentArticleData.getCurrent_price().toString());
        if (currentArticleData.getCurrent_price()> 1){ holder.current_price.setTextColor(R.color.colorLoss); }
        else {holder.current_price.setTextColor(R.color.colorProfit);}



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView symbol;
        final TextView date_time;
        final TextView price;
        final TextView current_price;
        ViewHolder(View view){
            super(view);


            symbol = view.findViewById(R.id.textView_signal_symbol_on_signals);
            date_time =  view.findViewById(R.id.textView_signal_date_time_on_signals);
            price = view.findViewById(R.id.textView_signal_price_on_signals);
            current_price = view.findViewById(R.id.textView_signal_current_price_on_signals);
        }
    }

}
