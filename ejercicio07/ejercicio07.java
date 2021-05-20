package ejercicios.ejercicio07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ejercicio07 extends Application {

  static List<String> sinComentarios = new ArrayList<String>();

  @Override
  public void start(Stage primaryStage) {
    
    
    try {
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("Ejercicio07.fxml"));
      var root = fxml.<Pane>load();
      var scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName()); 
      primaryStage.show();

    } catch(IOException e) {
      System.out.println("No se ha podido cargar el archivo fxml");
      e.printStackTrace();
    }
  }
  
  static void quitarComentarios() {
    
    FileChooser fileChooser = new FileChooser();
    File ficheroComentarios = fileChooser.showOpenDialog(null);
    
    try {
      BufferedReader origen = new BufferedReader(new FileReader(ficheroComentarios));
      
      boolean estoyEnUnBloqueDeComentarios = false;
      String lineaLeida; 
      String lineaaEscribir;

      while ((lineaLeida=origen.readLine()) != null) {
        lineaaEscribir = lineaLeida;

        if (lineaLeida.contains("/*")) {
          estoyEnUnBloqueDeComentarios = true;

        } else if (lineaLeida.contains("//")) {
          int posComentario = lineaLeida.indexOf("//");
          lineaaEscribir = lineaLeida.substring(0, posComentario);
        }

        if (! estoyEnUnBloqueDeComentarios) {
          sinComentarios.add(lineaaEscribir + "\n");
        }

        if (lineaLeida.contains("*/")) {
          estoyEnUnBloqueDeComentarios = false;
        }
      }
      origen.close();
      
    }catch (FileNotFoundException excp) {
      System.err.println("No se encuentra el archivo.");
    } catch (IOException excp) {
      System.err.println("Error de entrada/salida al manejar el fichero");
    }
  }
 
  public static List<String> getSinComentarios() {
    return sinComentarios;
  }

  public static void setSinComentarios(List<String> sinComentarios) {
    ejercicio07.sinComentarios = sinComentarios;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
