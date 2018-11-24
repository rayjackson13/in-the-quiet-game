package tk.rayjackson.rpg.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

class MapProcessor {

    static TiledMap loadMap(String name) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load(name);
    }
}
