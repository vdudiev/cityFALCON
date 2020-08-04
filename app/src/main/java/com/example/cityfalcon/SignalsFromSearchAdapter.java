package com.example.cityfalcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;

public class SignalsFromSearchAdapter extends RecyclerView.Adapter<SignalsFromSearchAdapter.ViewHolder> {

    LayoutInflater inflater;
    private List<SignalFromSignalsBuySellArticle> list;
    private Context cont;
    private RegistrationResponse registrationResponse = new RegistrationResponse();

    SignalsFromSearchAdapter(List<SignalFromSignalsBuySellArticle> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        cont = context;
    }

    @NonNull
    @Override
    public SignalsFromSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_signals_onsignals,parent,false);

        return new ViewHolder(view);
    }

    //signal_id_to_get for SignalDetailsFragment

    //signalSell or signalBuy
    private Integer SellBuyChek;
    public Integer getSellBuyChek() {
        return SellBuyChek;
    }
    public void setSellBuyChek(Integer sellBuyChek) {
        SellBuyChek = sellBuyChek;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull SignalsFromSearchAdapter.ViewHolder holder, int position) {
        SignalFromSignalsBuySellArticle currentArticleData = list.get(position);
       holder.symbol.setText(currentArticleData.getSymbol());
       holder.date_time.setText(currentArticleData.getDate_time());
       holder.price.setText(currentArticleData.getPrice().toString());
       holder.current_price.setText(currentArticleData.getCurrent_price().toString()+"%");
        if (currentArticleData.getCurrent_price()> 0){ holder.current_price.setTextColor(R.color.colorLoss); }
        else {holder.current_price.setTextColor(R.color.colorProfit);}
        holder.signal_id = currentArticleData.getId();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private Float signal_id;
        final TextView symbol;
        final TextView date_time;
        final TextView price;
        final TextView current_price;
        final ImageButton image_button_check;


        ViewHolder(View view){
            super(view);

            //more information about signal


            symbol = view.findViewById(R.id.textView_signal_symbol_on_signals);
            date_time =  view.findViewById(R.id.textView_signal_date_time_on_signals);
            price = view.findViewById(R.id.textView_signal_price_on_signals);
            current_price = view.findViewById(R.id.textView_signal_current_price_on_signals);
            image_button_check = view.findViewById(R.id.image_button_signals_watchlist_add_checked);

            image_button_check.setOnClickListener(v -> {
                RetrofitCreate.getRetrofit().addSignalToWatchList(registrationResponse.getAccept(),registrationResponse.getAuthorization(), signal_id);
            });

            view.setOnClickListener(v -> {
                FragmentTransaction fragmentTransaction = ((FragmentActivity) cont).getSupportFragmentManager().beginTransaction();
                SignalDetails signalDetails = new SignalDetails();
                signalDetails.setSignal_id_to_set(signal_id);
                signalDetails.setSellBuyCheking(getSellBuyChek());
                fragmentTransaction.replace(R.id.content_fragment, signalDetails);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            });
        }
    }



}
