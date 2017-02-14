package oisisi.views.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oisisi.Application;
import oisisi.models.InstallerPanel;

public class AddNewFrameDialog extends JDialog {

	private static final long serialVersionUID = 3838848995602730413L;

	Dimension dimLabel = new Dimension(150, 20);
	Dimension dimTextField = new Dimension(150, 20);
	JButton btnOk;
	JTextField imeTxtField;

	private ResourceBundle resourceBundle;

	public AddNewFrameDialog() {
		initialize();
		makePanel();
	}

	public void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(350, 120);
		setLocationRelativeTo(Application.getInstance());
		setTitle(resourceBundle.getString("newFrameDialogTitle"));
	}

	private void makePanel() {

		JPanel panel = new JPanel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/ic_launcher.png"));
		setIconImage(icon.getImage());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panel1());
		add(panel, BorderLayout.CENTER);
		panBottom();
	}

	private JPanel panel1() {

		resourceBundle = Application.getInstance().getResourceBundle();

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ime = new JLabel(resourceBundle.getString("newFrameDialogLabel"));
		imeTxtField = new JTextField();
		ime.setPreferredSize(dimLabel);
		imeTxtField.setPreferredSize(dimTextField);
		panel1.add(ime);
		panel1.add(imeTxtField);
		return panel1;
	}

	private JPanel panBottom() {

		resourceBundle = Application.getInstance().getResourceBundle();

		JPanel panBottom = new JPanel();
		BoxLayout box = new BoxLayout(panBottom, BoxLayout.X_AXIS);
		panBottom.setLayout(box);
		btnOk = new JButton(resourceBundle.getString("newFrameDialogConfirmButton"));
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (imeTxtField.getText().length() < 1) {
					JOptionPane.showMessageDialog(Application.getInstance(),
							resourceBundle.getString("needsToHaveAtLeastOneChar"));
				} else {
					InstallerPanel panel = new InstallerPanel();
					panel.setName(imeTxtField.getText());
					Application.getInstance().addInstallerPanel(panel);
					dispose();
				}
			}
		});
		btnOk.setPreferredSize(new Dimension(75, 25));

		JButton btnCancel = new JButton(resourceBundle.getString("newFrameDialogCancelButton"));
		btnCancel.setPreferredSize(new Dimension(75, 25));

		panBottom.add(Box.createGlue());
		panBottom.add(btnOk);
		panBottom.add(Box.createHorizontalStrut(10));
		panBottom.add(btnCancel);
		panBottom.add(Box.createHorizontalStrut(10));

		add(panBottom, BorderLayout.SOUTH);
		return panBottom;
	}

}
