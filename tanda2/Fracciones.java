package tanda2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fracciones extends Application {

  TextField numerador1;
  TextField denominador1;
  TextField numerador2;
  TextField denominador2;
  RadioButton suma;
  RadioButton resta;
  RadioButton producto;
  RadioButton division;
  TextField numeradorResultado;
  TextField denominadorResultado;

  @Override
  public void start(Stage primaryStage) throws Exception {

    Label label1 = new Label("Fracción 1");
    numerador1 = new TextField();
    denominador1 = new TextField();
    Separator separador1 = new Separator();
    VBox vbox1 = new VBox(5, label1, numerador1, separador1, denominador1);

    Label label2 = new Label("Fracción 2");
    numerador2 = new TextField();
    denominador2 = new TextField();
    Separator separador2 = new Separator();
    VBox vbox2 = new VBox(5, label2, numerador2, separador2, denominador2);

    suma = new RadioButton("Suma");
    resta = new RadioButton("Resta");
    producto = new RadioButton("Producto");
    division = new RadioButton("Division");
    VBox vbox3 = new VBox(10, suma, resta, producto, division);

    Button calcular = new Button("Calcula");
    calcular.setOnAction(ActionEvent -> calcular());
    VBox vbox4 = new VBox(calcular);
    vbox4.setAlignment(Pos.CENTER);

    Label label3 = new Label("Resultado");
    numeradorResultado = new TextField();
    denominadorResultado = new TextField();
    Separator separador3 = new Separator();
    VBox vbox5 = new VBox(5, label3, numeradorResultado, separador3, denominadorResultado);

    HBox hboxfinal = new HBox(30, vbox1, vbox2, vbox3, vbox4, vbox5);
    hboxfinal.setPadding(new Insets(40));
    hboxfinal.setAlignment(Pos.CENTER);

    primaryStage.setTitle("Fracciones");
    Scene scene = new Scene(hboxfinal);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void calcular() {

      if (suma.isSelected()) {
        sumarFracciones();
      } else if (resta.isSelected()) {
        restarFracciones();  
      } else if (producto.isSelected()) {
        multiplicarFracciones();
      } else if (division.isSelected()) {
        dividirFracciones();
      }
  }
  
  private void dividirFracciones() {
    numeradorResultado.setText(Integer.toString(Integer.parseInt(numerador1.getText()) * Integer.parseInt(denominador2.getText())));
    denominadorResultado.setText(Integer.toString(Integer.parseInt(denominador1.getText()) * Integer.parseInt(numerador2.getText())));
  }

  private void multiplicarFracciones() {
    numeradorResultado.setText(Integer.toString(Integer.parseInt(numerador1.getText()) * Integer.parseInt(numerador2.getText())));
    denominadorResultado.setText(Integer.toString(Integer.parseInt(denominador1.getText()) * Integer.parseInt(denominador2.getText())));
  }

  private void restarFracciones() {
    if(denominador1.getText().equals(denominador2.getText())) {
      numeradorResultado.setText(Integer.toString(Integer.parseInt(numerador1.getText()) - Integer.parseInt(numerador2.getText())));
      denominadorResultado.setText(denominador1.getText());
    }else {
      numeradorResultado.setText(Integer.toString(Integer.parseInt(numerador1.getText()) * Integer.parseInt(denominador2.getText()) - (Integer.parseInt(numerador2.getText()) * Integer.parseInt(denominador1.getText()))));
      denominadorResultado.setText(Integer.toString(Integer.parseInt(denominador1.getText()) * Integer.parseInt(denominador2.getText())));
    }
  }

  public void sumarFracciones(){
    if(denominador1.getText().equals(denominador2.getText())) {
      numeradorResultado.setText(Integer.toString(Integer.parseInt(numerador1.getText()) + Integer.parseInt(numerador2.getText())));
      denominadorResultado.setText(denominador1.getText());
    }else {
      numeradorResultado.setText(Integer.toString(Integer.parseInt(numerador1.getText()) * Integer.parseInt(denominador2.getText()) + (Integer.parseInt(numerador2.getText()) * Integer.parseInt(denominador1.getText()))));
      denominadorResultado.setText(Integer.toString(Integer.parseInt(denominador1.getText()) * Integer.parseInt(denominador2.getText())));
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}
