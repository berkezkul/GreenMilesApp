package tr.berke.sedef.greenmiles.com;


import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import tr.berke.sedef.greenmiles.com.databinding.FragmentSignupBinding;

public class ResetPasswordFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);



        Button back_login = view.findViewById(R.id.button_back_login);
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginFragment();
            }
        });

        return view;
    }

    private void navigateToLoginFragment() {
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.password_container, new LoginFragment());
            transaction.addToBackStack(null); // Geri düğmesi ile geri dönüşü sağlar
            transaction.commit();
        }
    }
}