
package tictactoe;

import javax.swing.JFrame;

public class TicTacToe {

    public static void main(String[] args) {
        // TODO code application logic here
        
        JFrame ticTacToe = new GameFrame();
        ticTacToe.setTitle("TicTacToe Game");
        ticTacToe.setSize(300, 300);
        ticTacToe.setVisible(true);
        ticTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
