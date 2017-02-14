package oisisi.views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import oisisi.Application;
import oisisi.controllers.toolbarActions.AddFrameAction;
import oisisi.controllers.toolbarActions.AddParameterAction;
import oisisi.controllers.toolbarActions.LoadInstallerAction;
import oisisi.controllers.toolbarActions.NewInstallerAction;
import oisisi.controllers.toolbarActions.RemoveFrameAction;
import oisisi.controllers.toolbarActions.RemoveParameterAction;

@SuppressWarnings("serial")
public class MainToolbar extends JToolBar {

	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	Dimension dimension;

	public MainToolbar() {
		super(SwingConstants.HORIZONTAL);
		initialize();
	}

	private void initialize() {
		dimension = new Dimension(7, 7);

		button1 = new JButton();
		button1.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipNewProject"));
		button1.setIcon(new ImageIcon(getClass().getResource("/resources/images/add.png")));
		button1.addActionListener(new NewInstallerAction());
		add(button1);

		addSeparator(dimension);

		button2 = new JButton();
		button2.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipOpenProject"));
		button2.setIcon(new ImageIcon(getClass().getResource("/resources/images/open.png")));
		button2.addActionListener(new LoadInstallerAction());
		add(button2);

		addSeparator(dimension);

		button3 = new JButton();
		button3.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipAddFrame"));
		button3.setIcon(new ImageIcon(getClass().getResource("/resources/images/frame.png")));
		button3.addActionListener(new AddFrameAction());
		add(button3);

		addSeparator(dimension);

		button4 = new JButton();
		button4.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipRemoveFrame"));
		button4.setIcon(new ImageIcon(getClass().getResource("/resources/images/remove-frame.png")));
		button4.addActionListener(new RemoveFrameAction());
		add(button4);

		addSeparator(dimension);

		button5 = new JButton();
		button5.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipAddParameter"));
		button5.setIcon(new ImageIcon(getClass().getResource("/resources/images/add_param.png")));
		button5.addActionListener(new AddParameterAction());
		add(button5);

		addSeparator(dimension);

		button6 = new JButton();
		button6.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipRemoveParameter"));
		button6.setIcon(new ImageIcon(getClass().getResource("/resources/images/remove_param.png")));
		button6.addActionListener(new RemoveParameterAction());
		add(button6);
	}

	public void updateLanguage() {
		button1.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipNewProject"));
		button2.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipOpenProject"));
		button3.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipAddFrame"));
		button4.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipRemoveFrame"));
		button5.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipAddParameter"));
		button6.setToolTipText(Application.getInstance().getResourceBundle().getString("toolTipRemoveParameter"));
	}
}
