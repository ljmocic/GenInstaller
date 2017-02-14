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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterTextArea;

public class AddMultiLineText extends JDialog {

	private static final long serialVersionUID = 1L;

	private Dimension dimensionLabel = new Dimension(150, 20);
	private Dimension dimensionTextField = new Dimension(150, 20);

	private JLabel imeParametraLabel;
	private JTextField imeParametraTextField;

	private JLabel labelText;
	private JTextField labelTextField;

	private JPanel textAreaPanel;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	private JPanel fieldsPanel;

	private JPanel masterPanel;
	private JPanel namePanel;
	private JPanel labelPanel;

	private JPanel checkBoxPanel;
	private JCheckBox checkBox;
	private JLabel checkBoxLabel;

	private JPanel buttonsPanel;
	private JButton potvrdiButton;
	private JButton otkaziButton;

	private ResourceBundle resourceBundle;

	public AddMultiLineText() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(350, 300));
		setLocationRelativeTo(Application.getInstance());
		setTitle(resourceBundle.getString("multiLineTextTitle"));

		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());

		fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

		namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imeParametraLabel = new JLabel(resourceBundle.getString("multiLineTextLabel"));
		imeParametraTextField = new JTextField();
		imeParametraLabel.setPreferredSize(dimensionLabel);
		imeParametraTextField.setPreferredSize(dimensionTextField);
		namePanel.add(imeParametraLabel);
		namePanel.add(imeParametraTextField);

		labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelText = new JLabel(resourceBundle.getString("multiLineTextLabel2"));
		labelTextField = new JTextField();
		labelText.setPreferredSize(dimensionLabel);
		labelTextField.setPreferredSize(dimensionTextField);
		labelPanel.add(labelText);
		labelPanel.add(labelTextField);

		checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBoxLabel = new JLabel(resourceBundle.getString("multiLineTextLabel3"));
		checkBox = new JCheckBox();
		checkBoxLabel.setPreferredSize(dimensionLabel);
		checkBoxPanel.add(checkBoxLabel);
		checkBoxPanel.add(checkBox);

		fieldsPanel.add(namePanel);
		fieldsPanel.add(labelPanel);
		fieldsPanel.add(checkBoxPanel);

		textAreaPanel = new JPanel(new BorderLayout());
		textAreaPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("multiLineTextBorderTitle")));
		textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(12f));
		scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textAreaPanel.add(scrollPane);

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));

		potvrdiButton = new JButton(resourceBundle.getString("multiLineTextConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Application.getInstance(), "Morate uneti bar jedan karakter");
				} else {
					String defaultText = textArea.getText();
					ParameterTextArea textArea = new ParameterTextArea(imeParametraTextField.getText(),
							labelTextField.getText(), defaultText, checkBox.isSelected());
					JPanel generatedPanel = PanelsFactory.generatePanel(textArea);
					textArea.setPanel(generatedPanel);
					Application.getInstance().addParameter(textArea);
					dispose();
				}
			}

		});

		otkaziButton = new JButton(resourceBundle.getString("multiLineTextCancelButton"));
		otkaziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		buttonsPanel.add(potvrdiButton);
		buttonsPanel.add(otkaziButton);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

		masterPanel.add(fieldsPanel, BorderLayout.NORTH);
		masterPanel.add(buttonsPanel, BorderLayout.SOUTH);
		masterPanel.add(textAreaPanel, BorderLayout.CENTER);
		add(masterPanel);
	}
}
