package at.mab.watchlistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.mab.watchlistapp.R;
import at.mab.watchlistapp.SeriesBig;

/**
 * Created by Lucas Huber on 28.11.2017.
 */

public class SeriesBigAdapter extends BaseAdapter {
    private ArrayList<SeriesBig> series;
    private Context context;
    private LayoutInflater layoutInflater;

    public SeriesBigAdapter(Context context, JSONObject data) throws JSONException {
        this.context = context;

        JSONArray content;

        content = data.getJSONArray("results");

        this.series = new ArrayList<>();
        for (int i = 0; i<content.length(); i++){

            try {
                this.series.add(new SeriesBig(content.getJSONObject(i).optString("overview"),"http://image.tmdb.org/t/p/w342" + content.getJSONObject(i).optString("poster_path"), content.getJSONObject(i).optString("name"), content.getJSONObject(i).optString("id")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        this.layoutInflater = ((Activity)context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.series.size();
    }

    @Override
    public Object getItem(int position) {
        return this.series.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.series.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ll = this.layoutInflater.inflate(R.layout.layout_listitembig,null);

        String fullString = this.series.get(position).getSynopsis();
        String shortString;
        String[] sentences = fullString.split("\\.",3);

        shortString = sentences[0];

        if (shortString.length()<30){
            shortString = "Es ist leider keine Synopsis vorhanden oder die Synopsis konnte nicht korrekt geladen werden. Tut uns sehr leid!";
        }

        TextView tvHeader = (TextView) ll.findViewById(R.id.tv_header);
        TextView tvSynopsis = (TextView) ll.findViewById(R.id.tv_synopsis);
        ImageView ivPoster = (ImageView) ll.findViewById(R.id.iv_seriesImage);

        tvHeader.setText(this.series.get(position).getName());
        tvSynopsis.setText(shortString);

        Picasso.with(context)
                .load(this.series.get(position).getPoster())
                .placeholder(R.mipmap.ic_placeholder) //optional
                //.resize(100, 150)         //optional
                //.centerCrop()                        //optional
                .into(ivPoster);                        //Your image view object.

        return ll;
    }
}
