/**
 * The purpose of the class Pagina is to represent a page of an album. There are
 * 2 attributes to this class: - pagina of type ColorImage; - fotos of type
 * vector
 */

class Pagina {
	private ColorImage pagina;
	private Foto[] fotos;

	// Constructors
	Pagina(Foto[] fotos, int width, int height) {
		/*
		 * Constructor that receives a list of Fotos and the size of the pagina.
		 */
		this.pagina = new ColorImage(width, height);
		this.fotos = fotos;
	}

	Pagina(Foto foto, int width, int height) {
		/*
		 * Constructor that receives a single Foto and the size of the pagina.
		 */
		this.pagina = new ColorImage(width, height);
		addPhoto(foto);
	}

	Pagina(ColorImage[] images, int width, int height) {
		/*
		 * Constructor that receives a list of ColorImages and the size of the pagina.
		 */
		this.pagina = new ColorImage(width, height);

		for (int i = 0; i < images.length; i++)
			addPhoto(new Foto(images[i], "", ""));
	}

	// Photo's functions and procedures
	void addPhoto(Foto foto) {
		/*
		 * Procedure that adds a Foto at the end of the pagina, i.e., Foto vector
		 */
		int i = 0;
		if (this.fotos == null) {
			this.fotos = new Foto[1];
		} else if (this.fotos[i] != null) {
			Foto[] faux = new Foto[getNbPhotos() + 1];
			while (i < getNbPhotos()) {
				faux[i] = this.fotos[i];
				i++;
			}
			this.fotos = faux;
		}
		this.fotos[i] = foto;
	}

	void removePhoto() {

	}

	// Pagina's functions and procedures
	int getWidth() {
		return this.pagina.getWidth();
	}

	int getHeight() {
		return this.pagina.getHeight();
	}

	int getNbPhotos() {
		return this.fotos.length;
	}

	ColorImage getPagina() {
		return this.pagina;
	}

	void setMozaico(ColorImage img, boolean isTransparent) {
		/*
		 * Procedure that creates a pattern to fill the pagina based on an image
		 */
		ColorImageUtil.mozaico(this.pagina, img, isTransparent);
	}

	// Test functions
	static void test_3_1() {
		// TODO: Add exception when adding a null vector
		ColorImage img = new ColorImage("cat.jpeg");
		Foto foto = new Foto(img, "Gato com fundo castanho.", "09-12-2021");

		Foto[] fotos = { foto, foto };
		Pagina pag1 = new Pagina(foto, 500, 1000);

		// ColorImage[] images = { img, img };
		// Pagina pag2 = new Pagina(images, 500, 1000);

		// Pagina pag3 = new Pagina(fotos, 500, 1000);

		pag1.setMozaico(img, true);

		return;
	}

	static void test_3_2() {
		ColorImage img1 = new ColorImage("cat.jpeg");
		ColorImage img2 = new ColorImage("photo04.png");
		ColorImage img3 = new ColorImage("photo02.png");

		Foto foto1 = new Foto(img1, "Gato com fundo castanho.", "09-12-2021");
		Foto foto2 = new Foto(img2, "Gato cinza.", "10-12-2021");
		Foto foto3 = new Foto(img3, "Gato preto anime.", "11-12-2021");

		Foto[] fotos = { foto1, foto2 };

		Pagina pag = new Pagina(fotos, 500, 1000);
		pag.addPhoto(foto3);

		return;
	}
}