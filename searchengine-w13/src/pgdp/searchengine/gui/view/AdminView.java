package pgdp.searchengine.gui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pgdp.searchengine.gui.controller.AdminController;

/**
 * Stellt die Admin View dar. Sie ist als JScrollPane implementiert und zeigt 1.
 * alle bisher vom Admin Controller geladenen Dokumente untereinander in Form
 * von AbstractDocumentPane-Objekten 2. darunter noch einen Button zum Laden
 * weiterer Dokumente an.
 */
public class AdminView extends JScrollPane {

	JPanel mainPanel;
	JPanel docPanel;
	JButton btnMore;

	private AdminController adminController;

	/**
	 * Erzeugt 1. ein Panel (o.Ä.) für die AbstractDocumentPanes 2. den
	 * Load-More-Button
	 */
	public AdminView() {
		mainPanel = new JPanel();
		docPanel = new JPanel();
		btnMore = new JButton("Load More");
		btnMore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminController.loadNextBatch();
			}
		});

		mainPanel.add(docPanel, BorderLayout.CENTER);
		mainPanel.add(btnMore, BorderLayout.SOUTH);
		this.add(mainPanel);
		// this.add(btnMore);
		this.verticalScrollBar.setVisible(true);
	}

	public void setAdminController(AdminController adminController) {
		this.adminController = adminController;
	}

	/**
	 * Fügt das übergebene AbstractDocumentPane-Objekt unten an die bereits
	 * vorhandenen an und updatet dann die Anzeige (mit einem Call der Methode
	 * 'updateUI()').
	 */
	public void addDocumentPane(AbstractDocumentPane documentPane) {
		docPanel.add(documentPane);
	}

	/**
	 * Löscht alle angezeigten AbstractDocumentPane-Objekte aus der View (nicht aber
	 * den Load-More-Button).
	 */
	public void clear() {
		for (Component comp : docPanel.getComponents()) {
			docPanel.remove(comp);
		}
		docPanel.updateUI();
	}
}
