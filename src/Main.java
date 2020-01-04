import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static JFrame frame;
	public static JPanel panel;
	public static byte[][] charset = new byte[128][5];

	public static final int WIDTH = 20;
	public static final int HEIGHT = 7;

	public static int[] leds = new int[WIDTH * HEIGHT];

	public static void main(String[] args) {
		//Set up the simulator's display
		frame = new JFrame("Lights simulator");
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				super.paintComponent(g2d);
				draw(g2d);
			}
		};
		panel.setDoubleBuffered(true);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 400));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Create character bitmaps
		// Bitmaps use binary in 5 vertical colums to determine which pixels are active
		// Bits start top and go down
		createChar('!', 0, 0, 0x5F, 0, 0);
		createChar('"', 0, 0x07, 0, 0x07, 0);
		createChar('#', 0x14, 0x7F, 0x14, 0x7F, 0x14);
		createChar('$', 0x24, 0x2A, 0x7F, 0x2A, 0x12);
		createChar('%', 0x23, 0x13, 0x08, 0x64, 0x62);
		createChar('\'', 0, 0x05, 0x03, 0, 0);
		createChar('.', 0, 0x60, 0x60, 0, 0);
		createChar(',', 0, 0x50, 0x30, 0, 0);
		createChar(':', 0, 0x36, 0x36, 0, 0);
		createChar('?', 0x02, 0x01, 0x51, 0x09, 0x06);
		createChar('0', 0x3E, 0x51, 0x49, 0x45, 0x3E);
		createChar('1', 0, 0x42, 0x7F, 0x40, 0);
		createChar('2', 0x42, 0x61, 0x51, 0x49, 0x46);
		createChar('3', 0x41, 0x41, 0x45, 0x4B, 0x31);
		createChar('4', 0x18, 0x14, 0x12, 0x7F, 0x10);
		createChar('5', 0x27, 0x45, 0x45, 0x45, 0x39);
		createChar('6', 0x3C, 0x4A, 0x49, 0x49, 0x30);
		createChar('7', 0x01, 0x01, 0x79, 0x05, 0x03);
		createChar('8', 0x36, 0x49, 0x49, 0x49, 0x36);
		createChar('9', 0x06, 0x49, 0x49, 0x29, 0x1E);
		createChar('A', 0x7E, 0x09, 0x09, 0x09, 0x7E);
		createChar('B', 0x7F, 0x49, 0x49, 0x49, 0x36);
		createChar('C', 0x3E, 0x41, 0x41, 0x41, 0x22);
		createChar('D', 0x7F, 0x41, 0x41, 0x22, 0x1C);
		createChar('E', 0x7F, 0x49, 0x49, 0x49, 0x41);
		createChar('F', 0x7F, 0x09, 0x09, 0x09, 0x01);
		createChar('G', 0x3E, 0x41, 0x49, 0x49, 0x7A);
		createChar('H', 0x7F, 0x08, 0x08, 0x08, 0x7F);
		createChar('I', 0, 0x41, 0x7F, 0x41, 0);
		createChar('J', 0x20, 0x40, 0x41, 0x3F, 0x01);
		createChar('K', 0x7F, 0x08, 0x14, 0x22, 0x41);
		createChar('L', 0x7F, 0x40, 0x40, 0x40, 0x40);
		createChar('M', 0x7F, 0x02, 0x0C, 0x02, 0x7F);
		createChar('N', 0x7F, 0x04, 0x08, 0x10, 0x7F);
		createChar('O', 0x3E, 0x41, 0x41, 0x41, 0x3E);
		createChar('P', 0x7F, 0x09, 0x09, 0x09, 0x06);
		createChar('Q', 0x3E, 0x41, 0x51, 0x21, 0x5E);
		createChar('R', 0x7F, 0x09, 0x19, 0x29, 0x46);
		createChar('S', 0x46, 0x49, 0x49, 0x49, 0x31);
		createChar('T', 0x01, 0x01, 0x7F, 0x01, 0x01);
		createChar('U', 0x3F, 0x40, 0x40, 0x40, 0x3F);
		createChar('V', 0x1F, 0x20, 0x40, 0x20, 0x1F);
		createChar('W', 0x7F, 0x20, 0x18, 0x20, 0x7F);
		createChar('X', 0x63, 0x14, 0x08, 0x14, 0x63);
		createChar('Y', 0x03, 0x04, 0x78, 0x04, 0x03);
		createChar('Z', 0x61, 0x51, 0x49, 0x45, 0x43);

		while (true) {
			System.out.println("Loop");
			
			//This is where you'll put your sequences
			
//			displayText("TEAM 79 KRUNCH!", 0x1515FF, 0);
//			line(0xE837FC);
//			displayText("FIRST POWER UP", 0xFECF10, 0x800000);
//			crossLines(0xFECF07, 0xFF0000);
//			displayText("GRACIOUS PROFESSIONALISM!", 0x1515FF, 0);
//			checkerboard(0x42F4EB, 0xAD2FED);
//			displayText("DEAN KAMEN ROCKS!", 0x00FF00, 0);
//			rainbow();
//			displayText("WOODIE FLOWERS IS OUR HERO!", 0xF918AE, 0);
//			water();
//			displayText("WATER GAME 2019 CONFIRMED!", 0xFFFFFF, 0x3737FC);
//			powerOff();
			
			//For Media Night
			displayText("HI MOM!   HI DAD!", 0x00ff99, 0x5b004e);
			crossLines(0x00ff99, 0x5b004e);
			displayText("WELCOME TO THE 2018 MEDIA NIGHT!", 0xFFFFFF, 0x151577);
			displayText("THANKS TO OUR SPONSORS:", 0xFFFFFF, 0x151577);
			displayText("EAST LAKE HIGH SCHOOL", 0xFFFFFF, 0x006496);
			displayText("HONEYWELL", 0xeb3527, 0xBBBBBB);
			displayText("JABIL", 0x595959, 0xBBBBBB);
			displayText("PINELLAS COUNTY STEM ACADEMY", 0x3b4f81, 0xdadbd4);
			displayText("DEFENSEWERX", 0xCC0000, 0x004888);
//			displayText("BUTLER", textColor, backgroundColor); 
			displayText("GE", 0x326FBD, 0xBBBBBB);
			displayText("NETWORK FOR GOOD", 0x708ECD, 0x84D435);
			displayText("JMS MEDICAL SUPPLY", 0x00ABE4, 0xBBBBBB);
			displayText("TATA CONSULTANCY SERVICES", 0x665CC7, 0xBBBBBB);
		}
	}

	/**
	 * Scrolls text along the panel. For color, you can use new Color(r,g,b).getRGB() or hex color picker
	 * @param text
	 * @param textColor
	 * @param backgroundColor
	 */
	public static void displayText(String text, int textColor, int backgroundColor) {
		clear();
		int scroll = -22; //An initial value that starts the text offscreen
		char[] chars = text.toCharArray();
		while (scroll < text.length() * 6 + 1) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					int xScroll = x + scroll;
					if (xScroll % 6 == 5 || xScroll < 0 || xScroll > text.length() * 6 - 1) {
						setPix(x, y, backgroundColor);
						continue;
					}
					int i = xScroll / 6;
					char c;
					if (i < chars.length)
						c = chars[i];
					else
						c = ' ';
					setPix(x, y, binValue(charset[c][xScroll % 6], y) ? textColor : backgroundColor);
				}
			}
			show();
			delay(1000 / 15);//Adjust this to make the text scroll faster/slower. 15 = 15FPS
			scroll++;
		}
	}

	/**
	 * Colors all the pixels sequentially, then turns them off.
	 * @param color
	 */
	public static void line(int color) {
		clear();
		int num = WIDTH * HEIGHT;
		for (int i = 0; i < num; i++) {
			leds[i] = color;
			show();
			delay(10);
		}
		delay(500);
		for (int i = num - 1; i >= 0; i--) {
			leds[i] = 0;
			show();
			delay(10);
		}
	}

	/**
	 * Looks like a screen turning off
	 */
	public static void powerOff() {
		clear();
		for (int i = 0; i < leds.length; i++) {
			leds[i] = 0xFFFFFF;
		}
		show();
		delay(500);
		for (int pos = 0; pos < 4; pos++) {
			for (int x = pos; x < WIDTH - pos; x++) {
				setPix(x, pos, 0);
				setPix(x, HEIGHT - 1 - pos, 0);
			}
			for (int y = pos; y < HEIGHT - pos; y++) {
				setPix(pos, y, 0);
				setPix(WIDTH - 1 - pos, y, 0);
			}
			show();
			delay(250);
		}
	}

	
	public static void crossLines(int color1, int color2) {
		clear();
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (y % 2 == 0) {
					setPix(x, y, color1);
				} else
					setPix(WIDTH - x - 1, y, color2);
			}
			show();
			delay(40);
		}
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (y % 2 == 0) {
					setPix(x, y, 0);
				} else
					setPix(WIDTH - x - 1, y, 0);
			}
			show();
			delay(40);
		}
	}

	/**
	 * Utility function for converting x and y coordinates to a pixel. This is necessary
	 * because of the way the wiring is done.
	 * @param x
	 * @param y
	 * @param color
	 */
	public static void setPix(int x, int y, int color) {
		if (x < 0 || x > WIDTH - 1 || y < 0 || y > HEIGHT - 1)
			return;
		int i = (HEIGHT - 1 - y) * WIDTH + ((y % 2) * ((WIDTH - 1) - 2 * x) + x);
		leds[i] = color;
	}

	/**
	 * Used for rendering the pixels in the simulator
	 * @param g2d
	 */
	public static void draw(Graphics2D g2d) {
		g2d.scale(15, 15);
		g2d.translate(2, -2);
		for (int i = 0; i < leds.length; i++) {
			int y = WIDTH - i / WIDTH;
			int x = (y % 2) * (WIDTH - 1 - 2 * (i % WIDTH)) + i % WIDTH;
			g2d.setColor(new Color(leds[i]));
			g2d.drawLine(x, y, x, y);
		}
	}

	/**
	 * Create a bitmap of a character
	 * @param c - The character to create
	 * @param b0 - The binary that represents the on/off pixels for column 0
	 * @param b1 - The binary that represents the on/off pixels for column 1
	 * @param b2 - The binary that represents the on/off pixels for column 2
	 * @param b3 - The binary that represents the on/off pixels for column 3
	 * @param b4 - The binary that represents the on/off pixels for column 4
	 */
	public static void createChar(char c, int b0, int b1, int b2, int b3, int b4) {
		charset[c] = new byte[] { (byte) b0, (byte) b1, (byte) b2, (byte) b3, (byte) b4 };
	}

	/**
	 * Returns the state of a bit at an offset. Ex: binValue(00000100, 2) -> true.<br>
	 * This is used for the character bitmap
	 * @param b
	 * @param pos
	 * @return
	 */
	public static boolean binValue(byte b, int pos) {
		return (b & (1 << pos)) > 0;
	}

	/**
	 * Delays code execution by the given inpt
	 * @param ms time in milliseconds
	 */
	public static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Render pixel updates.
	 */
	public static void show() {
		frame.repaint();
	}

	/**
	 * Sets all pixels to black
	 */
	public static void clear() {
		for (int i = 0; i < leds.length; i++) {
			leds[i] = 0;
		}
	}

	/**
	 * Looks like water filling by rain
	 */
	public static void water() {
		clear();
		Random random = new Random();
		int color = 0x3737FC;
		int x = 0;
		int x1 = 0;
		int dy = 0;
		for (int i = 0; i < 62; i++) {
			x = random.nextInt(WIDTH);
			x1 = random.nextInt(WIDTH);
			while (x1 == x)
				x1 = random.nextInt();
			dy = random.nextInt(HEIGHT);
			for (int y = -5; y < HEIGHT + 3; y++) {
				delay(2);
				show();
				setPix(x, y - 2, color);
				setPix(x1, y - 2 + dy, color);
				delay(2);
				show();
				setPix(x, y - 1, color);
				setPix(x1, y - 1 + dy, color);
				delay(2);
				show();
				setPix(x, y, color);
				setPix(x1, y + dy, color);
				delay(2);
				show();
				if (y - 3 < HEIGHT - i / 8) {
					setPix(x, y - 3, 0);
				}
				if (y - 3 + dy < HEIGHT - i / 8) {
					setPix(x1, y - 3 + dy, 0);
				}
			}
			if (i % 8 == 0) {
				for (int xx = 0; xx < WIDTH; xx++) {
					setPix(xx, HEIGHT - i / 8, color);
				}
			}
		}
	}

	/**
	 * A rainbow effect
	 */
	public static void rainbow() {
		for(int i=0; i<28; i++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					setPix(x, y, (((x+5+4*i)*(y+5+4*i))<<i) | (((x+5+2*i)*(y+5+2*i))<<(2*i)));
				}
			}
			delay(100);
			show();
		}
	}

	/**
	 * A checkerboard effect of 2 colors
	 * @param color1
	 * @param color2
	 */
	public static void checkerboard(int color1, int color2) {
		clear();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = y % 2; x < WIDTH; x += 2) {
				setPix(x, y, color1);
				show();
				delay(50);
			}
		}
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = (y + 1) % 2; x < WIDTH; x += 2) {
				setPix(x, y, color2);
				show();
				delay(50);
			}
		}
	}
	
}
