import aima.core.agent.Action;
import aima.core.search.framework.*;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RubikDemoInformed {

    private static RubikState iniState = null;
    private static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        iniState = new RubikState(3);
        int option = -1;
        while(option != 0){
            try{
                System.out.print("\n----------------------------------------\n" +
                        "1.Greedy placed colors\n" +
                        "2.Greedy placed pieces \n" +
                        "3.A* placed colors\n" +
                        "4.A* placed pieces\n" +
                        "5.Shuffle(default 3 movs)\n" +
                        "0.Out\n" +
                        "\nYour option: ");

                option = Integer.parseInt(sc.nextLine());
                switch (option){
                    case 1:
                        System.out.println("Greedy:");
                        RubikBFSDemo(new GreedyBestFirstSearch(new GraphSearch(), RubikHeuristics.createPlacedColors()));
                        break;
                    case 2:
                        System.out.println("Greedy:");
                        RubikBFSDemo(new GreedyBestFirstSearch(new GraphSearch(), RubikHeuristics.createPlacedPieces()));
                        break;
                    case 3:
                        System.out.println("A*:");
                        RubikBFSDemo(new AStarSearch(new GraphSearch(), RubikHeuristics.createPlacedColors()));
                        break;
                    case 4:
                        System.out.println("A*:");
                        RubikBFSDemo(new AStarSearch(new GraphSearch(), RubikHeuristics.createPlacedPieces()));
                        break;
                    case 5:
                        selectShuffle();
                        break;
                    default:
                        break;
                }
            }catch (Exception e){
                System.out.println("Error: it is not a valid option");
            }
        }
    }

    private static void selectShuffle(){
        int initialMovs = -1;
        while(initialMovs < 0){
            System.out.print("Introduce a number to shuffle: ");
            try{
                initialMovs = Integer.parseInt(sc.nextLine());
                if(initialMovs < 0)
                    throw new Exception();
            }catch (Exception e){
                System.out.println("Error: not a correct format");
            }
        }

        iniState = new RubikState(initialMovs);
    }

    private static void RubikBFSDemo(Search s) {
        System.out.println("\nRubikBFSDemo-->");
        try {
            Problem problem = new Problem(iniState,
                    RubikFunctionFactory.getActionsFunction(),
                    RubikFunctionFactory.getResultFunction(),
                    new RubikGoalTest());

            SearchAgent agent = new SearchAgent(problem, s);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printInstrumentation(Properties properties) {
        for (Object o : properties.keySet()) {
            String key = (String) o;
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List<Action> actions) {
        actions.forEach(System.out::println);
    }

}

