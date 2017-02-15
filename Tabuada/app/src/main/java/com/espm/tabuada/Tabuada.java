package com.espm.tabuada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;
import java.util.StringTokenizer;

public class Tabuada extends AppCompatActivity {

    private TextView txtN1;
    private TextView txtN2;
    private EditText edtResposta;
    private EditText edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuada);

        txtN1 = (TextView) findViewById(R.id.txtN1);
        txtN2 = (TextView) findViewById(R.id.txtN2);
        edtResposta = (EditText) findViewById(R.id.edtResposta);
        edtNome = (EditText) findViewById(R.id.edtNome);

        sorteio();

    }

    private void sorteio(){
        Random n1 = new Random();
        Random n2 = new Random();

        int v1 = n1.nextInt(10);
        int v2 = n2.nextInt(10);

        txtN1.setText(String.valueOf(v1));
        txtN2.setText(String.valueOf(v2));
    }

    public void validar(View v){
        int n1 = Integer.parseInt(txtN1.getText().toString());
        int n2 = Integer.parseInt(txtN2.getText().toString());
        int r = Integer.parseInt(edtResposta.getText().toString());
        String resp = "";
        String nome = this.edtNome.getText().toString();
        String url = "http://tabuada.mybluemix.net/Service?acao=resposta&nome="+nome+"&n1="+n1+"&n2="+n2+"&resposta="+r;
        RequestQueue rq = Volley.newRequestQueue(this);

        if(r == (n1*n2)){
            resp = "Certa resposta";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {

                }
            },  new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Tabuada.this, "Erro!", Toast.LENGTH_SHORT).show();
                }
            });

            rq.add(stringRequest);

        }
        else resp = "Resposta errada";

        Toast.makeText(this, resp, Toast.LENGTH_SHORT).show();
        sorteio();
    }

    public void registrar(View v){
        String nome = this.edtNome.getText().toString();

        String url = "http://tabuada.mybluemix.net/Service?acao=registro&nome=" + nome;
        RequestQueue rq = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

            }
        },  new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Tabuada.this, "Erro!", Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(stringRequest);
    }
}
