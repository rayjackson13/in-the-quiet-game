package tk.rayjackson.rpg.input;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.OrthographicCamera;

import tk.rayjackson.rpg.game.Game;
import tk.rayjackson.rpg.input.mapping.XboxMapping;

import static com.badlogic.gdx.Gdx.input;
import static tk.rayjackson.rpg.input.mapping.XboxMapping.*;

class TouchControls {
    private TouchSupport support;
    public TouchControls(TouchSupport support) {
        this.support = support;
    }
    boolean UP() { return support.getDirection().y > 0; }
    boolean DOWN() { return support.getDirection().y < 0; }
    boolean LEFT() { return support.getDirection().x < 0; }
    boolean RIGHT() { return support.getDirection().x > 0; }
}

class KeyboardControls {
    boolean UP() {
        return input.isKeyPressed(Input.Keys.W);
    }
    boolean DOWN() {
        return input.isKeyPressed(Input.Keys.S);
    }
    boolean LEFT() {
        return input.isKeyPressed(Input.Keys.A);
    }
    boolean RIGHT() {
        return input.isKeyPressed(Input.Keys.D);
    }
    boolean SPACE() {
        return input.isKeyPressed(Input.Keys.SPACE);
    }
    boolean LeftMouseDown() {
        return input.isButtonPressed(Input.Buttons.LEFT);
    }
}

class GamepadControls {
    private ControllerSupport support;
    GamepadControls(ControllerSupport support) {
        this.support = support;
    }

    boolean UP () {
        return support.getPov(POV) == PovDirection.north || support.getAxis(AXIS_LX) < 0;
    }
    boolean DOWN () {
        return support.getPov(POV) == PovDirection.south || support.getAxis(AXIS_LX) > 0;
    }
    boolean LEFT () {
        return support.getPov(POV) == PovDirection.west || support.getAxis(AXIS_LY) < 0;
    }
    boolean RIGHT () {
        return support.getPov(POV) == PovDirection.east || support.getAxis(AXIS_LY) > 0;
    }

    boolean A () {
        return support.getButton(0);
    }
}

public class Controls {
    private ControllerSupport controllerSupport;
    private KeyboardControls keyboard;
    private GamepadControls gamepad;
    private TouchControls touch;
    private TouchSupport touchSupport;

    public Controls(Game game, OrthographicCamera camera) {
        controllerSupport = new ControllerSupport();
        keyboard = new KeyboardControls();
        gamepad = new GamepadControls(controllerSupport);
        touchSupport = new TouchSupport(game, camera);
        touch = new TouchControls(touchSupport);
    }

    public void draw() {
        touchSupport.draw();
    }

    public boolean isUp() {
        return keyboard.UP() || gamepad.UP() || touch.UP();
    }

    public boolean isDown() {
        return keyboard.DOWN() || gamepad.DOWN() || touch.DOWN();
    }

    public boolean isLeft() {
        return keyboard.LEFT() || gamepad.LEFT() || touch.LEFT();
    }

    public boolean isRight() {
        return keyboard.RIGHT() || gamepad.RIGHT() || touch.RIGHT();
    }

    public boolean isSkip() {
        return  keyboard.SPACE() || gamepad.A() || keyboard.LeftMouseDown();
    }
}
