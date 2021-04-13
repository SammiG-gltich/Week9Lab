
package week9lab;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Sammi
 */
public class Week9Lab extends Application{
    Line[] tri;
    Circle[] littlecirc;
    Text[] text;
    
    double cv1= 20;
    double cv2 = 200;
    double cv3= 320;
    double cv4 = 335;
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    
    tri = new Line[3];
    littlecirc = new Circle[3];
    
    Circle circ = new Circle(200,200,180);
        circ.setFill(Color.WHITE);
        circ.setStroke(Color.BLACK);
        
    // as soon as the window opens it shows the base circle
    Group gp = new Group(circ);
    
    // the three little circles
    littlecirc[0] = new Circle(cv1,cv2, 15);
    littlecirc[1] = new Circle(cv2,cv1, 15);
    littlecirc[2] = new Circle(cv3,cv4, 15);
    
    // the texts that are going to display the angles
    /* for(int i = 0; i<3; i++){    
    text[i] = new Text(littlecirc[i].getCenterX(),littlecirc[i].getCenterY(), "test");
    text[i].setTranslateX(150*i);
    gp.getChildren().add(text[i]);
     }*/ // error given with displaying angles
    
    // the lines that make the triangle
    tri[0] = new Line( littlecirc[0].getCenterX(), littlecirc[0].getCenterY(), littlecirc[1].getCenterX(), littlecirc[1].getCenterY());
    tri[1] = new Line(littlecirc[1].getCenterX(), littlecirc[1].getCenterY(), littlecirc[2].getCenterX(), littlecirc[2].getCenterY());
    tri[2] = new Line(littlecirc[2].getCenterX(), littlecirc[2].getCenterY(), littlecirc[0].getCenterX(), littlecirc[0].getCenterY());
    
        for(int i = 0; i<3; i++){
        gp.getChildren().add(tri[i]);
    }
   
    
    // sets colors and strokes for the little circles and adds them into gp
    for(int i = 0; i<3; i++){
        littlecirc[i].setFill(Color.NAVAJOWHITE);
        littlecirc[i].setStroke(Color.BLACK);
        gp.getChildren().add(littlecirc[i]);
    }
    
    
    stage.setTitle("Group Assignment");
    Scene scn = new Scene(gp, 400, 500);
    stage.setScene(scn);
    stage.show();
    
    
    scn.setOnMouseDragged(this:: processMouseDrag);
    
    }

 
    public void processMouseDrag(MouseEvent event){
      littlecirc[0].setOnMouseDragged(e -> { 
      if (littlecirc[0].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        littlecirc[0].setCenterX(e.getX());
        littlecirc[0].setCenterY(e.getY());
        setLines();
      }
    });

    littlecirc[1].setOnMouseDragged(e -> { 
      if (littlecirc[1].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        littlecirc[1].setCenterX(e.getX());
        littlecirc[1].setCenterY(e.getY());
        setLines();
      }
    });
    
    littlecirc[2].setOnMouseDragged(e -> { 
      if (littlecirc[2].contains(e.getX(), e.getY())) {
	  
        // Recompute and display angles
        littlecirc[2].setCenterX(e.getX());
        littlecirc[2].setCenterY(e.getY());
        setLines();
      }
    });
  }
      private void setLines() {
    tri[0].setStartX(littlecirc[0].getCenterX());
    tri[0].setStartY(littlecirc[0].getCenterY());
    tri[0].setEndX(littlecirc[1].getCenterX());
    tri[0].setEndY(littlecirc[1].getCenterY());
    tri[1].setStartX(littlecirc[0].getCenterX());
    tri[1].setStartY(littlecirc[0].getCenterY());
    tri[1].setEndX(littlecirc[2].getCenterX());
    tri[1].setEndY(littlecirc[2].getCenterY());
    tri[2].setStartX(littlecirc[1].getCenterX());
    tri[2].setStartY(littlecirc[1].getCenterY());
    tri[2].setEndX(littlecirc[2].getCenterX());
    tri[2].setEndY(littlecirc[2].getCenterY());
    
    // Compute angles
    double a = new Point2D(littlecirc[2].getCenterX(), littlecirc[2].getCenterY()).
            distance(littlecirc[1].getCenterX(), littlecirc[1].getCenterY());
    double b = new Point2D(littlecirc[2].getCenterX(), littlecirc[2].getCenterY()).
            distance(littlecirc[0].getCenterX(), littlecirc[0].getCenterY());
    double c = new Point2D(littlecirc[1].getCenterX(), littlecirc[1].getCenterY()).
            distance(littlecirc[0].getCenterX(), littlecirc[0].getCenterY());     
    double[] angle = new double[3];
    
    angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
    angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
    angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

    for (int i = 0; i < 3; i++) {
      text[i].setX(littlecirc[i].getCenterX());
      text[i].setY(littlecirc[i].getCenterY() - littlecirc[i].getRadius());
      text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
    }
  }
 }
