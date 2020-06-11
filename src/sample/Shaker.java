package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {
    //Handles the shake effect
public TranslateTransition translateTransition;
    public Shaker(Node node) {
        translateTransition =new TranslateTransition(Duration.millis(50),node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

    }
}
