package oisisi.controllers.menuActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import oisisi.views.dialogs.Documentation;

public class DocumentationMessage extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Documentation dialog = new Documentation();
		dialog.setVisible(true);
	}

}
