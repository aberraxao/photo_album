class ColorImageUtil {
	/**
	 * This static class contains functions and procedures that are useful for the
	 * creation and manipulation of ColorImages.
	 */

	static ColorImage paste(ColorImage imgBig, ColorImage imgSmall, int xi, int yi) {
		/**
		 * Function that copies the non transparent part of a ColorImage on top of
		 * another.
		 * 
		 * The transparent colour is defined by the variable 'ignoreColor'. The
		 * ColorImage 'imgSmall' is pasted on the position (xi, yi) of the 'imgBig'.
		 */

		Color ignoreColor = new Color(255, 255, 255);

		// Goes through the big picture to ensure that we are between bounds
		for (int x = 0; x < imgBig.getWidth(); x++)
			for (int y = 0; y < imgBig.getHeight(); y++) {

				if (x > xi && x < xi + imgSmall.getWidth() && y > yi && y < yi + imgSmall.getHeight())
					if (!ignoreColor.isEqualTo(imgSmall.getColor(x - xi, y - yi)))
						imgBig.setColor(x, y, imgSmall.getColor(x - xi, y - yi));
			}

		return imgBig;
	}

	static void test_1_1() {
		// TODO: add the ignoreColor as an option
		ColorImage imgBig = new ColorImage("photo04.png");
		ColorImage imgSmall = new ColorImage("photo04_2.png");
		int x = 5, y = 20;

		paste(imgBig, imgSmall, x, y);
		// paste(imgSmall, imgBig, x, y);

		System.out.println("end");
	}
	
	
}