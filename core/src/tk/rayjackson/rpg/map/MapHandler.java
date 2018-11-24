package tk.rayjackson.rpg.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapHandler {
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final int[] terrainLayerIndices;
    private final MapLayer objectLayer;
    private final int[] decorationLayersIndices;
    private final MapProperties properties;

    public MapHandler(String mapName) {
        map = MapProcessor.loadMap(mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
        MapLayers mapLayers = map.getLayers();
        terrainLayerIndices = new int[] {
                mapLayers.getIndex("trees"),
                mapLayers.getIndex("people")
        };
        objectLayer = mapLayers.get("objects");

        decorationLayersIndices = new int[]{
                mapLayers.getIndex("background"),
                mapLayers.getIndex("graphics"),
                mapLayers.getIndex("bridge")
        };
        properties = map.getProperties();
    }

    public int getWidth() {
        int width = properties.get("width", Integer.class);
        int tileWidth = properties.get("tilewidth", Integer.class);
        return width * tileWidth;
    }

    public int getHeight() {
        int height = properties.get("height", Integer.class);
        int tileHeight = properties.get("tileheight", Integer.class);
        return height * tileHeight;
    }

    public void setCamera(OrthographicCamera camera) {
        renderer.setView(camera);
    }

    public void renderDecorations() {
        renderer.render(decorationLayersIndices);
    }

    public void renderTerrain() {
        renderer.render(terrainLayerIndices);
    }

    public MapObjects getObjects() {
        return objectLayer.getObjects();
    }

}
