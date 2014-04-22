package com.example.DnD;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import com.example.SQLTutorial.R;

import java.util.List;

/**
 * Created by marc on 1/30/14.
 */
public class LoadList extends ListActivity {
    private CharacterDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        datasource = new CharacterDataSource(this);
        datasource.open();

        List<Character> values = datasource.getAllCharacters();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        //load selected character

    }
    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Character> adapter = (ArrayAdapter<Character>) getListAdapter();
        Character character = null;

        adapter.notifyDataSetChanged();
    }
    public void loadCharacter(View view){

        if (getListAdapter().getCount() > 0) {
            character = (Character) getListAdapter().getItem(0);
            datasource.deleteCharacter(character);
            adapter.remove(character);
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
