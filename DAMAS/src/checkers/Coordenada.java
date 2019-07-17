package checkers;

import java.io.Serializable;

public class Coordenada implements Serializable{

    private int c;

    public Coordenada(int c) {
        this.c = c;
        
    }

    Coordenada() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int get() {
        return c;
    }

    public boolean equals(Coordenada d) {
        return (c == d.get());
    }

    // Returns the row (0-7) of the coordinate (1-32).
    public int row() {
        int row;
        row = (int) Math.floor(c / 4.0);
        if (c % 4 == 0) {
            return row - 1;
        } else {
            return row;
        }
    }

    // Returns the column (0-7) of the coordinate (1-32).
    public int column() {
        if (row() % 2 == 0) {
            return (((c - (row() * 4)) * 2) - 1);
        } else {
            return (((c - (row() * 4)) * 2) - 2);
        }
    }

    public String toString() {
        return "" + c;
    }

    // Returns the coordinate of the square that is one square above and one
    // square to the left of c.
    public Coordenada upLeftMove() {
        //System.out.println("Checking if it is Up left Move");

        if (row() % 2 == 0) {
            return new Coordenada(c - 5);
        } else {
            return new Coordenada(c - 4);
        }
    }

    public Coordenada upRightMove() {
        //System.out.println("Checking if it is Up Right Move");
        //System.out.println("The Row of this Checker is " + row());

        if (row() % 2 == 0) {
            return new Coordenada(c - 4);
        } else {
            return new Coordenada(c - 3);
        }
    }

    public Coordenada downLeftMove() {
        if (row() % 2 == 0) {
            return new Coordenada(c + 3);
        } else {
            return new Coordenada(c + 4);
        }
    }

    public Coordenada downRightMove() {
        if (row() % 2 == 0) {
            return new Coordenada(c + 4);
        } else {
            return new Coordenada(c + 5);
        }
    }

    public Coordenada upLeftJump() {
        return new Coordenada(c - 9);
    }

    public Coordenada upRightJump() {
        return new Coordenada(c - 7);
    }

    public Coordenada downLeftJump() {
        return new Coordenada(c + 7);
    }

    public Coordenada downRightJump() {
        return new Coordenada(c + 9);
    }
}
