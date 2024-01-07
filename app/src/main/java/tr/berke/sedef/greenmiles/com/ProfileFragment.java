package tr.berke.sedef.greenmiles.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public class ProfileFragment extends Fragment {

    private TextView textViewWelcome, textViewName, textViewEmail, textViewRegisterDate;
    private FirebaseAuth auth;

    private View v;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        /*
        v = getView(); // onCreateView içinde tanımlanan v'yi al

        // Buraya bir log ekleyerek findViews metodunun çağrıldığını kontrol et
        Log.d("ProfileFragment", "findViews method is called in onCreate");
        findViews(); // findViews metodunu burada çağır

        //show profile details
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser != null){
            showUserProfile(firebaseUser);
        }else{
            Toast.makeText(getContext(),"Something went wrong!!!", Toast.LENGTH_SHORT).show();
        }

         */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        // findViews ve showUserProfile metodlarını burada çağırabilirsin
        findViews();
        showUserProfile(auth.getCurrentUser());
        signOut();
        return v;
    }

    private void showUserProfile(FirebaseUser firebaseUser) {

        if (firebaseUser != null) {
            FirebaseUserMetadata metadata = firebaseUser.getMetadata();
            if (metadata != null) {
                long registerTimeStamp = metadata.getCreationTimestamp();
                String datePattern = "E, dd MMMM yyyy hh:mm a z";
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                sdf.setTimeZone(TimeZone.getDefault());
                String register = sdf.format(new Date(registerTimeStamp));

                String registerDate = getResources().getString(R.string.user_since, register);
                textViewRegisterDate.setText(registerDate);
            }
            String userName = firebaseUser.getDisplayName();
            textViewName.setText(userName);

            //String name = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();
            //textViewName.setText(name);
            textViewEmail.setText(email);

            String welcome = getResources().getString(R.string.welcome_user, userName);
            textViewWelcome.setText(welcome);
        }
    }

    private void signOut() {
        Button signOutButton = v.findViewById(R.id.buttonSignOut);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(getContext(), "Signed Out", Toast.LENGTH_SHORT).show();
                if (getFragmentManager() != null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.home_container, new LoginFragment());
                    transaction.addToBackStack(null); // Geri düğmesi ile geri dönüşü sağlar
                    transaction.commit();
                }
            }
        });
    }

    private void findViews() {

        textViewWelcome = v.findViewById(R.id.textView_show_welcome);
        textViewName = v.findViewById(R.id.textView_show_name);
        textViewEmail = v.findViewById(R.id.textView_show_email);
        textViewRegisterDate = v.findViewById(R.id.textView_show_register_date);

    }
}




























/*
public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        super.onViewCreated(view, savedInstanceState);
    }
}

 */