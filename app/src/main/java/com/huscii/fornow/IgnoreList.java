package com.huscii.fornow;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class IgnoreList extends ListActivity {
    private LinkedList<String> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignore_list);
        tags = new LinkedList<>();
        File tagFile = new File("tagFile.txt");
        if (tagFile.exists()) {
            Scanner scn;
            try {
                scn = new Scanner(tagFile);
                while (scn.hasNext()) {
                    tags.add(scn.next());
                }
                ArrayAdapter<String> list = new ArrayAdapter(this,
                        R.layout.abc_action_menu_item_layout, tags);
                ListView myIgnore = (ListView) findViewById(android.R.id.list);
                myIgnore.setAdapter(list);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ignore_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
