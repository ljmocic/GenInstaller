package oisisi;

import java.io.File;

import oisisi.views.FinalInstallerFrame;

public class Main {

	public static void main(String[] args) {

		// Initialize Application singleton
		Application.getInstance();

		// Show developer or user mode
		checkApplicationMode();
	}

	public static void checkApplicationMode() {
		// Check existence of config file
		File file = new File(Config.DEFAULT_SAVE_LOCATION + Config.CONFIG_FILE_NAME + Config.EXTENSION);
		if (file.exists() && !file.isDirectory()) {
			Config.ACTIVE_MODE = "user";
			Application.getInstance().loadInstaller(Config.DEFAULT_SAVE_LOCATION + "installer.gin");
			// Show user mode
			FinalInstallerFrame frame = new FinalInstallerFrame("");
			frame.setVisible(true);
		} else {
			Config.ACTIVE_MODE = "developer";

			// Show developer mode
			Application.getInstance().setVisible(true);
		}
	}
}
