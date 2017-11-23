import aima.core.agent.Action;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.UniformCostSearch;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RubikDemoUninformed {


    private static RubikState iniState = null;
    private static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int option = -1;

        iniState = new RubikState(3);

        while(option != 0){
            try{
                System.out.print("\n----------------------------------------\n" +
                        "1.Depth First Search\n" +
                        "2.Breath First Search\n" +
                        "3.Uniform cost\n" +
                        "4.Shuffle(default 3 movs)\n" +
                        "0.Out\n" +
                        "\nYour option: ");

                option = Integer.parseInt(sc.nextLine());
                switch (option){
                    case 1:
                        System.out.println("Depth First Search:");
                        RubikBFSDemo(new DepthLimitedSearch(10));
                        break;
                    case 2:
                        System.out.println("Breath First Search:");
                        RubikBFSDemo(new BreadthFirstSearch());
                        break;
                    case 3:
                        System.out.println("Uniform cost:");
                        RubikBFSDemo(new UniformCostSearch());
                        break;
                    case 4:
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
