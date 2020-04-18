// for testing
package main;

import java.util.*;
import java.io.*;
import lexer.*;
//import parser.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Lexer lex = new Lexer();
    
    System.out.println("start...");
    ArrayList<ArrayList<int> > tokens = new ArrayList<ArrayList<int> >();
    Token tok = lex.scan(); // temporary block is used to test earlier states
    do {
      if (tokens.size() < lex.line)
        tokens.add(new ArrayList<int>());
      tokens.get(lex.line).add(tok.tag);
      tok = lex.scan();
    } while (tok.tag != (int)(char)(-1)); // EOL
    
    for(ArrayList<int> t: tokens)
      System.out.println(t);
    
    //Parser parse = new Parser(lex);
    //parse.program();
    System.out.println();
  }
}
