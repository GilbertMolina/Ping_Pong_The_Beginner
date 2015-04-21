package moligamescr.pingpongthebeginner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Puntajes extends ActionBarActivity implements View.OnClickListener {
    private static SharedPreferences prefsPuntajes;
    private static final String highEasyScoreKey = "highEasyScoreKey";
    private static final String highMediumScoreKey = "highMediumScoreKey";
    private static final String highHardScoreKey = "highHardScoreKey";
    private static int highEasyScore = 0;
    private static int highMediumScore = 0;
    private static int highHardScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);

        prefsPuntajes = getPreferences(Activity.MODE_PRIVATE);

        TextView txtPuntajeFacil = (TextView) findViewById(R.id.txtPuntajeFacil);
        TextView txtPuntajeMedio = (TextView) findViewById(R.id.txtPuntajeMedio);
        TextView txtPuntajeDificil = (TextView) findViewById(R.id.txtPuntajeDificil);

        ImageView btnRestablecerPuntuaciones = (ImageView) findViewById(R.id.btn_restablecer_puntuaciones);
        btnRestablecerPuntuaciones.setOnClickListener(this);

        highEasyScore = retrieveHighEasyScore();
        txtPuntajeFacil.setText(Integer.toString(highEasyScore));
        highMediumScore = retrieveHighMediumScore();
        txtPuntajeMedio.setText(Integer.toString(highMediumScore));
        highHardScore = retrieveHighHardScore();
        txtPuntajeDificil.setText(Integer.toString(highHardScore));

        Log.d("---->EasyScore", "" + getHighEasyScore());
        Log.d("---->MediumScore", "" + getHighMediumScore());
        Log.d("---->HardScore", "" + getHighHardScore());

    }

    private int retrieveHighEasyScore(){
        return prefsPuntajes.getInt(highEasyScoreKey, 0);
    }

    private int retrieveHighMediumScore(){
        return prefsPuntajes.getInt(highMediumScoreKey, 0);
    }

    private int retrieveHighHardScore(){
        return prefsPuntajes.getInt(highHardScoreKey, 0);
    }

    public static int getHighEasyScore(){
        return highEasyScore;
    }

    public static void setHighEasyScore(int highScore){
        Puntajes.highEasyScore = highScore;
        Editor editorHighEasyScore = prefsPuntajes.edit();
        editorHighEasyScore.putInt(highEasyScoreKey, highScore);
        editorHighEasyScore.commit();
    }

    public static int getHighMediumScore(){
        return highMediumScore;
    }

    public static void setHighMediumScore(int highScore){
        Puntajes.highMediumScore = highScore;
        Editor editorHighMediumScore = prefsPuntajes.edit();
        editorHighMediumScore.putInt(highMediumScoreKey, highScore);
        editorHighMediumScore.commit();
    }

    public static int getHighHardScore(){
        return highHardScore;
    }

    public static void setHighHardScore(int highScore){
        Puntajes.highHardScore = highScore;
        Editor editorHighHardScore = prefsPuntajes.edit();
        editorHighHardScore.putInt(highHardScoreKey, highScore);
        editorHighHardScore.commit();
    }

    public static boolean IsNewHighEasyScore(int highScore){
        if (getHighEasyScore() < highScore){
            setHighEasyScore(highScore);
            return true;
        } else {
            return false;
        }
    }

    public static boolean IsNewHighMediumScore(int highScore){
        if (getHighMediumScore() < highScore){
            setHighMediumScore(highScore);
            return true;
        } else {
            return false;
        }
    }

    public static boolean IsNewHighHardScore(int highScore){
        if (getHighHardScore() < highScore){
            setHighHardScore(highScore);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_restablecer_puntuaciones:
                restablecerPuntuaciones();
            break;
        }
    }

    private void restablecerPuntuaciones(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea restablecer las puntuaciones?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setHighEasyScore(0);
                        setHighMediumScore(0);
                        setHighHardScore(0);

                        TextView txtPuntajeFacil = (TextView) findViewById(R.id.txtPuntajeFacil);
                        TextView txtPuntajeMedio = (TextView) findViewById(R.id.txtPuntajeMedio);
                        TextView txtPuntajeDificil = (TextView) findViewById(R.id.txtPuntajeDificil);
                        txtPuntajeFacil.setText("" + getHighEasyScore());
                        txtPuntajeMedio.setText("" + getHighMediumScore());
                        txtPuntajeDificil.setText("" + getHighHardScore());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
