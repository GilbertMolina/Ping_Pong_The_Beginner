package moligamescr.pingpongthebeginner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuPingPong extends ActionBarActivity implements View.OnClickListener {
    public static float velocidadDificultadFacil = 5f;
    public static float velocidadDificultadMedia = 10f;
    public static float velocidadDificultadDificil = 15f;
    public static float velocidadDificultadSeleccionada;
    public static String nivelDificultadSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ping_pong);
        final ImageView botonDificultadFacil = (ImageView) findViewById(R.id.btn_dificultad_facil);
        final ImageView botonDificultadMedia = (ImageView) findViewById(R.id.btn_dificultad_media);
        final ImageView botonDificultadDificil = (ImageView) findViewById(R.id.btn_dificultad_dificil);
        botonDificultadFacil.setOnClickListener(this);
        botonDificultadMedia.setOnClickListener(this);
        botonDificultadDificil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, PingPong.class));
        switch (v.getId()){
            case R.id.btn_dificultad_facil:
                PingPongView.puntaje_obtenido = 0;
                nivelDificultadSeleccionada = "facil";
                velocidadDificultadSeleccionada = velocidadDificultadFacil;
                break;
            case R.id.btn_dificultad_media:
                PingPongView.puntaje_obtenido = 0;
                nivelDificultadSeleccionada = "media";
                velocidadDificultadSeleccionada = velocidadDificultadMedia;
                break;
            case R.id.btn_dificultad_dificil:
                PingPongView.puntaje_obtenido = 0;
                nivelDificultadSeleccionada = "dificil";
                velocidadDificultadSeleccionada = velocidadDificultadDificil;
                break;
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MenuPrincipal.class));
    }

}
