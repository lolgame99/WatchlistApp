package at.mab.watchlistapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Lucas Huber on 05.12.2017.
 */

public class ViewSeriesActivity extends AppCompatActivity{
    private String seriesID;
    private Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewseries);

        seriesID = getIntent().getExtras().getString("ID");

        loadRestData();

    }

    private void loadRestData(){
        try {
            JsonObjectRequest request = new JsonObjectRequest("https://api.themoviedb.org/3/tv/" + this.seriesID + "?api_key=02315c61f82284303a120d89ce93baa4&language=de",
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            Log.d("HUL", response.toString());
                            series = new Series(
                                    response.optString("overview"),
                                    response.optString("id"),
                                    response.optString("name"),
                                    "http://image.tmdb.org/t/p/w342" + response.optString("poster_path"),
                                    response.optString("first_air_date"),
                                    response.optJSONArray("genres"),
                                    response.optString("last_air_date"),
                                    response.optInt("number_of_seasons"),
                                    response.optInt("number_of_episodes"),
                                    response.optJSONArray("production_companies"),
                                    response.optString("status"));


                            try {
                                displayData(series);
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

    private void displayData(Series series) throws JSONException {
        TextView tvHeader = (TextView) this.findViewById(R.id.tv_header);
        TextView tbtvName = (TextView) this.findViewById(R.id.tb_tv_name);
        TextView tbtvFirstAir = (TextView) this.findViewById(R.id.tb_tv_firstAir);
        TextView tbtvSeasonCount = (TextView) this.findViewById(R.id.tb_tv_season);
        TextView tbtvEpisodeCount = (TextView) this.findViewById(R.id.tb_tv_episode);
        TextView tbtvProduction = (TextView) this.findViewById(R.id.tb_tv_production);
        TextView tbtvGenres = (TextView) this.findViewById(R.id.tb_tv_genres);
        TextView tbtvLastAir = (TextView) this.findViewById(R.id.tb_tv_lastAir);
        TextView tbtvStatus = (TextView) this.findViewById(R.id.tb_tv_status);
        TextView tbtvSynopsis = (TextView) this.findViewById(R.id.tv_synpsisContent);
        ImageView ivSeriesImage = (ImageView) this.findViewById(R.id.iv_seriesImage);

        tvHeader.setText(series.getHeader());
        tbtvName.setText(series.getHeader());
        tbtvFirstAir.setText(series.getFirstAirDate());
        //tbtvSeasonCount.setText(series.getSeasonCount());
        //tbtvEpisodeCount.setText(series.getEpisodeCount());
        tbtvLastAir.setText(series.getLastAirDate());
        tbtvStatus.setText(series.getStatus());
        tbtvSynopsis.setText(series.getSynopsis());
        tbtvGenres.setText("");
        tbtvProduction.setText("");
        ArrayList<JSONObject> genreList = new ArrayList<>();
        for (int i = 0; i < series.getGenres().length(); i++){
            genreList.add((JSONObject) series.getGenres().get(i));
        }
        for (int i = 0; i < genreList.size(); i++){
            tbtvGenres.append(genreList.get(i).optString("name") + "\n");
        }
        ArrayList<JSONObject> productionList = new ArrayList<>();
        for (int i = 0; i < series.getProductionCompanies().length(); i++){
            productionList.add((JSONObject) series.getProductionCompanies().get(i));
        }
        for (int i = 0; i < productionList.size(); i++){
            tbtvProduction.append(productionList.get(i).optString("name") + "\n");
        }
        ivSeriesImage.setImageDrawable(LoadImageFromWebOperations(series.getPoster()));

    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
