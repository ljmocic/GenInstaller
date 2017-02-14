package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.views.dialogs.SelectParameterTypeDialog;

public class AddParameterAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent e) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			SelectParameterTypeDialog dialog = new SelectParameterTypeDialog();
			dialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog3"));
		}
	}

}
