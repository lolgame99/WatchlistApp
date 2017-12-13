package at.mab.watchlistapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        //Initialize
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

        //Setting Texts
        tvHeader.setText(this.series.getHeader());
        tbtvName.setText(this.series.getHeader());
        setRightDate(this.series.getFirstAirDate(), tbtvFirstAir);
        tbtvSeasonCount.setText(String.valueOf(series.getSeasonCount()));
        tbtvEpisodeCount.setText(String.valueOf(series.getEpisodeCount()));
        setRightDate(this.series.getLastAirDate(), tbtvLastAir);
        tbtvStatus.setText(this.series.getStatus());
        setSynopsis(tbtvSynopsis);
        setGenres(tbtvGenres);
        setProduction(tbtvProduction);
        loadImageFromUrl(this.series.getPoster(), ivSeriesImage);

    }

    private void setSynopsis(TextView tbtvSynopsis) {
        String Synopsis = this.series.getSynopsis();
        if (Synopsis.length() < 30) {
            Synopsis = "Es ist leider keine Synopsis vorhanden. Tut uns sehr leid!";
        }
        tbtvSynopsis.setText(Synopsis);
    }

    private void setRightDate(String date, TextView textView) {
        String strDate = date;
        SimpleDateFormat orgDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newDate = new SimpleDateFormat("dd.MM.yyyy");
        try {
            textView.setText(newDate.format(orgDate.parse(strDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setProduction(TextView tbtvProduction) throws JSONException {
        tbtvProduction.setText("");
        int c = 0;
        ArrayList<JSONObject> productionList = new ArrayList<>();
        for (int i = 0; i < this.series.getProductionCompanies().length(); i++){
            productionList.add((JSONObject) this.series.getProductionCompanies().get(i));
        }
        c = 0;
        for (int i = 0; i < productionList.size(); i++){
            tbtvProduction.append(productionList.get(i).optString("name"));
            if (c < productionList.size() - 1){
                tbtvProduction.append("\n");
                c++;
            }
        }
    }

    private void setGenres(TextView tbtvGenres) throws JSONException {
        tbtvGenres.setText("");
        int c = 0;
        ArrayList<JSONObject> genreList = new ArrayList<>();
        for (int i = 0; i < this.series.getGenres().length(); i++){
            genreList.add((JSONObject) this.series.getGenres().get(i));
        }
        for (int i = 0; i < genreList.size(); i++){
            tbtvGenres.append(genreList.get(i).optString("name"));
            if (c < genreList.size() - 1){
                tbtvGenres.append("\n");
                c++;
            }
        }
    }

    private void loadImageFromUrl (String url, ImageView imageView){
        Picasso.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_placeholder) //optional
                .error(R.mipmap.ic_placeholder)
                //.resize(100, 150)         //optional
                //.centerCrop()                        //optional
                .into(imageView);                        //Your image view object.

    }

}
