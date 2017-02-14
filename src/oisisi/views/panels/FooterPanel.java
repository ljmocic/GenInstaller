package oisisi.views.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import oisisi.Application;
import oisisi.controllers.footerActions.ExportAction;
import oisisi.controllers.footerActions.TestAction;

@SuppressWarnings("serial")
public class FooterPanel extends JPanel {

	private JButton testButton;
	private JButton exportButton;

	public FooterPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));

		testButton = new JButton("Test");
		testButton.addActionListener(new TestAction());
		testButton.setFont(testButton.getFont().deriveFont(Font.BOLD));
		testButton.setFont(testButton.getFont().deriveFont(12f));
		testButton.setPreferredSize(new Dimension(70, 26));

		exportButton = new JButton("Export");
		exportButton.addActionListener(new ExportAction());
		exportButton.setPreferredSize(new Dimension(80, 26));
		exportButton.setFont(exportButton.getFont().deriveFont(Font.BOLD));
		exportButton.setFont(exportButton.getFont().deriveFont(12f));

		add(testButton);
		add(exportButton);
	}

	public void updateLanguage() {
		testButton.setText(Application.getInstance().getResourceBundle().getString("buttonTest"));
		exportButton.setText(Application.getInstance().getResourceBundle().getString("buttonExport"));
	}

}
