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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterImage;

public class AddImageChooser extends JDialog {

	private static final long serialVersionUID = 1L;

	private Dimension dimensionLabel = new Dimension(140, 20);

	private JPanel imagePanel;
	private JPanel buttonPanel;
	private JPanel buttonsPanel;

	private JTextField pathField;
	private JButton pretraziButton;
	private JButton potvrdiButton;
	private JButton otkaziButton;
	private String path;

	private JPanel centerPanel;

	private JPanel fileNamePanel;
	private JLabel fileNameLabel;
	private JTextField fileNameTextField;

	private String ext1;

	private ResourceBundle resourceBundle;

	GridBagConstraints c;

	public AddImageChooser() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(320, 170));
		setTitle(resourceBundle.getString("imageChooserTitle"));
		setLocationRelativeTo(Application.getInstance());
		setLayout(new BorderLayout());

		imagePanel = new JPanel(new GridBagLayout());

		pathField = new JTextField(resourceBundle.getString("imageChooserPathField"));
		c = new GridBagConstraints();
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		imagePanel.add(pathField, c);

		fileNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		fileNameLabel = new JLabel(resourceBundle.getString("imageChooserLabel"));
		fileNameLabel.setPreferredSize(dimensionLabel);

		fileNameTextField = new JTextField(resourceBundle.getString("imageChooserTextField"));
		fileNameTextField.setPreferredSize(dimensionLabel);

		fileNamePanel.add(fileNameLabel);
		fileNamePanel.add(fileNameTextField);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		buttonPanel = new JPanel();
		pretraziButton = new JButton(resourceBundle.getString("imageChooserSearchButton"));
		pretraziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();

				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setDialogTitle(resourceBundle.getString("imageParameter"));
				fileChooser.setApproveButtonText(resourceBundle.getString("loadImageParameter"));

				int returnValue = fileChooser.showOpenDialog(Application.getInstance());

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					path = fileChooser.getSelectedFile().getPath();
					pathField.setText(path);
					int dot = path.lastIndexOf(".");
					ext1 = path.substring(dot + 1);
				}
			}

		});

		buttonPanel.add(pretraziButton);
		imagePanel.add(buttonPanel);
		imagePanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("imageChooserBorderTitle")));

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		potvrdiButton = new JButton(resourceBundle.getString("imageChooserConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File directory = new File("images");

				if (!directory.exists()) {
					try {
						directory.mkdir();
					} catch (SecurityException se) {
						se.printStackTrace();
					}
				}
				copyToDestFolder("images/", pathField.getText(), fileNameTextField.getText() + "." + ext1);
				ParameterImage image = new ParameterImage(resourceBundle.getString("image"), path);
				JPanel generatedPanel = PanelsFactory.generatePanel(image);
				image.setPanel(generatedPanel);

				path = "images/" + fileNameTextField.getText() + "." + ext1;

				image.setPath(path);
				Application.getInstance().addParameter(image);
				dispose();
			}

		});

		otkaziButton = new JButton(resourceBundle.getString("imageChooserCancelButton"));
		otkaziButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		buttonsPanel.add(potvrdiButton);
		buttonsPanel.add(otkaziButton);

		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));

		centerPanel.add(imagePanel);
		centerPanel.add(fileNamePanel);

		add(centerPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
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
