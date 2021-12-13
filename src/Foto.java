/**
 * The purpose of the class Foto is to create and manipulate photos with 5 attributes: 
 * - foto of type ColorImage 
 * - subtitle of type String
 * - creation of type String
 * - x, y of type int to represent the positions in the Pagina
 */
class Foto {
	private ColorImage foto;
	private String subtitle;
	private String creationDate;
	private int x;
	private int y;

	Foto(ColorImage foto, String sub, String cd) {
		this.foto = foto;
		this.subtitle = sub;
		this.creationDate = cd;
		this.x = 0;
		this.y = 0;
	}

	void setFoto(ColorImage foto) {
		this.foto = foto;
	}

	void setSubtitle(String sub) {
		this.subtitle = sub;
	}

	void setCreationDate(String cd) {
		this.creationDate = cd;
	}

	void setPositions(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	ColorImage getFoto() {
		return this.foto;
	}

	String getSubtitle() {
		return this.subtitle;
	}

	String getCreationDate() {
		return this.creationDate;
	}

	int getX() {
		return this.x;
	}

	int getY() {
		return this.y;
	}

	static void test() {
		ColorImage img = new ColorImage("photo04_2.png");
		String sub = "Gato com fundo branco.";
		String cd = "09-12-2021.";

		Foto fotoNew = new Foto(img, sub, cd);
	}

	static void test_2_1() {
		ColorImage img = new ColorImage("photo04_2.png");
		String sub = "Gato com fundo branco.";
		String cd = "09-12-2021";

		Foto fotoNew = new Foto(img, sub, cd);

		sub = "Gato cinza.";
		fotoNew.setSubtitle(sub);
		
		return;
	}

	static void test_2_2() {
		ColorImage img = new ColorImage("photo04_2.png");
		String sub = "Gato com fundo branco.";
		String cd = "09-12-2021.";

		Foto fotoNew = new Foto(img, sub, cd);

		cd = "01-12-2021";
		fotoNew.setCreationDate(cd);
		
		return;
	}

	static void test_2_3() {
		ColorImage img = new ColorImage("photo04_2.png");
		String sub = "Gato com fundo branco.";
		String cd = "09-12-2021.";

		Foto fotoNew = new Foto(img, sub, cd);

		int x = 10, y = 20;
		fotoNew.setPositions(x, y);

		x = 4;
		fotoNew.setX(x);

		y = 30;
		fotoNew.setY(y);
		
		return;
	}

}