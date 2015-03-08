package com.tiramisu.asthraappmk2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;


public class RegistrationActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{
    Spinner courseSpinner;  // A spinner is a dropdown list. You can retrieve its value in the OnItemSelectedMethod
    Spinner deptSpinner;
    ArrayAdapter courseAdapter;
    ArrayAdapter deptAdapter;
    EditText studName, studCollege, studEmail, studYear, studPhone,studTeam,studTeam_member1,studTeam_member2,studTeam_member3,studTeam_member4;
    Button submitButton;
    LinearLayout teamContainer;
    String eventId, eventName, eventDescription, eventBranch, eventTime;
    String courseSpinnerText, deptSpinnerText;
    Boolean eventSpot, eventTeam;
    int eventDay, eventPosterId, success;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //private static final String REG_URL="http://server.heyteam.me/SJCET/asthra-reg.php";
    private static final String REG_URL="http://server.asthra2015.com/asthra-reg.php";
    private static final String TAG_SUCCESS = "success";

    TelephonyManager telephonyManager;
    String uid;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        super.onBaseCreate("Registration");

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        uid = telephonyManager.getDeviceId();

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

        /*TODO: The button is made to be invisible, to be replaced by action bar button, remove on production release*/
        submitButton = (Button) findViewById(R.id.reg_button); //Set an OnClicklistener for this

        courseSpinner.setAdapter(courseAdapter);
        deptSpinner.setAdapter(deptAdapter);
        courseSpinner.setOnItemSelectedListener(this);
        deptSpinner.setOnItemSelectedListener(this);

        studTeam = (EditText) findViewById(R.id.team_name);

        /*
        *Note: Dont get confused by Team member (unmber 0 in layout and the variable declared here!
        * sry, I know i goof up in the Naming system, what makes sence there didnt here, apparently .. lol
        *
        * */
        studTeam_member1= (EditText) findViewById(R.id.team_member2);
        studTeam_member2= (EditText) findViewById(R.id.team_member3);
        studTeam_member3= (EditText) findViewById(R.id.team_member4);
        studTeam_member4= (EditText) findViewById(R.id.team_member5);



        eventId = intent.getStringExtra("eventId");              //This is the unique id for identifying the event in the database. The one we talked about.
        eventName = intent.getStringExtra("eventName");
        eventDescription = intent.getStringExtra("eventDescription");
        eventBranch = intent.getStringExtra("eventBranch");
        eventTime = intent.getStringExtra("eventTime");
        eventTeam = intent.getBooleanExtra("eventTeam", true);
        eventSpot = intent.getBooleanExtra("eventSpot", false);
        eventDay = intent.getIntExtra("eventDay", 1);
        eventPosterId = intent.getIntExtra("eventPosterId", R.drawable.csgo);
        toolbar.setTitle(eventName+" Registration");

        if(eventId.equals("LD")){
            eventName = uid;
        }

        /*To Hide the Team Section if not Group Event!*/
        teamContainer = (LinearLayout) findViewById(R.id.TeamLayout);
        teamContainer.setVisibility(View.VISIBLE);

        //TODO: There is  some problem here. the team menu does not always work out !
        //Fixed this - Ajay Thomas
        if(!eventTeam) {
            teamContainer.setVisibility(View.GONE);
        }



    }

    public void doReg(){

        //Doing some Varification here!
        if(studName.getText().toString().isEmpty() || studCollege.getText().toString().isEmpty() ||  studEmail.getText().toString().isEmpty() || studPhone.getText().toString().isEmpty() || studYear.getText().toString().isEmpty() )
        {
            Toast.makeText(this,"Please Fill in all the fields !",Toast.LENGTH_SHORT).show();
            //Note: We will go into Detailed validation for Later!
           if(studName.getText().toString().isEmpty()){ studName.setError("Enter Your Full Name");}
           if(studCollege.getText().toString().isEmpty()){ studCollege.setError("Enter College Name");}
           if(studEmail.getText().toString().isEmpty()){  studEmail.setError("Enter a valid Email ID");}
           if(studPhone.getText().toString().isEmpty()){  studPhone.setError("Enter your Mobile number");}
           if(studYear.getText().toString().isEmpty()){ studYear.setError("Enter your current Semester or Year");}


        }
        else if(eventTeam && ( studTeam.getText().toString().isEmpty() ||studTeam_member1.getText().toString().isEmpty()))
        {
            Toast.makeText(this,"The Team Name and at least one member name is required  !",Toast.LENGTH_SHORT).show();
            if(studTeam.getText().toString().isEmpty()){studTeam.setError("Choose a name for your team");}
            if(studTeam_member1.getText().toString().isEmpty()){studTeam_member1.setError("Enter name of team member");}
        }
        else
        {
            //Removing Error symbols from feilds!
            studName.setError(null);
            studCollege.setError(null);
            studEmail.setError(null);
            studPhone.setError(null);
            studYear.setError(null);
            studTeam.setError(null);
            studTeam_member1.setError(null);



            //TODO: Get VAlue from spinners... using dummy values for now!
            //TODO #2: I am Passing a lot of null values as args, like that of the members. Its dangerous, we need to look into this later on:

            new AttemptReg().execute(eventId, eventName, studName.getText().toString(), studCollege.getText().toString(), courseSpinnerText, deptSpinnerText, studEmail.getText().toString(),
                    studPhone.getText().toString(), studYear.getText().toString(), String.valueOf(eventTeam), studTeam.getText().toString(),
                    studTeam_member1.getText().toString(), studTeam_member2.getText().toString(), studTeam_member3.getText().toString(), studTeam_member4.getText().toString());
        }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO this might cause problems!!!

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        MenuItem item = menu.add(Menu.NONE, R.id.action_donereg,Menu.NONE,R.string.done);
        item.setIcon(R.drawable.ic_done_white_24dp);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_donereg) {
            if(isNetworkAvailable()){
                doReg();
            }
            else
                Toast.makeText(this,"Check your Internet connection!", Toast.LENGTH_SHORT).show();
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

        if (parent.getId() == R.id.reg_department)
            deptSpinnerText = deptSpinner.getSelectedItem().toString();             //Value in the Department Dropdown list

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

/*
* Look, I know We need to make a separate class for the async system! well.. yeah we can do that,
* but since this is the only point where we need http
* //TODO : We need a Seperate Class!
* */

/*Behold! my big arse Async Code*/
/**************************************************************************************************************/

    class AttemptReg extends AsyncTask<String, String, String>
    {
        @Override protected void onPreExecute() {
            Log.d("Inside AttemptReg()", "OnPreExec start");
            super.onPreExecute();
            Log.d("Inside AttemptReg()", "OnPreExec super");
            pDialog = new ProgressDialog(RegistrationActivity.this);
            Log.d("Inside AttemptReg()", "OnPreExec Dialog Made");
            pDialog.setMessage("Registration: Getting you in...");
            //pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

            Log.d("Inside AttemptReg()", "OnPreExec Completed");
        }


        @Override protected String doInBackground(String... args)
        {

        /*  here Check for success tag */
            Log.d("Background AttemptReg()", "Background Starting");

            String msg = "0";
            Log.d("Background AttemptReg()", "Creating Input Objects ");


            Log.d("Background AttemptReg()", "String to store obj data");

            String EventID_Asyncdata = args[0];
            String EventName_Asyncdata = args[1];
            String name_Asyncdata = args[2];
            String college_Asyncdata = args[3];
            String course_Asyncdata = args[4];
            String dept_Asyncdata = args[5];
            String email_Asyncdata = args[6];
            String mob_Asyncdata = args[7];
            String year_Asyncdata = args[8];
            String teamEvent_Asyncdata = args[9];
            String team_name_Asyncdata = args[10];
            String member1_Asyncdata = args[11];
            String member2_Asyncdata = args[12];
            String member3_Asyncdata = args[13];
            String member4_Asyncdata = args[14];
            //Log.d("Team", member1_Asyncdata + member2_Asyncdata + member3_Asyncdata +member4_Asyncdata);







            //TODO Debug: Remove this thing after testing!!!
            Log.d("AttemptReg() Params are",EventID_Asyncdata+" "+EventName_Asyncdata+" "+name_Asyncdata+" "+college_Asyncdata+" "+course_Asyncdata+" "+dept_Asyncdata+" "+
            email_Asyncdata+" "+mob_Asyncdata+" "+year_Asyncdata+" "+teamEvent_Asyncdata);

            Log.d("Background AttemptReg()", "Going to Try Block");


            try {


                List<NameValuePair> params = new ArrayList<>();

                Log.d("AttemptReg() Background", "Try Block: Adding Params");

                params.add(new BasicNameValuePair("EventID", EventID_Asyncdata));
                params.add(new BasicNameValuePair("EventName", EventName_Asyncdata));
                params.add(new BasicNameValuePair("Name", name_Asyncdata));
                params.add(new BasicNameValuePair("College", college_Asyncdata));
                params.add(new BasicNameValuePair("Course", course_Asyncdata));
                params.add(new BasicNameValuePair("Dept", dept_Asyncdata));
                params.add(new BasicNameValuePair("Email", email_Asyncdata));
                params.add(new BasicNameValuePair("Mob", mob_Asyncdata));
                params.add(new BasicNameValuePair("Year", year_Asyncdata));
                params.add(new BasicNameValuePair("TeamEvent", teamEvent_Asyncdata));

         /*If its a team event we add extra Params*/
         //Hope this works!!
          if(teamEvent_Asyncdata.equals("true")) {

              params.add(new BasicNameValuePair("TeamName", team_name_Asyncdata));


                /*
                *
                *
                * We check if all the member feilds are entered.
                * look, i know its an inefficet and stupid way to do it,
                * but yeah, we do it for simplicity!!
                *
                * */
              if (member1_Asyncdata != null && !member1_Asyncdata.isEmpty()) {
                  params.add(new BasicNameValuePair("Member1", member1_Asyncdata));
              }
              if (member2_Asyncdata != null && !member2_Asyncdata.isEmpty()) {
                  params.add(new BasicNameValuePair("Member2", member2_Asyncdata));
              }
              if (member3_Asyncdata != null && !member3_Asyncdata.isEmpty()) {
                  params.add(new BasicNameValuePair("Member3", member3_Asyncdata));
              }
              if (member4_Asyncdata != null && !member4_Asyncdata.isEmpty()) {
                  params.add(new BasicNameValuePair("Member4", member4_Asyncdata));
              }

          }
                Log.d("JSON request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest( REG_URL,  params);
                success = json.getInt(TAG_SUCCESS);



                if (success == 1) {
                    Log.d("Successfully Login!", "");
                    //msg = "You Have Successfully Registered for " + EventName_Asyncdata;
                    /*For the lucky draw, eventName gets changed to device ID and as a result EventName_Asyncdata.
                    * So, I'm passing the getStringExtra intent directly.*/
                    msg = "You Have Successfully Registered for " + getIntent().getStringExtra("eventName");
                    return msg;
                } else {
                    //TODO: We could add responses including Registration Closed at this point!!!
                    msg = "Registration Failed!";
                    return msg;
                }
            }
            catch (JSONException jexp)
            {
                jexp.printStackTrace();
            }




            return msg;
        }


        @Override protected void onPostExecute(String result){


            pDialog.dismiss();

            Toast.makeText(getApplicationContext(),result , Toast.LENGTH_LONG).show();

         //TODO: Very Important , create a dialog box saying the Reg ID of the person returned from the server! to be implimented.

            finish();

        }





    }



    /***************************************************************************************************************/

}
