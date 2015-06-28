package com.huscii.fornow;


import java.util.List;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    //private Activity activity;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, EventData> _listDataChild;



    // Ken: How to pull db data:
    // Listener
    //Firebase.setAndroidContext(this);
    //Firebase firebase = new Firebase("https://fornow.firebaseio.com/events");

    // Example push:
    // Event temp = new Event(45.4,20.7,"Frisbee", 5, "come play frisbee at Google");
    // firebase.push().setValue(temp);

    //Querying db:
    //Query queryRef = myRef.orderByChild("range");

    // Pulling (query contains all of our db data):
    // queryRef.addListenerForSingleValueEvent(new ValueEventListener(){
    //  @Override
    //  public void onDataChange(DataSnapShot dataSnapShot){
    //"string" is key for a row, "object" is an event
        //HashMap<String,Object> myEv = (HashMap<String, Object>).dataSnapshote.getValue();

        // for (String item: myEv.keySet()){
            // for(String x: item.keySet()){







    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, EventData> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = ((EventData) getChild(groupPosition, childPosition)).getTitle();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblTag);

        txtListChild.setText(childText);
//
//        TextView txtListChild2 = (TextView) convertView
//                .findViewById(R.id.lblDuration);
//
//        txtListChild2.setText("play with me");
//
//        TextView txtListChild2 = (TextView) convertView
//                .findViewById(R.id.lblDuration);
//
//        txtListChild2.setText("play with me");
//
//        TextView txtListChild2 = (TextView) convertView
//                .findViewById(R.id.lblDuration);
//
//        txtListChild2.setText("play with me");
//
//        TextView txtListChild2 = (TextView) convertView
//                .findViewById(R.id.lblDuration);
//
//        txtListChild2.setText("play with me");

        final TextView mapRedirect = (TextView) convertView.findViewById(R.id.lblDirectTo);
        //mapRedirect.setText(child.get(childPosition).);
        SpannableString ss = new SpannableString("Get Directions");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                String title = ((EventData) getChild(groupPosition, childPosition)).getTitle();
                double lat = _listDataChild.get(_listDataHeader.get(childPosition)).getLat();
                double lon = _listDataChild.get(_listDataHeader.get(childPosition)).getLon();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+lat+", "+lon+"("+title+")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(mapRedirect.getContext().getPackageManager()) != null) {
                    mapRedirect.getContext().startActivity(mapIntent);
                }
            }
        };
        ss.setSpan(clickableSpan, 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mapRedirect.setText(ss);
        mapRedirect.setMovementMethod(LinkMovementMethod.getInstance());


        Button btnListChildAttend = (Button) convertView
                .findViewById(R.id.btnAttend);
        btnListChildAttend.setText("going?");

        Button btnListChildIgnore = (Button) convertView
                .findViewById(R.id.btnIgnore);
        btnListChildIgnore.setText("Ignore Tag Genre");

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}