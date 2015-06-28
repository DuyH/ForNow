package com.huscii.fornow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ian on 6/28/2015.
 */
public class EventExpandableListAdapter extends BaseExpandableListAdapter{

    private Activity activity;
    private ArrayList<Object> childtems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems;
    private ArrayList<EventData> child;

    public EventExpandableListAdapter(ArrayList<String> parents, ArrayList<Object> children) {
        this.parentItems = parents;
        this.childtems = children;
    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }


    @Override
    public int getGroupCount() {
        return parentItems.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<EventData>) childtems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_body, null);
        }

        ((TextView) convertView).setText(parentItems.get(groupPosition));
        return convertView;


    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        child = (ArrayList<EventData>) childtems.get(groupPosition);
        TextView tag = null;
        TextView mapRedirect = null;
        TextView duration = null;
        TextView description = null;
        TextView start = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_head, null);
        }
        tag = (TextView) convertView.findViewById(R.id.eventTag);
        tag.setText(child.get(childPosition).getTag());

        //mapRedirect = (TextView) convertView.findViewById(R.id.eventMapRedirect);
        //mapRedirect.setText(child.get(childPosition).);

        duration = (TextView) convertView.findViewById(R.id.eventDuration);
        duration.setText(child.get(childPosition).getDuration());

        description = (TextView) convertView.findViewById(R.id.eventDescription);
        description.setText(child.get(childPosition).getDesc());

        start = (TextView) convertView.findViewById(R.id.eventStart);
        start.setText(child.get(childPosition).getTime());

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View view) {
////                Toast.makeText(activity, child.get(childPosition),
////                        Toast.LENGTH_SHORT).show();
////            }
//        });
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
