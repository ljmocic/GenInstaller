package oisisi.views.dialogs.parameterDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import oisisi.Application;
import oisisi.models.parameterTypes.PanelsFactory;
import oisisi.models.parameterTypes.ParameterHelp;

public class AddHelp extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
	private JScrollPane scrollPane;

	private JPanel textAreaPanel;

	private JPanel buttonsPanel;
	private JButton potvrdiButton;
	private JButton otkaziButton;

	private ResourceBundle resourceBundle;

	public AddHelp() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(new Dimension(350, 320));
		setLocationRelativeTo(Application.getInstance());
		setTitle(resourceBundle.getString("helpDialogTitle"));

		textAreaPanel = new JPanel(new BorderLayout());
		textAreaPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("helpDialogTextArea")));
		textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(12f));
		scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textAreaPanel.add(scrollPane);

		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(""));

		potvrdiButton = new JButton(resourceBundle.getString("helpDialogConfirmButton"));
		potvrdiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ParameterHelp parameterHelp = new ParameterHelp(textArea.getText().toString());

				JPanel generatedPanel = PanelsFactory.generatePanel(parameterHelp);
				parameterHelp.setPanel(generatedPanel);

				Application.getInstance().addParameter(parameterHelp);
				dispose();
			}
		});
		otkaziButton = new JButton(resourceBundle.getString("helpDialogCancelButton"));
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

		add(buttonsPanel, BorderLayout.SOUTH);
		add(textAreaPanel, BorderLayout.CENTER);
	}
}
