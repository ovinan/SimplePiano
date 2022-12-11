package com.example.simplepiano;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SoundPool misSonidos;
    private Integer intIDSonido_DO,intIDSonido_RE,intIDSonido_MI,intIDSonido_FA,intIDSonido_SOL,intIDSonido_LA,intIDSonido_SI;
    private boolean loaded = false; // Flag para saber si la carga asincrona de los sonidos ha finalizado
    // Parametros de la reproduccion de los sonidos:
    private int volumenIzda = 1;
    private int volumenDcha = 1;
    private int prioridadSonido = 1;
    private int loopSonido = 0;
    private int rateSonido = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creo el SoundPool y cargo los sonidos en el
        // NOTAR que cargaremos varios sonidos en el Pool,
        // despues reproduciremos el que queramos.
        crearPoolSonidos();
    }

    //********** Pulsaciones de cada uno de los botones del piano  **************************************
    public void btnDo(View view){
        misSonidos.play(intIDSonido_DO, volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    public void btnRe(View view){
        misSonidos.play(intIDSonido_RE, volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    public void btnMi(View view){
        misSonidos.play(intIDSonido_MI, volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    public void btnFa(View view){
        misSonidos.play(intIDSonido_FA, volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    public void btnSol(View view){
        misSonidos.play(intIDSonido_SOL,volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    public void btnLa(View view){
        misSonidos.play(intIDSonido_LA, volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    public void btnSi(View view){
        misSonidos.play(intIDSonido_SI, volumenIzda,volumenDcha, prioridadSonido,loopSonido,rateSonido);
    }

    // *************** Carga del Pool de sonidos *************************************
    // NOTA: Los archivos de sonido estaran en la carpeta RAW del directorio de recursos RES
    private void crearPoolSonidos() {
        // Segun la version del SDK del dispostivo, el constructor para el SoundPool es uno u otro
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            misSonidos = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
        }
        else
        {
            misSonidos = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        intIDSonido_DO = misSonidos.load(this, R.raw.c3do, 1);
        intIDSonido_RE = misSonidos.load(this, R.raw.d3re, 1);
        intIDSonido_MI = misSonidos.load(this, R.raw.e3mi, 1);
        intIDSonido_FA = misSonidos.load(this, R.raw.f3fa, 1);
        intIDSonido_SOL = misSonidos.load(this, R.raw.g3sol, 1);
        intIDSonido_LA = misSonidos.load(this, R.raw.a3la, 1);
        intIDSonido_SI = misSonidos.load(this, R.raw.b3si, 1);
        // Como la carga es asincrona (la ejecucion del programa no se detiene mientras se
        // esta cargando, actualizamos al final la variable de que todo se ha cargado OK
        misSonidos.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status)
            {
                loaded = true;
            }
        });
    }
}