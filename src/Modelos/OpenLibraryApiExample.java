package Modelos;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OpenLibraryApiExample {

    static Gson gson = new GsonBuilder().
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    public static LivroOpenLibrary buscarLivroPorISBN(String isbn) {

        String urlString = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();

                // A resposta da API é um objeto com a chave "ISBN:<isbn>"
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                JsonObject livroJson = jsonObject.getAsJsonObject("ISBN:" + isbn);

                if (livroJson != null) {
                    // Extraindo título
                    String title = livroJson.get("title").getAsString();

                    // Extraindo autores
                    List<LivroOpenLibrary.Autor> authors = new ArrayList<>();
                    JsonArray authorsArray = livroJson.getAsJsonArray("authors");
                    if (authorsArray != null) {
                        for (JsonElement authorElement : authorsArray) {
                            JsonObject authorObject = authorElement.getAsJsonObject();
                            authors.add(new LivroOpenLibrary.Autor(authorObject.get("name").getAsString()));
                        }
                    }

                    // Extraindo número de páginas
                    int numberOfPages = livroJson.has("number_of_pages") ? livroJson.get("number_of_pages").getAsInt() : 0;

                    // Extraindo editoras
                    List<LivroOpenLibrary.Publisher> publishers = new ArrayList<>();
                    JsonArray publishersArray = livroJson.getAsJsonArray("publishers");
                    if (publishersArray != null) {
                        for (JsonElement publisherElement : publishersArray) {
                            JsonObject publisherObject = publisherElement.getAsJsonObject();
                            publishers.add(new LivroOpenLibrary.Publisher(publisherObject.get("name").getAsString()));
                        }
                    }

                    LivroOpenLibrary livroOpen = new LivroOpenLibrary(title, authors, numberOfPages, publishers);

                    return livroOpen;
                } else {
                    System.out.println("Nenhum dado encontrado para o ISBN: " + isbn);
                }
            } else {
                System.out.println("Erro na conexão: " + response.statusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro de I/O: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Requisição interrompida: " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
