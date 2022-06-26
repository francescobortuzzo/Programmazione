
public class GiuseppeFlavio {
    
    
    public static IntSList josephus( int n ) {
        
        RoundTable tav = new RoundTable(n);
        
        while (tav.numberOfKnights() > 2) { // !tav.ritualeCompletato()
            
            tav = tav.serveNeighbour();
            tav = tav.passJug();
            
        }
        return tav.servingKnights();
    }
    
    
} // class GiuseppeFlavio