package br.com.bruno.apo.fragments;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import br.com.bruno.apo.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView;

    //Zxing
    private GoogleMap map;

    private static final String MAPVIEW_BUNDLE_KEY = "chave";

    // Zxing - Inicializa o launcher de leitura de QR Code
    private final ActivityResultLauncher<ScanOptions> qrCodeLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {
                    updateMapLocation(result.getContents());
                } else {
                    Toast.makeText(getContext(), "Nenhum QR Code lido", Toast.LENGTH_SHORT).show();
                }
            });

    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);

        // Zxing - Configuração do FloatingActionButton para escanear QR Code
        FloatingActionButton fab = v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Escaneie o QR Code");
            options.setBeepEnabled(true);
            qrCodeLauncher.launch(options);
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }
        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng initialLocation = new LatLng(-26.0864352, -53.0425739);
        map.addMarker(new MarkerOptions().position(initialLocation).title("Unipar Francisco Beltrão"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(initialLocation, 17);
        map.animateCamera(cameraUpdate);
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    // Z-xing - Método para atualizar a localização no mapa
    private void updateMapLocation(String qrCodeContent) {
        // Log para depuração
        Log.d("QRCodeContent", "Conteúdo do QR Code: " + qrCodeContent);

        // Divide o conteúdo por vírgula
        String[] parts = qrCodeContent.split(",");

        // Verifica se há pelo menos 2 partes (latitude e longitude)
        if (parts.length >= 2) {
            try {
                // Converte latitude e longitude para double
                double latitude = Double.parseDouble(parts[0].trim()); // Remove espaços
                double longitude = Double.parseDouble(parts[1].trim()); // Remove espaços
                // Define o nível de zoom se estiver presente, caso contrário, usa 17
                float zoomLevel = (parts.length > 2) ? Float.parseFloat(parts[2].trim()) : 17;
                // Cria nova localização
                LatLng newLocation = new LatLng(latitude, longitude);
                map.clear();
                map.addMarker(new MarkerOptions().position(newLocation).title("Nova Localização"));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, zoomLevel));
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Formato de QR Code inválido", Toast.LENGTH_SHORT).show();
                Log.e("QRCodeError", "Erro ao converter coordenadas: " + e.getMessage());
            }
        } else {
            Toast.makeText(getContext(), "Formato de QR Code não reconhecido", Toast.LENGTH_SHORT).show();
            Log.e("QRCodeError", "Formato de QR Code não reconhecido, partes: " + parts.length);
        }
    }

}