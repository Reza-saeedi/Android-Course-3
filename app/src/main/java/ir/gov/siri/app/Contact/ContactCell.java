package ir.gov.siri.app.Contact;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.gov.siri.app.R;

public class ContactCell extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textviewPhone;
    ImageView imageView;
    View parent;
    public ContactCell(@NonNull View itemView) {
        super(itemView);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        textViewName= itemView.findViewById(R.id.tv_name);
        textviewPhone= itemView.findViewById(R.id.tv_phone);
        imageView=itemView.findViewById(R.id.iv_contact);
        parent=itemView;

    }

    public void setTag(String tag)
    {
        parent.setTag(tag);
    }

    public void setName(String text)
    {
        textViewName.setText(text);
    }

    public void setPhone(String text)
    {
        textviewPhone.setText(text);
    }
}
