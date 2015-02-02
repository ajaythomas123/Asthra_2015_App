package com.tiramisu.asthraappmk2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{
    Spinner courseSpinner;  // A spinner is a dropdown list. You can retrieve its value in the OnItemSelectedMethod
    Spinner deptSpinner;
    ArrayAdapter courseAdapter;
    ArrayAdapter deptAdapter;
    EditText studName, studCollege, studEmail, studYear, studPhone;
    Button submitButton;
    String eventId, eventName, eventDescription, eventBranch, eventTime;
    String courseSpinnerText, deptSpinnerText;
    Boolean eventSpot, eventTeam;
    int eventDay, eventPosterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        super.onBaseCreate("Registration");
        Intent intent = getIntent();
        studName = (EditText) findViewById(R.id.reg_name);
        studCollege = (EditText) findViewById(R.id.reg_college);
        studEmail = (EditText) findViewById(R.id.reg_email);
        studYear = (EditText) findViewById(R.id.reg_year);
        studPhone = (EditText) findViewById(R.id.reg_phone);
        courseSpinner = (Spinner) findViewById(R.id.reg_course);
        deptSpinner = (Spinner) findViewById(R.id.reg_department);
        courseAdapter = ArrayAdapter.createFromResource(this, R.array.reg_courses, android.R.layout.simple_spinner_dropdown_item);
        deptAdapter = ArrayAdapter.createFromResource(this, R.array.reg_departments, android.R.layout.simple_spinner_dropdown_item);
        submitButton = (Button) findViewById(R.id.reg_button); //Set an OnClicklistener for this
        courseSpinner.setAdapter(courseAdapter);
        deptSpinner.setAdapter(deptAdapter);
        courseSpinner.setOnItemSelectedListener(this);
        deptSpinner.setOnItemSelectedListener(this);




        eventId = intent.getStringExtra("eventId");              //This is the unique id for identifying the event in the database. The one we talked about.
        eventName = intent.getStringExtra("eventName");
        eventDescription = intent.getStringExtra("eventDescription");
        eventBranch = intent.getStringExtra("eventBranch");
        eventTime = intent.getStringExtra("eventTime");
        eventTeam = intent.getBooleanExtra("eventTeam", true);
        eventSpot = intent.getBooleanExtra("eventSpot", false);
        eventDay = intent.getIntExtra("eventDay", 1);
        eventPosterId = intent.getIntExtra("eventPosterId", R.drawable.csgo);
        toolbar.setTitle("Registration - " + eventName);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_REG;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*This is where you can retrieve the values from the dropdown list.*/

        if (parent.getId() == R.id.reg_course)
            courseSpinnerText = courseSpinner.getSelectedItem().toString();         //Value in the Course dropdown list
            Toast.makeText(this, courseSpinnerText, Toast.LENGTH_SHORT).show();
        if (parent.getId() == R.id.reg_department)
            deptSpinnerText = deptSpinner.getSelectedItem().toString();             //Value in the Department Dropdown list
            Toast.makeText(this, deptSpinnerText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    @Override
//    public void onItemSelected(AdapterViewCompat<?> adapterViewCompat, View view, int i, long l) {
//        if(view.getId() == R.id.reg_course){
//            Toast.makeText(this, "Course", Toast.LENGTH_SHORT);
//        }
//        if(view.getId() == R.id.reg_department){
//            Toast.makeText(this, "Department", Toast.LENGTH_SHORT);
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterViewCompat<?> adapterViewCompat) {
//
//    }
}
