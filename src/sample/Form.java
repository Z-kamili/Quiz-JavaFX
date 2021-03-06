package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = new musiquePlayer("quiz-show.mp3");
        player1 = new Players(Controller.name, Controller.prenom, Integer.parseInt(Controller.age));
        lbNom.setText(String.format("Nom : %s Prenom : %s", Controller.name, Controller.prenom));
        t = new Timer();
        t.schedule(new TimerTask() {
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
        }, 1000, 1000);
        //
        niveau1();
    }

    //Validate the form
    @FXML
    public void validateForm() {
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
        Quiz quiz1 = new Quiz("JAVA est  un langage", "CompilÃ© et interpretÃ©", "CompilÃ©", "InterprÃ©tÃ©", "CompilÃ© et interpretÃ©");
        Quiz quiz2 = new Quiz("Toutes les classes hÃ©ritent de la classe", "Object", "Main", "Object", "AWT");
        Quiz quiz3 = new Quiz("Par convention une classe", "commence par une majuscule", "est en minuscule", "commence par une majuscule", "est en majuscules");
        Quiz quiz4 = new Quiz("Est-ce que on peut avoir plusieurs constructeurs pour la mÃªme classe", "oui", "oui", "non");
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
        Quiz quiz1 = new Quiz("AprÃ¨s la compilation, un programme Ã©crit en JAVA, il se transforme en programme en bytecode. Quelle est lâ€™extension du programme en bytecode ?", ".Class", "aucun des choix", ".JAVA", ".Class");
        Quiz quiz2 = new Quiz("Class Test{Public Test () {System.out.println(â€�Bonjourâ€�);}public Test (int i) {this(); System.out.println(â€�Nous sommes en â€�+i+â€�!â€�);}; }quâ€™affichera lâ€™instruction suivante? Test t1=new Test (2020);", "Bonjour Nous sommes en 2020 !", "aucun des choix", "Bonjour Nous sommes en 2020 !", "Nous sommes en 2020 !");
        Quiz quiz3 = new Quiz("Voici un constructeur de la classe Employee, y-a-t'il un problÃ¨me Public void Employe(String n){Nom=n;}", "vrai", "vrai", "faux");
        Quiz quiz4 = new Quiz("Pour spÃ©cifier que la variable ne pourra plus Ãªtre modifiÃ©e et doit Ãªtre initialisÃ©e dÃ¨s sa dÃ©claration, on la dÃ©clare comme une constante avec le mot rÃ©servÃ©", "final", "aucun des choix", "final", "const");
        Quiz quiz5 = new Quiz("Dans une classe, on accÃ¨de Ã  ses variables grÃ¢ce au mot clÃ©", "this", "aucun des choix", "this", "super");

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
        Quiz quiz1 = new Quiz("calculerSalaire(int) calculerSalaire(int, double)La mÃ©thode calculerSalaire est:", "surchargÃ©e", "aucun des choix", "surchargÃ©e", "redÃ©finie");
        Quiz quiz2 = new Quiz("Une classe qui contient au moins une mÃ©thode abstraite doit Ãªtre dÃ©clarÃ©e abstraite.", "vrai", "vrai", "faux");
        Quiz quiz3 = new Quiz("Est-ce quâ€™une classe peut implÃ©menter plusieurs interfaces?", "vrai", "vrai", "faux");
        Quiz quiz4 = new Quiz("La dÃ©claration d'une mÃ©thode suivante :public void traitement() throws IOExceptionprÃ©cise que la mÃ©thode propage une exception contrÃ´lÃ©e", "vrai", "vrai", "faux");
        Quiz quiz5 = new Quiz("class Test{public static void main (String[] args) {try {int a =10;System.out.println (\"a = \" + a );int b = 0 / a;System.out.println (\"b = \" + b);}catch(ArithmeticException e){System.out.println (\"diviser par 0!\"); }finally{System.out.println(\"je suis Ã  lâ€™intÃ©rieur de finally\");}}}", "a=10 b=0 Je suis Ã  lâ€™intÃ©rieur de finally", "aucun des choix", "a=10 b=0 Je suis Ã  lâ€™intÃ©rieur de finally", "a=10 b=0 diviser par 0! je suis Ã  lâ€™intÃ©rieur de finally");

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
    public void ActionDB(int niveau) {
        System.out.println(niveau);
        if (checkReponseAllQuestion(niveau)) {
            Tentation(niveau);
        } else {
            JOptionPane.showMessageDialog(null, "Merci de rÃ©pondre Ã  toutes les questions");
        }
    }
    //CHECK IF EVERY RADIO BUTTON GROUP HAS A SELECTED RADIO BUTTON
    public boolean checkReponseAllQuestion(int niveau) {
        boolean ret = true;
        for (int i = 0; i < 5; i++) {
            if (listeButtonGroup.get(i).getSelectedToggle() == null) {
                ret = false;
            }
        }
        return ret;
    }
    //
    public void Tentation(int niveau) {
        if (cpt == 0) {
            getreponses(niveau);
           // correction(niveau);
            if (cptN == 0) {
                if ((niveau == 1 && calculeScore(1) >= 40) || (niveau == 2 && calculeScore(2) >= 60) || (niveau == 3 && calculeScore(3) >= 80)) {
                  
                	//correction(niveau);
                   // System.out.println("Out of correction");
                    afficheCorrection(niveau);
                } else {
                    cptN = 1;
                    lbNbt.setText(String.format("Tentative : %d", cptN));
                    lbNbt.setStyle("-fx-text-fill:orange");
                    //
                    for (int i = 0; i < 5; i++) {
                        listePlayer_QUIZ.remove(listePlayer_QUIZ.size() - 1);
                    }
                    JOptionPane.showMessageDialog(null, "Error tu peut reprendre");
                }
            } else {
                cptN = 0;
                afficheCorrection(niveau);
            }
        } else {
            afficheCorrection(niveau);
        }
    }
    //FILL THE GLOBAL ARRAY LIST WITH CLASS INSTANCES OF THE QESUTION RESPONSES + USER INPUT RESPONSE
    public void getreponses(int niveau) {
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
        int btngroupIndex = 0;
        //System.out.println(listeButtonGroup.size());
        for (int i = start; i < end; i++) {
            boolean choice;
            RadioButton chk = (RadioButton) listeButtonGroup.get(btngroupIndex).getSelectedToggle();
            choice = listeQuiz.get(i).getReponse().equals(chk.getText());
            //
            System.out.println(chk.getText() + " responses " + listeQuiz.get(i).getReponse());
            //
            Player_QUIZ player_QUIZ = new Player_QUIZ(player1.getId_Player(), listeQuiz.get(i).getId_quiz(), chk.getText(), choice);
            listePlayer_QUIZ.add(player_QUIZ);
            //
            btngroupIndex++;
        }


    }
    //CALCULATE USER SCORE AND RETURN IT
    public int calculeScore(int niveau) {
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
        int score = 0;
        for (int i = start; i < end; i++) {
            if (listePlayer_QUIZ.get(i).isGoodchoice()) {
                score += 20;
            }
        }
        System.out.println(score);
        return score;
    }
    //
    public void afficheCorrection(int niveau) {
        if (cpt == 0) {
        	cpt = 1;
            correction(niveau);
        }
            
        else {
            cpt = 0;
            if (niveau == 1) {
                if (calculeScore(1) >= 40) {
                    niv = 2;
                    niveau2();
                    System.out.println(calculeScore(1));
                } else {
                    String invisibleChar = "\u200e";
                    final ImageIcon icon = new ImageIcon("lose.gif");
                    JOptionPane.showMessageDialog(null, invisibleChar, "YOU LOST HAHAHAH", JOptionPane.INFORMATION_MESSAGE, icon);
                    System.exit(0);
                }
            } else if (niveau == 2) {
                if (calculeScore(2) >= 60) {
                    niv = 3;
                    niveau3();
                } else {
                    new musiquePlayer("lose.mp3");
                    String invisibleChar = "\u200e";
                    final ImageIcon icon = new ImageIcon("lose.gif");
                    JOptionPane.showMessageDialog(null, invisibleChar, "YOU LOST HAHAHAH ", JOptionPane.INFORMATION_MESSAGE, icon);

                    System.exit(0);
                }
            } else {
                if (calculeScore(3) >= 80) {
                    new musiquePlayer("win.mp3");
                    String invisibleChar = "\u200e";
                    final ImageIcon icon = new ImageIcon("source.gif");
                    JOptionPane.showMessageDialog(null, invisibleChar, "YOU WON ! ", JOptionPane.INFORMATION_MESSAGE, icon);
                    System.exit(0);

                } else {
                    new musiquePlayer("lose.mp3");
                    String invisibleChar = "\u200e";
                    final ImageIcon icon = new ImageIcon("lose.gif");
                    JOptionPane.showMessageDialog(null, invisibleChar, "YOU LOST HAHAHAH ", JOptionPane.INFORMATION_MESSAGE, icon);
                    System.exit(0);
                }
            }
        }
    }
    //
    private void remplirePanelNiveau(int niveau) {
        lbNiv.setText(String.format("Niveau : %d", niveau));
        lbNbt.setStyle("-fx-text-fill:green");
        //
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

    //
    public void correction(int niveau) {
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
        int btngroupIndex = 0;
        //
        System.out.println("Correction");
        for (int i = start; i < end; i++) {
            ToggleGroup radsV = listeButtonGroup.get(btngroupIndex);
            ObservableList<Toggle> collection = radsV.getToggles();
            //
            for (Toggle element : collection) {
                RadioButton radioB = (RadioButton) element;
                //
                if (radioB.isSelected()) {
                    if (listePlayer_QUIZ.get(i).isGoodchoice()) {
                        radioB.setStyle("-fx-text-fill: green");
                        break;
                    } else
                        radioB.setStyle("-fx-text-fill: red");
                } else if (radioB.getText().equals(listeQuiz.get(i).getReponse()) && !listePlayer_QUIZ.get(i).isGoodchoice())
                    radioB.setStyle("-fx-text-fill: green");
            }
            btngroupIndex++;
        }
    }
}


