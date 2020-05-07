package com.example.htgh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.htgh.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 *普通用户中  选择温室的adapter
 */
public class SelectAdapter extends ArrayAdapter<JSONObject> {
    private int resourceId;

    public SelectAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public SelectAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SelectAdapter(@NonNull Context context, int resource, @NonNull JSONObject[] objects) {
        super(context, resource, objects);
    }

    public SelectAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull JSONObject[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public SelectAdapter(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    public SelectAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<JSONObject> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=null;
        try {
            JSONObject item=getItem(position);
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_checked_item, null);
            TextView label=view.findViewById(R.id.spinner_item_label);
            label.setText(item.getString("houseName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        JSONObject item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, null);
        TextView label=view.findViewById(R.id.spinner_item_label);
        TextView id=view.findViewById(R.id.spinner_item_id);
        try {
            label.setText(item.getString("houseName"));
            id.setText(item.getString("houseId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
