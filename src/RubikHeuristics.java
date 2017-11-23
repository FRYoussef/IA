import aima.core.search.framework.HeuristicFunction;

public class RubikHeuristics {

    public static HeuristicFunction createPlacedColors(){
        return new PlacedColors();
    }

    public static HeuristicFunction createPlacedPieces(){
        return new PlacedPieces();
    }

    private static class PlacedColors implements HeuristicFunction{

        @Override
        public double h(Object o) {
            return RubikState.TOTAL_CELLS - ((RubikState)o).correctColors();
        }
    }

    private static class PlacedPieces implements HeuristicFunction{

        @Override
        public double h(Object o) {
            return RubikState.PIECES - ((RubikState)o).placedPieces();
        }
    }

}
