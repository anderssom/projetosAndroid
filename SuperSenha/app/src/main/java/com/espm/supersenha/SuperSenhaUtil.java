package com.espm.supersenha;


import android.graphics.Color;
import android.util.Log;

import java.util.Random;

public class SuperSenhaUtil {

    public static final int cores[] = {Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.RED, Color.WHITE, Color.CYAN};

    public static int[] sortearCores() {

        Random r = new Random();

        return new int[]{cores[r.nextInt(7)], cores[r.nextInt(7)],cores[r.nextInt(7)]};

    }

    public static int[] resultado(int[] coresSorteadas, int[] coresEscolhidas) {

        Log.i("SuperSenha", coresSorteadas[0] + ", " + coresSorteadas[1] + ", " + coresSorteadas[2]);
        Log.i("SuperSenha", coresEscolhidas[0] + ", " + coresEscolhidas[1] + ", " + coresEscolhidas[2]);

        int[] ret = new int[3];
        for (int i = 0; i < coresSorteadas.length; i++) {
            if (coresSorteadas[i] == coresEscolhidas[i])
                ret[i] = Color.GREEN;
            else if (coresSorteadas[i] != coresEscolhidas[i])
                for (int j = 0; j < coresSorteadas.length; j++)
                    if (coresSorteadas[j] == coresEscolhidas[i]) {
                        ret[i] = Color.YELLOW;
                        break;
                    }
                    else ret[i] = Color.BLACK;

        }
        Log.i("SuperSenha", ret[0] + ", " + ret[1] + ", " + ret[2]);
        return ret;
    }

    public static boolean isVencedor(int[] resultado) {

        int total = 0;
        for (int i = 0; i < resultado.length; i++) {
            if (resultado[i] == Color.GREEN)
                total++;
        }
        if (total == 3) return true;
        else return false;

    }

}

