package oisisi.views.dialogs;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oisisi.Application;

public class VersionDialog extends JDialog {

	private static final long serialVersionUID = 7024049266328093485L;

	private ImageIcon icon;
	private ResourceBundle resourceBundle;

	public VersionDialog() {
		initialize();
		addPanel();
	}

	public void initialize() {
		resourceBundle = Application.getInstance().getResourceBundle();
		icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setSize(400, 200);
		setLocationRelativeTo(Application.getInstance());
		setIconImage(icon.getImage());
		setTitle(resourceBundle.getString("aboutTitle"));
	}

	private void addPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		label.setText(resourceBundle.getString("aboutLabel1"));

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel();
		label1.setIcon(icon);

		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel();
		label2.setText(resourceBundle.getString("aboutLabel2"));

		setIconImage(icon.getImage());

		panel.add(label);
		panel1.add(label1);
		panel2.add(label2);
		add(panel, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
	}

}
