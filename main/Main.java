// for testing
package main;

import java.io.*;
import lexer.*;
//import parser.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Lexer lex = new Lexer();
    
    System.out.println("start...");
    Token tok = lex.scan(); // temporary block is used to test earlier states
    do {
      System.out.print(lex.line);
      System.out.print(". ");
      System.out.println(tok.tag);
      tok = lex.scan();
    } while (tok.tag != -1); // EOL
    
    //Parser parse = new Parser(lex);
    //parse.program();
    System.out.println();
  }
}
