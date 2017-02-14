package oisisi.controllers.menuActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import oisisi.views.dialogs.VersionDialog;

public class InfoMessageAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		VersionDialog dialog = new VersionDialog();
		dialog.setVisible(true);
	}

}
