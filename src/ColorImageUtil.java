class ColorImageUtil {
	/**
	 * This static class contains functions and procedures that are useful for the
	 * creation and manipulation of ColorImages.
	 */

	static void paste(ColorImage imgBig, ColorImage imgSmall, int xi, int yi, boolean isTransparent) {
		/**
		 * Procedure that copies the non transparent part of a ColorImage on top of
		 * another.
		 * 
		 * The transparent colour is defined by the variable 'ignoreColor'. The
		 * ColorImage 'imgSmall' is pasted on the position (xi, yi) of the 'imgBig'.
		 */

		Color ignoreColor = new Color(255, 255, 255);

		// Goes through the big picture to ensure that we are between bounds
		for (int x = 0; x < imgBig.getWidth(); x++)
			for (int y = 0; y < imgBig.getHeight(); y++) {
				if (x >= xi && x < xi + imgSmall.getWidth() && y >= yi && y < yi + imgSmall.getHeight())
					if (!(isTransparent && ignoreColor.isEqualTo(imgSmall.getColor(x - xi, y - yi))))
						imgBig.setColor(x, y, imgSmall.getColor(x - xi, y - yi));
			}

		return;
	}

	static void mozaico(ColorImage img, int width, int height, boolean isTransparent) {
		/**
		 * Procedure that creates a pattern based on an image. It might include or not
		 * transparency.
		 */

		ColorImage page = new ColorImage(width, height);

		int x = 0, y = 0;

		while (y < page.getHeight()) {
			while (x < page.getWidth()) {
				paste(page, img, x, y, isTransparent);
				x = x + img.getWidth();
			}
			x = 0;
			y = y + img.getHeight();
		}

		return;
	}

	static ColorImage scale(ColorImage img, double factor) {
		/**
		 * Function that creates a copy of an image scaled by a factor.
		 */
		int widthNew = (int) (factor * img.getWidth());
		int heightNew = (int) (factor * img.getHeight());

		ColorImage imgNew = new ColorImage(widthNew, heightNew);

		for (int x = 0; x < imgNew.getWidth(); x++)
			for (int y = 0; y < imgNew.getHeight(); y++)
				imgNew.setColor(x, y, img.getColor((int)(x/factor), (int)(y/factor)));

		return imgNew;
	}

	static void test_1_3() {
		ColorImage img = new ColorImage("cat.jpeg");
		//ColorImage imgNew = scale(img, 0.7);
		ColorImage imgNew = scale(img, 1.25);
	}

	static void test_1_2() {
		ColorImage img = new ColorImage("cat.jpeg");
		boolean isTransparent = false;
		int width = 500, height = 1000;

		mozaico(img, width, height, isTransparent);
	}

	static void test_1_1() {
		ColorImage imgBig = new ColorImage("photo04.png");
		ColorImage imgSmall = new ColorImage("photo04_2.png");
		int x = 5, y = 20;

		paste(imgBig, imgSmall, x, y, true);
		// paste(imgSmall, imgBig, x, y, true);
	}

}