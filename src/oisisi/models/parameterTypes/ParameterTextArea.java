package oisisi.models.parameterTypes;

import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import oisisi.models.Parameter;

public class ParameterTextArea extends Parameter implements Serializable {

	private static final long serialVersionUID = -8684566127563134524L;

	public static final String parameterType = "PARAMETER_TEXT_AREA";

	private String labelString;
	private String textAreaDefaultText;
	private JTextArea textArea;
	private boolean editable;

	public ParameterTextArea(String name, String labelString, String textAreaDefaultText, boolean editable) {
		super(name, parameterType);
		this.textAreaDefaultText = textAreaDefaultText;
		this.labelString = labelString;
		this.editable = editable;
	}

	public String getTextAreaDefaultText() {
		return textAreaDefaultText;
	}

	public void setTextAreaDefaultText(String textAreaDefaultText) {
		this.textAreaDefaultText = textAreaDefaultText;
	}

	public String getLabelString() {
		return labelString;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
