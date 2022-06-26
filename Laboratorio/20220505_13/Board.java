/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Problema 13 parte 1
 * 5 Maggio 2022
 * 
 * Della Giustina Lorenzo
 * 
 * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Classe Board:
 *
 * Modella la scacchiera per affrontare il rompicapo delle N regine
 * 
 *
 * Protocollo ( i, j rappresentano indici interi, rispettivamente di riga e
 * colonna, compresi fra 1 e la dimensione N della scacchiera):
 *
 * new Board(n)        : Board      creazione di una scacchiera nxn vuota
 * b.size()            : int        dimensione della scacchiera
 * b.queensOn()        : int        numero di regine collocate sulla scacchiera
 * b.underAttack(i,j)  : boolean    la posizione di coordinate <i, j> è minacciata?
 * b.addQueen(i,j)     : Board      nuova scacchiera con una regina in posizione <i, j> che si aggiunge alla configurazione di b
 * b.arrangement()     : String     codifica testuale della configurazione
 *  
 */

public class Board {

    private static final String ROWS = "123456789ABCDEF";
    private static final String COLS = "abcdefghijklmno";

    private final int size;                         // Dimensione della scacchiera
    private final int queens;                       // Numero di regine collocate sulla scacchiera
    private final SList<SList<Integer>> queensC;    // Lista delle coppie di coordinate in cui sono collocate le regine
    private final String config;                    // Codifica testuale

    /* Board b = new Board(n)
     * creazione di una scacchiera nxn vuota */
    public Board( int n ) {
        size = n;
        queens = 0;
        queensC = new SList<SList<Integer>>();
        config = "";
    }

    // ----- Costruttore non pubblico di supporto (nuova scacchiera con una regina in posizione <i, j> che si aggiunge alla configurazione di b)
    private Board( Board b, int i, int j ) {
        size = b.size;
        queens = b.queens + 1;
        queensC = b.queensC.cons((new SList<Integer>()).cons(j).cons(i));
        config = b.config + " " + COLS.charAt(j-1) + ROWS.charAt(i-1);
    }

    /* b.size() : int
     * dimensione della scacchiera */
    public int size() {
        return size;
    }

    /* b.queensOn () : int
     * numero di regine collocate sulla scacchiera */
    public int queensOn() {
        return queens;
    }

    /* b.underAattack(i,j) : boolean
     * la posizione di coordinate <i, j> è minacciata? */
    public boolean underAttack( int i, int j ) {
        for ( SList<SList<Integer>> k = queensC; !(k.isNull()); k = k.cdr() ) {
            int u = k.car().car();
            int v = k.car().cdr().car();
            if ( u == i || v == j || u-v == i-j || u+v == i+j ) {
                return true;
            }
        }
        return false;
    }

    /* b.addQueen(i,j) : Board
     * nuova scacchiera con una regina in posizione <i, j> che si aggiunge alla configurazione di b */
    public Board addQueen( int i, int j ) {
        return new Board( this, i, j );
    }
    
    /* b.arrangement() : String
     * codifica testuale della configurazione */
    public String arrangement() {
        return config;
    }

    public String toString() {
        return config;
    }

} // class Board
