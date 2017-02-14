package oisisi.views.dialogs.parameterDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterValueList;

public class AddValueList extends JDialog {

	private static final long serialVersionUID = 1L;

	private Dimension dimensionLabel = new Dimension(150, 20);
	private Dimension dimensionTextField = new Dimension(150, 20);

	private JLabel imeParametraLabel;
	private JTextField imeParametraTextField;

	private JLabel labelText;
	private JTextField labelTextField;

	private JLabel comboBoxLabel;
	private JTextField newItem;

	private JPanel namePanel;
	private JPanel labelPanel;
	private JPanel comboBoxPanel;
	private JPanel masterPanel;

	private JComboBox<String> editableComboBox;
	private JButton addButtonTextField;
	private JPanel buttonPanel;

	private JPanel buttonsPanel;
	private JButton potvrdiButton;
	private JButton otkaziButton;

	private ResourceBundle resourceBundle;

	public AddValueList() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(350, 250));
		setTitle(resourceBundle.getString("valueListTitle"));
		setLocationRelativeTo(Application.getInstance());

		masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

		editableComboBox = new JComboBox<String>();
		((JLabel) editableComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		editableComboBox.setPreferredSize(dimensionTextField);

		namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imeParametraLabel = new JLabel(resourceBundle.getString("valueListLabel"));
		imeParametraTextField = new JTextField();
		imeParametraLabel.setPreferredSize(dimensionLabel);
		imeParametraTextField.setPreferredSize(dimensionTextField);
		namePanel.add(imeParametraLabel);
		namePanel.add(imeParametraTextField);

		labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelText = new JLabel(resourceBundle.getString("valueListLabel2"));
		labelTextField = new JTextField();
		labelText.setPreferredSize(dimensionLabel);
		labelTextField.setPreferredSize(dimensionTextField);
		labelPanel.add(labelText);
		labelPanel.add(labelTextField);

		masterPanel.add(namePanel);
		masterPanel.add(labelPanel);

		comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		comboBoxLabel = new JLabel(resourceBundle.getString("valueListLabel3"));
		comboBoxLabel.setPreferredSize(dimensionLabel);
		comboBoxPanel.add(comboBoxLabel);
		comboBoxPanel.add(editableComboBox);

		newItem = new JTextField();
		newItem.setPreferredSize(dimensionTextField);
		addButtonTextField = new JButton(resourceBundle.getString("valueListAddNewTypeButton"));
		addButtonTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				editableComboBox.addItem(newItem.getText());
			}
		});

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(newItem);
		buttonPanel.add(addButtonTextField);

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));

		potvrdiButton = new JButton(resourceBundle.getString("valueListConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> list = new ArrayList<>();

				int size = editableComboBox.getItemCount();
				for (int i = 0; i < size; i++) {
					list.add(editableComboBox.getItemAt(i));
				}
				if (imeParametraTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Application.getInstance(),
							resourceBundle.getString("needsToHaveAtLeastOneChar"));
				} else {
					ParameterValueList valueList = new ParameterValueList(imeParametraTextField.getText(),
							labelTextField.getText(), list);
					JPanel generatedPanel = PanelsFactory.generatePanel(valueList);
					valueList.setPanel(generatedPanel);
					Application.getInstance().addParameter(valueList);
					dispose();
				}
			}

		});

		otkaziButton = new JButton(resourceBundle.getString("valueListCancelButton"));
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

		masterPanel.add(comboBoxPanel);
		masterPanel.add(buttonPanel);
		add(masterPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

	}

}
