package at.mab.watchlistapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopularActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listitembig);
        loadRestData();
    }

    private void loadRestData(){
        try {
            JsonArrayRequest request = new JsonArrayRequest("https://api.themoviedb.org/3/tv/popular?api_key=02315c61f82284303a120d89ce93baa4&language=de",
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {
                            for(int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Log.d("MAB",jsonObject.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // allDone();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.d("MAB",volleyError.getMessage());
                            // Toast.makeText(MainActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


            Volley.newRequestQueue(this).add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}