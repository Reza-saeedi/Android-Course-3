package ir.gov.siri.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.gov.siri.app.Contact.Contact;
import ir.gov.siri.app.Contact.ContactAdapter;
import ir.gov.siri.app.Contact.ContactDelegate;
import ir.gov.siri.app.Contact.ContactManager;

public class CoordinatorActivity extends AppCompatActivity implements ContactDelegate {


    RecyclerView contactList;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactList=findViewById(R.id.rv_coordinator);
        contactList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ContactManager contactManager=new ContactManager();
        List<Contact> contacts= contactManager.getContacts();

        contactAdapter=new ContactAdapter(contacts,this);
        contactList.setAdapter(contactAdapter);

        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,1);
        // linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        contactList.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onClick(View v, Contact contact) {

    }

    @Override
    public boolean onLongClick(View v, Contact contact) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
