package oisisi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import oisisi.models.Installer;
import oisisi.models.InstallerPanel;
import oisisi.models.Parameter;
import oisisi.views.MainMenuBar;
import oisisi.views.MainToolbar;
import oisisi.views.panels.CenterPanel;
import oisisi.views.panels.FooterPanel;

@SuppressWarnings("serial")
public class Application extends JFrame {

	private static Application instance = null;

	private Toolkit toolkit;
	private ResourceBundle resourceBundle;

	private MainMenuBar menuBar;
	private MainToolbar mainToolbar;
	private CenterPanel centerPanelView;
	private FooterPanel footerPanel;
	private Installer installerModel;

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
			instance.initialize();
		}
		return instance;
	}

	private void initialize() {
		initializeLocale();
		initializeMVC();
		initializeGUI();
	}

	private void initializeLocale() {
		Locale.setDefault(new Locale("en", "US"));
		resourceBundle = ResourceBundle.getBundle("resources.translations.MessageResources", Locale.getDefault());
	}

	private void initializeMVC() {
		installerModel = new Installer();
		centerPanelView = new CenterPanel(installerModel);
	}

	private void initializeGUI() {
		toolkit = Toolkit.getDefaultToolkit();

		setMinimumSize(new Dimension(Config.INSTALLER_MIN_DIMENSION_X, Config.INSTALLER_MIN_DIMENSION_Y));

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));

		menuBar = new MainMenuBar();
		mainToolbar = new MainToolbar();
		footerPanel = new FooterPanel();

		setTitle(resourceBundle.getString("naslovAplikacije"));
		setJMenuBar(menuBar);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setFrameDimensions();

		add(centerPanelView, BorderLayout.CENTER);
		add(mainToolbar, BorderLayout.NORTH);
		add(footerPanel, BorderLayout.SOUTH);
	}

	private void setFrameDimensions() {
		Dimension screenSize = toolkit.getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		setSize((int) (screenWidth / 2 + Config.STARTING_WINDOWS_SIZE),
				(int) screenHeight / 2 + Config.STARTING_WINDOWS_SIZE);
		setLocationRelativeTo(null);
	}

	public void changeLanguage() {
		resourceBundle = ResourceBundle.getBundle("resources.translations.MessageResources", Locale.getDefault());
		setTitle(resourceBundle.getString("naslovAplikacije"));
		menuBar.updateLanguage();
		mainToolbar.updateLanguage();
		footerPanel.updateLanguage();
		centerPanelView.getTreePanel().updateLanguage();
		if (installerModel.getId() == 0) {
			installerModel.setName(resourceBundle.getString("installerDefaultName"));
		}
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public Installer getInstallerModel() {
		return installerModel;
	}

	public void setInstallerModel(Installer installer) {
		installerModel.setName(installer.getName());
		installerModel.setId(installer.getId());
		installerModel.setPanels(installer.getPanels());
		installerModel.setExecutionPath(installer.getExecutionPath());
		installerModel.setExecutionExists(installer.isExecutionExists());
		installerModel.setShortcut(installer.isShortcut());
	}

	public void loadInstaller(String path) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			setInstallerModel((Installer) in.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	public void saveInstaller(String path) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
			out.writeObject(installerModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	public void addInstallerPanel(InstallerPanel installerPanel) {
		installerModel.addPanel(installerPanel);
	}

	public void addParameter(Parameter parameter) {
		installerModel.addParameter(parameter);
	}

	public void removeInstaller() {
		installerModel.removeInstaller();
	}

	public CenterPanel getCenterPanelView() {
		return centerPanelView;
	}

	public void setCenterPanelView(CenterPanel centerPanelView) {
		this.centerPanelView = centerPanelView;
	}

}