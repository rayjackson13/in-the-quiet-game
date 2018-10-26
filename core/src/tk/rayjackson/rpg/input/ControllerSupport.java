package tk.rayjackson.rpg.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.Array;

public class ControllerSupport {
    private Array<Controller> controllers;
    private Controller active;

    public ControllerSupport() {
        controllers = Controllers.getControllers();
        System.out.println("Controllers: " + controllers.size);
        for (Controller c : controllers) {
            System.out.println(c.getName());
            if(c.getName().contains("Microsoft")) {
                active = c;
            }
        }
    }

    public boolean getButton(int buttonCode) {
        return active.getButton(buttonCode);
    }

    public float getAxis(int axisCode) {
        // deal 20% of deadzone
        float axis = active.getAxis(axisCode);
        if (Math.abs(axis) < .2f) {
            return 0;
        }
        return active.getAxis(axisCode);
    }

    public PovDirection getPov(int povCode) {
        return active.getPov(povCode);
    }
}
