package pgdp.searchengine.gui.view;

import pgdp.searchengine.gui.controller.AdminController;

import javax.swing.*;
import java.awt.*;

/** Stellt die Admin View dar. Sie ist als JScrollPane implementiert und zeigt
 *   1. alle bisher vom Admin Controller geladenen Dokumente untereinander in Form von AbstractDocumentPane-Objekten
 *   2. darunter noch einen Button zum Laden weiterer Dokumente
 *  an.
 */
public class AdminView extends JScrollPane {
    // TODO: Komponenten hinzufügen (und evtl. noch weitere Attribute ??)
    private AdminController adminController;

    /** Erzeugt
     *   1. ein Panel (o.Ä.) für die AbstractDocumentPanes
     *   2. den Load-More-Button
     */
    public AdminView() {
        // TODO: Implementieren
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    /** Fügt das übergebene AbstractDocumentPane-Objekt unten an die bereits vorhandenen an
     *  und updatet dann die Anzeige (mit einem Call der Methode 'updateUI()').
     */
    public void addDocumentPane(AbstractDocumentPane documentPane) {
        // TODO: Implementieren
    }

    /** Löscht alle angezeigten AbstractDocumentPane-Objekte aus der View
     *  (nicht aber den Load-More-Button).
     */
    public void clear() {
        // TODO: Implementieren
    }
}
