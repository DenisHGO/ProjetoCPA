package application;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class ApresentacaoController extends AnchorPane{
	
	@FXML
	private Button idNext;
	@FXML
	private Button idBack;
	@FXML
	private Button idTelaFull;	
	@FXML
	private FlowPane idAreaExibicao;
	@FXML
	private FlowPane idletramusicasApresenta;
	@FXML
	private ScrollPane idletramusicasApresentaScroll;
	
	
	ArrayList<String> musica = new ArrayList<>();
	
	Stage stageLocal = new Stage();
	
	List<Label> children = new ArrayList<>();
	
	public void setStageAndSetupListeners(Stage stage) {
		stageLocal = stage;
		
	}
	
	
	public void apresentarFullScreen(){
		
		if(idAreaExibicao.getChildren().isEmpty()){			
		
		/*Text text1 =  new Text();				
		text1.setText("Welcome to Tutorialspoint ");
	    text1.setFont(new Font(80));
	    
	    Label lab = new Label();	    
	    lab.setText("Cantai, um novo c�ntico ao Senhor Celebrai, uma grande festa de louvor! Cantai, um novo c�ntico ao Senhor Celebrai, uma grande festa de louvor!");	    	    
	    lab.getStyleClass().add("label_text_musica");
	    lab.setWrapText(true);
	    lab.setTextAlignment(TextAlignment.JUSTIFY);
	    lab.setMaxWidth(idAreaExibicao.getWidth());*/	   
        /*textLabel.setWrapText(true);
        textLabel.setPrefWidth(700);
        textLabel.getStyleClass().add("label_text_musica");
	    */   
		//escondendo elementos quando fullScreen	
	    idNext.setVisible(false);
	    idBack.setVisible(false);
	    idTelaFull.setVisible(false);
	    idletramusicasApresentaScroll.setVisible(false);
	    
	    idAreaExibicao.setHgap(5);
	    idAreaExibicao.setAlignment(Pos.CENTER);
	    //idAreaExibicao.setPadding(new Insets(0,0,0,0));
	    //idAreaExibicao.setPrefWrapLength(5);
	    
	    idAreaExibicao.prefWidthProperty().bind(stageLocal.widthProperty());
	    idAreaExibicao.prefHeightProperty().bind(stageLocal.heightProperty());
	    
	    
	    idAreaExibicao.setRowValignment(VPos.TOP);
	    //idAreaExibicao.getChildren().add();
		}else{
			
		}
	    stageLocal.setFullScreen(true);		
		
	}
	
	public void habilitarBotoesApresentacao(){
		idNext.setVisible(true);
	    idBack.setVisible(true);
	    idTelaFull.setVisible(true);
	    idletramusicasApresentaScroll.setVisible(true);
	    
	}
	
	/*public void popularMusica(ArrayList<String> musicaApresentada){
		musica.clear();
		musica.addAll(musicaApresentada);
	}*/
	
	public void construirColunaEstrofes(ArrayList<String> musicaConstruir){
		String stringFull = "";
		//JFXTextArea txLogin = new JFXTextArea();
		//Label lab = new Label();
		
				
		idletramusicasApresenta.getChildren().clear();
		for (String line : musicaConstruir)
			if (line != null && line.isEmpty()) {
								
				idletramusicasApresenta.getChildren().add(new JFXTextArea(stringFull));				
								
				stringFull = "";

			} else if (!stringFull.isEmpty() && line != null) {
				stringFull = stringFull + "\n" + line;
			} else {
				stringFull = line;
			}		
		idletramusicasApresenta.getChildren().add(new JFXTextArea(stringFull));
		idletramusicasApresenta.getStyleClass().add("flowArea");
		
		
		for(int i=0; i<idletramusicasApresenta.getChildren().size(); i++){			
			idletramusicasApresenta.getChildren().get(i).getStyleClass().add("flowAreaEstrofe");
		}
		
		musicaConstruir.clear();
	}
	


	public void shutdown() {
		System.out.println("Stop");
		idAreaExibicao.getChildren().clear();        
	}
				
	
}
