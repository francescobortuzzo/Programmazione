/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Problema 10 / parte 1
 * 7 Aprile 2022
 * 
 * Della Giustina Lorenzo
 * 
 * * * * * * * * * * * * * * * * * * * * *
 * 
 * StringSList : Liste di stringhe nello stile di Scheme
 * 
 * new StringSList()     : StringSList  | null |
 * 
 * new StringSList(e,sl) : StringSList  | (cons e sl) |
 * 
 * s.isNull()            : boolean      | (null? s) |
 * 
 * s.car()               : String       | (car s) |
 * 
 * s.cdr()               : StringSList  | (cdr s) |
 * 
 * s.cons(n)             : StringSList  | (cons n s) |
 * 
 * s.length()            : int          | (length s) |
 * 
 * s.listRef(k)          : String       | (list-ref s k) |
 * 
 * s.equals(sl)          : boolean      | (equal? s sl) |
 * 
 * s.append(sl)          : StringSList  | (append s.sl) |
 * 
 * s.reverse()           : StringSList  | (reverse s) |
 * 
 * s.toString()          : String       // visualizzazione testuale
 * 
 */

public class StringSList {

    public static final StringSList NULL_INTLIST = new StringSList();
    
    private final boolean empty;
    private final String first;
    private final StringSList rest;
    private final int length;
    
    /* null */
    public StringSList() {
        empty = true;
        first = "";
        rest = null;
        length = 0;
    }
    
    /* cons */
    public StringSList( String e, StringSList sl ) {
        empty = false;
        first = e;
        rest = sl;
        length = sl.length + 1;
    }

    /* null? */
    public boolean isNull() {
        return empty;
    }
    
    /* car */
    public String car() {
        return first;
    }
    
    /* cdr */
    public StringSList cdr() {
        return rest;
    }
    
    /* cons */
    public StringSList cons( String e ) {
        return ( new StringSList(e, this) );
    }
    
    /* length */
    public int length() {
        return length;
        /* ALTERNATIVA
        int n = 0;
        if ( !isNull() ) {
            
            StringSList r = cdr();
            n++;
            
            while ( !r.isNull() ) {
                r = r.cdr();
                n++;
            }
        }
        return n;*/
    }
    
    /* list-ref */
    public String listRef( int k ) {
        if ( k == 0 ) {
            return car();
        } else {
            return cdr().listRef( k-1 );
        }
    }
    
    /* equal */
    public boolean equals( StringSList sl ) {
        if ( isNull() || sl.isNull() ) {
            return ( isNull() && sl.isNull() );
        } else if ( car() == sl.car() ) {
            return cdr().equals( sl.cdr() );
        } else {
            return false;
        }
    }
    
    /* append */
    public StringSList append( StringSList sl ) {
        if ( isNull() ) {
            return sl;
        } else {
            return ( cdr().append( sl ) ).cons( car() );
        }
    }
    
    /* ALTERNATIVA ITERATIVA
    public StringSList append( StringSList sl ) {
        StringSList r = sl;
        
        if ( !isNull() ) {
            
            int i = length() - 1;
            while ( i >= 0 ) {
                r = r.cons( listRef( i ) );
                i--;
            }
        }
        return r;
    }*/
    
    /* reverse */
    public StringSList reverse() {
        return reverseRec( NULL_INTLIST );
    }
    
    private StringSList reverseRec( StringSList r ) {
        if ( isNull() ) {
            return r;
        } else {
            return cdr().reverseRec( r.cons( car() ) );
        }
    }    
    
    /* visualizzazione testuale */
    public String toString() {
        String desc = "(";
        
        if ( !isNull() ) {
            desc = desc + car();
            StringSList r = cdr();
            
            while ( !r.isNull() ) {
                desc = desc + ", " + r.car();
                r = r.cdr();
            }
        }
        return desc + ")";
    }
}
