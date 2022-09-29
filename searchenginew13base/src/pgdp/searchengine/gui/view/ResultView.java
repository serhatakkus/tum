package pgdp.searchengine.gui.view;

import pgdp.searchengine.gui.controller.ResultController;

import javax.swing.*;
import java.awt.*;

/** Stellt die Result View dar. Sie ist als JScrollPane implementiert und zeigt
 *   1. alle bisher vom Result Controller geladenen Ergebnisse der letzten Suchanfrage untereinander
 *      in Form von ResultPane-Objekten
 *   2. darunter noch einen Button zum Laden weiterer Dokumente
 *  an.
 */
public class ResultView extends JScrollPane {
    // TODO: Komponenten hinzufügen (und evtl. noch weitere Attribute ??)
    private ResultController resultController;

    /** Erzeugt
     *   1. ein Panel (o.Ä.) für die AbstractDocumentPanes
     *   2. den Load-More-Button
     */
    public ResultView() {
        // TODO: Implementieren
    }

    public void setResultController(ResultController resultController) {
        this.resultController = resultController;
    }

    /** Fügt das übergebene ResultPane-Objekt unten an die bereits vorhandenen an
     *  und updatet dann die Anzeige (mit einem Call der Methode 'updateUI()').
     */
    public void addResultPane(ResultPane resultPane) {
        // TODO: Implementieren
    }

    /** Löscht alle angezeigten ResultPane-Objekte aus der View
     *  (nicht aber den Load-More-Button).
     */
    public void clear() {
        // TODO: Implementieren
    }

}
