package ir.gov.siri.app.Contact;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.gov.siri.app.R;

public class ContactActivity extends AppCompatActivity implements ContactDelegate{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView contactList=findViewById(R.id.rv_contact);
        contactList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ContactManager contactManager=new ContactManager();
        List<Contact> contacts= contactManager.getContacts();

        ContactAdapter contactAdapter=new ContactAdapter(contacts,this);
        contactList.setAdapter(contactAdapter);



        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,1);
        contactList.setLayoutManager(linearLayoutManager);




    }

    @Override
    public void onClick(View v,Contact contact) {
        Toast.makeText(v.getContext(),"setOnClickListener"+contact.getFamily(),Toast.LENGTH_LONG)  .show();
    }
}
