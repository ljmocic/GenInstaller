package oisisi.models.parameterTypes;

import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JTextField;

import oisisi.models.Parameter;

public class ParameterFileChooser extends Parameter implements Serializable {

	private static final long serialVersionUID = 4641169481181373824L;

	public static final String parameterType = "PARAMETER_FILE_CHOOSER";

	private String labelName;
	private String fieldDefaultText;
	private String path;
	private JTextField textField;
	private boolean executionFile;

	public ParameterFileChooser(String name, boolean hidden) {
		super(name, parameterType);
		this.hidden = hidden;
	}

	public ParameterFileChooser(String name, String path, boolean hidden) {
		super(name, parameterType);
		this.path = path;
		this.hidden = hidden;
	}

	public ParameterFileChooser(String name, String path, boolean hidden, boolean executionFile) {
		super(name, parameterType);
		this.path = path;
		this.hidden = hidden;
		this.executionFile = executionFile;
	}

	public boolean isExecutionFile() {
		return executionFile;
	}

	public void setExecutionFile(boolean executionFile) {
		this.executionFile = executionFile;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getFieldDefaultText() {
		return fieldDefaultText;
	}

	public void setFieldDefaultText(String fieldDefaultText) {
		this.fieldDefaultText = fieldDefaultText;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
