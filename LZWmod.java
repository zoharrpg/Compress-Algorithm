/*************************************************************************
 * Compilation: javac LZW.java Execution: java LZW - < input.txt (compress)
 * Execution: java LZW + < input.txt (expand) Dependencies: BinaryIn.java
 * BinaryOut.java
 *
 * Compress or expand binary input from standard input using LZW.
 *
 *
 *************************************************************************/

public class LZWmod {
    private static final int R = 256; // number of input chars
    private static int L = 512; // number of codewords = 2^W
    private static int W = 9; // codeword width
    private static int bitcount = 0;

    public static void compress() {

        TSTmod<Integer> st = new TSTmod<Integer>();
        for (int i = 0; i < R; i++)
            st.put(new StringBuilder("" + (char) i), i);
        int code = R + 1; // R is codeword for EOF

        StringBuilder current = new StringBuilder();

        char c = BinaryStdIn.readChar();

        current.append(c);

        Integer codeword = st.get(current);

        while (!BinaryStdIn.isEmpty()) {

            codeword = st.get(current);
            // TODO: read and append the next char to current
            char next = BinaryStdIn.readChar();

            current.append(next);

            if (!st.contains(current)) 
            {

                BinaryStdOut.write(codeword, W);

                
                if (code == L) 
                {
                    L *= 2;
                    W++;
                    bitcount++;
                }
                
                

               
                // Add to symbol table if not full

               
               
                st.put(current, code++);

               

                
            

                // TODO: reset current
                char last = current.charAt(current.length() - 1);
                current = new StringBuilder();
                current.append(last);

            }

            // TODO: reset current

        }

        // TODO: Write the codeword of whatever remains

        // in current

        BinaryStdOut.write(st.get(current), W);

        BinaryStdOut.write(R, W); // Write EOF

        

        BinaryStdOut.close();

    }

    public static void expand() {
        String[] st = new String[65536];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = ""; // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        
        String val = st[codeword];

        while (true) {

            
            
            
            BinaryStdOut.write(val);

            if (i == L&& W < 16) 
            {
                
                W++;
                L *= 2;
               
                
              

            }
            
           
          

           
            codeword = BinaryStdIn.readInt(W);
        

            
            

            if (codeword == R)
                break;
            String s = st[codeword];
            if (i == codeword)
                s = val + val.charAt(0); // special case hack
            
            if (i < L)
            {
                //System.err.println(i);
                st[i++] = val + s.charAt(0);

            }
            
                
            val = s;

            
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new RuntimeException("Illegal command line argument");

        System.err.println(bitcount);
        System.err.println(W);

       
    }

  
}
