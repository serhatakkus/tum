package pgdp.game.ui;

import pgdp.game.helper.PlayerCtl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var board = new GameBoard();
        var boardContainer = new JPanel();
        JLabel hatTime, remainingTime;
        {
            boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.Y_AXIS));
            var header = new JPanel();
            header.setLayout(new BorderLayout());
            var hat = new JPanel();
            hat.setLayout(new BoxLayout(hat, BoxLayout.Y_AXIS));
            hat.add(new JLabel("Score"));
            hatTime = new JLabel("0");
            hat.add(hatTime);
            header.add(hat, BorderLayout.LINE_START);
            var time = new JPanel();
            time.setLayout(new BoxLayout(time, BoxLayout.Y_AXIS));
            time.add(new JLabel("remaining time"));
            remainingTime = new JLabel("0:00");
            time.add(remainingTime);
            header.add(time, BorderLayout.LINE_END);
            boardContainer.add(header);
            boardContainer.add(board);
            boardContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        }
        var settings = new Object() {
            JPanel p;
        };
        frame.getContentPane().add(settings.p = Settings.create(s -> {
            frame.add(boardContainer, BorderLayout.CENTER);
            try {
                new Wrapper().run(s, board, remainingTime, hatTime, () -> frame.add(settings.p, BorderLayout.CENTER));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }), BorderLayout.CENTER);
        var size = new Dimension(1700, 1000);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            boolean set;
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                set = true;
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                set = false;
            } else {
                return false;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> PlayerCtl.setUp(set);
                case KeyEvent.VK_A -> PlayerCtl.setLeft(set);
                case KeyEvent.VK_S -> PlayerCtl.setDown(set);
                case KeyEvent.VK_D -> PlayerCtl.setRight(set);
                case KeyEvent.VK_SPACE -> PlayerCtl.setAttack(set);
            }

            return false;
        });
        frame.setMinimumSize(size);
        frame.setVisible(true);
    }
}
