package tr.berke.sedef.greenmiles.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public StationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_station, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;

        // İstanbul
        LatLng istanbul = new LatLng(41.0082, 28.9784);
        addGreenMarker(myMap, istanbul, "Sharz.net Charging Station- İstanbul");

        // Bursa
        LatLng bursa = new LatLng(40.0192, 29.4324);
        addGreenMarker(myMap, bursa, "ZES Elektrikli Şarj İstasyonu- Bursa");

        // Muğla
        LatLng mugla = new LatLng(36.81054, 28.17907);
        addGreenMarker(myMap, mugla, "Trugo Şarj İstasyonu- Muğla");

        LatLng bodrum = new LatLng(37.2506, 27.6644);
        addGreenMarker(myMap, bodrum, "ZES Elektrikli Şarj İstasyonu- Bodrum");

        LatLng izmir = new LatLng(38.40371, 26.54182);
        addGreenMarker(myMap, izmir, "ZES Elektrikli Şarj İstasyonu- İzmir");


    }

    private void addGreenMarker(GoogleMap map, LatLng position, String title) {
        MarkerOptions options = new MarkerOptions().position(position).title(title);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(options);
        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }
}
