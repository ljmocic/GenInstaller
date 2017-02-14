package oisisi.views.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import oisisi.Application;
import oisisi.views.dialogs.parameterDialogs.AddDesktopShortcut;
import oisisi.views.dialogs.parameterDialogs.AddFileChooser;
import oisisi.views.dialogs.parameterDialogs.AddHelp;
import oisisi.views.dialogs.parameterDialogs.AddImageChooser;
import oisisi.views.dialogs.parameterDialogs.AddInstallationFile;
import oisisi.views.dialogs.parameterDialogs.AddMultiLineText;
import oisisi.views.dialogs.parameterDialogs.AddOneLineText;
import oisisi.views.dialogs.parameterDialogs.AddValueList;

@SuppressWarnings("serial")
public class SelectParameterTypeDialog extends JDialog {

	private JComboBox<String> comboBoxTypes;
	private JPanel comboBoxPanel;
	private JLabel parameterTypeLabel;
	private Dimension dimensionLabel = new Dimension(150, 20);
	private Dimension dimensionTextField = new Dimension(150, 20);

	private JPanel buttonsPanel;
	private JButton potvrdiButton;
	private JButton otkaziButton;

	private ResourceBundle resourceBundle;

	public SelectParameterTypeDialog() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();
		String[] paramTypes = { resourceBundle.getString("comboBoxChooseParameterType"),
				resourceBundle.getString("comboBoxOneLineText"), resourceBundle.getString("comboBoxMultiLineText"),
				resourceBundle.getString("comboBoxImageChooser"), resourceBundle.getString("comboBoxValueList"),
				resourceBundle.getString("comboBoxDesktopShortcut"), resourceBundle.getString("installationFileTitle"),
				resourceBundle.getString("comboBoxFileChooser"), resourceBundle.getString("comboBoxHelp") };

		setLayout(new BorderLayout());
		setSize(new Dimension(350, 120));
		setLocationRelativeTo(Application.getInstance());
		setTitle(resourceBundle.getString("selectTypeDialogTitle"));

		comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		parameterTypeLabel = new JLabel(resourceBundle.getString("selectTypeDialogTypeOfParameter"));
		comboBoxTypes = new JComboBox<String>(paramTypes);
		((JLabel) comboBoxTypes.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		// comboBoxTypes.addActionListener(this);
		parameterTypeLabel.setPreferredSize(dimensionLabel);
		comboBoxTypes.setPreferredSize(dimensionTextField);
		comboBoxPanel.add(parameterTypeLabel);
		comboBoxPanel.add(comboBoxTypes);

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));
		potvrdiButton = new JButton(resourceBundle.getString("selectTypeDialogConfirmButton"));
		otkaziButton = new JButton(resourceBundle.getString("selectTypeDialogCancelButton"));
		potvrdiButton.addActionListener(new MyActionListener());
		buttonsPanel.add(potvrdiButton);
		buttonsPanel.add(otkaziButton);
		otkaziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

		add(comboBoxPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String parameterName = comboBoxTypes.getSelectedItem().toString();

			if (parameterName.equals(resourceBundle.getString("comboBoxOneLineText"))) {
				AddOneLineText dialog = new AddOneLineText();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("comboBoxMultiLineText"))) {
				AddMultiLineText dialog = new AddMultiLineText();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("comboBoxValueList"))) {
				AddValueList dialog = new AddValueList();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("comboBoxImageChooser"))) {
				AddImageChooser dialog = new AddImageChooser();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("comboBoxDesktopShortcut"))) {
				AddDesktopShortcut dialog = new AddDesktopShortcut();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("comboBoxFileChooser"))) {
				AddFileChooser dialog = new AddFileChooser();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("comboBoxHelp"))) {
				AddHelp dialog = new AddHelp();
				dialog.setVisible(true);
			} else if (parameterName.equals(resourceBundle.getString("installationFileTitle"))) {
				AddInstallationFile dialog = new AddInstallationFile();
				dialog.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(Application.getInstance(),
						resourceBundle.getString("showMessageDialog1"));
			}

			dispose();

		}
	}

}
