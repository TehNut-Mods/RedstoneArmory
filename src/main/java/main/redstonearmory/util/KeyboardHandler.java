package main.redstonearmory.util;

import main.redstonearmory.ConfigHandler;
import org.lwjgl.input.Keyboard;

public class KeyboardHandler {

	public static String empowerKey = "KEY_" + KeyboardHandler.empowerKey();

	public static boolean isShiftDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}

	public static boolean isControlDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
	}

	public static boolean isCDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_C);
	}

	public static String empowerKey() {
		return ConfigHandler.empowerKey;
	}


}
