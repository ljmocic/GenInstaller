package oisisi.views.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import oisisi.Application;
import oisisi.Config;
import oisisi.models.Installer;
import oisisi.models.InstallerPanel;
import oisisi.models.Parameter;

@SuppressWarnings("serial")
public class LivePreviewPanel extends JDesktopPane implements Observer {

	private Installer installer;

	private JDesktopPane desktopPane;
	private JInternalFrame internalFrame;
	private JPanel finalInstallerPanel;

	private BoxLayout boxCenter;

	private JScrollPane scrollPane;
	private JPanel panelCenterInternal;
	private JPanel southInternal;
	private JButton nextTest;
	private JButton backTest;

	private ResourceBundle resourceBundle;

	public LivePreviewPanel(Installer installerModel) {
		this.resourceBundle = Application.getInstance().getResourceBundle();
		initialize();
		this.installer = installerModel;
		this.installer.addObserver(this);
		loadFromModel();
	}

	private void initialize() {
		setupWindowsLook();

		initGUI();
		setupMockupFinalInstallerPanel();
		setupInternalFrame();

		internalFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(final ComponentEvent e) {
				super.componentResized(e);
				installer.setWidth(internalFrame.getWidth());
				installer.setHeight(internalFrame.getHeight());
			}
		});

		Application.getInstance().getInstallerModel().setWidth(internalFrame.getWidth());
		Application.getInstance().getInstallerModel().setHeight(internalFrame.getHeight());

		add(desktopPane.add(internalFrame));
	}

	private void loadFromModel() {
		HashMap<Integer, InstallerPanel> panels = installer.getPanels();
		for (InstallerPanel panel : panels.values()) {
			internalFrame.setTitle(panel.getName());
			if (panel.isSelected()) {
				for (Parameter parameter : panel.getParameters().values()) {
					addNewPanel(parameter.getPanel());
				}
				break;
			}
		}

	}

	private void initGUI() {
		desktopPane = new JDesktopPane();
		internalFrame = new JInternalFrame("");
		finalInstallerPanel = new JPanel(new BorderLayout()); // panel inside
																// internal
																// frame
		panelCenterInternal = new JPanel();
		southInternal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		scrollPane = new JScrollPane(panelCenterInternal, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		boxCenter = new BoxLayout(panelCenterInternal, BoxLayout.Y_AXIS);
	}

	private void setupMockupFinalInstallerPanel() {
		panelCenterInternal.setLayout(boxCenter);

		nextTest = new JButton(resourceBundle.getString("next"));
		backTest = new JButton(resourceBundle.getString("back"));

		southInternal.add(backTest);
		southInternal.add(nextTest);

		finalInstallerPanel.add(southInternal, BorderLayout.SOUTH);
		finalInstallerPanel.add(scrollPane, BorderLayout.CENTER);
	}

	private void setupInternalFrame() {
		internalFrame.add(finalInstallerPanel);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setResizable(true);
		internalFrame.setClosable(false);
		internalFrame.setLocation(Config.PADDING, Config.PADDING);
		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setSize(Config.INSTALLER_DIMENSION_X + 50, Config.INSTALLER_DIMENSION_Y + 50);
		internalFrame.setVisible(true);
		internalFrame.setFrameIcon(new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png")));

	}

	public void addNewPanel(JPanel panel) {
		panelCenterInternal.add(panel);
		panelCenterInternal.validate();
		scrollPane.validate();
		scrollPane.repaint();
	}

	// Helps us to set up Windows 7 look on internalFrame in LivePreview
	public void setupWindowsLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if ((int) arg == 2) {
			panelCenterInternal.removeAll();
			loadFromModel();
			panelCenterInternal.revalidate();
			panelCenterInternal.repaint();
		}
	}
}
