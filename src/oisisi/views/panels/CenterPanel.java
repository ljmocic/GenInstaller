package oisisi.views.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import oisisi.Application;
import oisisi.Config;
import oisisi.models.Installer;

public class CenterPanel extends JPanel {

	private static final long serialVersionUID = -2086390002438187395L;

	private TreePanel treePanel;
	private LivePreviewPanel livePreviewPanel;

	private JSplitPane jsplitPane;

	public CenterPanel(Installer installerModel) {
		jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		treePanel = new TreePanel(installerModel);
		livePreviewPanel = new LivePreviewPanel(installerModel);

		jsplitPane.setDividerLocation(jsplitPane.getWidth() + Config.INSTALLER_DIMENSION_X);
		jsplitPane.setContinuousLayout(true);
		jsplitPane.add(treePanel, JSplitPane.LEFT);
		jsplitPane.add(livePreviewPanel, JSplitPane.RIGHT);

		setLayout(new BorderLayout());
		add(jsplitPane);
	}

	public TreePanel getTreePanel() {
		return treePanel;
	}

	public void switchToCompactView() {
		Application.getInstance().setSize(new Dimension(350, 550));
		jsplitPane.setDividerLocation(jsplitPane.getWidth() + Config.INSTALLER_DIMENSION_X + 100);
		jsplitPane.repaint();
		repaint();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = dim.getWidth();
		double screenHeight = dim.getHeight();
		Application.getInstance().setLocation((int) (screenWidth / 2 - Config.INSTALLER_MIN_DIMENSION_X / 2),
				(int) screenHeight / 2 - Config.INSTALLER_MIN_DIMENSION_Y / 2);

	}

	public void switchToRegularView() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = dim.getWidth();
		double screenHeight = dim.getHeight();
		Application.getInstance().setSize((int) (screenWidth / 2 + Config.STARTING_WINDOWS_SIZE),
				(int) screenHeight / 2 + Config.STARTING_WINDOWS_SIZE);
		if (jsplitPane.getDividerLocation() == jsplitPane.getWidth() + Config.INSTALLER_DIMENSION_X) {
			jsplitPane.setDividerLocation(jsplitPane.getWidth() + Config.INSTALLER_DIMENSION_X);
		}
		jsplitPane.repaint();
		repaint();
		Application.getInstance().setLocationRelativeTo(null);
	}

}
