package application;

import java.awt.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FMXLInicialController implements Initializable {

	@FXML
	public Button idmusicas;

	@FXML
	private ListView<String> idlistamusicas;

	ObservableList listMusicas = FXCollections.observableArrayList();
	

	@FXML
	private VBox idletramusicas;

	ArrayList filesNames = new ArrayList();
	ArrayList textoMusicas = new ArrayList();

	public void acaoBotaoMusicas(ActionEvent event) {

		// File arquivo = new File("C:\\Users\\Denis\\Pictures");
		File arquivo = new File(
				"C:\\Users\\denis.h.oliveira\\OneDrive - Accenture\\Documents\\ProjetoCPA\\ApresentCPA\\src\\musicas");
		File[] files = arquivo.listFiles();
		filesNames.clear();

		/*codigo para ordenacao da lista de arquivos comentado, pois nao foi necessario.
		 * Arrays.sort (files, new NumericFileNameComparator()); for (File f :
		 * files) { System.out.println (f); }
		 */

		// pegando todos nomes das musicas
		for (int i = 0; i < files.length; i++) {
			File arquivos = files[i];
			filesNames.add(arquivos.getName());
		}

		// ObservableList<String> seasonList =
		// FXCollections.<String>observableArrayList(filesNames);
		listMusicas.removeAll(listMusicas);
		listMusicas = FXCollections.<String> observableArrayList(filesNames);

		// seasons.setItems(listMusicas);
		idlistamusicas.setOrientation(Orientation.VERTICAL);
		idlistamusicas.setItems(listMusicas);

	}

	public void acaoExibirMusicas(ActionEvent event) throws IOException {

		String musicaSelecionada = idlistamusicas.getSelectionModel().getSelectedItem();
		
		BufferedReader buffRead = new BufferedReader(new FileReader(
				"C:\\Users\\denis.h.oliveira\\OneDrive - Accenture\\Documents\\ProjetoCPA\\ApresentCPA\\src\\musicas\\"+musicaSelecionada));
		String linha = "";
		idletramusicas.getChildren().clear();
		int i = 0;
		while (true) {
			if (linha != null) {
				// list.add(linha);
				// System.out.println(linha);
			} else
				break;
			linha = buffRead.readLine();
			Text text1 = new Text(linha);
			textoMusicas.add(text1);

			// || textoMusicas.size() == 1
			if (text1.getText().equals("")) {
				idletramusicas.getChildren().add(new Button("test1"));
			} else {
				idletramusicas.getChildren().add(new Button(text1.toString()));
			}

			// idletramusicas.getChildren().add(text1);
		}
		buffRead.close();
	}
	
	public String buscarArquivo(){
		
		
		
		
		return null;	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
