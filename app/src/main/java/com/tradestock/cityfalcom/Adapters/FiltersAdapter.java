package com.tradestock.cityfalcom.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        /*final TextView sector;
        final TextView sell;
        final TextView buy;
        final ImageView image_for_sector;
        final ImageButton imageButton_go_to_sector_details;*/


        ViewHolder(View view){
            super(view);



        }
    }



}
