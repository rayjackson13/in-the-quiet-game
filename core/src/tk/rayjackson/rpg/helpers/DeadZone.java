package tk.rayjackson.rpg.helpers;

import com.badlogic.gdx.math.Rectangle;

public class DeadZone extends Rectangle {
    private float x;
    private float y;
    private float width;
    private float height;

    public DeadZone(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }
}
