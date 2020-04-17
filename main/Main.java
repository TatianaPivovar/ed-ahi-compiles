// for testing
package main;

import java.io.*;
import lexer.*;
//import parser.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Lexer lex = new Lexer();
    
    System.out.write("start...\n");
    Token tok = lex.scan(); // temporary block is used to test earlier states
    do {
      System.out.write(tok.tag);
      break;
      tok = lex.scan();
    } while (tok.tag != 0);
    
    //Parser parse = new Parser(lex);
    //parse.program();
    System.out.write('\n');
  }
}
