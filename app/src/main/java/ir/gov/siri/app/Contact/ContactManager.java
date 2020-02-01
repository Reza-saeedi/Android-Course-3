package ir.gov.siri.app.Contact;

import android.content.Context;

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


    public void getContacts(ContactManagerDelegate contactManagerDelegate)
    {
      List<Contact> contacts=new ArrayList<>();
      contacts.add(new Contact("ali","aliF","09122222211"));
      contacts.add(new Contact("reza","rezaF","09122222233"));
      contacts.add(new Contact("hasan","hasani","09122222244"));
      contacts.add(new Contact("amir","amiri","09122222255"));

        contacts.add(new Contact("ali","aliF","09122222211"));
        contacts.add(new Contact("reza","rezaF","09122222233"));
        contacts.add(new Contact("hasan","hasani","09122222244"));
        contacts.add(new Contact("amir","amiri","09122222255"));
        contactManagerDelegate.onDataLoaded(contacts);
    }

    public  List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("ali", "aliF", "09122222211"));
        contacts.add(new Contact("reza", "rezaF", "09122222233"));
        contacts.add(new Contact("hasan", "hasani", "09122222244"));
        contacts.add(new Contact("amir", "amiri", "09122222255"));

        contacts.add(new Contact("ali", "aliF", "09122222211"));
        contacts.add(new Contact("reza", "rezaF", "09122222233"));
        contacts.add(new Contact("hasan", "hasani", "09122222244"));
        contacts.add(new Contact("amir", "amiri", "09122222255"));
        return (contacts);
    }



        public List<Contact> getContactFromNetwork(final ContactManagerDelegate delegate, final Context context)
    {
        final List<Contact> contacts=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContactService service = retrofit.create(ContactService.class);

        Call<List<Contact>> call=service.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                delegate.onDataLoaded(response.body());

                if( response.body()!=null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ContactDBHelper.getInstance(context).insertContact(response.body().get(i));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                delegate.onDataLoaded(null);
            }
        });
        return  contacts;
    }


    public List<Contact> getContacts(String search)
    {
        List<Contact> contacts=new ArrayList<>();
        if(search.length()%2==0) {
            contacts.add(new Contact("ali", "aliF", "09122222211"));
            contacts.add(new Contact("reza", "rezaF", "09122222233"));
            contacts.add(new Contact("hasan", "hasani", "09122222244"));
            contacts.add(new Contact("amir", "amiri", "09122222255"));
        }

        contacts.add(new Contact("ali","aliF","09122222211"));
        contacts.add(new Contact("reza","rezaF","09122222233"));
        contacts.add(new Contact("hasan","hasani","09122222244"));
        contacts.add(new Contact("amir","amiri","09122222255"));
        return contacts;
    }


    public void getContactFromDB(ContactManagerDelegate delegate,Context context)
    {
        ContactDBHelper.getInstance(context).deleteContact(0);
        List<Contact> contacts=ContactDBHelper.getInstance(context).selectContact();
        if(contacts==null) {
            getContactFromNetwork(delegate, context);
            return;
        }
        delegate.onDataLoaded(contacts);
    }

    public void getContactFromORM(Context context)
    {
        DaoSession daoSession = ApplicationLoader.getDaoSession();
        ContactORMDao contactORMDao = daoSession.getContactORMDao();
        List<ContactORM> joes = contactORMDao.queryBuilder()
                .where(ContactORMDao.Properties.Name.eq("Joe"))
                .orderAsc(ContactORMDao.Properties.Phone)
                .list();
    }

    public void insertContactORM(ContactORM contact)
    {

        DaoSession daoSession = ApplicationLoader.getDaoSession();
        ContactORMDao contactORMDao = daoSession.getContactORMDao();
        contactORMDao.insert(contact);
    }


    public void updateContactORM(ContactORM contact)
    {

        DaoSession daoSession = ApplicationLoader.getDaoSession();
        ContactORMDao contactORMDao = daoSession.getContactORMDao();
        contactORMDao.update(contact);
    }


}
