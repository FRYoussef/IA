import aima.core.search.framework.GoalTest;

public class RubikGoalTest implements GoalTest{

    RubikState rubikState = new RubikState(0);

    @Override
    public boolean isGoalState(Object o) {
        return rubikState.equals(o);
    }
}
