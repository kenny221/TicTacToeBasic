
package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class GameFrame extends JFrame {
    //Starting Player
    private char turn = 'O';
    //Check if game is over
    private boolean isGameOver = false;
    //Cell grid
    private Cell[][] cells= new Cell [3][3];
    //Starting Instruction
    JLabel statusInstruction = new JLabel ("O's turn to play");
    //Button for refresh
    JButton refreshGame = new JButton("Refresh Game");
    
    public GameFrame(){
        JPanel panelGame = new JPanel (new GridLayout(3, 3));
        //Put Cells into Grid
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                panelGame.add(cells[i][j] = new Cell());
            }
        }
        //Create Borders for all the components
        panelGame.setBorder(new LineBorder(Color.BLACK,2));
        statusInstruction.setBorder(new LineBorder (Color.BLACK, 2));
        refreshGame.setBorder(new LineBorder (Color.BLACK, 2));
        
        //add components to BorderLayout
        add(refreshGame, BorderLayout.EAST);
        add(panelGame, BorderLayout.CENTER);
        add(statusInstruction, BorderLayout.SOUTH);
    }
    
    public boolean gameFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j ++){
                if(cells[i][j].getToken() == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean gameWon(char XorO){
        //Check Rows and Columns
        for(int i = 0; i < 3; i ++){
            if(((cells[i][0].getToken() == XorO)&&(cells[i][1].getToken() == XorO)&&( cells[i][2].getToken() ==XorO))
                    ||((cells[0][i].getToken() == XorO)&&(cells[1][i].getToken() == XorO)&&( cells[2][i].getToken() ==XorO))){
                return true;
            }
        }
        //Check both Diagonals
        if(((cells[0][0].getToken() == XorO)&&(cells[1][1].getToken() == XorO)&&(cells[2][2].getToken() == XorO))
                ||((cells[0][2].getToken() == XorO)&&(cells[1][1].getToken() == XorO)&&(cells[2][0].getToken() == XorO))){
            return true;
        }      
        return false;
    }
    public class Cell extends JPanel{
        //Base Token
        private char token = ' ';
    
        public Cell(){
            setBorder (new LineBorder(Color.black, 1));
            addMouseListener(new MyMouseListener());
        }
        public char getToken(){
            return token;
        }
    
        public void setToken(char c){
            token = c;
            repaint();
        }
    
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
        
            if(token == 'X'){
                g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
                g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
            }
            else if (token == 'O'){
                g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
            }
        }
        
        private class MyMouseListener extends MouseAdapter{
            @Override
            public void mouseClicked(MouseEvent e){
                if(isGameOver)
                    return;
                
                if(token == ' ' && turn != ' ')
                    setToken(turn);
                if(gameWon(turn)){
                    statusInstruction.setText(turn + " won! Game Over!");
                    turn = ' ';
                    isGameOver = true;
                }
                else if (gameFull()){
                    statusInstruction.setText("Tie game! Game Over!");
                    turn = ' ';
                    isGameOver = true;
                }
                else{
                    turn = (turn == 'O')? 'X' : 'O';
                    statusInstruction.setText(turn + "'s turn.");
                }
            }
        }
    }
}
