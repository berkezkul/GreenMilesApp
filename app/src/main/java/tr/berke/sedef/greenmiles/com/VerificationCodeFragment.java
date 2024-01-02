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

public class VerificationCodeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification_code, container, false);

        Button btn_verify = view.findViewById(R.id.button_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToResetPasswordFragment();
            }
        });

        return view;
    }

    private void navigateToResetPasswordFragment() {
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.password_container, new ResetPasswordFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}