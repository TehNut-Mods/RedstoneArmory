package main.redstonearmory.util;

import org.lwjgl.input.Keyboard;

/**
 * Created by Nick on 6/6/14.
 */
public class KeyboardHandler {

	public static boolean isShiftDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}

	public static boolean isControlDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
	}

	public static boolean isVDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_V);
	}

}
