package me.willowcheng.makerthings.ui;

import android.content.Context;
import android.net.Uri;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import me.willowcheng.makerthings.model.OpenHABNotification;
import me.willowcheng.makerthings.util.Constants;
import me.willowcheng.makerthings.util.MySmartImageView;

import java.util.ArrayList;

/**
 * Created by belovictor on 03/04/15.
 */
public class OpenHABNotificationAdapter extends ArrayAdapter<OpenHABNotification> {
    private int mResource;
    private String mOpenHABUsername;
    private String mOpenHABPassword;

    public OpenHABNotificationAdapter(Context context, int resource, ArrayList<OpenHABNotification> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OpenHABNotification notification = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
        }
        TextView createdView = (TextView)convertView.findViewById(me.willowcheng.makerthings.R.id.notificationCreated);
        TextView messageView = (TextView)convertView.findViewById(me.willowcheng.makerthings.R.id.notificationMessage);
        MySmartImageView imageView = (MySmartImageView)convertView.findViewById(me.willowcheng.makerthings.R.id.notificationImage);
        if (imageView != null) {
            if (notification.getIcon() != null && imageView != null) {
                String iconUrl = Constants.MYOPENHAB_BASE_URL + "/images/" + Uri.encode(notification.getIcon() + ".png");
                imageView.setImageUrl(iconUrl, me.willowcheng.makerthings.R.drawable.openhabiconsmall,
                        mOpenHABUsername, mOpenHABPassword);
            } else {
                imageView.setImageDrawable(getContext().getResources().getDrawable(me.willowcheng.makerthings.R.drawable.openhabicon_light));
            }
        }
        createdView.setText(DateUtils.getRelativeDateTimeString(this.getContext(), notification.getCreated().getTime(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        messageView.setText(notification.getMessage());
        return convertView;
    }

    public String getOpenHABUsername() {
        return mOpenHABUsername;
    }

    public void setOpenHABUsername(String openHABUsername) {
        this.mOpenHABUsername = openHABUsername;
    }

    public String getOpenHABPassword() {
        return mOpenHABPassword;
    }

    public void setOpenHABPassword(String openHABPassword) {
        this.mOpenHABPassword = openHABPassword;
    }
}
