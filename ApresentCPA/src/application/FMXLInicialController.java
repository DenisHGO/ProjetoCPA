package application;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class FMXLInicialController implements Initializable {

		
	@FXML
	public Button idmusicas;
	
	@FXML
	private ListView<String>  idlistamusicas;
	
	ArrayList filesNames = new ArrayList();
	
	public void acaoBotaoMusicas(ActionEvent event){
		
		//String filePath = "C:\\Users\Denis\Pictures";
		File arquivo = new File("C:\\Users\\Denis\\Pictures");
		File[] files = arquivo.listFiles();
				
		
		int i = 0;
		for (int j = files.length; i < j; i++) {
			File arquivos = files[i];
			filesNames.add(arquivos.getName());
			//System.out.println(arquivos.getName());
		}
		
		
		ObservableList<String> seasonList = FXCollections.<String>observableArrayList(filesNames);
		//ObservableList<String> seasonList = FXCollections.<String>observableArrayList("Spring", "Summer", "Fall", "Winter");
		ListView<String> seasons = new ListView<>(seasonList);
		seasons.setItems(seasonList);
		idlistamusicas.setOrientation(Orientation.VERTICAL);
		idlistamusicas.setItems(seasonList);
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
  

 
}