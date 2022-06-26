public class Queens {
    
    public static int numberOfSolutions(int n) {
        
        return numberOfCompletions(new Board(n));
    }
    
    private static int numberOfCompletions( Board board ) {
        
        int n = board.size();
        int q = board.queensOn();
        int count = 0;
        
        if (q == n) {
            return 1;
        } else {
            
            int i = q + 1;
            
            for (int j = 1; j <= n; j++) {
                if (!board.underAttack(i,j)) {
                    count = count + numberOfCompletions(board.addQueen(i,j));
                }
            }
        }
        
        return count;
    }
    
    
    public static SList<Board> listOfSolutions(int n) {
        
        return listOfCompletions(new Board(n));
    }
    
    private static SList<Board> listOfCompletions( Board board ) {
        
        int n = board.size();
        int q = board.queensOn();
        
        if (q == n) {
            return (new SList()).cons(board);
        } else {
            
            SList<Board> list = new SList();
            int i = q + 1;
            
            for (int j = 1; j <= n; j++) {
                if (!board.underAttack(i,j)) {
                    list = list.append(listOfCompletions(board.addQueen(i,j)));
                }
            }
            return list;
        }
    }
} // class queens