package laba2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class Vuch {
	public BufferedImage image;
	public int[][] array;
	public int labl = 2;
	public boolean key = false;
	public int Sum = 0;

	
	// рекурсия делает команду из матлаба BWLABLE
	public void rec(int h, int w, int y, int x) {

		// if(array[y][x] == 1) {
		// array[y][x] = labl;

		if (x + 1 < w && array[y][x + 1] == 1) { // по x вправо
			// x=x+1;
			Sum = Sum + 1;
			array[y][x + 1] = labl;
			rec(h, w, y, (x + 1));
		}
		if (x - 1 >= 0 && array[y][x - 1] == 1) { // по x влево
			// x=x-1;
			Sum = Sum + 1;
			array[y][x - 1] = labl;
			rec(h, w, y, (x - 1));
		}
		if (y - 1 >= 0 && array[y - 1][x] == 1) { // по y вверх
			// y=y-1;
			Sum = Sum + 1;
			array[y - 1][x] = labl;
			rec(h, w, (y - 1), x);
		}

		if (y + 1 < h && array[y + 1][x] == 1) { // по y вниз
			// y=y+1;
			Sum = Sum + 1;
			array[y + 1][x] = labl;
			rec(h, w, (y + 1), x);
		}

//					key= true;
		// }

	}
	//Сортирует массив
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;

	}
	// основной метод алгоритма
	public Mass publ() {
		try {
			image = ImageIO.read(new File("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\labs\\laba2\\text3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage originalImage = (BufferedImage) image;

		System.out.println(originalImage.getWidth() + "x" + originalImage.getHeight());
		System.out.println();

		// ByteArrayOutputStream baos=new ByteArrayOutputStream();
		// ImageIO.write(originalImage, "bmp", baos );
		// byte[] imageInByte=baos.toByteArray();
		// int width = imageInByte.length;
		// System.out.println(width);
		array = new int[originalImage.getWidth()][originalImage.getHeight()];
		int y = 0; // i
		int x = 0; // j
		int h = originalImage.getHeight();
		int w = originalImage.getWidth();
		// считывание картинки
		for (y = 0; y < array.length; y++) {

			for (x = 0; x < array[y].length; x++) {

				array[y][x] = originalImage.getRGB(y, x);
				if (array[y][x] == -16777216) {
					array[y][x] = 0;
				}
				if (array[y][x] == -1) {
					array[y][x] = 1;
				}
				System.out.print(array[y][x] + "\t");
			}
			System.out.println();

		}
		System.out.println();
		System.out.println();
		System.out.println("Новый массив");
		// разделили на объекты , вычисление площади , добавление в map

		Map<Integer, Integer> map = new HashMap<>();
		// Map<Integer, String> map2 = new HashMap<>();
		for (y = 0; y < array.length; y++) {
			for (x = 0; x < array[y].length; x++) {
				if (array[y][x] == 1) {
					Sum = 1;
					array[y][x] = labl;
					rec(h, w, y, x);
					map.put(labl, Sum);

					// if(key == true) {
					labl++;
//					key = false;

//					}
				}
			}

		}

		// вывод массива
		for (y = 0; y < array.length; y++) {

			for (x = 0; x < array[y].length; x++) {

				System.out.print(array[y][x] + "\t");
			}
			System.out.println();

		}

		// не отсортированный map
		System.out.println(map);

		// отсортированый map по площадям
		Map<Integer, Integer> sortedMap = sortByValue(map);
		System.out.println("Новый");
		System.out.println(sortedMap);
		// переменная с цветом цвет
		

		image = originalImage;

		Mass m = new Mass();
		m.array = array;
		m.map = map;
		m.sortedMap = sortedMap;
		m.image = image;
		return m;

		// Отображение рисунка в левом верхнем углу.

		// Многократный вывод изображения в панели.

		// for(int i = 0; i * imageWidth <= getWidth(); i++)
		// for(int j = 0; j * imageHeight <= getHeight(); j++)
		// if(i + j > 0)
		// g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
	}

}
