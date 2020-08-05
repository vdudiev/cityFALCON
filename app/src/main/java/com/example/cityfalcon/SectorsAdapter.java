package com.example.cityfalcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SectorsAdapter extends RecyclerView.Adapter<SectorsAdapter.ViewHolder> {

    LayoutInflater inflater;
    private List<SectorForSectorsArticle> list;
    private Context cont;
    private RegistrationResponse registrationResponse = new RegistrationResponse();

    SectorsAdapter(List<SectorForSectorsArticle> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        cont = context;
    }

    @NonNull
    @Override
    public SectorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_sectors_on_sectors,parent,false);

        return new ViewHolder(view);
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull SectorsAdapter.ViewHolder holder, int position) {
        SectorForSectorsArticle currentArticleData = list.get(position);
        holder.sector.setText(currentArticleData.getSector());
        holder.sell.setText(currentArticleData.getSell().toString());
        holder.buy.setText(currentArticleData.getBuy().toString());
        Picasso.get().load(currentArticleData.getImage()).into(holder.image_for_sector);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView sector;
        final TextView sell;
        final TextView buy;
        final ImageView image_for_sector;
        final ImageButton imageButton_go_to_sector_details;


        ViewHolder(View view){
            super(view);


            sector = view.findViewById(R.id.textView_sector_name);
            sell = view.findViewById(R.id.textView_count_of_sell_on_sectors);
            buy = view.findViewById(R.id.textView_count_of_buy_on_sectors);
            image_for_sector = view.findViewById(R.id.image_for_sector_on_sectors);
            imageButton_go_to_sector_details = view.findViewById(R.id.image_button_on_sectors_to_go_sectors_signals_list);


            view.setOnClickListener(v -> {
                //тут будет переход на детаьлую страницу определённого сектора

               /* FragmentTransaction fragmentTransaction = ((FragmentActivity) cont).getSupportFragmentManager().beginTransaction();
                SignalDetails signalDetails = new SignalDetails();
                signalDetails.setSignal_id_to_set(signal_id);
                signalDetails.setSellBuyCheking(getSellBuyChek());
                fragmentTransaction.replace(R.id.content_fragment, signalDetails);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            });
        }
    }



}
