package lexer;

import java.io.*;
import java.util.*;

public class Lexer {
  
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
      case '\n':
        tag = Tag.NEW_LINE;
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
        peek();
        if (_peek == '/') {
          while (_peek != '\n')
            peek();
          tag = Tag.LINE_COMMENT;
        } else
          return new Token(Tag.DIV); // to save _peek state
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
