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

        System.out.print("Digite una URL: ");

       // Scanner scanner = new Scanner(System.in);

        String entrda = "https://www.facebook.com";//scanner.nextLine();
        //System.out.println("Entrada: " + entrda);

        if (validarURL(entrda)) {
            System.out.println("URL valida!");
            System.out.println("Cantidad de lineas: " + cantidadLineas());
            System.out.println("Cantidad de parrafos: " + cantidadParrafos());
            System.out.println("Cantidad de fotos dentro de parrafos: " + cantidadFotos());

            try {
                cantidadForm(entrda);

            } catch (IOException e) {
                e.printStackTrace();
            }

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

    private static int cantidadLineas(){


        return document.html().split("/n").length;
    }

    private static int cantidadParrafos() {

        int cantidad;

        Elements elements = document.select("p");
        cantidad = elements.size();

        return cantidad;
    }

    private static int cantidadFotos(){

        int cantidad =0;

        Elements elements = document.select("p img");
        cantidad = elements.size();

        return cantidad;
    }

    private static void cantidadForm(String url) throws IOException {

        int  cantidadPost =0, cantidadGet =0, form=1;

        Elements formElement = document.select("[method=post]");
        cantidadPost = formElement.size();
        System.out.println("Cantidad de formularios con el metodo POST: " + cantidadPost);

        formElement = document.select("[method=get]");
        cantidadGet = formElement.size();
        System.out.println("Cantidad de formularios con el metodo GET: " + cantidadGet);

        Elements elements = document.select("form");

        for (Element element:elements) {
            String metodo = element.attr("method");
            System.out.println("Formulario: #"+form);


            if (metodo.equalsIgnoreCase("post")){

                Document document1 = Jsoup.connect(url)
                        .data("asignatura","practica1")
                        .header("matricula","20140565").post();

                System.out.println(document1);
            }

            Elements inputs = element.select("input");
            for (Element element1: inputs) {
                System.out.println("Tipo: " + element1.attr("type"));
            }

            System.out.println("");
            form++;
        }
    }


}
