package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Wall {

    private int x;
    private int y;
    private char symbol;
    private int previousX;
    private int previousY;
    Terminal terminal;
    int length;


    public Wall(int x, int y, int length, char symbol, Terminal terminal) throws IOException {
        this.x = x;
        this.y = y;
        this.length = length;
        this.symbol = symbol;
        this.terminal = terminal;
        for (int i = 0; i < length; i++) {
            terminal.setCursorPosition(getX(), getY());
            terminal.putCharacter(getSymbol());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public boolean moveWall() throws IOException {
        previousX = x;
        previousY = y;
        for (int i = 0; i < length; i++) {
            terminal.setCursorPosition(previousX, previousY + i);
            terminal.putCharacter(' ');
        }
        this.x -= 2;
        for (int i = 0; i < length; i++) {
            if (Main.player.getY() == y + i && Main.player.getX() == x + i) {
                WallManager.continueReadingInput = false;
                String message = "GAME OVER!";
                terminal.flush();
                for (int b = 0; b < message.length(); b++) {
                    terminal.setCursorPosition(30 + b, 10);
                    terminal.putCharacter(message.charAt(b));
                    terminal.flush();
                }
                }
                if (x < 2) {
                    return false;
                }
                terminal.setCursorPosition(x, y + i);
                terminal.putCharacter(getSymbol());
            }
            return true;

        }


    }

