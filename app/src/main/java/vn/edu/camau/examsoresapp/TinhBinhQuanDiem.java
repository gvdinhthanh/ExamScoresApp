package vn.edu.camau.examsoresapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TinhBinhQuanDiem extends AppCompatActivity {
    private String arrayDienUT[]={"Diện ưu tiên","Diện 1","Diện 2","Diện 3"};
    private String arrayDiemKK[]={"Điểm khuyến khích","0.0","1.0","1.5","2.0","2.5","3.0","3.5","4.0"};
    private String arrayTiLe[]={"Tỉ lệ điểm","70% - 30%","50% - 50%"};

    private Spinner spinnerDiemKK;
    private Spinner spinnerDienUT;
    private Spinner spinnerTiLe;

    private EditText edtDiemTB12;
    private CheckBox checkboxMienNN;
    private CheckBox checkboxGDPT;
    private TextView txtKetQua;
    private double diemKK;
    private double diemUT;
    private double DTB12;
    private double tiLeThi;
    private double tiLeDTB;
    private double diemBinhQuan;

    private boolean mienNN;
    private boolean GDPT;

    private Button bt_tinhKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("TÍNH BÌNH QUÂN ĐIỂM THI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tinh_binh_quan_diem_thi);
        khoiTaoBien();
        edtDiemTB12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ketqua = findViewById(R.id.txtKetQuaBQD);
                txtKetQua.setText("");
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
        edtDiemTB12 = findViewById(R.id.edtBQDDTB12);

        spinnerDiemKK = (Spinner) findViewById(R.id.spinnerBQDDiemKK);
        ArrayAdapter<String> adapterDiemKK = new ArrayAdapter<String>(this, R.layout.spinner_item_ndthanh, arrayDiemKK);
        adapterDiemKK.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerDiemKK.setAdapter(adapterDiemKK);
        spinnerDiemKK.setOnItemSelectedListener(new MyProcessEventDiemKK());

        spinnerDienUT = (Spinner) findViewById(R.id.spinnerBQDDienUuTien);
        ArrayAdapter<String> adapterDienUT = new ArrayAdapter<String>(this, R.layout.spinner_item_ndthanh, arrayDienUT);
        adapterDienUT.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerDienUT.setAdapter(adapterDienUT);
        spinnerDienUT.setOnItemSelectedListener(new MyProcessEventDienUT());

        spinnerTiLe = (Spinner) findViewById(R.id.spinnerBQDTiLe);
        ArrayAdapter<String> adapterTile = new ArrayAdapter<String>(this, R.layout.spinner_item_ndthanh, arrayTiLe);
        adapterTile.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerTiLe.setAdapter(adapterTile);
        spinnerTiLe.setOnItemSelectedListener(new MyProcessEventTiLe());

        checkboxMienNN = findViewById(R.id.cb_mienNN_BQD);
        checkboxGDPT = findViewById(R.id.cb_heGDPT_BQD);
        bt_tinhKetQua = findViewById(R.id.bt_tinhKetQua_BQDXetTN);
        txtKetQua = findViewById(R.id.txtKetQuaBQD);
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

    public void tinhKetQuaBQD(View view){

        if(checkboxMienNN.isChecked()){
            mienNN = true;
        }
        else{
            mienNN = false;
        }

        if(checkboxGDPT.isChecked()){
            GDPT = true;
        }
        else{
            GDPT = false;
        }

        try{
            DTB12 = Double.parseDouble(edtDiemTB12.getText().toString());
            if(DTB12 <= 0 || DTB12 > 10.0){
                Toast.makeText(TinhBinhQuanDiem.this, "Kiểm tra dữ liệu điểm trung bình!", Toast.LENGTH_SHORT).show();
            }
            else{
                if(diemUT == -1.0){
                    Toast.makeText(TinhBinhQuanDiem.this, "Chưa chọn Diện ưu tiên!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(diemKK == -1.0){
                        Toast.makeText(TinhBinhQuanDiem.this, "Chưa chọn điểm khuyến khích!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(tiLeThi == -1.0) {
                            Toast.makeText(TinhBinhQuanDiem.this, "Chưa chọn tỉ lệ điểm thi xét tốt nghiệp!", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            if(GDPT){
                                if(mienNN){
                                    diemBinhQuan = (1/tiLeThi)*(5 - ((tiLeThi*diemKK)/4) - (tiLeDTB*DTB12) - diemUT - ((tiLeThi*10)/4))/tiLeThi;
                                }
                                else{
                                    diemBinhQuan = (5 - ((tiLeThi*diemKK)/4) - (tiLeDTB*DTB12) - diemUT)/tiLeThi;
                                }
                            }
                            else{
                                diemBinhQuan = (5 - ((tiLeThi*diemKK)/4) - (tiLeDTB*DTB12) - diemUT)/tiLeThi;
                            }
                            diemBinhQuan = (double) Math.round(diemBinhQuan * 100)/100;
                            txtKetQua.setText("" + diemBinhQuan);
                        }
                    }
                }
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(TinhBinhQuanDiem.this, "Chưa nhập điểm trung bình cả năm lớp 12!", Toast.LENGTH_SHORT).show();
        }
    }
}