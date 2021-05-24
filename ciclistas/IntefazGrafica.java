package ciclistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class IntefazGrafica extends Application {

  List <Ciclista> vueltaCiclista = new ArrayList<>();
  BufferedReader ficheroElegido;
  private static final String CSV_CABECERA  = "Nombre,Dorsal,Etapa1,Etapa2,Etapa3,Etapa4,Etapa5";
  TextArea areaTexto;
  Button ganadorVuelta;
  Button ganadorEtapa;
  Button ganadorCadaEtapa;
  Button mostrarTabla;
  Button exportarFichero;
  int posicion;
  List<Integer> posicionesCiclistas = new ArrayList<>();
  TextField textfield;
  String directorioInicial = System.getProperty("user.home");


  @Override
  public void init() {
    ganadorVuelta = new Button("Ganador de la vuelta");
    ganadorEtapa = new Button("Ganador de la etapa");
    ganadorCadaEtapa = new Button("Ganador de cada etapa");
    mostrarTabla = new Button("Mostrar tabla");
    exportarFichero = new Button("Exportar ganadores");
    
    ganadorVuelta.setDisable(true);
    ganadorCadaEtapa.setDisable(true);
    ganadorEtapa.setDisable(true);
    mostrarTabla.setDisable(true);
    exportarFichero.setDisable(true);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Vuelta ciclista");

    primaryStage.setScene(mostrarEscena(primaryStage));
    primaryStage.show();
  }

  private Scene mostrarEscena(Stage primaryStage) { 


    Button importarCSV = new Button("Importar CSV");
    importarCSV.setOnAction(ActionEvent -> {

      try {

        vueltaCiclista = new ArrayList<Ciclista>();
        areaTexto.clear();
        cargarCSV(primaryStage);
        mostrarCSV();

        ganadorVuelta.setDisable(false);
        ganadorCadaEtapa.setDisable(false);
        ganadorEtapa.setDisable(false);
        mostrarTabla.setDisable(false);
        exportarFichero.setDisable(false);

      } catch (IOException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("No se ha podido cargar el fichero");  
        
        alert.showAndWait();
        System.out.println("No se ha podido cargar el fichero");
      } catch (VueltaCiclistaCSVException e) {
        System.out.println("Error al cargar los ciclistas");
      }
    });


    ganadorVuelta.setOnAction(ActionEvent -> {

      areaTexto.clear();

      mostrarGanadorVueltaCiclista();

    });

    
    ganadorEtapa.setOnAction(ActionEvent -> {

      areaTexto.clear();

      try {
        posicion = Integer.parseInt(textfield.getText());
      } catch (NumberFormatException e) {
        areaTexto.setText("El valor introducido no es valido");
      }
      if (posicion < 1 || posicion > 5) {
        areaTexto.setText("El numero de etapa introducido es incorrecto");
      } else {

        areaTexto.appendText(getCiclista(ganadorEtapa(posicion)).toString());
        textfield.clear();
      }

    });

    
    ganadorCadaEtapa.setOnAction(ActionEvent -> {

      areaTexto.clear();

      ganadorCadaEtapa();

      Iterator<Integer> iterador = posicionesCiclistas.iterator();
      while(iterador.hasNext()) {
        Ciclista ganadorEtapa = getCiclista(iterador.next());
        areaTexto.appendText(ganadorEtapa.toString());
      }

    });

    
    mostrarTabla.setOnAction(ActionEvent -> {

      areaTexto.clear();

      mostrarCSV();
    });
    
    exportarFichero.setOnAction(ActionEvent-> {
      
      areaTexto.clear();
      try {
      FileChooser rutaFichero = new FileChooser();
      rutaFichero.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
      File miFichero = rutaFichero.showSaveDialog(primaryStage);
       guardarCSV(miFichero.getAbsolutePath());
       Mensaje("Operación realizada con éxito", null, AlertType.INFORMATION);
        
      } catch (NullPointerException | IOException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("No se ha podido cargar el fichero");  
        
        alert.showAndWait();
      }
    });

    textfield = new TextField();
    
    HBox hbox1 = new HBox (20, ganadorEtapa, textfield);

    VBox vbox1 = new VBox(20, importarCSV, ganadorVuelta, hbox1, ganadorCadaEtapa, exportarFichero, mostrarTabla);

    areaTexto = new TextArea();
    areaTexto.setEditable(false);
    
    VBox vbox2 = new VBox(areaTexto);

    HBox hbox2 = new HBox(50, vbox1, vbox2);
    hbox2.setPadding(new Insets(50));

    Scene scene = new Scene(hbox2);

    return scene;

  }


  private List<Integer> ganadorCadaEtapa() {
    posicionesCiclistas = new ArrayList<Integer>();

    for (int j = 1; j < 6; j++) {
      posicionesCiclistas.add(ganadorEtapa(j));
    }
    return posicionesCiclistas;
  }

  private int ganadorEtapa(int etapa) {

    int tiempo = 999999;
    int posicionCiclista = 0;
    for(int i = 0; i < vueltaCiclista.size(); i++) {
      if (tiempo > vueltaCiclista.get(i).getTiempoEtapa(etapa)) {
        tiempo = vueltaCiclista.get(i).getTiempoEtapa(etapa);
        posicionCiclista = i;
      }
    }
    return posicionCiclista;
  }

  public Ciclista getCiclista(int posicion) {

    return vueltaCiclista.get(posicion);

  }

  private void mostrarGanadorVueltaCiclista() {

    areaTexto.appendText(Collections.min(vueltaCiclista).toString());

  }

  private void mostrarCSV() {
    Iterator<Ciclista> iterador = vueltaCiclista.iterator();
    while(iterador.hasNext()) {
      areaTexto.appendText(iterador.next().toString());
    }
  }

  public void cargarCSV(Stage primaryStage) throws IOException, VueltaCiclistaCSVException {
    elegirFichero(primaryStage);
    if (ficheroElegido != null){
      leerFichero();  
    }
    
  }

  private void leerFichero() throws IOException, VueltaCiclistaCSVException {
    validarCabeceraCSV();
    String line;
    while ((line = ficheroElegido.readLine()) != null) {
      cargarCiclistaCSV(line);
    }
    Collections.sort(vueltaCiclista);
  }

  private void cargarCiclistaCSV(String line) throws VueltaCiclistaCSVException {
    String[] camposCiclista = line.split(",");

    String nombre = camposCiclista[0].replace("\"", "");
    int dorsal = Integer.parseInt(camposCiclista[1].replace("\"", ""));
    int etapa1 = Integer.parseInt(camposCiclista[2].replace("\"", ""));
    int etapa2 = Integer.parseInt(camposCiclista[3].replace("\"", ""));
    int etapa3 = Integer.parseInt(camposCiclista[4].replace("\"", ""));
    int etapa4 = Integer.parseInt(camposCiclista[5].replace("\"", ""));
    int etapa5 = Integer.parseInt(camposCiclista[6].replace("\"", ""));

    int[] tiempos = {etapa1,etapa2,etapa3,etapa4,etapa5};

    try {
      vueltaCiclista.add(new Ciclista(nombre, dorsal, tiempos));
    } catch (CiclistaErrorException e) {
      throw new VueltaCiclistaCSVException(e.getMessage());
    }

  }

  private void validarCabeceraCSV() throws IOException, VueltaCiclistaCSVException {
    String head = ficheroElegido.readLine().trim();
    if (! head.equalsIgnoreCase(CSV_CABECERA)) {
      throw new VueltaCiclistaCSVException("Cabecera err�nea en el CSV.");
    }
  }

  private void elegirFichero(Stage primaryStage) {

    try {
      FileChooser fileChooser = new FileChooser();
      File fichero = fileChooser.showOpenDialog(primaryStage);
      ficheroElegido = new BufferedReader(new FileReader(fichero));
    } catch (FileNotFoundException | NullPointerException e) {
      areaTexto.setText("Fichero no encontrado");
    }
  }
  
  public void guardarCSV(String nombreFichero) throws IOException {

    var file = Files.newBufferedWriter(Paths.get(nombreFichero), StandardOpenOption.CREATE);

    guardarCabeceraCSV(file);
    for (int i = 1; i < 6; i++) {
      int ganador = ganadorEtapa(i);
      Ciclista ciclista = getCiclista(ganador);
      guardarCiclistasCSV(ciclista, file);
    }
    file.close();
  }

  public  void guardarCabeceraCSV(BufferedWriter file) throws IOException {
    file.write(CSV_CABECERA);
    file.newLine();
  }
  
  private static void guardarCiclistasCSV(Ciclista ciclista, BufferedWriter file) throws IOException {
    file.write("\"" + ciclista.getNombre() + "\",");
    file.write("\"" + ciclista.getDorsal() + "\",");
    file.write("\"" + ciclista.getTiempoEtapa(1) + "\",");
    file.write("\"" + ciclista.getTiempoEtapa(2) + "\",");
    file.write("\"" + ciclista.getTiempoEtapa(3) + "\",");
    file.write("\"" + ciclista.getTiempoEtapa(4) + "\","); 
    file.write("\"" + ciclista.getTiempoEtapa(5) + "\","); 
    file.newLine();
  }
  
  @SuppressWarnings("exports")
  public static void Mensaje(String content, String header, AlertType type) {
    Alert alert = new Alert(type);
    alert.setContentText(content);
    alert.setHeaderText(header);
    alert.showAndWait();
  }
  
  public static void main(String[] args) {
    launch(args);
  }

}
