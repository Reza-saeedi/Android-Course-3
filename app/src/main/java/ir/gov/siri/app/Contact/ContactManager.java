package ir.gov.siri.app.Contact;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import ir.gov.siri.app.ApplicationLoader;
import ir.gov.siri.app.DatabaseManager.ContactDBHelper;
import ir.gov.siri.app.Rest.ContactService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactManager {


    public void getContacts(ContactManagerDelegate contactManagerDelegate) {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1L, "ali", "aliF", 1, "09122222211"));
        contacts.add(new Contact(1L, "reza", "rezaF", 1, "09122222233"));
        contacts.add(new Contact(1L, "hasan", "hasani", 1, "09122222244"));
        contacts.add(new Contact(1L, "amir", "amiri", 1, "09122222255"));

        contacts.add(new Contact(1L, "ali", "aliF", 1, "09122222211"));
        contacts.add(new Contact(1L, "reza", "rezaF", 1, "09122222233"));
        contacts.add(new Contact(1L, "hasan", "hasani", 1, "09122222244"));
        contacts.add(new Contact(1L, "amir", "amiri", 1, "09122222255"));
        contactManagerDelegate.onDataLoaded(contacts);
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1L, "ali", "aliF", 1, "09122222211"));
        contacts.add(new Contact(1L, "reza", "rezaF", 1, "09122222233"));
        contacts.add(new Contact(1L, "hasan", "hasani", 1, "09122222244"));
        contacts.add(new Contact(1L, "amir", "amiri", 1, "09122222255"));
        contacts.add(new Contact(1L, "ali", "aliF", 1, "09122222211"));
        contacts.add(new Contact(1L, "reza", "rezaF", 1, "09122222233"));
        contacts.add(new Contact(1L, "hasan", "hasani", 1, "09122222244"));
        contacts.add(new Contact(1L, "amir", "amiri", 1, "09122222255"));
        return (contacts);
    }

    public List<Contact> getContacts(String search) {
        List<Contact> contacts = new ArrayList<>();
        if (search.length() % 2 == 0) {
            contacts.add(new Contact(1L, "ali", "aliF", 1, "09122222211"));
            contacts.add(new Contact(1L, "reza", "rezaF", 1, "09122222233"));
            contacts.add(new Contact(1L, "hasan", "hasani", 1, "09122222244"));
            contacts.add(new Contact(1L, "amir", "amiri", 1, "09122222255"));
        }

        contacts.add(new Contact(1L, "ali", "aliF", 1, "09122222211"));
        contacts.add(new Contact(1L, "reza", "rezaF", 1, "09122222233"));
        contacts.add(new Contact(1L, "hasan", "hasani", 1, "09122222244"));
        contacts.add(new Contact(1L, "amir", "amiri", 1, "09122222255"));
        return contacts;
    }

    public List<Contact> getContactFromNetwork(final ContactManagerDelegate delegate, final Context context) {
        final List<Contact> contacts = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContactService service = retrofit.create(ContactService.class);

        Call<List<Contact>> call = service.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                delegate.onDataLoaded(response.body());

                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        SharedPreferences preferences = context.getSharedPreferences("SecureData", Context.MODE_PRIVATE);

                        if (preferences.getBoolean("enableORM", false)) {
                            insertContactORM((response.body().get(i)));
                        } else {
                            Contact contact=response.body().get(i);

                            ContactDBHelper.getInstance(context).insertContact(response.body().get(i));
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                delegate.onDataLoaded(null);
            }
        });
        return contacts;
    }


    public void getContactFromDB(ContactManagerDelegate delegate, Context context) {

        List<Contact> contacts = null;

        SharedPreferences preferences = context.getSharedPreferences("SecureData", Context.MODE_PRIVATE);

        if (preferences.getBoolean("enableORM", false)) {
            contacts = getContactFromORM();
        } else {
            ContactDBHelper.getInstance(context).deleteContact(0);
            contacts = ContactDBHelper.getInstance(context).selectContact();
        }


        if (contacts == null || contacts.size()==0) {
            getContactFromNetwork(delegate, context);
            return;
        }
        delegate.onDataLoaded(contacts);
    }

    public List<Contact> getContactFromORM() {
        DaoSession daoSession = ApplicationLoader.getDaoSession();
        ContactDao contactDao = daoSession.getContactDao();

        return  contactDao.loadAll();

        /*return contactDao.queryBuilder()
                .where(ContactDao.Properties.Name.eq("Joe"))
                .orderAsc(ContactDao.Properties.Phone)
                .list();*/
    }

    public void insertContactORM(Contact contact) {

        DaoSession daoSession = ApplicationLoader.getDaoSession();
        ContactDao contactDao = daoSession.getContactDao();



        ImageUrlDao imageUrlDao = daoSession.getImageUrlDao();
        imageUrlDao.insert(contact.getImageUrl());

        contact.setImageId(contact.getImageUrl().getId());
        contactDao.insert(contact);
    }


    public void updateContactORM(Contact contact) {

        DaoSession daoSession = ApplicationLoader.getDaoSession();
        ContactDao contactORMDao = daoSession.getContactDao();
        contactORMDao.update(contact);
    }


    public ArrayList<Contact> getContactFromProvider(Context context)
    {
        ArrayList<Contact> contactArrayList=new ArrayList<>();

        Cursor cursor= context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext())
        {
            Contact contact=new Contact();
            String name=cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
            String id=cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
            Cursor phoneCursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"= ?",new String[]{id},null);
            StringBuilder phones=new StringBuilder();
            while (phoneCursor.moveToNext())
            {
                String phone=phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phones.append(phone);
                phones.append("\n");
            }
            contact.setPhone(phones.toString());
            contact.setName(name);
            contactArrayList.add(contact);
        }

        return contactArrayList;
    }


}
