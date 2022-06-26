/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Progetto “Longest Increasing Subsequence” – I (top-down)
 * 12 Maggio 2022
 * 
 * Della Giustina Lorenzo
 * 
 * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Data una sequenza s di n interi positivi, rappresentata da un array, il seguente programma in Java calcola la lunghezza
 * della più lunga sottosequenza di s strettamente crescente (llis: length of the longest increasing subsequence).
 *  
 */

public class lis {

    private final static int UNKNOWN = 0;

    /* llis
     * calcola la lunghezza della più lunga sottosequenza di s strettamente crescente */
    public static int llis( int[] s ) { // s[i] > 0 per i in [0,n-1], dove n = s.length
        return llisRec( s, 0, 0 );
    }

    /* llisRec
     * calcola la llis di s a partire dalla posizione i (imponendo il vincolo che gli elementi della sottosequenza debbano essere > t) */
    private static int llisRec( int[] s, int i, int t ) {
        if ( i == s.length ) { // i = n : coda di s vuota
            return 0;
        } else if ( s[i] <= t ) { // x = s[i] ≤ t : x non può essere scelto
            return llisRec( s, i+1, t );
        } else { // x > t : x può essere scelto o meno
            return Math.max( 1+llisRec(s,i+1,s[i]), llisRec(s,i+1,t) );
        }
    }

    /* 1. llisMem
     * applicazione della tecnica top-down di memoization in SITUAZIONI SEMPLIFICATE */
    public static int llisMemTmp( int[] s ) {
        int len = s.length;

        // creazione array sufficientemente grande usando come indici i (la posizione nella lista) e t (la soglia)
        int[][] mem = new int[len+1][len+1];

        // inizializazione array
        for (int i=0; i<=len; i++) {
            for (int j=0; j<=len; j++) {
                mem[i][j] = UNKNOWN;
            }
        }

        // chiamata procedura ricorsiva
        return llisMemTmpRec(s, 0, 0, mem);

    }

    private static int llisMemTmpRec( int[] s, int i, int t, int[][] mem) {
        if ( mem[i][t] == UNKNOWN) { // calcolo il risultato solo se non l'ho già calcolato prima
            if ( i == s.length ) { // i = n : coda di s vuota
                mem[i][t] = 0;
            } else if ( s[i] <= t ) { // x = s[i] ≤ t : x non può essere scelto
                mem[i][t] = llisMemTmpRec( s, i+1, t, mem);
            } else { // x > t : x può essere scelto o meno
                mem[i][t] = Math.max( 1+llisMemTmpRec(s, i+1, s[i], mem), llisMemTmpRec(s, i+1, t, mem) );
            }
        }
        return mem[i][t];
    }

    /* 2. llisMem
     * Applicazione della tecnica top-down di memoization in CASI più GENERALI */
    public static int llisMem( int[] s ) {
        int len = s.length;

        // creazione array sufficientemente grande usando come indici i (la posizione nella lista) e la posizione di t (la soglia) nella lista
        int[][] mem = new int[len+1][len+1];

        // inizializazione array
        for ( int i = 0; i <= len; i++ ) {
            for ( int j = 0; j <= len; j++) {
                mem[i][j] = UNKNOWN;
            }
        }

        // chiamata procedura ricorsiva
        return llisMemRec( s, 0, len, mem ); // j = len rappresenta t = 0
    }

    private static int llisMemRec( int[] s, int i, int j, int[][] mem ) {
        int t = 0;
        if ( j != s.length ) { // j = len rappresenta t = 0
            t = s[j];
        }
        if ( mem[i][j] == UNKNOWN ) { // calcolo il risultato solo se non l'ho già calcolato prima
            if ( i == s.length ) { // i = n : coda di s vuota
                mem[i][j] = 0;
            } else if ( s[i] <= t ) { // x = s[i] ≤ t : x non può essere scelto
                mem[i][j] = llisMemRec( s, i+1, j, mem );
            } else { // x > t : x può essere scelto o meno
                mem[i][j] = Math.max( 1+llisMemRec(s,i+1,i, mem), llisMemRec(s,i+1,j,mem) );
            }
        }
        return mem[i][j];
    }

} // classe lis
