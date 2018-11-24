package tk.rayjackson.rpg.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import tk.rayjackson.rpg.helpers.CharacterFacing;

class JackAnimations {
    private final TextureAtlas atlas;

    // Down Animations
    private Animation<TextureRegion> downIdle;
    private Animation<TextureRegion> downRun;
    // Up Animations
    private Animation<TextureRegion> upIdle;
    private Animation<TextureRegion> upRun;
    // Side Animations
    private Animation<TextureRegion> sideIdle;
    private Animation<TextureRegion> sideRun;

    JackAnimations(Jack jack, TextureAtlas atlas) {
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

    private Animation<TextureRegion> defineAnimation(Array<TextureRegion> frameArray, String name) {
        for (int i = 0; i < 4; i++) {
            frameArray.add(new TextureRegion(atlas.
                    findRegion(name), i * 16, 0, 16, 16));
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(.2f, frameArray);
        frameArray.clear();
        return animation;
    }

    Animation<TextureRegion> getAnimation(boolean isRunning, CharacterFacing facing) {
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
