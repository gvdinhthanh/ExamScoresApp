package vn.edu.camau.examsoresapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;

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

public class TraDiemThiThu12 extends AppCompatActivity {
    private List<String> listKhoaThi = new ArrayList<String>();
    private String soBaoDanh;
    private String khoaThi;
    private EditText edtSBD;
    private Spinner spinnerKhoaThi;

    private TextView lbHoTen;
    private TextView txtHoTen;
    private TextView lbNgaySinh;
    private TextView txtNgaySinh;
    private TextView lbLop;
    private TextView txtLop;
    private TextView lbTruongPT;
    private TextView txtTruongPT;

    private TextView diemToan;
    private TextView diemVan;
    private TextView diemNN;
    private TextView diemLy;
    private TextView diemHoa;
    private TextView diemSinh;
    private TextView diemSu;
    private TextView diemDia;
    private TextView diemGDCD;

    private TextView diemTrungBinh12;
    private TextView diemKK;
    private TextView dienUT;

    private TextView diemXetTN;
    private TextView ketQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("KẾT QUẢ THI THỬ THPT 12");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tra_diem_thi_thu_12);

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
                                Toast.makeText(TraDiemThiThu12.this, "Không có dữ liệu kỳ thi!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TraDiemThiThu12.this, "Lỗi kết nối internet!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("kyThi", "TT12");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void khoiTaoBien(){
        edtSBD = findViewById(R.id.editTextSBDTT12);
        spinnerKhoaThi = (Spinner) findViewById(R.id.spinnerKhoaThiThu12);
        ArrayAdapter<String> adapterKhoaThi=new ArrayAdapter<String>(this,R.layout.spinner_item_ndthanh,listKhoaThi);
        adapterKhoaThi.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerKhoaThi.setAdapter(adapterKhoaThi);
        spinnerKhoaThi.setOnItemSelectedListener(new MyProcessEventKhoaThi());

        lbHoTen = findViewById(R.id.lbHoTenTT12);
        txtHoTen = findViewById(R.id.textViewHoTenTT12);
        lbNgaySinh = findViewById(R.id.lbNgaySinhTT12);
        txtNgaySinh = findViewById(R.id.textViewNgaySinhTT12);
        lbLop = findViewById(R.id.lbLopTT12);
        txtLop = findViewById(R.id.textViewLopTT12);
        lbTruongPT = findViewById(R.id.lbTruongTT12);
        txtTruongPT = findViewById(R.id.textViewTruongTT12);

        diemToan = findViewById(R.id.txtDiemToanTT12);
        diemVan = findViewById(R.id.txtDiemVanTT12);
        diemNN = findViewById(R.id.txtDiemNNTT12);
        diemLy = findViewById(R.id.txtDiemLyTT12);
        diemHoa = findViewById(R.id.txtDiemHoaTT12);
        diemSinh = findViewById(R.id.txtDiemSinhTT12);
        diemSu = findViewById(R.id.txtDiemSuTT12);
        diemDia = findViewById(R.id.txtDiemDiaTT12);
        diemGDCD = findViewById(R.id.txtDiemGDCDTT12);

        diemTrungBinh12 = findViewById(R.id.txtDiemTBTT12);
        diemKK = findViewById(R.id.txtDiemKKTT12);
        dienUT = findViewById(R.id.txtDienUTTT12);

        diemXetTN = findViewById(R.id.txtDiemXetTNTT12);
        ketQua = findViewById(R.id.txtKetQuaTT12);
    }

    private void xoaDuLieu(){
        lbHoTen.setText("");
        txtHoTen.setText("");
        lbNgaySinh.setText("");
        txtNgaySinh.setText("");
        lbLop.setText("");
        txtLop.setText("");
        lbTruongPT.setText("");
        txtTruongPT.setText("");

        diemToan.setText("");
        diemVan.setText("");
        diemNN.setText("");
        diemLy.setText("");
        diemHoa.setText("");
        diemSinh.setText("");
        diemSu.setText("");
        diemDia.setText("");
        diemGDCD.setText("");

        diemTrungBinh12.setText("");
        diemKK.setText("");
        dienUT.setText("");

        diemXetTN.setText("");
        ketQua.setText("");
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

    public void traCuuTT12(View view){
        soBaoDanh = edtSBD.getText().toString();

        if(soBaoDanh.isEmpty()){
            Toast.makeText(TraDiemThiThu12.this, "Chưa nhập số báo danh!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(khoaThi == ""){
                Toast.makeText(TraDiemThiThu12.this, "Chưa chọn khóa thi!", Toast.LENGTH_SHORT).show();
            }
            else{
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="https://thptsongdoc.edu.vn/apptracuu/get_ThiThuTHPT12.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray json = new JSONArray(response);
                                    if (json.length() != 0) {
                                        String ketQuaResponse = json.get(0).toString();
                                        JSONObject obj = new JSONObject(ketQuaResponse);

                                        lbHoTen.setText("Họ và tên:");
                                        txtHoTen.setText(obj.get("HoVaTen").toString());
                                        lbNgaySinh.setText("Ngày sinh:");
                                        txtNgaySinh.setText(obj.get("NgaySinh").toString());
                                        lbLop.setText("Lớp:");
                                        txtLop.setText(obj.get("Lop12").toString());
                                        lbTruongPT.setText("Trường:");
                                        txtTruongPT.setText(obj.get("TruongPT").toString());

                                        diemToan.setText("Toán: " + obj.get("DiemToan").toString());
                                        diemVan.setText("Ngữ văn: " + obj.get("DiemVan").toString());
                                        diemNN.setText("Ng. ngữ: " + obj.get("DiemNN").toString());
                                        diemLy.setText("Vật lý: " + obj.get("DiemLy").toString());
                                        diemHoa.setText("Hóa học: " + obj.get("DiemHoa").toString());
                                        diemSinh.setText("Sinh học: " + obj.get("DiemSinh").toString());
                                        diemSu.setText("Lịch sử: " + obj.get("DiemSu").toString());
                                        diemDia.setText("Địa lý: " + obj.get("DiemDia").toString());
                                        diemGDCD.setText("GDCD: " + obj.get("DiemGDCD").toString());

                                        diemTrungBinh12.setText("ĐTB12: " + obj.get("DTB12").toString());
                                        diemKK.setText("Điểm KK: " + obj.get("DiemKK").toString());
                                        dienUT.setText("Diện ƯT: "+obj.get("DienUT").toString());

                                        diemXetTN.setText("Điểm xét TN: " + obj.get("DiemXetTN").toString());
                                        String ketQuaXetTN = obj.get("KetQua").toString();
                                        if(ketQuaXetTN != ""){
                                            if(ketQuaXetTN=="H"){
                                                ketQua.setText("Kết quả: Hỏng");
                                            }
                                            else{
                                                ketQua.setText("Kết quả: Đạt");
                                            }
                                        }

                                    } else{
                                        Toast.makeText(TraDiemThiThu12.this, "Không có kết quả", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TraDiemThiThu12.this, "Lỗi kết nối internet!", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("SBD", soBaoDanh);
                        params.put("khoaThi", khoaThi);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        }
    }
}