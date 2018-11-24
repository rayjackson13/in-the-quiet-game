package tk.rayjackson.rpg.dialogue;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.List;

import tk.rayjackson.rpg.game.Game;

public class DialogueStack {
    private final List<Dialogue> series;
    private final Game game;

    public DialogueStack(Game game, OrthographicCamera camera, List<Dialogue> series) {
        this.series = series;
        this.game = game;
        for (Dialogue d : series) {
            d.setCamera(camera);
        }
    }

    public void pop() {
        series.get(0).dispose();
        series.remove(0);
    }

    public boolean hasObjects() {
        return series.size() != 0;
    }

    public void render() {
        if (hasObjects()) {
            game.pause();
            series.get(0).show();
        } else {
            dispose();
        }
    }

    private void dispose() {
        game.resume();
    }
}
