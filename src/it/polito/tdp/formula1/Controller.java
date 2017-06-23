package it.polito.tdp.formula1;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Drivers;
import it.polito.tdp.formula1.model.Model;
import it.polito.tdp.formula1.model.Race;
import it.polito.tdp.formula1.model.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Circuit> boxCircuit;

    @FXML
    private ComboBox<Drivers> boxDriver;

    @FXML
    private ComboBox<Season> boxSeason;

    @FXML
    private TextArea txtResult;

	private Model model;
	
	@FXML
	void doCircuiti(ActionEvent event) {
		Season s = boxSeason.getValue() ;
		this.boxCircuit.getItems().clear();
		this.boxCircuit.getItems().addAll(model.getCircuiti(s));
		
	}

    @FXML
    void doFantaGara(ActionEvent event) {
//    	if(s == null)
//			txtResult.setText("Selezionare una stagione");
//		else{
//			
    }

    @FXML
    void doInfoGara(ActionEvent event) {
    	Circuit c = boxCircuit.getValue() ;
    	Season s = boxSeason.getValue() ;
    	if(c== null || s==null)
    		txtResult.setText("Selezionare un circuito o  un anno" );
    	else{
    		Race r = model.getInfo(c, s);
    		txtResult.clear();
    		txtResult.setText("INFO GARA: \n"+r+" \n a cui hanno partecipato: "+ r.getPartecipanti().toString());
    	}

    }

    @FXML
    void initialize() {
        assert boxCircuit != null : "fx:id=\"boxCircuit\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxDriver != null : "fx:id=\"boxDriver\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Formula1.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		boxSeason.getItems().addAll(model.getAnni()) ;
		
		
	}
}
