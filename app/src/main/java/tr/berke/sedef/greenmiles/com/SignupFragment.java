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

import tr.berke.sedef.greenmiles.com.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {

    Button signup;

    private FragmentSignupBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_signup, container, false);
        binding = FragmentSignupBinding.inflate(getLayoutInflater());



        return viewGroup;
    }
}
