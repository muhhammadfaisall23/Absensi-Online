package com.faisal.loginsqllite.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.faisal.loginsqllite.Model.Profile;
import com.faisal.loginsqllite.R;

import java.util.List;

public class ProfileAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Profile> items;

    public ProfileAdapter(Activity activity, List<Profile> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_row, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nip = (TextView) convertView.findViewById(R.id.nip);
        TextView pass = (TextView) convertView.findViewById(R.id.pass);
        TextView nama_karyawan = (TextView) convertView.findViewById(R.id.nama_karyawan);
        TextView divisi = (TextView) convertView.findViewById(R.id.divisi);
        TextView unit = (TextView) convertView.findViewById(R.id.unit);
        TextView lokasi = (TextView) convertView.findViewById(R.id.lokasi);
        TextView jabatan = (TextView) convertView.findViewById(R.id.jabatan);
        TextView hp = (TextView) convertView.findViewById(R.id.hp);
        TextView email = (TextView) convertView.findViewById(R.id.email);

        Profile profile = items.get(position);

        id.setText(profile.getId());
        nip.setText(profile.getnip());
        pass.setText(profile.getPass());
        nama_karyawan.setText(profile.getNama_karyawan());
        divisi.setText(profile.getDivisi());
        unit.setText(profile.getUnit());
        lokasi.setText(profile.getLokasi());
        jabatan.setText(profile.getJabatan());
        hp.setText(profile.getHp());
        email.setText(profile.getEmail());

        return convertView;

    }
}
