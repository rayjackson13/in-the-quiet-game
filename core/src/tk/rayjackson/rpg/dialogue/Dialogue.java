package tk.rayjackson.rpg.dialogue;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import tk.rayjackson.rpg.Game;
import tk.rayjackson.rpg.WorldParams;

public class Dialogue {
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    public Dialogue(Game game, OrthographicCamera camera) {
        this.font = new BitmapFont();
        this.spriteBatch = new SpriteBatch();
        this.camera = camera;
    }

    public void show(String message) {
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, WorldParams.WORLD_WIDTH, WorldParams.WORLD_HEIGHT);
        spriteBatch.setProjectionMatrix(uiMatrix);
        spriteBatch.begin();
        spriteBatch.draw(new Texture("message.png"), 16, 48, 224, 48);
        font.draw(spriteBatch, message, 16, 48);
        spriteBatch.end();
    }
}
