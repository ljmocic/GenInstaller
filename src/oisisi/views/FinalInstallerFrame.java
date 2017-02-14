package oisisi.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.jimmc.jshortcut.JShellLink;
import oisisi.Application;
import oisisi.Config;
import oisisi.models.Installer;
import oisisi.models.InstallerPanel;
import oisisi.models.Parameter;
import oisisi.models.parameterTypes.PanelsFactory;

@SuppressWarnings("serial")
public class FinalInstallerFrame extends JFrame {

	private int activePanel = 0;

	private MyFinishListener finishListener;

	private Toolkit toolkit;

	private Installer installer;
	private JPanel finalInstallerPanel;
	private JPanel panelCenterInternal;
	private JPanel southInternal;
	private JScrollPane scrollPane;

	private JButton nextTest;
	private JButton backTest;

	private String mode;

	private ResourceBundle resourceBundle;

	public FinalInstallerFrame(String mode) {
		this.activePanel = 0;
		this.mode = mode;
		initialize();
	}

	private void initialize() {
		resourceBundle = Application.getInstance().getResourceBundle();
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				activePanel = 0;
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}

		});

		installer = Application.getInstance().getInstallerModel();
		toolkit = Toolkit.getDefaultToolkit();

		finalInstallerPanel = new JPanel(new BorderLayout());
		panelCenterInternal = new JPanel();
		southInternal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		scrollPane = new JScrollPane(panelCenterInternal, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelCenterInternal.setLayout(new BoxLayout(panelCenterInternal, BoxLayout.Y_AXIS));

		nextTest = new JButton("Next");
		nextTest.addActionListener(new MyActionListener());
		backTest = new JButton("Back");
		backTest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (installer.getPanels().size() - 1 == activePanel) {
					nextTest.removeActionListener(finishListener);
					nextTest.removeActionListener(this);
					nextTest.setText("Next");
					nextTest.addActionListener(new MyActionListener());
				}
				if (activePanel != 0) {
					panelCenterInternal.removeAll();
					activePanel--;
					loadPanelFromModel(activePanel);
				}
			}

		});

		southInternal.add(backTest);
		southInternal.add(nextTest);

		finalInstallerPanel.add(southInternal, BorderLayout.SOUTH);
		finalInstallerPanel.add(scrollPane, BorderLayout.CENTER);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

		loadPanelFromModel(0);

		setFrameDimensions();
		add(finalInstallerPanel);

	}

	private void setFrameDimensions() {
		Dimension screenSize = toolkit.getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		setSize((int) (screenWidth / 4 + Config.STARTING_WINDOWS_SIZE),
				(int) screenHeight / 4 + Config.STARTING_WINDOWS_SIZE);

		setSize(installer.getWidth(), installer.getHeight());
		setLocationRelativeTo(null);
	}

	private void loadPanelFromModel(int id) {
		if (installer.getPanels().size() == 1) {
			nextTest.setText("Finish");
			nextTest.addActionListener(new MyFinishListener());
		}
		HashMap<Integer, InstallerPanel> panels = installer.getPanels();
		int i = 0;
		for (InstallerPanel panel : panels.values()) {
			if (i == id) {
				setTitle(panel.getName());
				if (panel.getParameters().size() > 0) {
					for (Parameter parameter : panel.getParameters().values()) {
						addNewPanel(PanelsFactory.generatePanel(parameter));
					}
				} else {
					addNewPanel(new JPanel());
				}
			}
			i++;
		}
	}

	public void addNewPanel(JPanel panel) {
		panelCenterInternal.add(panel);
		panelCenterInternal.validate();
		scrollPane.validate();
		scrollPane.repaint();
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			panelCenterInternal.removeAll();
			activePanel++;
			loadPanelFromModel(activePanel);
			if (installer.getPanels().size() == (activePanel + 1)) {
				nextTest.removeActionListener(this);
				panelCenterInternal.removeAll();
				loadPanelFromModel(activePanel);
				nextTest.setText("Finish");
				finishListener = new MyFinishListener();
				nextTest.addActionListener(finishListener);
			}

		}
	}

	class MyFinishListener extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (!mode.equals("TEST")) {
				addFiles();

				boolean shortcutEnabled = Application.getInstance().getInstallerModel().isShortcut();
				if (shortcutEnabled) {
					boolean executionExists = Application.getInstance().getInstallerModel().isExecutionExists();
					if (executionExists) {
						createShortcut();
					} else {
						JOptionPane.showMessageDialog(Application.getInstance(),
								resourceBundle.getString("executionNotAdded"));
					}
				}
				setVisible(false);
				System.exit(0);
			}

			setVisible(false);
		}

	}

	public void addFiles() {
		File directory = new File("files");

		if (!directory.exists()) {
			try {
				directory.mkdir();
			} catch (SecurityException se) {
			}
		}
		File folder = new File("files/");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				// proverava da li se radi o exe fajlu i stavlja
				// putanju sa kojom mora povezati shortcut
				if (("files/" + listOfFiles[i].getName())
						.equals(Application.getInstance().getInstallerModel().getExecutionPath())) {
					// install destination sadrzi string destinacije
					// koju je korisnik odabrao ali moramo jos
					// dodati \ kako bi pristupili fajlu u tom
					// folderu
					Application.getInstance().getInstallerModel()
							.setInstallPath(Application.getInstance().getInstallerModel().getInstallDestionation()
									+ "\\" + listOfFiles[i].getName());
				}
				copyToDestFolder(Application.getInstance().getInstallerModel().getInstallDestionation(),
						listOfFiles[i].toString(), listOfFiles[i].getName());
			}
		}
	}

	public boolean copyToDestFolder(String destPath, String sourcePath, String fileName) {

		boolean result = true;

		Path source = Paths.get(sourcePath);
		Path destination = Paths.get(destPath + File.separatorChar + fileName);

		try {
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}

		return result;

	}

	private void createShortcut() {
		try {
			JShellLink link = new JShellLink();
			String path = Application.getInstance().getInstallerModel().getInstallPath();
			// System.out.println(path);
			// String iconPath =
			// Application.getInstance().getInstallerModel().getIconPath();
			link.setFolder(JShellLink.getDirectory("desktop"));
			link.setName(Application.getInstance().getInstallerModel().getName());
			link.setPath(path);
			link.save();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
