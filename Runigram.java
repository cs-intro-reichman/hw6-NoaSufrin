import java.awt.Color;
import java.awt.desktop.SystemEventListener;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);

		// Tests the Vertically flipping of an image:
		image = flippedVertically(tinypic);
		System.out.println();
		print(image);
		
		// Test the grayScaled of an image:
		image = grayScaled(tinypic);
		System.out.println();
		print(image);

		// Test the scaled of an image:
		image = scaled(tinypic, 4, 4);
		System.out.println();
		print(image);

		// Test the blend of an Color:
		Color Color1 = new Color(50, 50, 50);
		Color Color2 = new Color(25, 25, 25);
		System.out.println();
		print(blend(Color2, Color2, 0.2));

		// Test the blend of an image:
		image = blend(tinypic, tinypic, 0.7);
		System.out.println();
		print(image);

		
				
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
	
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				int red = in.readInt();
				int green = in.readInt();
				int blue = in.readInt();
				
				image[row][col] = new Color(red, green, blue) ;
			}		
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				print(image[i][j]);
				System.out.print(" ");
			}
			System.out.println();	
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] flippedHorizontally = new Color[image.length][image[1].length];
		for (int i = 0; i < flippedHorizontally.length; i++) {
			for (int j = 0; j < flippedHorizontally[i].length; j++) {
				flippedHorizontally[i][j] = image[i][image[i].length - j -1];
			}
		} 
		return flippedHorizontally;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		
		Color[][] flippedVertically = new Color[image[1].length][image.length];
		for (int i = 0; i < flippedVertically[0].length; i++) {
			for (int j = 0; j < flippedVertically.length; j++) {
				flippedVertically[i][j] = image[image[0].length - 1 - i][j];
			}
		}
		return flippedVertically;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();

		int lum = (int) (0.299 * r  + 0.587 * g + 0.114 * b);
		Color lumColor = new Color(lum, lum, lum);
		return lumColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] grayScaled = new Color [image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				grayScaled[i][j] = luminance(image[i][j]);	
			}
		}
		return grayScaled;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		Color[][] newImage = new Color[height][width];
		int sourceW = image[0].length;
		int sourceH = image.length;
		double dWidth = (double) width;
		double dHeight = (double) height;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int newHeight = (int) (i * (sourceH / dHeight));
				int newWidth = (int) (j * (sourceW / dWidth));
				newImage[i][j] = image[newHeight][newWidth];
			}
		}
		return newImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		double beta = 0;
		if (alpha >= 0 && alpha <= 1) {
			beta = 1 - alpha;
		} else {
			return null;
		}
		int r = (int) (alpha * c1.getRed() + beta * c2.getRed());
		int g = (int) (alpha * c1.getGreen() + beta * c2.getGreen());
		int b = (int) (alpha * c1.getBlue() + beta * c2.getBlue());
		Color newColor = new Color(r, g, b);
		return newColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		Color[][] newImage = new Color[image1.length][image1[0].length];
		for (int i = 0; i < image1.length; i++) {
			for (int j = 0; j < image1[0].length; j++) {
				Color c1 = image1[i][j];
				Color c2 = image2[i][j];
				newImage[i][j] = blend(c1, c2, alpha);
			}
		}
		return newImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		Color[][] newImage = new Color [source.length][source[0].length];
		newImage = scaled(target, source[0].length, source.length);

		for (int i = 0; i < n; i++) {
			double alpha = (double) (n - i) / (double) n;
			display(blend(source, newImage, alpha));
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

