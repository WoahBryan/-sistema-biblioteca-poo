package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa un libro físico en la biblioteca.
 * Se define como final para impedir subclases.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public final class Libro extends Material {
    private String isbn;
    private int numeroPaginas;
    private Autor autor;
    private Editorial editorial;

    /**
     * Constructor vacío requerido por la rúbrica.
     */
    public Libro() {
        super();
        this.isbn = "";
        this.numeroPaginas = 0;
        this.autor = new Autor();
        this.editorial = new Editorial();
    }

    /**
     * Constructor con los atributos mínimos indispensables.
     * 
     * @param id Identificador entero único.
     * @param titulo Título del libro.
     * @param isbn Código ISBN internacional.
     */
    public Libro(int id, String titulo, String isbn) {
        super(id, titulo);
        this.isbn = isbn;
        this.numeroPaginas = 0;
    }

    /**
     * Obtiene el ISBN del libro.
     * @return isbn.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN.
     * @param isbn código a asignar.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el número de páginas.
     * @return número de páginas.
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Establece el número de páginas.
     * @param numeroPaginas páginas a asignar.
     */
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Obtiene el autor del libro.
     * @return objeto Autor.
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     * @param autor objeto Autor a asignar.
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * Obtiene la editorial.
     * @return objeto Editorial.
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * Establece la editorial del libro.
     * @param editorial objeto Editorial a asignar.
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return super.toString() + " Libro{" + "isbn=" + isbn + ", numeroPaginas=" + numeroPaginas + ", autor=" + autor + ", editorial=" + editorial + '}';
    }
}