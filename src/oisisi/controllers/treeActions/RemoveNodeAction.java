package oisisi.controllers.treeActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.views.panels.TreePanel;

@SuppressWarnings("serial")
public class RemoveNodeAction extends AbstractAction {

	private TreePanel treePanel;
	private ResourceBundle resourceBundle;

	public RemoveNodeAction(TreePanel treePanel, String name) {
		super(name);
		this.treePanel = treePanel;
	}

	public RemoveNodeAction(String name) {
		super(name);
	}

	public RemoveNodeAction(String name, Icon icon) {
		super(name, icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO lokalizacija
		resourceBundle = Application.getInstance().getResourceBundle();

		if (treePanel.getTextRemoveButton().equals("Remove Frame")
				|| treePanel.getTextRemoveButton().equals("Ukloni okvir")) {
			Application.getInstance().getInstallerModel().removeSelectedPanel();
		} else if (treePanel.getTextRemoveButton().equals("Remove Parameter")
				|| treePanel.getTextRemoveButton().equals("Ukloni parametar")) {
			Application.getInstance().getInstallerModel().removeSelectedParameter();
		} else {
			JOptionPane.showMessageDialog(oisisi.Application.getInstance(),
					resourceBundle.getString("showMessageDialog6"));
		}
	}

}
