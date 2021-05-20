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

public class Ej05 extends Application {

  TextField campoDeTexto = new TextField();
  TextArea areaDeTexto = new TextArea();

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Calcular el cambio");       
    primaryStage.setScene(mostrarEscena());
    primaryStage.show();
  }

  private Scene mostrarEscena() {

    Label label = new Label("Introduce el cambio");

    Button boton = new Button("Calcular");
    boton.setOnAction(ActionEvent -> {
      try {
        devolverCambio(Double.parseDouble(campoDeTexto.getText()));
      }catch(NumberFormatException e) {
        areaDeTexto.setText("El valor introducido no es un numero");
      }
    });

    VBox hBox2 = new VBox (crearHBox(label), crearVBox(boton));

    Scene scene = new Scene(hBox2);
    return scene;
  }

  private VBox crearVBox(Button boton) {
    VBox vBox = new VBox(10, boton, areaDeTexto);
    areaDeTexto.setEditable(false);
    vBox.setAlignment(Pos.CENTER);
    vBox.setPadding(new Insets(20));
    return vBox;
  }

  private HBox crearHBox(Label label) {
    HBox hbox = new HBox(10, label, campoDeTexto);
    hbox.setAlignment(Pos.CENTER);
    hbox.setPadding(new Insets(5));
    return hbox;
  }

  public void devolverCambio(double num) {

    double [] monedas={500, 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.20, 0.10, 0.05, 0.02, 0.01};

    // Creamos un array con 0 de longitud igual a la cantidad de monedas
    // Este array contendra las monedas a devolver
    double [] devolver={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    // Recorremos todas las monedas
    for(int i=0;i<monedas.length;i++)
    {
      // Si el importe actual, es superior a la moneda
      if(num>=monedas[i])
      {
        // obtenemos cantidad de monedas
        devolver[i]=Math.floor(num/monedas[i]);

        // actualizamos el valor del importe que nos queda por didivir
        num=num-(devolver[i]*monedas[i]);
      }
    }

    // Bucle para mostrar el resultado
    for(int i=0;i<monedas.length;i++)
    {
      if(devolver[i]>0)
      {
        if(monedas[i]>2)
        {
          areaDeTexto.appendText("Hay "+devolver[i]+" billetes de: "+monedas[i]+" Euros\n");
        }else{
          areaDeTexto.appendText("Hay "+devolver[i]+" monedas de: "+monedas[i]+" Euros\n");
        }
      }
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
