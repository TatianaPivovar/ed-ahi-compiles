package lexer;
public class Float extends Tag {
  private final float value;
  public Float(float val) {
    super(Tag.FLOAT);
    value = val;
  }
  public float value() {
    return value;
  }
}
