package com.alejo_zr.exceldb.Segmento.Rigido;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alejo_zr.exceldb.BaseDatos;
import com.alejo_zr.exceldb.R;
import com.alejo_zr.exceldb.utilidades.Utilidades;

public class RegistroSegmentoRigiActivity extends AppCompatActivity {


    private EditText campoNCalzadas, campoNCarriles, campoEspesorLosa, campoAnchoBerma, campoPRI, campoPRF, campoComentarios;
    private TextView tvId_Carretera_Segmento,tvNombre_Carretera_Segmento;
    private TextInputLayout input_camponCalzadas,input_campoNCarriles,input_campoEspesorLosa,input_campoAnchoBerma,input_campoPRI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_segmento_rigi);


        campoNCalzadas = (EditText) findViewById(R.id.campoNCalzadasRigi);
        campoNCarriles = (EditText) findViewById(R.id.campoNCarrilesRigi);
        campoEspesorLosa = (EditText) findViewById(R.id.EspesorLosaSegmentoRigiActivity);
        campoAnchoBerma = (EditText) findViewById(R.id.campoAnchoBermaRigi);
        campoPRI = (EditText) findViewById(R.id.campoPRIRigi);
        campoPRF = (EditText) findViewById(R.id.campoPRFRigi);
        campoComentarios = (EditText) findViewById(R.id.campoComentariosRigi);

        tvId_Carretera_Segmento = (TextView) findViewById(R.id.tvId_Carretera_SegmentoRigi);
        tvNombre_Carretera_Segmento = (TextView) findViewById(R.id.tvNombre_Carretera_SegmentoRigi);

        input_camponCalzadas = (TextInputLayout) findViewById(R.id.input_camponCalzadasRigi);
        input_campoNCarriles= (TextInputLayout) findViewById(R.id.input_campoNCarrilesRigi );
        input_campoEspesorLosa= (TextInputLayout) findViewById(R.id.input_campoEspesorLosaRigi );
        input_campoAnchoBerma= (TextInputLayout) findViewById(R.id.input_campoAnchoBermaRigi);
        input_campoPRI= (TextInputLayout) findViewById(R.id.input_campoPRIRigi);


        Bundle bundle = getIntent().getExtras();
        String dato_nom = bundle.getString("nom_carretera").toString();
        tvNombre_Carretera_Segmento.setText(dato_nom);


    }

    public void onClick(View view) {
        verificarDatos();

    }

    /**Se verifica que los datos minimos sean diligenciados**/

    private void verificarDatos() {
        boolean isValid = true;
        if(campoNCalzadas.getText().toString().trim().isEmpty()){
            input_camponCalzadas.setError("Ingrese el número de calzadas");
            isValid=false;
        }else{
            input_camponCalzadas.setErrorEnabled(false);
        }
        if(campoNCarriles.getText().toString().trim().isEmpty()){
            input_campoNCarriles.setError("Ingrese el número de carriles");
            isValid=false;
        }else{
            input_campoNCarriles.setErrorEnabled(false);
        }
        if(campoEspesorLosa.getText().toString().trim().isEmpty()){
            input_campoEspesorLosa.setError("Ingrese el espesor de la losa");
            isValid=false;
        }else{
            input_campoEspesorLosa.setErrorEnabled(false);
        }
        if(campoAnchoBerma.getText().toString().trim().isEmpty()){
            input_campoAnchoBerma.setError("Ingrese el ancho de la berma");
            isValid=false;
        }else{
            input_campoAnchoBerma.setErrorEnabled(false);
        }
        if(campoPRI.getText().toString().trim().isEmpty()){
            input_campoPRI.setError("Ingrese el PRI");
            isValid=false;
        }else{
            input_campoPRI.setErrorEnabled(false);
        }


        if(isValid){
            registrarSegmento();
        }
    }

    private void registrarSegmento() {

        BaseDatos bd=new BaseDatos(this);

        SQLiteDatabase db=bd.getWritableDatabase();

        String insert="INSERT INTO "+ Utilidades.SEGMENTORIGI.TABLA_SEGMENTO
                +" ( "+Utilidades.SEGMENTORIGI.CAMPO_NOMBRE_CARRETERA_SEGMENTO+","+Utilidades.SEGMENTORIGI.CAMPO_CALZADAS_SEGMENTO+","
                +Utilidades.SEGMENTORIGI.CAMPO_CARRILES_SEGMENTO+","+Utilidades.SEGMENTORIGI.CAMPO_ESPESOR_LOSA+","+Utilidades.SEGMENTORIGI.CAMPO_ANCHO_BERMA+","+Utilidades.SEGMENTORIGI.CAMPO_PRI_SEGMENTO+","
                +Utilidades.SEGMENTORIGI.CAMPO_PRF_SEGMENTO+","+Utilidades.SEGMENTORIGI.CAMPO_COMENTARIOS+")" +
                " VALUES ('"+tvNombre_Carretera_Segmento.getText().toString()+"' , '"
                +campoNCalzadas.getText().toString()+"' , '"+campoNCarriles.getText().toString()+"' , '"+campoEspesorLosa.getText().toString()+"' , '"
                +campoAnchoBerma.getText().toString()+"' , '"+campoPRI.getText().toString()+"' , '"+campoPRF.getText().toString()+"' , '"
                +campoComentarios.getText().toString()+"')";

        db.execSQL(insert);
        Toast.makeText(getApplicationContext(),R.string.regisSeg,Toast.LENGTH_SHORT).show();

        db.close();


    }
}
