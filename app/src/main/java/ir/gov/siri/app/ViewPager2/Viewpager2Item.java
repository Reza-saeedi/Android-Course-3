package ir.gov.siri.app.ViewPager2;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.gov.siri.app.R;

public class Viewpager2Item extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textviewPhone;
    ImageView imageView;
    View parent;
    public Viewpager2Item(@NonNull View itemView) {
        super(itemView);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        textViewName= itemView.findViewById(R.id.tv_name);
        textviewPhone= itemView.findViewById(R.id.tv_phone);
        imageView=itemView.findViewById(R.id.iv_contact);
        parent=itemView;

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


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
