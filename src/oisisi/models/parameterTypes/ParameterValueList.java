package oisisi.models.parameterTypes;

import java.io.Serializable;
import java.util.List;

import javax.swing.JPanel;

import oisisi.models.Parameter;

public class ParameterValueList extends Parameter implements Serializable {

	private static final long serialVersionUID = 7512480647511661708L;

	public static final String parameterType = "PARAMETER_VALUE_LIST";

	private String labelText;
	private List<String> values;

	public ParameterValueList(String name, String labelText, List<String> values) {
		super(name, parameterType);
		this.labelText = labelText;
		this.values = values;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
