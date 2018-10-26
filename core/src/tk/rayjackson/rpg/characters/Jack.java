package tk.rayjackson.rpg.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import tk.rayjackson.rpg.Level;
import tk.rayjackson.rpg.helpers.CharacterFacing;

import static tk.rayjackson.rpg.WorldParams.WORLD_WIDTH;

public class Jack extends Sprite {
    private Level screen;

    public enum State {RUNNING, STANDING, ATTACKING}

    public State currentState;
    public State previousState;

    private Vector2 position;

    private JackAnimations animations;
    private Animation animRun;
    private Animation animIdle;
    private CharacterFacing facing;

    private float stateTimer;
    private MapObjects mapObjects;

    private Array<Rectangle> deadZones;

    public Jack(Level screen, Vector2 startPosition) {
        this.screen = screen;
        position = startPosition;
        setBounds(position.x, position.y, 16, 16);

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0f;
        facing = CharacterFacing.TOP;
        animations = new JackAnimations(this, screen.getAtlas());
        mapObjects = screen.mapHandler.getObjects();
        deadZones = instantiateDeadZones(mapObjects);
    }

    public Array<Rectangle> instantiateDeadZones(MapObjects mapObjects) {
        deadZones = new Array<Rectangle>();
        for (MapObject mapObject : mapObjects) {
            deadZones.add(((RectangleMapObject) mapObject).getRectangle());
        }
        return deadZones;
    }

    private boolean canGoThere(Vector2 direction) {
        Rectangle hero = getBoundingRectangle();
        Vector2 point;
        if (direction.y > 0) {
            point = new Vector2(hero.x + 8, hero.y + direction.y * 16);
        } else if (direction.y < 0) {
            point = new Vector2(hero.x + 8, hero.y + direction.y);
        } else if (direction.x > 0) {
            point = new Vector2(hero.x + direction.x * 16, hero.y + 8);
        } else {
            point = new Vector2(hero.x + direction.x, hero.y + 8);
        }
        for (Rectangle rect : deadZones) {
            if (rect.contains(point)) {
                return false;
            }
        }
        return true;
    }

    public void move(Vector2 direction) {
        if (canGoThere(direction)) {
            this.position.x += direction.x * 1;
        }
        if (canGoThere(direction)) {
            this.position.y += direction.y * 1;
        }
        currentState = State.RUNNING;
        facing = updateFacing(direction);
    }

    private CharacterFacing updateFacing(Vector2 direction) {
        if (direction.x == 0) {
            if (direction.y < 0) {
                return CharacterFacing.DOWN;
            } else if (direction.y > 0) {
                return CharacterFacing.TOP;
            }
        }
        if (direction.x < 0) {
            return CharacterFacing.LEFT;
        }
        return CharacterFacing.RIGHT;
    }

    public void update(float delta) {
        setBounds(position.x, position.y, 16, 16);
        setRegion(getFrame(delta));
        currentState = State.STANDING;
    }

    private TextureRegion getFrame(float delta) {
        TextureRegion region;
        animIdle = animations.getAnimation(false, facing);
        animRun = animations.getAnimation(true, facing);
        switch (currentState) {
            case RUNNING:
                region = (TextureRegion) animRun.getKeyFrame(stateTimer, true);
                break;
            default:
                region = (TextureRegion) animIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if (facing == CharacterFacing.RIGHT && !region.isFlipX()) {
            region.flip(true, false);
        } else if (facing == CharacterFacing.LEFT && region.isFlipX()) {
            region.flip(true, false);
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return region;
    }

    public Vector2 getPosition() {
        return position;
    }
}
