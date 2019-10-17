package com.danilolimadev.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia"; //A palavra final indica que o valor não pode ser mudado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = findViewById(R.id.editTextId);
        textoExibicao = findViewById(R.id.textViewId);
        botaoSalvar = findViewById(R.id.buttonId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0); //0 Indica arquivo privado, só pode ser lido pela nosso aplicação
                SharedPreferences.Editor editor = sharedPreferences.edit(); //Editor nos permite editar o nosso arquivo

                if(textoNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Preencher o nome", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("nome", textoNome.getText().toString());
                    editor.commit();
                    textoExibicao.setText("Olá, " + textoExibicao.getText().toString());
                }

            }
        });

        //Recuperar dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "Usuário Não definido");
            textoExibicao.setText("Olá, " + nomeUsuario);
        }else{
            textoExibicao.setText("Olá usuário não definido");
        }


    }
}
