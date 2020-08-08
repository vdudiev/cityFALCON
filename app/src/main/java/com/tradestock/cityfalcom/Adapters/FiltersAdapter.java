package com.tradestock.cityfalcom.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tradestock.cityfalcom.Models.InstrumentsForFilters;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.R;

import java.util.List;

public class FiltersAdapter extends RecyclerView.Adapter<FiltersAdapter.ViewHolder> {

    LayoutInflater inflater;
    private List<InstrumentsForFilters> list;
    private Context cont;
    private RegistrationResponse registrationResponse = new RegistrationResponse();

    String filtrForSignals = "";

    public FiltersAdapter(List<InstrumentsForFilters> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        cont = context;
    }

    @NonNull
    @Override
    public FiltersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_instruments_on_filters,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull FiltersAdapter.ViewHolder holder, int position) {
        InstrumentsForFilters currentArticleData = list.get(position);
        holder.image_check_on_filters.setImageResource(R.drawable.ic_filter_not_checked);
        holder.textView_instrument_title_on_filters.setText(currentArticleData.getTitle());
        holder.instrumentId = currentArticleData.getId().toString();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView image_check_on_filters;
        final TextView textView_instrument_title_on_filters;
        private String instrumentId;



        ViewHolder(View view){
            super(view);

            image_check_on_filters = view.findViewById(R.id.instrument_image_on_filters);
            textView_instrument_title_on_filters = view.findViewById(R.id.textView_instrument_title_on_filters);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (filtrForSignals.contains(instrumentId)){
                        filtrForSignals = filtrForSignals.replaceAll(instrumentId +" ", "");
                        image_check_on_filters.setImageResource(R.drawable.ic_filter_not_checked);
                    }
                    else{
                        filtrForSignals += instrumentId + " ";
                        image_check_on_filters.setImageResource(R.drawable.ic_filter_checked);

                    }

                }
            });
        }
    }



}
