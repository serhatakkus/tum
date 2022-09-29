package pgdp.searchengine.gui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pgdp.searchengine.gui.controller.SearchEngineController;

/**
 * Stellt die Leiste mit Überschrift und Buttons ganz oben im Frame dar.
 */
public class TopBar extends JPanel {
	private JLabel title;

	private JButton crawlButton;
	private JButton toAdminViewButton;
	private JButton toSearchViewButton;
	private JButton exitButton;

	/**
	 * Setzt die fünf Attribute dieser Klasse auf neue Objekte des entsprechenden
	 * Typs und fügt den Buttons geeignete ActionListener (Lambdas genügen hier
	 * völlig) hinzu. Zwischen dem Titel und den Buttons ist Platz, d.h. der Titel
	 * befindet sich ganz links, die Buttons ganz rechts in der Top-Bar.
	 *
	 * @param controller Ein Search Engine Controller (für die Events)
	 */
	public TopBar(SearchEngineController controller) {

		title = new JLabel("Search");
		this.add(title, BorderLayout.WEST);

		crawlButton = new JButton("Crawl");
		crawlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.crawlButtonPressed();
			}
		});

		toAdminViewButton = new JButton("Admin View");
		toAdminViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				title.setText("Admin View");
				setAllButtonsVisible();
				hideToAdminViewButton();
				controller.changeToAdminView();
			}
		});

		toSearchViewButton = new JButton("Back to Search");
		toSearchViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSearch();
				controller.changeToSearchView();
			}
		});

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.add(crawlButton, BorderLayout.EAST);
		this.add(toAdminViewButton, BorderLayout.EAST);
		this.add(toSearchViewButton, BorderLayout.EAST);
		this.add(exitButton, BorderLayout.EAST);

		showSearch();
	}

	/**
	 * Setzt den Text im title-Feld auf den übergebenen Text.
	 *
	 * @param titleText Neuer Titel-Text
	 */
	public void setTitle(String titleText) {
		title.setText(titleText);
	}

	/**
	 * Setzt den dem Methodennamen entsprechenden Button unsichtbar.
	 */
	public void hideCrawlButton() {
		crawlButton.setVisible(false);
	}

	public void hideToAdminViewButton() {
		toAdminViewButton.setVisible(false);
	}

	public void hideToSearchViewButton() {
		toSearchViewButton.setVisible(false);
	}

	/**
	 * Setzt alle Buttons auf sichtbar.
	 */
	public void setAllButtonsVisible() {
		crawlButton.setVisible(true);
		toAdminViewButton.setVisible(true);
		toSearchViewButton.setVisible(true);
		exitButton.setVisible(true);
	}

	private void showSearch() {
		setTitle("Search");
		setAllButtonsVisible();
		hideToSearchViewButton();
		hideCrawlButton();
	}
}
