package vn.edu.camau.examsoresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_thiNghe;
    Button bt_thiThu12;
    Button bt_thiTHPT12;
    Button bt_tinhBinhQuanDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //getSupportActionBar().setTitle("ỨNG DỤNG TRA CỨU KẾT QUẢ THI");
        setContentView(R.layout.activity_main);

        bt_thiNghe = findViewById(R.id.bt_thinghe);
        bt_thiNghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNghe = new Intent(MainActivity.this, TraDiemNghe.class);
                startActivity(intentNghe);
            }
        });

        bt_thiThu12 = findViewById(R.id.bt_thithu12);
        bt_thiThu12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTT12 = new Intent(MainActivity.this, TraDiemThiThu12.class);
                startActivity(intentTT12);
            }
        });

        bt_thiTHPT12 = findViewById(R.id.bt_thithpt);
        bt_thiTHPT12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTN12 = new Intent(MainActivity.this, TraDiemThiTN12.class);
                startActivity(intentTN12);
            }
        });

        bt_tinhBinhQuanDiem = findViewById(R.id.bt_tinhBinhQuanDiemThi);
        bt_tinhBinhQuanDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBQD = new Intent(MainActivity.this, TinhBinhQuanDiem.class);
                startActivity(intentBQD);
            }
        });
    }
}