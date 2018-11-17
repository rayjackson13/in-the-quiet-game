package tk.rayjackson.rpg.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
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

    private Vector3 touchPosition;

    public TouchSupport(Game game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        leftArrow = new Rectangle(10, 30, BUTTON_SIZE, BUTTON_SIZE);
        rightArrow = new Rectangle(50, 30, BUTTON_SIZE, BUTTON_SIZE);
        upArrow = new Rectangle(30, 50, BUTTON_SIZE, BUTTON_SIZE);
        downArrow = new Rectangle(30, 10, BUTTON_SIZE, BUTTON_SIZE);
        textureProcessor = new TextureProcessor();
        touchPosition = new Vector3();
    }

    public void draw() {
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, WorldParams.WORLD_WIDTH, WorldParams.WORLD_HEIGHT);
        game.batch.setProjectionMatrix(uiMatrix);
        game.batch.begin();
        drawLeft();
        drawRight();
        drawUp();
        drawDown();
        game.batch.end();

        if (Gdx.input.isTouched()) {
            touchPosition.set(Gdx.input.getX(), WorldParams.WORLD_HEIGHT, 0);
            System.out.println(touchPosition.toString());

        }
    }

    public void drawLeft() {
        game.batch.draw(textureProcessor.getTexture(State.LEFT), 10, 30, BUTTON_SIZE, BUTTON_SIZE);
    }

    public void drawRight() {
        game.batch.draw(textureProcessor.getTexture(State.RIGHT), 50, 30, BUTTON_SIZE, BUTTON_SIZE);
    }

    public void drawUp() {
        game.batch.draw(textureProcessor.getTexture(State.UP), 30, 50, BUTTON_SIZE, BUTTON_SIZE);
    }

    public void drawDown() {
        game.batch.draw(textureProcessor.getTexture(State.DOWN), 30, 10, BUTTON_SIZE, BUTTON_SIZE);
    }
}
