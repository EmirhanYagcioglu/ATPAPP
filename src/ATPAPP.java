import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ATPAPP extends JFrame implements KeyListener {
    public ATPAPP() {
        setTitle("ATPAPP v0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addKeyListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        ATPAPP atpapp = new ATPAPP();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
