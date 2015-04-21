package moligamescr.pingpongthebeginner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AcercaDe extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        obtenerVersionAplicacion();
    }

    public void obtenerVersionAplicacion(){
        String versionName = BuildConfig.VERSION_NAME;
        TextView txtVersionVideoJuego = (TextView) findViewById(R.id.txtVersion);
        txtVersionVideoJuego.setText(versionName);
    }

}
