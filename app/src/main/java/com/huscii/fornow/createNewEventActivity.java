package com.huscii.fornow;


        import android.content.Intent;
        import android.app.Activity;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ListView;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.ScrollView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.EditText;

        import com.firebase.client.Firebase;

public class createNewEventActivity extends ActionBarActivity {

    GPSTracker Thegps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thegps = new GPSTracker(createNewEventActivity.this);
        setContentView(R.layout.activity_create_new_event);


        RadioGroup myListOfEventDurations=(RadioGroup) findViewById(R.id.ListOfRadioButtonDurations);

    }

    public void createAnEvent(View v)
    {
        Firebase.setAndroidContext(this);
        Firebase theDataBase = new Firebase("https://fornow.firebaseio.com/events");
        String eventName = "";
        String eventTags = "";
        int startTime = 0;
        int eventDuration = 0;
        String description = "";
        double latitude = 0;
        double longitude = 0;

        RadioGroup myStartTimeGroup =(RadioGroup) findViewById(R.id.startTimeGroup);
        RadioGroup myListOfEventDurations=(RadioGroup) findViewById(R.id.ListOfRadioButtonDurations);

        latitude = Thegps.getLatitude();
        longitude = Thegps.getLongitude();

        int mySelectedRadioButtonStartTimeID = myStartTimeGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonStartTime =(RadioButton) findViewById(mySelectedRadioButtonStartTimeID);

        int mySelectedRadioButtonDurationTimeID = myListOfEventDurations.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonDurationTime =(RadioButton) findViewById(mySelectedRadioButtonDurationTimeID);

        startTime = Integer.parseInt(selectedRadioButtonStartTime.getText().toString());
        eventDuration = Integer.parseInt(selectedRadioButtonDurationTime.getText().toString());

        EditText myEventName =(EditText) findViewById(R.id.titleInput);
        EditText myEventTags =(EditText) findViewById(R.id.tagsInput);
        EditText myEventDescription =(EditText) findViewById(R.id.descriptionInput);





        eventName = myEventName.getText().toString();
        eventTags = myEventTags.getText().toString();
        description = myEventDescription.getText().toString();

        //String title, String tag, String desc, int time, int duration, double lat, double lon)
        EventData theNewEvent = new EventData(eventName,eventTags,description,startTime,eventDuration,latitude,longitude  );

        theDataBase.push().setValue(theNewEvent);
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);
    }

    public void cancelTheCreation(View v)
    {
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_creation, menu);
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
