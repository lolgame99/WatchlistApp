package at.mab.watchlistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Lucas Huber on 15.12.2017.
 */

public class SeriesSmallAdapter extends BaseAdapter {
    private ArrayList<SeriesSmall> series;
    private Context context;
    private LayoutInflater layoutInflater;

    public SeriesSmallAdapter(Context context, JSONObject data) throws JSONException {
        this.context = context;

        JSONArray content;

        content = data.getJSONArray("series");

        this.series = new ArrayList<>();
        for (int i = 0; i<content.length(); i++){

            try {
                String idString = content.getJSONObject(i).optString("id");
                this.series.add(new SeriesSmall(idString.substring(0, idString.indexOf("§")), content.getJSONObject(i).optString("name")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        this.layoutInflater = ((Activity)context).getLayoutInflater();
    }


    @Override
    public int getCount() {
        return series.size();
    }

    @Override
    public Object getItem(int i) {
        return series.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.valueOf(series.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View ll = this.layoutInflater.inflate(R.layout.layout_listitemsmall,null);

        TextView tvHeader = (TextView) ll.findViewById(R.id.tv_header);

        tvHeader.setText(this.series.get(i).getName());

        return ll;
    }
}
