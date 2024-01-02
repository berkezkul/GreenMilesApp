package tr.berke.sedef.greenmiles.com;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

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
                        selectedFragment = new StationFragment();
                    }else if(item.getItemId() == R.id.home_icon){
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        selectedFragment = null;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, selectedFragment).commit();

                    return true;

                };
            };
}