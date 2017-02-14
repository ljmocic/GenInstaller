package oisisi.controllers.treeActions;

import java.util.ResourceBundle;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import oisisi.Application;
import oisisi.models.InstallerPanel;
import oisisi.models.Parameter;
import oisisi.views.panels.TreePanel;

public class MyTreeSelectionListener implements TreeSelectionListener {

	private TreePanel treePanel;
	private ResourceBundle resourceBundle;

	public MyTreeSelectionListener(TreePanel treePanel) {
		super();
		this.treePanel = treePanel;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		resourceBundle = Application.getInstance().getResourceBundle();

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel.getTree().getLastSelectedPathComponent();
		if (node != null) {

			treePanel.setTextAddButton(resourceBundle.getString("buttonAdd"));
			treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemove"));

			Object nodeInfo = node.getUserObject();

			if (node.getLevel() == 1) {

				treePanel.setTextAddButton(resourceBundle.getString("buttonAddFrame"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemove"));

			} else if (node.getLevel() == 2) {
				treePanel.setTextAddButton(resourceBundle.getString("buttonAddParameter"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemoveFrame"));

				InstallerPanel installerPanel = (InstallerPanel) nodeInfo;
				treePanel.setLastSelectedId(installerPanel.getId());
			} else if (node.getLevel() == 3) {
				Parameter parameter = (Parameter) nodeInfo;
				treePanel.setLastSelectedParameterId(parameter.getId());

				treePanel.setTextAddButton(resourceBundle.getString("buttonAddParameter"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemoveParameter"));
			} else {
				treePanel.setTextAddButton(resourceBundle.getString("buttonAdd"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemove"));

			}

			treePanel.setTreeLevel(node.getLevel());
		}
	}

	public void updateLanguage() {

		resourceBundle = Application.getInstance().getResourceBundle();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel.getTree().getLastSelectedPathComponent();

		if (node != null) {
			treePanel.setTextAddButton(Application.getInstance().getResourceBundle().getString("buttonAdd"));
			treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemove"));

			if (node.getLevel() == 1) {

				treePanel.setTextAddButton(Application.getInstance().getResourceBundle().getString("buttonAddFrame"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemove"));
			} else if (node.getLevel() == 2) {

				treePanel.setTextAddButton(resourceBundle.getString("buttonAddParameter"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemoveFrame"));

			} else if (node.getLevel() == 3) {

				treePanel.setTextAddButton(resourceBundle.getString("buttonAdd"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemoveParameter"));
			} else {
				treePanel.setTextAddButton(resourceBundle.getString("buttonAdd"));
				treePanel.setTextRemoveButton(resourceBundle.getString("buttonRemove"));
			}
		}
	}

}
