package ir.gov.siri.app.Contact;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import ir.gov.siri.app.R;

public class ContactActivity extends AppCompatActivity implements ContactDelegate,ContactManagerDelegate{
    RecyclerView contactList;
    ContactAdapter contactAdapter;

    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        swipeRefreshLayout=findViewById(R.id.srl_contact);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ContactManager contactManager=new ContactManager();
                contactManager.getContactFromDB(ContactActivity.this,ContactActivity.this);

            }
        });
        contactList=findViewById(R.id.rv_contact);
        contactList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ContactManager contactManager=new ContactManager();


       contactManager.getContactFromDB(this,this);





        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,1);
       // linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        contactList.setLayoutManager(linearLayoutManager);


    }



    @Override
    public void onClick( View v,Contact contact) {
        Toast.makeText(v.getContext(),"setOnClickListener"+contact.getFamily(),Toast.LENGTH_LONG)  .show();
    }

    @Override
    public boolean onLongClick(final View v, final Contact contact) {

        startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.contact_action_menu,menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if(item.getItemId()==R.id.delete_contact)
                {
                    contactAdapter.deleteContacts((contact));
                    contactAdapter.notifyItemRemoved(Integer.parseInt((String)v.getTag()));
                    mode.finish();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu,menu);

       MenuItem menuItem= menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(ContactActivity.this,newText,Toast.LENGTH_SHORT).show();
                ContactManager contactManager=new ContactManager();
                List<Contact> contacts= contactManager.getContacts(newText);
                contactAdapter.setContacts(contacts);
                contactAdapter.notifyDataSetChanged();
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDataLoaded(List<Contact> contacts) {
        if(contacts!=null) {
            contactAdapter = new ContactAdapter(contacts, this);
            contactList.setAdapter(contactAdapter);
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
