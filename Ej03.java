package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ej03 extends Application {

  TextField campoDeTexto = new TextField();
  TextArea areaDeTexto = new TextArea();

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Adivinar el numero de palabras");       
    primaryStage.setScene(mostrarEscena());
    primaryStage.show();
  }

  private Scene mostrarEscena() {

    Label label = new Label("Introduce la cadena");

    Button boton = new Button("Contar ");
    boton.setOnAction(ActionEvent -> {
      areaDeTexto.setText(Integer.toString(contarPalabras(campoDeTexto.getText())));
    });


    HBox hbox = new  HBox(10, label, campoDeTexto);
    hbox.setAlignment(Pos.CENTER);

    Scene scene = new Scene(crearVbox(hbox, boton));
    return scene;
  }

  private Integer contarPalabras(String cadena) {
    int contador = 1, pos;
    cadena = cadena.trim();                            
    if (cadena.isEmpty()) {
        contador = 0;
    } else {
            pos = cadena.indexOf(" ");
            while (pos != -1) {
                   contador++;
                   pos = cadena.indexOf(" ", pos + 1);
            }                                     
    }
    return contador;
  }

  private VBox crearVbox(HBox hbox, Button boton) {
    VBox vbox = new VBox(hbox, boton, areaDeTexto);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(50));
    vbox.setSpacing(10);
    return vbox;
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}
