import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CarMovePrototype extends Application {

    private Rectangle car;
    private TranslateTransition forwardTransition;
    private RotateTransition rotateTransition;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 200);

        // Create a car (rectangle)
        car = new Rectangle(50, 30, Color.BLUE);
        car.setTranslateX(10);
        car.setTranslateY(100);

        root.getChildren().add(car);

        // Create TranslateTransition for forward movement
        forwardTransition = createTranslateTransition(car, Duration.seconds(1), 0, 0);

        // Create RotateTransition for rotation
        rotateTransition = createRotateTransition(car, Duration.seconds(0.5), 0);

        // Set up event handlers for key presses
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        scene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));

        primaryStage.setTitle("Key Control Car Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TranslateTransition createTranslateTransition(Rectangle node, Duration duration, double byX, double byY) {
        TranslateTransition transition = new TranslateTransition(duration, node);
        transition.setByX(byX);
        transition.setByY(byY);
        return transition;
    }

    private RotateTransition createRotateTransition(Rectangle node, Duration duration, double angle) {
        RotateTransition transition = new RotateTransition(duration, node);
        transition.setByAngle(angle);
        return transition;
    }

    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case W:
                double angle = car.getRotate(); // Get the current rotation angle
                double deltaX = Math.cos(Math.toRadians(angle)) * 50; // Calculate X component of movement
                double deltaY = Math.sin(Math.toRadians(angle)) * 50; // Calculate Y component of movement

                forwardTransition.setByX(deltaX);
                forwardTransition.setByY(deltaY);
                forwardTransition.play();
                break;
            case A:
                rotateTransition.setByAngle(-30);
                rotateTransition.play();
                break;
            case D:
                rotateTransition.setByAngle(30);
                rotateTransition.play();
                break;
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case W:
                forwardTransition.stop();
                break;
            case A:
            case D:
                rotateTransition.stop();
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
