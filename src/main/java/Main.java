import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Document document;
    public static void main(String[] args) {

        System.out.println("Digite una URL: ");

       // Scanner scanner = new Scanner(System.in);

        String entrda = "https://developer.mozilla.org/en-US/docs/Learn/HTML/Forms/Your_first_HTML_form";//scanner.nextLine();
        //System.out.println("Entrada: " + entrda);

        if (validarURL(entrda)) {
            System.out.println("URL valida!");

            System.out.println("Cantidad de Parrafos: " + cantidadParrafos(entrda));
            System.out.println("Cantidad de Fotos dentro de parrafos: " + cantidadFotos(entrda));

            cantidadForm(entrda);

        }
        else {
            System.out.println("URL invalida!");
        }
    }

    private static boolean validarURL(String url){

        try {

            document = Jsoup.connect(url).get();


        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

        private static int cantidadParrafos(String url) {

        int cantidad = 0;

        Elements elements = document.select("p");
        cantidad = elements.size();

        return cantidad;
    }

    private static int cantidadFotos(String url){

        int cantidad =0;

        Elements elements = document.select("p img");
        cantidad = elements.size();

        return cantidad;
    }

    private static void cantidadForm(String url){

        int  cantidadPost =0, cantidadGet =0, form=1;

        Elements formElement = document.select("[method=post]");
        cantidadPost = formElement.size();
        System.out.println("Cantidad de formularios con el metodo POST: " + cantidadPost);

        formElement = document.select("[method=get]");
        cantidadGet = formElement.size();
        System.out.println("Cantidad de formularios con el metodo GET: " + cantidadGet);

        Elements elements = document.select("form");

        for (Element element:elements) {

            System.out.println("Formulario: #"+form);

            Elements inputs = element.select("input");
            for (Element element1: inputs) {
                System.out.println("Tipo: " + element1.attr("type"));
            }

            System.out.println("");
            form++;
            

        }


    }
}
