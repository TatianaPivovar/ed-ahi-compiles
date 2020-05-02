package lexer;

import java.io.*;
import java.util.*;

public class Lexer {
  
  public int line_num = 0; // multiline comments can contain new lines, but not the NEW_LINE token
  
  private char _peek = ' ';
  private Hashtable words = new Hashtable();
  
  void reserve(Word t) {
    words.put(t.lexeme, t);
  }
  
  public Lexer() {
    reserve( new Word(Tag.TRUE, "true") );
    reserve( new Word(Tag.FALSE, "false") );
  }
  
  char peek() throws IOException {
    _peek = (char)System.in.read();
    return _peek;
  }
  
  float addFractionToInt(int val) throws IOException{
    float frac = 0.0f;
    float base = 0.1f;
    while (Character.isDigit(peek())) {
      frac += Character.digit(_peek, 10) * base;
      base *= 0.1;
    }
    return val + frac;
  }
  
  public Token scan() throws IOException {
    for( ; _peek == ' ' || _peek == '\t'; peek() ) {}

    if( Character.isDigit(_peek) ) {
      int v = 0;
      do {
        v = 10*v + Character.digit(_peek, 10);
      } while( Character.isDigit(peek()) );
      if (_peek == '.') {
        return new Float(addFractionToInt(v));
      } else
        return new Num(v);
    }

    if( Character.isLetter(_peek) ) {
      StringBuffer b = new StringBuffer();
      do {
        b.append(_peek);
      } while( Character.isLetterOrDigit(peek()) );
  
      String s = b.toString();
      Word w = (Word)words.get(s);
  
      if( w != null ) return w;
  
      w = new Word(Tag.ID, s);
      words.put(s, w);
      return w;
    }
    Tag tag = Tag.UNDEFINED;
    switch(_peek) {
      case (char)(-1):
        tag = Tag.EOF;
        break;
      case '\n':
        ++line_num;
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
        if (peek() == '/') {
          while (peek() != '\n') ;
          return new Token(Tag.LINE_COMMENT);
        } else if (_peek =='*') {
          for (;;) {
            if (peek() == '*')
              if (peek() == '/') {
                peek();
                return new Token(Tag.MULTILINE_COMMENT);
              }
            else if (_peek == '\n')
              ++line_num;
          }
        } else
          return new Token(Tag.DIV); // to save _peek state
      case '!':
        if (peek() == '=') {
          peek();
          return new Token(Tag.NE);
        } else
          return new Token(Tag.NOT);
      case '=':
        if (peek() == '=') {
          peek();
          return new Token(Tag.EQ);
        }
        break;
      case '<':
        if (peek() == '=') {
          peek();
          return new Token(Tag.LE);
        } else
          return new Token(Tag.LT);
      case '>':
        if (peek() == '=') {
          peek();
          return new Token(Tag.GE);
        } else
          return new Token(Tag.GT);
      case '.':
        return new Float(addFractionToInt(0));
      default:
        tag = Tag.UNDEFINED;
        break;
    }
    Token t = new Token(tag);
    _peek = ' ';
    return t;
  }
}
