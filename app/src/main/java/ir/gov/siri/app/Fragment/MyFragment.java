package ir.gov.siri.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.gov.siri.app.R;

public class MyFragment extends Fragment {

    public  static final  String EXTRA_TEXT="text";

    public String extra;

    float x=0;
    float y=0;
    public static  MyFragment getInstance(String text)
    {
       MyFragment myFragment= new MyFragment(text);

       Bundle bundle=new Bundle();
       bundle.putString(EXTRA_TEXT,text);

        myFragment.setArguments(bundle);

        return myFragment;
    }

    public MyFragment(String extra) {
        this.extra = extra;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragmet,null);


        final TextView textView=view.findViewById(R.id.tv_fragment_name);
        if(getArguments()!=null)
        textView.setText( getArguments().getString(EXTRA_TEXT,""));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(0xff0000ff);
            }
        });


        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FrameLayout.LayoutParams param=(FrameLayout.LayoutParams)v.getLayoutParams();
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    x=  event.getRawX()-param.leftMargin;
                    y=event.getRawY()-param.topMargin;
                }else if(event.getAction()==MotionEvent.ACTION_MOVE)
                {

                    param.leftMargin= (int)(event.getRawX()-x);
                   param.topMargin= (int)(event.getRawY()-y);
                   v.setLayoutParams(param);
                }else if(event.getAction()==MotionEvent.ACTION_UP)
                {

                }



                return false;
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {

       super.onDestroyView();
    }
}
