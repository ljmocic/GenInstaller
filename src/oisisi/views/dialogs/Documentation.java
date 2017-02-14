package oisisi.views.dialogs;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oisisi.Application;

@SuppressWarnings("serial")
public class Documentation extends JDialog {

	private JPanel panel, panel1;
	@SuppressWarnings("unused")
	private JLabel label, labelEN, labelSR;
	@SuppressWarnings("unused")
	private String txtEN, txtSR;

	private ResourceBundle resourceBundle;

	public Documentation() {
		initialize();
	}

	private void initialize() {

		resourceBundle = Application.getInstance().getResourceBundle();

		setSize(800, 600);
		setLocationRelativeTo(Application.getInstance());
		setTitle(resourceBundle.getString("documentationTitle"));
		makePanel();

	}

	private JPanel makePanel() {

		resourceBundle = Application.getInstance().getResourceBundle();

		panel = new JPanel();
		label = new JLabel();
		label.setText(resourceBundle.getString("documentationLabel"));

		panel1 = new JPanel();

		labelEN = new JLabel();

		txtEN = resourceBundle.getString("documentationLine1");
		txtEN = txtEN + resourceBundle.getString("documentationLine2");
		txtEN = txtEN + resourceBundle.getString("documentationLine3");
		txtEN = txtEN + resourceBundle.getString("documentationLine4");

		txtEN = txtEN + resourceBundle.getString("documentationLine5");
		txtEN = txtEN + resourceBundle.getString("documentationLine6");
		txtEN = txtEN + resourceBundle.getString("documentationLine7");
		txtEN = txtEN + resourceBundle.getString("documentationLine8");

		txtEN = txtEN + resourceBundle.getString("documentationLine9");
		txtEN = txtEN + resourceBundle.getString("documentationLine10");
		txtEN = txtEN + resourceBundle.getString("documentationLine11");
		txtEN = txtEN + resourceBundle.getString("documentationLine12");
		labelEN.setText(txtEN);

		// srpski prevod
		/*
		 * labelSR = new JLabel();
		 * 
		 * 
		 * txtSR = "<html><h3>Operacije menija</h3>"; txtSR = txtSR +
		 * "Dokument meni olaksava upotrebu instalera. On sadrzi opcije kao sto su pravljenje novog instalera, ucitavanje instalera i cuvanje instalera kojeg ste napravili.<br> Omoguceno je zatvaranje trenutnog instalera i izlazenje iz programa."
		 * ; txtSR = txtSR +
		 * "<br>Uredi meni omogucava uredjivanje Vaseg instalera i sadrzi osnovne funkcije kao sto su iseci, kopiraj, nalepi i obrisi."
		 * ; txtSR = txtSR +
		 * "<br>Prikaz meni omogucava Vam da promenite temu instalatora. <br>U pomoc meniju se nalaze informacije o trenutnoj verziji programa i dokumentaciji programa. Jezik meni omogucava odabir jezika."
		 * ;
		 * 
		 * txtSR = txtSR + "<h3>Operacije s linijom alatki</h3>"; txtSR = txtSR
		 * +
		 * "Na liniji alatki se nalaze najcesce koriscene funkcije instalera. To su pravljenje novog ili otvaranje postojeceg instalera.<br> Takodje omogucava dodavanje ili uklanjanje kako okvira, tako i parametara. "
		 * ; txtSR = txtSR + "<h3>Radni deo i pretpregled</h3>"; txtSR = txtSR +
		 * "Radni deo se nalazi na levoj strani instalera dok je pretpregled na desnoj. U radnom delu omoguceno je dodavanje novih okvira<br> kao i dodavanje parametara u sklopu okvira.<br>"
		 * +
		 * "Pretpregled Vam prikazuje trenutni okvir i omogucava kretanje kroz instaler pomocu dugmadi dalje i nazad."
		 * ;
		 * 
		 * txtSR = txtSR + "<h3>Testiranje i izvoz</h3>"; txtSR = txtSR +
		 * "Pomocu dugmadi text i izvoz omoguceno je testiranje Vaseg instalera kao i izvoz odradjenog instalera."
		 * ; txtSR = txtSR + "<h3>Tipovi parametara</h3>"; txtSR = txtSR +
		 * "Ponudjeni su sledeci tipovi parametara: jednolinijski ili viselinijski tekst, unos slike ili fajla, liste vrednosti i desktop ikonica.<br> Takodje omoguceno je pisanje sopstvene ''Pomoc'' sekcije."
		 * ;
		 * 
		 * 
		 * labelSR.setText(txtSR);
		 */

		panel.add(label);
		panel1.add(labelEN);
		// panel1.add(labelSR);
		add(panel, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		return panel;
	}
}
