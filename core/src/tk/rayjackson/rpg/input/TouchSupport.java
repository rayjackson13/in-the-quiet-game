package tk.rayjackson.rpg.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import tk.rayjackson.rpg.WorldParams;
import tk.rayjackson.rpg.game.Game;

enum State {
    UP, UP_PRESSED, LEFT, LEFT_PRESSED, DOWN, DOWN_PRESSED, RIGHT, RIGHT_PRESSED
}

class TextureProcessor {
    private Texture atlas;

    public TextureProcessor() {
        atlas = new Texture("skins/controls.png");
    }

    public TextureRegion getTexture(State state) {
        switch (state) {
            case UP:
                return new TextureRegion(atlas, 0, 0, 16, 16);
            case UP_PRESSED:
                return new TextureRegion(atlas, 16, 0, 16, 16);
            case LEFT:
                return new TextureRegion(atlas, 32, 0, 16, 16);
            case LEFT_PRESSED:
                return new TextureRegion(atlas, 48, 0, 16, 16);
            case DOWN:
                return new TextureRegion(atlas, 64, 0, 16, 16);
            case DOWN_PRESSED:
                return new TextureRegion(atlas, 80, 0, 16, 16);
            case RIGHT:
                return new TextureRegion(atlas, 96, 0, 16, 16);
            case RIGHT_PRESSED:
                return new TextureRegion(atlas, 112, 0, 16, 16);
            default:
                return new TextureRegion(atlas, 0, 0, 16, 16);
        }
    }
}

public class TouchSupport {
    private int BUTTON_SIZE = 20;
    private OrthographicCamera camera;
    private Game game;

    private TextureProcessor textureProcessor;

    private Rectangle leftArrow;
    private Rectangle rightArrow;
    private Rectangle upArrow;
    private Rectangle downArrow;

    private State currentlyPressed;
    private Vector2 collisionPoint;

    public TouchSupport(Game game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        leftArrow = new Rectangle(10, 30, BUTTON_SIZE, BUTTON_SIZE);
        rightArrow = new Rectangle(50, 30, BUTTON_SIZE, BUTTON_SIZE);
        upArrow = new Rectangle(30, 50, BUTTON_SIZE, BUTTON_SIZE);
        downArrow = new Rectangle(30, 10, BUTTON_SIZE, BUTTON_SIZE);
        textureProcessor = new TextureProcessor();
        collisionPoint = new Vector2();
    }

    public Vector2 getDirection() {
        if (currentlyPressed != null) {
            switch (currentlyPressed) {
                case UP:
                    return new Vector2(0, 1);
                case DOWN:
                    return new Vector2(0, -1);
                case LEFT:
                    return new Vector2(-1, 0);
                case RIGHT:
                    return new Vector2(1, 0);
                default:
                    return new Vector2(0, 0);
            }
        }
        return new Vector2(0,0);
    }

    public void draw() {
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, WorldParams.WORLD_WIDTH, WorldParams.WORLD_HEIGHT);
        game.batch.setProjectionMatrix(uiMatrix);
        currentlyPressed = null;

        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
            collisionPoint.set(transformCoordinates(touchPosition));
            System.out.println(touchPosition.toString());
            if (leftArrow.contains(collisionPoint)) {
                game.batch.begin();
                drawLeft(true);
                currentlyPressed = State.LEFT;
                game.batch.end();
            } else if (rightArrow.contains(collisionPoint)) {
                game.batch.begin();
                drawRight(true);
                currentlyPressed = State.RIGHT;
                game.batch.end();
            } else if (upArrow.contains(collisionPoint)) {
                game.batch.begin();
                drawUp(true);
                currentlyPressed = State.UP;
                game.batch.end();
            } else if (downArrow.contains(collisionPoint)) {
                game.batch.begin();
                drawDown(true);
                currentlyPressed = State.DOWN;
                game.batch.end();
            }
        }

        game.batch.begin();
        if (currentlyPressed != State.LEFT) {
            drawLeft(false);
        }
        if (currentlyPressed != State.RIGHT) {
            drawRight(false);
        }
        if (currentlyPressed != State.UP) {
            drawUp(false);
        }
        if (currentlyPressed != State.DOWN) {
            drawDown(false);
        }
        game.batch.end();
    }

    private Vector2 transformCoordinates(Vector3 origin) {
        float[] divider = {origin.x / Gdx.graphics.getWidth(), origin.y / Gdx.graphics.getHeight()};
        Vector2 result = new Vector2();
        result.set(WorldParams.WORLD_WIDTH * divider[0], WorldParams.WORLD_HEIGHT * divider[1]);
        return result;
    }

    public void drawLeft(boolean isPressed) {
        State state = isPressed ? State.LEFT_PRESSED : State.LEFT;
        game.batch.draw(textureProcessor.getTexture(state), 10, 30, BUTTON_SIZE, BUTTON_SIZE);
    }

    public void drawRight(boolean isPressed) {
        State state = isPressed ? State.RIGHT_PRESSED : State.RIGHT;
        game.batch.draw(textureProcessor.getTexture(state), 50, 30, BUTTON_SIZE, BUTTON_SIZE);
    }

    public void drawUp(boolean isPressed) {
        State state = isPressed ? State.UP_PRESSED : State.UP;
        game.batch.draw(textureProcessor.getTexture(state), 30, 50, BUTTON_SIZE, BUTTON_SIZE);
    }

    public void drawDown(boolean isPressed) {
        State state = isPressed ? State.DOWN_PRESSED : State.DOWN;
        game.batch.draw(textureProcessor.getTexture(state), 30, 10, BUTTON_SIZE, BUTTON_SIZE);
    }
}
