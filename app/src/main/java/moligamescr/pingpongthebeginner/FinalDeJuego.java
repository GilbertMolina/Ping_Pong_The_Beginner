package moligamescr.pingpongthebeginner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalDeJuego extends ActionBarActivity implements View.OnClickListener {
    public boolean IsNewHighEasyScore = false;
    public boolean IsNewHighMediumScore = false;
    public boolean IsNewHighHardScore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_de_juego);

        ocultarBotonesDeRecord();

        final ImageView jugarDeNuevo = (ImageView) findViewById(R.id.btn_jugar_de_nuevo);
        jugarDeNuevo.setOnClickListener(this);

        TextView txtMarcadorFinal = (TextView) findViewById(R.id.txtMarcadorFinal);
        txtMarcadorFinal.setText(Integer.toString(PingPongView.puntaje_obtenido));

        verificarPuntajesMayoresAlmacenados();
    }

    public void verificarPuntajesMayoresAlmacenados(){
        switch (MenuPingPong.nivelDificultadSeleccionada){
            case "facil":
                IsNewHighEasyScore = Puntajes.IsNewHighEasyScore(PingPongView.puntaje_obtenido);
                if (IsNewHighEasyScore){
                    ImageView msgRecordFacil = (ImageView) findViewById(R.id.msg_record_facil);
                    msgRecordFacil.setVisibility(View.VISIBLE);
                }
                break;
            case "media":
                IsNewHighMediumScore = Puntajes.IsNewHighMediumScore(PingPongView.puntaje_obtenido);
                if (IsNewHighMediumScore){
                    ImageView msgRecordMedia = (ImageView) findViewById(R.id.msg_record_media);
                    msgRecordMedia.setVisibility(View.VISIBLE);
                }
                break;
            case "dificil":
                IsNewHighHardScore = Puntajes.IsNewHighHardScore(PingPongView.puntaje_obtenido);
                if (IsNewHighHardScore){
                    ImageView msgRecordDificil = (ImageView) findViewById(R.id.msg_record_dificil);
                    msgRecordDificil.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_jugar_de_nuevo:
                PingPongView.puntaje_obtenido = 0;
                startActivity(new Intent(this, MenuPingPong.class));
                finish();
                break;
        }
    }

    public void ocultarBotonesDeRecord(){
        ImageView msgRecordFacil = (ImageView) findViewById(R.id.msg_record_facil);
        msgRecordFacil.setVisibility(View.INVISIBLE);
        ImageView msgRecordMedia = (ImageView) findViewById(R.id.msg_record_media);
        msgRecordMedia.setVisibility(View.INVISIBLE);
        ImageView msgRecordDificil = (ImageView) findViewById(R.id.msg_record_dificil);
        msgRecordDificil.setVisibility(View.INVISIBLE);
    }

}
