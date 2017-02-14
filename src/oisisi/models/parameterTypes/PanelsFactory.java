package oisisi.models.parameterTypes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import oisisi.Application;
import oisisi.models.Parameter;

public class PanelsFactory {

	private static PanelsFactory instance = null;

	private static String installDestination;

	private PanelsFactory() {

	}

	public static PanelsFactory getInstance() {
		if (instance == null) {
			instance = new PanelsFactory();
		}
		return instance;
	}

	public static JPanel generatePanel(Parameter parameter) {

		String parameterType = parameter.getType();

		if (parameterType.equals(ParameterOneLineText.parameterType)) {

			ParameterOneLineText parameterOneLineTextEditable = (ParameterOneLineText) parameter;
			return getOneLineTextPanel(parameterOneLineTextEditable);

		} else if (parameterType.equals(ParameterTextArea.parameterType)) {

			ParameterTextArea parameterTextArea = (ParameterTextArea) parameter;
			return generateTextAreaPanel(parameterTextArea);

		} else if (parameterType.equals(ParameterImage.parameterType)) {

			ParameterImage parameterImage = (ParameterImage) parameter;
			return generateParameterImagePanel(parameterImage);

		} else if (parameterType.equals(ParameterValueList.parameterType)) {

			ParameterValueList parameterValueList = (ParameterValueList) parameter;
			return generateValueListPanel(parameterValueList);

		} else if (parameterType.equals(ParameterDesktopShortcut.parameterType)) {

			ParameterDesktopShortcut param = (ParameterDesktopShortcut) parameter;
			return generateDesktopShortcutPanel(param);

		} else if (parameterType.equals(ParameterFileChooser.parameterType)) {

			ParameterFileChooser parameterFileChooser = (ParameterFileChooser) parameter;
			return generateFileChooserPanel(parameterFileChooser);

		} else if (parameterType.equals(ParameterHelp.parameterType)) {

			ParameterHelp parameterHelp = (ParameterHelp) parameter;
			return generateHelpPanel(parameterHelp);

		}
		return null;
	}

	private static JPanel generateHelpPanel(ParameterHelp parameterHelp) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton helpButton = new JButton("Help Button");
		JTextArea textArea = new JTextArea(parameterHelp.getHelpText());
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		JLabel labela = new JLabel("Click button for help");
		labela.setPreferredSize(new Dimension(140, 20));
		JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		helpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("GenInstaller");
				dialog.setSize(new Dimension(400, 300));
				dialog.setLocationRelativeTo(Application.getInstance());
				dialog.setLayout(new BorderLayout());
				dialog.add(scrollPane);
				scrollPane.setBorder(BorderFactory.createTitledBorder("Help"));
				dialog.setVisible(true);

				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();

					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(okButton);
				dialog.add(buttonPanel, BorderLayout.SOUTH);

			}
		});

		panel.add(labela);
		panel.add(helpButton);
		return panel;
	}

	private static JPanel generateFileChooserPanel(ParameterFileChooser parameterFileChooser) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setMaximumSize(new Dimension(2000, 500));
		panel.setMinimumSize(new Dimension(200, 200));
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);

		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (textField.getText().length() == 0) {
					textField.setText("");
				}

			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(textField, c);

		JPanel buttonPanel = new JPanel();
		JButton buttonPretrazi = new JButton("Pretrazi");
		parameterFileChooser.setPath(Application.getInstance().getInstallerModel().getInstallDestionation());
		// default save location
		textField.setText(Application.getInstance().getInstallerModel().getInstallDestionation());

		buttonPretrazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					installDestination = chooser.getSelectedFile().getAbsolutePath();
					Application.getInstance().getInstallerModel().setInstallDestination(installDestination);
					textField.setText(installDestination);
					parameterFileChooser.setPath(installDestination);
				}

			}
		});
		buttonPanel.add(buttonPretrazi);
		panel.add(buttonPanel);

		panel.setBorder(BorderFactory.createTitledBorder("Destination folder"));

		return panel;
	}

	private static JPanel generateDesktopShortcutPanel(ParameterDesktopShortcut param) {

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelText = new JLabel("Desktop Shortcut");
		Dimension dimensionLabel = new Dimension(135, 20);
		labelText.setPreferredSize(dimensionLabel);
		JCheckBox checkBox = new JCheckBox();
		checkBox.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				boolean checkBoxValue = false;
				if (checkBox.isSelected()) {
					checkBoxValue = true;
				}
				Application.getInstance().getInstallerModel().setShortcut(checkBoxValue);
			}

		});
		panel.add(labelText);
		panel.add(checkBox);

		return panel;
	}

	private static JPanel generateValueListPanel(ParameterValueList parameterValueList) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel(parameterValueList.getLabelText());
		label.setPreferredSize(new Dimension(140, 20));

		JComboBox<String> valueComboBox = new JComboBox<>();
		((JLabel) valueComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		valueComboBox.setPreferredSize(new Dimension(150, 20));

		for (String value : parameterValueList.getValues()) {
			valueComboBox.addItem(value);
		}

		panel.add(label);
		panel.add(valueComboBox);

		return panel;
	}

	private static JPanel generateParameterImagePanel(ParameterImage parameterImage) {
		class ImagePanel extends JPanel {

			private static final long serialVersionUID = -3768390756070937076L;

			private Image image;

			public ImagePanel(Image image) {
				this.image = image;
			}

			public void paintComponent(Graphics g) {
				g.drawImage(image, (int) (this.getSize().getWidth() - image.getWidth(null)) / 2,
						(int) (this.getSize().getHeight() - image.getHeight(null)) / 2, null);
			}

		}

		ImagePanel imagePanel = new ImagePanel(new ImageIcon(parameterImage.getPath()).getImage());
		imagePanel.setPreferredSize(new Dimension(150, 50));
		return imagePanel;
	}

	private static JPanel generateTextAreaPanel(ParameterTextArea parameterTextArea) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(parameterTextArea.getLabelString());
		label.setPreferredSize(new Dimension(150, 20));

		label.setBorder(new EmptyBorder(7, 7, 7, 7));

		JTextArea textArea = new JTextArea(parameterTextArea.getTextAreaDefaultText());
		textArea.setFont(textArea.getFont().deriveFont(12f));
		textArea.setEditable(parameterTextArea.isEditable());
		JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(200, 120));

		parameterTextArea.setTextArea(textArea);

		panel.add(label, BorderLayout.WEST);
		panel.add(scrollPane, BorderLayout.CENTER);

		JLabel granicniLabel = new JLabel();
		granicniLabel.setPreferredSize(new Dimension(20, 20));
		panel.add(granicniLabel, BorderLayout.EAST);

		return panel;
	}

	private static JPanel getOneLineTextPanel(ParameterOneLineText parameterOneLineTextEditable) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(parameterOneLineTextEditable.getLabelText());
		label.setPreferredSize(new Dimension(140, 20));
		JTextField field = new JTextField(parameterOneLineTextEditable.getTextFieldDefaultText());
		field.setPreferredSize(new Dimension(150, 20));
		field.setEditable(parameterOneLineTextEditable.isEditable());
		parameterOneLineTextEditable.setField(field);
		panel.add(label);
		panel.add(field);
		return panel;
	}

}
