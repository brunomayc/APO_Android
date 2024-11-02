package br.com.bruno.apo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.bruno.apo.retrofit.DadosTempo;
import br.com.bruno.apo.retrofit.Forecast;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView data;
        TextView diaSemana;
        TextView min;
        TextView max;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.forecastDate);
            diaSemana = itemView.findViewById(R.id.forecastWeekday);
            description = itemView.findViewById(R.id.forecastDescription);
            max = itemView.findViewById(R.id.forecastMax);
            min = itemView.findViewById(R.id.forecastMin);

        }
    }

    private ArrayList<Forecast> listaDadosTempo;
    public ListAdapter(DadosTempo dados) {
        listaDadosTempo = dados.getLista();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Forecast itemPosition = listaDadosTempo.get(position);

        viewHolder.data.setText(itemPosition.getDate());
        viewHolder.diaSemana.setText(itemPosition.getWeekday());
        viewHolder.description.setText(itemPosition.getDescription());
        viewHolder.max.setText(String.valueOf(itemPosition.getMax()) + "°");
        viewHolder.min.setText(String.valueOf(itemPosition.getMin()) + "°");
    }

    @Override
    public int getItemCount() {
        return listaDadosTempo.size();
    }

}
