package ir.gov.siri.app.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.gov.siri.app.Contact.Contact;
import ir.gov.siri.app.Contact.ContactDelegate;
import ir.gov.siri.app.R;

public class BazaarViewpager2Adapter extends RecyclerView.Adapter {

    List<Contact> contacts;
    ContactDelegate delegate;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void deleteContacts(Contact contact) {
        this.contacts.remove(contact);
    }

    public  BazaarViewpager2Adapter(List<Contact> contacts, ContactDelegate delegate)
    {
        this.contacts=contacts;
        this.delegate=delegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_contact, parent, false);



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onClick(v,contacts.get(Integer.parseInt((String)v.getTag())));
                v.getTag();
                //Toast.makeText(v.getContext(),"setOnClickListener"+v.getTag(),Toast.LENGTH_LONG)  .show();
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return delegate.onLongClick(v,contacts.get(Integer.parseInt((String)v.getTag())));
            }
        });
        return new Viewpager2Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewpager2Item viewpager2Item = (Viewpager2Item) holder;
        viewpager2Item.setTag(position + "");
        viewpager2Item.setName(contacts.get(position).getName()+" "+contacts.get(position).getFamily());
        viewpager2Item.setPhone(contacts.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public int getItemViewType(int position) {

        return 0;
    }
}
