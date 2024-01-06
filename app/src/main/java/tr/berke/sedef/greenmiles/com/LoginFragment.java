package tr.berke.sedef.greenmiles.com;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginFragment extends Fragment {

    EditText EditTextMail;
    EditText EditTextPassword;

    String txtEmail;
    String  txtPassword;

    private FirebaseAuth userAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //click sign in
        Button signInButton = view.findViewById(R.id.buttonSignin);
        EditTextMail = view.findViewById(R.id.textEmail);
        EditTextPassword = view.findViewById(R.id.textPassword);

        //hide password using eye icon
        showHidePassword(view);

        // Initialize Firebase Auth
        userAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Home Activity'e geçiş
                signIn();
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

    private void showHidePassword(View view) {
        ImageView imageViewShowHidePw = view.findViewById(R.id.imageView_show_hide_pw);
        imageViewShowHidePw.setImageResource(R.drawable.show_password);

        imageViewShowHidePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If password is visible then
                if(EditTextPassword.getTransformationMethod().
                        equals(HideReturnsTransformationMethod.getInstance())){
                    EditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewShowHidePw.setImageResource(R.drawable.show_password);
                }else{
                    EditTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePw.setImageResource(R.drawable.hide_password);
                }
            }
        });
    }



    private Executor executor = Executors.newSingleThreadExecutor();

    private void signIn(){
        txtEmail = EditTextMail.getText().toString();
        txtPassword = EditTextPassword.getText().toString();

        if(!TextUtils.isEmpty(txtEmail) && (!TextUtils.isEmpty(txtPassword))){
            userAuth.signInWithEmailAndPassword(txtEmail, txtPassword)
                    .addOnCompleteListener(executor, task -> {

                        if(task.isSuccessful()) {
                            FirebaseUser current = userAuth.getCurrentUser();

                            if (current != null) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "Successfully login", Toast.LENGTH_SHORT).show();
                                        navigateToHomeActivity();
                                    }
                                });
                            }else{
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "Please try again! Password or mail can be wrong.", Toast.LENGTH_SHORT).show();
                                        navigateToHomeActivity();
                                    }
                                });
                            }
                        }else{
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Please try again! " +
                                            "Password or mail can be wrong.", Toast.LENGTH_SHORT).show();
                                    //navigateToPasswordActivity();


                                 }
                            });
                        }


                    });

        }
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
