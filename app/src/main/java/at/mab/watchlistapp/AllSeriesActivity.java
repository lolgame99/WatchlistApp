package at.mab.watchlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bmaye on 07.11.2017.
 */

public class AllSeriesActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_allserieslist);
        loadRestData();
        this.lv = (ListView) findViewById(R.id.lv_allseries);

        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                SeriesSmall sm = (SeriesSmall) ((ListView) parent).getAdapter().getItem(i);

                Bundle bundle = new Bundle();
                bundle.putString("ID", sm.getId());

                Intent intentLoadNewActivity = new Intent(AllSeriesActivity.this, ViewSeriesActivity.class);
                intentLoadNewActivity.putExtras(bundle);
                startActivity(intentLoadNewActivity);
            }
        });

    }

    private ListView lv;
    private SeriesSmallAdapter adapter;

    private void displayData(JSONObject data) throws JSONException {
        this.adapter = new SeriesSmallAdapter(this, data);
        this.lv.setAdapter(this.adapter);
    }

    private void loadRestData(){
        try {
            JsonObjectRequest request = new JsonObjectRequest("http://benni.dyndns.info:4841/Watchlist_API/api/series/all",
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            Log.d("HUL", response.toString());

                            try {
                                displayData(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.wtf("HUL", e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d("HUL", error.getMessage());
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
