/**
 * The purpose of the class Album is to represent an album with a certain
 * dimension and number of pages.
 */
class Album {
	private Pagina[] paginas;
	int currentPage;

	Album(int width, int height, int nb) {
		/*
		 * Constructor builds the Album based on the dimensions and number of pages.
		 */

		this.currentPage = 0;
		this.paginas = new Pagina[nb];
		Foto foto = null;

		for (int i = 0; i < nb; i++)
			this.paginas[i] = new Pagina(foto, width, height);
	}

	int getNbPages() {
		/*
		 * Gets the number of pages.
		 */

		return this.paginas.length;
	}

	void addPagina(Pagina page, int pos) {
		/*
		 * Procedure that adds a page in a given position.
		 */

		this.paginas[pos] = page;
	}

	void next() {
		/*
		 * Procedure that moves the cursor 'currentPage' to the next page.
		 */

		if (currentPage == getNbPages() - 1)
			throw new IllegalStateException("You were on the last page. There are no more pages.");
		else
			this.currentPage++;
	}

	void prev() {
		/*
		 * Procedure that moves the cursor 'currentPage' to the previous page.
		 */

		if (currentPage == 0)
			throw new IllegalStateException("You were on the first page. There are no more pages.");
		else
			this.currentPage--;
	}

	ColorImage getCurrentPagina(boolean isTransparent) {
		/*
		 * Function that obtains the visualization of a pagina of an ALbum
		 */

		return this.paginas[currentPage].getPagina(isTransparent);
	}

	void switchPaginaPos(int pos1, int pos2) {
		/*
		 * Procedure that switches the position of 2 Paginas given their positions.
		 */

		if (pos1 < 0 || pos2 < 0 || pos1 >= this.getNbPages() || pos2 >= this.getNbPages())
			throw new IllegalArgumentException("One or both positions to be switched are not allowed (" + pos1 + ", "
					+ pos2 + "). Please ensure that both positions are between 0 and " + (this.getNbPages() - 1) + ".");
		else if (this.paginas[pos1] == null || this.paginas[pos2] == null)
			throw new NullPointerException("At least one of the Paginas is null (" + pos1 + ", " + pos2 + ")");
		else if (pos1 != pos2) {
			// If the positions are different performs the switch
			Pagina faux = this.paginas[pos1];
			this.paginas[pos1] = this.paginas[pos2];
			this.paginas[pos2] = faux;
		}
	}

	// Test functions
	static void test() {
		Album alb = new Album(500, 1000, 5);
		ColorImage img = new ColorImage("cat.jpeg");
		Foto foto = new Foto(img, "Gato com fundo castanho.", "09-12-2021");

		Foto[] fotos = { foto, foto };
		Pagina pag1 = new Pagina(foto, 500, 1000);
		pag1.setMozaico(img, true);
		alb.addPagina(pag1, 0);

		// ColorImage currentPagePreview = alb.getCurrentPagina(true);
		alb.switchPaginaPos(0, 1);
		// ColorImage currentPagePreview2 = alb.getCurrentPagina(true);
		alb.next();
		alb.switchPaginaPos(0, 1);
		alb.prev();
		ColorImage currentPagePreview3 = alb.getCurrentPagina(true);

		return;
	}

}