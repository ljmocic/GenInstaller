package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import oisisi.Application;

public class RemoveFrameAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent e) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			if (Application.getInstance().getInstallerModel().getLastSelectedPanel() != -1) {
				Application.getInstance().getInstallerModel().removeSelectedPanel();
			} else {
				JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog3"));
			}
		} else {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog4"));
		}
	}

}
