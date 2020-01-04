package ir.gov.siri.app.Contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.gov.siri.app.R;

public class ContactAdapter extends RecyclerView.Adapter {

    List<Contact> contacts;
    ContactDelegate delegate;

    public  ContactAdapter(List<Contact> contacts,ContactDelegate delegate)
    {
        this.contacts=contacts;
        this.delegate=delegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_contact, parent, false);
                break;
            case 1:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_contact_r, parent, false);
                break;
            default:
                return new TextCell(textView);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onClick(v,contacts.get(Integer.parseInt((String)v.getTag())));
                v.getTag();
                //Toast.makeText(v.getContext(),"setOnClickListener"+v.getTag(),Toast.LENGTH_LONG)  .show();
            }
        });
        return new ContactCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
            case 1:

                ContactCell contactCell = (ContactCell) holder;
                contactCell.setTag(position + "");
                contactCell.setName(contacts.get(position).getName()+" "+contacts.get(position).getFamily());
                contactCell.setPhone(contacts.get(position).getPhone());
                break;
            default:
                TextCell textCell = (TextCell) holder;
                textCell.setText("item : " + position);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0)
            return 0;
        else if (position % 3 == 1)
            return 1;

        return 2;
    }
}
