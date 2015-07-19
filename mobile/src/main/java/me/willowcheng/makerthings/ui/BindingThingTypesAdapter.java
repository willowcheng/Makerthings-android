package me.willowcheng.makerthings.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import me.willowcheng.makerthings.R;
import me.willowcheng.makerthings.model.thing.ThingType;

import java.util.ArrayList;

/**
 * Created by belovictor on 23/05/15.
 */
public class BindingThingTypesAdapter extends ArrayAdapter<ThingType> {
    private int mResource;

    public BindingThingTypesAdapter(Context context, int resource, ArrayList<ThingType> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThingType thingType = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
        }
        TextView labelView = (TextView)convertView.findViewById(R.id.thingTypeLabel);
        TextView descriptionView = (TextView)convertView.findViewById(R.id.thingTypeDescription);
        labelView.setText(thingType.getLabel());
        descriptionView.setText(thingType.getDescription());
        return convertView;
    }
}
