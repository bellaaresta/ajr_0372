package com.bellaarestakadang.ajr_0372.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bellaarestakadang.ajr_0372.R;
import com.bellaarestakadang.ajr_0372.models.Promo;

import java.util.ArrayList;
import java.util.List;

public class PromoAdapter extends
        RecyclerView.Adapter<PromoAdapter.ViewHolder>
        implements Filterable {

    private List<Promo> promoList, filteredPromoList;
    private Context context;

    public PromoAdapter(List<Promo> promoList, Context context) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_promo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int positiion){
        Promo promo = filteredPromoList.get(positiion);

        holder.tvKodePromo.setText(promo.getKode_promo());
        holder.tvJenisPromo.setText(promo.getJenis_promo());
        holder.tvDiskonPromo.setText(String.valueOf(promo.getDiskon_promo()));
        holder.tvKeterangan.setText(promo.getKeterangan());
        holder.tvStatusPromo.setText(promo.getStatus_promo());
    }

    @Override
    public int getItemCount() {
        return filteredPromoList.size();
    }

    public void setPromoList(List<Promo> promoList) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Promo> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()){
                    filtered.addAll(promoList);
                } else {
                    for (Promo promo : promoList) {
                        if (promo.getKode_promo().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(promo);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredPromoList.clear();
                filteredPromoList.addAll((List<Promo>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKodePromo, tvJenisPromo, tvDiskonPromo, tvKeterangan, tvStatusPromo;
        CardView cvPromo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKodePromo = itemView.findViewById(R.id.tv_kodepromo);
            tvJenisPromo = itemView.findViewById(R.id.tv_jenispromo);
            tvDiskonPromo = itemView.findViewById(R.id.tv_diskonpromo);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvStatusPromo = itemView.findViewById(R.id.tv_kodepromo);
            cvPromo = itemView.findViewById(R.id.cv_promo);
        }
    }
}
