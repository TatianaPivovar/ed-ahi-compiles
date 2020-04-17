// for testing
package main;

import java.io.*;
import lexer.*;
import parser.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Lexer lex = new Lexer();
    lex.scan(); // temporary call is used to test earlier states
    //Parser parse = new Parser(lex);
    //parse.program();
    System.out.write(’\n’);
  }
}
