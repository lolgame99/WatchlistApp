package at.mab.watchlistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        ImageButton btn_popular = (ImageButton) findViewById(R.id.btn_popular);
        ImageButton btn_allSeries = (ImageButton) findViewById(R.id.btn_allSeries);

        btn_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, PopularActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        btn_allSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, AllSeriesActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}
