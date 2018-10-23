package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ACTIVITY;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import l.com.ldk.duykhanh.conkhongduocnua.R;

public class IntroduceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
    }
    public void btnBack(View view) {
        finish();
    }
}
