package pgdp.searchengine.gui.controller;

import java.awt.event.WindowEvent;

import pgdp.searchengine.gui.view.CrawlDialog;
import pgdp.searchengine.gui.view.SearchEngineView;

public class SearchEngineController {
	private SearchController searchController;
	private ResultController resultController;
	private AdminController adminController;

	private SearchEngineView searchEngineView;

	public void setSearchEngineView(SearchEngineView searchEngineView) {
		this.searchEngineView = searchEngineView;
	}

	public void setAdminController(AdminController adminController) {
		this.adminController = adminController;
	}

	public void setResultController(ResultController resultController) {
		this.resultController = resultController;
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}

	/**
	 * Arbeitet die übergebene Such-Anfrage ab. Teilt dabei dem Result Controller
	 * mit, die entsprechenden Ergebnisse zu laden und wechselt dann die aktuell
	 * angezeigte View von der Search View zur Result View.
	 *
	 * @param query Der Such-String
	 */
	public void processQuery(String query) {
		resultController.loadResultsFor(query);
		changeToResultView();
	}

	public void changeToAdminView() {
		searchEngineView.displayAdminView();
	}

	public void changeToResultView() {
		searchEngineView.displayResultView();
	}

	public void changeToSearchView() {
		searchEngineView.displaySearchView();
	}

	/**
	 * Wird aufgerufen, wenn der Crawl-Button, der in der Admin View in der Top-Bar
	 * zu sehen ist, gedrückt wurde. Erstellt einen neuen CrawlDialog und macht ihn
	 * sichtbar.
	 */
	public void crawlButtonPressed() {
		CrawlDialog crawl = new CrawlDialog(adminController);
        crawl.setLocationRelativeTo(crawl.getParent());
        crawl.setSize(360, 200);
		crawl.setVisible(true);
	}

	public void exitButtonPressed() {
		searchEngineView.dispatchEvent(new WindowEvent(searchEngineView, WindowEvent.WINDOW_CLOSING));
	}
}
