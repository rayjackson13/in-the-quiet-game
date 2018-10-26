package tk.rayjackson.rpg;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapProcessor {

    public static TiledMap loadMap(String name) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load(name);
    }
}
