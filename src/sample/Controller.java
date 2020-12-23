package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Controller {
    @FXML
    Button btnStart;
    @FXML
    VBox container;
    @FXML Label lblerror;
    @FXML
    GridPane root;
    Players player1;
    @FXML   TextField txt_nom,txt_prenom,txt_age;
    static String name;
    static String prenom;
    static String age;
    //
    
    public Controller() {
		// TODO Auto-generated constructor stub
	}
    @FXML public void startQuiz(){
        //DIR HNA TRAITMENT DYLK
        //MN B3D N'AFFICHIW L'FORM
		this.name =  txt_nom.getText();
		this.prenom = txt_prenom.getText();
		this.age = txt_age.getText();
		if( !name.equals("") && !prenom.equals("") && !txt_age.equals("")) {
			
			nextScene();
			
		}else {
			
			lblerror.setText("Error");
			
			
		}
    }
    
    
    public Players AddPlayers() {
    	
    	 
    	
    	return player1;
    }
    
    
/*	public  void login() {
		

		
	/*	btn_debut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				player1 = new Players(txt_nom.getText(), txt_prenom.getText(), Integer.parseInt(txt_age.getText()));
				//viderPanelNiveau();
				t = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(player1.getDuration() > 0) {
							player1.setDuration(player1.getDuration()-1);
							lapelTime.setText(LocalTime.MIN.plusSeconds(player1.getDuration()).toString());
							frame.setTitle(NiveauActuel(listeQuiz));
						}
						else {
							t.stop();
							JOptionPane.showMessageDialog(null, "GAME OVER");
		                    System.exit(0);
						}
					}
				});
				java.util.Timer tt = new java.util.Timer(false);
		        tt.schedule(new TimerTask() {
		            @Override
		            public void run() {
		                t.start();
		            }
		        }, 0);
				niveau1();
				
				
			}
		});*/
	//}
    //FUNCTION kdir l'appele l fichier .fxml li fih la form mn b3d knchdo dik l form wkan7otoha fl fenetre li fiha 7na dba
    public void nextScene(){
        try {
            VBox content = FXMLLoader.load(getClass().getResource("form.fxml"));
            root.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
