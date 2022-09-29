package pgdp.searchengine.gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import pgdp.searchengine.gui.controller.AdminController;

/**
 * Stellt den Dialog dar, der aufpoppt, wenn man in der Admin View den
 * Crawl-Button drückt. Es wird erstens nach einer Start-Adresse zum crawlen
 * und zweitens nach der Anzahl an zu crawlenden Seiten gefragt. Der Nutzer hat
 * die Möglichkeit, auf einen von zwei Buttons zu drücken: 1. Cancel: Bricht
 * das Crawling ab, bevor es begonnen hat. 2. Crawl: Crawlt mit den aktuell
 * eingegebenen Parametern
 *
 * Der Dialog zeigt sich nicht selbst an (ruft also nicht 'setVisible(true)'
 * auf), sondern wartet, bis er von außen her (= in
 * SearchEngineController.crawlButtonPressed() dann) sichtbar gesetzt wird.
 */
public class CrawlDialog extends JDialog {

	/**
	 * Erzeugt den Dialog wie oben beschrieben. Mittels des 'adminController's
	 * können die Buttons nach außen hin kommunizieren.
	 *
	 * @param adminController Ein Admin Controller
	 */
	public CrawlDialog(AdminController adminController) {
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setSize(200, 30);
		lblAmount.setAlignmentX(Container.LEFT_ALIGNMENT);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setSize(200, 30);
		lblAddress.setAlignmentX(Container.LEFT_ALIGNMENT);

		SpinnerModel spnModel = new SpinnerNumberModel(1, 1, 10, 1);
		JSpinner spnAmount = new JSpinner(spnModel);
		spnAmount.setSize(200, 30);
		spnAmount.setAlignmentX(Container.CENTER_ALIGNMENT);

		TextField txtAddress = new TextField();
		txtAddress.setSize(200, 30);

		JPanel panelMain = new JPanel();
		BoxLayout layout = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(layout);
		panelMain.setPreferredSize(new Dimension(330, 100));

		panelMain.add(lblAmount);
		panelMain.add(spnAmount);
		panelMain.add(lblAddress);
		panelMain.add(txtAddress);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getOwner().setEnabled(true);
				CrawlDialog.this.dispose();
			}
		});
		
		JButton btnCrawl = new JButton("Crawl");
		btnCrawl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adminController.crawlFromAddress(Integer.parseInt(spnAmount.getValue().toString()), txtAddress.getText());
			}
		});
		
		JPanel panelButton = new JPanel();
		panelButton.add(btnCancel);
		panelButton.add(btnCrawl);
		
		this.getContentPane().add(panelMain, BorderLayout.CENTER);
		this.getContentPane().add(panelButton, BorderLayout.SOUTH);
		this.pack();
	}

}
