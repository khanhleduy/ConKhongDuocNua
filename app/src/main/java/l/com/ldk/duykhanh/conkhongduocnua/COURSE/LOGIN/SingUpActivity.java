package l.com.ldk.duykhanh.conkhongduocnua.COURSE.LOGIN;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_User;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.User;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class SingUpActivity extends AppCompatActivity {

    private EditText edtUser, edtPass, edtRePass;
    DAO_User daoUser;
    private Button btnRegistrationDK;
    private String TAG = "SingUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        btnRegistrationDK = findViewById(R.id.btnRegistrationDK);
        edtUser = findViewById(R.id.edtEmailDK);
        edtPass = findViewById(R.id.edtPasswordDK);
        edtRePass = findViewById(R.id.edtRePasswordDK);
        btnRegistrationDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoUser = new DAO_User(SingUpActivity.this);
                User user = new User(edtUser.getText().toString(), edtPass.getText().toString());


                try {
                    if (validateFrom() > 0) {
                        if (daoUser.inserUser(user) > 0) {
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }



    public void imgBack(View view) {
        finish();
    }

    public int validateFrom() {
        int check = 1;
        if (edtUser.getText().length() == 0 || edtPass.getText().length() == 0 || edtRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPass.getText().toString();
            String rePass = edtRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mậ khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
