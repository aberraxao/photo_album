/**
 * Represents RGB colors.
 * RGB values are stored in a 3-position array, with values in the interval [0, 255].
 * rgb[0] - Red
 * rgb[1] - Green
 * rgb[2] - Blue
 */

class Color {

	/**
	 * A1 Commonly used variables & colours
	 **/
	static final int MAX = 255;
	static final Color RED = new Color(Color.MAX, 0, 0);
	static final Color GREEN = new Color(0, Color.MAX, 0);
	static final Color BLUE = new Color(0, 0, Color.MAX);
	static final Color WHITE = new Color(Color.MAX, Color.MAX, Color.MAX);
	static final Color BLACK = new Color(0, 0, 0);

	private final int[] rgb; // @color

	/**
	 * Creates an RGB color. Provided values have to be in the interval [0, 255]
	 */
	Color(int r, int g, int b) {
		if (!valid(r) || !valid(g) || !valid(b))
			throw new IllegalArgumentException("invalid RGB values: " + r + ", " + g + ", " + b);

		this.rgb = new int[] { r, g, b };
	}

	/**
	 * Red value [0, 255]
	 */
	int getR() {
		return rgb[0];
	}

	/**
	 * Green value [0, 255]
	 */
	int getG() {
		return rgb[1];
	}

	/**
	 * Blue value [0, 255]
	 */
	int getB() {
		return rgb[2];
	}

	/**
	 * Obtains the luminance in the interval [0, 255].
	 */
	int getLuminance() {
		return (int) Math.round(rgb[0] * .21 + rgb[1] * .71 + rgb[2] * .08);
	}

	static boolean valid(int value) {
		return value >= 0 && value <= 255;
	}

	/**
	 * Checks if the Color A is equal to the Color B
	 */
	boolean isEqualTo(Color c) {
		if (c == null)
			return false;
		return this.rgb[0] == c.rgb[0] && this.rgb[1] == c.rgb[1] && this.rgb[2] == c.rgb[2];
	}
	
	/**
	 * Function that makes a colour brighter or darker depending on a 'value'
	 */
	Color brighter(int value) {
		int r = Math.max(Math.min(this.getR() + value, Color.MAX), 0);
		int g = Math.max(Math.min(this.getG() + value, Color.MAX), 0);
		int b = Math.max(Math.min(this.getB() + value, Color.MAX), 0);

		return new Color(r, g, b);
	}

}
