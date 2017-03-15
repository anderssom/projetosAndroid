package com.espm.json;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spMarcas;
    private Spinner spVeiculos;
    private Spinner spModelos;
    private Button btn_verificar;
    private List<Marca> dados;
    private List<Veiculo> nomesV;
    private List<Modelo> modV;
    private ArrayAdapter<Modelo> adpm;
    private ArrayAdapter<Veiculo> adpv;
    private ArrayAdapter<Marca> adp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spMarcas = (Spinner) findViewById(R.id.spMarcas);
        spMarcas.setOnItemSelectedListener(this);
        spVeiculos = (Spinner) findViewById(R.id.spVeiculos);
        spVeiculos.setOnItemSelectedListener(this);
        spModelos = (Spinner) findViewById(R.id.spModelos);
        btn_verificar = (Button) findViewById(R.id.btn_verificar);

        String url = "http:///fipeapi.appspot.com/api/1/carros/marcas.json";

        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dados = new ArrayList<Marca>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        dados.add(new Marca(obj.getInt("id"), obj.getString("name")));
                        //Log.i("JSON", String.valueOf(dados));
                        //Log.i("JSON", String.valueOf(obj));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adp = new ArrayAdapter<Marca>(getApplicationContext(), android.R.layout.simple_spinner_item, dados);
                spMarcas.setAdapter(adp);

                //Log.i("JSON", String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Log.i("Spinner: ", String.valueOf(parent.getId()));

        //Log.i("Teste:", "teste");
        if(parent.getId() == R.id.spMarcas){
            Marca sel = (Marca) parent.getItemAtPosition(position);
            int idParam = sel.getId();
            Log.i("ID", String.valueOf(idParam));

            String url = "http:///fipeapi.appspot.com/api/1/carros/veiculos/"+idParam+".json";

            JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    nomesV = new ArrayList<Veiculo>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            nomesV.add(new Veiculo(obj.getString("name")));
                            //Log.i("JSON", String.valueOf(nomesV));
                            Log.i("JSON", String.valueOf(obj));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adpv = new ArrayAdapter<Veiculo>(getApplicationContext(), android.R.layout.simple_spinner_item, nomesV);
                    spVeiculos.setAdapter(adpv);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsRequest);

        }

        if(parent.getId() == R.id.spVeiculos){
            Veiculo vsel = (Veiculo) parent.getItemAtPosition(position);
            int idVeiculo = vsel.getId();
            Log.i("ID", String.valueOf(idVeiculo));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
