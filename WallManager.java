package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class WallManager {
    Terminal terminal;
    Random r = new Random();
    long doItTicks = 0;
    LinkedList<Wall> listOfWalls = new LinkedList<>();
    Player player;
    static boolean continueReadingInput = true;

    public boolean isContinueReadingInput() {
        return continueReadingInput;
    }

    public WallManager(Terminal terminal) throws IOException {
        this.terminal = terminal;
        listOfWalls.add(new Wall(70, 10, 5, 'X', terminal));
    }

    public void doIt() throws IOException {
        doItTicks++;
        if (doItTicks % 4 == 0) {
            listOfWalls.add(new Wall(70, r.nextInt(16) + 2, r.nextInt(10) + 2, 'X', terminal));
        }
        LinkedList<Wall> toBeRemoved = new LinkedList<>();
        for (Wall wall : listOfWalls) {
            if (!wall.moveWall()) {
                toBeRemoved.add(wall);
            }
        }
        for (Wall wall : toBeRemoved) {
            listOfWalls.remove(wall);
        }
    }

}
