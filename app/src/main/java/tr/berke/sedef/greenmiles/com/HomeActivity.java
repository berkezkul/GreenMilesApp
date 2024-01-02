package tr.berke.sedef.greenmiles.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import tr.berke.sedef.greenmiles.com.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        /*
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new ProfileFragment()).commit();
        }
        */

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){
                    Fragment selectedFragment = null;
                    if(item.getItemId() == R.id.profile_icon){
                        selectedFragment = new ProfileFragment();
                    }
                    else if(item.getItemId() == R.id.notification_icon){
                        selectedFragment = new NotificationFragment();
                    }
                    else if(item.getItemId() == R.id.add_car_icon){
                        selectedFragment = new CarFragment();
                    }
                    else if(item.getItemId() == R.id.map_icon){
                        selectedFragment = new MapFragment();
                    }else{
                        selectedFragment = null;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, selectedFragment).commit();

                    return true;

                };
            };
}