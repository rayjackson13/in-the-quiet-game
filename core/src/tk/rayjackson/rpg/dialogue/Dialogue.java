package tk.rayjackson.rpg.dialogue;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tk.rayjackson.rpg.game.Game;
import tk.rayjackson.rpg.WorldParams;
import tk.rayjackson.rpg.input.Controls;

class SkipListener implements KeyListener {
    private Dialogue dialogue;

    public SkipListener(Dialogue dialogue) {
        this.dialogue = dialogue;
        System.out.println("AAAAAA");
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("AAAAAA");
        dialogue.dispose();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

public class Dialogue {
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Game game;

    public Dialogue(Game game, OrthographicCamera camera) {
        this.font = new BitmapFont();
        this.spriteBatch = new SpriteBatch();
        this.camera = camera;
        font.getData().setScale(.5f, .5f);
        this.game = game;
    }

    public void show(String message) {
        game.pause();
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, WorldParams.WORLD_WIDTH, WorldParams.WORLD_HEIGHT);
        spriteBatch.setProjectionMatrix(uiMatrix);
        spriteBatch.begin();
        spriteBatch.draw(new Texture("message.png"), 16, 8, 224, 48);
        font.draw(spriteBatch, message, 32, 48);
        spriteBatch.end();
        SkipListener skipListener = new SkipListener(this);
    }

    public void dispose() {
        game.resume();
        spriteBatch.dispose();
    }
}
