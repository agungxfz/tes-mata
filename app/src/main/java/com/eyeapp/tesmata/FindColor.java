package com.eyeapp.tesmata;

import android.app.Activity;
import java.util.Random;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;
/**
 * Created by Yulian on 16/06/2016.
 */
public class FindColor extends Activity {
    private CountDownTimer timer;
    private long millis_pause;

    @Override
    public void onBackPressed() {
        timer.cancel();
        Data._score = 0;
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new mView(this).Show();

        SharedPreferences sharedPref = getSharedPreferences("HighScore",  Context.MODE_PRIVATE);
        TextView user_name           = (TextView) findViewById(R.id.txt_yourname);
        TextView tv_score            = (TextView) findViewById(R.id.tv_score);
        TextView tv_hscore           = (TextView) findViewById(R.id.tv_hscore);

        Data._highscore              = sharedPref.getInt("highscore", 0);

        user_name.setText("" + Data.myName);

        setFont(tv_score);
        tv_score.setText("00");

        setFont(tv_hscore);
        String s = String.format("%02d", Data._highscore);
        tv_hscore.setText("" + s);

        CreateMenu();
        CreateTimer(61000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    public void setFont(TextView id) {
        String fontPath;
        fontPath = "Molot.otf";
        Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);
        id.setTypeface(tf);
    }

    private void CreateTimer(long index) {

        final TextView tv_time = (TextView) findViewById(R.id.tv_time);
        timer = new CountDownTimer(index, 1000) {

            public void onTick(long millisUntilFinished) {
                String scr = String.format("%02d", millisUntilFinished / 1000);
                tv_time.setText(scr + "");
                millis_pause = millisUntilFinished;
            }

            public void onFinish() {
                tv_time.setText("00");
                final TextView time_up = (TextView) findViewById(R.id.time_up);
                setFont(time_up);
                Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.timeup);
                time_up.setText("Waktu habis!");
                time_up.setTextColor(Color.rgb(random(254, 1), random(254, 1),
                        random(148, 1)));
                time_up.startAnimation(anim);
                Data.check_pause = true;

                final Dialog d = new Dialog(FindColor.this, R.style.Transparent);
                d.setContentView(R.layout.game_over);
                d.getWindow().getAttributes().windowAnimations = R.style.Dialogdown;

                TextView score = (TextView) d.findViewById(R.id.score);
                setFont(score);
                //String s = String.format("%02d", Data._score);
                String s;
                if(Data._score <= 14){
                    s = "MATA KURANG SEHAT.\nHarap Segera Ke Dokter Mata";
                }else if(Data._score <= 23){
                    s = "MATA LEMAH.\nMata Anda Membutuhkan Istirahat Yang Cukup";
                }else if(Data._score <= 34){
                    s = "MATA NORMAL.\nMata Anda Adalah Mata Yang Sehat";
                }else{
                    s = "MATA TAJAM.\nAnda Memiliki Kemampuan Mata Yang Hebat";
                }
                score.setText("Kondisi Mata Anda : " + s);

                Button newgame = (Button) d.findViewById(R.id.bt_newgame);
                newgame.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        time_up.setText("");
                        time_up.clearAnimation();
                        CreateTimer(61000);
                        Data._score = 0;
                        LinearLayout show_view = (LinearLayout) findViewById(R.id.show_view);
                        show_view.removeAllViews();
                        new mView(FindColor.this).Show();
                        d.cancel();
                    }
                });

                d.show();
            }
        }.start();
    }

    private void CreateMenu() {

        final LinearLayout l = (LinearLayout) findViewById(R.id.L1);
        final Button pause = (Button) findViewById(R.id.bt_pause);

        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            if (Data.check_pause) {
                CreateTimer(millis_pause);
                Data.check_pause = false;
                pause.setText("Tunda");
            } else {
                timer.cancel();
                Data.check_pause = true;
                pause.setText("Main");
            }
            }
        });

        Button exit = (Button) findViewById(R.id.bt_exit);
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            timer.cancel();
            finish();
            }
        });

        final Button btSetting = (Button) findViewById(R.id.im_setting);
        btSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            PopupMenu setting = new PopupMenu(FindColor.this, btSetting);
            setting.getMenuInflater().inflate(R.menu.popup_menu,  setting.getMenu());
            setting.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                int it = item.getItemId();
                switch (it) {
                    case R.id.Save:
                        break;
                    case R.id.Load:
                        break;
                    case R.id.Exit:
                        break;
                    default:
                        break;
                }
                return true;
                }
            });
            setting.show();
            }
        });
    }

    private int random(int u, int v) {
        Random r = new Random();
        return (int) (Math.abs(r.nextInt()) % u + v);
    }
}
