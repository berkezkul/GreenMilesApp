package tr.berke.sedef.greenmiles.com;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public class ProfileFragment extends Fragment {

    private TextView textViewWelcome, textViewName, textViewEmail, textViewRegisterDate;
    private ImageView imageView;
    private FirebaseAuth authProfile;
    private FirebaseFirestore db;
    private View v;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment'ın layout'unu inflate et
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        return v;
    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        findViews();

        if (firebaseUser != null) {
            showUserProfile(firebaseUser);
        } else {
            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
        }

        //imageView
        imageView =  v.findViewById(R.id.imageView_profile_pic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToUploadProfilePicActivity();
            }
        });

        signOut();
    }

    private void findViews() {
        textViewWelcome = v.findViewById(R.id.textView_show_welcome);
        textViewName = v.findViewById(R.id.textView_show_name);
        textViewEmail = v.findViewById(R.id.textView_show_email);
        textViewRegisterDate = v.findViewById(R.id.textView_show_register_date);
        //imageView =  v.findViewById(R.id.imageView_profile_pic);
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

            DocumentReference userRef = db.collection("Users").document(firebaseUser.getUid());

            userRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Map<String, Object> userData = task.getResult().getData();

                    if (userData != null) {
                        // Firestore'daki verilerle TextView'leri güncelleme
                        String userName = (String) userData.get("Username");
                        String email = (String) userData.get("Email");


                        textViewName.setText(userName);
                        textViewEmail.setText(email);

                        String welcome = getResources().getString(R.string.welcome_user, userName);
                        textViewWelcome.setText(welcome);

                        Uri uri = firebaseUser.getPhotoUrl();
                        Picasso.with(getContext()).load(uri).into(imageView);
                    }
                } else {
                    // Hata durumunda
                    Log.e("ProfileFragment", "Error getting user document", task.getException());
                }
            });
        }
    }

    private void signOut() {
        Button signOutButton = v.findViewById(R.id.buttonSignOut);
        signOutButton.setOnClickListener(v -> {
            authProfile.signOut();
            Toast.makeText(getContext(), "Signed Out", Toast.LENGTH_SHORT).show();
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.home_container, new LoginFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void navigateToUploadProfilePicActivity(){
        Intent intent = new Intent(getActivity(), UploadProfilePicActivity.class);
        startActivity(intent);
    }
}

