package pgdp.searchengine.gui.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pgdp.searchengine.gui.controller.SearchEngineController;

/**
 * Das Haupt-Frame. Besteht aus einer Top-Bar oben, in der der Titel der
 * aktuellen View und einige Buttons angezeigt werden, sowie das Haupt-Fenster
 * darunter, in das die jeweils angezeigte View (Admin View, Result View oder
 * Search View) geladen wird.
 */
public class SearchEngineView extends JFrame {
	private SearchEngineController searchEngineController;

	private TopBar topBar;
	private JPanel body;

	private AdminView adminView;
	private ResultView resultView;
	private SearchView searchView;

	// TODO: Evtl. mehr Attribute ?? (nicht unbedingt nötig)

	public SearchEngineView() {
		super("PinguPinguLos");
	}

	public void setSearchEngineController(SearchEngineController searchEngineController) {
		this.searchEngineController = searchEngineController;
	}

	/**
	 * Initialisiert die Search Engine View mit einer neuen Top-Bar in 'topBar' und
	 * einem Panel in 'body', in dem, je nach Programmzustand, je eines der drei
	 * übergebenen Views angezeigt werden kann. Als Erstes wird die dabei Search
	 * View angezeigt. Setzt außerdem die vier Attribute 'searchEngineController',
	 * 'adminView', 'resultView' und 'searchView' auf die entsprechenden Parameter.
	 *
	 * @param searchEngineController Ein Search Engine Controller
	 * @param adminView              Eine Admin View
	 * @param resultView             Eine Result View
	 * @param searchView             Eine Search View
	 */
	public void init(SearchEngineController searchEngineController, AdminView adminView, ResultView resultView,
			SearchView searchView) {
		// TODO: Implementieren
		this.searchEngineController = searchEngineController;
		this.adminView = adminView;
		this.searchView = searchView;
		this.resultView = resultView;

		this.topBar = new TopBar(searchEngineController);
		this.body = new JPanel(new CardLayout());

		body.add(adminView);
		body.add(resultView);
		body.add(searchView);

		this.add(topBar, BorderLayout.NORTH);
		this.add(body, BorderLayout.CENTER);

		displaySearchView();
	}

	/**
	 * Diese drei Methoden zeigen die dem Namen entsprechende View an. Dabei wird
	 * der Titel in der Top-Bar korrekt gesetzt und die Buttons in ihr, die in
	 * dieser View nicht angezeigt werden sollen, werden versteckt.
	 */
	public void displayAdminView() {
		adminView.setVisible(true);
		resultView.setVisible(false);
		searchView.setVisible(false);
	}

	public void displayResultView() {
		adminView.setVisible(false);
		resultView.setVisible(true);
		searchView.setVisible(false);
	}

	public void displaySearchView() {
		adminView.setVisible(false);
		resultView.setVisible(false);
		searchView.setVisible(true);
	}
}
