public class PointsRule extends Rule {
  int constPoints;
  RNG variablePoints;
  boolean delta; // True to award points, False to deduct points

  public PointsRule(int constPoints, boolean delta) {
    this(new RNG({constPoints, constPoints}, delta);
  }

  public PointsRule(RNG variablePoints, boolean delta) {
    super();
    this.variablePoints = variablePoints;
    this.delta = delta;
  }

  public void execute(GameState state) {
    state.getCurPlayer().deltaScore(variablePoints.ran_int();
  }
}
