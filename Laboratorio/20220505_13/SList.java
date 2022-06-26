import java.util.function.*;

public class SList<T> {
    

    private final boolean empty;
    private final T first;
    private final SList rest;

    /**
     * Costruttore degli oggetti di classe  IntSList
     */
    
    // new BoardSList() : BoardSList [ null ]
    
    public SList()
    {
        // inizializza le variabili d'istanza
        empty = true;
        first = null;
        rest = null;
    }
    
    public SList( T b, SList<T> s )
    {
        // inizializza le variabili d'istanza
        empty = false;
        first = b;
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
    
    public SList<T> cons( T b ) {
        
        return (new SList<T>(b, this));
    }
    
    
    // -------------------------
    
    // s.length:
    
    public int length(){
        if (isNull()) {
            return 0;
        } else {
            return 1 + cdr().length();
        }
    }
    
    // list-ref:
    
    public T listRef( int i ){
        if ( i == 0) {
            return car();
        } else {
            return cdr().listRef( i-1 );
        }
    }
    
    // equal
    
    public boolean equals( SList<T> s ) {
        
        if ( isNull() || s.isNull() ) {
            return ( isNull() && s.isNull() );
        } else if ( car().equals( s.car() ) ) {
            return cdr().equals( s.cdr() );
        } else {
            return false;
        }
        
    }
    
    // append
    
    public SList<T> append (SList<T> s){
        if ( isNull() ) {
            return s;
        } else {
            return cdr().append(s).cons(car());
        }
    }
    
    // reverse
    
    public SList<T> reverse () {
        return reverseRec( new SList<T>() );
    }

    private SList<T> reverseRec( SList<T> s ) {
        if ( isNull() ) {
            return s;
        } else {
            return cdr().reverseRec(s.cons(car()));
        }
    }
    
    
    public boolean inList( T s ) {
        
        for (int i = 0; i < length(); i++) {
            if ( listRef(i) ==  s) {
                return true;
            }
        }
        
        return false;
        
    }
    
    // -------------------------
    
    public String toString() {
        
        String desc = "[(";
        
        if ( !isNull() ) {
            
            desc = desc + car().toString();
            SList r = cdr();
            
            while ( !r.isNull() ) {
                desc = desc + ") (" + r.car().toString();
                r = r.cdr();
            }
        }
        return desc + ")]";
    }
    
}