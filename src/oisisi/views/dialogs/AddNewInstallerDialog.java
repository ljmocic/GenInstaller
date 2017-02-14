package oisisi.views.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oisisi.Application;

@SuppressWarnings("serial")
public class AddNewInstallerDialog extends JDialog {

	private Dimension dimensionLabel = new Dimension(150, 20);
	private Dimension dimensionTextField = new Dimension(150, 20);

	private JPanel buttonPanel;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField imeTextField;

	private JLabel labelKompanije;
	private JTextField textFieldKompanije;

	private ResourceBundle resourceBundle;

	public AddNewInstallerDialog() {
		initialize();
	}

	public void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(350, 170);
		setLocationRelativeTo(Application.getInstance());
		setTitle(resourceBundle.getString("newInstallerDialogTitle"));
		makePanel();
	}

	private void makePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panel1());
		panel.add(imeKompanijePanel());

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

		add(panel, BorderLayout.CENTER);
		panBottom();
	}

	private JPanel imeKompanijePanel() {

		resourceBundle = Application.getInstance().getResourceBundle();

		JPanel panelKompanije = new JPanel(new FlowLayout(FlowLayout.LEFT));

		labelKompanije = new JLabel(resourceBundle.getString("newInstallerDialogLabel"));
		labelKompanije.setPreferredSize(dimensionLabel);

		textFieldKompanije = new JTextField();
		textFieldKompanije.setPreferredSize(dimensionTextField);

		panelKompanije.add(labelKompanije);
		panelKompanije.add(textFieldKompanije);

		return panelKompanije;
	}

	private JPanel panel1() {

		resourceBundle = Application.getInstance().getResourceBundle();

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ime = new JLabel(resourceBundle.getString("newInstallerDialogLabel2"));
		imeTextField = new JTextField();
		ime.setPreferredSize(dimensionLabel);
		imeTextField.setPreferredSize(dimensionTextField);

		panel1.add(ime);
		panel1.add(imeTextField);
		return panel1;
	}

	private void panBottom() {

		resourceBundle = Application.getInstance().getResourceBundle();

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBorder(BorderFactory.createTitledBorder(""));
		buttonOK = new JButton(resourceBundle.getString("newInstallerDialogConfirmButton"));
		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Application.getInstance().getInstallerModel().addNewInstaller(imeTextField.getText(),
						resourceBundle.getString("newInstallerDialogNameOfCompany"));
				dispose();
			}
		});
		buttonOK.setPreferredSize(new Dimension(75, 25));

		buttonCancel = new JButton(resourceBundle.getString("newInstallerDialogCancelButton"));
		buttonCancel.setPreferredSize(new Dimension(75, 25));

		buttonPanel.add(buttonOK);
		buttonPanel.add(buttonCancel);

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent arg0) {
		Application.getInstance().getInstallerModel().addNewInstaller(imeTextField.getText(),
				resourceBundle.getString("newInstallerDialogNameOfCompany"));
		dispose();
	}

}
