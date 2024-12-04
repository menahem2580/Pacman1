package PacMan;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends Element {



    public MyFrame() throws IOException {

        JFrame frame = new JFrame();
        MyJpanel myJpanel = new MyJpanel(col * size, row * size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.add(myJpanel);
        myJpanel.requestFocusInWindow();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
