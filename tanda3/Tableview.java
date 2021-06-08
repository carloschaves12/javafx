package tanda3;

import java.util.Optional;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tableview extends Application {
  
  TableView<Contact> tableView = new TableView<Contact>();
  String nombre;
  String apellido;
  String telefono;
  TextArea textArea;

  @Override
  public void start(Stage primaryStage) {

    Scene scene = new Scene(crearEscena());
    primaryStage.setScene(scene);
    primaryStage.setTitle("TableView Example");
    primaryStage.show();
    
  }

  private VBox crearEscena() {
    
    TableColumn<Contact, String> column1 = new TableColumn<>("First Name");
    TableColumn<Contact, String> column2 = new TableColumn<>("Last Name");
    TableColumn<Contact, String> column3 = new TableColumn<>("Name");
    TableColumn<Contact, String> column4 = new TableColumn<>("Phone");

    column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    column3.setCellValueFactory(new PropertyValueFactory<>("name"));
    column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);
    tableView.getColumns().add(column4);

    tableView.setPlaceholder(new Label("No rows to display"));

    tableView.getItems().add(new Contact("John", "Doe", "666111222"));
    tableView.getItems().add(new Contact("Jane", "Deer", "666111222"));
    tableView.getItems().add(new Contact("Mike", "Scot", "666111222"));
    tableView.getItems().add(new Contact("Lucy", "Bonn", "666111222"));
    tableView.getItems().add(new Contact("Pepe", "Bond", "666111222"));

    textArea = new TextArea();

    Button imprimirTabla = new Button("Print Table");
    imprimirTabla.setOnAction(ActionEvent -> {
      
      for (Contact contact : tableView.getItems()) {
        textArea.appendText(contact + "\n");
      }
      
      textArea.appendText("---\n");
    });

    TableViewSelectionModel<Contact> selectionModel = tableView.getSelectionModel();

    selectionModel.setSelectionMode(SelectionMode.SINGLE);

    Button seleccionVarios = new Button("Multiple Selection");
    seleccionVarios.setOnAction(ActionEvent -> {
      
      if (selectionModel.getSelectionMode().equals(SelectionMode.SINGLE)) {
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        seleccionVarios.setText("Single Selection");
      } else {
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        seleccionVarios.setText("Multiple Selection");
      }
    });

    Button mostrarContacto = new Button("Selected Items");
    mostrarContacto.setOnAction(ActionEvent -> {
      
      ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();
      
      for (Contact contact : selectedItems) {
        textArea.appendText(contact + "\n");
      }
      
      textArea.appendText("---\n");
    });

    Button mostrarIndice = new Button("Selected Indices");
    mostrarIndice.setOnAction(ActionEvent -> {
      
      ObservableList<Integer> selectedIndices = selectionModel.getSelectedIndices();
      
      textArea.appendText("Indices: " + selectedIndices.toString() + "\n");
      textArea.appendText("---\n");
    });

    Button botonEliminar = new Button("Clear");
    botonEliminar.setOnAction(ActionEvent -> {
      
      selectionModel.clearSelection();
      
      textArea.clear();
      
    });
    
    Button botonAnadir = new Button("Add Contact");
    botonAnadir.setOnAction(ActionEvent -> {
      
      selectionModel.clearSelection();
      
      textArea.clear();
      anadirContacto();
      
    });
    
    Button botonModificar = new Button("Modify");
    botonModificar.setOnAction(ActionEvent -> {
      
      ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();
      
      int indice = 0;
      
      for (Contact contact : selectedItems) {
        nombre = contact.getFirstName();
        apellido = contact.getLastName();
        telefono = contact.getPhoneNumber();
        indice = tableView.getItems().indexOf(contact);
      }
      
      modificarContacto(nombre, apellido, telefono, indice);
      tableView.refresh();
      
      textArea.clear();
      textArea.setText("Contacto modificado");
    });
    
   
    Button botonBorrar = new Button("Delete");
    botonBorrar.setOnAction(ActionEvent -> {
      
      ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();
      
      for (Contact contact : selectedItems) {
        tableView.getItems().remove(contact);
      }
      
      tableView.refresh();
      
      textArea.clear();
      textArea.setText("Contacto eliminado");
    });
    
    ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();

    selectedItems.addListener(new ListChangeListener<Contact>() {
      
      @Override
      public void onChanged(Change<? extends Contact> change) {
        
        System.out.println("Selection changed: " + change.getList());
        }
    });
    
    HBox hBox = new HBox(10, imprimirTabla, mostrarContacto, mostrarIndice, seleccionVarios, botonEliminar);
    hBox.setAlignment(Pos.CENTER);
    HBox hBox2 = new HBox(10, botonAnadir, botonModificar, botonBorrar);
    hBox2.setAlignment(Pos.CENTER);
    VBox vboxFinal = new VBox(10, tableView, hBox, hBox2, textArea);
    vboxFinal.setPadding(new Insets(10));
    return vboxFinal;
  }
  
  private void anadirContacto() {
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Añadir Contacto");
    dialog.setHeaderText("Introduce los datos para añadir un nuevo contacto.");

    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    Label firstName = new Label("First Name: ");
    Label lastName = new Label("Last Name: ");
    Label phoneNumber = new Label ("Phone Number: ");
    
    TextField firstNameField = new TextField();
    TextField lastNameField = new TextField();
    TextField phoneNumberField = new TextField();
    
    grid.add(firstName, 0, 0);
    grid.add(lastName, 0, 1);
    grid.add(phoneNumber, 0, 2);
    grid.add(firstNameField, 1, 0);
    grid.add(lastNameField, 1, 1);
    grid.add(phoneNumberField, 1, 2);

    dialog.getDialogPane().setContent(grid);

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.get() == ButtonType.OK && firstNameField.getText() != "" && lastNameField.getText() != "" && phoneNumberField.getText() != "") {
      
      tableView.getItems().add(new Contact(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText()));
      textArea.setText("Contacto añadido");
    } else {
      textArea.setText("El contacto no se ha podido añadir");
    }
  }
  
  private void modificarContacto(String nombre, String apellido, String telefono, int indice) {
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Añadir Contacto");
    dialog.setHeaderText("Introduce los datos para añadir un nuevo contacto.");


    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    Label firstName = new Label("First Name: ");
    Label lastName = new Label("Last Name: ");
    Label phoneNumber = new Label ("Phone Number: ");
    
    TextField firstNameField = new TextField(nombre);
    firstNameField.setPromptText("Nombre");
    TextField lastNameField = new TextField(apellido);
    lastNameField.setPromptText("Apellido");
    TextField phoneNumberField = new TextField(telefono);
    
    grid.add(firstName, 0, 0);
    grid.add(lastName, 0, 1);
    grid.add(phoneNumber, 0, 2);
    grid.add(firstNameField, 1, 0);
    grid.add(lastNameField, 1, 1);
    grid.add(phoneNumberField, 1, 2);

    dialog.getDialogPane().setContent(grid);

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      
      tableView.getItems().get(indice).setFirstName(firstNameField.getText());
      tableView.getItems().get(indice).setLastName(lastNameField.getText());
      tableView.getItems().get(indice).setPhoneNumber(phoneNumberField.getText());
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}