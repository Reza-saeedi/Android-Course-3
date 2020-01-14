package ir.gov.siri.app;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        WebView webView=findViewById(R.id.wv_browser);
       // webView.loadData("<html><body> Hello Browser</body></html>","text/html","UTF-8");
      //  webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://media.kasperskydaily.com/wp-content/uploads/sites/92/2019/12/09084248/android-device-identifiers-featured.jpg");
    }
}
