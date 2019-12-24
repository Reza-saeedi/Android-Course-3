package ir.gov.siri.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    public  static final  String EXTRA_TEXT="text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String text="null";
        if(getIntent().hasExtra(EXTRA_TEXT))
          text=getIntent().getStringExtra(EXTRA_TEXT);

        Log.e("MyApp",text);


        setContentView(R.layout.splash_screen);

        ImageView imageView=findViewById(R.id.iv_splash);

        ScaleAnimation scaleAnimation=new ScaleAnimation(1,1,0,1, Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,1);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scaleAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1000);
       // alphaAnimation.setStartOffset(2000);

       /* imageView.setAnimation(scaleAnimation);
        imageView.setAnimation(alphaAnimation);*/

        AnimationSet animationSet=new AnimationSet(true);
        animationSet.setDuration(3000);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation( alphaAnimation);


        imageView.setAnimation(animationSet);


        //AnimationUtils.loadAnimation(this,R.anim.open_animation);
        //imageView.setAnimation(  AnimationUtils.loadAnimation(this,R.anim.open_animation));

        /*ObjectAnimator animator= ObjectAnimator.ofFloat(imageView,"alpha",0,1);
        animator.setDuration(2000);
        animator.start();*/




    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
