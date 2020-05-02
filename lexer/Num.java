package lexer;
public class Num extends Token {
  private final int characteristic;
  public Num(int v) {
    super(Tag.NUM);
    characteristic = v;
  }
  public int value() {
    return characteristic;
  }
}
