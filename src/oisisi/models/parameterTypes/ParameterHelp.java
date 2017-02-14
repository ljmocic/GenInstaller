package oisisi.models.parameterTypes;

import javax.swing.JPanel;

import oisisi.models.Parameter;

public class ParameterHelp extends Parameter {

	private static final long serialVersionUID = 1346304729545665746L;

	public static final String parameterType = "PARAMETER_HELP";

	private String helpText;

	public ParameterHelp(String helpText) {
		super("Help", parameterType);
		this.helpText = helpText;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public JPanel getPanel() {
		return panel;
	}

}
