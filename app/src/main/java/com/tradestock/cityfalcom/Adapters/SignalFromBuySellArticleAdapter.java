package com.tradestock.cityfalcom.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.tradestock.cityfalcom.Fragments.SignalDetails;
import com.tradestock.cityfalcom.Models.AddedAndDeletedSignalIdArticle;
import com.tradestock.cityfalcom.Models.SignalsBuySellArticle;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.tradestock.cityfalcom.R;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignalFromBuySellArticleAdapter extends RecyclerView.Adapter<SignalFromBuySellArticleAdapter.ViewHolder> {
    LayoutInflater inflater;

    private List<SignalsBuySellArticle> list;
    private Context cont;
    private RegistrationResponse registrationResponse = new RegistrationResponse();

    public SignalFromBuySellArticleAdapter(List<SignalsBuySellArticle> list, Context context) {
        this.list = list;
        Collections.sort(this.list);
        this.inflater = LayoutInflater.from(context);
        cont = context;
    }

    @NonNull
    @Override
    public SignalFromBuySellArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_signals_on_signals,parent,false);

        return new ViewHolder(view);
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SignalsBuySellArticle currentArticleData = list.get(position);
        holder.symbol.setText(currentArticleData.getSymbol());
        holder.price.setText(currentArticleData.getPrice().toString());
        holder.signal_id = currentArticleData.getId();
        Date date = new Date(System.currentTimeMillis()-24*60*60*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        holder.date_time.setText(sdf.format(date));
        holder.current_price.setText(currentArticleData.getCurrent_price().toString());
        Float perDif =  0.0f;



        perDif = 100 - ((currentArticleData.getPrice()/currentArticleData.getCurrent_price())*100);
        if(currentArticleData.getOrder().equals("buy")) {
            if (currentArticleData.getPrice() > currentArticleData.getCurrent_price()) {
                perDif = 100 - ((currentArticleData.getPrice() / currentArticleData.getCurrent_price()) * 100);
            } else {
                perDif = Math.abs(100 - ((currentArticleData.getCurrent_price() / currentArticleData.getPrice()) * 100));
            }
        } else {
            if (currentArticleData.getPrice() > currentArticleData.getCurrent_price()) {
                perDif = Math.abs(100 - ((currentArticleData.getPrice() / currentArticleData.getCurrent_price()) * 100));
            } else {
                perDif = 100 - ((currentArticleData.getCurrent_price() / currentArticleData.getPrice()) * 100);
            }
        }
        if (perDif > 0 ) {
            holder.percentage_difference.setTextColor(cont.getResources().getColor(R.color.colorProfit));
            holder.percentage_difference.setText("+" + String.format("%.2f",perDif)+"%");
        }
        else {
            holder.percentage_difference.setTextColor(cont.getResources().getColor(R.color.colorLoss));
            holder.percentage_difference.setText(String.format("%.2f",perDif)+"%");
        }


        //проверка сигнала на принадлежность к watchlist
        if (currentArticleData.getWatchlist() == 1)
            holder.image_button_check.setImageResource(R.drawable.ic_ok);
        else
            holder.image_button_check.setImageResource(R.drawable.ic_plus);

        holder.watchlisthek = currentArticleData.getWatchlist();
        holder.signal_to_set_from_adapter = currentArticleData;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private Integer watchlisthek;
        private Integer signal_id;
        final TextView symbol;
        final TextView date_time;
        final TextView price;
        final TextView current_price;
        final TextView percentage_difference;
        final ImageButton image_button_check;
        private SignalsBuySellArticle signal_to_set_from_adapter;


        ViewHolder(View view){
            super(view);
            //more information about signal


            symbol = view.findViewById(R.id.textView_signal_symbol_on_signals);
            date_time =  view.findViewById(R.id.textView_signal_date_time_on_signals);
            price = view.findViewById(R.id.textView_signal_price_on_signals);
            current_price = view.findViewById(R.id.textView_signal_current_price_on_signals);
            percentage_difference = view.findViewById(R.id.textView_signal_percentage_difference_on_signals);

            //проверка watchlist без обновление страницы


            image_button_check = view.findViewById(R.id.image_button_signals_watchlist_add_checked);
            image_button_check.setOnClickListener(v -> {

                if(watchlisthek==0) {
                    watchlisthek = 1;
                    RetrofitCreate.getRetrofit().addSignalToWatchList(registrationResponse.getAccept(),
                            registrationResponse.getAuthorization(),
                            signal_id).enqueue(new Callback<AddedAndDeletedSignalIdArticle>() {
                        @Override
                        public void onResponse(Call<AddedAndDeletedSignalIdArticle> call, Response<AddedAndDeletedSignalIdArticle> response) {
                            image_button_check.setImageResource(R.drawable.ic_ok);
                        }

                        @Override
                        public void onFailure(Call<AddedAndDeletedSignalIdArticle> call, Throwable t) {
                            Toast.makeText(cont, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            image_button_check.setImageResource(R.drawable.ic_plus);
                        }
                    });

                }
                else {
                    watchlisthek = 0;
                    RetrofitCreate.getRetrofit().DeleteSignalFromWatchList(registrationResponse.getAccept(),
                            registrationResponse.getAuthorization(),
                            signal_id).enqueue(new Callback<AddedAndDeletedSignalIdArticle>() {
                        @Override
                        public void onResponse(Call<AddedAndDeletedSignalIdArticle> call, Response<AddedAndDeletedSignalIdArticle> response) {
                            image_button_check.setImageResource(R.drawable.ic_plus);
                        }

                        @Override
                        public void onFailure(Call<AddedAndDeletedSignalIdArticle> call, Throwable t) {
                            Toast.makeText(cont, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            image_button_check.setImageResource(R.drawable.ic_ok);
                        }
                    });

                }
            });

            view.setOnClickListener(v -> {
                FragmentTransaction fragmentTransaction = ((FragmentActivity) cont).getSupportFragmentManager().beginTransaction();
                SignalDetails signalDetails = new SignalDetails();
                signalDetails.setSignal_to_set(signal_to_set_from_adapter);

                fragmentTransaction.add(R.id.content_fragment, signalDetails);
                fragmentTransaction.addToBackStack("SignalDetails");
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();

            });
        }
    }
    public void  setItems(List<SignalsBuySellArticle> newItems){
        list = newItems;
        Collections.sort(list);
        notifyDataSetChanged();
    }

}
