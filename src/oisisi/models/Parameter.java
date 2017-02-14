package oisisi.models;

import java.io.Serializable;

import javax.swing.JPanel;

public class Parameter implements Serializable {

	private static final long serialVersionUID = 3234466127225975363L;

	public static int COUNTER;

	protected int id;
	protected String name;
	protected String type;
	protected transient JPanel panel;
	protected boolean hidden;
	protected boolean selected;

	public Parameter(String name, String type) {
		this.id = COUNTER++;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public static void clearCounter() {
		COUNTER = 0;
	}

	@Override
	public String toString() {
		return name;
	}

}
