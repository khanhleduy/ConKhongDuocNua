package l.com.ldk.duykhanh.conkhongduocnua.COURSE.LOGIN;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.ACTIVITY.MainActivity;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_User;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sdf;
    EditText edtEmail, edtPass;
    String mUser, mPass;
    CheckBox chkRememberPass;
    DAO_User daoUser;
    String strUserName, strPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sdf = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);
        chkRememberPass = findViewById(R.id.chkLuu);


        edtEmail.setText(sdf.getString("USERNAME", ""));
        edtPass.setText(sdf.getString("PASSWORD", ""));
        chkRememberPass.setChecked(sdf.getBoolean("REMEMBER", false));
        daoUser = new DAO_User(LoginActivity.this);

        if(checkLoginShap() > 0){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);

        }

    }

    public void btnLogin(View view){
        mUser = edtEmail.getText().toString();
        mPass = edtPass.getText().toString();

        if(mUser.isEmpty()||mPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }
        else{
            if(daoUser.checkLogin(mUser,mPass)>0){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                rememberUser(mUser, mPass, chkRememberPass.isChecked());
                Toast.makeText(getApplicationContext(), "Đang nhập thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
            else if (mUser.equals("admin") && mPass.equals("admin")) {
                rememberUser(mUser, mPass, chkRememberPass.isChecked());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không chính xac", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnRegistration(View view){
        Intent intentDK = new Intent(this,SingUpActivity.class);
        startActivity(intentDK);

    }

    public void rememberUser (String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            edit.clear();
        }
        else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.apply();
    }

    public int checkLoginShap(){
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = preferences.getBoolean("REMEMBER",false);
        if(chk){
            strUserName = preferences.getString("USERNAME","");
            strPassword = preferences.getString("PASSWORD","");
            return 1;
        }
        return -1;
    }
}

