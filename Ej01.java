package ejercicios;

/*
  Modifica los siguientes ejercicios para que funcionen con una GUI usando JavaFX:
  ################################################################################
  Mostrar en pantalla los N primeros números primos. Se pide por teclado la cantidad
  de números primos que queremos mostrar.
  ################################################################################
  Análisis
  Tengo que leer la cantidad de números primos que voy a mostrar. La cantidad debe
  ser positivo. El primer número primo es el 2 (lo muestro) a partir de este son
  todos impares. Voy probando desde el 3 todos los impares hasta que muestre la
  cantidad que hemos indicados (necesito un contador).
  Para comprobar si son primos, los voy dividiendo desde 3 hasta la raíz cuadrada
  del número, si es divisible por algún número no es primo (necesito un indicador).
  Datos de entrada: Cantidad de números a mostrar.
  Información de salida: Los números primos indicados.
 */

import javafx.scene.control.TextArea;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ej01 extends Application {

  TextField campoDeTexto;
  TextArea areaDeTexto;

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Los n primeros numeros primos");

    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("calculadora.png")));
    primaryStage.setScene(mostrarEscena());
    primaryStage.show();
  }

  private Scene mostrarEscena() {

    Label label = new Label("Introduce el numero de primos que quieres que se impriman");
    label.setTextFill(Color.web("Green",1));

    // Le asigno al boton un string y la imagen que he importado
    Button boton = new Button("Calcular", importarFoto());
    boton.setDefaultButton(true);
    boton.setOnAction(ActionEvent -> {
      
      mostrarTextArea();
    });

    campoDeTexto = new TextField();
    areaDeTexto = new TextArea();
    areaDeTexto.setEditable(false);
    areaDeTexto.setPadding(new Insets(10));

    HBox hbox = new  HBox(10, label, campoDeTexto);

    VBox vbox = new VBox(10, hbox, boton, areaDeTexto);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(50));

    Scene scene = new Scene(vbox);
    return scene;

  }

  private void mostrarTextArea() {
    
    try {

      Ej01Primos.setN(Integer.parseInt(campoDeTexto.getText())); 

      Ej01Primos.comprobarPrimo();

      areaDeTexto.clear();

      Iterator<Integer> iterador = Ej01Primos.listaPrimos.iterator();
      int cont1 = 0;
      while(iterador.hasNext()) {
        cont1++;
        areaDeTexto.appendText("Primo " + cont1 + ": " + iterador.next() + "\n");
      }

      Ej01Primos.listaPrimos.clear();
      
    }catch(NumberFormatException e) {
      areaDeTexto.appendText("El valor introducido no es un numero");
      
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Exception Dialog");
      alert.setHeaderText("Se ha producido una excepción.");
      alert.setContentText("Esta aplicación solo permite introducir dígitos.");  

      alert.showAndWait();
    }
  }

  private ImageView importarFoto() {
    // Importo la imagen y le doy tamaño
    Image img = new Image(getClass().getResourceAsStream("calculadora.png"));
    ImageView imgView = new ImageView(img);
    imgView.setFitHeight(50);
    imgView.setFitWidth(50);
    return imgView;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
