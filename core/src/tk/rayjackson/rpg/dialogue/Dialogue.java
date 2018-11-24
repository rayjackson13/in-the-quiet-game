package tk.rayjackson.rpg.dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Matrix4;

import tk.rayjackson.rpg.WorldParams;

public class Dialogue {
    private final BitmapFont font;
    private final SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private final String message;

    Dialogue(String message) {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 13;
        parameter.spaceY = 10;
        this.font = generator.generateFont(parameter);
        generator.dispose();
        this.spriteBatch = new SpriteBatch();
        this.message = message;
        font.getData().setScale(.5f, .5f);
    }

    void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    void show() {
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, WorldParams.WORLD_WIDTH, WorldParams.WORLD_HEIGHT);
        spriteBatch.setProjectionMatrix(uiMatrix);
        spriteBatch.begin();
        spriteBatch.draw(new Texture("message.png"), 16, 8, 224, 48);
        font.draw(spriteBatch, message, 32, 48);
        spriteBatch.end();
    }

    void dispose() {
        spriteBatch.dispose();
    }
}
