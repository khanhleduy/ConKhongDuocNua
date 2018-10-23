package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Registration;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Shedule;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.RegistrationLearn;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.Shedule;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class SheduleAdapter extends BaseAdapter {

    List<Shedule> arrShedule;
    public Activity context;
    public LayoutInflater inflater;
    DAO_Shedule daoShedule;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public SheduleAdapter(Activity context, List<Shedule> arrShedule) {
        super();
        this.context = context;
        this.arrShedule = arrShedule;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        daoShedule = new DAO_Shedule(context);
    }
    @Override
    public int getCount() {
        return arrShedule.size();
    }
    @Override
    public Object getItem(int position) {
        return arrShedule.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMonC,txtNgayc;
        ImageView imgDeleteC;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SheduleAdapter.ViewHolder holder;
        if(convertView==null)
        {
            holder = new SheduleAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_listshedule, null);
            holder.txtMonC = convertView.findViewById(R.id.txtMonCalender);
            holder.txtNgayc = convertView.findViewById(R.id.txtCalender);
            holder.imgDeleteC = convertView.findViewById(R.id.imgDeleteCalender);
            holder.imgDeleteC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    daoShedule.deleteShedu(String.valueOf(arrShedule.get(position).getId()));
                    arrShedule.remove(position);
                    notifyDataSetChanged();
                }
            });
            Log.d( "getView: ","loi view" );
            convertView.setTag(holder);
        }
        else
            holder=(SheduleAdapter.ViewHolder)convertView.getTag();
        Shedule _entry = (Shedule) arrShedule.get(position);
        holder.txtMonC.setText(_entry.getMon());
        holder.txtNgayc.setText(sdf.format( _entry.getNgay()));
        notifyDataSetChanged();
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Shedule> items){
        this.arrShedule = items;
        notifyDataSetChanged();
    }
}
