package game.ui;
import game.core.Game ;
import game.animal.Animal ;
import javax.swing.*;
import java.awt.* ; 

public class GamePanel extends JPanel {
    private Game game ;

    public GamePanel(Game game) {
        this.game = game ;
        setBackground(new Color(135,206,235)); // set bg color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //clear panel

        Graphics2D g2 = (Graphics2D) g ;

        g2.setColor(new Color(34,139,34)) ;
        g2.fillRect(0,200,getWidth(),100) ;

        Animal p1 = game.getPlayer1() ;
        Animal p2 = game.getPlayer2() ;

        drawAnimal(g2, p1, 50, 150, Color.RED);
        drawAnimal(g2,p2,300,150,Color.BLUE) ;
    }

    private void drawAnimal(Graphics2D g2 , Animal animal , int x , int y , Color c) {
        g2.setColor(c) ;
        g2.fillRect(x,y,50,50) ;

        g2.setColor(Color.BLACK) ;
        g2.drawRect(x,y,50,50) ;

        g2.setFont(new Font("Arial" , Font.BOLD , 14));
        g2.drawString(animal.getClass().getSimpleName(),x,y-10) ;
    }
}

//gamepanel
