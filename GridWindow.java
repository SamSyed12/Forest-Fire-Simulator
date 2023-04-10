import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Displays images provided as 2D arrays of Color; the window automatically
 * scales to fit the grid on the screen; pressing 'f' while focus is on the
 * window changes to full screen mode, and pressing 'escape' returns to a
 * standard window
 *
 */
@SuppressWarnings("serial")
public class GridWindow extends JPanel implements KeyListener {

  /** Alters the speed of window updates. Larger value is slower updates. */
  private int speedFactor;

	private String title;
	private JFrame frame;
	private Image image;
	private BufferedImage imageBuffer;
	private boolean fullScreen;

	/**
	 * creates a window for displaying cellular automata
	 *
	 * @param title Name of window printed in titlebar
	 * @param speed Sets the speed of window updates, larger value is slower
	 */
	public GridWindow(String title, int speed) {
		this.title = title;
    this.speedFactor = speed;
		reset();
	}

	/**
	 * closes the window and cleans up resources
	 */
	public void close() {
		frame.dispose();
		frame = null;
	}

	/**
	 * updates the window with a grid of Colors; this makes the window visible
	 * if currently hidden
	 *
	 * @param grid 2D array of colored cells
	 */
	public void updateWindow(Color[][] grid) {
		if (frame == null)
			reset();
		BufferedImage newImage = new BufferedImage(grid[0].length, grid.length,
				BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < newImage.getHeight(); row++)
			for (int col = 0; col < newImage.getWidth(); col++)
				newImage.setRGB(col, row, grid[row][col].getRGB());
		imageBuffer = newImage;
		scaleImage();
		try {
			Thread.sleep(speedFactor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * responds to keys that the user presses while focused on the window:
	 * 'f' switches to full screen; escape exits full screen
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 0x1B && fullScreen) {
			fullScreen = false;
			reset();
			if (imageBuffer != null)
				scaleImage();
		} else if (e.getKeyChar() == 'f' && !fullScreen) {
			if (fullScreen = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getScreenDevices()[0].isFullScreenSupported()) {
				reset();
				if (imageBuffer != null)
					scaleImage();
			}
		}
	}

	/**
	 * unused method for KeyListener
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * unused method for KeyListener
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * needed for image to refresh nicely
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * creates a new window; closes current window, if necessary
	 */
	private void reset() {
		if (frame != null)
			frame.dispose();
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setUndecorated(fullScreen);
	}

	/**
	 * expands image to a multiple that fits nicely on the screen
	 */
	private void scaleImage() {
		GraphicsDevice screen = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getScreenDevices()[0];
		DisplayMode display = screen.getDisplayMode();
		int zoom = Math.min(display.getHeight() / imageBuffer.getHeight(),
				display.getWidth() / imageBuffer.getWidth());
		zoom = Math.max(1, fullScreen ? zoom : zoom*4/5);
		image = imageBuffer.getScaledInstance(imageBuffer.getWidth() * zoom,
				imageBuffer.getHeight() * zoom, Image.SCALE_FAST);
		if (!frame.isVisible()) {
			if (fullScreen)
				screen.setFullScreenWindow(frame);
			frame.setVisible(true);
		}
		if (!fullScreen) {
			Insets border = frame.getInsets();
			frame.setSize(border.left + border.right + image.getWidth(frame),
					border.top + border.bottom + image.getHeight(frame));
		}
		repaint();
	}

}
