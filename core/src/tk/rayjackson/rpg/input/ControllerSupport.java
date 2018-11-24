package tk.rayjackson.rpg.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.Array;

class ControllerSupport {
    private Controller active;

    ControllerSupport() {
        Array<Controller> controllers = Controllers.getControllers();
        System.out.println("Controllers: " + controllers.size);
        for (Controller c : controllers) {
            System.out.println(c.getName());
            if(c.getName().contains("Microsoft")) {
                active = c;
            }
        }
    }

    boolean getButton(int buttonCode) {
        return active != null && active.getButton(buttonCode);
    }

    float getAxis(int axisCode) {
        if (active == null) {
            return 0;
        }
        // deal 20% of dead zone
        float axis = active.getAxis(axisCode);
        if (Math.abs(axis) < .2f) {
            return 0;
        }
        return active.getAxis(axisCode);
    }

    PovDirection getPov(int povCode) {
        if (active == null) {
            return PovDirection.center;
        }
        return active.getPov(povCode);
    }
}
