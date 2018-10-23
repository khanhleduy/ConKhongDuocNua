package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Registration;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.RegistrationLearn;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class MonHocAdapter extends BaseAdapter{

    List<RegistrationLearn> arrMon;
    public Activity context;
    public LayoutInflater inflater;
    DAO_Registration daoRegistration;
    public MonHocAdapter(Activity context, List<RegistrationLearn> arrMon) {
        super();
        this.context = context;
        this.arrMon = arrMon;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        daoRegistration = new DAO_Registration(context);
    }
    @Override
    public int getCount() {
        return arrMon.size();
    }
    @Override
    public Object getItem(int position) {
        return arrMon.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        TextView txtTenSV;
        TextView txtMon;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_listviewregistration, null);
            holder.txtTenSV = (TextView)
                    convertView.findViewById(R.id.txtTenDK);
            holder.txtMon = (TextView)
                    convertView.findViewById(R.id.txtMonDK);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.imgDeleteDK);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    daoRegistration.deleteSachByID(arrMon.get(position).getMaSinhVien());
                    arrMon.remove(position);
                    notifyDataSetChanged();
                }
            });
            Log.d( "getView: ","loi view" );
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        RegistrationLearn _entry = (RegistrationLearn) arrMon.get(position);
        holder.txtTenSV.setText(_entry.getTenSinhVien());
        holder.txtMon.setText(_entry.getMonHoc());
        Log.d("me", "lol: ");
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<RegistrationLearn> items){
        this.arrMon = items;
        notifyDataSetChanged();
    }
}
