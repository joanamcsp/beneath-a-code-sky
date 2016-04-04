package model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Created by joana on 4/4/16.
 */
public class Door extends InteractableObject {
    public static final int COLUMNS = 8;
    public static final int COUNT = 8;
    public static final int OFFSET_X = 0;
    public static final int OFFSET_Y = 0;
    public static final int WIDTH = 201;
    public static final int HEIGHT = 190;

    private Key key;
    final DoubleProperty velocity = new SimpleDoubleProperty();
    final LongProperty lastUpdateTime = new SimpleLongProperty();
    private Animation doorAnimation;
    private ImageView doorView;
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (lastUpdateTime.get() > 0) {
                final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1000000000.0;
                final double deltaX = elapsedSeconds * velocity.get();
                //final double oldX = doorView.getTranslateX();
                //final double newX = Math.max(0, Math.min(1200, oldX + deltaX));
               // doorView.setTranslateX(newX);
            }
            lastUpdateTime.set(timestamp);
        }
    };


    public Door(float x, float y) {
        super(x, y, "interactables/door.png");


    }

    public void open() {

        setFileName("interactables/doorAnimation.png");
        float px = getX();
        float py = getY();
        doorView = new ImageView(getFileName());
        doorView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        doorView.setY(px);
        doorView.setY(py);

        doorAnimation = new SpriteAnimation(
                doorView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        //doorAnimation.setCycleCount(Animation.INDEFINITE);
    }

    public ImageView getDoorView() {
        return doorView;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public Animation getDoorAnimation() {
        return doorAnimation;
    }

    public void setVelocity(double velocity) {
        this.velocity.set(velocity);
    }


}



