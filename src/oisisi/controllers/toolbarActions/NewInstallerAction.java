package oisisi.controllers.toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import oisisi.views.dialogs.AddNewInstallerDialog;

@SuppressWarnings("serial")
public class NewInstallerAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		AddNewInstallerDialog dialog = new AddNewInstallerDialog();
		dialog.setVisible(true);
	}

}
