package oisisi.controllers.footerActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.views.FinalInstallerFrame;

@SuppressWarnings("serial")
public class TestAction extends AbstractAction {

	private ResourceBundle resourceBundle;

	@Override
	public void actionPerformed(ActionEvent e) {

		resourceBundle = Application.getInstance().getResourceBundle();

		int installerId = Application.getInstance().getInstallerModel().getId();
		if (installerId != 0) {
			FinalInstallerFrame frame = new FinalInstallerFrame("TEST");
			frame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("showMessageDialog5"));
		}
	}

}
