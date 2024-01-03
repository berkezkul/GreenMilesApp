package tr.berke.sedef.greenmiles.com;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    EditText textUserName;
    EditText textPassword;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //click sign in
        Button signInButton = view.findViewById(R.id.buttonSignin);

        textUserName = view.findViewById(R.id.textUsername);
        textPassword = view.findViewById(R.id.textPassword);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Home Activity'e geçiş
                //navigateToHomeActivity();



            }
        });


        //click forgot password

        Button forgotPassword = view.findViewById(R.id.buttonPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Forgot Password'e geçiş
                navigateToPasswordActivity();
            }
        });
        return view;
    }






    private void navigateToHomeActivity() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    private void navigateToPasswordActivity(){
        Intent intent = new Intent(getActivity(), PasswordActivity.class);
        startActivity(intent);
    }
}



















/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);


        /*
        TextView textView = viewGroup.findViewById(R.id.textView);
        textView.setText("1st Fragment Example");



        //return viewGroup;
    } */
