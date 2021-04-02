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
    private static final int maxL =65536;
    private static final int maxW=16;

    public static void compress(String word) {

        boolean type=false;

        if(word.equals("r"))
        {
            BinaryStdOut.write(true);
            type=true;

        }
        else
        {
            BinaryStdOut.write(false);

        }

        TSTmod<Integer> st = new TSTmod<Integer>();
        for (int i = 0; i < R; i++)
            st.put(new StringBuilder("" + (char) i), i);
        int code = R + 1; // R is codeword for EOF

        StringBuilder current = new StringBuilder();

        char c = BinaryStdIn.readChar();

        current.append(c);

        Integer codeword = st.get(current);

        boolean nolimit=true;

        while (!BinaryStdIn.isEmpty()) {

            codeword = st.get(current);
            // TODO: read and append the next char to current
            char next = BinaryStdIn.readChar();

            current.append(next);

            if (!st.contains(current)) 
            {

                BinaryStdOut.write(codeword, W);

                
                
            
                
                if (W< maxW && code == L) 
                {
                    L *= 2;
                    W++;
                    
                    bitcount++;
                }
                
                

               
                // Add to symbol table if not full
                if(code<maxL)
                {
                    st.put(current, code++);
                }
                else 
                {
                    
                    
                    if(type)
                    {
                    st.put(current, code++);
                    
                    L=512;
                    W=9;
                    st = new TSTmod<Integer>();

                    for (int i = 0; i < R; i++)
                    st.put(new StringBuilder("" + (char) i), i);

                    code = R + 1;
                    }

                   



                }
            

               

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

    public static void expand(){
        
        boolean type = BinaryStdIn.readBoolean();
        
        String[] st = new String[maxL];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = ""; // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        
        String val = st[codeword];
        boolean nolimit = true;

        while (true) {

            
            
            BinaryStdOut.write(val);
            
     
        

            if ( W < maxW &&i<maxL && i == L) 
            {
                W++;
                L *= 2;

            }
            
            
            if( type && W==maxW && i==maxL)
            {
                W=9;
                L=512;

                st = new String[maxL];
            for (i = 0; i < R; i++)
                st[i] = "" + (char) i;
                    
                st[i++] = ""; // (unused) lookahead for EOF

                    codeword = BinaryStdIn.readInt(W);

                    val = st[codeword];

                    BinaryStdOut.write(val);

                    

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
        if (args[0].equals("-"))  
        {
           if(!(args[1].equals("n")||args[1].equals("r")))
           throw new RuntimeException("Illegal command line argument");

            compress(args[1]);
        }
        else if (args[0].equals("+")) expand();
        else throw new RuntimeException("Illegal command line argument");

        System.err.println(bitcount);
        System.err.println(W);

       
    }

  
}
