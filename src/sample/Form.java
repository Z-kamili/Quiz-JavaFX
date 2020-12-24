package sample;

import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javazoom.jl.decoder.JavaLayerException;
import javax.swing.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Form implements Initializable {
    private boolean musiqueState = true;
    private musiquePlayer player;
    //
    private int attemptsCount = 2;
	int cpt = 0;
	int cptN = 0;
  static int niv;
    private int lvlCount = 1;
    private ArrayList<Quiz> listeQuiz = new ArrayList<Quiz>();
    private ArrayList<ToggleGroup> listeButtonGroup;
    ArrayList<Player_QUIZ> listePlayer_QUIZ = new ArrayList<Player_QUIZ>();
    private Timer t;
    Players player1;
    //@FXML Button btnTest ;
    @FXML
    SVGPath btnMusique;
    @FXML
    Button btnDebut;
    @FXML
    Label lbNbt, lbNiv, lbComp, lbNom;
    @FXML
    VBox contentPane;
     Controller crl = new Controller();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = new musiquePlayer("quiz-show.mp3");
        player1 = new Players(crl.name, crl.prenom, Integer.parseInt(crl.age));
        lbNom.setText(String.format("Nom : %s Prenom : %s", crl.name,crl.prenom));
        t = new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run() {
                if (player1.getDuration() > 0) {
                    player1.setDuration(player1.getDuration() - 1);
                    Platform.runLater(() -> lbComp.setText(LocalTime.MIN.plusSeconds(player1.getDuration()).toString()));
                } else {
                    t.cancel();
                    JOptionPane.showMessageDialog(null, "GAME OVER");
                    System.exit(0);
                }
            }
        },1000,1000);
        niveau1();
    }

    //
    //on page load
    /*public Form() {
        //play music
        player = new musiquePlayer("quiz-show.mp3");
        //niveau1();
        //System.out.println("slm");
    }*/

    //Validate the form
    @FXML
    public void validateForm() {
    //    boolean valide = true;
        //Code de validation ici
        //
        //traitment apres validation
  /*      if (valide) {
            //next level
            niveau2();
        } else {
            if (attemptsCount > 0) {
                attemptsCount--;
                lbNbt.setText(String.format("Tentatives : %d", attemptsCount));
            } else {
                final ImageIcon icon = new ImageIcon("lose.gif");
                //JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH", JOptionPane.INFORMATION_MESSAGE, icon);
                //JOptionPane.showMessageDialog(null, "GAME OVER\nNombre de tentatives a atteint 0.", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, icon);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GAME OVER");
                alert.setHeaderText("Vous avez échoué le quiz");
                alert.setContentText("Nombre de tentatives a atteint 0!");
                alert.showAndWait();
            }
        }*/
    	ActionDB(niv);
    	
    }

    //on_click de button de volume
    @FXML
    public void musiqueController() {
        if (musiqueState) {
            btnMusique.setContent("M9.383 3.076A1 1 0 0110 4v12a1 1 0 01-1.707.707L4.586 13H2a1 1 0 01-1-1V8a1 1 0 011-1h2.586l3.707-3.707a1 1 0 011.09-.217zM12.293 7.293a1 1 0 011.414 0L15 8.586l1.293-1.293a1 1 0 111.414 1.414L16.414 10l1.293 1.293a1 1 0 01-1.414 1.414L15 11.414l-1.293 1.293a1 1 0 01-1.414-1.414L13.586 10l-1.293-1.293a1 1 0 010-1.414z");
        } else {
            btnMusique.setContent("M9.383 3.076A1 1 0 0110 4v12a1 1 0 01-1.707.707L4.586 13H2a1 1 0 01-1-1V8a1 1 0 011-1h2.586l3.707-3.707a1 1 0 011.09-.217zM14.657 2.929a1 1 0 011.414 0A9.972 9.972 0 0119 10a9.972 9.972 0 01-2.929 7.071 1 1 0 01-1.414-1.414A7.971 7.971 0 0017 10c0-2.21-.894-4.208-2.343-5.657a1 1 0 010-1.414zm-2.829 2.828a1 1 0 011.415 0A5.983 5.983 0 0115 10a5.984 5.984 0 01-1.757 4.243 1 1 0 01-1.415-1.415A3.984 3.984 0 0013 10a3.983 3.983 0 00-1.172-2.828 1 1 0 010-1.415z");
        }
        musiqueState = !musiqueState;
        player.playState(musiqueState);
    }
    //
    private void niveau1() {
        Quiz quiz1 = new Quiz("JAVA est  un langage", "Compilé et interpreté", "Compilé", "Interprété", "Compilé et interpreté");
        Quiz quiz2 = new Quiz("Toutes les classes héritent de la classe", "Object", "Main", "Object", "AWT");
        Quiz quiz3 = new Quiz("Par convention une classe", "commence par une majuscule", "est en minuscule", "commence par une majuscule", "est en majuscules");
        Quiz quiz4 = new Quiz("Est-ce que on peut avoir plusieurs constructeurs pour la même classe", "oui", "oui", "non");
        Quiz quiz5 = new Quiz("Dans la ligne \"public class A implements B\", B est:", "Interfacce", "Interfacce", "Classe parent", "Package");

        listeQuiz.add(quiz1);
        listeQuiz.add(quiz2);
        listeQuiz.add(quiz3);
        listeQuiz.add(quiz4);
        listeQuiz.add(quiz5);

        remplirePanelNiveau(1);
        niv = 1;
    }

    private void niveau2() {
    	lbNbt.setText(String.format("Tentative : %d", 2));
       System.out.println("niveau 2");
        Quiz quiz1 = new Quiz("Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecode. Quelle est l’extension du programme en bytecode ?", ".Class", "aucun des choix", ".JAVA", ".Class");
        Quiz quiz2 = new Quiz("Class Test{Public Test () {System.out.println(”Bonjour”);}public Test (int i) {this(); System.out.println(”Nous sommes en ”+i+”!”);}; }qu’affichera l’instruction suivante? Test t1=new Test (2020);", "Bonjour Nous sommes en 2020 !", "aucun des choix", "Bonjour Nous sommes en 2020 !", "Nous sommes en 2020 !");
        Quiz quiz3 = new Quiz("Voici un constructeur de la classe Employee, y-a-t'il un problème Public void Employe(String n){Nom=n;}", "vrai", "vrai", "faux");
        Quiz quiz4 = new Quiz("Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé", "final", "aucun des choix", "final","const");
        Quiz quiz5 = new Quiz("Dans une classe, on accède à ses variables grâce au mot clé", "this", "aucun des choix", "this", "super");

        listeQuiz.add(quiz1);
        listeQuiz.add(quiz2);
        listeQuiz.add(quiz3);
        listeQuiz.add(quiz4);
        listeQuiz.add(quiz5);

        remplirePanelNiveau(2);
        niv = 2;
    }

    private void niveau3() {
    	lbNbt.setText(String.format("Tentative : %d", 2));
        Quiz quiz1 = new Quiz("calculerSalaire(int) calculerSalaire(int, double)La méthode calculerSalaire est:", "surchargée", "aucun des choix", "surchargée", "redéfinie");
        Quiz quiz2 = new Quiz("Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite.", "vrai", "vrai", "faux");
        Quiz quiz3 = new Quiz("Est-ce qu’une classe peut implémenter plusieurs interfaces?", "vrai", "vrai", "faux");
        Quiz quiz4 = new Quiz("La déclaration d'une méthode suivante :public void traitement() throws IOExceptionprécise que la méthode propage une exception contrôlée", "vrai", "vrai", "faux");
        Quiz quiz5 = new Quiz("class Test{public static void main (String[] args) {try {int a =10;System.out.println (\"a = \" + a );int b = 0 / a;System.out.println (\"b = \" + b);}catch(ArithmeticException e){System.out.println (\"diviser par 0!\"); }finally{System.out.println(\"je suis à l’intérieur de finally\");}}}", "a=10 b=0 Je suis à l’intérieur de finally", "aucun des choix", "a=10 b=0 Je suis à l’intérieur de finally", "a=10 b=0 diviser par 0! je suis à l’intérieur de finally");

        listeQuiz.add(quiz1);
        listeQuiz.add(quiz2);
        listeQuiz.add(quiz3);
        listeQuiz.add(quiz4);
        listeQuiz.add(quiz5);

        remplirePanelNiveau(3);
        niv = 3;
        System.out.println("remplire");
    }

    //
    private void remplirePanelNiveau(int niveau) {
        lbNiv.setText(String.format("Niveau : %d", niveau));
        int start, end;
        if (niveau == 1) {
            start = 0;
            end = 5;
        } else if (niveau == 2) {
            start = 5;
            end = 10;
        } else {
            start = 10;
            end = 15;
        }
        //remove the questions
        contentPane.getChildren().clear();
        //
        String idLabel = "qu_", idRadio = "rd_qu_", idGroup = "question_";
        listeButtonGroup = new ArrayList<ToggleGroup>();
        //
        for (int i = start; i < end; i++) {
            VBox container = new VBox();
            container.setSpacing(10);
            container.setMinHeight(60);
            //
            Text lb = new Text(listeQuiz.get(i).getQuestion());
            lb.setWrappingWidth(750);
            //
            contentPane.getChildren().add(lb);
            lb.setId(String.format("%s%d", idLabel, i + 1));
            //
            HBox answers = new HBox();
            answers.setSpacing(40);
            //TEST IF THERE IS 2 OR 3 ANSWERS
            int answersCount = 2;
            if (listeQuiz.get(i).getChoice_three() != null)
                answersCount = 3;
            //
            ToggleGroup radiosContainer = new ToggleGroup();
            for (int j = 0; j < answersCount; j++) {
                RadioButton choice = new RadioButton();
                choice.setId(String.format("%s%d_%d", idRadio, i, j));
                choice.setText(listeQuiz.get(i).getChoice(j));
                choice.setToggleGroup(radiosContainer);
                //
                answers.getChildren().add(choice);
            }
            listeButtonGroup.add(radiosContainer);
            //
            if (i != start)
                container.getChildren().add(new Separator(Orientation.HORIZONTAL));
            container.getChildren().addAll(lb, answers);
            //
            contentPane.getChildren().add(container);
        }
    }
    
	public  void getreponses(int niveau)
	{
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		int btngroupIndex = 0;
		//System.out.println(listeButtonGroup.size());
		for(int i = start;i<end;i++){
			boolean choice; 
			RadioButton chk = (RadioButton)listeButtonGroup.get(btngroupIndex).getSelectedToggle();
			if(listeQuiz.get(i).getReponse() == chk.getText().toString()){
				choice=true;
			}
			else {
				choice=false;
			}
			System.out.println(chk.getText() + "responses " + listeQuiz.get(i).getReponse());
			
			Player_QUIZ player_QUIZ = new Player_QUIZ(player1.getId_Player(), listeQuiz.get(i).getId_quiz(), chk.getText(), choice);
			listePlayer_QUIZ.add(player_QUIZ);
			//
			btngroupIndex++;
		}
		
		
	}

	public  void afficheCorrection(int niveau) {
		if(cpt==0) {
			
			cpt=1;
			//btn_valider.setText("Suivant");
			//correction(niveau);
			//lapel_score.setText("score : " + calculeScore(niveau));
		}
		else {
			cpt=0;
			if(niveau==1) {
				
				if(calculeScore(1) >= 40) {
					niv = 2;
					niveau2();
					System.out.println(calculeScore(1));
				}
				else {	
					/*try {
						//StopMusic();
					}catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
				//playerMusic("lose.mp3",100);
				String invisibleChar = "\u200e";
				final ImageIcon icon = new ImageIcon("lose.gif");
		        JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH", JOptionPane.INFORMATION_MESSAGE, icon);
		        System.exit(0);
					}

					 
				}
			else if(niveau==2) {
				if(calculeScore(2) >= 60) {
					niv = 3;
					niveau3();
					
					
				}
				else {
					/* try {
							StopMusic();
						} catch (JavaLayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					playerMusic("lose.mp3",100);*/
					 String invisibleChar= "\u200e";
						final ImageIcon icon = new ImageIcon("lose.gif");
                        JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH ", JOptionPane.INFORMATION_MESSAGE, icon);
					
                    System.exit(0);
				}
			}
			else {
				if(calculeScore(3) >= 80) {

					//affichage du resultat
				/*	t.stop();
					try {
						StopMusic();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					playerMusic("win.mp3",100);*/
                  
                   String invisibleChar= "\u200e";
					final ImageIcon icon = new ImageIcon("source.gif");
                    JOptionPane.showMessageDialog(null,invisibleChar, "YOU WON ! ", JOptionPane.INFORMATION_MESSAGE, icon);
					/* try {
							StopMusic();
						} catch (JavaLayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
					  System.exit(0);
					
				}
				else {
					/* try {
							StopMusic();
						} catch (JavaLayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					playerMusic("lose.mp3",100);*/
					 String invisibleChar= "\u200e";
						final ImageIcon icon = new ImageIcon("lose.gif");
                        JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH ", JOptionPane.INFORMATION_MESSAGE, icon);
					
                    System.exit(0);
				}
				
			}
	}
}
	public  void Tentation(int niveau){
		
		if(cpt == 0) {
			getreponses(niveau);
			if(cptN == 0){
				if((niveau == 1 && calculeScore(1) >= 40 ) || (niveau == 2 && calculeScore(2) >= 60 ) || (niveau == 3 && calculeScore(3) >= 80 )) {
					
					afficheCorrection(niveau);
					//System.out.println("Tentation " + niv);
					
				}else {
					cptN = 1;
					lbNbt.setText(String.format("Tentative : %d", cptN));
					for (int i = 0; i < 5; i++) {
						
						listePlayer_QUIZ.remove(listePlayer_QUIZ.size() - 1);
						
					}
					
					JOptionPane.showMessageDialog(null, "Error tu peut reprendre");
					
				}
				
				
			}else {
				cptN = 0;
				afficheCorrection(niveau);
				//JOptionPane.showMessageDialog(null, "hello");
			}
			
		}else {
			
			afficheCorrection(niveau);
			//JOptionPane.showMessageDialog(null, "hello2");
			
		}
		
		
	}
	
	public  int calculeScore(int niveau)
	{
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		int score = 0;
		for(int i = start;i<end;i++) {
			if(listePlayer_QUIZ.get(i).isGoodchoice()) {
				score += 20;
			}
		}
		
		System.out.println(score);
		return score;
	}
	public void ActionDB(int niveau) {
		System.out.println(niveau);
		if(checkReponseAllQuestion(niveau)){
			Tentation(niveau);
		}
		else {
			JOptionPane.showMessageDialog(null, "Merci de répondre à toutes les questions");
		}
		
	}
	
	public  boolean checkReponseAllQuestion(int niveau){
		int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		boolean ret=true;
		for(int i = 0;i<5;i++){
		//	RadioButton chk = (RadioButton)listeButtonGroup.get(i).getSelectedToggle();
			if(listeButtonGroup.get(i).getSelectedToggle() == null ) {
				ret = false;
			}
		}
		return ret;
	}

}


