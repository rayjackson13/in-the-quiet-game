package tk.rayjackson.rpg.input;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.PovDirection;

import tk.rayjackson.rpg.input.mapping.XboxMapping;

import static com.badlogic.gdx.Gdx.input;
import static tk.rayjackson.rpg.input.mapping.XboxMapping.*;

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
}

public class Controls {
    private ControllerSupport controllerSupport;
    private KeyboardControls keyboard;
    private GamepadControls gamepad;

    public Controls() {
        this.controllerSupport = new ControllerSupport();
        this.keyboard = new KeyboardControls();
        this.gamepad = new GamepadControls(controllerSupport);
    }

    public boolean isUp() {
        return keyboard.UP() || gamepad.UP();
    }

    public boolean isDown() {
        return keyboard.DOWN() || gamepad.DOWN();
    }

    public boolean isLeft() {
        return keyboard.LEFT() || gamepad.LEFT();
    }

    public boolean isRight() {
        return keyboard.RIGHT() || gamepad.RIGHT();
    }
}
