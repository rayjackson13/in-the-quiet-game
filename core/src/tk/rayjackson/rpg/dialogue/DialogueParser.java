package tk.rayjackson.rpg.dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;


public class DialogueParser {

    /**
     * Deserialize dialog json-file into a list of messages for DialogueStack.
     * @param path - path to json file.
     */
    public static List<Dialogue> parse(String path) {
        List<Dialogue> dialogues = new ArrayList<Dialogue>();
        Json json = new Json();
        ArrayList list = json.fromJson(ArrayList.class, Gdx.files.internal(path));
        for (Object item : list) {
            dialogues.add(new Dialogue((String) item));
        }
        return dialogues;
    }
}
