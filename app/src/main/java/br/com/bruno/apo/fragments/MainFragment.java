package br.com.bruno.apo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.bruno.apo.ListAdapter;
import br.com.bruno.apo.R;
import br.com.bruno.apo.retrofit.ApiPojo;
import br.com.bruno.apo.retrofit.ApiWheater;
import br.com.bruno.apo.retrofit.DadosTempo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    DadosTempo dados;
    TextView cidade;
    TextView data;
    TextView temperatura;
    TextView descricao;
    TextView chuva;
    TextView velVento;
    TextView umidade;

    public MainFragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dados = new DadosTempo();

        //Cria a instância Retrofit
        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://api.hgbrasil.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiWheater httpRequest = client.create(ApiWheater.class);

        Call<ApiPojo> call = httpRequest.getInfTempo();

        call.enqueue(callback);

        mAdapter = new ListAdapter(dados);

        mRecyclerView.setAdapter(mAdapter);

        cidade = v.findViewById(R.id.resultsCityName);
        data = v.findViewById(R.id.resultsDate);
        temperatura = v.findViewById(R.id.resultsTemp);
        descricao = v.findViewById(R.id.resultsDescription);
        chuva = v.findViewById(R.id.resultsRain);
        velVento = v.findViewById(R.id.resultsWindySpeedy);
        umidade = v.findViewById(R.id.resultsHumidity);

        return v;
    }

    private Callback<ApiPojo> callback = new Callback<ApiPojo>() {
        @Override
        public void onResponse(Call<ApiPojo> call, Response<ApiPojo> response) {

            dados.setCidade(response.body().getResults().getCityName());
            dados.setData(response.body().getResults().getDate());
            dados.setTemperatura(response.body().getResults().getTemp());
            dados.setDescricao(response.body().getResults().getDescription());
            dados.setChuva(response.body().getResults().getRain());
            dados.setVelVento(response.body().getResults().getWindSpeedy());
            dados.setUmidade(response.body().getResults().getHumidity());
            dados.setLista(response.body().getResults().getForecast());

            cidade.setText(dados.getCidade());
            data.setText(dados.getData());
            temperatura.setText(String.valueOf(dados.getTemperatura()) + "°");
            descricao.setText(dados.getDescricao());
            chuva.setText(String.valueOf(dados.getChuva()) + " mm");
            velVento.setText(dados.getVelVento());
            umidade.setText(String.valueOf(dados.getUmidade()) + "%");

            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<ApiPojo> call, Throwable t) {
            Log.e(TAG, "Falha no Retrofit: "+ t.toString());
        }
    };

}