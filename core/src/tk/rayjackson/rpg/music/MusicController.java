package tk.rayjackson.rpg.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import tk.rayjackson.rpg.exceptions.NoSuchMusicException;

public class MusicController {
    public static final String musicPath = "music/";
    private Map<String, Music> musicMap;

    public MusicController() {
        File folder = new File(musicPath);
        musicMap = new HashMap<String, Music>();
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
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

    public void pause(String title) {
        if (musicMap.containsKey(title)) {
            musicMap.get(title).pause();
        }
    }
}
