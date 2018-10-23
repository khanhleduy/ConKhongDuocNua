package l.com.ldk.duykhanh.conkhongduocnua.NEWS;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import l.com.ldk.duykhanh.conkhongduocnua.R;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView wvData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String titleCategory = getIntent().getStringExtra("CATEGORY");
        getSupportActionBar().setTitle(titleCategory);
        wvData = findViewById(R.id.wvData);
        String url = getIntent().getStringExtra("LINK");
        if (url != null) {
            wvData.loadUrl(url);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}


