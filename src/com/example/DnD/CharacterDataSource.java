package com.example.DnD;

/**
 * Created by mkorte on 1/30/14.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CharacterDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_CLASS };

    public CharacterDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Character createCharacter(String name, String cClass) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_CLASS, cClass);

        long insertId = database.insert(MySQLiteHelper.TABLE_CHARACTERS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_CHARACTERS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Character newCharacter = cursorToCharacter(cursor);
        cursor.close();
        return newCharacter;
    }

    public void deleteCharacter(Character character) {
        long id = character.getId();
        System.out.println("Character deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_CHARACTERS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<Character>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CHARACTERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Character character = cursorToCharacter(cursor);
            characters.add(character);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return characters;
    }

    private Character cursorToCharacter(Cursor cursor) {
        Character character = new Character();
        character.setId(cursor.getLong(0));
        character.setName(cursor.getString(1));
        character.setcClass(cursor.getString(2));
        return character;
    }
}