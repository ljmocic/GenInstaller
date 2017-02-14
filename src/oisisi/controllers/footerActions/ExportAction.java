package oisisi.controllers.footerActions;

import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.Config;

@SuppressWarnings("serial")
public class ExportAction extends AbstractAction {

	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			saveConfig();

			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog7"));
		} else {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog5"));
		}
	}

	public void saveConfig() {
		Application.getInstance().saveInstaller(Config.DEFAULT_SAVE_LOCATION + "installer.gin");
		String activeMode = "user";
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(Config.DEFAULT_SAVE_LOCATION + "config.gin")));
			out.writeObject(activeMode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}
