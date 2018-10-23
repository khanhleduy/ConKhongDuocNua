package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ACTIVITY;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import l.com.ldk.duykhanh.conkhongduocnua.R;

public class RegistrationMonActivity extends AppCompatActivity {

    private TextView txtTen,txtMa,txtMon,txtSDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_mon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtTen = findViewById(R.id.txtTenSV);
        txtMa = findViewById(R.id.txtMSSVDT);
        txtMon = findViewById(R.id.txtMonDT);
        txtSDT = findViewById(R.id.txtSDTDT);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        txtTen.setText(bundle.getString("TENSINHVIEN"));
        txtMa.setText(bundle.getString("MASINHVIEN"));
        txtMon.setText(bundle.getString("MONHOC"));
        txtSDT.setText(bundle.getString("SODIENTHOAI"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
