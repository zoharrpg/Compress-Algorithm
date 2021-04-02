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
    private static final int R = 256;       // number of input chars
    private static int L = 512;            // number of codewords = 2^W
    private static int W = 9;             // codeword width
    private static int bitcount = 0;
    private static final int maxL =65536;  //  max number of the codeword;
    private static final int maxW=16;      // max codeword width

    public static void compress(String word) {

        boolean type=false;          // type for whether it is reset or no reset, false for no reset
                                    // true for reset 

        if(word.equals("r"))
        {
            BinaryStdOut.write(true);     // write 1 bit for to mark the file as reset compress
            type=true;

        }
        else
        {
            BinaryStdOut.write(false);    // write 1 bit for mark the file as no reset compress

        }

        TSTmod<Integer> st = new TSTmod<Integer>();     // set up the DLB version codebook, make method in TST as StringBuilder
        for (int i = 0; i < R; i++)
            st.put(new StringBuilder("" + (char) i), i); // set up the initial codebook
        int code = R + 1;                               // R is codeword for EOF

        StringBuilder current = new StringBuilder();    // 

        char c = BinaryStdIn.readChar();          // read next Character

        current.append(c);                    

        Integer codeword = st.get(current);       // get the codeword

        

        while (!BinaryStdIn.isEmpty()) {        

            codeword = st.get(current);
            
            char next = BinaryStdIn.readChar();     // read and append the next char to current

            current.append(next);

            if (!st.contains(current))            // find the longest prefix 
            {

                BinaryStdOut.write(codeword, W);     // output to compress

                
                
            
                
                if (W< maxW && code == L)  //check whether codeword width reach the max, also check whether symbol table full
                {
                    L *= 2;                
                    W++;                   // double size the number of codeword, and increment the codeword width, making it become varible length codeword

                                         // if the width reach the max in the no rset type, the codeword will remain the same with double size and increment of the symbol table
                    
                    //bitcount++;
                }
                
                

               
                
                if(code<maxL)                // check whether symbil reach the max of codeword
                {
                    st.put(current, code++);     //Add to symbol table 
                }
                else                        // if it reach the max
                {
                    
                    
                    if(type)              // if the compress type is reset 
                    {
                    
                
                    st.put(current, code++);
                    
                    L=512;
                    W=9;
                    st = new TSTmod<Integer>();

                    for (int i = 0; i < R; i++)
                    st.put(new StringBuilder("" + (char) i), i);        // reset the whole codebook

                    code = R + 1;
                    
                }

                   



                }
            

               

              
                char last = current.charAt(current.length() - 1);
                current = new StringBuilder();                           // reset current
                current.append(last);

            }

           

        }

        //  Write the codeword of whatever remains

        // in current

        BinaryStdOut.write(st.get(current), W);                 //  Write the codeword of whatever remains

                                                                // in current

        BinaryStdOut.write(R, W); // Write EOF

        

        BinaryStdOut.close();

    }

    public static void expand(){
        
        boolean type = BinaryStdIn.readBoolean();                // check whether the compress is reset type
        
        String[] st = new String[maxL];                         // String array for codeword, make the size become the max
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = ""; // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);              // read the codeword
        
        String val = st[codeword];                          //get the value of the codeword
       

        while (true) {

            
            
            BinaryStdOut.write(val);                  // output value to expand
            
     
        

            if ( W < maxW &&i<maxL && i == L)         // when current W and L is all used 
            {
                W++;                                    // expanding by using increment of the length of codeword width
                L *= 2;

            }
            
            
            if( type && W==maxW && i==maxL)        // if the compress type is reset
            {
                W=9;                             // reset the codebook
                L=512;

                st = new String[maxL];
            for (i = 0; i < R; i++)
                st[i] = "" + (char) i;
                    
                st[i++] = ""; // (unused) lookahead for EOF

                    codeword = BinaryStdIn.readInt(W);               // reset the condition

                    val = st[codeword];

                    BinaryStdOut.write(val);

                    

            }
        

            
           codeword = BinaryStdIn.readInt(W);
            
            
            if (codeword == R)                      // expand finished
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
           if(!(args[1].equals("n")||args[1].equals("r")))         // check whether command line is correct
           throw new RuntimeException("Illegal command line argument");

            compress(args[1]);
        }
        else if (args[0].equals("+")) expand();
        else throw new RuntimeException("Illegal command line argument");

       // System.err.println(bitcount);
       // System.err.println(W);

       
    }

  
}
