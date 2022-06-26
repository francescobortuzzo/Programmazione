public class SList<T> {
    
    // variabili d'istanza - sostituisci l'esempio che segue con il tuo
    private final boolean empty;
    private final T first;
    private final SList<T> rest;

    /**
     * Costruttore degli oggetti di classe IntSList
     */
    
    // new SList() : SList<T> [ null ]
    
    public SList() {
        
        empty = true;
        first = null;
        rest = null;
    }
    
    public SList( T n, SList<T> s )
    {
        // inizializza le variabili d'istanza
        empty = false;
        first = n;
        rest = s;
    }

    // s.isNull()     : boolean   | (null? s) |

    public boolean isNull() {
        
        return empty;
    }
    
    // s.car()        : int       | (car s) |

    public T car() {
        
        return first;
    }
    
    // s.cdr()        : IntSList  | (cdr s) |
    
    public SList<T> cdr() {
        
        return rest;
    }
    
    // s.cons(n)      : IntSList  | (cons n s) |
    
    public SList<T> cons(T n) {
        
        return (new SList(n, this));
    }
    
    
    // -------------------------------
    
    // length
    public int length() {
        int n = 0;
        
        if ( !isNull() ) {
            
            SList<T> r = cdr();
            n++;
            
            while ( !r.isNull() ) {
                r = r.cdr();
                n++;
            }
        }
        return n;
    }
    
    // list-ref
    public T listRef( int i ) {
        
        if ( i == 0 ) {
            return car();
        } else {
            return cdr().listRef( i-1 );
        }
    }
    
    // equal
    public boolean equals( SList<T> s ) {
        
        if ( isNull() || s.isNull() ) {
            return ( isNull() && s.isNull() );
        } else if ( car() == s.car() ) {
            
            return cdr().equals( s.cdr() );
        } else {
            return false;
        }
    }
    
    // append
    public SList<T> append( SList<T> s ) {
        
        SList<T> r = s;
        
        if ( !isNull() ) {
            
            int i = length() - 1;
            while ( i >= 0 ) {
                r = r.cons( listRef( i ) );
                i--;
            }
        }
        return r;
    }
    
    // reverse
    public SList<T> reverse() {
        
        return reverseRec( new SList() );
    }
    
    private SList<T> reverseRec( SList<T> r ) {
        
        if ( isNull() ) {
            return r;
        } else {
            return cdr().reverseRec( r.cons( car() ) );
        }
    }
    
    // -------------------------------
    
    
    public String toString() {
        
        String desc = "(";
        
        if ( !isNull() ) {
            
            desc = desc + car().toString();
            SList<T> r = cdr();
            
            while ( !r.isNull() ) {
                desc = desc + ", " + r.car().toString();
                r = r.cdr();
            }
        }
        return desc + ")";
    }
}
