import java.util.Scanner;

public class TestRubik {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lin = "";
        RubikState rubik = new RubikState(0);
        while(!lin.equals("o")){
            System.out.print("\ns: show the cube \n" +
                    "o: out\n" +
                    "u: rotate up\n" +
                    "d: rotate down\n" +
                    "l: rotate left\n" +
                    "r: rotate right\n" +
                    "f: rotate front\n" +
                    "b: rotate back\n" +
                    "uP: U prime\n" +
                    "option: ");
            lin = sc.nextLine();
            switch (lin){
                case "s":
                    System.out.println(rubik.toString());
                    break;
                case "u":
                    rubik.moveU();
                    break;
                case "d":
                    rubik.moveD();
                    break;
                case "l":
                    rubik.moveL();
                    break;
                case "r":
                    rubik.moveR();
                    break;
                case "f":
                    rubik.moveF();
                    break;
                case "b":
                    rubik.moveB();
                    break;
                case "uP":
                    rubik.moveU_PRIME();
                    break;
                case "dP":
                    rubik.moveD_PRIME();
                    break;
                case "lP":
                    rubik.moveL_PRIME();
                    break;
                case "rP":
                    rubik.moveR_PRIME();
                    break;
                case "fP":
                    rubik.moveF_PRIME();
                    break;
                case "bP":
                    rubik.moveB_PRIME();
                    break;
                default:
                    break;
            }
        }
    }

}

