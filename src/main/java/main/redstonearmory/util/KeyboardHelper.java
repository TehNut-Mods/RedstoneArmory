package main.redstonearmory.util;

import org.lwjgl.input.Keyboard;

public class KeyboardHelper {

    /**
     *
     * @return - If shift is being held.
     */
	public static boolean isShiftDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}

    /**
     *
     * @return - If control is being held.
     */
	public static boolean isControlDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
	}
}