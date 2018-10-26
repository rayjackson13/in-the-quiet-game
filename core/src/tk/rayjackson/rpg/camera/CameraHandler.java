package tk.rayjackson.rpg.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tk.rayjackson.rpg.helpers.vectors.Vectors;

import static tk.rayjackson.rpg.WorldParams.WORLD_HEIGHT;
import static tk.rayjackson.rpg.WorldParams.WORLD_WIDTH;

public class CameraHandler {
    private int mapWidth;
    private int mapHeight;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Vector2 position;
    private Vector2 newPosition;

    public CameraHandler(Vector2 hero, int mapWidth, int mapHeight) {
        this.newPosition = hero;
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        position = new Vector2(128, 208);
        setPosition(position);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void scrollCamera(Vector2 direction) {
        Vector2 realDirection;
        if (direction.x != 0) {
            realDirection = Vectors.mul(direction, WORLD_WIDTH);
        } else {
            realDirection = Vectors.mul(direction, WORLD_HEIGHT);
        }
        newPosition = Vectors.add(position, realDirection);
        setPosition(position);
        updateCamera();
    }

    private void setPosition(Vector2 position) {
        this.position = position;
        camera.position.set(position.x, position.y, 0);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void updateCamera() {
        float lerp = .1f;
        Vector2 temp = new Vector2();
        temp.x = camera.position.x + (newPosition.x - camera.position.x) * lerp;
        temp.y = camera.position.y + (newPosition.y - camera.position.y) * lerp;
        if (temp.x - WORLD_WIDTH / 2 > 0 && temp.x + WORLD_WIDTH / 2 < mapWidth) {
            if (temp.y - WORLD_HEIGHT / 2 > 0 || temp.y + WORLD_HEIGHT / 2 < mapHeight) {
                setPosition(new Vector2(temp.x, position.y));
            } else {
                setPosition(temp);
            }
        }
        if (temp.y - WORLD_HEIGHT / 2 > 0 && temp.y + WORLD_HEIGHT / 2 < mapHeight) {
            if (temp.x - WORLD_WIDTH / 2 > 0 || temp.x + WORLD_WIDTH / 2 < mapWidth) {
                setPosition(new Vector2(position.x, temp.y));
            } else {
                setPosition(temp);
            }
        }
        camera.update();
    }

    public void updateViewPort(int width, int height) {
        viewport.update(width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}
