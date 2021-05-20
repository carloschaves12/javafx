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

public class Ej04 extends Application{

  TextField campoDeTexto1 = new TextField();
  TextField campoDeTexto2 = new TextField();
  TextArea areaDeTexto = new TextArea();

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Calcular el perímetro y area de un cuadrado");       
    primaryStage.setScene(mostrarEscena());
    primaryStage.show();
  }

  private Scene mostrarEscena() {

    Label label1 = new Label("Introduce la altura");
    Label label2 = new Label("Introduce la base");
    
    
    Button boton = new Button("Calcular");
    boton.setOnAction(ActionEvent ->{
      try {
    areaDeTexto.setText(
        calcularAreaYPerimetro(Float.parseFloat(campoDeTexto1.getText()), Float.parseFloat(campoDeTexto2.getText())));
      }catch(NumberFormatException e) {
        areaDeTexto.setText("El valor introducido no es un numero");
      }
    });
    
    VBox hBox3 = new VBox (crearVBox(label1, label2), crearVBox2(boton));
    
    Scene scene = new Scene(hBox3);
    return scene;
  }

  private VBox crearVBox2(Button boton) {
    VBox vBox2 = new VBox(10, boton, areaDeTexto);
    vBox2.setAlignment(Pos.CENTER);
    vBox2.setPadding(new Insets(20));
    return vBox2;
  }

  private VBox crearVBox(Label label1, Label label2) {
    HBox hBox1 = new HBox(10, label1, campoDeTexto1);
    hBox1.setAlignment(Pos.CENTER);
    hBox1.setPadding(new Insets(5));
    
    HBox hBox2 = new HBox(10, label2, campoDeTexto2);
    hBox2.setAlignment(Pos.CENTER);
    hBox2.setPadding(new Insets(5));
    
    VBox vBox1 = new VBox(hBox1, hBox2);
    return vBox1;
  }
  
  public String calcularAreaYPerimetro(float base, float altura){
    
    float area = base * altura;
    float perimetro = (base * 2) + (altura * 2);
    return ("El area del rectangulo es: " + area + "\nEl perimetro del rectangulo es: " + perimetro);
  }
  
  public static void main(String[] args) {
    Application.launch(args);
  }
}
