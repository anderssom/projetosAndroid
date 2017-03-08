package com.espm.pizzariadomoreira;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener{

    private List<PizzaBean> pizzas;
    private ArrayAdapter<PizzaBean> adp;
    private Spinner spPagamento;
    private Spinner spSabor;
    private ImageView imagePizza;
    private RadioGroup rgTamanho;
    private TextView txtPreco;
    private boolean borda;
    private int tamanho;
    private double precoPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spPagamento = (Spinner) findViewById(R.id.spPagamento);
        spSabor = (Spinner) findViewById(R.id.spSabor);
        popularPizzas();

        spSabor.setOnItemSelectedListener(this);
        imagePizza = (ImageView) findViewById(R.id.imagePizza);
        rgTamanho = (RadioGroup) findViewById(R.id.rgTamanho);
        rgTamanho.setOnCheckedChangeListener(this);
        txtPreco = (TextView) findViewById(R.id.txtPreco);
    }

    private void calcular(){
        double p = this.precoPizza;
        if(this.tamanho == R.id.rbGrande){
            p += 15.0;
        }
        else if(this.tamanho == R.id.rbMedio){
            p += 10.0;
        }
        else if(this.tamanho == R.id.rbPequeno){
            p += 5.0;
        }

        if(this.borda){
            p += 5.0;
        }

        this.txtPreco.setText(String.valueOf(p));
    }

    public void borda(View v){
        this.borda = ((CheckBox) v).isChecked();
        calcular();
    }

    private void popularPizzas(){
        pizzas = new ArrayList<PizzaBean>();
        pizzas.add(new PizzaBean("Bacon", 10.0, R.drawable.pizzabacon));
        pizzas.add(new PizzaBean("Queijo", 10.0, R.drawable.pizzaqueijo));

        adp = new ArrayAdapter<PizzaBean>(this, android.R.layout.simple_spinner_item, pizzas);
        spSabor.setAdapter(adp);

    }

    private void criarPizzasTeste(){
        pizzas = new ArrayList<PizzaBean>();
        pizzas.add(new PizzaBean("Bacon", 10.0, R.drawable.pizzabacon));
        pizzas.add(new PizzaBean("Queijo", 10.0, R.drawable.pizzaqueijo));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PizzaBean sel = (PizzaBean) parent.getItemAtPosition(position);
        imagePizza.setImageResource(sel.getImagem());
        this.precoPizza = sel.getPreco();
        calcular();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //findViewById(checkedId).setBackgroundColor(Color.MAGENTA);
        this.tamanho = checkedId;
        calcular();
    }
}
