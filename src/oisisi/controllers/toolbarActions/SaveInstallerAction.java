package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.Config;

@SuppressWarnings("serial")
public class SaveInstallerAction extends AbstractAction {

	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("Load Installer");
			fileChooser.setApproveButtonText("Save Installer");

			int returnValue = fileChooser.showOpenDialog(Application.getInstance());

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getPath();
				Application.getInstance().saveInstaller(path + Config.EXTENSION);
			}
		} else {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog2"));
		}
	}

}
