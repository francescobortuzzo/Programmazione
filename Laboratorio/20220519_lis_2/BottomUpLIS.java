/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Progetto “Longest Increasing Subsequence” – II (bottom-up)
 * 19 Maggio 2022
 * 
 * Della Giustina Lorenzo
 * 
 * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Data una sequenza s di n interi positivi, rappresentata da un array, il seguente programma in Java calcola la lunghezza
 * della più lunga sottosequenza di s strettamente crescente (llis: length of the longest increasing subsequence).
 *  
 */

public class BottomUpLIS {

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

    // Length of Longest Increasing Subsequence (LLIS):
    // Programmazione dinamica bottom-up

    public static int llisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRec
        // relativi a diversi valori degli argomenti

        for ( int j=0; j<=n; j=j+1 ) {

            // --------------------------------------------------
            //  Inserisci qui i comandi per registrare i valori
            //  corrispondenti ai casi base della ricorsione
            // --------------------------------------------------
            mem[0][j] = 0;
        }

        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {

                // ------------------------------------------------
                //  Inserisci qui le strutture di controllo
                //  appropriate e i comandi per registrare
                //  i valori corrispondenti ai casi ricorsivi
                // ------------------------------------------------
                int t = (j == n) ? 0 : s[j];
                boolean k = ( i>j ) || ( j==n );
                if ( s[i]>t && k ) {
                    mem[i][j] = Math.max( mem[i+1][i]+1, mem[i+1][j] );
                } else if ( k ) {
                    mem[i][j] = mem[i+1][j];
                }
            }}

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        return  mem[0][n]/* elemento appropriato della matrice */;

        // ----------------------------------------------------
    }

    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up

    public static int[] lisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // 1. Matrice: valori delle ricorsioni di llisRec
        //    calcolati esattamente come per llisDP

        // ------------------------------------------------
        //  Replica qui il codice del corpo di llisDP
        //  che registra nella matrice i valori
        //  corrispondenti alle ricorsioni di llisRec
        // ------------------------------------------------
        for ( int j=0; j<=n; j=j+1 ) {

            mem[0][j] = 0;
        }

        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {

                int t = (j == n) ? 0 : s[j];
                boolean k = ( i>j ) || ( j==n );
                if ( s[i]>t && k ) {
                    mem[i][j] = Math.max( mem[i+1][i]+1, mem[i+1][j] );
                } else if ( k ) {
                    mem[i][j] = mem[i+1][j];
                }
            }}

        // 2. Cammino attraverso la matrice per ricostruire
        //    un esempio di Longest Increasing Subsequence

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        int m = mem[0][n] /* elemento appropriato della matrice */;

        // ----------------------------------------------------

        int[] r = new int[ m ];  // per rappresentare una possibile LIS

        // ----------------------------------------------------
        //  Introduci e inizializza qui gli indici utili
        //  per seguire un cammino attraverso la matrice e
        //  per assegnare gli elementi della sottosequenza r
        // ----------------------------------------------------
        int i = 0;
        int j = n;
        int k = 0;

        while ( mem[i][j] > 0 ) {

            int t = ( j == n ) ? 0 : s[j];

            // --------------------------------------------------
            //  Inserisci qui strutture di controllo e comandi
            //  per scegliere e seguire un percorso appropriato
            //  attraverso la matrice in modo da ricostruire in
            //  r una possibile LIS relativa alla sequenza s
            // --------------------------------------------------
            if ( mem[i][j]==mem[i+1][j] ) {
                i++;
            } else {
                r[k] = s[i];
                j = i;
                i++;
                k++;
            }
        }

        return r;                // = LIS relativa alla sequenza s
    }

}  // class BottomUpLIS
