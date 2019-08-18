package com.eyeapp.tesmata;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by Yulian on 13/06/2016.
 */
public class SoalBT extends Activity {
    Database    db;

    ImageView   imgSoal;
    RadioGroup  a0;
    RadioButton rdoSoal1, rdoSoal2, rdoSoal3, rdoSoal4, rdoJawaban;
    Button      btn_OK;

    ArrayList<String> soal1;
    ArrayList<String> soal2;
    ArrayList<String> soal3;
    ArrayList<String> soal4;
    ArrayList<String> img;
    ArrayList<String> jawaban;

    int img_res;

    int n, i, skor;
    double hasil;
    String ket;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_soal_butawarna);

        imgSoal  = (ImageView) findViewById(R.id.imgLoadGambarSoal);
        a0       = (RadioGroup) findViewById(R.id.rdoGroupBT);
        rdoSoal1 = (RadioButton) findViewById(R.id.rdoJawaban_bt_1);
        rdoSoal2 = (RadioButton) findViewById(R.id.rdoJawaban_bt_2);
        rdoSoal3 = (RadioButton) findViewById(R.id.rdoJawaban_bt_3);
        rdoSoal4 = (RadioButton) findViewById(R.id.rdoJawaban_bt_4);
        btn_OK   = (Button) findViewById(R.id.btnJawab_bt);

        img      = new ArrayList<String>();
        soal1    = new ArrayList<String>();
        soal2    = new ArrayList<String>();
        soal3    = new ArrayList<String>();
        soal4    = new ArrayList<String>();
        jawaban  = new ArrayList<String>();

        db       = new Database(this);
        Cursor c = db.getSoalBT();

        c.moveToFirst(); n = 0;
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("soal")) != null) {
                img.add("drawable/" + c.getString(c.getColumnIndex("soal")));
            } else {
                img.add("drawable/image_not_found.gif");
            }

            soal1.add(c.getString(c.getColumnIndex("jawab_1")));
            soal2.add(c.getString(c.getColumnIndex("jawab_2")));
            soal3.add(c.getString(c.getColumnIndex("jawab_3")));
            soal4.add(c.getString(c.getColumnIndex("jawab_4")));
            jawaban.add(c.getString(c.getColumnIndex("kunci")));
            c.moveToNext(); n++;
        }

        rdoSoal1.setText(soal1.get(0).toString());
        rdoSoal2.setText(soal2.get(0).toString());
        rdoSoal3.setText(soal3.get(0).toString());
        rdoSoal4.setText(soal4.get(0).toString());
        img_res = getResources().getIdentifier(img.get(0).toString(), null, getPackageName());

        Bitmap soal = BitmapFactory.decodeResource(getResources(), img_res);
        imgSoal.setImageBitmap(soal);

        skor = 0; i = 0;
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int idTerpilih = a0.getCheckedRadioButtonId();
            rdoJawaban = (RadioButton) findViewById(idTerpilih);

            if (rdoJawaban.getText().toString().equals(jawaban.get(i).toString())){
                skor++;
            }

            i++;
            if (i < n) {
                rdoSoal1.setText(soal1.get(i).toString());
                rdoSoal2.setText(soal2.get(i).toString());
                rdoSoal3.setText(soal3.get(i).toString());
                rdoSoal4.setText(soal4.get(i).toString());
                img_res = getResources().getIdentifier(img.get(i).toString(), null, getPackageName());

                Bitmap soal = BitmapFactory.decodeResource(getResources(), img_res);
                imgSoal.setImageBitmap(soal);
                a0.clearCheck();
            } else {
                AlertDialog.Builder build = new AlertDialog.Builder(SoalBT.this);
                hasil = skor * 5;
                if(hasil <= 25){
                    ket = "BUTA WARNA TOTAL";
                }else if(hasil <= 75){
                    ket = "BUTA WARNA PARTIAL";
                }else{
                    ket = "MATA NORMAL";
                }
                build.setMessage("Nilai anda: " + String.valueOf(hasil) + "\n Kondisi mata anda saat ini : " + ket);
                build.setCancelable(false);
                build.setPositiveButton("Kembali ke Menu Utama", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alert = build.create();
                alert.show();
            }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.soal_bt, menu);
        return true;
    }
}