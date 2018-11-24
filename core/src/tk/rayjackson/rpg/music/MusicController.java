package tk.rayjackson.rpg.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MusicController {
    private static final String musicPath = "music/";
    private final Map<String, Music> musicMap;

    public MusicController() {
        File folder = new File(musicPath);
        musicMap = new HashMap<String, Music>();
        File[] files = folder.listFiles();
        if (folder.exists() && folder.isDirectory() && files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    musicMap.put(
                            file.getName(),
                            Gdx.audio.newMusic(Gdx.files.internal(musicPath + file.getName()))
                    );
                }
            }
        }
    }

    public void play(String title) {
        if (musicMap.containsKey(title)) {
            musicMap.get(title).play();
        }
    }

// --Commented out by Inspection START (24.11.18, 22:27):
//    public void pause(String title) {
//        if (musicMap.containsKey(title)) {
//            musicMap.get(title).pause();
//        }
//    }
// --Commented out by Inspection STOP (24.11.18, 22:27)
}
