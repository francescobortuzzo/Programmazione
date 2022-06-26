
/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Problema 10 / parte 2
 * 7 Aprile 2022
 * 
 * Della Giustina Lorenzo
 * 
 */

public class btr {
    /* btr
     * data una stringa che rappresenta un intero non negativo nella notazione BTR ritorna il successivo
     */
    public static String btrSucc( String btr ) {
        int n = btr.length();
        char lsb = btr.charAt( n - 1 );
        if ( n == 1 ) {
            if ( lsb == '+' ) {
                return "+-";
            } else {
                return "+";
            }
        } else {
            String pre = btr.substring(0, n - 1);
            if ( lsb == '+' ) {
                return btrSucc(pre) + "-";
            } else {
                return pre + ((lsb == '-') ? "." : "+");
            }
        }
    }
    
    /* btrList
     * data una stringa che rappresenta un intero non negativo nella notazione BTR e un intero non negativo restituisce la lista di n interi consecutivi in notazione BTR
     */
    public static StringSList btrList( String btr, int n ) {
        if( n == 0 ) {
            return new StringSList();
        } else {
            return btrList( btrSucc( btr ), n-1 ).cons( btr );
        }
    }
}
