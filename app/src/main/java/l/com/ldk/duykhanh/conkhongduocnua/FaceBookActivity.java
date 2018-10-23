package l.com.ldk.duykhanh.conkhongduocnua;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import l.com.ldk.duykhanh.conkhongduocnua.R;

public class FaceBookActivity extends AppCompatActivity {

    private Button btnShareLink,btnSharePhoto,btnShareVideo;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_facebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnShareLink = findViewById(R.id.btnShareLink);


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .build();

                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(content);
                }
            }
        });

        btnSharePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnShareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });

        printKeyHash();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == 1) {


            if (ShareDialog.canShow(ShareLinkContent.class)) {Uri videoFileUri = data.getData();
                ShareVideo video = new ShareVideo.Builder()
                        .setLocalUrl(videoFileUri)
                        .build();
                ShareVideoContent content = new ShareVideoContent.Builder()
                        .setVideo(video)
                        .build();

                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();


                shareDialog.show(linkContent);
            }
        } else {
            // Call callbackManager.onActivityResult to pass login result to the LoginManager via callbackManager.
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
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

