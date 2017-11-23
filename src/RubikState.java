import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class RubikState {
	private static final int DIM = 3;
	private enum Color {GREEN, WHITE, ORANGE, RED, BLUE, YELLOW}
	public static final int TOTAL_CELLS = 27;
	public static final int PIECES = 20;

	private Color u[][] = new Color[DIM][DIM];
	private Color d[][] = new Color[DIM][DIM];
	private Color b[][] = new Color[DIM][DIM];
	private Color f[][] = new Color[DIM][DIM];
	private Color l[][] = new Color[DIM][DIM];
	private Color r[][] = new Color[DIM][DIM];

	public static Action U = new DynamicAction("U");
	public static Action D = new DynamicAction("D");
	public static Action F = new DynamicAction("F");
	public static Action B = new DynamicAction("B");
	public static Action L = new DynamicAction("L");
	public static Action R = new DynamicAction("R");
	public static Action U_PRIME = new DynamicAction("U_PRIME");
	public static Action D_PRIME = new DynamicAction("D_PRIME");
	public static Action F_PRIME = new DynamicAction("F_PRIME");
	public static Action B_PRIME = new DynamicAction("B_PRIME");
	public static Action L_PRIME = new DynamicAction("L_PRIME");
	public static Action R_PRIME = new DynamicAction("R_PRIME");

	public RubikState(int movs) {
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				u[i][j] = Color.WHITE;
				d[i][j] = Color.YELLOW;
				f[i][j] = Color.BLUE;
				b[i][j] = Color.GREEN;
				l[i][j] = Color.RED;
				r[i][j] = Color.ORANGE;
			}
		}
		//here, we should do BULFRU
        if (movs >= 1)
            moveB();
        if (movs >= 2)
            moveU();
        if (movs >= 3)
            moveL();
        if (movs >= 4)
            moveF();
        if (movs >= 5)
            moveR();
        if (movs >= 6)
            moveU();
	}

    public RubikState(RubikState rb) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                u[i][j] = rb.getColor(rb.getU(),i, j);
                d[i][j] = rb.getColor(rb.getD(),i, j);
                l[i][j] = rb.getColor(rb.getL(),i, j);
                r[i][j] = rb.getColor(rb.getR(),i, j);
                f[i][j] = rb.getColor(rb.getF(),i, j);
                b[i][j] = rb.getColor(rb.getB(),i, j);
            }
        }
    }

    public Color getColor(Color[][] face, int fila, int columna) {
		return face[fila][columna];
	}

    public Color[][] getU() {
        return u;
    }

    public Color[][] getD() {
        return d;
    }

    public Color[][] getB() {
        return b;
    }

    public Color[][] getF() {
        return f;
    }

    public Color[][] getL() {
        return l;
    }

    public Color[][] getR() {
        return r;
    }

    /**
     * It rotates the face´s colors
     * @param clockWise orientation
     * @param face to rotate
     */
	private void rotate(boolean clockWise, Color[][] face) {
		Color[][] faceAux = new Color[DIM][DIM];

		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++)
				if(clockWise)   //rotate clock _wise
					faceAux[i][j] = face[DIM - 1 - j][i];
				else  //rotate counteR_PRIMEclock_wise
					faceAux[i][j] = face[j][DIM - 1 - i];
		}
		face = faceAux;
	}

    /**
     * It changes the faces´s column depending of the clockwise
     * @param face1
     * @param face2
     * @param face3
     * @param face4
     * @param column
     * @param clockwise
     */
	private void moveColToCol(Color[][] face1, Color[][] face2, Color[][] face3,
                              Color[][] face4, int column, boolean clockwise)
    {
		Color[] aux = new Color[DIM];

		for(int i = 0; i < DIM; i++) {
		    if(clockwise)
		    {
                aux[i] = face1[i][column];
                face1[i][column] = face4[i][column];
                face4[i][column] = face3[i][column];
                face3[i][column] = face2[i][column];
                face2[i][column] = aux[i];
            }
            else
            {
                aux[i] = face4[i][column];
                face4[i][column] = face1[i][column];
                face1[i][column] = face2[i][column];
                face2[i][column] = face3[i][column];
                face3[i][column] = aux[i];
            }
		}
	}

    /**
     * It changes the faces´s row depending of the clockwise
     * @param face1
     * @param face2
     * @param face3
     * @param face4
     * @param row
     * @param clockwise
     */
	private void moveRowToRow(Color[][] face1, Color[][] face2, Color[][] face3,
                              Color[][] face4, int row, boolean clockwise)
    {
		Color[] aux = new Color[DIM];

		for(int i = 0; i < DIM; i++) {
		    if(clockwise)
		    {
                aux[i] = face1[row][i];
                face1[row][i] = face4[row][i];
                face4[row][i] = face3[row][i];
                face3[row][i] = face2[row][i];
                face2[row][i] = aux[i];
            }
            else
            {
                aux[i] = face4[row][i];
                face4[row][i] = face1[row][i];
                face1[row][i] = face2[row][i];
                face2[row][i] = face3[row][i];
                face3[row][i] = aux[i];
            }
		}
	}

    /**
     * It changes the faces´s rows and columns, depending of the clockwise
     * @param face1
     * @param face2
     * @param face3
     * @param face4
     * @param row
     * @param column
     * @param clockwise
     */
	private void moveColToRow(Color[][] face1, Color[][] face2, Color[][] face3,
                              Color[][] face4, int row, int column, boolean clockwise)
    {
		Color[] aux = new Color[DIM];

		for(int i = 0; i < DIM; i++) {
		    if(clockwise)
		    {
                aux[i] = face1[row][i];
                face1[row][i] = face2[DIM-1-i][DIM-column-1];
                face2[DIM-1-i][DIM-column-1] = face3[DIM-row-1][DIM-i-1];
                face3[DIM-row-1][DIM-i-1] = face4[i][column];
                face4[i][column] = aux[i];
            }
            else
            {
                aux[i] = face4[i][column];
                face4[i][column] = face3[DIM-row-1][DIM-i-1];
                face3[DIM-row-1][DIM-i-1] = face2[DIM-1-i][DIM-column-1];
                face2[DIM-1-i][DIM-column-1] = face1[row][i];
                face1[row][i] = aux[i];
            }
		}
	}

	public void moveU() {
	    rotate(true, u);
		moveRowToRow(f, l, b, r, 0, true);
	}

	public void moveD() {
	    rotate(true, d);
		moveRowToRow(f, r, b, l, DIM - 1, true);
	}

	public void moveF() {
	    rotate(true, f);
		moveColToRow(u, l, d, r, DIM-1, 0, true);
	}

	public void moveB() {
	    rotate(true, b);
		moveColToRow(u, l, d, r, 0, DIM-1, false);
	}

	public void moveR() {
	    rotate(true, r);
		moveColToCol(u, b, d, f, DIM - 1, true);
	}

	public void moveL() {
	    rotate(true, l);
		moveColToCol(u, f, d, b, 0, true);
	}

	public void moveU_PRIME() {
        rotate(false, u);
        moveRowToRow(f, l, b, r, 0, false);
	}

	public void moveD_PRIME() {
        rotate(false, d);
        moveRowToRow(f, r, b, l, DIM - 1, false);
	}

	public void moveF_PRIME() {
        rotate(false, f);
        moveColToRow(u, l, d, r, DIM-1, 0, false);
	}

	public void moveB_PRIME() {
        rotate(false, b);
        moveColToRow(u, l, d, r, 0, DIM-1, true);
	}

	public void moveL_PRIME() {
        rotate(false, l);
        moveColToCol(u, f, d, b, 0, false);
	}

	public void moveR_PRIME() {
        rotate(false, r);
        moveColToCol(u, b, d, f, DIM - 1, false);
	}

	private String toStringColor(Color[][] face, int fila, int columna) {
		Color c = this.getColor(face, fila, columna);

		if(c == Color.GREEN)
			return "G";
		else if(c == Color.WHITE)
			return "W";
		else if (c == Color.ORANGE)
            return "O";
		else if(c == Color.RED)
			return "R";
		else if(c == Color.BLUE)
			return "B";
		else
			return "Y";
			
	}
	
	private String toStringFace(Color[][] face) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < face.length; i++) {
			for(int j = 0; j < face.length; j++)
				sb.append(toStringColor(face, i, j)).append(" ");
			sb.append("\n");
		}
		return sb.toString();
	}

    @Override
    public String toString() {
        return "Up:\n" + toStringFace(u) + "\n" + "Back:\n" + toStringFace(b) + "\n" +
                "Down:\n" + toStringFace(d) + "\n" +
                "Left:\n" + toStringFace(l) + "\n" +
                "Right:\n" + toStringFace(r) + "\n" +
                "Front:\n" + toStringFace(f) + "\n";
    }

    public int correctColors(){
	    int num = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if(u[DIM/2][DIM/2] == u[i][j]) num++;
                if(d[DIM/2][DIM/2] == d[i][j]) num++;
                if(l[DIM/2][DIM/2] == l[i][j]) num++;
                if(r[DIM/2][DIM/2] == r[i][j]) num++;
                if(b[DIM/2][DIM/2] == b[i][j]) num++;
                if(f[DIM/2][DIM/2] == f[i][j]) num++;
            }
        }
        return num;
    }

    public int placedPieces(){
        int num = 0;

        num += correctCorners(u, f, r, b, l);
        num += correctCorners(d, f, l, b, r);

        num += correctEdges(u, f, r, b, l);
        num += correctEdges(d, f, l, b, r);

        num += correctInsideEdges(f, r, b, l);

        return num;
    }

    private int correctCorners(Color[][] f1, Color[][] f2, Color[][] f3, Color[][] f4, Color[][] f5)
    {
        int num = 0;

        //Corner 1
        if(f1[DIM/2][DIM/2] == f1[0][0] && f4[DIM/2][DIM/2] == f4[0][DIM-1] && f5[DIM/2][DIM/2] == f5[0][0])
            num++;
        //Corner 2
        if(f1[DIM/2][DIM/2] == f1[0][DIM-1] && f3[DIM/2][DIM/2] == f3[0][DIM-1] && f4[DIM/2][DIM/2] == f4[0][0])
            num++;
        //Corner 3
        if(f1[DIM/2][DIM/2] == f1[DIM-1][0] && f5[DIM/2][DIM/2] == f5[0][DIM-1] && f2[DIM/2][DIM/2] == f2[0][0])
            num++;
        //Corner 4
        if(f1[DIM/2][DIM/2] == f1[DIM-1][DIM-1] && f2[DIM/2][DIM/2] == f2[0][DIM-1] && f3[DIM/2][DIM/2] == f3[0][0])
            num++;

        return num;
    }

    private int correctEdges(Color[][] f1, Color[][] f2, Color[][] f3, Color[][] f4, Color[][] f5){
        int num = 0;

        //Corner 1
        if(f1[DIM/2][DIM/2] == f1[0][DIM-2] && f4[DIM/2][DIM/2] == f4[0][DIM-2])
            num++;
        //Corner 2
        if(f1[DIM/2][DIM/2] == f1[DIM-2][0] && f5[DIM/2][DIM/2] == f5[0][DIM-2])
            num++;
        //Corner 3
        if(f1[DIM/2][DIM/2] == f1[DIM-2][DIM-1] && f3[DIM/2][DIM/2] == f3[0][DIM-2])
            num++;
        //Corner 4
        if(f1[DIM/2][DIM/2] == f1[DIM-1][DIM-2] && f2[DIM/2][DIM/2] == f2[0][DIM-2])
            num++;

        return num;
    }

    private int correctInsideEdges(Color[][] f1, Color[][] f2, Color[][] f3, Color[][] f4){
        int num = 0;

        //Corner 1
        if(f1[DIM/2][DIM/2] == f1[DIM-2][DIM-1] && f2[DIM/2][DIM/2] == f2[DIM-2][0])
            num++;
        //Corner 2
        if(f2[DIM/2][DIM/2] == f2[DIM-2][DIM-1] && f3[DIM/2][DIM/2] == f3[DIM-2][0])
            num++;
        //Corner 3
        if(f3[DIM/2][DIM/2] == f3[DIM-2][DIM-1] && f4[DIM/2][DIM/2] == f4[DIM-2][0])
            num++;
        //Corner 4
        if(f4[DIM/2][DIM/2] == f4[DIM-2][DIM-1] && f1[DIM/2][DIM/2] == f1[DIM-2][0])
            num++;

        return num;
    }

    @Override
    public int hashCode() {
	    int num[] = new int[6];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                num[0] += u[i][j].ordinal();
                num[1] += d[i][j].ordinal();
                num[2] += l[i][j].ordinal();
                num[3] += r[i][j].ordinal();
                num[4] += f[i][j].ordinal();
                num[5] += b[i][j].ordinal();
            }
        }
        return num[0]*100000+num[1]*10000+num[2]*1000+num[3]*100+num[4]*10+num[5];
    }

    @Override
    public boolean equals(Object obj) {
	    RubikState rb = (RubikState)obj;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if(u[i][j]!=rb.getColor(rb.getU(),i, j)||
                    d[i][j]!=rb.getColor(rb.getD(),i, j)||
                    l[i][j]!=rb.getColor(rb.getL(),i, j)||
                    r[i][j]!=rb.getColor(rb.getR(),i, j)||
                    f[i][j]!=rb.getColor(rb.getF(),i, j)||
                    b[i][j]!=rb.getColor(rb.getB(),i, j)){
                    return false;
                }
            }
        }
        return true;
    }
}
