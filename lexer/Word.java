package lexer;
public class Word extends Token {
  public final String lexeme;
  public Word(Tag t, String s) {
    super(t);
    lexeme = new String(s);
  }
}
