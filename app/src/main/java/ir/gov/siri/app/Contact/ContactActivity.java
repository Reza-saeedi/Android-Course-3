package ir.gov.siri.app.Contact;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ir.gov.siri.app.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView contactList=findViewById(R.id.rv_contact);

        ContactAdapter contactAdapter=new ContactAdapter();
        contactList.setAdapter(contactAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        contactList.setLayoutManager(linearLayoutManager);
    }
}
