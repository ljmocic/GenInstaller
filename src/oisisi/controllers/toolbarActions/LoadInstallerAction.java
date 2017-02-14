package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import oisisi.Application;

@SuppressWarnings("serial")
public class LoadInstallerAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();

		ResourceBundle resourceBundle = Application.getInstance().getResourceBundle();

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle(resourceBundle.getString("loadInstallerDialog"));
		fileChooser.setApproveButtonText(resourceBundle.getString("loadInstallerDialogApprove"));

		int returnValue = fileChooser.showOpenDialog(Application.getInstance());

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getPath();
			Application.getInstance().loadInstaller(path);
		}
	}

}
