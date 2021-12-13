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
		addFoto(foto);
	}

	Pagina(ColorImage[] images, int width, int height) {
		/*
		 * Constructor that receives a list of ColorImages and the size of the pagina.
		 */

		this.pagina = new ColorImage(width, height);

		for (int i = 0; i < images.length; i++)
			addFoto(new Foto(images[i], "", ""));
	}
	
	int getNbFotos() {
		/*
		 * Function that returns the number of fotos
		 */
		return this.fotos.length;
	}
	
	int getWidth() {
		/*
		 * Function that returns the width of a pagina
		 */
		return this.pagina.getWidth();
	}

	int getHeight() {
		/*
		 * Function that returns the height of a pagina
		 */
		return this.pagina.getHeight();
	}

	ColorImage getPagina(boolean isTransparent) {
		/*
		 * Function that obtains the visualization of a pagina
		 */
		return this.pagina;
	}
	
	void addFoto(Foto foto) {
		/*
		 * Procedure that adds a Foto at the end of the pagina, i.e., Foto vector
		 */

		int i = 0;
		if (this.fotos == null) {
			this.fotos = new Foto[1];
		} else if (this.fotos[i] != null) {
			Foto[] faux = new Foto[getNbFotos() + 1];
			while (i < getNbFotos()) {
				faux[i] = this.fotos[i];
				i++;
			}
			this.fotos = faux;
		}
		this.fotos[i] = foto;
	}

	void removeFoto(int pos) {
		/*
		 * Procedure that removes a Foto from a given position of the pagina, shifting
		 * the remaining Fotos
		 */

		if (getNbFotos() == 0)
			throw new IllegalArgumentException("It's not possible to remove the Foto as there are none.");
		else if (pos < 0 || pos >= this.getNbFotos())
			throw new IllegalArgumentException("The position of the Foto to remove that was provided, " + pos
					+ ", needs to be " + "widhtin the size of the Foto vector, i.e., between 0 and "
					+ (this.getNbFotos() - 1) + ".");
		else {
			Foto[] faux = new Foto[getNbFotos() - 1];
			for (int i = 0; i < faux.length; i++)
				if (i < pos)
					faux[i] = this.fotos[i];
				else if (i > pos)
					faux[i] = this.fotos[i];

			this.fotos = faux;
		}
	}

	void setMozaico(ColorImage img, boolean isTransparent) {
		/*
		 * Procedure that creates a pattern to fill the pagina based on an image
		 */
		ColorImageUtil.mozaico(this.pagina, img, isTransparent);
	}

	void switchFotoPos(int pos1, int pos2) {
		/*
		 * Procedure that switches the position of 2 Fotos given their positions.
		 */

		if (pos1 < 0 || pos2 < 0 || pos1 >= this.getNbFotos() || pos2 >= this.getNbFotos())
			throw new IllegalArgumentException("One or both positions to be switched are not allowed (" + pos1 + ", "
					+ pos2 + "). Please ensure that both positions are between 0 and " + (this.getNbFotos() - 1) + ".");
		else if (pos1 != pos2) {
			// If the positions are different performs the switch
			Foto faux = this.fotos[pos1];
			this.fotos[pos1] = this.fotos[pos2];
			this.fotos[pos2] = faux;
		}
	}

	void autoFotoPosition(boolean isTransparent) {
		/*
		 * Procedure that positions the Fotos in the page, leaving a margin of 5px from
		 * the margins and between Fotos
		 */

		int x = 5, y = 5, i = 0, maxHeight = 0;

		while ((y + 5) < this.getHeight() && i < getNbFotos()) {
			while ((x + 5) < getWidth() && i < getNbFotos()) {
				ColorImageUtil.paste(getPagina(true), this.fotos[i].getFoto(), x, y, isTransparent);
				this.fotos[i].setPositions(x, y);
				x = x + 5 + this.fotos[i].getFoto().getWidth();
				maxHeight = Math.max(maxHeight, this.fotos[i].getFoto().getHeight());
				i++;
			}
			x = 5;
			y = y + 5 + maxHeight;
			maxHeight = 0;
		}
	}

	// Test functions
	static void test_3_5() {
		ColorImage img1 = new ColorImage("photo01.png");
		ColorImage img2 = new ColorImage("photo02.png");
		ColorImage img3 = new ColorImage("photo03.png");
		ColorImage img4 = new ColorImage("photo04_2.png");
		ColorImage img5 = new ColorImage("photo05.png");

		Foto foto1 = new Foto(img1, "Gato tigre.", "09-12-2021");
		Foto foto2 = new Foto(img2, "Gato preto anime.",  "10-12-2021");
		Foto foto3 = new Foto(img3, "Marie.", "11-12-2021");
		Foto foto4 = new Foto(img4, "Gato cinza.", "12-12-2021");
		Foto foto5 = new Foto(img5, "Gato surpreso.", "13-12-2021");
	
		Foto[] fotos1 = { foto1, foto2, foto3, foto4 , foto5};
		Pagina pag1 = new Pagina(fotos1, 500, 1000);
		pag1.autoFotoPosition(true);
		ColorImage pagPreview = pag1.getPagina(true);
		
		Foto[] fotos2 = {};
		Pagina pag2 = new Pagina(fotos2, 500, 1000);
		pag2.autoFotoPosition(true);
		ColorImage pagPreview2 = pag2.getPagina(true);
		
		return;
	}
	
	static void test_3_4() {
		ColorImage img1 = new ColorImage("cat.jpeg");
		ColorImage img2 = new ColorImage("photo04.png");
		ColorImage img3 = new ColorImage("photo02.png");

		Foto foto1 = new Foto(img1, "Gato com fundo castanho.", "09-12-2021");
		Foto foto2 = new Foto(img2, "Gato cinza.", "10-12-2021");
		Foto foto3 = new Foto(img3, "Gato preto anime.", "11-12-2021");
		
		/*
		Foto[] fotos1 = { foto1, foto2, foto3 };
		Pagina pag1 = new Pagina(fotos1, 500, 1000);
		pag1.switchFotoPos(-1, 0);
		*/
	
		Foto[] fotos2 = { foto1, foto2, foto3 };
		Pagina pag2 = new Pagina(fotos2, 500, 1000);
		pag2.autoFotoPosition(true);
		ColorImage pagPreview2 = pag2.getPagina(true);
		
		Pagina pag3 = new Pagina(fotos2, 500, 1000);
		pag3.switchFotoPos(0, 2);
		pag3.autoFotoPosition(true);
		ColorImage pagPreview3 = pag3.getPagina(true);
		
		return;
	}
	
	static void test_3_3() {
		ColorImage img1 = new ColorImage("cat.jpeg");
		ColorImage img2 = new ColorImage("photo04.png");
		ColorImage img3 = new ColorImage("photo02.png");

		Foto foto1 = new Foto(img1, "Gato com fundo castanho.", "09-12-2021");
		Foto foto2 = new Foto(img2, "Gato cinza.", "10-12-2021");
		Foto foto3 = new Foto(img3, "Gato preto anime.", "11-12-2021");
		
		/*
		Foto[] fotos1 = { foto1, foto2, foto3 };
		Pagina pag1 = new Pagina(fotos1, 500, 1000);
		pag1.removeFoto(-1);
		*/
		/*
		Foto[] fotos2 = { };
		Pagina pag2 = new Pagina(fotos2, 500, 1000);
		pag2.removeremoveFoto(1);
		*/

		Foto[] fotos3 = { foto1, foto2, foto3 };
		Pagina pag3 = new Pagina(fotos3, 500, 1000);
		pag3.removeFoto(2);
		pag3.autoFotoPosition(true);
		ColorImage pagPreview = pag3.getPagina(true);
		
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
		pag.addFoto(foto3);
		
		pag.autoFotoPosition(true);
		ColorImage pagPreview = pag.getPagina(true);

		return;
	}
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
		
		pag1.autoFotoPosition(true);
		ColorImage pagPreview = pag1.getPagina(true);

		return;
	}

}
