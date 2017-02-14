package oisisi.models.parameterTypes;

import java.io.Serializable;

import javax.swing.JPanel;

import oisisi.models.Parameter;

public class ParameterImage extends Parameter implements Serializable {

	private static final long serialVersionUID = -3202919411972441496L;

	public static final String parameterType = "PARAMETER_IMAGE";

	private String path;

	public ParameterImage(String name, String path) {
		super(name, parameterType);
		this.path = path;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
