package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Arrays;

public class MatrixPage {
    public Button showValues;

    public TextArea zeroone;
    public TextArea zerotwo;
    public TextArea zerothree;
    public TextArea zerofour;
    public TextArea zerofive;
    public TextArea zerosix;
    public TextArea zeroseven;
    public TextArea zeroeight;
    public TextArea zeronine;

    public TextArea onezero;
    public TextArea twozero;
    public TextArea threezero;
    public TextArea fourzero;
    public TextArea fivezero;
    public TextArea sixzero;
    public TextArea sevenzero;
    public TextArea eightzero;
    public TextArea ninezero;

    public TextArea oneone;
    public TextArea onetwo;
    public TextArea onethree;
    public TextArea onefour;
    public TextArea onefive;
    public TextArea onesix;
    public TextArea oneseven;
    public TextArea oneeight;
    public TextArea onenine;

    public TextArea twoone;
    public TextArea twotwo;
    public TextArea twothree;
    public TextArea twofour;
    public TextArea twofive;
    public TextArea twosix;
    public TextArea twoseven;
    public TextArea twoeight;
    public TextArea twonine;
    
    public TextArea threeone;
    public TextArea threetwo;
    public TextArea threethree;
    public TextArea threefour;
    public TextArea threefive;
    public TextArea threesix;
    public TextArea threeseven;
    public TextArea threeeight;
    public TextArea threenine;

    public TextArea fourone;
    public TextArea fourtwo;
    public TextArea fourthree;
    public TextArea fourfour;
    public TextArea fourfive;
    public TextArea foursix;
    public TextArea fourseven;
    public TextArea foureight;
    public TextArea fournine;

    public TextArea fiveone;
    public TextArea fivetwo;
    public TextArea fivethree;
    public TextArea fivefour;
    public TextArea fivefive;
    public TextArea fivesix;
    public TextArea fiveseven;
    public TextArea fiveeight;
    public TextArea fivenine;

    public TextArea sixone;
    public TextArea sixtwo;
    public TextArea sixthree;
    public TextArea sixfour;
    public TextArea sixfive;
    public TextArea sixsix;
    public TextArea sixseven;
    public TextArea sixeight;
    public TextArea sixnine;
    public TextArea sevenone;

    public TextArea seventwo;
    public TextArea seventhree;
    public TextArea sevenfour;
    public TextArea sevenfive;
    public TextArea sevensix;
    public TextArea sevenseven;
    public TextArea seveneight;
    public TextArea sevennine;

    public TextArea eightone;
    public TextArea eighttwo;
    public TextArea eightthree;
    public TextArea eightfour;
    public TextArea eightfive;
    public TextArea eightsix;
    public TextArea eightseven;
    public TextArea eighteight;
    public TextArea eightnine;

    public TextArea nineone;
    public TextArea ninetwo;
    public TextArea ninethree;
    public TextArea ninefour;
    public TextArea ninefive;
    public TextArea ninesix;
    public TextArea nineseven;
    public TextArea nineeight;
    public TextArea ninenine;

    public Button backButton;
    public Button helpButton;

    private String[] firstToDisplay;
    private String[] secondToDisplay;
    Cell[][] matrix;

    public void transferInfo(String[] firstSeq, String[] secondSeq, Cell[][] matrix) {
        this.firstToDisplay = firstSeq;
        this.secondToDisplay = secondSeq;
        this.matrix = matrix;

    }

    public void handleBack() throws IOException {
        Parent root = null;
        Scene scene = backButton.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        FXMLLoader loader = new FXMLLoader((getClass().getResource("bugged2.fxml")));
        root = loader.load();


        Scene home = new Scene(loader.getRoot());

        stage.setScene(home);
    }

    public void handleShowValues() {
        zeroone.setText("      ---");
        onezero.setText("      ---");

        oneone.setText("        0");

        zerotwo.setText("      " + firstToDisplay[0]);
        zerothree.setText("      " + firstToDisplay[1]);
        zerofour.setText("      " + firstToDisplay[2]);
        zerofive.setText("      " + firstToDisplay[3]);
        zerosix.setText("      " + firstToDisplay[4]);
        zeroseven.setText("      " + firstToDisplay[5]);
        zeroeight.setText("      " + firstToDisplay[6]);
        zeronine.setText("      " + firstToDisplay[7]);

        twozero.setText("      " + secondToDisplay[0]);
        threezero.setText("      " + secondToDisplay[1]);
        fourzero.setText("      " + secondToDisplay[2]);
        fivezero.setText("      " + secondToDisplay[3]);
        sixzero.setText("      " + secondToDisplay[4]);
        sevenzero.setText("      " + secondToDisplay[5]);
        eightzero.setText("      " + secondToDisplay[6]);
        ninezero.setText("      " + secondToDisplay[7]);

        onetwo.setText("      " + matrix[0][1].getMaxOfCell());
        onethree.setText("      " + matrix[0][2].getMaxOfCell());
        onefour.setText("      " + matrix[0][3].getMaxOfCell());
        onefive.setText("      " + matrix[0][4].getMaxOfCell());
        onesix.setText("      " + matrix[0][5].getMaxOfCell());
        oneseven.setText("      " + matrix[0][6].getMaxOfCell());
        oneeight.setText("      " + matrix[0][7].getMaxOfCell());
        onenine.setText("      " + matrix[0][8].getMaxOfCell());

        twoone.setText("       " + matrix[1][0].getMaxOfCell());
        twotwo.setText("      " + matrix[1][1].getMaxOfCell());
        twothree.setText("      " + matrix[1][2].getMaxOfCell());
        twofour.setText("      " + matrix[1][3].getMaxOfCell());
        twofive.setText("      " + matrix[1][4].getMaxOfCell());
        twosix.setText("      " + matrix[1][5].getMaxOfCell());
        twoseven.setText("      " + matrix[1][6].getMaxOfCell());
        twoeight.setText("      " + matrix[1][7].getMaxOfCell());
        twonine.setText("      " + matrix[1][8].getMaxOfCell());

        threeone.setText("      " + matrix[2][0].getMaxOfCell());
        threetwo.setText("      " + matrix[2][1].getMaxOfCell());
        threethree.setText("      " + matrix[2][2].getMaxOfCell());
        threefour.setText("      " + matrix[2][3].getMaxOfCell());
        threefive.setText("      " + matrix[2][4].getMaxOfCell());
        threesix.setText("      " + matrix[2][5].getMaxOfCell());
        threeseven.setText("      " + matrix[2][6].getMaxOfCell());
        threeeight.setText("      " + matrix[2][7].getMaxOfCell());
        threenine.setText("      " + matrix[2][8].getMaxOfCell());

        fourone.setText("      " + matrix[3][0].getMaxOfCell());
        fourtwo.setText("      " + matrix[3][1].getMaxOfCell());
        fourthree.setText("      " + matrix[3][2].getMaxOfCell());
        fourfour.setText("      " + matrix[3][3].getMaxOfCell());
        fourfive.setText("      " + matrix[3][4].getMaxOfCell());
        foursix.setText("      " + matrix[3][5].getMaxOfCell());
        fourseven.setText("      " + matrix[3][6].getMaxOfCell());
        foureight.setText("      " + matrix[3][7].getMaxOfCell());
        fournine.setText("      " + matrix[3][8].getMaxOfCell());

        fiveone.setText("      " + matrix[4][0].getMaxOfCell());
        fivetwo.setText("      " + matrix[4][1].getMaxOfCell());
        fivethree.setText("      " + matrix[4][2].getMaxOfCell());
        fivefour.setText("      " + matrix[4][3].getMaxOfCell());
        fivefive.setText("      " + matrix[4][4].getMaxOfCell());
        fivesix.setText("      " + matrix[4][5].getMaxOfCell());
        fiveseven.setText("      " + matrix[4][6].getMaxOfCell());
        fiveeight.setText("      " + matrix[4][7].getMaxOfCell());
        fivenine.setText("      " + matrix[4][8].getMaxOfCell());

        sixone.setText("      " + matrix[5][0].getMaxOfCell());
        sixtwo.setText("      " + matrix[5][1].getMaxOfCell());
        sixthree.setText("      " + matrix[5][2].getMaxOfCell());
        sixfour.setText("      " + matrix[5][3].getMaxOfCell());
        sixfive.setText("      " + matrix[5][4].getMaxOfCell());
        sixsix.setText("      " + matrix[5][5].getMaxOfCell());
        sixseven.setText("      " + matrix[5][6].getMaxOfCell());
        sixeight.setText("      " + matrix[5][7].getMaxOfCell());
        sixnine.setText("      " + matrix[5][8].getMaxOfCell());


        sevenone.setText("      " + matrix[6][0].getMaxOfCell());
        seventwo.setText("      " + matrix[6][1].getMaxOfCell());
        seventhree.setText("      " + matrix[6][2].getMaxOfCell());
        sevenfour.setText("      " + matrix[6][3].getMaxOfCell());
        sevenfive.setText("      " + matrix[6][4].getMaxOfCell());
        sevensix.setText("      " + matrix[6][5].getMaxOfCell());
        sevenseven.setText("      " + matrix[6][6].getMaxOfCell());
        seveneight.setText("      " + matrix[6][7].getMaxOfCell());
        sevennine.setText("      " + matrix[6][8].getMaxOfCell());


        eightone.setText("      " + matrix[7][0].getMaxOfCell());
        eighttwo.setText("      " + matrix[7][1].getMaxOfCell());
        eightthree.setText("      " + matrix[7][2].getMaxOfCell());
        eightfour.setText("      " + matrix[7][3].getMaxOfCell());
        eightfive.setText("      " + matrix[7][4].getMaxOfCell());
        eightsix.setText("      " + matrix[7][5].getMaxOfCell());
        eightseven.setText("      " + matrix[7][6].getMaxOfCell());
        eighteight.setText("      " + matrix[7][7].getMaxOfCell());
        eightnine.setText("      " + matrix[7][8].getMaxOfCell());

        nineone.setText("      " + matrix[8][0].getMaxOfCell());
        ninetwo.setText("      " + matrix[8][1].getMaxOfCell());
        ninethree.setText("      " + matrix[8][2].getMaxOfCell());
        ninefour.setText("      " + matrix[8][3].getMaxOfCell());
        ninefive.setText("      " + matrix[8][4].getMaxOfCell());
        ninesix.setText("      " + matrix[8][5].getMaxOfCell());
        nineseven.setText("      " + matrix[8][6].getMaxOfCell());
        nineeight.setText("      " + matrix[8][7].getMaxOfCell());
        ninenine.setText("      " + matrix[8][8].getMaxOfCell());

    }

    public void handleHelpClicked(ActionEvent actionEvent) {
        ConfirmBox.display();

    }
}
