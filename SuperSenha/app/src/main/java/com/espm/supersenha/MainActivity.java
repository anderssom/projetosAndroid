package com.espm.supersenha;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView color1;
    private ImageView color2;
    private ImageView color3;

    private ImageView cor00, cor10, cor20, result00, result10, result20;
    private ImageView cor01, cor11, cor21, result01, result11, result21;
    private ImageView cor02, cor12, cor22, result02, result12, result22;

    private Button btn_Jogar;

    private int[] coresSecretas;
    private int corAtual;
    private boolean iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color1 = (ImageView) findViewById(R.id.color1);
        color2 = (ImageView) findViewById(R.id.color2);
        color3 = (ImageView) findViewById(R.id.color3);

        cor00 = (ImageView) findViewById(R.id.cor00);
        cor10 = (ImageView) findViewById(R.id.cor10);
        cor20 = (ImageView) findViewById(R.id.cor20);
        result00 = (ImageView) findViewById(R.id.result00);
        result10 = (ImageView) findViewById(R.id.result10);
        result20 = (ImageView) findViewById(R.id.result20);

        cor01 = (ImageView) findViewById(R.id.cor01);
        cor11 = (ImageView) findViewById(R.id.cor11);
        cor21 = (ImageView) findViewById(R.id.cor21);
        result01 = (ImageView) findViewById(R.id.result01);
        result11 = (ImageView) findViewById(R.id.result11);
        result21 = (ImageView) findViewById(R.id.result21);

        cor02 = (ImageView) findViewById(R.id.cor02);
        cor12 = (ImageView) findViewById(R.id.cor12);
        cor22 = (ImageView) findViewById(R.id.cor22);
        result02 = (ImageView) findViewById(R.id.result02);
        result12 = (ImageView) findViewById(R.id.result12);
        result22 = (ImageView) findViewById(R.id.result22);

        btn_Jogar = (Button) findViewById(R.id.btn_jogar);

    }

    public void jogar(View v){
        if(!this.iniciar){
            coresSecretas = SuperSenhaUtil.sortearCores();
            btn_Jogar.setText(R.string.lblPronto);
            this.iniciar = true;
        }
        else{
            int[] sel0 = {((ColorDrawable)this.cor00.getBackground()).getColor(),
                    ((ColorDrawable)this.cor10.getBackground()).getColor(),
                    ((ColorDrawable)this.cor20.getBackground()).getColor()};

            int r[] = SuperSenhaUtil.resultado(coresSecretas, sel0);

            this.result00.setBackgroundColor(r[0]);
            this.result10.setBackgroundColor(r[1]);
            this.result20.setBackgroundColor(r[2]);
            //revelarSegredo();
        }
        //revelarSegredo();
    }

    private void revelarSegredo(){
        color1.setBackgroundColor(coresSecretas[0]);
        color2.setBackgroundColor(coresSecretas[1]);
        color3.setBackgroundColor(coresSecretas[2]);
    }

    public void selecionarCor(View v){
        v.setBackgroundColor(SuperSenhaUtil.cores[corAtual]);
        corAtual++;

        if(corAtual >= SuperSenhaUtil.cores.length){
            corAtual = 0;
        }

    }
}
