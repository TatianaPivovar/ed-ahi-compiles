package lexer;

import java.io.*;
import java.util.*;

public class Lexer {

  public int line = 1;
  
  private char _peek = ' ';
  private Hashtable words = new Hashtable();
  
  void reserve(Word t) {
    words.put(t.lexeme, t);
  }
  
  public Lexer() {
    reserve( new Word(Tag.TRUE, "true") );
    reserve( new Word(Tag.FALSE, "false") );
  }
  
  void peek() throws IOException {
    _peek = (char)System.in.read();
  }
  
  public Token scan() throws IOException {
    for( ; ; peek() ) {
      if( _peek == ' ' || _peek == '\t' ) continue;
      else if( _peek == '\n' ) line = line + 1;
      else break;
    }

    if( Character.isDigit(_peek) ) {
      int v = 0;
      do {
        v = 10*v + Character.digit(_peek, 10);
        peek();
      } while( Character.isDigit(_peek) );
      return new Num(v);
    }

    if( Character.isLetter(_peek) ) {
      StringBuffer b = new StringBuffer();
      do {
        b.append(_peek);
        peek();
      } while( Character.isLetterOrDigit(_peek) );
  
      String s = b.toString();
      Word w = (Word)words.get(s);
  
      if( w != null ) return w;
  
      w = new Word(Tag.ID, s);
      words.put(s, w);
      return w;
    }
    Tag tag;
    switch(_peek) {
      case (char)(-1):
        tag = Tag.EOF;
        break;
      case '+':
        tag = Tag.ADD;
        break;
      case '-':
        tag = Tag.SUB;
        break;
      case '*':
        tag = Tag.MUL;
        break;
      case '/':
        tag = Tag.DIV;
        break;
      default:
        tag = Tag.UNDEFINED;
        break;
    }
    Token t = new Token(tag);
    _peek = ' ';
    return t;
  }
}
