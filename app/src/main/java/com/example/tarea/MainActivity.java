package com.example.tarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements  Serializable {
    private Button btnIngresar,btnModificar;
    private ImageButton btnBuscar;
    private Spinner spntarea;
    private TextInputLayout tilTitulo, tildescription;
    private String[] tblInfo;
    private ArrayList<Tarea> ArrayTareas;
    private VisualizarInfo secundoClass;
    private ArrayList<Tarea> lasTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyArray();
        referencias();
        eventos();
    }

    //region METODOS
    private  void ObtenerData(){
        String titulo, description;
       lasTareas=new ArrayList<Tarea>();
        // get text from textInputLayout
        titulo = tilTitulo.getEditText().getText().toString();
        description = tildescription.getEditText().getText().toString();
        //TODO: parsear y asignar al tarea
        Tarea tra = new Tarea(titulo,description);
        MyArray().add(tra);
        Intent mostrarInfo = new Intent(this,VisualizarInfo.class);
        mostrarInfo.putExtra("la_tarea",tra);
        startActivity(mostrarInfo);
    }
    private void camposRequired(){
        if (TextUtils.isEmpty(tilTitulo.getEditText().getText().toString()) ||TextUtils.isEmpty(tildescription.getEditText().getText().toString())){
            Toast.makeText(MainActivity.this, "descripción y titulo son obligatorios!",
                    Toast.LENGTH_SHORT).show();
        }else{
            ObtenerData();
        }

    }

    private ArrayList<Tarea> MyArray(){
        ArrayTareas = new ArrayList<Tarea>();
        ArrayTareas.add(new Tarea("Mser","kil"));
        ArrayTareas.add(new Tarea("bali","yj"));
        ArrayTareas.add(new Tarea("Mali","sil"));
        ArrayTareas.add(new Tarea("Baseli","lil"));
        return ArrayTareas;
    }

    private void buscarTareaModificar( String Titulo) {
        Tarea t = new Tarea();
        for (int i = 0; i < MyArray().size(); i++) {
            if (!MyArray().get(i).getTitulo().contains(Titulo)) {
                Toast.makeText(this, "No Existe" + Titulo, Toast.LENGTH_SHORT).show();
            } else {
                t.setTitulo(MyArray().get(i).getTitulo()+"Modify");
                Toast.makeText(this, "Existe" + Titulo, Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void DataToList(){
        ArrayTareas = new ArrayList<Tarea>();
        ArrayTareas.add(new Tarea("Mser","kil"));
        ArrayTareas.add(new Tarea("bali","yj"));
        ArrayTareas.add(new Tarea("Mali","sil"));
        ArrayTareas.add(new Tarea("Baseli","lil"));

        Intent mostrarInfo = new Intent(this,VisualizarInfo.class);
        mostrarInfo.putExtra("la_tarea",ArrayTareas);
        startActivity(mostrarInfo);
    }
    //endregion

    //region EVENTOS
    private void eventos() {
    btnIngresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            camposRequired();

        }
    });

    btnModificar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String titulo;
            titulo=tilTitulo.getEditText().getText().toString();
            buscarTareaModificar(tilTitulo.getEditText().getText().toString());
        }
    });

    btnBuscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataToList();
        }
    });
    }


    //endregion

    //region REFERENCIAS
    private void referencias(){
        tilTitulo=findViewById(R.id.tilTitulo);
        tildescription=findViewById(R.id.tilDescription);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnModificar=findViewById(R.id.btnModificar);
        btnBuscar=findViewById(R.id.btnBuscar);
        }
    //endregion





}