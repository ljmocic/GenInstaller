package oisisi.models.parameterTypes;

import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JTextField;

import oisisi.models.Parameter;

public class ParameterOneLineText extends Parameter implements Serializable {

	private static final long serialVersionUID = -2842236680618166154L;

	public static final String parameterType = "PARAMETER_ONE_LINE_TEXT_EDITABLE";

	private String labelText;
	private String textFieldDefaultText;
	private JTextField field;
	private boolean editable;

	public ParameterOneLineText(String name, String labelText, String textFieldDefaultText, boolean editable) {
		super(name, parameterType);
		this.labelText = labelText;
		this.textFieldDefaultText = textFieldDefaultText;
		this.editable = editable;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public String getTextFieldDefaultText() {
		return textFieldDefaultText;
	}

	public void setTextFieldDefaultText(String textFieldDefaultText) {
		this.textFieldDefaultText = textFieldDefaultText;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
