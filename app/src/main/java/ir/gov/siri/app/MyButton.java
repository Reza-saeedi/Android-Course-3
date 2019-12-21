package ir.gov.siri.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray= context.obtainStyledAttributes(attrs,R.styleable.MyButton,0,0);
        setText( typedArray.getString(R.styleable.MyButton_count));
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray= context.obtainStyledAttributes(attrs,R.styleable.MyButton,0,0);
        setText( typedArray.getString(R.styleable.MyButton_count));
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        //setText("test");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
