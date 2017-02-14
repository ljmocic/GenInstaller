package oisisi.views.dialogs.parameterDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
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
import javax.swing.JPanel;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterDesktopShortcut;

public class AddDesktopShortcut extends JDialog {

	private static final long serialVersionUID = 1L;

	private Dimension dimensionLabel = new Dimension(150, 10);
	private JLabel choiceLabel;
	private JCheckBox checkBox;

	private JPanel buttonsPanel;
	private JPanel centerPanel;
	private JPanel choicePanel;

	private JButton potvrdiButton;
	private JButton otkaziButton;
	private String path;

	private ResourceBundle resourceBundle;

	GridBagConstraints c;

	public AddDesktopShortcut() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(240, 110));
		setTitle(resourceBundle.getString("desktopDialogTitle"));
		setLocationRelativeTo(Application.getInstance());
		setLayout(new BorderLayout());

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		potvrdiButton = new JButton(resourceBundle.getString("desktopDialogConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBox.isSelected()) {
					ParameterDesktopShortcut shortcut = new ParameterDesktopShortcut(checkBox.isSelected());
					JPanel generatedPanel = PanelsFactory.generatePanel(shortcut);
					shortcut.setPanel(generatedPanel);
					Application.getInstance().addParameter(shortcut);
					Application.getInstance().getInstallerModel().setInstallPath(path);
					dispose();
				} else {
					Application.getInstance().getInstallerModel().setShortcut(true);
					dispose();
				}
			}

		});

		otkaziButton = new JButton(resourceBundle.getString("desktopDialogCancelButton"));
		otkaziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		buttonsPanel.add(potvrdiButton);
		buttonsPanel.add(otkaziButton);

		choicePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		choicePanel.setBorder(BorderFactory.createTitledBorder(""));
		choiceLabel = new JLabel(resourceBundle.getString("desktopDialogLabel"));
		choiceLabel.setPreferredSize(dimensionLabel);
		checkBox = new JCheckBox();
		choicePanel.add(choiceLabel);
		choicePanel.add(checkBox);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(choicePanel);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

		add(centerPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

	}
}
