package pgdp.searchengine.gui.view;

import pgdp.searchengine.gui.controller.AdminController;

import javax.swing.*;
import java.awt.*;

/** Stellt den Dialog dar, der aufpoppt, wenn man in der Admin View den Crawl-Button drückt.
 *  Es wird erstens nach einer Start-Adresse zum crawlen und zweitens nach der Anzahl an zu crawlenden
 *  Seiten gefragt.
 *  Der Nutzer hat die Möglichkeit, auf einen von zwei Buttons zu drücken:
 *   1. Cancel: Bricht das Crawling ab, bevor es begonnen hat.
 *   2. Crawl: Crawlt mit den aktuell eingegebenen Parametern
 *
 *  Der Dialog zeigt sich nicht selbst an (ruft also nicht 'setVisible(true)' auf), sondern wartet,
 *  bis er von außen her (= in SearchEngineController.crawlButtonPressed() dann) sichtbar gesetzt wird.
 */
public class CrawlDialog extends JDialog {

    /** Erzeugt den Dialog wie oben beschrieben.
     *  Mittels des 'adminController's können die Buttons nach außen hin kommunizieren.
     *
     * @param adminController Ein Admin Controller
     */
    public CrawlDialog(AdminController adminController) {
        // TODO: Implementieren
    }

}
