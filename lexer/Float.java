package lexer;
public class Float extends Token {
  private final float value;
  public Float(float val) {
    super(Tag.FLOAT);
    value = val;
  }
  public float value() {
    return value;
  }
}
