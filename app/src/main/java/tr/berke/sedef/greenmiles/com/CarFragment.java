package tr.berke.sedef.greenmiles.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import tr.berke.sedef.greenmiles.com.databinding.FragmentCarBinding;


public class CarFragment extends Fragment {


    private FragmentCarBinding binding;

    private Spinner spinnerBrand, spinnerModel;
    private TextView txtBrand, txtModel;
    ArrayAdapter<CharSequence> adapterBrand;

    String selectedBrand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void init(View view) {
        spinnerBrand = view.findViewById(R.id.spinnerBrand);
        txtBrand = view.findViewById(R.id.txtBrand);
        spinnerModel = view.findViewById(R.id.spinnerModel);
        txtModel = view.findViewById(R.id.txtModel);

        ImageView logoImageView = view.findViewById(R.id.mercedes_logo);

        adapterBrand = ArrayAdapter.createFromResource(requireContext(), R.array.BrandList,
                android.R.layout.simple_spinner_item);
        adapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(adapterBrand);

        // spinnerBrand için listener
        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtBrand.setText(parent.getItemAtPosition(position).toString());
                selectedBrand = spinnerBrand.getSelectedItem().toString();
                // Seçilen markaya göre modelleri güncelle
                updateModelSpinner(selectedBrand);
                // Seçilen markaya göre logo güncelle
                updateBrandLogo(selectedBrand, logoImageView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtBrand.setText(parent.getItemAtPosition(0).toString());
            }
        });

        // spinnerModel için listener
        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtModel.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtModel.setText(parent.getItemAtPosition(0).toString());
            }
        });
    }

    // Seçilen markaya göre model seçeneklerini göstermeyi sağlayan fonksiyon
    private void updateModelSpinner(String selectedBrand) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item);

        if (selectedBrand.equals("Mercedes")) {
            adapter.addAll(requireContext().getResources().getStringArray(R.array.Mercedes_models));
        } else if (selectedBrand.equals("BMW")) {
            adapter.addAll(requireContext().getResources().getStringArray(R.array.Bmw_models));
        } else if (selectedBrand.equals("TOGG")) {
            adapter.addAll(requireContext().getResources().getStringArray(R.array.TOOG_models));
        } else if (selectedBrand.equals("Toyota")) {
            adapter.addAll(requireContext().getResources().getStringArray(R.array.Toyota_models));
        } else if (selectedBrand.equals("Ford")) {
            adapter.addAll(requireContext().getResources().getStringArray(R.array.Ford_models));
        } else if (selectedBrand.equals("Tesla")) {
            adapter.addAll(requireContext().getResources().getStringArray(R.array.Tesla_models));
        }
        spinnerModel.setAdapter(adapter);
    }

    // Seçilen markaya göre markanın logosunu göstermeyi sağlayan fonksiyon
    private void updateBrandLogo(String selectedBrand, ImageView logoImageView) {
        int logoResource = 0;
        if (selectedBrand.equals("Mercedes")) {
            logoResource = R.drawable.mercedes_logo;
        } else if (selectedBrand.equals("BMW")) {
            logoResource = R.drawable.bmw_logo;
        } else if (selectedBrand.equals("Tesla")) {
            logoResource = R.drawable.tesla_logo;
        } else if (selectedBrand.equals("Toyota")) {
            logoResource = R.drawable.toyota_logo;
        } else if (selectedBrand.equals("Ford")) {
            logoResource = R.drawable.ford_logo;
        } else if (selectedBrand.equals("TOGG")) {
            logoResource = R.drawable.togg_logo;
        } else if (selectedBrand.equals("Select Brand")) {
            logoResource = R.drawable.cardefault;
        }
        logoImageView.setImageResource(logoResource);
    }



    FirebaseFirestore db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCarBinding.bind(view);
        binding.addCarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), HomeActivity.class);
            startActivity(intent);
        });

        //firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        super.onViewCreated(view, savedInstanceState);
        init(view);
    }







    /*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_car, container, false);
    }

     */
}