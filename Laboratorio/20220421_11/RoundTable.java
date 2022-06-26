
/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Problema 11
 * 21 Aprile 2022
 * 
 * Della Giustina Lorenzo
 * 
 * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Classe RoundTable:
 *
 * Modello alla base del problema di Giuseppe Flavio
 * (rivisto in termini di cavalieri attorno al tavolo)
 * 
 *
 * Protocollo:
 *
 * rt = new RoundTable(n)  : RoundTable   costruttore della disposizione iniziale con (int) n ≥ 2 cavalieri
 * rt.numberOfKnights()    : int          numero di cavalieri a tavola
 * rt.servingKnights()     : IntSList     coppia (lista di due elementi) di cavalieri che servono il terzo se c’è
 * rt.serveNeighbour()     : RoundTable   disposizione risultante dopo aver servito il prossimo cavaliere, che esce
 * rt.passJug()            : RoundTable   disposizione risultante dopo aver passato la brocca
 */

public class RoundTable {

    private final int num;       // numero di cavalieri a tavola
    private final IntSList jug;  // etichetta dei cavalieri che servono il terzo
    private final IntSList head; // lista di cavalieri successivi (numerati)
    private final IntSList tail; // lista rovesciata dei cavalieri rimanenti

    /* RoundTable
     * costruttore della disposizione iniziale con (int) n ≥ 2 cavalieri */
    public RoundTable( int n ) {
        // con n cavalieri
        num = n;
        jug = IntSList.NULL_INTLIST.cons(2).cons(1);
        head = range( 3, n );
        tail = IntSList.NULL_INTLIST;
    }

    // ----- Costruttore non pubblico di supporto
    private RoundTable( int n, IntSList j, IntSList h, IntSList t ) {
        num = n;
        jug = j;
        head = h;
        tail = t;
    }

    /* numberOfKnights
     * numero di cavalieri a tavola */
    public int numberOfKnights() {
        return num;
    }

    /* servingKnights
     * coppia (lista di due elementi) di cavalieri che servono il terzo se c’è */
    public IntSList servingKnights() {
        return jug;
    }

    
    //     (*)  n: (c_1, c_2) () (c_k, ..., c_4, c_3)
    //                                                    -->  n-1: (c_1, c_2) (c_4, ..., c_k) ()
    //    (**)  n: (c_1, c_2) (c_3, c_4, ..., c_j) (c_k, ..., c_j+1)
    //                                                    -->  n-1: (c_1, c_2) (c_4, ..., c_j) (c_k, ..., c_j+1)

    /* serveNeighbour
     * disposizione risultante dopo aver servito il prossimo cavaliere, che esce */
    public RoundTable serveNeighbour() {
        if ( num < 3 ) {              // meno di tre commensali
            return this;
        } else if ( head.isNull() ) { // (*)
            IntSList rev = tail.reverse();
            return new RoundTable( num-1, jug, rev.cdr(), IntSList.NULL_INTLIST );
        } else {                      // (**)
            return new RoundTable( num-1, jug, head.cdr(), tail );
        }
    }

    
    //     (*)  n: (c_1, c_2) (c_3, c_4, c_5, ..., c_j) (c_k, ..., c_j+1)
    //                                                                -->  n: (c_3, c_4) (c_5, ..., c_j) (c_2, c_1, c_k, ..., c_j+1)
    //    (**)  n: (c_1, c_2) () (c_k, ..., c_5, c_4, c_3)
    //                                                                -->  n: (c_3, c_4) (c_5, ..., c_k) (c_2, c_1)
    //   (***)  n: (c_1, c_2) () (c_3)
    //                                                                -->  n: (c_3, c_1) (c_2) ()
    //  (****)  n: (c_1, c_2) (c_3) ()
    //                                                                -->  n: (c_3, c_1) (c_2) ()
    // (*****)  n: (c_1, c_2) (c_3) (c_k, ..., c_4)
    //                                                                -->  n: (c_3, c_4) (c_5, ..., c_k) (c_2, c_1)

    /* passJug
     * disposizione risultante dopo aver passato la brocca */
    public RoundTable passJug() {
        // (a sinistra)
        if ( num < 3 ) { // meno di due commensali
            return this;
        } else if ( head.length() >= 2 ) { // (*)
            return new RoundTable(
                num,
                IntSList.NULL_INTLIST.cons(head.cdr().car()).cons(head.car()), // jug diventa la lista dei primi due di head
                head.cdr().cdr(),                                              // tolgo i primi due da head (che finiscono in jug)
                tail.cons(jug.car()).cons(jug.cdr().car()) );                  // metto i due elementi di jug in tail (al contrario)
        } else if ( head.isNull() && tail.length() >= 2 ) { // (**)
            IntSList rev = tail.reverse();                                     // creo la lista inversa di tail
            return new RoundTable(
                num,
                IntSList.NULL_INTLIST.cons(rev.cdr().car()).cons(rev.car()),   // jug diventa la lista dei primi due di rev
                rev.cdr().cdr(),                                               // head diventa rev senza i primi due elementi (che finiscono in jug)
                jug.reverse() );                                               // metto i due elementi di jug in tail (al contrario -> siccome tail è vuota bata fare il reverse di jug)
        } else if ( head.isNull() /* && tail.length() == 1 [condizione ovvia] */ ) { // (***)
            return new RoundTable(
                num,
                IntSList.NULL_INTLIST.cons(jug.car()).cons(tail.car()),
                jug.cdr(),
                IntSList.NULL_INTLIST );
        } else if ( tail.isNull() /* && head.length() == 1 [condizione ovvia] */ ) { // (****)
            return new RoundTable(
                num,
                IntSList.NULL_INTLIST.cons(jug.car()).cons(head.car()),
                jug.cdr(),
                IntSList.NULL_INTLIST );
        } else /* !tail.isNull && head.length() == 1 [condizione ovvia] */ { // (*****)
            IntSList rev = tail.reverse();                                     // creo la lista inversa di tail
            return new RoundTable(
                num,
                IntSList.NULL_INTLIST.cons(rev.car()).cons(head.car()),
                rev.cdr(),
                jug.reverse() ); 
        }
    }

    // ----- Procedura interna di supporto (private!)
    private static IntSList range( int inf, int sup ) {
        if ( inf > sup ) {
            return IntSList.NULL_INTLIST;
        } else {
            return range( inf+1, sup ).cons( inf );
        }
    }

}  // class RoundTable
