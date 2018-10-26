package tk.rayjackson.rpg.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import tk.rayjackson.rpg.helpers.CharacterFacing;

public class JackAnimations {
    private Jack jack;
    private TextureAtlas atlas;

    // Down Animations
    private Animation downIdle;
    private Animation downRun;
    // Up Animations
    private Animation upIdle;
    private Animation upRun;
    // Side Animations
    private Animation sideIdle;
    private Animation sideRun;

    public JackAnimations(Jack jack, TextureAtlas atlas) {
        this.jack = jack;
        this.atlas = atlas;
        initializeAnimations();
    }

    private void initializeAnimations() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        downIdle = defineAnimation(frames, "FrontIdle");
        downRun = defineAnimation(frames, "FrontRun");
        upIdle = defineAnimation(frames, "BackIdle");
        upRun = defineAnimation(frames, "BackRun");
        sideIdle = defineAnimation(frames, "SideIdle");
        sideRun = defineAnimation(frames, "SideRun");
    }

    private Animation defineAnimation(Array<TextureRegion> frameArray, String name) {
        for (int i = 0; i < 4; i++) {
            frameArray.add(new TextureRegion(atlas.
                    findRegion(name), i * 16, 0, 16, 16));
        }

        Animation animation = new Animation(.2f, frameArray);
        frameArray.clear();
        return animation;
    }

    public Animation getAnimation(boolean isRunning, CharacterFacing facing) {
        switch (facing) {
            case TOP:
                return isRunning ? upRun : upIdle;
            case DOWN:
                return isRunning ? downRun : downIdle;
            case LEFT:
            case RIGHT:
            default:
                return isRunning ? sideRun : sideIdle;
        }
    }
}
