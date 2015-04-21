package moligamescr.pingpongthebeginner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MenuPrincipal extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        final ImageView botonJugar = (ImageView) findViewById(R.id.btn_juegoNuevo);
        final ImageView botonPractica = (ImageView) findViewById(R.id.btn_practica);
        final ImageView botonConfiguracion = (ImageView) findViewById(R.id.btn_configuracion);
        final ImageView botonPuntajes = (ImageView) findViewById(R.id.btn_puntajes);
        final ImageView botonAcercaDe = (ImageView) findViewById(R.id.btn_acercaDe);
        botonJugar.setOnClickListener(this);
        botonPractica.setOnClickListener(this);
        botonConfiguracion.setOnClickListener(this);
        botonPuntajes.setOnClickListener(this);
        botonAcercaDe.setOnClickListener(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_juegoNuevo:
                startActivity(new Intent(this, MenuPingPong.class));
                break;
            case R.id.btn_practica:
                startActivity(new Intent(this, Instrucciones.class));
                break;
            case R.id.btn_configuracion:
                startActivity(new Intent(this, Configuracion.class));
                break;
            case R.id.btn_puntajes:
                startActivity(new Intent(this, Puntajes.class));
                break;
            case R.id.btn_acercaDe:
                startActivity(new Intent(this, AcercaDe.class));
                break;
        }
    }

}
