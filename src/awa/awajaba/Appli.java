/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awa.awajaba;


import awa.entities.Repas;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author root
 */
public class Appli extends Application {
    
      private Callback<DatePicker, DateCell> getDayCellFactory() {
 
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
 
            @Override
            public DateCell call(final DatePicker date) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
 
                        LocalDate lendemain = LocalDate.now().plusDays(1);
                        if (empty||item.compareTo(lendemain)<0){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Label labelRepas = new Label("Programmer un repas :");
        
        Label labelDate = new Label("Date : ");
        DatePicker date = new DatePicker();
        date.setValue(LocalDate.now());
        date.setShowWeekNumbers(true);
  
        // Factory to create Cell of DatePicker
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        date.setDayCellFactory(dayCellFactory);
        
        
        Label labelTypeRepas= new Label("Type de Repas :");
        ToggleGroup TypeRepas = new ToggleGroup();
        RadioButton radioMidi = new RadioButton("Midi");
        radioMidi.setToggleGroup(TypeRepas);
        radioMidi.setUserData(false);
        radioMidi.setSelected(true);
        RadioButton radioSoir = new RadioButton("Soir");
        radioSoir.setUserData(true);
        radioSoir.setToggleGroup(TypeRepas);
        
        Label labelSpecialite= new Label("Spécialité :");
        ComboBox comboSpecialite = new ComboBox();
        comboSpecialite.getItems().addAll("Regional","Oriental","Asiatique","Africaine");
        comboSpecialite.setValue("Regional");
        
        CheckBox checkBoxBio= new CheckBox("Bio");
        CheckBox checkBoxVegan= new CheckBox("Vegan");
        
        Label labelparticipationFrais= new Label("Participation aux Frais :");
        TextField paf = new TextField();
        paf.setText("15");
        
        Label labelCouverts= new Label("Nombres de Couverts :");
        Spinner spinnerCouverts= new Spinner();
        SpinnerValueFactory.IntegerSpinnerValueFactory valeurCouverts = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200,3);
        spinnerCouverts.setValueFactory(valeurCouverts);

        Button Valider = new Button("Valider");
        Button Annuler = new Button("Annuler");
        Annuler.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                date.setDayCellFactory(dayCellFactory);
                comboSpecialite.setValue("Regional");
                paf.setText("15");
                radioMidi.setSelected(true);
                checkBoxVegan.setSelected(false);
                checkBoxBio.setSelected(false);
                spinnerCouverts.setValueFactory(valeurCouverts);
            }
        });
        Valider.setOnAction(new EventHandler<ActionEvent>() {
            
            
            @Override
            public void handle(ActionEvent event) {
                if(paf.toString().length()==0 || paf.getText().matches("[0-9]+")==false){
                    Alert dlgNok = new Alert (Alert.AlertType.ERROR);
                    dlgNok.setTitle("Erreur");
                    dlgNok.setHeaderText("La participation au frais doit etre exlclusivement un nombre");
                    dlgNok.showAndWait();   
                }
                else{
                    Repas unRepas = new Repas(date.getValue(),Boolean.parseBoolean(TypeRepas.getSelectedToggle().getUserData().toString()),comboSpecialite.getValue().toString(),valeurCouverts.getValue(),checkBoxBio.isSelected(),checkBoxVegan.isSelected(),Integer.parseInt(paf.getText()));
                    System.out.println(unRepas.toString());
                    Alert dlgNok = new Alert (Alert.AlertType.INFORMATION);

                    dlgNok.setTitle("Comfirmation");
                    dlgNok.setHeaderText("Vous avez bien evalué votre repas");
                    dlgNok.setContentText("Date : "+unRepas.getDate()+" Reservation pour le soir : "+unRepas.isSoir()+"\nSpécialité : "+unRepas.getSpecailite()+"\nNombres de Personnes : "+unRepas.getNbCouverts()+"\nBio : "+unRepas.isBio()+"\n Vegan :"+unRepas.isVegan()+"\nParticipation de "+unRepas.getPaf()+"€");
                    dlgNok.showAndWait();
                }
                
            }
                
               
        });
       
 
        GridPane.setHalignment(labelRepas, HPos.RIGHT);
        root.add(labelRepas, 0, 0,2,1);
        GridPane.setHalignment(labelDate, HPos.RIGHT);
        root.add(labelDate, 0, 1);
        root.add(date, 1, 1);
        
        GridPane.setHalignment(labelTypeRepas, HPos.RIGHT);
        root.add(labelTypeRepas, 0,2);
        root.add(radioMidi,1,2);
        root.add(radioSoir,2,2);
        
        GridPane.setHalignment(labelSpecialite, HPos.RIGHT);
        root.add(labelSpecialite, 0,3);
        root.add(comboSpecialite,1,3);
        
        root.add(checkBoxBio,1,4);
        root.add(checkBoxVegan,2,4);
        
        root.add(labelparticipationFrais,0,5);
        root.add(paf,1,5);
        
        root.add(labelCouverts,0,6);
        root.add(spinnerCouverts,1,6);
        
        
        GridPane.setHalignment(Valider, HPos.RIGHT);
        root.add(Valider, 4, 7);
        GridPane.setHalignment(Annuler, HPos.LEFT);
        root.add(Annuler, 0, 7);
        
        Scene scene = new Scene(root, 650, 350);
        primaryStage.setTitle("AWA_08");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
