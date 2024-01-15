package tr.berke.sedef.greenmiles.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StationFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap myMap;
    private String selectedBrand;
    Spinner spinnerBrand;
    TextView txtBrand;
    ArrayAdapter adapterBrand;

    public StationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_station, container, false);

    }


    private void init(View view) {
        spinnerBrand = view.findViewById(R.id.spinnerBrand);
        txtBrand = view.findViewById(R.id.txtBrandStation);

        adapterBrand = ArrayAdapter.createFromResource(requireContext(), R.array.BrandList,
                android.R.layout.simple_spinner_item);
        adapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(adapterBrand);

        // spinnerBrand için listener
        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBrand = spinnerBrand.getSelectedItem().toString();
                // Seçilen markaya göre haritayı güncelle
                updateMap();
                txtBrand.setText(parent.getItemAtPosition(position).toString()+ "Charging Stations");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtBrand.setText(parent.getItemAtPosition(0).toString());
            }

        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void updateMap() {
        // Seçilen markaya göre haritayı güncelleyin
        if (myMap != null && selectedBrand != null) {
            myMap.clear();

            switch (selectedBrand) {
                case "Mercedes":
                    addGreenMarker(new LatLng(41.404076, -81.553065), "Mercedes Elektrikli Şarj İstasyonu-Rockside");
                    addGreenMarker(new LatLng(37.936441, 32.566409), "Tora Şarj- Konya");
                    addGreenMarker(new LatLng(39.394789, -30.034858), "ZES Güral Porselen- Kütahya");
                    addGreenMarker(new LatLng(39.607916, 27.90289), "VoltRun e-Power- Balıkesir");
                    break;
                case "BMW":
                    addGreenMarker(new LatLng(52.56613, 13.46247), "BMW Elektrikli Şarj İstasyonu- Berlin, Almanya");
                    addGreenMarker(new LatLng(47.50206, 21.25408), "BMW Elektrikli Şarj İstasyonu- Macaristan");
                    break;
                case "Tesla":
                    addGreenMarker(new LatLng(41.40451,26.34295), "Tesla Elektrikli Şarj İstasyonu- Edirne");
                    addGreenMarker(new LatLng(41.005560, 29.07274), "Tesla Elektrikli Şarj İstasyonu- İstanbul");
                    addGreenMarker(new LatLng(39.95066381, 32.8313260), "Tesla Elektrikli Şarj İstasyonu- Ankamall, Ankara");
                    break;
                case "Toyota":
                    addGreenMarker(new LatLng(40.36849, -3.697984), "Toyota Elektrikli Şarj İstasyonu- Deportivo, İspanya");

                    break;
                case "Ford":
                    addGreenMarker(new LatLng(41.404076, -81.553065), "Ford Elektrikli Şarj İstasyonu-1");
                    // Ford için diğer istasyonları ekleyin
                    break;
                case "TOGG":
                    addGreenMarker(new LatLng(41.404076, -81.553065), "TOGG Elektrikli Şarj İstasyonu-1");
                    // TOGG için diğer istasyonları ekleyin
                    break;
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        // İlk oluşturulduğunda haritayı güncelleme
        updateMap();
    }

    private void addGreenMarker(LatLng position, String title) {
        MarkerOptions options = new MarkerOptions().position(position).title(title);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        myMap.addMarker(options);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }
}
