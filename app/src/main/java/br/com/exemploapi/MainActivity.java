package br.com.exemploapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Este projeto está usando a biblioteca Volley para comunicação web (api)
    //Preparei um web service de teste que devolve um JSON com os seguintes dados:
    //{
    //  "aluno":"Mestre Yoda",
    //  "matricula":"202012345",
    //  "ano":"3",
    //  "curso":"ADS",
    //  "disciplina":"Inteligência Artificial",
    //  "nota":7.5
    // }
//O projeto está pegando o retorno da chamada em formato String e inserindo dentro de um JSONObject para
// facilitar a manipulação das informações recebidas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect();
    }

    public void connect(){
        // Chamada HTTP para a api de teste
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://152.70.216.140:1880/teste_api";
        //Configuraçaõ para uma chamada HTTP GET, resultado armazenado em formato string
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Exibe o resultado recebido já formatado em JSON
                        try {
                            JSONObject aluno = new JSONObject(response);
                            Toast.makeText(MainActivity.this, aluno.toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this, "Dados extraídos do JSON:\n" +
                                    aluno.get("aluno")+"\n"+
                                    aluno.get("matricula")+"\n"+
                                    aluno.get("ano")+"\n"+
                                    aluno.get("curso")+"\n"+
                                    aluno.get("disciplina")+"\n"+
                                    aluno.get("nota")+"\n", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}