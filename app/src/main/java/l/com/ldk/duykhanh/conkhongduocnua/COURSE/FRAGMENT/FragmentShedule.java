package l.com.ldk.duykhanh.conkhongduocnua.COURSE.FRAGMENT;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.ADAPTER.SheduleAdapter;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Shedule;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.Shedule;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class FragmentShedule extends Fragment {

    public List<Shedule> dsLich;
    ListView lvLichHoc;

    public EditText edtId, edtNgay, edtPhong, edtGiangDuong, edtMaMon, edtMon, edtGiangVien;
    public Button btnADD;
    DAO_Shedule daoShedule;
    SheduleAdapter adapter;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shedule, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_shedule);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });

        lvLichHoc = view.findViewById(R.id.lvShedule);
        daoShedule = new DAO_Shedule(getContext());
        dsLich = new ArrayList<>();
        try {
            dsLich = daoShedule.getAllShedule();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new SheduleAdapter(getActivity(), dsLich);
        Toast.makeText(getActivity(), "Chua" + dsLich, Toast.LENGTH_SHORT).show();
        lvLichHoc.setAdapter(adapter);
        lvLichHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
        return view;
    }

    public void dialogShow() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_shedule);
        dialog.setTitle("Them lich hoc");
        dialog.show();
        edtId = dialog.findViewById(R.id.edtID);
        edtNgay = dialog.findViewById(R.id.edtNgay);
        edtPhong = dialog.findViewById(R.id.edtPhong);
        edtGiangDuong = dialog.findViewById(R.id.edtGiangDuong);
        edtMaMon = dialog.findViewById(R.id.edtMaMon);
        edtMon = dialog.findViewById(R.id.edtMon);
        edtGiangVien = dialog.findViewById(R.id.edtGiangVien);
        btnADD = dialog.findViewById(R.id.btnThemLich);
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                daoShedule = new DAO_Shedule(getContext());

                try {
                    Shedule rl = new Shedule(Integer.parseInt(edtId.getText().toString()), sdf.parse(edtNgay.getText().toString()),
                            edtPhong.getText().toString(), edtGiangDuong.getText().toString(), edtMaMon.getText().toString(),
                            edtMon.getText().toString(), edtGiangVien.getText().toString());

                    if (validationFrom() > 0) {
                        if (daoShedule.inserShedule(rl) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Date Error ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public int validationFrom() {
        if (edtId.getText().toString().isEmpty() || edtNgay.getText().toString().isEmpty() || edtPhong.getText().toString().isEmpty() ||
                edtGiangDuong.getText().toString().isEmpty() || edtMaMon.getText().toString().isEmpty() || edtMon.getText().toString().isEmpty() ||
                edtGiangVien.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }
}
