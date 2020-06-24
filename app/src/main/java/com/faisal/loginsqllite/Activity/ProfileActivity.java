package com.faisal.loginsqllite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.faisal.loginsqllite.Adapter.ProfileAdapter;
import com.faisal.loginsqllite.Control.AppController;
import com.faisal.loginsqllite.Model.Profile;
import com.faisal.loginsqllite.R;
import com.faisal.loginsqllite.Util.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<Profile> itemList = new ArrayList<Profile>();
    ProfileAdapter adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_id, txt_nip, txt_pass, txt_namak, txt_divisi ,txt_unit, txt_lokasi, txt_jabatan, txt_hp,  txt_email;
    String id, nip, pass, nama_karyawan, divisi, unit, lokasi, jabatan, hp, email;

    private static final String TAG = ProfileActivity.class.getSimpleName();

    private static String url_select = Server.URL + "select.php";
    private static String url_insert = Server.URL + "insert.php";
    private static String url_edit   = Server.URL + "edit.php";
    private static String url_update = Server.URL + "update.php";
    private static String url_delete = Server.URL + "delete.php";

    public static final String TAG_ID = "id";
    public static final String TAG_NIP = "nip";
    public static final String TAG_PASS = "pass";
    public static final String TAG_NAMAKARYAWAN = "nama_karyawan";
    public static final String TAG_DIVISI = "divisi";
    public static final String TAG_UNIT = "unit";
    public static final String TAG_LOKASI = "lokasi";
    public static final String TAG_JABATAN = "jabatan";
    public static final String TAG_HP = "hp";
    public static final String TAG_EMAIL = "email";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // menghubungkan variablel pada layout dan pada java
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list = (ListView) findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new ProfileAdapter(ProfileActivity.this, itemList);
        list.setAdapter(adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );

        // fungsi floating action button untuk memanggil form kontak
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm("", "", "", "", "","","",
                        "", "","","SIMPAN");
            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(ProfileActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                edit(idx);
                                break;
                            case 1:
                                delete(idx);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
    }
    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }

    // untuk mengosongi edittext pada form
    private void kosong() {
        txt_id.setText(null);
        txt_nip.setText(null);
        txt_pass.setText(null);
        txt_namak.setText(null);
        txt_divisi.setText(null);
        txt_unit.setText(null);
        txt_lokasi.setText(null);
        txt_jabatan.setText(null);
        txt_hp.setText(null);
        txt_email.setText(null);
    }

    // untuk menampilkan dialog form kontak
    private void DialogForm(String idx, String nipx, String passx, String namakx,
                            String divisix, String unitx, String lokasix,
                            String jabatanx, String hpx, String emailx, String button) {
        dialog = new AlertDialog.Builder(ProfileActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_profile, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_contact);
        dialog.setTitle("Profile");

        txt_id = (EditText) dialogView.findViewById(R.id.txt_id);
        txt_nip = (EditText) dialogView.findViewById(R.id.txt_nip);
        txt_pass = (EditText) dialogView.findViewById(R.id.txt_pass);
        txt_namak = (EditText) dialogView.findViewById(R.id.txt_namak);
        txt_divisi = (EditText) dialogView.findViewById(R.id.txt_divisi);
        txt_unit = (EditText) dialogView.findViewById(R.id.txt_unit);
        txt_lokasi = (EditText) dialogView.findViewById(R.id.txt_lokasi);
        txt_jabatan = (EditText) dialogView.findViewById(R.id.txt_jabatan);
        txt_hp  = (EditText) dialogView.findViewById(R.id.txt_hp);
        txt_email = (EditText) dialogView.findViewById(R.id.txt_email);

        if (!idx.isEmpty()) {
            txt_id.setText(idx);
            txt_nip.setText(nipx);
            txt_pass.setText(passx);
            txt_namak.setText(namakx);
            txt_divisi.setText(divisix);
            txt_unit.setText(unitx);
            txt_lokasi.setText(lokasix);
            txt_jabatan.setText(jabatanx);
            txt_hp.setText(hpx);
            txt_email.setText(emailx);
        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id = txt_id.getText().toString();
                nip = txt_nip.getText().toString();
                pass = txt_pass.getText().toString();
                nama_karyawan = txt_namak.getText().toString();
                divisi = txt_divisi.getText().toString();
                unit = txt_unit.getText().toString();
                lokasi = txt_lokasi.getText().toString();
                jabatan = txt_jabatan.getText().toString();
                hp = txt_hp.getText().toString();
                email = txt_email.getText().toString();
                simpan_update();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();
    }

    // untuk menampilkan semua data pada listview
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Profile item = new Profile();

                        item.setId(obj.getString(TAG_ID));
                        item.setnip(obj.getString(TAG_NIP));
                        item.setPass(obj.getString(TAG_PASS));
                        item.setNama_karyawan(obj.getString(TAG_NAMAKARYAWAN));
                        item.setDivisi(obj.getString(TAG_DIVISI));
                        item.setUnit(obj.getString(TAG_UNIT));
                        item.setLokasi(obj.getString(TAG_LOKASI));
                        item.setJabatan(obj.getString(TAG_JABATAN));
                        item.setHp(obj.getString(TAG_HP));
                        item.setEmail(obj.getString(TAG_EMAIL));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        String url;

        if (id.isEmpty()) {
            url = url_insert;
        } else {
            url = url_update;
        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());

                        callVolley();
                        kosong();

                        Toast.makeText(ProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(ProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })

        {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (id.isEmpty()) {
                    params.put("nip", nip);
                    params.put("pass", pass);
                    params.put("nama karyawan", nama_karyawan);
                    params.put("divisi", divisi);
                    params.put("unit", unit);
                    params.put("lokasi", lokasi);
                    params.put("jabatan", jabatan);
                    params.put("hp", hp);
                    params.put("email", email);
                } else {
                    params.put("id", id);
                    params.put("nip", nip);
                    params.put("pass", pass);
                    params.put("nama karyawan", nama_karyawan);
                    params.put("divisi", divisi);
                    params.put("unit", unit);
                    params.put("lokasi", lokasi);
                    params.put("jabatan", jabatan);
                    params.put("hp", hp);
                    params.put("email", email);
                }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk get edit data kontak
    private void edit(final String idx) {
        StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String idx = jObj.getString(TAG_ID);
                        String nipx = jObj.getString(TAG_NIP);
                        String passx = jObj.getString(TAG_PASS);
                        String namakx = jObj.getString(TAG_NAMAKARYAWAN);
                        String divisix = jObj.getString(TAG_DIVISI);
                        String unitx = jObj.getString(TAG_UNIT);
                        String lokasix = jObj.getString(TAG_LOKASI);
                        String jabatanx = jObj.getString(TAG_JABATAN);
                        String hpx = jObj.getString(TAG_HP);
                        String emailx = jObj.getString(TAG_EMAIL);

                        DialogForm(idx, nipx, passx, namakx, divisix, unitx, lokasix, jabatanx, hpx, emailx, "UPDATE");

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(ProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk menghapus
    private void delete(final String idx) {
        StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error pada json
                    if (success == 1) {
                        Log.d("delete", jObj.toString());

                        callVolley();

                        Toast.makeText(ProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(ProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
