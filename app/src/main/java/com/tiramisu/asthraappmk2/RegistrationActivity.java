package com.tiramisu.asthraappmk2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class RegistrationActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{
Spinner courseSpinner;
    Spinner deptSpinner;
    ArrayAdapter courseAdapter;
    ArrayAdapter deptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        super.onBaseCreate("Registration");
        courseSpinner = (Spinner) findViewById(R.id.reg_course);
        deptSpinner = (Spinner) findViewById(R.id.reg_department);
        courseAdapter = ArrayAdapter.createFromResource(this, R.array.reg_courses, android.R.layout.simple_spinner_dropdown_item);
        deptAdapter = ArrayAdapter.createFromResource(this, R.array.reg_departments, android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);
        deptSpinner.setAdapter(deptAdapter);
        courseSpinner.setOnItemSelectedListener(this);
        deptSpinner.setOnItemSelectedListener(this);

        //I'll add the rest in the next commit.

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
        if (parent.getId() == R.id.reg_course)
            Toast.makeText(this, "Course", Toast.LENGTH_SHORT).show();
        if (parent.getId() == R.id.reg_department)
            Toast.makeText(this, "Department", Toast.LENGTH_SHORT).show();
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
