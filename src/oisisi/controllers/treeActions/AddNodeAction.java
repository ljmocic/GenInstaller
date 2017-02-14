package oisisi.controllers.treeActions;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import oisisi.Application;
import oisisi.views.dialogs.AddNewFrameDialog;
import oisisi.views.dialogs.SelectParameterTypeDialog;
import oisisi.views.panels.TreePanel;

public class AddNodeAction extends AbstractAction {

	private static final long serialVersionUID = -6336006691566333751L;

	private TreePanel treePanel;
	private ResourceBundle resourceBundle;

	public AddNodeAction(TreePanel treePanel, String name) {
		super(name);
		this.treePanel = treePanel;
	}

	public AddNodeAction(String name, Icon icon) {
		super(name, icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		resourceBundle = Application.getInstance().getResourceBundle();

		if (treePanel.getTreeLevel() == 1) {
			if (Application.getInstance().getInstallerModel().getName().equals("No active installer")
					|| Application.getInstance().getInstallerModel().getName().equals("Nema aktivnih instalera")) {
				JOptionPane.showMessageDialog(Application.getInstance(),
						resourceBundle.getString("showMessageDialog2"));

			} else {
				AddNewFrameDialog dialog = new AddNewFrameDialog();
				dialog.setVisible(true);
			}
		} else if (treePanel.getTreeLevel() == 2) {
			SelectParameterTypeDialog dialog = new SelectParameterTypeDialog();
			dialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(Application.getInstance(), resourceBundle.getString("showMessageDialog6"));
		}
	}

}
