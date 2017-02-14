package oisisi.views.panels;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import oisisi.Application;
import oisisi.controllers.treeActions.AddNodeAction;
import oisisi.controllers.treeActions.MyTreeSelectionListener;
import oisisi.controllers.treeActions.RemoveNodeAction;
import oisisi.models.Installer;
import oisisi.models.InstallerPanel;
import oisisi.models.Parameter;

@SuppressWarnings("serial")
public class TreePanel extends JPanel implements Observer {

	private Installer installer;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode workspaceNode;
	private DefaultTreeCellRenderer renderer;

	private JPanel actionsPanel;
	private JButton addButton;
	private JButton removeButton;
	private int lastSelectedPanelId;

	private int treeLevel;

	private ResourceBundle resourceBundle;

	private MyTreeSelectionListener focus;

	public TreePanel(Installer installerModel) {
		this.installer = installerModel;
		this.installer.addObserver(this);
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());

		resourceBundle = Application.getInstance().getResourceBundle();

		initTree();
		initActionsPanel();
		loadFromModel();

		add(new JScrollPane(tree), BorderLayout.CENTER);
		add(actionsPanel, BorderLayout.SOUTH);
	}

	private void initTree() {

		workspaceNode = new DefaultMutableTreeNode(resourceBundle.getString("workspaceNode"));
		treeModel = new DefaultTreeModel(workspaceNode);
		treeModel.setAsksAllowsChildren(true);

		tree = new JTree(treeModel) {

			@Override
			public boolean isPathEditable(TreePath path) {

				// Svi se mogu editovati sem root-a.
				if (path != null) {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
					if (!treeNode.isRoot()) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}

		};
		tree.setEditable(true);
		treeModel.addTreeModelListener(new MyTreeModelListener());

		// adding listeners
		focus = new MyTreeSelectionListener(this);
		tree.addTreeSelectionListener(focus);

		renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		// renderer.setClosedIcon(closedIcon);
		// renderer.setOpenIcon(openIcon);
		ImageIcon test = new ImageIcon(getClass().getResource("/resources/images/leaf.png"));
		renderer.setLeafIcon(test);
	}

	private void initActionsPanel() {
		actionsPanel = new JPanel();

		addButton = new JButton(new AddNodeAction(this, "Add"));

		removeButton = new JButton(new RemoveNodeAction(this, "Remove"));

		actionsPanel.add(addButton);
		actionsPanel.add(removeButton);
		actionsPanel.setFocusable(true);
	}

	private void loadFromModel() {
		HashMap<Integer, InstallerPanel> panels = installer.getPanels();
		DefaultMutableTreeNode installerNode = new DefaultMutableTreeNode(installer.getName());
		treeModel.insertNodeInto(installerNode, workspaceNode, workspaceNode.getChildCount());
		for (InstallerPanel panel : panels.values()) {
			DefaultMutableTreeNode panelNode = new DefaultMutableTreeNode(panel);
			treeModel.insertNodeInto(panelNode, installerNode, installerNode.getChildCount());
			for (Parameter parameter : panel.getParameters().values()) {
				DefaultMutableTreeNode parameterNode = new DefaultMutableTreeNode(parameter);
				parameterNode.setAllowsChildren(false);
				treeModel.insertNodeInto(parameterNode, panelNode, panelNode.getChildCount());
			}
		}

		DefaultMutableTreeNode currentNode = workspaceNode.getNextNode();
		if (!workspaceNode.isLeaf()) {
			do {
				if (currentNode.getLevel() == 1)
					tree.expandPath(new TreePath(currentNode.getPath()));
				currentNode = currentNode.getNextNode();
			} while (currentNode != null);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		this.installer = (Installer) o;
		if ((int) arg == 1) {
			removeListener();
			workspaceNode.removeAllChildren();
			loadFromModel();
			treeModel.reload();
			addListener();

			// Prikazivanje JTree
			DefaultMutableTreeNode currentNode = workspaceNode.getNextNode();
			if (!workspaceNode.isLeaf()) {
				do {
					if (currentNode.getLevel() == 1)
						tree.expandPath(new TreePath(currentNode.getPath()));
					currentNode = currentNode.getNextNode();
				} while (currentNode != null);
			}
		}
	}

	public int getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(int treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getTextAddButton() {
		return addButton.getText();
	}

	public void setTextAddButton(String text) {
		addButton.setText(text);
	}

	public String getTextRemoveButton() {
		return removeButton.getText();
	}

	public void setTextRemoveButton(String text) {
		removeButton.setText(text);
	}

	public int getLastSelectedId() {
		return lastSelectedPanelId;
	}

	public JTree getTree() {
		return tree;
	}

	public void setLastSelectedId(int lastSelectedId) {
		this.lastSelectedPanelId = lastSelectedId;
		installer.setClickedPanel(lastSelectedId);
	}

	public void setLastSelectedParameterId(int lastSelectedParameterId) {
		installer.setClickedParameter(lastSelectedParameterId);
	}

	class MyTreeModelListener implements TreeModelListener {

		@Override
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

			int index = e.getChildIndices()[0];
			if ((node.getChildAt(index)).toString().isEmpty()) {
				JOptionPane.showMessageDialog(Application.getInstance(), "Morate uneti bar jedan karakter");

				izmenjenPrikaz();
			} else {
				// Proveravamo da li menjamo ime instalera
				if (node.getUserObject().toString().equals(treeModel.getRoot().toString())) {
					if (installer.getName().equals("No active installer")
							|| installer.getName().equals("Nema aktivnih instalera")) {
						// Ne mozemo promeniti ime ukoliko nemamo aktivnih
						// instalera
						JOptionPane.showMessageDialog(Application.getInstance(),
								resourceBundle.getString("noActiveInstaller"));

						removeListener();
						workspaceNode.removeAllChildren();
						loadFromModel();
						treeModel.reload();
						addListener();
					} else {
						// menjanje ime za instoler
						node = (DefaultMutableTreeNode) (node.getChildAt(index));
						installer.setName(node.getUserObject().toString());
						izmenjenPrikaz();
					}

				} else {
					HashMap<Integer, InstallerPanel> panels = installer.getPanels();
					boolean nasaoFrameKoren = false;
					Integer kljucFrame = null;
					// Ukoliko je koren promenjenog cvora nadjen znaci da je
					// promenjeno ime parametra
					for (Map.Entry<Integer, InstallerPanel> entry : panels.entrySet()) {
						if (entry.getValue().getName().equals(node.toString())) {
							nasaoFrameKoren = true;
							kljucFrame = entry.getKey();
							break;
						}
					}
					if (nasaoFrameKoren) {
						// ovde nadjemo izmenjeni koren
						node = (DefaultMutableTreeNode) (node.getChildAt(index));
						int indeks = 0;
						int kljuc = -1;
						HashMap<Integer, Parameter> parameters = installer.getPanels().get(kljucFrame).getParameters();
						for (Map.Entry<Integer, Parameter> entry : parameters.entrySet()) {
							if (indeks == index) {
								kljuc = entry.getKey();
								break;
							} else {
								indeks++;
							}
						}
						if (kljuc != -1) {
							installer.getPanels().get(kljucFrame).getParameters().get(kljuc)
									.setName(node.getUserObject().toString());
						}

						izmenjenPrikaz();
					} else {
						node = (DefaultMutableTreeNode) (node.getChildAt(index));
						int indeks = 0;
						int kljuc = -1;
						for (Map.Entry<Integer, InstallerPanel> entry : panels.entrySet()) {
							if (indeks == index) {
								kljuc = entry.getKey();
								break;
							} else {
								indeks++;
							}
						}
						if (kljuc != -1) {
							installer.getPanels().get(kljuc).setName(node.getUserObject().toString());
						}

						izmenjenPrikaz();

					}
				}
			}
		}

		@Override
		public void treeNodesInserted(TreeModelEvent arg0) {

		}

		@Override
		public void treeNodesRemoved(TreeModelEvent arg0) {

		}

		@Override
		public void treeStructureChanged(TreeModelEvent arg0) {

		}

	}

	private void izmenjenPrikaz() {
		removeListener();
		workspaceNode.removeAllChildren();
		loadFromModel();
		treeModel.reload();
		addListener();

		DefaultMutableTreeNode currentNode = workspaceNode.getNextNode();
		if (!workspaceNode.isLeaf()) {
			do {
				if (currentNode.getLevel() == 1)
					tree.expandPath(new TreePath(currentNode.getPath()));
				currentNode = currentNode.getNextNode();
			} while (currentNode != null);
		}
	}

	private void addListener() {
		tree.addTreeSelectionListener(focus);
	}

	private void removeListener() {
		tree.removeTreeSelectionListener(focus);
	}

	public void updateLanguage() {
		resourceBundle = Application.getInstance().getResourceBundle();
		addButton.setText(resourceBundle.getString("buttonAdd"));
		removeButton.setText(resourceBundle.getString("buttonRemove"));
		workspaceNode.setUserObject(resourceBundle.getString("workspaceNode"));
		focus.updateLanguage();
	}
}
