package hust.soict.globalict.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;
    private boolean pen;

    @FXML
    void clearButtonPressed(ActionEvent event) {
    	drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	if(pen == true) {
    		Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
        	drawingAreaPane.getChildren().add(newCircle);
    	}
    	else {
    		Circle whiteCircle = new Circle(event.getX(), event.getY(), 16, Color.WHITE);
    		drawingAreaPane.getChildren().add(whiteCircle);
    	}
    }
    
    @FXML
    void penSelected(ActionEvent event) {
    	pen = true;
    }
    
    @FXML
    void eraserSelected(ActionEvent event) {
    	pen = false;
    }
}

