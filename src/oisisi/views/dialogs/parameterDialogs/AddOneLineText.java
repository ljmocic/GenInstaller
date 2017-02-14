package oisisi.views.dialogs.parameterDialogs;

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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterOneLineText;

public class AddOneLineText extends JDialog {

	private static final long serialVersionUID = 1L;

	private Dimension dimensionLabel = new Dimension(150, 20);
	private Dimension dimensionTextField = new Dimension(150, 20);

	private JLabel imeParametraLabel;
	private JTextField imeParametraTextField;

	private JLabel labelText;
	private JTextField labelTextField;

	private JLabel defaultTextLabel;
	private JTextField defaultTextTextField;

	private JPanel namePanel;
	private JPanel labelPanel;
	private JPanel defaultTextPanel;
	private JPanel masterPanel;

	private JPanel checkBoxPanel;
	private JCheckBox checkBox;
	private JLabel checkBoxLabel;

	private JPanel buttonsPanel;
	private JButton potvrdiButton;
	private JButton otkaziButton;

	private ResourceBundle resourceBundle;

	public AddOneLineText() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(350, 230));
		setTitle(resourceBundle.getString("oneLineTextTitle"));
		setLocationRelativeTo(Application.getInstance());

		masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

		namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imeParametraLabel = new JLabel(resourceBundle.getString("oneLineTextLabel"));
		imeParametraTextField = new JTextField();
		imeParametraLabel.setPreferredSize(dimensionLabel);
		imeParametraTextField.setPreferredSize(dimensionTextField);
		namePanel.add(imeParametraLabel);
		namePanel.add(imeParametraTextField);

		labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelText = new JLabel(resourceBundle.getString("oneLineTextLabel2"));
		labelTextField = new JTextField();
		labelText.setPreferredSize(dimensionLabel);
		labelTextField.setPreferredSize(dimensionTextField);

		labelPanel.add(labelText);
		labelPanel.add(labelTextField);

		defaultTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		defaultTextLabel = new JLabel(resourceBundle.getString("oneLineTextLabel3"));
		defaultTextTextField = new JTextField();
		defaultTextLabel.setPreferredSize(dimensionLabel);
		defaultTextTextField.setPreferredSize(dimensionTextField);
		defaultTextPanel.add(defaultTextLabel);
		defaultTextPanel.add(defaultTextTextField);

		checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBoxLabel = new JLabel(resourceBundle.getString("oneLineTextLabel4"));
		checkBox = new JCheckBox();
		checkBoxLabel.setPreferredSize(dimensionLabel);
		checkBoxPanel.add(checkBoxLabel);
		checkBoxPanel.add(checkBox);

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));
		potvrdiButton = new JButton(resourceBundle.getString("oneLineTextConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (imeParametraTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Application.getInstance(),
							resourceBundle.getString("needsToHaveAtLeastOneChar"));
				} else {
					ParameterOneLineText oneLineText = new ParameterOneLineText(imeParametraTextField.getText(),
							labelTextField.getText(), defaultTextTextField.getText(), checkBox.isSelected());
					JPanel generatedPanel = PanelsFactory.generatePanel(oneLineText);
					oneLineText.setPanel(generatedPanel);

					Application.getInstance().addParameter(oneLineText);
					dispose();
				}
			}

		});

		otkaziButton = new JButton(resourceBundle.getString("oneLineTextCancelButton"));
		otkaziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

		buttonsPanel.add(potvrdiButton);
		buttonsPanel.add(otkaziButton);

		masterPanel.add(namePanel);
		masterPanel.add(labelPanel);
		masterPanel.add(defaultTextPanel);
		masterPanel.add(checkBoxPanel);
		add(masterPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

	}

	public String getImeParametra() {
		return imeParametraTextField.getText();
	}

	public String getLabelParameter() {
		return labelTextField.getText();
	}

	public String getDefaultTextParameter() {
		return defaultTextTextField.getText();
	}

	public boolean getIsSelected() {
		return checkBox.isSelected();
	}

}
