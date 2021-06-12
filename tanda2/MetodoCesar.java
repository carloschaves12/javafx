package tanda2;

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
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MetodoCesar extends Application {

  TextArea textarea1;
  TextArea textarea2;
  TextField textfield;
  BufferedReader ficheroGuardado;
  Button boton1;
  Button boton2;
  Button boton4;
  Button boton5;
  Slider slide;
  List<String> ficheroLeido = new ArrayList<>();
  List <String> ficheroFinal = new ArrayList<String>();

  @Override
  public void init() {
    boton1 = new Button("<");
    boton2 = new Button(">");
    boton4 = new Button("Guardar fichero");
    boton5 = new Button("Desencriptar");
    textfield = new TextField();

    boton1.setDisable(true);
    boton2.setDisable(true);
    boton4.setDisable(true);
    boton5.setDisable(true);
    textfield.setText("1");
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Metodo Cesar");
    Scene scene = new Scene(crearEscena(primaryStage));
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private VBox crearEscena(Stage primaryStage) {
    Label label1 = new Label("Texto Encriptado");
    Label label2 = new Label("Texto Desencriptado");
    HBox hbox1 = new HBox(700,label1, label2);

    textarea1 = new TextArea();
    textarea1.setEditable(false);
    textarea2 = new TextArea();
    textarea2.setEditable(false);

    Label label3 = new Label("Clave");
    slide =new Slider();
    slide.setOnMousePressed(ActionEvent -> moverSlide(ActionEvent));
    VBox vbox = new VBox(20,label3, textfield, slide);
    HBox hbox2 = new HBox (30,textarea1,vbox,textarea2);

    boton1.setOnAction(ActionEvent -> {
      textfield.setText(Integer.toString(Integer.parseInt(textfield.getText()) - 1));
      botonesSlide(ActionEvent);
    });
    boton2.setOnAction(ActionEvent -> {
      textfield.setText(Integer.toString(Integer.parseInt(textfield.getText()) + 1));
      botonesSlide(ActionEvent);
    });
    HBox hbox3 = new HBox(20, boton1, boton2);
    Button boton3 = new Button("Cargar fichero");
    boton3.setOnAction(ActionEvent -> {

      boton1.setDisable(false);
      boton2.setDisable(false);
      boton4.setDisable(false);
      boton5.setDisable(false);

      cargarFichero(primaryStage);

      Iterator<String> iterator = ficheroLeido.iterator();
      while (iterator.hasNext()) {
        textarea1.appendText(iterator.next());
      }
    });

    HBox hbox4 = new HBox(65,boton3,hbox3,boton4);
    hbox4.setAlignment(Pos.CENTER);
    
    boton4.setOnAction(ActionEvent -> guardarFichero(primaryStage));

    boton5.setOnAction(ActionEvent -> desencriptar());

    HBox hbox5 = new HBox(boton5);
    hbox5.setAlignment(Pos.CENTER);

    VBox vboxfinal = new VBox(20,hbox1,hbox2,hbox4, hbox5);
    vboxfinal.setPadding(new Insets(40));
    return vboxfinal;
  }
  
  private void escribirFichero() {
    ficheroFinal.add(textarea2.getText());
  }
  
  private void guardarFichero(Stage primaryStage) {
    FileChooser file = new FileChooser();
    
    File ficheroprueba = file.showSaveDialog(primaryStage);
    
    try {
      PrintWriter ficheroguardado = new PrintWriter(ficheroprueba);
      escribirFichero();
      Iterator<String> iterador = ficheroFinal.iterator();

      while(iterador.hasNext()) {
        ficheroguardado.write(iterador.next());
      }
      ficheroguardado.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("No se ha podido escribir en el fichero");
    }
  }
  
  public void moverSlide(MouseEvent e) {
    slide.valueProperty().addListener((observable, oldValue, newValue) ->  {
      textfield.setText(Integer.toString(observable.getValue().intValue()));
    });  
  }

  public void botonesSlide(ActionEvent e) {
    if (e.getSource().equals(boton2)) {
      slide.valueProperty().setValue(slide.valueProperty().getValue().intValue()+1);
      textfield.setText(Integer.toString(slide.valueProperty().getValue().intValue()));
    } else if (e.getSource().equals(boton1)) {
      slide.valueProperty().setValue(slide.valueProperty().getValue().intValue()-1);
      textfield.setText(Integer.toString(slide.valueProperty().getValue().intValue()));
    }
  }

  public void cargarFichero(Stage primaryStage){

    try {
      FileChooser fileChooser = new FileChooser();
      File fichero1 = fileChooser.showOpenDialog(primaryStage);
      ficheroGuardado = new BufferedReader(new FileReader(fichero1));
    } catch (FileNotFoundException e) {
      System.out.println("No se ha podido cargar el fichero");
    }
    leerFichero();
  }

  public void leerFichero() {
    String linea = "";
    do {
      try {
        linea = ficheroGuardado.readLine();
        if(linea != null) {
          ficheroLeido.add(linea);
        }
      } catch (IOException e) {
        System.out.println("fallo");
      }
    }while(linea != null);
  }

  public void desencriptar() {
    String desencriptado = (descifradoCesar(textarea1.getText(), Integer.parseInt(textfield.getText())));
    textarea2.setText(desencriptado);
  }

  public static String descifradoCesar(String texto, int codigo) {
    String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúüñÁÉÍÓÚÜÑ";
    String cadenaDesencriptada = "";
    for (char caracter: texto.toCharArray()) {
      char caracterDesencriptado = caracter;
      // si el carácter es alfabético, desencriptamos
      if (letras.contains(Character.toString(caracter))) {
        int posicionDondeEsta = letras.indexOf(caracter);
        int posicionCaracterDesencriptado = ((posicionDondeEsta - codigo) % letras.length());
        if (posicionCaracterDesencriptado < 0) {
          posicionCaracterDesencriptado = letras.length() + posicionCaracterDesencriptado;
        }
        caracterDesencriptado = letras.charAt(posicionCaracterDesencriptado);
      } 
      cadenaDesencriptada += caracterDesencriptado;
    }
    return cadenaDesencriptada;
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}
