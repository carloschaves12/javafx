package tanda2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ExpresionesRegulares extends Application {
  
  ComboBox<String> fuentes;
  double tamañoLetra;
  String fuenteLetra;
  Button boton2;
  TextArea buscarPatron;
  TextArea resultadosBusqueda;
  Spinner<Integer> spinner;
  TextField patron;
  BufferedReader ficheroGuardado;
  List<String> ficheroLeido = new ArrayList<>();

  @Override
  public void init() {
    
    boton2 = new Button("Buscar coincidencias");
    boton2.setDisable(true);
    fuentes = new ComboBox<String>();
    fuentes.setOnAction(ActionEvent -> cambiarFuente(ActionEvent));
    spinner = new Spinner<Integer>();
    resultadosBusqueda = new TextArea();
    
    fuentes.setValue("Arial");
    fuentes.getItems().addAll("Arial", "Comic Sans MS", "Impact", "Verdana", "Gill Sans");
    tamañoLetra = resultadosBusqueda.getFont().getSize();
    fuenteLetra = resultadosBusqueda.getFont().getName(); 
    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 40, (int) tamañoLetra));
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    

    primaryStage.setTitle("Expresiones regulares");
    Scene scene = new Scene(crearEscena(primaryStage));
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private VBox crearEscena(Stage primaryStage) {
    Label label1 = new Label("Texto donde buscar patrón.");
    Label label2 = new Label("Cadenas encontradas.");
    HBox hbox1 = new HBox(600, label1, label2);
    hbox1.setAlignment(Pos.CENTER);
    
    buscarPatron = new TextArea();
    Button boton1 = new Button("Cargar fichero");
    boton1.setOnAction(ActionEvent -> {
      
    cargarFichero(primaryStage);
    
    Iterator<String> iterator = ficheroLeido.iterator();
    while (iterator.hasNext()) {
      buscarPatron.appendText(iterator.next());
    }
    
    boton2.setDisable(false);
    
    });
    
    boton2.setOnAction(ActionEvent -> {
      
      resultadosBusqueda.clear();
      
      buscarCoincidencias();
      });
    
    VBox vbox1 = new VBox(10, boton1, boton2);
    vbox1.setAlignment(Pos.CENTER);
    HBox hbox2 = new HBox(20, buscarPatron, vbox1, resultadosBusqueda);
    hbox2.setAlignment(Pos.CENTER);
    
    patron = new TextField();
    patron.setPromptText("Escribe el patron");
    spinner.setOnMouseClicked(ActionEvent -> cambiarTamanio(ActionEvent));
    HBox hbox3 = new HBox(300, patron, fuentes, spinner);
    hbox3.setAlignment(Pos.CENTER);
    
    VBox vboxfinal = new VBox(40, hbox1, hbox2, hbox3);
    vboxfinal.setPadding(new Insets(40));
    vboxfinal.setAlignment(Pos.CENTER);
    return vboxfinal;
  }
  
  private void buscarCoincidencias() {
    
    String regexp = patron.getText();
    Pattern patron = Pattern.compile(regexp);
    for(String linea : ficheroLeido) {
      Matcher matcher = patron.matcher(linea);
      if (matcher.find()){
        for (int i = 0; i <= matcher.groupCount(); i++) {
          resultadosBusqueda.appendText(matcher.group() + "\n");
        }
      }
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
          ficheroLeido.add(linea + "\n");
        }
      } catch (IOException e) {
        System.out.println("fallo");
      }
    }while(linea != null);
  }
  
  public void cambiarFuente (ActionEvent e) {
    if (fuentes.getValue().toString().compareTo("Arial")==0) {
      fuenteLetra = "Arial";
    } else if (fuentes.getValue().toString().compareTo("Comic Sans MS")==0) {
      fuenteLetra = "Comic Sans MS";
    } else if (fuentes.getValue().toString().compareTo("Impact")==0) {
      fuenteLetra = "Impact";
    } else if (fuentes.getValue().toString().compareTo("Verdana")==0) {
      fuenteLetra = "Verdana";
    } else if (fuentes.getValue().toString().compareTo("Gill Sans")==0) {
      fuenteLetra = "Gill Sans";
    } else {
      fuenteLetra = fuentes.getValue().toString();
    }
    resultadosBusqueda.setFont(new Font(fuenteLetra, tamañoLetra));
    buscarPatron.setFont(new Font(fuenteLetra, tamañoLetra));    
  }
  
  public void cambiarTamanio (MouseEvent e) {
    tamañoLetra = spinner.getValue();
    resultadosBusqueda.setFont(new Font(fuenteLetra, tamañoLetra));
  }
  public static void main(String[] args) {
    Application.launch(args);
  }
}
