package vn.edu.camau.examsoresapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
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

import java.util.HashMap;
import java.util.Map;

public class TinhKetQuaXetTN extends AppCompatActivity {
    private String arrayDienUT[]={"Diện ưu tiên","Diện 1","Diện 2","Diện 3"};
    private String arrayDiemKK[]={"Điểm khuyến khích","0.0","1.0","1.5","2.0","2.5","3.0","3.5","4.0"};
    private String arrayTiLe[]={"Tỉ lệ điểm","70% - 30%","50% - 50%"};

    private Spinner spinnerDiemKK;
    private Spinner spinnerDienUT;
    private Spinner spinnerTiLe;

    private EditText edtDiemTB12;
    private CheckBox checkboxMienNN;
    private CheckBox checkboxHeGDPT;

    private double diemKK;
    private double diemUT;
    private double DTB12;
    private double tiLeThi;
    private double tiLeDTB;

    private String SoBaoDanh;
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

    private double diemToan;
    private double diemVan;
    private double diemNN;
    private double diemLy;
    private double diemHoa;
    private double diemSinh;
    private double diemSu;
    private double diemDia;
    private double diemGDCD;

    private boolean thiToan = false;
    private boolean thiVan = false;
    private boolean thiNN = false;
    private boolean thiLy = false;
    private boolean thiHoa = false;
    private boolean thiSinh = false;
    private boolean thiSu = false;
    private boolean thiDia = false;
    private boolean thiGDCD = false;

    private double diemKHTN = 0.0;
    private double diemKHXH = 0.0;
    private double diemTC;
    private double diemXetTN;

    private boolean heGDPT;
    private boolean mienNN;

    private Button bt_tinhKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("TÍNH KẾT QUẢ XÉT TỐT NGHIỆP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tinh_ket_qua_thi);

        khoiTaoBien();
        xoaDuLieu();
        edtDiemTB12.setOnClickListener(new View.OnClickListener() {
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

        edtDiemTB12 = findViewById(R.id.editTextDTBLop12);

        spinnerDiemKK = (Spinner) findViewById(R.id.spinnerDiemKK);
        ArrayAdapter<String> adapterDiemKK = new ArrayAdapter<String>(this, R.layout.spinner_item_ndthanh, arrayDiemKK);
        adapterDiemKK.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerDiemKK.setAdapter(adapterDiemKK);
        spinnerDiemKK.setOnItemSelectedListener(new MyProcessEventDiemKK());

        spinnerDienUT = (Spinner) findViewById(R.id.spinnerDienUuTien);
        ArrayAdapter<String> adapterDienUT = new ArrayAdapter<String>(this, R.layout.spinner_item_ndthanh, arrayDienUT);
        adapterDienUT.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerDienUT.setAdapter(adapterDienUT);
        spinnerDienUT.setOnItemSelectedListener(new MyProcessEventDienUT());

        spinnerTiLe = (Spinner) findViewById(R.id.spinnerTiLe);
        ArrayAdapter<String> adapterTile = new ArrayAdapter<String>(this, R.layout.spinner_item_ndthanh, arrayTiLe);
        adapterTile.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerTiLe.setAdapter(adapterTile);
        spinnerTiLe.setOnItemSelectedListener(new MyProcessEventTiLe());

        checkboxMienNN = findViewById(R.id.cb_mienNN);
        checkboxHeGDPT = findViewById(R.id.cb_heGDPT);
        bt_tinhKetQua = findViewById(R.id.bt_tinhKetQuaXetTN);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SoBaoDanh = "";
        HoVaTen = "";
        DiemToan = "";
        if(bundle != null){
            SoBaoDanh = bundle.getString("SoBaoDanh");
            HoVaTen = bundle.getString("HoVaTen");
            DiemToan = bundle.getString("DiemToan");
            DiemVan = bundle.getString("DiemVan");
            DiemNN = bundle.getString("DiemNN");
            DiemLy = bundle.getString("DiemLy");
            DiemHoa = bundle.getString("DiemHoa");
            DiemSinh = bundle.getString("DiemSinh");
            DiemSu = bundle.getString("DiemSu");
            DiemDia = bundle.getString("DiemDia");
            DiemGDCD = bundle.getString("DiemGDCD");

            if(!DiemToan.isEmpty()){
                diemToan = Double.parseDouble(DiemToan);
                thiToan = true;
            }
            else{
                diemToan = 0;
            }
            if(!DiemVan.isEmpty()){
                diemVan = Double.parseDouble(DiemVan);
                thiVan = true;
            }
            else{
                diemVan = 0;
            }
            if(!DiemNN.isEmpty()){
                diemNN = Double.parseDouble(DiemNN);
                thiNN = true;
            }
            else{
                diemNN = 0;
            }
            if(!DiemLy.isEmpty()){
                diemLy = Double.parseDouble(DiemLy);
                thiLy = true;
            }
            else{
                diemLy = 0;
            }
            if(!DiemHoa.isEmpty()){
                diemHoa = Double.parseDouble(DiemHoa);
                thiHoa = true;
            }
            else{
                diemHoa = 0;
            }
            if(!DiemSinh.isEmpty()){
                diemSinh = Double.parseDouble(DiemSinh);
                thiSinh = true;
            }
            else{
                diemSinh = 0;
            }
            if(!DiemSu.isEmpty()){
                diemSu = Double.parseDouble(DiemSu);
                thiSu = true;
            }
            else{
                diemSu = 0;
            }
            if(!DiemDia.isEmpty()){
                diemDia = Double.parseDouble(DiemDia);
                thiDia = true;
            }
            else{
                diemDia = 0;
            }
            if(!DiemGDCD.isEmpty()){
                diemGDCD = Double.parseDouble(DiemGDCD);
                thiGDCD = true;
            }
            else{
                diemGDCD = 0;
            }
        }
    }

    private class MyProcessEventTiLe implements OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position==1){
                tiLeThi = 0.7;
                tiLeDTB = 0.3;
            }
            else{
                if(position==2){
                    tiLeThi = 0.5;
                    tiLeDTB = 0.5;
                }
                else{
                    tiLeThi = -1.0;
                    tiLeDTB = -1.0;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            tiLeThi = -1.0;
            tiLeDTB = -1.0;
        }
    }

    private class MyProcessEventDienUT implements OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 1){
                diemUT = 0.0;
            }
            else{
                if(position == 2){
                    diemUT = 0.25;
                }
                else{
                    if(position == 3){
                        diemUT = 0.5;
                    }
                    else{
                        diemUT = -1.0;
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            diemUT = -1.0;
        }
    }

    private class MyProcessEventDiemKK implements OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){
                diemKK = Double.parseDouble(arrayDiemKK[position]);
            }
            else{
                diemKK = -1.0;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            diemKK = -1.0;
        }
    }

    private void xoaDuLieu(){
        TextView soBaoDanh = findViewById(R.id.txtSoBaoDanh);
        soBaoDanh.setText("");
        TextView hoVaTen = findViewById(R.id.txtHoVaTen);
        hoVaTen.setText("");
        TextView ketQua = findViewById(R.id.txtKetQuaXetTN);
        ketQua.setText("");
    }

    private boolean khongCheXet(){
        if(heGDPT){
            if(mienNN){
                diemNN = 10.0;
                if(((diemToan <= 1.0) && (thiToan)) || ((diemVan <= 1.0) && (thiVan)) ||
                        ((diemLy <=1.0) && (thiLy)) || ((diemHoa <= 1.0) && (thiHoa)) || ((diemSinh <= 1.0) && (thiSinh)) ||
                        ((diemSu <= 1.0) && (thiSu)) || ((diemDia <= 1.0) && (thiDia)) || ((diemGDCD <= 1.0) && (thiGDCD))){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if(((diemToan <= 1.0) && (thiToan)) || ((diemVan <= 1.0) && (thiVan)) || ((diemNN <= 1.0) && (thiNN)) ||
                        ((diemLy <=1.0) && (thiLy)) || ((diemHoa <= 1.0) && (thiHoa)) || ((diemSinh <= 1.0) && (thiSinh)) ||
                        ((diemSu <= 1.0) && (thiSu)) || ((diemDia <= 1.0) && (thiDia)) || ((diemGDCD <= 1.0) && (thiGDCD))){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            if(((diemToan <= 1.0) && (thiToan)) || ((diemVan <= 1.0) && (thiVan)) ||
                    ((diemLy <=1.0) && (thiLy)) || ((diemHoa <= 1.0) && (thiHoa)) || ((diemSinh <= 1.0) && (thiSinh)) ||
                    ((diemSu <= 1.0) && (thiSu)) || ((diemDia <= 1.0) && (thiDia))){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public void tinhKetQuaXetTN(View view){

        if(checkboxHeGDPT.isChecked()){
            heGDPT = true;
        }
        else{
            heGDPT = false;
        }
        if(checkboxMienNN.isChecked()){
            mienNN = true;
        }
        else{
            mienNN = false;
        }
        try{
            DTB12 = Double.parseDouble(edtDiemTB12.getText().toString());
            if(DTB12 <= 0 || DTB12 > 10.0){
                Toast.makeText(TinhKetQuaXetTN.this, "Kiểm tra dữ liệu điểm trung bình!", Toast.LENGTH_SHORT).show();
            }
            else{
                if(diemUT == -1.0){
                    Toast.makeText(TinhKetQuaXetTN.this, "Chưa chọn Diện ưu tiên!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(diemKK == -1.0){
                        Toast.makeText(TinhKetQuaXetTN.this, "Chưa chọn điểm khuyến khích!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(tiLeThi == -1) {
                            Toast.makeText(TinhKetQuaXetTN.this, "Chưa chọn tỉ lệ điểm thi xét tốt nghiệp!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            TextView soBaoDanh = findViewById(R.id.txtSoBaoDanh);
                            soBaoDanh.setText("Số báo danh: " + SoBaoDanh);
                            TextView hoVaTen = findViewById(R.id.txtHoVaTen);
                            hoVaTen.setText("Họ và tên: "+ HoVaTen);
                            TextView ketQua = findViewById(R.id.txtKetQuaXetTN);
                            if(khongCheXet()){
                                ketQua.setText("Kết quả: Hỏng");
                            }
                            else{
                                diemKHTN = (diemLy + diemHoa + diemSinh)/3;
                                if(heGDPT){
                                    diemKHXH = (diemSu + diemDia + diemGDCD)/3;
                                }
                                else{
                                    diemKHXH = (diemSu + diemDia)/2;
                                }
                                if(diemKHTN > diemKHXH){
                                    diemTC = diemKHTN;
                                }
                                else{
                                    diemTC = diemKHXH;
                                }

                                if(heGDPT){
                                    Double tongDiem = diemToan + diemVan + diemNN + diemTC;
                                    diemXetTN = tiLeDTB * DTB12 + (tiLeThi * (tongDiem + diemKK)/4) + diemUT;
                                }
                                else{
                                    Double tongDiem = diemToan + diemVan + diemTC;
                                    diemXetTN = tiLeDTB * DTB12 + (tiLeThi * ((tongDiem/3) + (diemKK/4))) + diemUT;
                                }
                                if(diemXetTN >= 5.0){
                                    ketQua.setText("Kết quả: Đạt");
                                }
                                else{
                                    ketQua.setText("Kết quả: Hỏng");
                                }

                            }
                        }
                    }
                }
            }

        }
        catch (NumberFormatException e){
            Toast.makeText(TinhKetQuaXetTN.this, "Chưa nhập điểm trung bình cả năm lớp 12!", Toast.LENGTH_SHORT).show();
        }
    }
}