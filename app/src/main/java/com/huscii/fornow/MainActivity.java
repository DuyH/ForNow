package com.huscii.fornow;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class MainActivity extends ExpandableListActivity {

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<EventData> childItems = new ArrayList<EventData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        parentItems.add("Android");
        ArrayList<EventData> child = new ArrayList<EventData>();
        child.add(new EventData("title","tag","description",15,30,10,10));
        child.add(new EventData("t2","tagme","descripture",15,30,10,10));
        childItems = child;



        ExpandableListView expandableList = getExpandableListView();
        expandableList.setClickable(true);
        EventExpandableListAdapter adapter = new EventExpandableListAdapter(parentItems, childItems);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableList.setAdapter(adapter);
        expandableList.setOnChildClickListener(this);
    }
}
