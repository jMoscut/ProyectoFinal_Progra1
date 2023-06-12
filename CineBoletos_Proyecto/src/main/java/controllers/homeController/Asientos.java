/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.homeController;

import com.mycompany.cineboletos_proyecto.App;
import com.mycompany.cineboletos_proyecto.Context;
import com.mycompany.cineboletos_proyecto.Location;
import com.mycompany.cineboletos_proyecto.Validations;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.DAsientos;
import models.Events;
import models.Params;

/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class Asientos implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    public GridPane initialGrid;
    
    /**
     * Etiqueta que muestra la vista de los asientos.
    */
    @FXML
    public Label viewAsientos;
    
    @FXML
    public Button regresar;
    
    @FXML
    public TextField plateaA;

    @FXML
    public TextField plateaB;
    
    @FXML
    public TextField vip;

    @FXML
    public TextField vipM;
    
    @FXML
    public Label timerLabel;
    
    private Timeline timeline;
    private  int DURATION_IN_SECONDS = 10;
    private final DAsientos _asientos = new DAsientos();
    private final int fontSize = 8;
    private final ArrayList<String> seleccionados = new ArrayList<>();
    private final GridPane container = new GridPane();
    private ArrayList<String> idSeleccion = new ArrayList<>();
    private final ArrayList<models.Asientos> todosList = new ArrayList<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        regresar.setOnMouseClicked(event -> {
            App.view("Cantidad");
        });
        
        Params<Integer> params = Location.getParams();
        viewAsientos.setText(String.format("%s Asientos seleccionados de %s :",seleccionados.size(),params.getDato()));
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        
        vbox.setStyle("-fx-column-count: 2; -fx-column-gap: 10px; -fx-pref-width: 50%; -fx-margin-top: 200px;");
        
        plateaA.setText(String.valueOf(Context.getEvent().getPlanta_a()));
        plateaB.setText(String.valueOf(Context.getEvent().getPlanta_b()));
        vip.setText(String.valueOf(Context.getEvent().getVip()));
        vipM.setText(String.valueOf(Context.getEvent().getVip_mg()));
        container.setVgap(5);
        container.setHgap(5);
        container.setAlignment(Pos.CENTER);
        
        GridPane grid1R = printButtonsR(5,4,0,"'A','B','C','D','E','F','G'","R");   
        container.add(grid1R, 0, 0);        
        GridPane grid1L = printButtonsL(6,7,0,"'A','B','C','D','E','F','G'","L");   
        container.add(grid1L, 3, 0);
        GridPane grid2R = printButtonsCenterR(0,0,"'AA','BB','CC','DD','EE','FF','GG'","R");   
        container.add(grid2R, 1, 0);
        GridPane grid2L = printButtonsCenterL(10,10,"'AA','BB','CC','DD','EE','FF','GG'","L");   
        container.add(grid2L, 2, 0);
        GridPane grid3R = printButtonsCenterR(0,0,"'H','I','J','K','L','M','N','O'","R");   
        container.add(grid3R, 0, 1);
        GridPane grid3L = printButtonsCenterL(10,10,"'H','I','J','K','L','M','N','O'","L");   
        container.add(grid3L, 3, 1);
        GridPane grid4R = printButtonsCenterR(0,0,"'HH','II','JJ','KK','LL','MM','NN'","R");   
        container.add(grid4R, 1, 1);
        GridPane grid4L = printButtonsCenterL(10,10,"'HH','II','JJ','KK','LL','MM','NN'","L");   
        container.add(grid4L, 2, 1);
        vbox.getChildren().add(container);
        
        
       
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        
        initialGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        initialGrid.setMargin(scrollPane, new Insets(90, 0, 0, 0));
        initialGrid.add(scrollPane, 0, 0);
        this.startTimer();
        
    }    
    
     private GridPane printButtonsR(int columnIndex, int tmpColumnIndex, int rowIndex, String columns, String lado){
        ArrayList<models.Asientos> list = _asientos.ListAsientos(columns, lado);
        todosList.addAll(list);
        GridPane newGrid = new GridPane();
        for (models.Asientos asiento : list) {
           String text = asiento.getFile()+asiento.getColumn()+asiento.getLado();
           Button btn = new Button(text);
           btn.setId(text+"|"+asiento.getId());
           btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
           btn.setOnAction(event -> {                
               this.eventAsiento(text, asiento);
            });
           Background bg = asiento.isActive() ? colorAsiento(asiento.getIdSection()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
           btn.setBackground(bg);
           btn.setTextFill(Color.WHITE);
           btn.setWrapText(false);
           newGrid.add(btn, columnIndex, rowIndex);
           columnIndex++;
            if (columnIndex == 11) {                
                columnIndex = tmpColumnIndex != 0 ? tmpColumnIndex-- : tmpColumnIndex;
                rowIndex++;
            }            
        }        
        return newGrid;
    }
     
     private GridPane printButtonsL(int columnIndex, int tmpColumnIndex, int rowIndex, String columns, String lado){
        ArrayList<models.Asientos> list = _asientos.ListAsientos(columns, lado);
        todosList.addAll(list);
        GridPane newGrid = new GridPane();
        for (models.Asientos asiento : list) {
          String text = asiento.getFile()+asiento.getColumn()+asiento.getLado();
           Button btn = new Button(text);
           btn.setId(text+"|"+asiento.getId());
           btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
           btn.setOnAction(event -> {
                this.eventAsiento(text, asiento);
           });
           Background bg = asiento.isActive() ? colorAsiento(asiento.getIdSection()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
           btn.setBackground(bg);
           btn.setTextFill(Color.WHITE);
           newGrid.add(btn, columnIndex, rowIndex);
           columnIndex--;
            if (columnIndex == 0) {    
                columnIndex = tmpColumnIndex != 11 ? tmpColumnIndex++ : tmpColumnIndex;
                rowIndex++;
            }            
        }
        return newGrid;
     }
     
     private GridPane printButtonsCenterR(int columnIndex, int rowIndex, String columns, String lado){
        ArrayList<models.Asientos> list = _asientos.ListAsientos(columns, lado);
        todosList.addAll(list);
        GridPane newGrid = new GridPane();
        for (models.Asientos asiento : list) {
           String text = asiento.getFile()+asiento.getColumn()+asiento.getLado();
           Button btn = new Button(text);
           btn.setId(text+"|"+asiento.getId());
           btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
           btn.setOnAction(event -> {
               this.eventAsiento(text, asiento);
           });
           Background bg = asiento.isActive() ? colorAsiento(asiento.getIdSection()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
           btn.setBackground(bg);
           btn.setTextFill(Color.WHITE);
           newGrid.add(btn, columnIndex, rowIndex);
           columnIndex++;
            if (columnIndex == 10) {    
                columnIndex = 0;
                rowIndex++;
            }            
        }
        return newGrid;
     }
     
     private GridPane printButtonsCenterL(int columnIndex, int rowIndex, String columns, String lado){
        ArrayList<models.Asientos> list = _asientos.ListAsientos(columns, lado);
        todosList.addAll(list);
        GridPane newGrid = new GridPane();
        for (models.Asientos asiento : list) {
           String text = asiento.getFile()+asiento.getColumn()+asiento.getLado();
           Button btn = new Button(text);
           btn.setId(text+"|"+asiento.getId());
           btn.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
           btn.setOnAction(event -> {
                this.eventAsiento(text, asiento);
           });
           Background bg = asiento.isActive() ? colorAsiento(asiento.getIdSection()) : new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null));
           btn.setBackground(bg);
           btn.setTextFill(Color.WHITE);
           newGrid.add(btn, columnIndex, rowIndex);
           columnIndex--;
            if (columnIndex == 0) {    
                columnIndex = 10;
                rowIndex++;
            }            
        }
        return newGrid;
     }
     
     private StringBuilder addSelection(String asiento){
         Params<Integer> param = Location.getParams();
         StringBuilder asientos = new StringBuilder();
         if (seleccionados.contains(asiento)) {
            // Eliminar el elemento            
            Button targetButton = (Button) container.lookup("#"+asiento);
//            models.Asientos filtro = todosList.stream().filter(x -> x.getId() == Integer.parseInt(asiento.split("\\|")[1])).toList().get(0);
//            targetButton.setBackground(this.colorAsiento(filtro.getIdSection()));
            targetButton.setTextFill(Color.WHITE);
            seleccionados.remove(asiento);
        }else if (seleccionados.size() < param.getDato()) {
              seleccionados.add(asiento);
              Button targetButton = (Button) container.lookup("#"+asiento);
              targetButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
              targetButton.setTextFill(Color.BLACK);
        }else{
             String ultimoDato = seleccionados.get(0);
             Button targetButton = (Button) container.lookup("#"+ultimoDato);
//             models.Asientos filtro = todosList.stream().filter(x -> x.getId() == Integer.parseInt(ultimoDato.split("\\|")[1])).toList().get(0);
//             targetButton.setBackground(this.colorAsiento(filtro.getIdSection()));
             targetButton.setTextFill(Color.WHITE);
             seleccionados.remove(0);
             seleccionados.add(asiento);
             targetButton = (Button) container.lookup("#"+asiento);
             targetButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null))); 
             targetButton.setTextFill(Color.BLACK);
        }
        
        int filas = seleccionados.size();
        int columnas = 2;
        String[][] matriz = new String[filas][columnas];

        // Asignar los valores a la matriz
        for (int i = 0; i < filas; i++) {
            String[] dato = seleccionados.get(i).split("\\|");
            matriz[i][0] = dato[0];
            matriz[i][1] = dato[1];
        }
        
        ArrayList<String> ids = new ArrayList<>();
        
        for (int i = 0; i < filas; i++) {
            String nombre = matriz[i][0];
            String id = matriz[i][1];
            asientos.append(" ").append(nombre).append(" ");
            ids.add(id);            
        }
        
        idSeleccion = ids;
        
        return asientos;                 
     }
     
     private Background colorAsiento(int idSeccion){
         Background model = null;
         switch (idSeccion) {
             case 1:
                 model = new Background(new BackgroundFill(Color.RED, new CornerRadii(10), null));
                 break;
             case 2:
                  model = new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(10), null));
                 break;
             case 3:
                 model = new Background(new BackgroundFill(Color.GREEN, new CornerRadii(10), null));
                 break;
             case 4:
                 model = new Background(new BackgroundFill(Color.BLUE, new CornerRadii(10), null));
                 break;
             default:
                  model = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null));
         }
         
         return model;
     }
     
     private void eventAsiento(String text, models.Asientos asiento){
          if (!asiento.isActive()) {
                Validations.AlertMessage("Asiento En Mantenimiento", Alert.AlertType.WARNING, "Asiento Desactivado!");
                return;
           }

           StringBuilder asientos = addSelection(text+"|"+asiento.getId());  
           Params<Integer> params = Location.getParams();
           viewAsientos.setText(String.format("%s Asientos seleccionados de %s : %s",seleccionados.size(),params.getDato(),asientos.toString()));
     }
     
     private void startTimer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            DURATION_IN_SECONDS--;
            updateTimerLabel();
            
            if (DURATION_IN_SECONDS <= 0) {
                timeline.stop();
                Params<Events> param = new Params<>();
                param.setDato(Context.getEvent());
                App.view("EventDetail", param);
                // Aquí puedes agregar la lógica que deseas ejecutar después de que el temporizador llegue a cero.
            }
        });
        
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
     
     private void updateTimerLabel() {
        int minutes = DURATION_IN_SECONDS / 60;
        int seconds = DURATION_IN_SECONDS % 60;
        
        String minutesString = String.format("%02d", minutes);
        String secondsString = String.format("%02d", seconds);
        
        timerLabel.setText(minutesString + ":" + secondsString);
    }  
    
}
