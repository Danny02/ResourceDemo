package de.darwin.resourcedemo;

import java.awt.*;
import java.awt.image.*;

import darwin.resourcehandling.dependencies.ResourceHandlingModul;
import darwin.resourcehandling.dependencies.annotation.InjectResource;

import com.google.inject.*;
import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App extends JFrame {

    @InjectResource(file = "logo3.png")
    private ImageWrapper logo;

    public App() throws HeadlessException {
        super("Simple Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        setBounds(0, 0, 800, 600);
        setResizable(false);

        setVisible(true);
        createBufferStrategy(2);
    }

    public void start() {
        BufferStrategy bs = getBufferStrategy();
        while (true) {
            Graphics2D drawGraphics = (Graphics2D) bs.getDrawGraphics();
            drawGraphics.clearRect(0, 0, 800, 600);

            BufferedImage i = logo.getImg();
            drawGraphics.drawImage(i, 400 - i.getWidth() / 2, 300 - i.getHeight() / 2, null);

            bs.show();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    public static void main(String... args) {
        Guice.createInjector(Stage.DEVELOPMENT, new ResourceHandlingModul())
                .getInstance(App.class).start();
    }
}
