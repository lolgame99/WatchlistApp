package at.mab.watchlistapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopularActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HUL", "Oncreate ist am Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listitembig);
        loadRestData();
    }

    private ListView lv;
    private SeriesBigAdapter adapter;

    private void displayData(JSONArray data){
        this.lv = (ListView) findViewById(R.id.lv_popular);
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
                        }
                    }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("HUL", error.getMessage());
                    }
                });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("HUL", "Catch ist am Start");
        }

    }


}