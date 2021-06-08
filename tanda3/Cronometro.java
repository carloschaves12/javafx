package tanda3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Cronometro extends Application {

  Thread hilo = new Thread();
  private boolean funcionando = true;
  private int hora = 0;
  private int minuto = 0;
  private int segundo = 0;
  Button pausar;
  Button reiniciar;
  
  @Override
  public void init() {
    
    pausar = new Button ("Pausar");
    reiniciar = new Button ("Reiniciar");
    
    pausar.setDisable(true);
    reiniciar.setDisable(true);
  }
  
  @Override
  public void start(Stage primaryStage) {

    primaryStage.setScene(crearEscena());
    primaryStage.setTitle("Cronómetro");
    primaryStage.setOnCloseRequest(e -> funcionando = false);
    primaryStage.show();
  }

  private Scene crearEscena() {
    
    Font fuente = new Font(30);
    
    Label label1 = new Label(":");
    label1.setFont(fuente);
    
    Label label2 = new Label(":");
    label2.setFont(fuente);

    Label hour = new Label("00");
    hour.setFont(fuente);

    Label minute = new Label("00");
    minute.setFont(fuente);

    Label second = new Label("00");
    second.setFont(fuente);

    Button iniciar = new Button ("Iniciar / Continuar");

    iniciar.setOnAction(ActionEvent -> {
      
      if (!funcionando) {
        funcionando = true;
      }else {

      }
      cronometro(hour, minute, second);  
      iniciar.setDisable(true);
      pausar.setDisable(false);
      reiniciar.setDisable(false);
    });


    pausar.setOnAction(ActionEvent -> {
      
      funcionando = false;
      iniciar.setDisable(false);
      pausar.setDisable(true);
    });


    reiniciar.setOnAction(ActionEvent ->{
      
      funcionando = false;
      hora = 0;
      minuto = 0;
      segundo = 0;
      
      hour  .setText(String.format("%02d", hora));
      minute.setText(String.format("%02d", minuto));
      second.setText(String.format("%02d", segundo));
      
      iniciar.setDisable(false);
      reiniciar.setDisable(true);
      pausar.setDisable(true);
    });


    HBox hbox = new HBox(5, hour, label1, minute, label2, second);
    hbox.setAlignment(Pos.CENTER);
    hbox.setPadding(new Insets(10));
    GridPane gridPane = new GridPane();
    gridPane.add(hbox, 0, 0, 3, 1);
    gridPane.add(iniciar, 0, 1);
    gridPane.add(pausar, 1, 1);
    gridPane.add(reiniciar, 2, 1);
    gridPane.setPadding(new Insets(50));
    Scene scene = new Scene(gridPane);
    return scene;
  }

  private void cronometro(Label hour, Label minute, Label second) {
    hilo = new Thread(new Runnable() {
      
      @Override
      public void run() {
        
        while (funcionando) { 
          System.out.println(hora+":"+minuto+":"+segundo+"\n");
          if (segundo <59) {
            segundo++;
          } else if (segundo == 59 && minuto < 59) {
            minuto++;
            segundo = 0;
          } else if (segundo == 59 && minuto == 59){
            hora++;
            segundo = 0;
            minuto = 0;
          }

          Platform.runLater(new Runnable() {
            
            @Override
            public void run() {

              hour  .setText(String.format("%02d", hora));
              minute.setText(String.format("%02d", minuto));
              second.setText(String.format("%02d", segundo));
            }
          });

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }   
        }
      }
    });

    hilo.start();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}