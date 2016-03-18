package javafx.debug.axis;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * Main class starting the JavaFX application and displaying the demo
 * of the debug axis
 * @author Pierre Monnin
 */
public class Main extends Application {
    private Scene scene;
    private Group root;

    private Group cameraGroup;
    private PerspectiveCamera camera;

    private double oldMouseX;
    private double oldMouseY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Box
        Sphere sphere = new Sphere(2);
        sphere.setMaterial(new PhongMaterial(Color.RED));

        // Camera
        this.camera = new PerspectiveCamera(true);
        this.cameraGroup = new Group();
        this.cameraGroup.getChildren().add(this.camera);
        this.cameraGroup.getTransforms().addAll(new Translate(0, 0, -15));

        // Root node
        this.root = new Group();
        this.root.getChildren().add(this.cameraGroup);
        this.root.getChildren().add(new DebugAxis(-2, 4, -2, 6, -5, 6));

        // Scene
        this.scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED);
        this.scene.setFill(Color.WHITESMOKE);
        this.scene.setCamera(this.camera);

        // Controls for the scene
        handleKeyboard();
        handleMouse();

        // Primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo - JavaFX debug axis");
        primaryStage.show();
    }

    /**
     * Handles keyboard to reset or translate the camera
     */
    public void handleKeyboard() {
        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case Z:
                        Main.this.cameraGroup.getTransforms().clear();
                        Main.this.cameraGroup.getTransforms().add(new Translate(0.0, 0.0, -15));
                        break;

                    case UP:
                        Main.this.cameraGroup.getTransforms().add(new Translate(0.0, 0.0, 0.4));
                        break;

                    case DOWN:
                        Main.this.cameraGroup.getTransforms().add(new Translate(0.0, 0.0, -0.4));
                        break;

                    case LEFT:
                        Main.this.cameraGroup.getTransforms().add(new Translate(-0.4, 0.0, 0.0));
                        break;

                    case RIGHT:
                        Main.this.cameraGroup.getTransforms().add(new Translate(0.4, 0.0, 0.0));
                        break;

                    case PAGE_DOWN:
                        Main.this.cameraGroup.getTransforms().add(new Translate(0.0, 0.4, 0.0));
                        break;

                    case PAGE_UP:
                        Main.this.cameraGroup.getTransforms().add(new Translate(0.0, -0.4, 0.0));

                    default:
                        // Nothing happens
                        break;
                }
            }
        });
    }


    /**
     * Handles mouse dragging to change the camera orientation
     */
    public void handleMouse() {
        this.scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isPrimaryButtonDown()) {
                    Main.this.oldMouseX = event.getX();
                    Main.this.oldMouseY = event.getY();
                }
            }
        });

        this.scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    double deltaX = event.getX() - Main.this.oldMouseX;
                    double deltaY = event.getY() - Main.this.oldMouseY;

                    Main.this.oldMouseX = event.getX();
                    Main.this.oldMouseY = event.getY();


                    Main.this.cameraGroup.getTransforms().add(new Rotate(deltaX * 0.01, Rotate.Y_AXIS));
                    Main.this.cameraGroup.getTransforms().add(new Rotate(-deltaY * 0.01, Rotate.X_AXIS));
                }
            }
        });
    }

    /**
     * Java main for running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
