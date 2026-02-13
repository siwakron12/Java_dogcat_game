package game.ui;
import game.animal.Animal ;
import game.core.Game ;
import java.awt.*; 
import javax.swing.* ; 

public class GamePanel extends JPanel {
    private Game game ;
    final int GROUND_Y = 500 ;
    
    public GamePanel(Game game) {
        this.game = game ;
        setBackground(new Color(135,206,235)); // set bg color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //ล้างหน้าจอก่อนวาดใหม่

        Graphics2D g2 = (Graphics2D) g ;

        g2.setColor(new Color(34,139,34)) ;
        g2.fillRect(0,GROUND_Y,getWidth(),100) ;
        
        Animal p1 = game.getPlayer1() ;
        Animal p2 = game.getPlayer2() ;

        drawAnimal(g2, p1, p1.getX_position(), GROUND_Y, Color.RED);
        drawAnimal(g2,p2,p2.getX_position(),GROUND_Y,Color.BLUE) ;
    }

   private void drawAnimal(Graphics2D g2 , Animal animal , int x , int y , Color c) {

    // วาดรูป
    g2.drawImage(animal.getImage(), x, y, 100, 100, null);

    // วาดกรอบ


    // ชื่อสัตว์
    g2.setFont(new Font("Arial" , Font.BOLD , 14));
    g2.drawString(animal.getClass().getSimpleName(), x, y-10);
}
}

//gamepanel
