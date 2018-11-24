package tk.rayjackson.rpg.dialogue;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;

import tk.rayjackson.rpg.game.Game;

public class DialogueStack {
    private List<Dialogue> series;
    private Game game;

    public DialogueStack(Game game, OrthographicCamera camera, List<Dialogue> series) {
        this.series = series;
        this.game = game;
        for (Dialogue d : series) {
            d.setCamera(camera);
        }
    }

    public Dialogue pop() {
        series.get(0).dispose();
        return series.remove(0);
    }

    public boolean isEmpty() {
        return series.size() == 0;
    }

    public void render() {
        if (!isEmpty()) {
            game.pause();
            series.get(0).show();
        } else {
            dispose();
        }
    }

    public void dispose() {
        game.resume();
    }
}
