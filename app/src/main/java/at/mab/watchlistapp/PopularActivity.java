package at.mab.watchlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PopularActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popularlist);
        loadRestData();
        this.lv = (ListView) findViewById(R.id.lv_popular);
        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                SeriesBig sb = (SeriesBig)((ListView) parent).getAdapter().getItem(i);

                Bundle bundle = new Bundle();
                bundle.putString("ID", sb.getId());

                Intent intentLoadNewActivity = new Intent(PopularActivity.this, ViewSeriesActivity.class);
                intentLoadNewActivity.putExtras(bundle);
                startActivity(intentLoadNewActivity);
            }
        });
    }

    private ListView lv;
    private SeriesBigAdapter adapter;

    private void displayData(JSONObject data) throws JSONException {
        this.adapter = new SeriesBigAdapter(this, data);
        this.lv.setAdapter(this.adapter);
    }

    private void loadRestData(){
        try {
            JsonObjectRequest request = new JsonObjectRequest("https://api.themoviedb.org/3/tv/popular?api_key=02315c61f82284303a120d89ce93baa4&language=de",
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