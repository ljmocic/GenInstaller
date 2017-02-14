package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import oisisi.Application;

@SuppressWarnings("serial")
public class CloseInstallerAction extends AbstractAction {

	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			Application.getInstance().removeInstaller();
		} else {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog4"));
		}
	}

}
