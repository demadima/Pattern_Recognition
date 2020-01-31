package laba4;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

public class Proba {
	public static BufferedImage image;
	public static BufferedImage image_0;
	public static BufferedImage image2;
	public static int[][] array;
	public static String[] video1 = { "Video_00000.jpg", "Video_00001.jpg" };
	public static String[] video1_0 = { "Video_00000.jpg", "Video_00002.jpg" };
	public static String[] video2 = { "Video2_00000.jpg", "Video2_00001.jpg" };
	public static double r;
	public static double g;
	public static double b;
	public static double r2;
	public static double g2;
	public static double b2;
	public static double Sumr;
	public static double Sumg;
	public static double Sumb;
	public static int a;
	public static int number;
	public static int Sum;
	public static int lable;
	public static double koefLeft;
	public static double koefRight;
	public static boolean key = true;

	public static void main(String[] args) throws IOException {
		 BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\labs\\laba4\\Пробник\\" +"notes3.txt"));
		System.out.println("Длинна массива = " + video1.length + " элемента.");
		for(int l = 0; l < 10; l++) {
			for(int k = 0; k < 10; k++) {
			number++;			// считываем картинку №1(наш объект)
			try {
				image = ImageIO.read(
						new File("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\labs\\laba4\\Пробник\\Video6\\Video_000"+l+
								+ k + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// считываем картинку №1_0 (Маска наша)
			try {
				image_0 = ImageIO.read(
						new File("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\labs\\laba4\\Пробник\\Video6\\Video_000"+l+
								+ k + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// считываем картинку №2(наш фон)
			try {
				image2 = ImageIO.read(
						new File("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\labs\\laba4\\Пробник\\Video2\\Video2_000"+l+
								+ k + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			BufferedImage originalImage = (BufferedImage) image;
			BufferedImage originalImage_0 = (BufferedImage) image_0;
			BufferedImage originalImage2 = (BufferedImage) image2;

			System.out.println(originalImage.getWidth() + "x" + originalImage.getHeight());
			System.out.println();

			array = new int[originalImage.getWidth()][originalImage.getHeight()];

			int y = 0; // i
			int x = 0; // j
			int h = originalImage.getHeight();
			int w = originalImage.getWidth();
			// считывание картинки 1

			for (y = 0; y < array.length; y++) {
				for (x = 0; x < array[y].length; x++) {
					array[y][x] = image.getRGB(y, x);
					/*
					 * try(FileWriter writer = new FileWriter("notes3.txt", true)) { // запись всей
					 * строки int text = array[y][x]; writer.write(text+"\t"); } catch(IOException
					 * ex){ System.out.println(ex.getMessage()); }
					 */
					// System.out.print(array[y][x] + "\t");
				}
				// System.out.println();
			}
			// красим в красный цвет фон картинки 1

			/*
			 * for (y = 0; y < array.length; y++) { for (x = 0; x < array[y].length; x++) {
			 * if (array[y][x] >= -20656128 && array[y][x] <= -16055000) { image.setRGB(y,
			 * x, Color.red.getRGB()); } } // System.out.println(); }
			 */

			// делаем маску для картинки 1
			for (y = 0; y < array.length; y++) {
				for (x = 0; x < array[y].length; x++) {
					Color c = new Color(image.getRGB(y,x));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                  
                    
					if (r<70 && r>0 && g<=130 && g>40 && b<=130 && b>40 ) {
						image_0.setRGB(y, x, Color.white.getRGB());
					}

					else {
						image_0.setRGB(y, x, Color.black.getRGB());
					}
				}
			}

			// обновляем массив
			for (y = 0; y < array.length; y++) {
				for (x = 0; x < array[y].length; x++) {
					array[y][x] = image_0.getRGB(y, x);

					// Color colors = new Color(image_0.getRGB(y, x));

					// System.out.println("Red = " + colors.getRed());
					// System.out.println("Green = " + colors.getGreen());
					// System.out.println("Blue = " + colors.getBlue());

				}
			}
			System.out.println(new Color(1, 217, 0).getRGB());
			System.out.println(Color.black.getRGB());

			// Находим контур по маске c радиусом 10

			for (lable = 10; lable > 0; lable--) {
				if (lable == 10) {
					for (y = 0; y < array.length; y++) {
						for (x = 0; x < array[y].length; x++) {

							if (x - 1 >= 0 && array[y][x] == -16777216 && array[y][x - 1] == -1
									|| y + 1 < h && array[y][x] == -16777216 && array[y + 1][x] == -1
									|| y - 1 >= 0 && array[y][x] == -16777216 && array[y - 1][x] == -1) {
								array[y][x] = lable;

							} else {
								// System.out.print(array[y][x]);
							}

						}
						// System.out.println();
					}
				} else {

					for (y = 0; y < array.length; y++) {
						for (x = 0; x < array[y].length; x++) {

							if (x - 1 >= 0 && array[y][x] == -16777216 && array[y][x - 1] == lable + 1
									|| y + 1 < h && array[y][x] == -16777216 && array[y + 1][x] == lable + 1
									|| y - 1 >= 0 && array[y][x] == -16777216 && array[y - 1][x] == lable + 1) {
								array[y][x] = lable;
								// System.out.print(lable);
							}

						}

					}
				}
			}
			// Проверка на маску
			for (y = 0; y < array.length; y++) {
				for (x = 0; x < array[y].length; x++) {
					
						int text = array[y][x];
						writer.write(text + " ");
					
				}
				writer.write("\n");
			}

			// размытие
			
			
			for (y = 0; y < array.length; y++) {
				for (x = 0; x < array[y].length; x++) {
					// Если это фон
					if(array[y][x] == -1) {
						image_0.setRGB(y, x,image2.getRGB(y, x) );//фон
					}else if(array[y][x] == -16777216) {
						image_0.setRGB(y, x,image.getRGB(y, x) );//объект
					}else if(array[y][x] >= 1 && array[y][x] <= 10 ) {
						// Расчитываем коеф для фона
					
						koefLeft = (double)array[y][x]/10;
						//System.out.println(koefLeft);
						Color colors = new Color(image2.getRGB(y, x));
						r = colors.getRed();
						g = colors.getGreen();
						b = colors.getBlue();
						
						r =r*koefLeft;
						g =g*koefLeft;
						b =b*koefLeft;
						
						// Расчитываем коеф для объекта
						koefRight= (double)(10-array[y][x])/10;
						Color colors2 = new Color(image.getRGB(y, x));
						r2 = colors2.getRed();
						g2 = colors2.getGreen();
						b2 = colors2.getBlue();
						r2 =r2*koefRight;
						g2 =g2*koefRight;
						b2 =b2*koefRight;
						
						Sumr = r + r2;
						Sumg = g + g2;
						Sumb = b + b2;
						Sumr = Math.round(Sumr);
						Sumg = Math.round(Sumg);
						Sumb = Math.round(Sumb);
					
						// Результат
						image_0.setRGB(y, x,new Color((int)Sumr,(int)Sumg, (int)Sumb).getRGB() );
				
						
					}
					
				}
			}
			double summ = 0;
			int kul = 7;
			summ = (double)29/10;
			
			System.out.println(Math.round (summ));
			
			// на конечную картинку,на маску налаживаем изображение
			/*
			 * for (y = 0; y < array.length; y++) { for (x = 0; x < array[y].length; x++) {
			 * if (array[y][x] == -16777216) { image_0.setRGB(y, x,image.getRGB(y, x) ); }
			 * else {
			 * 
			 * }
			 * 
			 * } }
			 */

			try {
				ImageIO.write(image_0, "png",
						new File("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\labs\\laba4\\Пробник\\Video7\\text" + number
								+ ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		}
	}

}
