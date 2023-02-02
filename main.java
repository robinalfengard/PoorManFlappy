package org.example;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Main {
    public static Player player = new Player(10, 10, 'O');
    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
        WallManager wallManager = new WallManager(terminal);
        terminal.flush();

        
        long tic = 0;
        while (wallManager.isContinueReadingInput()) {
            KeyStroke keyStroke = null;
            do {
                tic++;
                if (tic % 50 == 0){
                    wallManager.doIt();
                    terminal.flush();

                }
                Thread.sleep(4);
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            switch (keyStroke.getKeyType()) {
                case ArrowDown ->  player.moveDown();
                case ArrowUp -> player.moveUp();
                case ArrowRight -> player.moveRight();
                case ArrowLeft -> player.moveLeft();
            }
            terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
            terminal.putCharacter(' ');
            terminal.setCursorPosition(player.getX(), player.getY());
            terminal.putCharacter(player.getSymbol());
            terminal.flush();

                }
        }

    }
