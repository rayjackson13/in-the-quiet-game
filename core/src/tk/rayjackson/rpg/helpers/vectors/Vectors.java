package tk.rayjackson.rpg.helpers.vectors;

import com.badlogic.gdx.math.Vector2;

public class Vectors {
    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 mul(Vector2 a, int b) {
        return new Vector2(a.x * b, a.y * b);
    }
}
