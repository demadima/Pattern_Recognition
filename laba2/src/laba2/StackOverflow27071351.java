package laba2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StackOverflow27071351 {

	private static class ImagePanel extends JPanel {
		private BufferedImage image;
		private Mass m;
		//конструктор
		public ImagePanel(int width, int height, BufferedImage image, Mass m) {
			this.image = image;
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			this.m = m;
			repaint();
		}
		//союытие вывода на JPanel
		public void paintComponent(Graphics g) {

			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}

		/*
		 * public void paintComponent(Graphics g) { super.paintComponent(g); for (int i
		 * = 0; i < image.getWidth(); i++) { for (int j = 0; j < image.getHeight(); j++)
		 * { image.setRGB(i, j, new Color(255, 0, 0, 127).getRGB()); } }
		 * g.drawImage(image, 0, 0, getWidth(), getHeight(), this); }
		 */

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		int width = 50;
		int height = 50;
		frame.setSize(width, height);

		Vuch v = new Vuch();
		Mass m = v.publ();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		image = Pokraska(m, image);//красим все объекты
		image = PokraskaRavnuh(m, image);//красим равные объекты
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new ImagePanel(width, height, image, m));

		frame.setVisible(true);
	}

	private static BufferedImage PokraskaRavnuh(Mass m, BufferedImage image) {
		int c = 255 / m.sortedMap.size();
		// создать рандомный цвет
		// System.out.print(m.sortedMap.get(i)+" ");
		// System.out.print(m.sortedMap.get(i+1)+" ");
		// System.out.println("-------------");
		for (int i = 2; i < m.sortedMap.size() + 3; i++) {

			for (int j = 2; j < m.sortedMap.size() + 3; j++) {
				System.out.println(m.sortedMap.get(i)+"  "+m.sortedMap.get(j));
				System.out.println(m.sortedMap.get(i) == m.sortedMap.get(j));
				
				
				if (m.sortedMap.get(i) == m.sortedMap.get(j) && i != j) {
					System.out.println("Равныыыыы");
					for (int x = 0; x < m.image.getWidth(); x++) {
						for (int y = 0; y < m.image.getHeight(); y++) {
							if (m.array[x][y] == j) {
								System.out.print(j);
								image.setRGB(x, y, new Color(c, 10, 60, 255).getRGB());
							}
							if (m.array[x][y] == i) {
								System.out.print(i);
								image.setRGB(x, y, new Color(c, 10, 60, 255).getRGB());
							}

						}
					}
					c = c + 10;
					
				}
				System.out.println("ледующее сравнение");
			}
			System.out.println();

			System.out.println(c);
			// System.out.println("YES");
		}
		return image;
	}

	public static BufferedImage Pokraska(Mass m, BufferedImage image) {
		int a = 255 / m.sortedMap.size();
		int b = 255 / m.sortedMap.size() + 20;
		System.out.println(a);
		for (int i = 2; i < m.sortedMap.size() + 3; i++) {

			for (int x = 0; x < m.image.getWidth(); x++) {
				for (int y = 0; y < m.image.getHeight(); y++) {
					if (m.array[x][y] == i) {
						System.out.print(i);
						image.setRGB(x, y, new Color(170, b, a, 255).getRGB());
					}
				}
			}
			// System.out.print(m.sortedMap.get(i)+" ");
			// System.out.print(m.sortedMap.get(i+1)+" ");
			// System.out.println("NO");
			System.out.println();

			a = a + 255 / m.sortedMap.size();
			if (a >= 255) {
				b = b + 255 / m.sortedMap.size();
			}
			System.out.println(a);
		}

		for (int x = 0; x < m.image.getWidth(); x++) {
			for (int y = 0; y < m.image.getHeight(); y++) {

				if (m.array[x][y] == 0) {

					image.setRGB(x, y, Color.black.getRGB());
				}

			}
		}

		return image;
	}

}
