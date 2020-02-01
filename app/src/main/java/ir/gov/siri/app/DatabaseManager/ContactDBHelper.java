package ir.gov.siri.app.DatabaseManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.gov.siri.app.Contact.Contact;

public class ContactDBHelper {

    static  SqliteHelper sqliteHelper;
    SQLiteDatabase database;

    public static ContactDBHelper getInstance(Context context) {
        sqliteHelper=new SqliteHelper(context);
        return new ContactDBHelper();
    }

    public List<Contact> selectContact() {
        openDatabase();
        Cursor cursor=database.query(sqliteHelper.ContactTable,null,null,null,null,null,null);
        //database.rawQuery("select * from "+sqliteHelper.ContactTable+";",null);
        int count=cursor.getCount();
        cursor.moveToFirst();
        List<Contact> contacts=new ArrayList<>();
        while (!cursor.isAfterLast())
        {
            Contact contact=new Contact();

            contact.setName( cursor.getString(cursor.getColumnIndex(sqliteHelper.ContactName)));
            contact.setPhone( cursor.getString(cursor.getColumnIndex(sqliteHelper.ContactPhone)));

            contact.setUrl( cursor.getString(cursor.getColumnIndex(sqliteHelper.ContactUrl)));
            contacts.add(contact);

            cursor.moveToNext();
        }

        closeDatabase();
        return  contacts;
    }

    public void insertContact(Contact contact)
    {
        openDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(sqliteHelper.ContactName, contact.getName());
        contentValues.put(sqliteHelper.ContactUrl, contact.getUrl().getSmall());
        contentValues.put(sqliteHelper.ContactPhone, contact.getPhone());

        database.insert(sqliteHelper.ContactTable,null,contentValues);

        closeDatabase();
    }


    public void deleteContact(int id)
    {
        openDatabase();

        database.delete(sqliteHelper.ContactTable,"name=?",new String[]{"The Finest Hours"});

        closeDatabase();
    }




    private void openDatabase() {
        database= sqliteHelper.getWritableDatabase();

    }

    private void closeDatabase()
    {
        database.close();
    }
}
