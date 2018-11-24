package tk.rayjackson.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import tk.rayjackson.rpg.camera.CameraHandler;
import tk.rayjackson.rpg.characters.Jack;
import tk.rayjackson.rpg.dialogue.Dialogue;
import tk.rayjackson.rpg.dialogue.DialogueParser;
import tk.rayjackson.rpg.dialogue.DialogueStack;
import tk.rayjackson.rpg.game.Game;
import tk.rayjackson.rpg.input.Controls;
import tk.rayjackson.rpg.input.TouchSupport;
import tk.rayjackson.rpg.map.MapHandler;
import tk.rayjackson.rpg.music.MusicController;

public class Level implements Screen {
    private Game game;
    public CameraHandler cameraHandler;
    public MapHandler mapHandler;

    // Sprites
    private TextureAtlas jackAtlas;
    private Jack jack;

    // Music
    private MusicController musicController;

    private Controls controls;
    private DialogueStack dialogueStack;

    public Game getGame() {
        return this.game;
    }

    public Level(Game game) {
        this.game = game;
        musicController = new MusicController();
        jackAtlas = new TextureAtlas("Jack.pack");
        mapHandler = new MapHandler("map.tmx");
        int mapWidth = mapHandler.getWidth();
        int mapHeight = mapHandler.getHeight();
        jack = new Jack(this, new Vector2(128, 224));
        cameraHandler = new CameraHandler(jack.getPosition(), mapWidth, mapHeight);
        controls = new Controls(game, cameraHandler.getCamera());
        initializeDialogues();
    }

    public void initializeDialogues() {
        List<Dialogue> dialogues = DialogueParser.parse("dialogues/venom1.json");
        dialogueStack = new DialogueStack(game, cameraHandler.getCamera(), dialogues);
    }

    public TextureAtlas getAtlas() {
        return jackAtlas;
    }

    private void handleInput(float dt) {
        if (controls.isUp())
            jack.move(new Vector2(0, 1));
        if (controls.isDown())
            jack.move(new Vector2(0, -1));
        if (controls.isLeft())
            jack.move(new Vector2(-1, 0));
        if (controls.isRight())
            jack.move(new Vector2(1, 0));
        if (controls.isSkip() && !dialogueStack.isEmpty()) {
            dialogueStack.pop();
        }
    }

    private void update(float dt) {
        jack.update(dt);
        cameraHandler.updateCamera();
        mapHandler.setCamera(cameraHandler.getCamera());
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!game.isPaused()) {
            update(delta);
        }
        handleInput(delta);

        mapHandler.renderDecorations();

        game.batch.setProjectionMatrix(cameraHandler.getCamera().combined);

        game.batch.begin();
        jack.draw(game.batch);
        game.batch.end();

        mapHandler.renderTerrain();

        controls.draw();
        dialogueStack.render();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        cameraHandler.updateViewPort(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
