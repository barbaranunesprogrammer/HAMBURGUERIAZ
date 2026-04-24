package com.example.hamburgueriaz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int quantidade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void somar(View view) {
        quantidade++;
        atualizarQuantidade();
    }

    public void subtrair(View view) {
        if (quantidade > 0) {
            quantidade--;
        }
        atualizarQuantidade();
    }

    private void atualizarQuantidade() {
        TextView txt = findViewById(R.id.quantidade);
        txt.setText(String.valueOf(quantidade));
    }

    public void enviarPedido(View view) {

        EditText nome = findViewById(R.id.nomeCliente);
        CheckBox bacon = findViewById(R.id.checkBacon);
        CheckBox queijo = findViewById(R.id.checkQueijo);
        CheckBox onion = findViewById(R.id.checkOnion);
        TextView precoTxt = findViewById(R.id.preco);

        String nomeCliente = nome.getText().toString();

        boolean temBacon = bacon.isChecked();
        boolean temQueijo = queijo.isChecked();
        boolean temOnion = onion.isChecked();

        int preco = 20 * quantidade;

        if (temBacon) preco += 2 * quantidade;
        if (temQueijo) preco += 2 * quantidade;
        if (temOnion) preco += 3 * quantidade;

        String resumo =
                "Nome: " + nomeCliente +
                        "\nBacon: " + (temBacon ? "Sim" : "Não") +
                        "\nQueijo: " + (temQueijo ? "Sim" : "Não") +
                        "\nOnion: " + (temOnion ? "Sim" : "Não") +
                        "\nQuantidade: " + quantidade +
                        "\nPreço: R$ " + preco;

        precoTxt.setText("Pedido de " +nomeCliente+ "  -Total: R$ " + preco);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:barbaranunesprogrammer@outlook.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nomeCliente);
        intent.putExtra(Intent.EXTRA_TEXT, resumo);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
