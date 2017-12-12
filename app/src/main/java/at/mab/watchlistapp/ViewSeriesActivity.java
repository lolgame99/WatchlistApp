package at.mab.watchlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Lucas Huber on 05.12.2017.
 */

public class ViewSeriesActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewseries);

        Bundle bundle = getIntent().getExtras();
        String seriesID = bundle.getString("ID");

        Log.d("HUL", seriesID);
    }


}
