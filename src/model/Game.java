
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

	public Game()
	{
		World world = new World(640,480);
		add(world, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game g = new Game();
	}

}
