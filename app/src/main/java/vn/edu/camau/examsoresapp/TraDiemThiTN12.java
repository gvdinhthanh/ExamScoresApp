package vn.edu.camau.examsoresapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class TraDiemThiTN12 extends AppCompatActivity {
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

    private String HoVaTen;
    private String DiemToan;
    private String DiemVan;
    private String DiemNN;
    private String DiemLy;
    private String DiemHoa;
    private String DiemSinh;
    private String DiemSu;
    private String DiemDia;
    private String DiemGDCD;

    private Button bt_chuyenManHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("KẾT QUẢ THI THPT CHÍNH THỨC");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tra_diem_thi_thpt_12);

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
                                Toast.makeText(TraDiemThiTN12.this, "Không có dữ liệu kỳ thi!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TraDiemThiTN12.this, "Lỗi kết nối internet!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("kyThi", "TN12");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void xoaDuLieu(){
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

        bt_chuyenManHinh.setVisibility(View.INVISIBLE);
    }

    private void khoiTaoBien(){
        edtSBD = findViewById(R.id.editTextSBDTN12);

        spinnerKhoaThi = (Spinner) findViewById(R.id.spinnerKhoaThiTN12);
        ArrayAdapter<String> adapterKhoaThi=new ArrayAdapter<String>(this,R.layout.spinner_item_ndthanh,listKhoaThi);
        adapterKhoaThi.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerKhoaThi.setAdapter(adapterKhoaThi);
        spinnerKhoaThi.setOnItemSelectedListener(new MyProcessEventKhoaThi());

        lbHoTen = findViewById(R.id.lbHoTenTN12);
        txtHoTen = findViewById(R.id.textViewHoTenTN12);
        lbNgaySinh = findViewById(R.id.lbNgaySinhTN12);
        txtNgaySinh = findViewById(R.id.textViewNgaySinhTN12);
        lbLop = findViewById(R.id.lbLopTN12);
        txtLop = findViewById(R.id.textViewLopTN12);
        lbTruongPT = findViewById(R.id.lbTruongTN12);
        txtTruongPT = findViewById(R.id.textViewTruongTN12);

        diemToan = findViewById(R.id.txtDiemToanTN12);
        diemVan = findViewById(R.id.txtDiemVanTN12);
        diemNN = findViewById(R.id.txtDiemNNTN12);
        diemLy = findViewById(R.id.txtDiemLyTN12);
        diemHoa = findViewById(R.id.txtDiemHoaTN12);
        diemSinh = findViewById(R.id.txtDiemSinhTN12);
        diemSu = findViewById(R.id.txtDiemSuTN12);
        diemDia = findViewById(R.id.txtDiemDiaTN12);
        diemGDCD = findViewById(R.id.txtDiemGDCDTN12);

        bt_chuyenManHinh = findViewById(R.id.bt_chuyenTinhKetQuaTN);
        bt_chuyenManHinh.setVisibility(View.INVISIBLE);
    }

    private class MyProcessEventKhoaThi implements OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 1){
                khoaThi = "2019";
            }
            else{
                if(position == 2){
                    khoaThi = "2020";
                }
                else{
                    if(position == 3){
                        khoaThi = "2021";
                    }
                    else {
                        khoaThi = "";
                    }
                }
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {khoaThi = ""; }
    }

    public void traCuuTN12(View view){
        soBaoDanh = edtSBD.getText().toString();
        
        if(soBaoDanh.isEmpty()){
            Toast.makeText(TraDiemThiTN12.this, "Chưa nhập số báo danh!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(khoaThi == ""){
                Toast.makeText(TraDiemThiTN12.this, "Chưa chọn khóa thi!", Toast.LENGTH_SHORT).show();
            }
            else{
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="https://thptsongdoc.edu.vn/apptracuu/get_ThiTHPT12.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray json = new JSONArray(response);
                                    if (json.length() != 0) {
                                        String ketQuaResponse = json.get(0).toString();
                                        JSONObject obj = new JSONObject(ketQuaResponse);

                                        HoVaTen = obj.get("HoVaTen").toString();
                                        DiemToan = obj.get("DiemToan").toString();
                                        DiemVan = obj.get("DiemVan").toString();
                                        DiemNN = obj.get("DiemNN").toString();
                                        DiemLy = obj.get("DiemLy").toString();
                                        DiemHoa = obj.get("DiemHoa").toString();
                                        DiemSinh = obj.get("DiemSinh").toString();
                                        DiemSu = obj.get("DiemSu").toString();
                                        DiemDia = obj.get("DiemDia").toString();
                                        DiemGDCD = obj.get("DiemGDCD").toString();

                                        lbHoTen.setText("Họ và tên:");
                                        txtHoTen.setText(HoVaTen);
                                        lbNgaySinh.setText("Ngày sinh:");
                                        txtNgaySinh.setText(obj.get("NgaySinh").toString());
                                        lbLop.setText("Lớp:");
                                        txtLop.setText(obj.get("Lop12").toString());
                                        lbTruongPT.setText("Trường:");
                                        txtTruongPT.setText(obj.get("TruongPT").toString());

                                        diemToan.setText("Toán: " + DiemToan);
                                        diemVan.setText("Ngữ văn: " + DiemVan);
                                        diemNN.setText("Ng. ngữ: " + DiemNN);
                                        diemLy.setText("Vật lý: " + DiemLy);
                                        diemHoa.setText("Hóa học: " + DiemHoa);
                                        diemSinh.setText("Sinh học: " + DiemSinh);
                                        diemSu.setText("Lịch sử: " + DiemSu);
                                        diemDia.setText("Địa lý: " + DiemDia);
                                        diemGDCD.setText("GDCD: " + DiemGDCD);

                                        bt_chuyenManHinh.setVisibility(View.VISIBLE);

                                    } else{
                                        Toast.makeText(TraDiemThiTN12.this, "Không có kết quả", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TraDiemThiTN12.this, "Lỗi kết nối internet!", Toast.LENGTH_SHORT).show();
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

    public void chuyenTinhKetQuaXetTN(View view){
        Intent intent = new Intent(TraDiemThiTN12.this, TinhKetQuaXetTN.class);
        intent.putExtra("SoBaoDanh", soBaoDanh);
        intent.putExtra("HoVaTen", HoVaTen);
        intent.putExtra("DiemToan", DiemToan);
        intent.putExtra("DiemVan", DiemVan);
        intent.putExtra("DiemNN", DiemNN);
        intent.putExtra("DiemLy", DiemLy);
        intent.putExtra("DiemHoa", DiemHoa);
        intent.putExtra("DiemSinh", DiemSinh);
        intent.putExtra("DiemSu", DiemSu);
        intent.putExtra("DiemDia", DiemDia);
        intent.putExtra("DiemGDCD", DiemGDCD);
        startActivity(intent);
    }
}