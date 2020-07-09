package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {


    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Help");
        window.setMinWidth(400);

        Label label = new Label("Each cell in the matrix contains it's maximum value along with the direction of the traceback:");
        Label diag = new Label("d: diagonal\n h: horizontal\n v: vertical");
        Label direction = new Label("Multiple directions in a cell indicate there are multiple maximums present");
        Label traceback = new Label("Traceback starts in the bottom right and ends in the top left cell for Needleman-Wunsch.");
        Label traceback2 = new Label("For Smith-Waterman, traceback starts in the cell with the highest value and ends when the first 0 is encountered ");
        Label space = new Label("");
        label.setPadding(new Insets(20, 20, 0, 20));
        diag.setPadding(new Insets(0, 20, 0, 20));
        direction.setPadding(new Insets(0, 20, 0, 20));

        Button yesButton = new Button("Okay");
        yesButton.setPadding(new Insets(10, 10, 10, 10));

        yesButton.setOnAction(e -> {
            window.close();
        });


        VBox layout = new VBox(30);
        layout.getChildren().addAll(label, diag, direction, traceback,traceback2,yesButton, space);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
