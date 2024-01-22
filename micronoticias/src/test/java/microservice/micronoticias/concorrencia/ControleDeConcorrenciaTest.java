package microservice.micronoticias.concorrencia;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql", "/sql/noticias/noticias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Concorrência")
class ControleDeConcorrenciaTest {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    @AfterEach
    void tearDown() {
        this.noticiaRepository.deleteAll();
        this.editoriaRepository.deleteAll();
    }

    static void log(Object obj, Object... args) {
        System.out.println(String.format("[Log " + System.currentTimeMillis() + "]", args));
    }

    static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}
    }

    @Nested
    @DisplayName("notícia")
    class Noticia {

        @Test
        @DisplayName("não permitirá atualizar Notícia desatualizada")
        void dadoNoticiaDesatualizadaParaAtualizar_QuandoUpdateComLockOtimista_EntaoLancarException() {

            // Criar
            Runnable runnable1 = () -> {
                log("Runnable 01 carregará o Notícia 1.");
                var noticia = noticiaRepository.findById(1001L).orElseThrow();

                log("Runnable 01 esperará por 3 segundos.");
                esperar(3);

                log("Runnable 01 alterará o Notícia.");
                noticia.setTitulo("Essa atualização não vingará frente ao Controlde de Concorrência do tipo LockOptimistic");

                log("Runnable 01 confirmará a transação.");
                noticiaRepository.saveAndFlush(noticia);
            };

            // Criar
            Runnable runnable2 = () -> {
                log("Runnable 02 carregará o Notícia 1.");
                var noticia2 = noticiaRepository.findById(1001L).orElseThrow();

                log("Runnable 02 esperará por 1 segundos.");
                esperar(1);

                log("Runnable 02 alterará o Notícia.");
                noticia2.setTitulo("Essa atualização vingará frente ao Controlde de Concorrência do tipo LockOptimistic");

                log("Runnable 02 confirmará a transação.");
                noticiaRepository.saveAndFlush(noticia2);
            };

            // Instanciar
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);

            // Iniciar
            thread1.start();
            thread2.start();

            try {
                // Ponto de encontro
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

            var noticia3 = noticiaRepository.findById(1001L).orElseThrow();

            Assertions.assertEquals("Essa atualização vingará frente ao Controlde de Concorrência do tipo LockOptimistic",
                noticia3.getTitulo());

            log("Encerrar método de teste");
        }
    }

    @Nested
    @DisplayName("editoria")
    class Editoria {

        @Test
        @DisplayName("não permitirá atualizar Editoria desatualizada")
        void dadoEditoriaDesatualizadaParaAtualizar_QuandoUpdateComLockOtimista_EntaoLancarException() {

            // Criar
            Runnable runnable1 = () -> {
                log("Runnable 01 carregará o Editoria 1.");
                var editoria1 = editoriaRepository.findById(1001L).orElseThrow();

                log("Runnable 01 esperará por 3 segundos.");
                esperar(3);

                log("Runnable 01 alterará o Editoria.");
                editoria1.setNomenclatura("Atualização não vinga frente ao Controlde de Concorrência LockOptimistic");

                log("Runnable 01 confirmará a transação.");
                editoriaRepository.saveAndFlush(editoria1);
            };

            // Criar
            Runnable runnable2 = () -> {
                log("Runnable 02 carregará o Editoria 1.");
                var editoria2 = editoriaRepository.findById(1001L).orElseThrow();

                log("Runnable 02 esperará por 1 segundos.");
                esperar(1);

                log("Runnable 02 alterará o Editoria.");
                editoria2.setNomenclatura("Atualização vinga frente ao Controlde de Concorrência LockOptimistic");

                log("Runnable 02 confirmará a transação.");
                editoriaRepository.saveAndFlush(editoria2);
            };

            // Instanciar
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);

            // Iniciar
            thread1.start();
            thread2.start();

            try {
                // Ponto de encontro
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

            var noticia3 = editoriaRepository.findById(1001L).orElseThrow();

            Assertions.assertEquals("Atualização vinga frente ao Controlde de Concorrência LockOptimistic",
                noticia3.getNomenclatura());

            log("Encerrar método de teste");
        }
    }
}

