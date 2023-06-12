/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.homeController;

import com.mycompany.cineboletos_proyecto.App;
import com.mycompany.cineboletos_proyecto.Context;
import com.mycompany.cineboletos_proyecto.Location;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.DDatesEvent;
import models.DatesEvent;
import models.Events;
import models.Params;
import models.UniversalDateTimeFormat;

/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class EventDetail implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public ImageView imagen;

    @FXML
    public Label nombre;

    @FXML
    public Button regresar;

    @FXML
    public TextArea synopsis;
    
    @FXML
    public TextField vip;

    @FXML
    public TextField vipM;
    
    @FXML
    public TextField plateaA;

    @FXML
    public TextField plateaB;
    
    @FXML
    public TableColumn<DatesEvent, Button> compra;

    @FXML
    public TableColumn<DatesEvent, String> fecha;
    
    @FXML
    public TableView<DatesEvent> tablaFechas;
    
    @FXML
    public TextField duracion;
    
    private final DDatesEvent _fechas = new DDatesEvent();


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Params<Events> evento = Location.getParams();
        Context.setEvent(evento.getDato());
        
        nombre.setText(evento.getDato().getName());
        synopsis.setText(evento.getDato().getSynopsis());
        String pathResource = System.getProperty("user.dir")+evento.getDato().getImage();
        imagen.setImage(new Image("file:"+pathResource));
        plateaA.setText(String.valueOf(evento.getDato().getPlanta_a()));
        plateaB.setText(String.valueOf(evento.getDato().getPlanta_b()));
        vip.setText(String.valueOf(evento.getDato().getVip()));
        vipM.setText(String.valueOf(evento.getDato().getVip_mg()));
        duracion.setText(evento.getDato().getDuration());
        this.viewsDates();
        
        regresar.setOnMouseClicked(event -> {
            App.view("Index");
        });
    }    
    
    private void viewsDates(){
        Params<Events> parametros = Location.getParams();
        ArrayList<DatesEvent> dates = _fechas.ListDateEvents(parametros.getDato().getId(),true);
        
        ObservableList<DatesEvent> ob = FXCollections.observableArrayList(dates);
        tablaFechas.setItems(ob);
        fecha.setCellFactory((TableColumn<DatesEvent, String> param) -> new TableCell<DatesEvent, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    // Obtener el objeto Usuario de la fila actual
                    DatesEvent evento = getTableView().getItems().get(getIndex());
                    // Llamar al método de tu modelo con el parámetro deseado
                    String texto = evento.getFecha_evento(UniversalDateTimeFormat.getDdMMyyy_HHmmss());
                    setText(texto);
                }
            }
        });        
        fecha.setStyle("-fx-alignment: CENTER;");
        compra.setCellFactory(param -> {
            return new TableCell<DatesEvent, Button>() {
                private final Button modificar = new Button("Comprar");

                {
                    modificar.getStyleClass().add("button-success");
                    modificar.setOnAction(event -> {
                        DatesEvent evento = getTableRow().getItem();
                        Context.setDate(evento);
                        App.view("Cantidad");
                    });
                }

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                         setGraphic(null);
                    }else{
                         setGraphic(modificar);
                    }
                }
            };  
        });
        compra.setStyle("-fx-alignment: CENTER;");  
        fecha.setMaxWidth(Double.MAX_VALUE);
    }
    
}
    
