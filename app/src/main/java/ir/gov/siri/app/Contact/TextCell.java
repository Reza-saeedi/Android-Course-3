package ir.gov.siri.app.Contact;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextCell extends RecyclerView.ViewHolder {
    TextView textView;
    public TextCell(@NonNull View itemView) {
        super(itemView);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
        textView=(TextView) itemView;

    }

    public void setText(String text)
    {
        textView.setText(text);
    }
}