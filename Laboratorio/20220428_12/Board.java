/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Problema 12
 * 28 Aprile 2022
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

    private final int size;                 // Dimensione della scacchiera
    private final int queens;               // Numero di regine collocate sulla scacchiera
    private final SList<Integer> row;       // Codifica numerica delle righe
    private final SList<Integer> col;       // Codifica numerica delle colonne
    private final SList<Integer> dAsc;      // Codifica numerica delle diagonali ascendenti
    private final SList<Integer> dDes;      // Codifica numerica delle diagonali discendenti
    private final String config;            // Codifica testuale

    /* Board b = new Board(n)
     * creazione di una scacchiera nxn vuota */
    public Board( int n ) {
        size = n;
        queens = 0;
        row = new SList();
        col = new SList();
        dAsc = new SList();
        dDes = new SList();
        config = "";
    }

    // ----- Costruttore non pubblico di supporto (nuova scacchiera con una regina in posizione <i, j> che si aggiunge alla configurazione di b)
    private Board( Board b, int i, int j ) {
        size = b.size();
        queens = b.queensOn() + 1;
        row = b.row.cons(i);
        col = b.col.cons(j);
        dAsc = b.dAsc.cons(i - j);
        dDes = b.dDes.cons(i + j);
        config = b.arrangement() + " " + COLS.charAt(j-1) + ROWS.charAt(i-1);
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
        SList<Integer> row_tmp = row;
        SList<Integer> col_tmp = col;
        SList<Integer> dAsc_tmp = dAsc;
        SList<Integer> dDes_tmp = dDes;

        while (row_tmp.length() >= 1) {
            if (i == row_tmp.car() || j == col_tmp.car() || (i - j) == dAsc_tmp.car() || (i + j) == dDes_tmp.car()) {
                return true;
            } else {
                row_tmp = row_tmp.cdr();
                col_tmp = col_tmp.cdr();
                dAsc_tmp = dAsc_tmp.cdr();
                dDes_tmp = dDes_tmp.cdr();
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
