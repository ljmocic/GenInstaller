package oisisi.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;

public class Installer extends Observable implements Serializable {

	private static final long serialVersionUID = 5312490802168727898L;

	private static int COUNTER;

	private int id;
	private String name;
	private String companyName;
	private HashMap<Integer, InstallerPanel> panels;
	private boolean shortcut;

	private String installPath;
	private String executionPath;
	private String installDestination;
	private boolean executionExists;

	private int height;
	private int width;

	public Installer(String name, String companyName) {
		this.executionExists = false;
		this.id = COUNTER++;
		this.name = name;
		this.companyName = companyName;
		this.panels = new HashMap<>();
		this.installPath = "desktop";
	}

	public Installer() {
		this.executionExists = false;
		this.id = 0;
		this.name = "No active installer";
		this.setCompanyName("");
		this.panels = new HashMap<>();
		this.installPath = "desktop";
		// default save location
		this.installDestination = "C:\\Program Files";
	}

	public HashMap<Integer, InstallerPanel> getPanels() {
		return panels;
	}

	public void setPanels(HashMap<Integer, InstallerPanel> panels) {
		this.panels = panels;
		for (InstallerPanel panel : panels.values()) {
			if (panel.getId() > InstallerPanel.COUNTER) {
				InstallerPanel.COUNTER = panel.getId();
			}
			for (Parameter parameter : panel.getParameters().values()) {
				parameter.setPanel(PanelsFactory.generatePanel(parameter));
				if (parameter.getId() > Parameter.COUNTER) {
					Parameter.COUNTER = parameter.getId();
				}
			}
		}
		updateTree();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		updateTree();
	}

	public boolean isShortcut() {
		return shortcut;
	}

	public void setShortcut(boolean shortcut) {
		this.shortcut = shortcut;
	}

	public String getInstallPath() {
		return installPath;
	}

	public void setInstallPath(String installPath) {
		this.installPath = installPath;
	}

	public int getLastSelectedPanel() {
		for (InstallerPanel panel : panels.values()) {
			if (panel.isSelected()) {
				return panel.getId();
			}
		}
		return -1;
	}

	public int getLastSelectedParameter() {
		for (InstallerPanel panel : panels.values()) {
			for (Parameter parameter : panel.getParameters().values()) {
				if (parameter.isSelected()) {
					return parameter.getId();
				}
			}
		}
		return -1;
	}

	public void setClickedPanel(int i) {
		clearClickedPanel();
		panels.get(i).setSelected(true);
		updateLivePreview();
	}

	public boolean addPanel(InstallerPanel installerPanel) {
		if (!panels.containsKey(installerPanel.getId())) {
			panels.put(installerPanel.getId(), installerPanel);
			updateTree();
			return true;
		}
		return false;
	}

	public boolean removeSelectedPanel() {
		if (panels.containsKey(getLastSelectedPanel())) {
			panels.remove(getLastSelectedPanel());
			updateTree();
			updateLivePreview();
			return true;
		}
		return false;
	}

	public boolean setClickedParameter(int i) {
		clearClickedParameter();
		for (InstallerPanel panel : panels.values()) {
			if (panel.getParameters().containsKey(i)) {
				panel.getParameters().get(i).setSelected(true);
				return true;
			}
		}
		return false;
	}

	public void clearClickedParameter() {
		for (InstallerPanel panel : panels.values()) {
			for (Parameter parameter : panel.getParameters().values()) {
				parameter.setSelected(false);
			}
		}
	}

	public void clearClickedPanel() {
		for (InstallerPanel panel : panels.values()) {
			panel.setSelected(false);
		}
	}

	public void addNewInstaller(String name, String companyName) {
		removeInstaller();
		COUNTER++;
		this.id = COUNTER;
		this.name = name;
		this.companyName = companyName;
		this.panels.clear();
		this.installPath = "desktop";
		updateTree();
	}

	public void removeInstaller() {
		Installer.clearCounter();
		InstallerPanel.clearCounter();
		Parameter.clearCounter();
		this.name = Application.getInstance().getResourceBundle().getString("installerDefaultName");
		this.id = 0;
		panels.clear();
		updateLivePreview();
		updateTree();
	}

	private static void clearCounter() {
		COUNTER = 0;
	}

	public boolean addParameter(Parameter parameter) {
		if (getLastSelectedPanel() != -1
				&& !panels.get(getLastSelectedPanel()).getParameters().containsKey(parameter.getId())) {
			panels.get(getLastSelectedPanel()).addParameter(parameter);
			updateTree();
			updateLivePreview();
			return true;
		}
		return false;
	}

	public boolean removeSelectedParameter() {
		if (panels.containsKey(getLastSelectedPanel())) {
			panels.get(getLastSelectedPanel()).removeParameter(getLastSelectedParameter());
			updateTree();
			updateLivePreview();
			return true;
		}
		return false;
	}

	private void updateTree() {
		setChanged();
		notifyObservers(1);
	}

	private void updateLivePreview() {
		setChanged();
		notifyObservers(2);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setExecutionPath(String string) {
		executionPath = string;
	}

	public String getExecutionPath() {
		return executionPath;
	}

	public void setInstallDestination(String destPath) {
		installDestination = destPath;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getInstallDestionation() {
		return installDestination;
	}

	public boolean isExecutionExists() {
		return executionExists;
	}

	public void setExecutionExists(boolean executionExists) {
		this.executionExists = executionExists;
	}

}
