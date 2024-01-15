package tr.berke.sedef.greenmiles.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btn_login);
        signup = (Button) findViewById(R.id.btn_signup);

        login.setOnClickListener(new View.OnClickListener() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            @Override
            public void onClick(View v) {

                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.replace(R.id.main_container, loginFragment).commit();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            @Override
            public void onClick(View v) {

                SignupFragment signupFragment = new SignupFragment();
                fragmentTransaction.replace(R.id.main_container, signupFragment).commit();
            }
        });
    }
}