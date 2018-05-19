import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Digite una URL: ");

        Scanner scanner = new Scanner(System.in);

        String entrda = scanner.nextLine();
        //System.out.println("Entrada: " + entrda);

        if (validarURL(entrda)) {
            System.out.println("URL valida!");
            System.out.println("Cantidad de Parrafos: " + cantidadParrafos(entrda));

        }
        else {
            System.out.println("URL invalida!");
        }
    }

    public static boolean validarURL(String url){

        try {
            Connection.Response response = Jsoup.connect(url).execute();
            if (response.statusCode() == 200)
            {
                return true;
            }
        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static int cantidadParrafos(String url){

        int cantidad = 0;

        Document document = Jsoup.parse(url);
       Elements elements = document.select("p");

        for (Element element: elements) {


            cantidad++;
        }

        return cantidad;
    }
}
