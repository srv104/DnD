package com.example.DnD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.SQLTutorial.R;

public class MyActivity extends Activity {
    private CharacterDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick (View view){

        switch (view.getId()) {
            case R.id.btnLoad:

                Intent myIntent = new Intent(view.getContext(), LoadList.class);
                startActivityForResult(myIntent, 0);

                break;

            case R.id.btnNew:

                Intent myIntent2 = new Intent(view.getContext(), CharacterSheet.class);
                startActivityForResult(myIntent2, 0);

                break;
        }

    }


}