package tr.berke.sedef.greenmiles.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SocietyPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_page);


        ImageView societyBackImageView = findViewById(R.id.societyback);
        societyBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Diğer aktiviteye geçmek için Intent kullan
                Intent intent = new Intent(SocietyPageActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}