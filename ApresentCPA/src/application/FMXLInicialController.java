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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FMXLInicialController implements Initializable {

	@FXML
	public Button idmusicas;
	
	@FXML
	public Button	idButtonPesquisar;

	@FXML
	public Button idcarregamusica;

	@FXML
	public Button idbuttonApresentar;

	@FXML
	private ListView<String> idlistamusicas;

	ObservableList listMusicas = FXCollections.observableArrayList();

	String helper = System.getProperty("user.dir");

	@FXML
	private FlowPane idletramusicas;

	@FXML
	private ScrollPane idscroll;
	
	@FXML
	private TextField idsearch;
	
	@FXML
	private Label idtituloMusica;

	ArrayList filesNames = new ArrayList();
	ArrayList<String> textoMusicas = new ArrayList();
	String musicaSelecionada = "";
	String musicaSelecionadaApresenta = "";
	

	public void acaoBotaoMusicas(ActionEvent event) {

		File arquivo = new File(helper + "/src/musicas");

		File[] files = arquivo.listFiles();
		filesNames.clear();

		// pegando todos nomes das musicas
		for (int i = 0; i < files.length; i++) {
			File arquivos = files[i];
			filesNames.add(arquivos.getName());
		}

		listMusicas.removeAll(listMusicas);
		listMusicas = FXCollections.<String>observableArrayList(filesNames);

		idlistamusicas.setOrientation(Orientation.VERTICAL);
		idlistamusicas.setItems(listMusicas);
		
		//controlar liberacao da busca pela musica e capturar eventos dentro.
		idsearch.setDisable(false);
		idsearch.setOnMouseClicked(e -> {filtrarMusicas(event);
		});
		

	}
	
	public void filtrarMusicas(ActionEvent event){
		
		File arquivo = new File(helper + "/src/musicas");

		File[] files = arquivo.listFiles();
		//filesNames.clear();
		
		 ObservableList<String> data = FXCollections.observableArrayList();
		
		 for (int i = 0; i < files.length; i++) {
				File arquivos = files[i];
				//filesNames.add(arquivos.getName());
				data.add(arquivos.getName());
			}
		 
		 FilteredList<String> filteredData = new FilteredList<>(data, s -> true);
		 
		 //TextField filterInput = new TextField();
		 idsearch.textProperty().addListener(obs->{
		        String filter = idsearch.getText(); 
		        if(filter == null || filter.length() == 0) {
		            filteredData.setPredicate(s -> true);
		        }
		        else {
		            filteredData.setPredicate(s -> s.toUpperCase().contains(filter.toUpperCase()));
		        }
		    });		    
		
			 idlistamusicas.setItems(filteredData); 
		 
		    
		
	}

	public void acaoExibirMusicas(ActionEvent event) throws IOException {

		TextArea txLogin = new TextArea();
		String stringFull = "";
		

		if (!(idlistamusicas.getSelectionModel().getSelectedItem() == null)) {
			musicaSelecionada = idlistamusicas.getSelectionModel().getSelectedItem();
			textoMusicas = lerMusica(musicaSelecionada);
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
			dialogoErro.setTitle("Error");
			dialogoErro.setHeaderText("OPS..");
			dialogoErro.setContentText("Necessario escolher uma musica para ser exibida!");
			dialogoErro.showAndWait();
		}
		
		idtituloMusica.setText(musicaSelecionada);
		
		idletramusicas.getChildren().clear();
		for (String line : textoMusicas)
			if (line != null && line.isEmpty()) {

				txLogin.setText(stringFull);

				idletramusicas.getChildren().add(new TextArea(String.valueOf(stringFull)));

				stringFull = "";

			} else if (!stringFull.isEmpty() && line != null) {
				stringFull = stringFull + "\n" + line;
			} else {
				stringFull = line;
			}
		idletramusicas.getChildren().add(new TextArea(String.valueOf(stringFull)));
		textoMusicas.clear();
		idbuttonApresentar.setDisable(false);
		
		
		
	}

	public ArrayList<String> lerMusica(String musicaSelecionada) throws IOException {

		String linha;
		FileReader reader = new FileReader(helper + "/src/musicas/" + musicaSelecionada);
		BufferedReader leitor = new BufferedReader(reader);

		while ((linha = leitor.readLine()) != null) {
			textoMusicas.add(linha);
		}

		leitor.close();
		return textoMusicas;

	}
	
	public void apresentarMusica(ActionEvent event){		
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FMXLApresentacao.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle(musicaSelecionada);			
			stage.setScene(new Scene(root1));
			
			ApresentacaoController controller = (ApresentacaoController)fxmlLoader.getController();
			controller.setStageAndSetupListeners(stage);
			
			//se a tela de apresentacao for fechada
			stage.setOnHidden(e -> controller.shutdown());
			
			//logica para ficar capturando os clicks na tela de apresentacao.
			stage.addEventFilter(KeyEvent.ANY, keyEvent -> {
		        System.out.println(keyEvent);
		        
		        if(keyEvent.getCode() == KeyCode.ESCAPE ){
		        	controller.habilitarBotoesApresentacao();
		        }
		    });
			
			
			String musicaSelecionadaApresent = idlistamusicas.getSelectionModel().getSelectedItem();
			ArrayList<String> textoMusicasApresent = lerMusica(musicaSelecionadaApresent);
			//controller.popularMusica(textoMusicasApresent);
			controller.construirColunaEstrofes(textoMusicasApresent);
			stage.show();
			 
			
		}catch(Exception e){
			System.out.println("Nao foi possivel carregar a Apresentacao.");
		}
		
	}	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
				
	}

}
