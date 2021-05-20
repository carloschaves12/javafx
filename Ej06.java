package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Ej06 extends Application{

  BufferedReader fichero1Guardado;
  BufferedReader fichero2Guardado;
  TextArea areaDeTexto = new TextArea();
  List <String> ficheroFinal = new ArrayList<String>();


  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Mezclar dos ficheros");

    primaryStage.setScene(mostrarEscena(primaryStage));
    primaryStage.show();

  }

  private Scene mostrarEscena(Stage primaryStage) {

    Button boton1 = crearBoton1(primaryStage);

    Button boton2 = crearBoton2(primaryStage);

    HBox hbox = new HBox(20, boton1, boton2);
    hbox.setAlignment(Pos.CENTER);

    Button boton3 = boton3(primaryStage);

    VBox vbox = new VBox(30, hbox, boton3,areaDeTexto);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(30));
    areaDeTexto.setEditable(false);

    Scene scene = new Scene(vbox);
    return scene;
  }

  private Button boton3(Stage primaryStage) {

    Button boton3 = new Button("Mezclar ficheros");
    boton3.setMinHeight(35);
    boton3.setMinWidth(20);
    boton3.setOnAction(ActionEvent -> {

      try {
        mezclarFicheros();
      } catch (IOException e) {
        System.out.println("No se han encontrado los ficheros");
      }
      
      guardarFichero(primaryStage);

      Iterator<String> iterador = ficheroFinal.iterator();
      while(iterador.hasNext()) {
        areaDeTexto.appendText(iterador.next());
      }
      
    });
    return boton3;
  }

  
  private void guardarFichero(Stage primaryStage) {
    FileChooser file = new FileChooser();
    
    File ficheroprueba = file.showSaveDialog(primaryStage);
    
    try {
      PrintWriter ficheroguardado = new PrintWriter(ficheroprueba);
      
      Iterator<String> iterador = ficheroFinal.iterator();

      while(iterador.hasNext()) {
        ficheroguardado.write(iterador.next());
      }
      ficheroguardado.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("No se ha podido escribir en el fichero");
    }
  }

  private Button crearBoton2(Stage primaryStage) {

    Button boton2 = new Button("Elegir fichero 2");
    boton2.setMinHeight(35);
    boton2.setMinWidth(20);
    boton2.setOnAction(ActionEvent -> elegirFichero2(primaryStage));
    return boton2;
  }

  private Button crearBoton1(Stage primaryStage) {

    Button boton1 = new Button("Elegir fichero 1");
    boton1.setMinHeight(35);
    boton1.setMinWidth(20);
    boton1.setOnAction(ActionEvent -> elegirFichero1(primaryStage));
    return boton1;
  }

  private void mezclarFicheros() throws IOException {

    String lineaFichero1;
    String lineaFichero2;

    do {
      lineaFichero1 = fichero1Guardado.readLine();
      if(lineaFichero1 != null) {
        ficheroFinal.add(lineaFichero1 + "\n");
      }

      lineaFichero2 = fichero1Guardado.readLine();
      if(lineaFichero2 != null) {
        ficheroFinal.add(lineaFichero2 + "\n");
      }
    }while(lineaFichero1 != null && lineaFichero2 != null);

  }

  private void elegirFichero2(Stage primaryStage) {

    try {
      FileChooser fileChooser = new FileChooser();
      File fichero2 = fileChooser.showOpenDialog(primaryStage);
      fichero2Guardado = new BufferedReader(new FileReader(fichero2));
    } catch (FileNotFoundException e) {
      System.out.println("Stage");
    }
  }

  private void elegirFichero1(Stage primaryStage) {

    try {
      FileChooser fileChooser = new FileChooser();
      File fichero1 = fileChooser.showOpenDialog(primaryStage);
      fichero1Guardado = new BufferedReader(new FileReader(fichero1));
    } catch (FileNotFoundException e) {
      System.out.println("Stage");
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
