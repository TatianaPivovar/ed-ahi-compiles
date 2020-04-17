// for testing
package main;

import java.io.*;
import lexer.*;
//import parser.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Lexer lex = new Lexer();
    
    System.out.println("start...");
    int counter = 0;
    Token tok = lex.scan(); // temporary block is used to test earlier states
    do {
      ++counter;
      System.out.print(tok.tag);
      tok = lex.scan();
    } while (tok.tag != 0 && counter < 30);
    
    //Parser parse = new Parser(lex);
    //parse.program();
    System.out.println();
  }
}
