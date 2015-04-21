package moligamescr.pingpongthebeginner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.Random;

public class Instrucciones extends ActionBarActivity {
    private MediaPlayer mp;
    public static String SwitchSilencio = "ValorSwitchSilencioSeleccionado";
    private SharedPreferences prefsInstrucciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        this.prefsInstrucciones = getSharedPreferences(SwitchSilencio, MODE_PRIVATE);
        this.mp = new MediaPlayer();

        if (cargarPrefenciasValorSwitchSilencio()){
            inicializarMusica();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp.isPlaying()){
            mp.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()){
            mp.stop();
            mp.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()){
            mp.stop();
            mp.release();
        }
    }

    public void inicializarMusica() {
        Random r = new Random();
        int numeroCancionAleatoria = r.nextInt(9)+1;
        switch (numeroCancionAleatoria){
            case 1:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song01);
                break;
            case 2:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song02);
                break;
            case 3:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song03);
                break;
            case 4:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song04);
                break;
            case 5:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song05);
                break;
            case 6:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song06);
                break;
            case 7:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song07);
                break;
            case 8:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song08);
                break;
            case 9:
                mp = MediaPlayer.create(Instrucciones.this, R.raw.song09);
                break;
        }
        mp.start();
    }

    public boolean cargarPrefenciasValorSwitchSilencio(){
        this.prefsInstrucciones = getSharedPreferences(SwitchSilencio, MODE_PRIVATE);
        return prefsInstrucciones.getBoolean("valorSwitchSilencio", true);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MenuPrincipal.class));
    }

}
