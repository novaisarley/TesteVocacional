package com.arley.testevocacional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PerguntaActivity extends AppCompatActivity {

    Button btProximo;
    Button btAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        btProximo = findViewById(R.id.bt_proximo);
        btAnterior = findViewById(R.id.bt_anterior);

        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerguntaActivity.this, ResultadoActivity.class));
            }
        });
    }
}