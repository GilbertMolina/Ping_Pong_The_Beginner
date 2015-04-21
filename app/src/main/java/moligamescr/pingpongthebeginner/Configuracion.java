package moligamescr.pingpongthebeginner;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Configuracion extends ActionBarActivity {

    private SeekBar sbBrillo, sbVolumen;
    private AudioManager audioManager;
    private ContentResolver cResolver;
    private Window window;
    private int valorBrillo;
    private TextView txtPorcentajeBrillo, txtPorcentajeVolumen;
    private Switch swSilenciarVolumen;
    public static String SwitchSilencio = "ValorSwitchSilencioSeleccionado";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        this.swSilenciarVolumen = (Switch) findViewById(R.id.swSilenciarSonido);
        this.sp = getSharedPreferences(SwitchSilencio, MODE_PRIVATE);
        cargarPreferenciasDeUsuario();
        inicializarBarraDeBrillo();
        inicializarBarraDeVolumen();
        cargarPorcentajesBarras();
        inicializarSwitchVolumen();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    public void cargarPreferenciasDeUsuario(){
        this.sp = getSharedPreferences(SwitchSilencio, MODE_PRIVATE);
        boolean valorSwitchSilencio = sp.getBoolean("valorSwitchSilencio", true);
        this.swSilenciarVolumen.setChecked(valorSwitchSilencio);
        if (this.swSilenciarVolumen.isChecked()){
            //this.swSilenciarVolumen.setText("Activado");
        }else{
            //this.swSilenciarVolumen.setText("Desactivado");
        }
    }

    public void inicializarBarraDeBrillo(){
        sbBrillo = (SeekBar) findViewById(R.id.sbBarraBrillo);
        txtPorcentajeBrillo = (TextView) findViewById(R.id.txtPorcentajeBrillo);
        cResolver = getContentResolver();
        window = getWindow();
        sbBrillo.setMax(255);
        sbBrillo.setKeyProgressIncrement(1);
        try {
            valorBrillo = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        }catch (Exception e){
            Log.e("Error: ", "No se puede acceder al brillo del dispositivo.");
            e.printStackTrace();
        }

        sbBrillo.setProgress(valorBrillo);

        sbBrillo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 20) {
                    valorBrillo = 20;
                } else {
                    valorBrillo = progress;
                }
                float porcentaje = (valorBrillo / (float) 255) * 100;
                txtPorcentajeBrillo.setText((int) porcentaje + "%");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, valorBrillo);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.screenBrightness = valorBrillo / (float) 255;
                window.setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void inicializarBarraDeVolumen(){
        sbVolumen = (SeekBar) findViewById(R.id.sbBarraVolumenMusica);
        txtPorcentajeVolumen = (TextView) findViewById(R.id.txtPorcentajeVolumen);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final int maximoVolumen = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int actualVolumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sbVolumen.setMax(maximoVolumen);
        sbVolumen.setProgress(actualVolumen);

        sbVolumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                float porcentaje = (progress / (float) maximoVolumen) * 100;
                txtPorcentajeVolumen.setText((int) porcentaje + "%");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void cargarPorcentajesBarras(){
        txtPorcentajeBrillo = (TextView) findViewById(R.id.txtPorcentajeBrillo);
        txtPorcentajeVolumen = (TextView) findViewById(R.id.txtPorcentajeVolumen);
        int brilloActual = 0, actualVolumen = 0, maximoVolumen = 0;
        try {
            brilloActual = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
            actualVolumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            maximoVolumen = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float porcentajeBrillo = (brilloActual / (float) 255) * 100;
        float porcentajeVolumen = (actualVolumen / (float) maximoVolumen) * 100;
        txtPorcentajeBrillo.setText((int) porcentajeBrillo + "%");
        txtPorcentajeVolumen.setText((int) porcentajeVolumen + "%");
    }

    public void inicializarSwitchVolumen(){
        this.swSilenciarVolumen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    swSilenciarVolumen.setChecked(true);
                    //swSilenciarVolumen.setText("Activado");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("valorSwitchSilencio", swSilenciarVolumen.isChecked());
                    editor.apply();
                }else{
                    swSilenciarVolumen.setChecked(false);
                    //swSilenciarVolumen.setText("Desactivado");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("valorSwitchSilencio", swSilenciarVolumen.isChecked());
                    editor.apply();
                }
            }
        });
    }

}
