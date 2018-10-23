package l.com.ldk.duykhanh.conkhongduocnua.COURSE.FRAGMENT;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.ACTIVITY.RegistrationMonActivity;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.ADAPTER.MonHocAdapter;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Registration;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.RegistrationLearn;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class FragmentRegistration extends Fragment {

    public List<RegistrationLearn> dsDangKy;
    ListView lvDangKy;

    private EditText txtTenDK, txtMSSV, txtSDT, txtTenUp, txtMSSVUp, txtSDTUp, edtThemMon;
    private Button btnDangKyMon, btnThemMon, btnUpdateMon;
    private ImageView imgADDspn;
    Spinner spinner;
    String monHoc = "";
    String monHocUp = "";
    DAO_Registration daoRegistration;
    MonHocAdapter adapter;
    String ma = "";
    String ten = "";
    String sdt = "";
    String m;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_registration);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });
        lvDangKy = view.findViewById(R.id.lvRegistration);
        daoRegistration = new DAO_Registration(getContext());
        dsDangKy = new ArrayList<>();
        dsDangKy = daoRegistration.getALLMonHoc();
        adapter = new MonHocAdapter(getActivity(), dsDangKy);

        lvDangKy.setAdapter(adapter);
        lvDangKy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ma = dsDangKy.get(i).getMaSinhVien();
                ten = dsDangKy.get(i).getTenSinhVien();
                sdt = dsDangKy.get(i).getSoDienThoai();
                m = dsDangKy.get(i).getMonHoc();
                dialogShowUpdate();

            }
        });
        Log.e("onCreateView: ", "ddddd" + monHocUp);


        return view;
    }


    public void dialogShow() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.setContentView(R.layout.dialog_registration);
        dialog.setTitle("Đăng ký môn học");
        dialog.show();
        txtTenDK = dialog.findViewById(R.id.txtTen);
        txtMSSV = dialog.findViewById(R.id.txtMSSV);
        spinner = dialog.findViewById(R.id.spnDK);
        txtSDT = dialog.findViewById(R.id.txtSDT);
        imgADDspn = dialog.findViewById(R.id.imgADDspn);


        imgADDspn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShowThemMon();
            }
        });
        btnDangKyMon = dialog.findViewById(R.id.btnDangKyMon);

        final List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("PHP");
        list.add("C#");
        list.add("ASP.NET");


        final ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monHoc = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDangKyMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoRegistration = new DAO_Registration(getContext());
                RegistrationLearn rl = new RegistrationLearn(txtMSSV.getText().toString(), txtTenDK.getText().toString(),
                        monHoc, txtSDT.getText().toString());

                try {
                    if (validayFrom() > 0) {
                        if (daoRegistration.insertRegistrationLearn(rl) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                            Log.d("onClick: ", "" + monHoc);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }
        });
    }

    public void dialogShowUpdate() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_updatemon);
        dialog.setTitle("Update môn học");
        dialog.show();
        txtTenUp = dialog.findViewById(R.id.txtTenUp);
        txtMSSVUp = dialog.findViewById(R.id.txtMSSVUp);
        spinner = dialog.findViewById(R.id.spnDKUp);
        txtSDTUp = dialog.findViewById(R.id.txtSDTUp);
        imgADDspn = dialog.findViewById(R.id.imgADDspnup);
        btnUpdateMon = dialog.findViewById(R.id.btnUpdateMon);

        txtMSSVUp.setText(ma);
        txtTenUp.setText(ten);
        txtSDTUp.setText(sdt);


        imgADDspn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShowThemMon();
            }
        });
        btnUpdateMon = dialog.findViewById(R.id.btnUpdateMon);

        final List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("PHP");
        list.add("C#");
        list.add("ASP.NET");


        final ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monHocUp = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnUpdateMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoRegistration = new DAO_Registration(getContext());

                try {
                    if (validayFromUp() > 0) {
                        if (daoRegistration.updateRegistrationif(ma, txtTenUp.getText().toString(), txtMSSVUp.getText().toString(),
                                monHocUp, txtSDTUp.getText().toString()) > 0) {
                            Toast.makeText(getContext(), "Update thành công", Toast.LENGTH_SHORT).show();


                            dialog.dismiss();
                        } else {
                            Log.d("onClick: ", "" + monHocUp);
                            Toast.makeText(getContext(), "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }
        });
    }

    public void dialogShowThemMon() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_themspn);
        dialog.show();
        edtThemMon = dialog.findViewById(R.id.edtThemMon);
        btnThemMon = dialog.findViewById(R.id.btnThemMon);


        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private int validayFrom() {
        int check = 1;
        if (txtTenDK.getText().length() == 0 || txtMSSV.getText().length() == 0 ||
                monHoc.length() == 0 || txtSDT.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;
    }

    private int validayFromUp() {
        int check = 1;
        if (txtTenUp.getText().length() == 0 || txtMSSVUp.getText().length() == 0 ||
                monHocUp.length() == 0 || txtSDTUp.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;
    }


}



