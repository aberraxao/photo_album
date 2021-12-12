/**
 * The purpose of the class Pagina is to represent a page of an album There are
 * 2 attributes to this class: - pagina of type ColorImage - fotos of type
 * vector
 */

class Pagina {
	private ColorImage pagina;
	private Foto[] fotos;

	Pagina(Foto[] fotos, int width, int height) {
		this.pagina = new ColorImage(width, height);
		this.fotos = fotos;
	}
	
	Pagina(ColorImage[] images, int width, int height) {
		this.pagina = new ColorImage(width, height);
		this.fotos = new Foto[images.length];
		
		for (int i= 0; i<images.length; i++)
			this.fotos[i].setFoto(images[i]);
	}
	
	int getWidth(){
		return this.pagina.getWidth();
	}
	
	int getHeight(){
		return this.pagina.getHeight();
	}
	
	ColorImage getPagina(){
		return this.pagina;
	}
	
	void setMozaico(ColorImage img, boolean isTransparent) {
		this.pagina = ColorImageUtil.mozaico(img, this.getWidth(), this.getHeight(), isTransparent);
	}

	
	static void test_3_1() {
		// Improve test function
		ColorImage img = new ColorImage("cat.jpeg");
		String sub = "Gato com fundo castanho.";
		String cd = "09-12-2021.";

		Foto foto1 = new Foto(img, sub, cd);
		
		int width = 500;
		int height = 1000;
		
		Foto[] fotos = {foto1}; 

		Pagina paginaNew = new Pagina(fotos, width, height);

		paginaNew.setMozaico(img, false);
		ColorImage page = paginaNew.getPagina();
		System.out.println("here");
	}
	

}