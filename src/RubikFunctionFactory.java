import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

import java.util.LinkedHashSet;
import java.util.Set;

public class RubikFunctionFactory {

    private static ActionsFunction _actionsFunction = null;
    private static ResultFunction _resultFunction = null;


    public static ActionsFunction getActionsFunction() {
        if(_actionsFunction == null)
            _actionsFunction = new RubikActionsFunctions();
        return _actionsFunction;
    }

    public static ResultFunction getResultFunction() {
        if(_resultFunction == null)
            _resultFunction = new RubikResultFunction();
        return _resultFunction;
    }

    private static class RubikActionsFunctions implements ActionsFunction{

        @Override
        public Set<Action> actions(Object o) {
            Set<Action> actions = new LinkedHashSet<Action>();

            actions.add(RubikState.U);
            actions.add(RubikState.D);
            actions.add(RubikState.F);
            actions.add(RubikState.B);
            actions.add(RubikState.L);
            actions.add(RubikState.R);
            actions.add(RubikState.U_PRIME);
            actions.add(RubikState.D_PRIME);
            actions.add(RubikState.F_PRIME);
            actions.add(RubikState.B_PRIME);
            actions.add(RubikState.L_PRIME);
            actions.add(RubikState.R_PRIME);
            return actions;
        }
    }

    private static class RubikResultFunction implements ResultFunction{

        @Override
        public Object result(Object o, Action a) {
            RubikState s = (RubikState) o;
            if(RubikState.U.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveU();
                return s;
            }else if(RubikState.D.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveD();
                return s;
            }else if(RubikState.F.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveF();
                return s;
            }else if(RubikState.B.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveB();
                return s;
            }else if(RubikState.L.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveL();
                return s;
            }else if(RubikState.R.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveR();
                return s;
            }else if(RubikState.U_PRIME.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveU_PRIME();
                return s;
            }else if(RubikState.D_PRIME.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveD_PRIME();
                return s;
            }else if(RubikState.F_PRIME.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveF_PRIME();
                return s;
            }else if(RubikState.B_PRIME.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveB_PRIME();
                return s;
            }else if(RubikState.L_PRIME.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveL_PRIME();
                return s;
            }else if(RubikState.R_PRIME.equals(a)){
                s = new RubikState((RubikState) o);
                s.moveR_PRIME();
                return s;
            }
            return o;
        }
    }
}
