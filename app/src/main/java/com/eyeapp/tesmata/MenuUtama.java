package com.eyeapp.tesmata;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yulian on 12/06/2016.
 */
public class
MenuUtama extends Activity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.menu_utama);

        Button tentang_app = (Button) findViewById(R.id.btnTentang);
        Button keluar = (Button) findViewById(R.id.btnKeluar);
        Button buta_warna = (Button) findViewById(R.id.btnButaWarna);
        Button visus_mata = (Button) findViewById(R.id.btnVisus);
        Button kuku_kube = (Button) findViewById(R.id.btnKukuKube);

        tentang_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), TentangApp.class);
                startActivity(myIntent);
            }
        });

        buta_warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SoalBT.class);
                startActivity(myIntent);
            }
        });

        visus_mata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SoalVS.class);
                startActivity(myIntent);
            }
        });

        kuku_kube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), FindColor.class);
                startActivity(myIntent);
            }
        });
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
    }
}
