package ejercicios.ejercicio07;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Ejercicio07Controller {

  @FXML
  private Button elegir;

  @FXML
  private TextArea textArea;

  @FXML
  void elegirFichero(ActionEvent event) {

    textArea.clear();

    ejercicio07.quitarComentarios();

    Iterator<String> iterador = ejercicio07.sinComentarios.iterator();
    @SuppressWarnings("unused")
    int cont1 = 0;
    while(iterador.hasNext()) {
      cont1++;
      textArea.appendText(iterador.next());
            
      ejercicio07.setSinComentarios(new ArrayList<String>());
    }   
  }
}
