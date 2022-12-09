package com.example.tarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class VisualizarInfo extends AppCompatActivity implements  Serializable {
    private Spinner spntarea;
    private ListView lstViewtarea;
    private ArrayAdapter<Tarea> adaptadorTareas;
    private ImageButton btnFilter;
    private String[] lasTareas;
    private ArrayList<Tarea> ArrayTareas;
    private TextInputLayout tilfilrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_info);
        //
        poblarArregloArrayList();
        referencias();
        eventos();
    }

    //region EVENTOS
    private void eventos() {
        lstViewtarea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int indice, long id) {
                Tarea t = ArrayTareas.get(indice);
                Log.d("TAG_", "Seleccioón en la lista de Tarea:");
                Log.d("TAG_", "Tarea :" + t.getTitulo());
            }
        });

        lstViewtarea.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(VisualizarInfo.this, "Desea eliminar a " + ArrayTareas.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo;
                titulo=tilfilrar.getEditText().getText().toString();
                Filtrar(titulo);
            }
        });
    }
    //endregion

    //region REFERENCIAS
    private  void referencias(){
        tilfilrar=findViewById(R.id.tilsearchTarea);
        btnFilter=findViewById(R.id.btnFilter);
        lstViewtarea =(ListView) findViewById(R.id.lstViewTareas);
        adaptadorTareas = new ArrayAdapter<Tarea>(this, android.R.layout.simple_list_item_1, ArrayTareas);
        lstViewtarea.setAdapter(adaptadorTareas);
    }
    //endregion
    //region ARRAYLIST STATIC
    public void poblarArregloArrayList() {
        ArrayTareas = new ArrayList<Tarea>();
        ArrayTareas.add(new Tarea("Mser","kil"));
        ArrayTareas.add(new Tarea("Mdr","kil"));

        for(int x = 0; x <=1; ++x){
            Tarea t = new Tarea();
            t.setTitulo(ArrayTareas.get(x).getTitulo() );
            t.setDescripcion(ArrayTareas.get(x).getDescripcion());
            ArrayTareas.add(t);

        }
        //get data  in the mainActity principal

        Tarea tra = (Tarea) getIntent().getExtras().getSerializable("la_tarea");
        ArrayTareas.add(tra);

    /*    ArrayList<Tarea> tr = (ArrayList<Tarea>) getIntent().getExtras().getSerializable("la_tarea");
        ArrayTareas.addAll(tr);*/
    }
    //endregion

    //region METODOS
    private  void Filtrar(String Titulo){
        Tarea t = new Tarea();
        for(int i=0;i<ArrayTareas.size();i++){
            if (!ArrayTareas.get(i).getTitulo().contains(Titulo)) {
                Toast.makeText(this, "No Existe"+Titulo, Toast.LENGTH_SHORT).show();
            } else {
                t.setTitulo(ArrayTareas.get(i).getTitulo() );
                Toast.makeText(this, "Existe"+Titulo, Toast.LENGTH_SHORT).show();
            }
        }

    }//endregion

}