package pgdp.searchengine.gui.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pgdp.searchengine.gui.controller.SearchController;

/**
 * Stellt die Search View dar. Sie enthält 1. den Text "PinguPinguLos" fett über
 * dem Suchfeld und dem Search-Button. 2. das Suchfeld 3. den Search-Button
 */
public class SearchView extends JPanel {

	private SearchController searchController;

	/**
	 * Erzeugt 1. Den Text "PinguPinguLos" in einer großen, fetten Schrift über den
	 * beiden anderen Komponenten. 2. Ein Text-Feld, in das man Suchanfragen
	 * eingeben kann. 3. Rechts neben 2. einen Search-Button, auf dessen Klick hin
	 * die Suche mit dem aktuell in 2. stehenden String abgeschickt wird.
	 */
	public SearchView() {
		JLabel label = new JLabel("PinguPinguLos");
		Font font = new Font("Times New Roman", Font.BOLD, 36);
		label.setFont(font);

		TextField txtSearch = new TextField();
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchController.executeSearch(txtSearch.getText());
			}
		});

		this.add(label, BorderLayout.NORTH);
		this.add(txtSearch, BorderLayout.CENTER);
		this.add(btnSearch, BorderLayout.EAST);
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}

}
