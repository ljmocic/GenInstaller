package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.views.dialogs.AddNewFrameDialog;

public class AddFrameAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			AddNewFrameDialog dialog = new AddNewFrameDialog();
			dialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(Application.getInstance(), resourceBundle.getString("showMessageDialog2"));
		}
	}

}
