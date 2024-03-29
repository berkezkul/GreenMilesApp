package tr.berke.sedef.greenmiles.com;


import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SignupFragment extends Fragment {

    private FirebaseAuth userAuth;

    private FirebaseFirestore db;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText userNameEditText;

    //private EditText phoneEditText;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        emailEditText = view.findViewById(R.id.textMail);
        passwordEditText = view.findViewById(R.id.textPassword);
        userNameEditText = view.findViewById(R.id.textUsername);
        //phoneEditText = view.findViewById(R.id.textPhone);


        userAuth = FirebaseAuth.getInstance();

        Button signUpButton = view.findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        return view;
    }


    private Executor executor = Executors.newSingleThreadExecutor();
    private void signUp() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String username = userNameEditText.getText().toString();
        //String phoneNumber = phoneEditText.getText().toString();


        userAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(executor,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser current = userAuth.getCurrentUser();
                                    if(current != null){
                                        db = FirebaseFirestore.getInstance();
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("Email", email);
                                        user.put("Password", password);
                                        user.put("Username", username);
                                        //user.put("Phone", phoneNumber);

                                        db.collection("Users").document(current.getUid()).
                                                set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(getContext(),
                                                                "Succesfully sign up", Toast.LENGTH_SHORT).show();
                                                        navigateToLoginFragment();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getContext(), "Failed to add user to Firestore.", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = userAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                            private void updateUI(FirebaseUser user) {

                            }

                        });
    }


    private void navigateToLoginFragment() {
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, new LoginFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}