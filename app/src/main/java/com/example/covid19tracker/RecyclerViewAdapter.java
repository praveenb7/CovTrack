package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<CoronaModelClass> coronaModelClassArrayList;

    RecyclerViewAdapter(Context context, ArrayList<CoronaModelClass> coronaItemArrayList) {
        this.context = context;
        this.coronaModelClassArrayList = coronaItemArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_items, parent, false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        CoronaModelClass coronaModelClass = coronaModelClassArrayList.get(position);
        String state = coronaModelClass.getState();
        String death = coronaModelClass.getDeath();
        String recovered = coronaModelClass.getRecovered();
        String active = coronaModelClass.getActive();
        String confirmed = coronaModelClass.getConfirmed();
        String lastUpdt = coronaModelClass.getLastUpdated();
        String todayDeath = coronaModelClass.getTodayDeath();
        String todayActive = coronaModelClass.getTodayActive();
        String todayRecovered = coronaModelClass.getTodayRecovered();

        holder.state.setText(state);
        holder.death.setText(death);
        holder.recovered.setText(recovered);
        holder.active.setText(active);
        holder.confirmed.setText(confirmed);
        holder.lastUpdate.setText(lastUpdt);
        holder.todayDeath.setText(String.format("(%s)", todayDeath));
        holder.todayActive.setText(String.format("(%s)", todayActive));
        holder.todayRecovered.setText(String.format("(%s)", todayRecovered));


    }

    @Override
    public int getItemCount() {
        return coronaModelClassArrayList.size();
    }

    //Holding all the views of the layout chosen to display the content
    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView state, death, recovered, active, confirmed, lastUpdate, todayDeath, todayActive, todayRecovered;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.stateName);
            death = itemView.findViewById(R.id.death);
            recovered = itemView.findViewById(R.id.recovered);
            active = itemView.findViewById(R.id.active);
            confirmed = itemView.findViewById(R.id.confirmed);
            lastUpdate = itemView.findViewById(R.id.lastUpdated);
            todayDeath = itemView.findViewById(R.id.todayDeath);
            todayActive = itemView.findViewById(R.id.todayActive);
            todayRecovered = itemView.findViewById(R.id.todayRecovered);


        }
    }

}
