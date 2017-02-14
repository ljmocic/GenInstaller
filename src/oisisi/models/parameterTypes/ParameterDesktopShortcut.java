package oisisi.models.parameterTypes;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import oisisi.models.Parameter;

public class ParameterDesktopShortcut extends Parameter {

	private static final long serialVersionUID = 932863310185664119L;

	public static final String parameterType = "PARAMETER_DESKTOP_SHORTCUT";

	private JLabel labelText;
	private Dimension dimensionLabel = new Dimension(140, 20);
	private String path;

	public ParameterDesktopShortcut(boolean hidden) {
		super("Desktop Shortcut", parameterType);
		generateLabelText();
		this.hidden = hidden;
	}

	private void generateLabelText() {
		labelText = new JLabel("Desktop Shortcut");
		labelText.setPreferredSize(dimensionLabel);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
