package tanda3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CuatroCronometros extends Application {

  Thread hilo = new Thread();
  private boolean funcionando = true;
  private int hora = 0;
  private int minuto = 0;
  private int segundo = 0;
  Button pausar;
  Button reiniciar;

  Thread hilo2 = new Thread();
  private boolean funcionando2 = true;
  private int hora2 = 0;
  private int minuto2 = 0;
  private int segundo2 = 0;
  Button pausar2;
  Button reiniciar2;
  
  Thread hilo3 = new Thread();
  private boolean funcionando3 = true;
  private int hora3 = 0;
  private int minuto3 = 0;
  private int segundo3 = 0;
  Button pausar3;
  Button reiniciar3;

  Thread hilo4 = new Thread();
  private boolean funcionando4 = true;
  private int hora4 = 0;
  private int minuto4 = 0;
  private int segundo4 = 0;
  Button pausar4;
  Button reiniciar4;
  
  @Override
  public void init() {

    pausar = new Button ("Pausar");
    reiniciar = new Button ("Reiniciar");
    
    pausar2 = new Button ("Pausar");
    reiniciar2 = new Button ("Reiniciar");
    
    pausar3 = new Button ("Pausar");
    reiniciar3 = new Button ("Reiniciar");
    
    pausar4 = new Button ("Pausar");
    reiniciar4 = new Button ("Reiniciar");
    
    pausar.setDisable(true);
    reiniciar.setDisable(true);
    
    pausar2.setDisable(true);
    reiniciar2.setDisable(true);
    
    pausar3.setDisable(true);
    reiniciar3.setDisable(true);
    
    pausar4.setDisable(true);
    reiniciar4.setDisable(true);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setScene(crearEscena());
    primaryStage.setTitle("Cronómetro");
    primaryStage.setOnCloseRequest(ActionEvent -> funcionando = false);
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

      hour.setText(String.format("%02d", hora));
      minute.setText(String.format("%02d", minuto));
      second.setText(String.format("%02d", segundo));

      iniciar.setDisable(false);
      reiniciar.setDisable(true);
      pausar.setDisable(true);
    });

    Label label3 = new Label(":");
    label3.setFont(fuente);
    Label label4 = new Label(":");
    label4.setFont(fuente);
    Label hour2 = new Label("00");
    hour2.setFont(fuente);
    Label minute2 = new Label("00");
    minute2.setFont(fuente);
    Label second2 = new Label("00");
    second2.setFont(fuente);
    Button iniciar2 = new Button ("Iniciar / Continuar");

    iniciar2.setOnAction(ActionEvent -> {

      if (!funcionando2) {
        funcionando2 = true;
      }else {

      }
      cronometro2(hour2, minute2, second2);  
      iniciar2.setDisable(true);
      pausar2.setDisable(false);
      reiniciar2.setDisable(false);
    });


    pausar2.setOnAction(ActionEvent -> {

      funcionando2 = false;
      iniciar2.setDisable(false);
      pausar2.setDisable(true);
    });

    
    reiniciar2.setOnAction(ActionEvent ->{

      funcionando2 = false;
      hora2 = 0;
      minuto2 = 0;
      segundo2 = 0;

      hour2.setText(String.format("%02d", hora2));
      minute2.setText(String.format("%02d", minuto2));
      second2.setText(String.format("%02d", segundo2));

      iniciar2.setDisable(false);
      reiniciar2.setDisable(true);
      pausar2.setDisable(true);
    });

    Label label5 = new Label(":");
    label5.setFont(fuente);
    Label label6 = new Label(":");
    label6.setFont(fuente);
    Label hour3 = new Label("00");
    hour3.setFont(fuente);
    Label minute3 = new Label("00");
    minute3.setFont(fuente);
    Label second3 = new Label("00");
    second3.setFont(fuente);
    Button iniciar3 = new Button ("Iniciar / Continuar");

    iniciar3.setOnAction(ActionEvent -> {

      if (!funcionando3) {
        funcionando3 = true;
      }else {

      }
      cronometro3(hour3, minute3, second3);  
      iniciar3.setDisable(true);
      pausar3.setDisable(false);
      reiniciar3.setDisable(false);
    });


    pausar3.setOnAction(ActionEvent -> {

      funcionando3 = false;
      iniciar3.setDisable(false);
      pausar3.setDisable(true);
    });

    
    reiniciar3.setOnAction(ActionEvent ->{

      funcionando3 = false;
      hora3 = 0;
      minuto3 = 0;
      segundo3 = 0;

      hour3.setText(String.format("%02d", hora3));
      minute3.setText(String.format("%02d", minuto3));
      second3.setText(String.format("%02d", segundo3));

      iniciar3.setDisable(false);
      reiniciar3.setDisable(true);
      pausar3.setDisable(true);
    });
    
    Label label7 = new Label(":");
    label7.setFont(fuente);
    Label label8 = new Label(":");
    label8.setFont(fuente);
    Label hour4 = new Label("00");
    hour4.setFont(fuente);
    Label minute4 = new Label("00");
    minute4.setFont(fuente);
    Label second4 = new Label("00");
    second4.setFont(fuente);
    Button iniciar4 = new Button ("Iniciar / Continuar");

    iniciar4.setOnAction(ActionEvent -> {

      if (!funcionando4) {
        funcionando4 = true;
      }else {

      }
      cronometro4(hour4, minute4, second4);  
      iniciar4.setDisable(true);
      pausar4.setDisable(false);
      reiniciar4.setDisable(false);
    });


    pausar4.setOnAction(ActionEvent -> {

      funcionando4 = false;
      iniciar4.setDisable(false);
      pausar4.setDisable(true);
    });


    reiniciar4.setOnAction(ActionEvent ->{

      funcionando4 = false;
      hora4 = 0;
      minuto4 = 0;
      segundo4 = 0;

      hour4.setText(String.format("%02d", hora));
      minute4.setText(String.format("%02d", minuto));
      second4.setText(String.format("%02d", segundo));

      iniciar4.setDisable(false);
      reiniciar4.setDisable(true);
      pausar4.setDisable(true);
    });

    
    HBox hbox = new HBox(5, hour, label1, minute, label2, second);
    hbox.setAlignment(Pos.CENTER);
    
    HBox hbox2 = new HBox(5, hour2, label3, minute2, label4, second2);
    hbox2.setAlignment(Pos.CENTER);
    
    HBox hbox3 = new HBox(5, hour3, label5, minute3, label6, second3);
    hbox3.setAlignment(Pos.CENTER);
    
    HBox hbox4 = new HBox(5, hour4, label7, minute4, label8, second4);
    hbox3.setAlignment(Pos.CENTER);
    
    HBox hbox5 = new HBox(5, iniciar, reiniciar, pausar);
    HBox hbox6 = new HBox(5, iniciar2, reiniciar2, pausar2);
    HBox hbox7 = new HBox(5, iniciar3, reiniciar3, pausar3);
    HBox hbox8 = new HBox(5, iniciar4, reiniciar4, pausar4);

    VBox vbox1 = new VBox(5, hbox, hbox5);
    VBox vbox2 = new VBox(5, hbox2,hbox6);
    VBox vbox3 = new VBox(5, hbox3,hbox7);
    VBox vbox4 = new VBox(5, hbox4,hbox8);

    VBox vboxFinal = new VBox(15, vbox1, vbox2, vbox3, vbox4);
    vboxFinal.setPadding(new Insets(50));
    Scene scene = new Scene(vboxFinal);
    return scene;
  }

  private void cronometro(Label hour, Label minute, Label second) {
    
    hilo = new Thread(new Runnable() {

      @Override
      public void run() {

        while (funcionando) { 
          System.out.println(hora + ":" + minuto + ":" + segundo + "\n");
          if (segundo < 59) {
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

  private void cronometro2(Label hour2, Label minute2, Label second2) {
    
    hilo2 = new Thread(new Runnable() {

      @Override
      public void run() {

        while (funcionando2) { 
          System.out.println(hora2 + ":" + minuto2 + ":" + segundo2 + "\n");
          if (segundo2 < 59) {
            segundo2++;
          } else if (segundo2 == 59 && minuto2 < 59) {
            minuto2++;
            segundo2 = 0;
          } else if (segundo2 == 59 && minuto2 == 59){
            hora2++;
            segundo2 = 0;
            minuto2 = 0;
          }

          Platform.runLater(new Runnable() {

            @Override
            public void run() {

              hour2.setText(String.format("%02d", hora2));
              minute2.setText(String.format("%02d", minuto2));
              second2.setText(String.format("%02d", segundo2));
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

    hilo2.start();
  }
  
  private void cronometro3(Label hour3, Label minute3, Label second3) {
    
    hilo3 = new Thread(new Runnable() {

      @Override
      public void run() {

        while (funcionando3) { 
          System.out.println(hora3 + ":" + minuto3 + ":" + segundo3 + "\n");
          if (segundo3 < 59) {
            segundo3++;
          } else if (segundo3 == 59 && minuto3 < 59) {
            minuto3++;
            segundo3 = 0;
          } else if (segundo3 == 59 && minuto3 == 59){
            hora3++;
            segundo3 = 0;
            minuto3 = 0;
          }

          Platform.runLater(new Runnable() {

            @Override
            public void run() {

              hour3.setText(String.format("%02d", hora3));
              minute3.setText(String.format("%02d", minuto3));
              second3.setText(String.format("%02d", segundo3));
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

    hilo3.start();
  }
  
  private void cronometro4(Label hour4, Label minute4, Label second4) {
    
    hilo4 = new Thread(new Runnable() {

      @Override
      public void run() {

        while (funcionando4) { 
          System.out.println(hora4 + ":" + minuto4+":" + segundo4 + "\n");
          if (segundo4 < 59) {
            segundo4++;
          } else if (segundo == 59 && minuto < 59) {
            minuto4++;
            segundo4 = 0;
          } else if (segundo4 == 59 && minuto4 == 59){
            hora4++;
            segundo4 = 0;
            minuto4 = 0;
          }

          Platform.runLater(new Runnable() {

            @Override
            public void run() {

              hour4.setText(String.format("%02d", hora4));
              minute4.setText(String.format("%02d", minuto4));
              second4.setText(String.format("%02d", segundo4));
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

    hilo4.start();
  }
  
  public static void main(String[] args) {
    launch(args);
  }

}
