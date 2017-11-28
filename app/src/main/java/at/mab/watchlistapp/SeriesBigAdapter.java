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

import java.util.ArrayList;

/**
 * Created by Lucas Huber on 28.11.2017.
 */

public class SeriesBigAdapter extends BaseAdapter {
    private ArrayList<SeriesBig> series;
    private Context context;
    private LayoutInflater layoutInflater;

    public SeriesBigAdapter(Context context, JSONArray content) {
        this.context = context;

        this.series = new ArrayList<>();
        for (int i = 0; i<content.length(); i++){

            try {
                this.series.add(new SeriesBig(content.getJSONObject(i).optString("overview"),"http://image.tmdb.org/t/p/w342" + content.getJSONObject(i).optString("poster_path"), content.getJSONObject(i).optString("name")));
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
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ll = this.layoutInflater.inflate(R.layout.layout_listitembig,null);

        TextView tvHeader = (TextView) ll.findViewById(R.id.tv_header);
        TextView tvSynopsis = (TextView) ll.findViewById(R.id.tv_synopsis);
        ImageView ivPoster = (ImageView) ll.findViewById(R.id.iv_seriesImage);

        tvHeader.setText(this.series.get(position).getName());
        tvSynopsis.setText(this.series.get(position).getSynopsis());

        Picasso.with(context)
                .load(this.series.get(position).getPoster())
                .placeholder(R.mipmap.ic_placeholder) //optional
                //.resize(imgWidth, imgHeight)         //optional
                //.centerCrop()                        //optional
                .into(ivPoster);                        //Your image view object.

        return ll;
    }
}
