package ejercicios;

/*
 Modifica los siguientes ejercicios para que funcionen con una GUI usando JavaFX:
 ################################################################################
 Crea una aplicación que permita adivinar un número. La aplicación genera un
 número aleatorio del 1 al 100. A continuación va pidiendo números y va
 respondiendo si el número a adivinar es mayor o menor que el introducido,
 a demás de los intentosQueQuedan que te quedan (tienes 10 intentosQueQuedan para acertarlo).
 El programa termina cuando se acierta el número (además te dice en cuantos
 intentosQueQuedan lo has acertado), si se llega al limite de intentosQueQuedan te muestra el
 número que había generado.
 ################################################################################
 Análisis
 Tenemos que generar un número aleatorio (función Aleatorio) del 1 al 100.
 Necesitamos un contador para contar los 10 intentosQueQuedan. Al principio vale 10 y
 irá decrementando.
 Mientras no acierte el número y me queden intentosQueQuedan:
 Leo un número
 Lo comparo con el número generado (digo si es mayor o menor)
 Pido otro numero
 Puedo terminar el bucle por dos razones: si he adivinado el numero (he ganado)
 y muestro los intentosQueQuedan que he necesitado.
 sino he perdido, he utilizado los 10 intentosQueQuedan y muestro el número generado.
 Datos de entrada: número pedido al usuario.
 Información de salida: Has ganado y los intentosQueQuedan, o has perdido y el número.
 */
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

public class Ej02 extends Application {

  int intentosQueQuedan = 10;
  int numSecreto;

  TextField campoDeTexto = new TextField();
  TextArea areaDeTexto = new TextArea();

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Adivinar el números");       
    primaryStage.setScene(mostrarEscena());
    primaryStage.show();
  }

  private Scene mostrarEscena() {

    Label label = new Label("Introduce un numero entre 1 y 100");

    Button boton = new Button("Adivinar");
    boton.setOnAction(ActionEvent -> {

      areaDeTexto.setText(adivinarNumero(Integer.parseInt(campoDeTexto.getText())));

      campoDeTexto.clear();

    });


    HBox hbox = new  HBox(10, label, campoDeTexto);

    Scene scene = new Scene(crearVbox(hbox, boton));
    return scene;
  }

  private VBox crearVbox(HBox hbox, Button boton) {
    VBox vbox = new VBox(10, hbox, boton, areaDeTexto);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(50));
    return vbox;
  }

  public void init() {

    numSecreto = (int) (Math.random()*100 + 1);

  }
  private String adivinarNumero(int num) {

    try {
      if (num > 100 || num < 0) {
        return ("El número introducido no está entre los valores aceptados");
      } else if (num > numSecreto && intentosQueQuedan > 0){
        return(num + " es mayor que el número a adivinar\n" + "Te quedan " + (intentosQueQuedan--) + " intentos.");
      } else if (intentosQueQuedan == 0) {
        return ("Has agotado el número máximo de intentos. El número a adivinar era: " + numSecreto);
      } else if(num < numSecreto && intentosQueQuedan > 0) {
        return(num + " es menor que el número a adivinar \n" + "Te quedan " + (intentosQueQuedan--) + " intentos.");
      }else {
        return ("Exacto! Usted adivinó en "+ intentosQueQuedan +" intentos.");
      }
    }catch (NumberFormatException e) {
      return("El valor introducido no es un numero");
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
