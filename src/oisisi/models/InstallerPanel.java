package oisisi.models;

import java.io.Serializable;
import java.util.HashMap;

public class InstallerPanel implements Serializable {

	private static final long serialVersionUID = -7175053092065499811L;

	public static int COUNTER;

	private int id;
	private String name;
	private HashMap<Integer, Parameter> parameters;
	private boolean selected;

	public InstallerPanel() {
		this.id = COUNTER++;
		this.name = "Frame";
		this.parameters = new HashMap<>();
		this.selected = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Integer, Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<Integer, Parameter> parameters) {
		this.parameters = parameters;
	}

	public void addParameter(Parameter parameter) {
		if (!parameters.containsKey(parameter.getId())) {
			parameters.put(parameter.getId(), parameter);
		}
	}

	public void removeParameter(int id) {
		parameters.remove(id);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static void clearCounter() {
		COUNTER = 0;
	}

	@Override
	public String toString() {
		return name;
	}

}
