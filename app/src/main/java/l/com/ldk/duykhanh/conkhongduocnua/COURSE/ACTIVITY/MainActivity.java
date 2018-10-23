package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ACTIVITY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.LOGIN.LoginActivity;
import l.com.ldk.duykhanh.conkhongduocnua.MapsActivity;
import l.com.ldk.duykhanh.conkhongduocnua.NEWS.CategoryActivity;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgCourse, imgMaps, imgNews, imgSocial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMaps = findViewById(R.id.imgMaps);
        imgNews = findViewById(R.id.imgNews);
        imgSocial = findViewById(R.id.imgSocial);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCourse:
                Intent itCourse = new Intent(MainActivity.this, CourseActivity.class);
                startActivity(itCourse);
                break;
            case R.id.imgMaps:
                Intent itMaps = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(itMaps);
                break;
            case R.id.imgNews:
                Intent itNews = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(itNews);
                break;
            case R.id.imgSocial:
               Intent intent2 = new Intent(MainActivity.this,FacebookActivity.class);
               startActivity(intent2);
                break;
        }
    }

    private void printKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo("emt.dev.androidfbshare",PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest nd = MessageDigest.getInstance("SHA");
                nd.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(nd.digest(),Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itabs:
                Intent itIntro = new Intent(getApplicationContext(), IntroduceActivity.class);
                startActivity(itIntro);
                break;
            case R.id.itlogout:
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.clear();
                edit.commit();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

