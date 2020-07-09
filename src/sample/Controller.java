package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Arrays;

public class Controller {
    public TextArea secondSeq;
    public TextArea firstSeq;
    public Button alignButton;
    public TextField gapPen;
    public ChoiceBox scoringMatrix;
    public ChoiceBox algorithm;
    public TextField match;
    public ToggleGroup type;
    public TextField mismatch;
    public TextArea output;
    public Button viewMatrix;
    public GridPane table;



    String algo;
    Cell [][] matrixToDisplay;
    private String[] firstToDisplay;
    String[] secondToDisplay;

    public void setFirstToDisplay(String[] firstToDisplay) {
        this.firstToDisplay = firstToDisplay;
    }

    public String [] getFirstToDisplay(){
        return this.firstToDisplay;
    }

    public void initialize(){
        scoringMatrix.setDisable(false);
        match.setDisable(true);
        mismatch.setDisable(true);
        viewMatrix.setDisable(true);

        gapPen.setText("-1");
        match.setText("1");
        mismatch.setText("-1");

    }

    public void handleDNAClick(ActionEvent e){
        scoringMatrix.setDisable(true);
        match.setDisable(false);
        mismatch.setDisable(false);
    }

    public void handleProteinClick(ActionEvent e){
        scoringMatrix.setDisable(false);
        match.setDisable(true);
        mismatch.setDisable(true);
    }



    public void handleAlignClicked(ActionEvent actionEvent) {

        String firstSequence =firstSeq.getText();
        String secondSequence = secondSeq.getText();
        String matrix = (String) scoringMatrix.getValue();
        RadioButton selected = (RadioButton) type.getSelectedToggle();
        String type = selected.getText();
        int gapPenalty = Integer.parseInt(gapPen.getText());
        String algoType = (String) algorithm.getValue();
        algo = algoType;
        int matchToPass=0;
        int mismatchToPass = 0;

        if(type.equals("DNA")) {
             matchToPass = Integer.parseInt(match.getText());
             mismatchToPass = Integer.parseInt(mismatch.getText());
        }

        if(firstSequence.length() == 8 && secondSequence.length() == 8){
            viewMatrix.setDisable(false);
        }

        setFirstToDisplay(firstSequence.split(""));
        System.out.println(Arrays.toString(this.firstToDisplay));
        if(algoType.equals("Needleman-Wunsch")) {
            NeedlemanWunsch n = new NeedlemanWunsch(firstSequence, secondSequence, type, gapPenalty, matchToPass, mismatchToPass, matrix);
            matrixToDisplay = n.getMatrix();
            //firstToDisplay = n.getFirstSequence();
            secondToDisplay = n.getSecondSequence();
            n.startPropagation();
            output.setText(n.printAlignment());

        }else if(algoType.equals("Smith-Waterman")){
            SmithWaterman s = new SmithWaterman(firstSequence,secondSequence,type,gapPenalty,matchToPass,mismatchToPass,matrix);
            //s.printMatrix(s.getMatrix());

            matrixToDisplay = s.getMatrix();
            firstToDisplay = s.getFirstSequence();
            secondToDisplay = s.getSecondSequence();
            s.startPropagation();
            output.setText(s.printAlignment());
        }


    }


    public void handleViewMatrixClicked(ActionEvent e) throws IOException {

        Parent root = null;
        Scene scene = viewMatrix.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        FXMLLoader loader = new FXMLLoader((getClass().getResource("matrixPage.fxml")));
        root = loader.load();
        if(e.getSource() == viewMatrix) {
            MatrixPage matrixPage = loader.getController();
            matrixPage.transferInfo(firstToDisplay,secondToDisplay,matrixToDisplay);
        }

        Scene matrixScene = new Scene(loader.getRoot());

        stage.setScene(matrixScene);
        //stage.show();


    }

    public Node getNodebyIndex(int row, int column, GridPane gridPane){
        Node result = null;
        ObservableList<Node> cells = gridPane.getChildren();
        for(Node node: cells){
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node)== column){
                result = node;
                break;
            }
        }
        return result;
    }
}
