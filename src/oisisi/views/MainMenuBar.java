package oisisi.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import oisisi.Application;
import oisisi.controllers.menuActions.DocumentationMessage;
import oisisi.controllers.menuActions.InfoMessageAction;
import oisisi.controllers.menuActions.RegularViewAction;
import oisisi.controllers.menuActions.CompactViewAction;
import oisisi.controllers.toolbarActions.CloseInstallerAction;
import oisisi.controllers.toolbarActions.LoadInstallerAction;
import oisisi.controllers.toolbarActions.NewInstallerAction;
import oisisi.controllers.toolbarActions.SaveInstallerAction;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	private ResourceBundle resourceBundle;

	private JMenu menuLanguage;
	private JCheckBoxMenuItem menuSerbian;
	private JCheckBoxMenuItem menuEnglish;

	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;
	private JMenu menu4;

	private JMenuItem newInstaller;
	private JMenuItem loadInstaller;
	private JMenuItem saveInstaller;
	private JMenuItem closeInstaller;
	private JMenuItem exit;
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem delete;
	private JMenuItem theme1;
	private JMenuItem theme2;

	private JMenuItem docs;
	private JMenuItem version;

	public MainMenuBar() {
		initialize();
	}

	private void initialize() {
		resourceBundle = Application.getInstance().getResourceBundle();

		menu1 = new JMenu("File");
		menu1.setMnemonic(KeyEvent.VK_F);

		newInstaller = new JMenuItem("New installer");
		newInstaller.setIcon(new ImageIcon(getClass().getResource("/resources/images/New16.gif")));
		newInstaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

		loadInstaller = new JMenuItem("Load installer");
		loadInstaller.setIcon(new ImageIcon(getClass().getResource("/resources/images/Import16.gif")));
		loadInstaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		saveInstaller = new JMenuItem("Save installer");
		saveInstaller.setIcon(new ImageIcon(getClass().getResource("/resources/images/Save16.gif")));
		saveInstaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		closeInstaller = new JMenuItem("Close installer");
		closeInstaller.setIcon(new ImageIcon(getClass().getResource("/resources/images/Stop16.gif")));
		closeInstaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));

		exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		exit.setIcon(new ImageIcon(getClass().getResource("/resources/images/exit.png")));

		newInstaller.addActionListener(new NewInstallerAction());
		loadInstaller.addActionListener(new LoadInstallerAction());
		saveInstaller.addActionListener(new SaveInstallerAction());
		closeInstaller.addActionListener(new CloseInstallerAction());
		exit.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menu1.add(newInstaller);
		menu1.add(loadInstaller);
		menu1.add(saveInstaller);
		menu1.add(closeInstaller);
		menu1.add(exit);

		menu2 = new JMenu("Edit");
		menu2.setMnemonic(KeyEvent.VK_E);

		cut = new JMenuItem("Cut");
		cut.setIcon(new ImageIcon(getClass().getResource("/resources/images/Cut16.gif")));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		copy = new JMenuItem("Copy");
		copy.setIcon(new ImageIcon(getClass().getResource("/resources/images/Copy16.gif")));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

		paste = new JMenuItem("Paste");
		paste.setIcon(new ImageIcon(getClass().getResource("/resources/images/Paste16.gif")));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

		delete = new JMenuItem("Delete");
		delete.setIcon(new ImageIcon(getClass().getResource("/resources/images/Delete16.gif")));
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACKSPACE, ActionEvent.CTRL_MASK));

		menu2.add(cut);
		menu2.add(copy);
		menu2.add(paste);
		menu2.add(delete);

		menu3 = new JMenu("View");
		menu3.setMnemonic(KeyEvent.VK_V);
		theme1 = new JMenuItem("Compact Mode");
		theme1.addActionListener(new CompactViewAction());
		theme2 = new JMenuItem("Regular Mode");
		theme2.addActionListener(new RegularViewAction());
		menu3.add(theme1);
		menu3.add(theme2);

		menu4 = new JMenu("Help");
		menu4.setMnemonic(KeyEvent.VK_H);

		docs = new JMenuItem("Documenation");
		docs.setIcon(new ImageIcon(getClass().getResource("/resources/images/Information16.gif")));
		docs.addActionListener(new DocumentationMessage());

		version = new JMenuItem("Current version");
		version.setIcon(new ImageIcon(getClass().getResource("/resources/images/About16.gif")));
		version.addActionListener(new InfoMessageAction());

		menu4.add(docs);
		menu4.add(version);

		menuLanguage = new JMenu(resourceBundle.getString("menuJezik"));
		menuLanguage.setMnemonic(menuLanguage.getText().charAt(0));

		menuSerbian = new JCheckBoxMenuItem(resourceBundle.getString("menuItemSrpski"));
		menuSerbian.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("sr", "RS"));
				Application.getInstance().changeLanguage();

			}
		});

		menuLanguage.add(menuSerbian);

		menuEnglish = new JCheckBoxMenuItem(resourceBundle.getString("menuItemEngleski"));
		menuEnglish.setSelected(true);
		menuEnglish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en", "US"));
				Application.getInstance().changeLanguage();
			}
		});
		menuLanguage.add(menuEnglish);

		ButtonGroup bg = new ButtonGroup();
		bg.add(menuSerbian);
		bg.add(menuEnglish);

		add(menu1);
		add(menu2);
		add(menu3);
		add(menuLanguage);
		add(menu4);

	}

	public void updateLanguage() {
		resourceBundle = Application.getInstance().getResourceBundle();
		menuLanguage.setText(resourceBundle.getString("menuJezik"));

		menuEnglish.setText(resourceBundle.getString("menuItemEngleski"));
		menuSerbian.setText(resourceBundle.getString("menuItemSrpski"));

		menu1.setText(resourceBundle.getString("menuFile"));
		menu2.setText(resourceBundle.getString("menuEdit"));
		menu3.setText(resourceBundle.getString("menuView"));
		menu4.setText(resourceBundle.getString("menuHelp"));

		newInstaller.setText(resourceBundle.getString("menuItemNewInstaller"));
		loadInstaller.setText(resourceBundle.getString("menuItemLoadInstaller"));
		saveInstaller.setText(resourceBundle.getString("menuItemSaveInstaller"));
		closeInstaller.setText(resourceBundle.getString("menuItemCloseInstaller"));
		exit.setText(resourceBundle.getString("menuItemExit"));

		cut.setText(resourceBundle.getString("menuItemCut"));
		copy.setText(resourceBundle.getString("menuItemCopy"));
		paste.setText(resourceBundle.getString("menuItemPaste"));
		delete.setText(resourceBundle.getString("menuItemDelete"));

		theme1.setText(resourceBundle.getString("checkBoxMenuItemCompactMode"));
		theme1.addActionListener(new CompactViewAction());
		theme2.setText(resourceBundle.getString("checkBoxMenuItemRegularMode"));

		docs.setText(resourceBundle.getString("menuItemDocumenation"));
		version.setText(resourceBundle.getString("menuItemCurrentVersion"));

		menu1.setMnemonic(menu1.getText().charAt(0));
		menu2.setMnemonic(menu2.getText().charAt(0));
		menu3.setMnemonic(menu3.getText().charAt(0));
		menu4.setMnemonic(menu4.getText().charAt(0));
		menuLanguage.setMnemonic(menuLanguage.getText().charAt(0));
	}

}
