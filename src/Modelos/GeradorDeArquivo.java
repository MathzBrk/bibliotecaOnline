package Modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeradorDeArquivo {
    static Gson gson = new GsonBuilder().
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    public void gerarArquivoDeLivro(Livro livro) throws IOException {
        // Validar e sanitizar o título para usar como nome de arquivo
        String nomeArquivo = livro.getTitulo().replaceAll("[\\/:*?\"<>|]", "_") + ".json";

        try (FileWriter escrita = new FileWriter(nomeArquivo)) {
            escrita.write(gson.toJson(livro));
        } catch (IOException e) {
            // Lida com erros ao escrever no arquivo
            System.err.println("Erro ao gerar o arquivo: " + e.getMessage());
            throw e; // Re-throw a exceção para que o chamador possa lidar com ela, se necessário
        }
    }

    public void gerarArquivoDeLista(List<Livro> lista, String caminhoArquivo) throws IOException {
        try (FileWriter escrita = new FileWriter(caminhoArquivo)) {
            // Converte a lista para JSON e escreve no arquivo
            String json = gson.toJson(lista);
            escrita.write(json);
        } catch (IOException e) {
            // Lida com erros ao escrever no arquivo
            System.err.println("Erro ao gerar o arquivo: " + e.getMessage());
            throw e; // Re-throw a exceção para que o chamador possa lidar com ela, se necessário
        }
    }
}
