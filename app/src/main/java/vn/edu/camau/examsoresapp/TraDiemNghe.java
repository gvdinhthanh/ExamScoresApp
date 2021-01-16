package vn.edu.camau.examsoresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraDiemNghe extends AppCompatActivity {
    private String arrayMonThi[]={"Chọn môn nghề","Nghề Điện dân dụng","Nghề Tin học văn phòng"};
    private List<String> listKhoaThi = new ArrayList<String>();

    private String soBaoDanh;
    private String monThi;
    private String khoaThi;
    private EditText edtSBD;
    private Spinner spinnerKhoaThi;
    private Spinner spinnerMonThi;

    private TextView lbHoTen;
    private TextView txtHoTen;
    private TextView lbNgaySinh;
    private TextView txtNgaySinh;
    private TextView lbLop11;
    private TextView txtLop11;
    private TextView lbTruongPT;
    private TextView txtTruongPT;
    private TextView lbLyThuyet;
    private TextView txtLyThuyet;
    private TextView lbThucHanh;
    private TextView txtThucHanh;
    private TextView lbDiemTB;
    private TextView txtDiemTB;
    private TextView lbKetQua;
    private TextView txtKetQua;
    private TextView lbXepLoai;
    private TextView txtXepLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("KẾT QUẢ THI NGHỀ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tra_diem_nghe);
        getKhoaThi();
        khoiTaoBien();
        edtSBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaDuLieu();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void khoiTaoBien(){
        edtSBD = findViewById(R.id.editTextSBD);
        spinnerMonThi = (Spinner) findViewById(R.id.spinnerMonThi);
        spinnerKhoaThi = (Spinner) findViewById(R.id.spinnerKhoaThi);
        ArrayAdapter<String> adapterMonThi=new ArrayAdapter<String>(this,R.layout.spinner_item_ndthanh,arrayMonThi);
        adapterMonThi.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerMonThi.setAdapter(adapterMonThi);
        spinnerMonThi.setOnItemSelectedListener(new MyProcessEventMonThi());

        ArrayAdapter<String> adapterKhoaThi=new ArrayAdapter<String>(this,R.layout.spinner_item_ndthanh,listKhoaThi);
        adapterKhoaThi.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerKhoaThi.setAdapter(adapterKhoaThi);
        spinnerKhoaThi.setOnItemSelectedListener(new MyProcessEventKhoaThi());

        lbHoTen = findViewById(R.id.lbHoTen);
        txtHoTen = findViewById(R.id.textViewHoTen);
        lbNgaySinh = findViewById(R.id.lbNgaySinh);
        txtNgaySinh = findViewById(R.id.textViewNgaySinh);
        lbLop11 = findViewById(R.id.lbLop11);
        txtLop11 = findViewById(R.id.textViewLop11);
        lbTruongPT = findViewById(R.id.lbTruongPT);
        txtTruongPT = findViewById(R.id.textViewTruongPT);
        lbLyThuyet = findViewById(R.id.lbLyThuyet);
        txtLyThuyet = findViewById(R.id.textViewLyThuyet);
        lbThucHanh = findViewById(R.id.lbThucHanh);
        txtThucHanh = findViewById(R.id.textViewThucHanh);
        lbDiemTB = findViewById(R.id.lbDiemTB);
        txtDiemTB = findViewById(R.id.textViewDiemTB);
        lbKetQua = findViewById(R.id.lbKetQua);
        txtKetQua = findViewById(R.id.textViewKetQua);
        lbXepLoai = findViewById(R.id.lbXepLoai);
        txtXepLoai = findViewById(R.id.textViewXepLoai);
    }

    private class MyProcessEventMonThi implements OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 1){
                monThi = "dienDanDung";
            }
            else{
                if(position == 2){
                    monThi = "tinHoc";
                }
                else{
                    monThi = "";
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            monThi = "";
        }
    }

    private class MyProcessEventKhoaThi implements OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position >= 1){
                khoaThi = listKhoaThi.get(position).substring(15);
            }
            else{
                khoaThi = "";
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {khoaThi = ""; }
    }

    public void xoaDuLieu(){
        lbHoTen.setText("");
        txtHoTen.setText("");
        lbNgaySinh.setText("");
        txtNgaySinh.setText("");
        lbLop11.setText("");
        txtLop11.setText("");
        lbTruongPT.setText("");
        txtTruongPT.setText("");
        lbLyThuyet.setText("");
        txtLyThuyet.setText("");
        lbThucHanh.setText("");
        txtThucHanh.setText("");
        lbDiemTB.setText("");
        txtDiemTB.setText("");
        lbKetQua.setText("");
        txtKetQua.setText("");
        lbXepLoai.setText("");
        txtXepLoai.setText("");
    }

    public void getKhoaThi(){
        listKhoaThi.add("Chọn khóa thi");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://thptsongdoc.edu.vn/apptracuu/get_ThoiGianTraCuu.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray json = new JSONArray(response);
                            if (json.length() != 0) {
                                for(int i=0; i<json.length(); i++){
                                    String ketQua = json.get(i).toString();
                                    JSONObject obj = new JSONObject(ketQua);
                                    String khoaThi = obj.get("KhoaThi").toString();
                                    listKhoaThi.add("Khóa thi ngày: " + khoaThi);
                                }
                            } else{
                                Toast.makeText(TraDiemNghe.this, "Không có dữ liệu kỳ thi!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TraDiemNghe.this, "Lỗi kết nối internet!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("kyThi", "NPT");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void traCuuNghe(View view){
        soBaoDanh = edtSBD.getText().toString();
        if(soBaoDanh.isEmpty()){
            Toast.makeText(TraDiemNghe.this, "Chưa nhập số báo danh!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(monThi == ""){
                Toast.makeText(TraDiemNghe.this, "Chưa chọn môn thi Nghề!", Toast.LENGTH_SHORT).show();
            }
            else{
                if(khoaThi == ""){
                    Toast.makeText(TraDiemNghe.this, "Chưa chọn khóa thi!", Toast.LENGTH_SHORT).show();
                }
                else{
                    RequestQueue queue = Volley.newRequestQueue(this);
                    String url ="https://thptsongdoc.edu.vn/apptracuu/get_ThiNghePT.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray json = new JSONArray(response);
                                        if (json.length() != 0) {
                                            String ketQua = json.get(0).toString();
                                            JSONObject obj = new JSONObject(ketQua);
                                            lbHoTen.setText("Họ và tên:");
                                            txtHoTen.setText(obj.get("HoDem").toString() + " " + obj.get("Ten").toString());
                                            lbNgaySinh.setText("Ngày sinh:");
                                            txtNgaySinh.setText(obj.get("NgaySinh").toString());
                                            lbLop11.setText("Lớp:");
                                            txtLop11.setText(obj.get("Lop11").toString());
                                            lbTruongPT.setText("Trường:");
                                            txtTruongPT.setText(obj.get("TruongPT").toString());
                                            lbLyThuyet.setText("Điểm lý thuyết:");
                                            txtLyThuyet.setText(obj.get("LyThuyet").toString());
                                            lbThucHanh.setText("Điểm thực hành:");
                                            txtThucHanh.setText(obj.get("ThucHanh").toString());
                                            lbDiemTB.setText("Điểm trung bình:");
                                            txtDiemTB.setText(obj.get("DTB").toString());
                                            lbKetQua.setText("Kết quả:");
                                            txtKetQua.setText(obj.get("KetQua").toString());
                                            lbXepLoai.setText("Xếp loại:");
                                            txtXepLoai.setText(obj.get("XepLoai").toString());
                                        } else{
                                            Toast.makeText(TraDiemNghe.this, "Không có kết quả", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(TraDiemNghe.this, "Lỗi kết nối internet!", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("SBD", soBaoDanh);
                            params.put("monThi", monThi);
                            params.put("khoaThi", khoaThi);
                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        }
    }
}