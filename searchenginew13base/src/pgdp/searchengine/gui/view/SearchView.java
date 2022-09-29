package pgdp.searchengine.gui.view;

import pgdp.searchengine.gui.controller.SearchController;

import javax.swing.*;
import java.awt.*;

/** Stellt die Search View dar. Sie enthält
 *   1. den Text "PinguPinguLos" fett über dem Suchfeld und dem Search-Button.
 *   2. das Suchfeld
 *   3. den Search-Button
 */
public class SearchView extends JPanel {
    // TODO: Evtl. Attribute ??

    private SearchController searchController;

    /** Erzeugt
     *   1. Den Text "PinguPinguLos" in einer großen, fetten Schrift über den beiden anderen Komponenten.
     *   2. Ein Text-Feld, in das man Suchanfragen eingeben kann.
     *   3. Rechts neben 2. einen Search-Button, auf dessen Klick hin die Suche mit dem aktuell in 2. stehenden
     *      String abgeschickt wird.
     */
    public SearchView() {
        // TODO: Implementieren
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

}
