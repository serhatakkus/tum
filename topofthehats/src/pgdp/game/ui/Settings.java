package pgdp.game.ui;

import pgdp.game.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;

public class Settings {

    final int numPingus, numHamsters, numHats, numSeconds;
    final boolean pingu;

    public Settings(int numPingus, int numHamsters, int numHats, int numSeconds, boolean pingu) {
        this.numPingus = numPingus;
        this.numHamsters = numHamsters;
        this.numHats = numHats;
        this.numSeconds = numSeconds;
        this.pingu = pingu;
    }

    public static JPanel create(Consumer<Settings> handler) {
        JPanel root = new JPanel();
        root.setLayout(new GridLayout(0, 3));
        JFormattedTextField numPingu = new JFormattedTextField();
        numPingu.setValue(2);
        {
            var parent = new JPanel();
            parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
            parent.add(new JLabel("number of wolf pingus"));
            var inputParent = new JPanel();
            inputParent.setLayout(new BoxLayout(inputParent, BoxLayout.X_AXIS));
            inputParent.add(new JLabel(new ImageIcon(Wrapper.fromConstant(Image.WOLF_PINGU_HAT))));
            inputParent.add(numPingu);
            parent.add(inputParent);
            numPingu.setMaximumSize(new Dimension(100, 25));
            root.add(parent);
        }
        JFormattedTextField numHamster = new JFormattedTextField();
        numHamster.setValue(2);
        {
            var parent = new JPanel();
            parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
            parent.add(new JLabel("number of hamsters"));
            var inputParent = new JPanel();
            inputParent.setLayout(new BoxLayout(inputParent, BoxLayout.X_AXIS));
            inputParent.add(new JLabel(new ImageIcon(Wrapper.fromConstant(Image.HAMSTER_HAT))));
            inputParent.add(numHamster);
            numHamster.setMaximumSize(new Dimension(100, 25));
            parent.add(inputParent);
            root.add(parent);
        }
        JFormattedTextField numHat = new JFormattedTextField();
        numHat.setValue(2);
        {
            var parent = new JPanel();
            parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
            parent.add(new JLabel("number of hats"));
            var inputParent = new JPanel();
            inputParent.setLayout(new BoxLayout(inputParent, BoxLayout.X_AXIS));
            inputParent.add(new JLabel(new ImageIcon(Wrapper.fromConstant(Image.HAT))));
            inputParent.add(numHat);
            parent.add(inputParent);
            numHat.setMaximumSize(new Dimension(100, 25));
            root.add(parent);
        }
        JFormattedTextField seconds = new JFormattedTextField();
        seconds.setValue(60);
        {
            var parent = new JPanel();
            parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
            parent.add(new JLabel("number of seconds"));
            parent.add(seconds);
            seconds.setMaximumSize(new Dimension(100, 25));
            root.add(parent);
        }
        JComboBox<String> player = new JComboBox<>(new String[]{"WolfPingu", "Hamster"});
        {
            var parent = new JPanel();
            parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
            parent.add(new JLabel("player figure"));
            var inputParent = new JPanel();
            inputParent.setLayout(new BoxLayout(inputParent, BoxLayout.X_AXIS));
            var label = new JLabel(new ImageIcon(Wrapper.fromConstant(Image.WOLF_PINGU)));
            inputParent.add(label);
            inputParent.add(player);
            parent.add(inputParent);
            root.add(parent);
            player.setMaximumSize(new Dimension(100, 25));
            player.addItemListener(event -> {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    label.setIcon(new ImageIcon(Wrapper.fromConstant(
                            "Hamster".equals(event.getItem()) ? Image.HAMSTER : Image.WOLF_PINGU
                    )));
                }
            });
        }
        {

            var parent = new JPanel();
            parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
            var button = new JButton("Start");
            button.addActionListener(l -> {
                var s = new Settings((Integer) numPingu.getValue(), (Integer) numHamster.getValue(),
                        (Integer) numHat.getValue(), (Integer) seconds.getValue(),
                        "WolfPingu".equals(player.getSelectedItem())
                );
                root.setVisible(false);
                handler.accept(s);
            });
            button.setMaximumSize(new Dimension(100, 20));
            button.setMinimumSize(new Dimension(0, 25));
            parent.add(button);
            root.add(parent);
        }
        return root;
    }

}
