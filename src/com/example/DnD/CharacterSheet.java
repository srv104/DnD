package com.example.DnD;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.SQLTutorial.R;


/**
 * Created by marc on 1/30/14.
 */
public class CharacterSheet extends Activity{
    private CharacterDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charactersheet);

        datasource = new CharacterDataSource(this);
        datasource.open();
    }

    // of the buttons in main.xml
    public void onClick (View view){

        Character character = null;

        switch (view.getId()) {
            case R.id.btnSave:

                EditText name=(EditText)findViewById(R.id.editTxtName);
                EditText cClass=(EditText)findViewById(R.id.editTxtClass);


            // save the new character to the database
            character = datasource.createCharacter(name.getText().toString(), cClass.getText().toString() );


            break;

        case R.id.btnDelete:


            break;

        case R.id.btnInv:

            break;
    }


}
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
