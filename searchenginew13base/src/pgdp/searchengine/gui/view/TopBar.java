package pgdp.searchengine.gui.view;

import pgdp.searchengine.gui.controller.SearchEngineController;

import javax.swing.*;
import java.awt.*;

/** Stellt die Leiste mit Überschrift und Buttons ganz oben im Frame dar.
 */
public class TopBar extends JPanel {
    private JLabel title;

    private JButton crawlButton;
    private JButton toAdminViewButton;
    private JButton toSearchViewButton;
    private JButton exitButton;

    /** Setzt die fünf Attribute dieser Klasse auf neue Objekte des entsprechenden Typs
     *  und fügt den Buttons geeignete ActionListener (Lambdas genügen hier völlig) hinzu.
     *  Zwischen dem Titel und den Buttons ist Platz, d.h. der Titel befindet sich ganz links,
     *  die Buttons ganz rechts in der Top-Bar.
     *
     * @param controller Ein Search Engine Controller (für die Events)
     */
    public TopBar(SearchEngineController controller) {
        this.title = new JLabel("Title");
    }

    /** Setzt den Text im title-Feld auf den übergebenen Text.
     *
     * @param titleText Neuer Titel-Text
     */
    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    /** Setzt den dem Methodennamen entsprechenden Button unsichtbar.
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

    /** Setzt alle Buttons auf sichtbar.
     */
    public void setAllButtonsVisible() {
        crawlButton.setVisible(true);
        toAdminViewButton.setVisible(true);
        toSearchViewButton.setVisible(true);
        exitButton.setVisible(true);
    }
}
