package oisisi.views.dialogs.parameterDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterFileChooser;

public class AddFileChooser extends JDialog {

	private static final long serialVersionUID = 1L;

	private Dimension dimensionLabel = new Dimension(140, 20);

	private JPanel pathPanel;
	private JPanel buttonPanel;
	private JPanel buttonsPanel;

	private JTextField pathField;
	private JButton pretraziButton;
	private JButton potvrdiButton;
	private JButton otkaziButton;

	private JPanel fileNamePanel;
	private JLabel fileNameLabel;
	private JTextField fileNameTextField;

	private JPanel centerPanel;

	private ResourceBundle resourceBundle;

	GridBagConstraints c;

	public AddFileChooser() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(320, 150));
		setTitle(resourceBundle.getString("fileChooserTitle"));
		setLocationRelativeTo(Application.getInstance());
		setLayout(new BorderLayout());

		pathPanel = new JPanel(new GridBagLayout());

		pathField = new JTextField(resourceBundle.getString("fileChooserPathField"));
		c = new GridBagConstraints();
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		pathPanel.add(pathField, c);

		buttonPanel = new JPanel();
		pretraziButton = new JButton(resourceBundle.getString("fileChooserSearchButton"));

		buttonPanel.add(pretraziButton);
		pathPanel.add(buttonPanel);
		pathPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("fileChooserTitleBorder")));

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));
		potvrdiButton = new JButton(resourceBundle.getString("fileChooserConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ako nije znaci da je to destinacija koju bira korisnik
				ParameterFileChooser file = new ParameterFileChooser(resourceBundle.getString("fileChooser"),
						pathField.getText(), false);
				JPanel generatedPanel = PanelsFactory.generatePanel(file);
				file.setPanel(generatedPanel);
				Application.getInstance().addParameter(file);
				dispose();

			}

		});

		otkaziButton = new JButton(resourceBundle.getString("fileChooserCancelButton"));
		otkaziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		fileNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fileNameLabel = new JLabel(resourceBundle.getString("fileChooserLabel"));
		fileNameLabel.setPreferredSize(dimensionLabel);

		fileNameTextField = new JTextField(resourceBundle.getString("fileChooserTextField"));
		fileNameTextField.setPreferredSize(dimensionLabel);

		fileNamePanel.add(fileNameLabel);
		fileNamePanel.add(fileNameTextField);

		buttonsPanel.add(potvrdiButton);
		buttonsPanel.add(otkaziButton);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		add(centerPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

		centerPanel.add(pathPanel);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());

	}

	public boolean copyToDestFolder(String destPath, String sourcePath, String fileName) {

		boolean result = true;

		Path source = Paths.get(sourcePath);
		Path destination = Paths.get(destPath + File.separatorChar + fileName);

		try {
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}

		return result;

	}

}
