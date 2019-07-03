package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class FMXLInicialController implements Initializable {

		
	@FXML
	public Button idmusicas;
	
	@FXML
	private ListView<String>  idlistamusicas;
	
	@FXML
	private VBox idletramusicas;
 	
	ArrayList filesNames = new ArrayList();
	ArrayList textoMusicas = new ArrayList();
	
	public void acaoBotaoMusicas(ActionEvent event){
		
		//File arquivo = new File("C:\\Users\\Denis\\Pictures");
		File arquivo = new File("C:\\Users\\denis.h.oliveira\\OneDrive - Accenture\\Documents\\ProjetoCPA\\ApresentCPA\\src\\musicas");
		File[] files = arquivo.listFiles();
				
		
		int i = 0;
		for (int j = files.length; i < j; i++) {
			File arquivos = files[i];
			filesNames.add(arquivos.getName());
			
		}
		
		
		ObservableList<String> seasonList = FXCollections.<String>observableArrayList(filesNames);
		//ObservableList<String> seasonList = FXCollections.<String>observableArrayList("Spring", "Summer", "Fall", "Winter");
		ListView<String> seasons = new ListView<>(seasonList);
		seasons.setItems(seasonList);
		idlistamusicas.setOrientation(Orientation.VERTICAL);
		idlistamusicas.setItems(seasonList);
		
	}

	public void acaoExibirMusicas(ActionEvent event) throws IOException{	

		BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\denis.h.oliveira\\OneDrive - Accenture\\Documents\\ProjetoCPA\\ApresentCPA\\src\\musicas\\Eu sou Livre.txt"));
		String linha = "";
		idletramusicas.getChildren().clear();
		int i = 0;
		while (true) {
			if (linha != null) {

				//list.add(linha);
				System.out.println(linha);

			} else
				break;
			linha = buffRead.readLine();            
			Text text1 = new Text(linha);
			textoMusicas.add(text1);
		
			//|| textoMusicas.size() == 1
			if (text1.getText().equals("") ){
				idletramusicas.getChildren().add(new Button("test2")) ;
			}else {
				idletramusicas.getChildren().add(new Button("test1")) ;
		}
			
			//idletramusicas.getChildren().add(text1);
		}   
		buffRead.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
  

 
}
