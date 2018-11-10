package tk.rayjackson.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tk.rayjackson.rpg.Level;

public class Game extends com.badlogic.gdx.Game {
    public SpriteBatch batch;
    private GameState state;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Level(this));
		state = GameState.RUNNING;
	}

	@Override
	public void render () {
		super.render();
	}

	public void pause() {
		state = GameState.PAUSED;
	}

	public void resume() {
		state = GameState.RUNNING;
	}

	public boolean isPaused() {
		return state == GameState.PAUSED;
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
