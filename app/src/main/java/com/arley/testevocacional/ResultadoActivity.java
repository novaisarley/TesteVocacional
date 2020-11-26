package com.arley.testevocacional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arley.testevocacional.model.Answer;
import com.arley.testevocacional.model.Group;
import com.arley.testevocacional.model.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultadoActivity extends AppCompatActivity {
    protected TextView tv_grupo, tv_area_atuacao, tv_descricao, tv_profissoes;
    protected Button activity_resultado_bt_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tv_grupo = findViewById(R.id.tv_grupo);
        tv_area_atuacao = findViewById(R.id.tv_area_atuacao);
        tv_descricao = findViewById(R.id.tv_descricao);
        tv_profissoes = findViewById(R.id.tv_profissoes);

        activity_resultado_bt_voltar = findViewById(R.id.activity_resultado_bt_voltar);

        if (PerguntaActivity.answers.size() == 0) {
            startActivity(new Intent(ResultadoActivity.this, InicioActivity.class));
            finish();
        }

        Result result = calculateResult();

        tv_grupo.setText(result.getGroup().getName());
        tv_area_atuacao.setText(result.getName());
        tv_descricao.setText(result.getDescription());
        tv_profissoes.setText(result.getProfessions());

        activity_resultado_bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultadoActivity.this, InicioActivity.class));
                finish();
            }
        });
    }

    private String getName(String name) {
        switch (name) {
            case "A":
                return "Ciências da saúde e biológicas";
            case "B":
                return "Ciências humanas e sociais";
            case "C":
                return "Comportamento e ajuda pessoal";
            case "D":
                return "Ciências exatas";
            case "E":
                return "Negócios e administração";
            case "F":
                return "Ecologia";
            case "G":
                return "Tecnologias e engenharia";
            case "H":
                return "Artes";
            case "I":
                return "Comunicações";
            case "J":
                return "Esporte e lazer";
            default:
                return "";
        }
    }

    private String getDescription(String name) {
        switch (name) {
            case "A":
                return "A atração principal é o cuidado com seres vivos e a cura de doenças. É preciso gostar de pesquisas, biologia e fisiologia, entre outras matérias.";
            case "B":
                return "Ciências Humanas estão envolvidas diretamente com os homens, suas relações, história e pensamento. As sociais privilegiam a vida do homem em sociedade. O perfil dos que se sentem inclinados para essa área é a transformação do homem e da sociedade, o bem-estar e o progresso da coletividade.";
            case "C":
                return "Este é o campo das profissões cujo objetivo é de ajudarem as pessoas a viverem melhor. Seu foco é o indivíduo e a qualidade de vida.";
            case "D":
                return "Gostar de matemática é o principal requisito das Ciências.";
            case "E":
                return "A área tem várias especializações, mas sempre centradas em administração e nas finanças das empresas. Exigem várias habilidades, desde cálculos e matemática até capacidade de comunicação e bom relacionamento pessoal, além de espírito empreendedor.";
            case "F":
                return "Trabalhar com a natureza e a preservação do meio ambiente é a principal função desta área. Vários cursos podem formar profissionais dedicados à ecologia, e o mercado de trabalho cresce a cada dia.";
            case "G":
                return "O mundo da tecnologia é muito amplo e inclui especialidades das mais variadas. A intensa e rápida inovação tecnológica mantém o mercado de trabalho em constante expansão. É preciso gostar de ciências exatas, informática e ter pensamento lógico.";
            case "H":
                return "No mundo das artes, o requisito básico não é talento nato, mas criatividade, sensibilidade, senso estético, que são habilidades adquiridas pela experiência e formação cultural.";
            case "I":
                return "O foco dessa área é a produção e transmissão de informações pelos vários meios de comunicação. Muitas das profissões são multidisciplinares e incluem arte, cultura e tecnologia.";
            case "J":
                return "A área abrange esporte, lazer e turismo. A preocupação cada vez maior com a qualidade de vida e a transformação do esporte, lazer e turismo em empreendimentos altamente rentáveis valorizou profissões e criou novas especializações.";
            default:
                return "";
        }
    }

    private String getProfessions(String name) {
        switch (name) {
            case "A":
                return "Profissões: Medicina, Ciências Biológicas, Ciências biomédicas, Farmácia, Bioquímica, Medicina veterinária, Obstetrícia, Odontologia, Zootecnia, Engenharia agrícola, Aquicultura, Oceanografia, Engenharia florestal, Microbiologia, e Imunologia";
            case "B":
                return "Profissões: Ciências sociais, Sociologia, Política, História, Geografia, Letras, Direito, Filosofia, Teologia, Pedagogia, Estudos literários, Linguística, Antropologia e Museologia.";
            case "C":
                return "Profissões: Enfermagem, Nutrição, Fisioterapia, Fonoaudiologia, Musicoterapia, Gastronomia, Terapia Ocupacional, Psicopedagogia, Serviço Social, Psicologia, Pedagogia, Naturologia, Quiropraxia, Optometria, Acumpuntura, Educação e Educação Física.";
            case "D":
                return "Profissões: Matemática, Física, Química, Geofísica, Astronomia, Estatística e Meteorologia.";
            case "E":
                return "Profissões: Administração, Ciências econômicas, Ciências Contábeis, Comércio exterior, Relações internacionais, Agronegócios, Administração rural e Secretariado executivo.";
            case "F":
                return "Profissões: Engenharia agrícola, Geologia, Engenharia ambiental, Engenharia de pesca, Engenharia florestal e Biologia.";
            case "G":
                return "Profissões: Ciências da computação, Engenharia civil, Mecânica, Naval, Industrial, Metalúrgica, Química, Da Computação, de Petróleo e Gás, Elétrica, Telecomunicações, de Alimento, Aeronáutica, Têxtil, Sanitária, Mecatrônica, Física e Cartografia.";
            case "H":
                return "Profissões: Arquitetura e Urbanismo, Artes plásticas, Artes do corpo, Dança, Design de Interiores Música e Moda";
            case "I":
                return "Profissões: Jornalismo, Audiovisual, Cinema, Rádio e TV, Multimídia, Fotografia, Design Gráfico, Desenho Industrial, Produção editorial, Produção cultural, Relações públicas, Publicidade e Propaganda e Marketing.\n";
            case "J":
                return "Profissões: Esporte, Educação física, Gastronomia, Hotelaria e Turismo.";
            default:
                return "";
        }
    }

    private Result calculateResult() {
        HashMap<Group, Integer> groupsController = new HashMap<>();

        for (Answer answer : PerguntaActivity.answers) {
            Integer count = groupsController.get(answer.getGroup());

            if (count == null) {
                groupsController.put(answer.getGroup(), 1);
            } else {
                groupsController.put(answer.getGroup(), count + 1);
            }
        }

        Pair<Group, Integer> winner_group = new Pair<>(null, 0);

        for (Map.Entry<Group, Integer> pair : groupsController.entrySet()) {
            if (winner_group.first == null | winner_group.second < pair.getValue()) {
                winner_group = new Pair<>(pair.getKey(), pair.getValue());
            }
        }

        String group_name = winner_group.first.getName();

        return new Result(winner_group.first, getName(group_name), getProfessions(group_name), getDescription(group_name));
    }
}