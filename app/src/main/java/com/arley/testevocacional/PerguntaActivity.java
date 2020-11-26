package com.arley.testevocacional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.arley.testevocacional.model.Alternative;
import com.arley.testevocacional.model.Answer;
import com.arley.testevocacional.model.Group;
import com.arley.testevocacional.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class PerguntaActivity extends AppCompatActivity {
    protected Button btProximo;
    protected Button btAnterior;
    private TextView tv_pergunta;
    private RadioGroup radio_group;
    private RadioButton rb_opcao_1, rb_opcao_2, rb_opcao_3, rb_opcao_4, rb_opcao_5, rb_opcao_6, rb_opcao_7, rb_opcao_8, rb_opcao_9, rb_opcao_10;

    private final ArrayList<Question> questions = new ArrayList<>();
    public static ArrayList<Answer> answers;

    private Integer current_question = 0;
    private Boolean is_clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        answers = new ArrayList<>();

        btProximo = findViewById(R.id.bt_proximo);
        btAnterior = findViewById(R.id.bt_anterior);

        btProximo.setClickable(false);
        btProximo.setAlpha(0.6f);
        btAnterior.setVisibility(View.GONE);

        tv_pergunta = findViewById(R.id.tv_pergunta);
        radio_group = findViewById(R.id.radio_group);

        rb_opcao_1 = findViewById(R.id.rb_opcao_1);
        rb_opcao_2 = findViewById(R.id.rb_opcao_2);
        rb_opcao_3 = findViewById(R.id.rb_opcao_3);
        rb_opcao_4 = findViewById(R.id.rb_opcao_4);
        rb_opcao_5 = findViewById(R.id.rb_opcao_5);
        rb_opcao_6 = findViewById(R.id.rb_opcao_6);
        rb_opcao_7 = findViewById(R.id.rb_opcao_7);
        rb_opcao_8 = findViewById(R.id.rb_opcao_8);
        rb_opcao_9 = findViewById(R.id.rb_opcao_9);
        rb_opcao_10 = findViewById(R.id.rb_opcao_10);

        getQuestions();
        setQuestionInfo();

        btAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_question != 0) {
                    if (current_question == 1) {
                        btAnterior.setVisibility(View.GONE);
                    }

                    current_question -= 1;
                    setQuestionInfo();
                }

                if (!checkIsAnswered()) {
                    is_clear = true;
                    radio_group.clearCheck();
                }
            }
        });

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (!is_clear) {
                    btProximo.setClickable(true);
                    btProximo.setAlpha(1);

                    int index_radio_button_selected = -1;

                    if (i == R.id.rb_opcao_1) {
                        index_radio_button_selected = 0;
                    } else if (i == R.id.rb_opcao_2) {
                        index_radio_button_selected = 1;
                    } else if (i == R.id.rb_opcao_3) {
                        index_radio_button_selected = 2;
                    } else if (i == R.id.rb_opcao_4) {
                        index_radio_button_selected = 3;
                    } else if (i == R.id.rb_opcao_5) {
                        index_radio_button_selected = 4;
                    } else if (i == R.id.rb_opcao_6) {
                        index_radio_button_selected = 5;
                    } else if (i == R.id.rb_opcao_7) {
                        index_radio_button_selected = 6;
                    } else if (i == R.id.rb_opcao_8) {
                        index_radio_button_selected = 7;
                    } else if (i == R.id.rb_opcao_9) {
                        index_radio_button_selected = 8;
                    } else if (i == R.id.rb_opcao_10) {
                        index_radio_button_selected = 9;
                    }

                    if (index_radio_button_selected != -1) {
                        int index = getAnswerIndex();
                        Group selected_group = questions.get(current_question).getAlternatives().get(index_radio_button_selected).getGroup();

                        if (index == -1) {
                            answers.add(new Answer(questions.get(current_question), selected_group));
                        } else {
                            answers.get(index).setGroup(selected_group);
                        }
                    }
                } else {
                    is_clear = false;
                }
            }
        });

        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio_group.getCheckedRadioButtonId() != -1) {
                    btAnterior.setVisibility(View.VISIBLE);

                    if (current_question == questions.size() - 1) {
                        startActivity(new Intent(PerguntaActivity.this, ResultadoActivity.class));
                    } else {
                        current_question += 1;
                        setQuestionInfo();

                        if (!checkIsAnswered()) {
                            is_clear = true;
                            radio_group.clearCheck();
                            btProximo.setClickable(false);
                            btProximo.setAlpha(0.6f);
                        }
                    }
                }
            }
        });
    }

    private int getAnswerIndex() {
        if (current_question < answers.size()) {
            if (answers.get(current_question).getQuestion() == questions.get(current_question)) {
                return current_question;
            }
        }

        return -1;
    }

    private boolean checkIsAnswered() {
        if (current_question < answers.size()) {
            if (answers.get(current_question).getQuestion() == questions.get(current_question)) {
                for (int i = 0; i < questions.get(current_question).getAlternatives().size(); i += 1) {
                    if (questions.get(current_question).getAlternatives().get(i).getGroup() == answers.get(current_question).getGroup()) {
                        is_clear = true;

                        switch (i) {
                            case 0: {
                                radio_group.check(R.id.rb_opcao_1);
                                break;
                            }
                            case 1: {
                                radio_group.check(R.id.rb_opcao_2);
                                break;
                            }
                            case 2: {
                                radio_group.check(R.id.rb_opcao_3);
                                break;
                            }
                            case 3: {
                                radio_group.check(R.id.rb_opcao_4);
                                break;
                            }
                            case 4: {
                                radio_group.check(R.id.rb_opcao_5);
                                break;
                            }
                            case 5: {
                                radio_group.check(R.id.rb_opcao_6);
                                break;
                            }
                            case 6: {
                                radio_group.check(R.id.rb_opcao_7);
                                break;
                            }
                            case 7: {
                                radio_group.check(R.id.rb_opcao_8);
                                break;
                            }
                            case 8: {
                                radio_group.check(R.id.rb_opcao_9);
                                break;
                            }
                            case 9: {
                                radio_group.check(R.id.rb_opcao_10);
                                break;
                            }
                        }
                        break;
                    }
                }

                return true;
            }
        }

        return false;
    }

    private void setQuestionInfo() {
        tv_pergunta.setText(questions.get(current_question).getDescription());

        rb_opcao_1.setText(questions.get(current_question).getAlternatives().get(0).getDescription());
        rb_opcao_2.setText(questions.get(current_question).getAlternatives().get(1).getDescription());
        rb_opcao_3.setText(questions.get(current_question).getAlternatives().get(2).getDescription());
        rb_opcao_4.setText(questions.get(current_question).getAlternatives().get(3).getDescription());
        rb_opcao_5.setText(questions.get(current_question).getAlternatives().get(4).getDescription());
        rb_opcao_6.setText(questions.get(current_question).getAlternatives().get(5).getDescription());
        rb_opcao_7.setText(questions.get(current_question).getAlternatives().get(6).getDescription());
        rb_opcao_8.setText(questions.get(current_question).getAlternatives().get(7).getDescription());
        rb_opcao_9.setText(questions.get(current_question).getAlternatives().get(8).getDescription());
        rb_opcao_10.setText(questions.get(current_question).getAlternatives().get(9).getDescription());
    }

    private ArrayList<Question> getQuestions() {
        HashMap<String, Group> groups = new HashMap<>();
        groups.put("A", new Group("A"));
        groups.put("B", new Group("B"));
        groups.put("C", new Group("C"));
        groups.put("D", new Group("D"));
        groups.put("E", new Group("E"));
        groups.put("F", new Group("F"));
        groups.put("G", new Group("G"));
        groups.put("H", new Group("H"));
        groups.put("I", new Group("I"));
        groups.put("J", new Group("J"));

        ArrayList<Alternative> alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("H"), "Visitar museus, exposições e feiras arte"));
        alternatives.add(new Alternative(groups.get("I"), "Assistir televisão"));
        alternatives.add(new Alternative(groups.get("F"), "Participar de uma Ong de defesa do meio ambiente"));
        alternatives.add(new Alternative(groups.get("B"), "Assistir a séries, seriadosou filmes sobre justiça"));
        alternatives.add(new Alternative(groups.get("D"), "Fazer experiências no laboratório da escola"));
        alternatives.add(new Alternative(groups.get("E"), "Acompanhar a rentabilidade dos investimentos financeiros nos jornais"));
        alternatives.add(new Alternative(groups.get("A"), "Pesquisar Atlas do corpo humano"));
        alternatives.add(new Alternative(groups.get("C"), "Conversar sobre os problemas pessoais dos seus amigos"));
        alternatives.add(new Alternative(groups.get("G"), "Desmontar pequenos aparelhos para saber como funciona"));
        alternatives.add(new Alternative(groups.get("J"), "Esportes"));
        questions.add(new Question(1, "O que você prefere?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("F"), "Assistir à documentários sobre o meio ambiente"));
        alternatives.add(new Alternative(groups.get("H"), "Desenhar páginas para a Internet"));
        alternatives.add(new Alternative(groups.get("A"), "Ler artigos sobre saúde"));
        alternatives.add(new Alternative(groups.get("D"), "Conhecer laboratórios de pesquisas de Universidades"));
        alternatives.add(new Alternative(groups.get("I"), "Escrever artigos e reportagens para o jornal da escola"));
        alternatives.add(new Alternative(groups.get("E"), "Liderar grupos de trabalho na Escola"));
        alternatives.add(new Alternative(groups.get("J"), "Planejar com detalhes as viagens de férias"));
        alternatives.add(new Alternative(groups.get("C"), "Conversar com pessoas idosas ou doentes"));
        alternatives.add(new Alternative(groups.get("G"), "Estudar informática"));
        alternatives.add(new Alternative(groups.get("B"), "Ler artigos sobre a realidade nacional"));
        questions.add(new Question(1, "O que você prefere?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("A"), "Ver documentários sobre medicina e saúde"));
        alternatives.add(new Alternative(groups.get("C"), "Atuar em uma Ong de defesa dos Direitos Humanos"));
        alternatives.add(new Alternative(groups.get("H"), "Desenhar modelos de roupas eacessórios"));
        alternatives.add(new Alternative(groups.get("E"), "Trabalhar na comissão de formatura"));
        alternatives.add(new Alternative(groups.get("J"), "Frequentar uma academia e cultivar o corpo"));
        alternatives.add(new Alternative(groups.get("G"), "Aprender a instalar componentes de computadores"));
        alternatives.add(new Alternative(groups.get("I"), "Fotografar e fazer gravações em vídeo de amigos e parentes"));
        alternatives.add(new Alternative(groups.get("B"), "Participar de uma Ong em sua comunidade"));
        alternatives.add(new Alternative(groups.get("F"), "Ler sobre o efeito estufa e o fenômeno “El Niño”"));
        alternatives.add(new Alternative(groups.get("D"), "Resolver quebra-cabeças de matemática"));
        questions.add(new Question(1, "O que você prefere?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("A"), "Ler sobre engenharia genética"));
        alternatives.add(new Alternative(groups.get("H"), "Pintar e desenhar"));
        alternatives.add(new Alternative(groups.get("E"), "Fazer planos para um negócio próprio no futuro"));
        alternatives.add(new Alternative(groups.get("D"), "Observar o céu com um telescópio"));
        alternatives.add(new Alternative(groups.get("J"), "Ler revistas de viagens e turismo"));
        alternatives.add(new Alternative(groups.get("C"), "Trabalhar como voluntário com menores carentes"));
        alternatives.add(new Alternative(groups.get("G"), "Aprender a utilizar novos programas de computador"));
        alternatives.add(new Alternative(groups.get("B"), "Ajudar a resolver conflitos entre amigos"));
        alternatives.add(new Alternative(groups.get("I"), "Trabalhar em uma rádio comunitária"));
        alternatives.add(new Alternative(groups.get("F"), "Participar da elaboração de projetos de prevenção ambiental"));
        questions.add(new Question(1, "O que você prefere?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("H"), "Instalar e consertar aparelhos eletrônicos"));
        alternatives.add(new Alternative(groups.get("D"), "Ler sobre os efeitos do crescimento da população sobre o ecossistema"));
        alternatives.add(new Alternative(groups.get("E"), "Comentar notícias dos telejornais"));
        alternatives.add(new Alternative(groups.get("F"), "Participar de uma banda ou grupode teatro da escola"));
        alternatives.add(new Alternative(groups.get("C"), "Viajar e descobrir lugares pouco conhecidos"));
        alternatives.add(new Alternative(groups.get("G"), "Entender mais sobre política"));
        alternatives.add(new Alternative(groups.get("B"), "Estudar a fisiologia dos animais"));
        alternatives.add(new Alternative(groups.get("A"), "Discutir com os pais o orçamento doméstico"));
        alternatives.add(new Alternative(groups.get("I"), "Conhecer o funcionamento de um motor"));
        alternatives.add(new Alternative(groups.get("J"), "Estudar terapias alternativas"));
        questions.add(new Question(1, "O que você prefere?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("H"), "Software de desenho gráfico e tratamento de imagens"));
        alternatives.add(new Alternative(groups.get("G"), "Máquinas e equipamentos industriais de alta tecnologia"));
        alternatives.add(new Alternative(groups.get("F"), "Plantas e flores"));
        alternatives.add(new Alternative(groups.get("D"), "Equipamentos de laboratório"));
        alternatives.add(new Alternative(groups.get("I"), "Câmeras de tv e cinema"));
        alternatives.add(new Alternative(groups.get("J"), "Equipamentos de academia e ginástica"));
        alternatives.add(new Alternative(groups.get("E"), "Livros e revistas sobre empreendedorismo"));
        alternatives.add(new Alternative(groups.get("B"), "Objetos e documentos históricos"));
        alternatives.add(new Alternative(groups.get("A"), "Aparelhos de diagnóstico por imagem"));
        alternatives.add(new Alternative(groups.get("C"), "Favelas"));
        questions.add(new Question(2, "O que mais te chama atenção?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("J"), "Equipamentos esportivos"));
        alternatives.add(new Alternative(groups.get("G"), "Satélites de comunicação"));
        alternatives.add(new Alternative(groups.get("F"), "Pedras e minerais"));
        alternatives.add(new Alternative(groups.get("E"), "Bolsa de valores"));
        alternatives.add(new Alternative(groups.get("D"), "Computadores"));
        alternatives.add(new Alternative(groups.get("C"), "Livros de Psicologia e autoajuda"));
        alternatives.add(new Alternative(groups.get("H"), "Instrumentos musicais"));
        alternatives.add(new Alternative(groups.get("I"), "Estúdio de televisão"));
        alternatives.add(new Alternative(groups.get("A"), "O corpo humano"));
        alternatives.add(new Alternative(groups.get("B"), "Museus e arquivos públicos"));
        questions.add(new Question(2, "O que mais te chama atenção?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("B"), "Livros sobre legislação"));
        alternatives.add(new Alternative(groups.get("C"), "Meninos de rua em situação de risco"));
        alternatives.add(new Alternative(groups.get("F"), "Máquinas e equipamentos de reciclagem de materiais"));
        alternatives.add(new Alternative(groups.get("G"), "Robôs e instrumentos de nanotecnologia"));
        alternatives.add(new Alternative(groups.get("J"), "Estádios de futebol"));
        alternatives.add(new Alternative(groups.get("I"), "Site de notícias"));
        alternatives.add(new Alternative(groups.get("H"), "Tintas e pincéis"));
        alternatives.add(new Alternative(groups.get("D"), "Microscópios"));
        alternatives.add(new Alternative(groups.get("E"), "Softwares de administração de empresas"));
        alternatives.add(new Alternative(groups.get("A"), "Instrumentos cirúrgicos"));
        questions.add(new Question(2, "O que mais te chama atenção?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("A"), "Em um hospital"));
        alternatives.add(new Alternative(groups.get("F"), "Em uma área de florestas"));
        alternatives.add(new Alternative(groups.get("G"), "Em uma grande indústria"));
        alternatives.add(new Alternative(groups.get("D"), "Em um laboratório"));
        alternatives.add(new Alternative(groups.get("B"), "Em uma universidade"));
        alternatives.add(new Alternative(groups.get("I"), "Em uma rádio ou canal de tv"));
        alternatives.add(new Alternative(groups.get("H"), "Em um teatro"));
        alternatives.add(new Alternative(groups.get("C"), "Em uma Ong de ajuda comunitária "));
        alternatives.add(new Alternative(groups.get("E"), "Em uma empresa multinacional"));
        alternatives.add(new Alternative(groups.get("J"), "Em um grande clube social e esportivo"));
        questions.add(new Question(3, "Onde você gostaria de trabalhar?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("I"), "Em um jornal"));
        alternatives.add(new Alternative(groups.get("H"), "Em uma escola de artes"));
        alternatives.add(new Alternative(groups.get("C"), "Em meu próprio consultório"));
        alternatives.add(new Alternative(groups.get("D"), "No departamento de pesquisas de uma grande empresa"));
        alternatives.add(new Alternative(groups.get("E"), "Na minha própria empresa"));
        alternatives.add(new Alternative(groups.get("B"), "Em uma escola"));
        alternatives.add(new Alternative(groups.get("A"), "Em um consultório médico ou odontológico"));
        alternatives.add(new Alternative(groups.get("F"), "No litoral"));
        alternatives.add(new Alternative(groups.get("G"), "Em grandes canteiros de obras"));
        alternatives.add(new Alternative(groups.get("J"), "Em um hotel"));
        questions.add(new Question(3, "Onde você gostaria de trabalhar?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("E"), "Em uma empresa exportadora"));
        alternatives.add(new Alternative(groups.get("D"), "Em uma empresa farmacêutica"));
        alternatives.add(new Alternative(groups.get("B"), "Em uma instituição governamental"));
        alternatives.add(new Alternative(groups.get("F"), "Em uma Ong de defesa ambiental"));
        alternatives.add(new Alternative(groups.get("G"), "Em uma empresa de telecomunicações"));
        alternatives.add(new Alternative(groups.get("H"), "Em uma empresa de arquitetura"));
        alternatives.add(new Alternative(groups.get("I"), "Em uma empresa de publicidade"));
        alternatives.add(new Alternative(groups.get("J"), "Em uma empresa de turismo"));
        alternatives.add(new Alternative(groups.get("A"), "Em um laboratório de análises clínicas"));
        alternatives.add(new Alternative(groups.get("C"), "Em uma clínica de saúde"));
        questions.add(new Question(3, "Onde você gostaria de trabalhar?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("A"), "Ciências biomédicas"));
        alternatives.add(new Alternative(groups.get("D"), "Matemática"));
        alternatives.add(new Alternative(groups.get("B"), "Direito"));
        alternatives.add(new Alternative(groups.get("C"), "Fonoaudiologia"));
        alternatives.add(new Alternative(groups.get("F"), "Geologia"));
        alternatives.add(new Alternative(groups.get("E"), "Ciências econômicas"));
        alternatives.add(new Alternative(groups.get("G"), "Ciência da computação"));
        alternatives.add(new Alternative(groups.get("I"), "Publicidade e propaganda"));
        alternatives.add(new Alternative(groups.get("H"), "Artes cênicas"));
        alternatives.add(new Alternative(groups.get("J"), "Hotelaria"));
        questions.add(new Question(4, "Escolha as profissões que mais lhe agradam?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("J"), "Turismo"));
        alternatives.add(new Alternative(groups.get("H"), "Arquitetura e urbanismo"));
        alternatives.add(new Alternative(groups.get("I"), "Cinema e vídeo"));
        alternatives.add(new Alternative(groups.get("A"), "Medicina"));
        alternatives.add(new Alternative(groups.get("B"), "Ciências sociais"));
        alternatives.add(new Alternative(groups.get("C"), "Psicologia"));
        alternatives.add(new Alternative(groups.get("E"), "Administração"));
        alternatives.add(new Alternative(groups.get("G"), "Engenharia de Telecomunicações"));
        alternatives.add(new Alternative(groups.get("F"), "Ecologia"));
        alternatives.add(new Alternative(groups.get("D"), "Física"));
        questions.add(new Question(4, "Escolha as profissões que mais lhe agradam?", alternatives));

        alternatives = new ArrayList<>();
        alternatives.add(new Alternative(groups.get("J"), "Esporte e lazer"));
        alternatives.add(new Alternative(groups.get("I"), "Design gráfico"));
        alternatives.add(new Alternative(groups.get("H"), "Artes plásticas"));
        alternatives.add(new Alternative(groups.get("G"), "Engenharia civil"));
        alternatives.add(new Alternative(groups.get("F"), "Engenharia agrícola"));
        alternatives.add(new Alternative(groups.get("E"), "Comércio exterior"));
        alternatives.add(new Alternative(groups.get("D"), "Química"));
        alternatives.add(new Alternative(groups.get("C"), "Pedagogia"));
        alternatives.add(new Alternative(groups.get("B"), "História"));
        alternatives.add(new Alternative(groups.get("A"), "Farmácia e bioquímica"));
        questions.add(new Question(4, "Escolha as profissões que mais lhe agradam?", alternatives));

        return questions;
    }
}