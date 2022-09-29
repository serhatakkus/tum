package pgdp.searchengine.gui.view;

import pgdp.searchengine.gui.controller.SearchEngineController;

import javax.swing.*;
import java.awt.*;

/** Das Haupt-Frame. Besteht aus einer Top-Bar oben, in der der Titel der aktuellen View und einige Buttons angezeigt
 *  werden, sowie das Haupt-Fenster darunter, in das die jeweils angezeigte View (Admin View, Result View oder
 *  Search View) geladen wird.
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

    /** Initialisiert die Search Engine View mit einer neuen Top-Bar in 'topBar' und einem Panel in 'body',
     *  in dem, je nach Programmzustand, je eines der drei übergebenen Views angezeigt werden kann.
     *  Als Erstes wird die dabei Search View angezeigt.
     *  Setzt außerdem die vier Attribute 'searchEngineController', 'adminView', 'resultView'
     *  und 'searchView' auf die entsprechenden Parameter.
     *
     * @param searchEngineController Ein Search Engine Controller
     * @param adminView Eine Admin View
     * @param resultView Eine Result View
     * @param searchView Eine Search View
     */
    public void init(SearchEngineController searchEngineController, AdminView adminView, ResultView resultView, SearchView searchView) {
        // TODO: Implementieren
    	this.searchEngineController = searchEngineController;
		this.topBar = new TopBar(searchEngineController);
		this.adminView = adminView;
		this.resultView = resultView;
		this.searchView = searchView;
		this.body = new JPanel();
		this.body.add(searchView);
		this.body.add(resultView);
		this.body.add(adminView);

		this.getContentPane().add(topBar);
		this.getContentPane().add(body);
		topBar.setVisible(true);
		
		displaySearchView();
		
		JPanel j = new JPanel();
		for (Component comp : j.getComponents()) {
			j
		}
    }

    /** Diese drei Methoden zeigen die dem Namen entsprechende View an.
     *  Dabei wird der Titel in der Top-Bar korrekt gesetzt und die Buttons in ihr, die in dieser View nicht angezeigt
     *  werden sollen, werden versteckt.
     */
    public void displayAdminView() {
        // TODO: Implementieren
    	this.searchView.setVisible(false);
    	this.resultView.setVisible(false);
    	this.adminView.setVisible(true);
    	this.topBar.setTitle("Admin View");
    }

    public void displayResultView() {
        // TODO: Implementieren
    	this.searchView.setVisible(false);
    	this.resultView.setVisible(true);
    	this.adminView.setVisible(false);
    	this.topBar.setTitle("Search Results");
    }

    public void displaySearchView() {
        // TODO: Implementieren
    	this.searchView.setVisible(true);
    	this.resultView.setVisible(false);
    	this.adminView.setVisible(false);
    	this.topBar.setTitle("Search");
    }
}
