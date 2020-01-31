package laba2;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferShort;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;


import javax.imageio.ImageIO;

public class Proba {
	
			
	 public static void main(String args[]) throws IOException {
		 
		 File fnew=new File("D:\\Dima Yniversitet\\4 kyrcc\\Розпізнаввння образів\\laba1\\text.bmp");
		 
		 BufferedImage originalImage=ImageIO.read(fnew);
		 
		 System.out.println( originalImage.getWidth()+"x"+originalImage.getHeight());
		 System.out.println( );
		 
		 
		
		 
	//	 ByteArrayOutputStream baos=new ByteArrayOutputStream();
	//	 ImageIO.write(originalImage, "bmp", baos );
	//	 byte[] imageInByte=baos.toByteArray();
	//	int width = imageInByte.length;
	//	 System.out.println(width);
		int[][] array = new int[ originalImage.getWidth()][originalImage.getHeight()];
		
			for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[i].length; j++) {
						array[i][j] = originalImage.getRGB(i,j);
						if(array[i][j] == -16777216) {
							array[i][j] = 0;
						}
						if(array[i][j] == -1) {
							array[i][j] = 1;
						}
						System.out.print(array[i][j] + "\t");
					}
					System.out.println();
					
			}
			
			
			
			
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					
					if(array[i][j] == 0) {
						array[i][j] = -16777216;
					}
					if(array[i][j] == 1) {
						array[i][j] = -1;
					}
					originalImage.setRGB(i, j, array[i][j]);
					
				}
			}
			
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //Вот что я использую для преобразования необработанных пиксельных данных в BufferedImage. Мои пиксели подписаны 16-бит:
	 public static BufferedImage short2Buffered(short[] pixels, int width, int height) throws IllegalArgumentException {
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY);
		    short[] imgData = ((DataBufferShort)image.getRaster().getDataBuffer()).getData();
		    System.arraycopy(pixels, 0, imgData, 0, pixels.length);     
		    return image;
		}
	 // из байткода обратно в картинку//
	 public BufferedImage byte2Buffered(byte[] pixels, int width, int height) throws IllegalArgumentException {
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		    byte[] imgData = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		    System.arraycopy(pixels, 0, imgData, 0, pixels.length);     
		    return image;
		}
}
