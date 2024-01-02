package tr.berke.sedef.greenmiles.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PasswordActivity extends AppCompatActivity {

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        send = (Button) findViewById(R.id.sendBtn);
        send.setOnClickListener(new View.OnClickListener() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            @Override
            public void onClick(View v) {

                VerificationCodeFragment VCFragment = new VerificationCodeFragment();
                fragmentTransaction.replace(R.id.password_container, VCFragment).commit();
            }
        });

    }
}