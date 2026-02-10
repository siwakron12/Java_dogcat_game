package game.ui;

import game.animal.Animal;
import game.core.CharacterFactory;
import javax.swing.*;
public class SelectCharacterFrame extends JFrame {

    private JComboBox<String> player1Box;
    private JComboBox<String> player2Box;
    private JButton playButton;

    public SelectCharacterFrame() {
        setTitle("Select Character");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel p1Label = new JLabel("Player 1:");
        p1Label.setBounds(50, 40, 100, 25);
        add(p1Label);

        player1Box = new JComboBox<>(new String[]{"Dog", "Cat", "Bird"});
        player1Box.setBounds(150, 40, 150, 25);
        add(player1Box);

        JLabel p2Label = new JLabel("Player 2:");
        p2Label.setBounds(50, 80, 100, 25);
        add(p2Label);

        player2Box = new JComboBox<>(new String[]{"Dog", "Cat", "Bird"});
        player2Box.setBounds(150, 80, 150, 25);
        add(player2Box);

        playButton = new JButton("PLAY");
        playButton.setBounds(140, 140, 100, 30);
        add(playButton);

        setVisible(true);

        playButton.addActionListener(e -> {

    String p1Type = player1Box.getSelectedItem().toString();
    String p2Type = player2Box.getSelectedItem().toString();

    Animal player1 = CharacterFactory.create(p1Type);
    Animal player2 = CharacterFactory.create(p2Type);

    new GameFrame(player1, player2); // ส่งตัวละครเข้าเกม
    dispose(); // ปิดหน้าเลือก
});
    }
}
