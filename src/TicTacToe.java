import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidht = 600; //pixeles de ancho
    int boardHeight = 650; //pixeles de largo, 50px 

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textlabel1 = new JLabel();
    JPanel textPanel1 = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns= 0;

    //es donde s eva armando la interfaz del juego 
    TicTacToe() { //constructor 
        frame.setVisible(true);
        frame.setSize(boardHeight, boardWidht);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textlabel1.setBackground(Color.BLACK);
        textlabel1.setForeground(Color.white);
        textlabel1.setFont(new Font("Arial", Font.BOLD, 45));
        textlabel1.setText("Gato");
        textlabel1.setHorizontalAlignment(JLabel.CENTER);
        textlabel1.setOpaque(true);

        textPanel1.setLayout(new BorderLayout());
        textPanel1.add(textlabel1);
        frame.add(textPanel1);
        frame.add(textPanel1, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.black);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                //tile es el texto que va arriba de recuadro, como arriba del azulejo
                //en donde se pone si es "X" o "O"
                tile.setBackground(Color.WHITE);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed (ActionEvent e) {
                        if(gameOver) return;

                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().isEmpty()){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                              currentPlayer = currentPlayer == playerX ? playerO : playerX;
                              textlabel1.setText("turno de " + currentPlayer);
                            }
                        }
                        
                    }
                });

            }
        }

    }

    //rows is horizontal "filas"
        void checkWinner(){
            //horizontal 
            for(int r = 0; r < 3; r++){
            if(board[r][0].getText().isEmpty()) continue;

            if(board [r][0].getText() == board[r][1].getText() && 
            board[r][1].getText() == board[r][2].getText()) {
                for(int i = 0; i <3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++){
            if(board[0][c].getText().isEmpty()) continue;

            if (board [0][c].getText() == board [1][c].getText() && 
            board [1][c].getText() == board[2][c].getText()) {
                for (int i=0; i < 3; i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {
            for (int i=0; i < 3; i++){
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
        }

        //anti-diagonally 
        if (board[0][2].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;

        }

        //gato 
        if (turns == 9) {
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++){
                    setgato(board[r][c]);
                }
            }
            gameOver = true;
        }
        
  }

    void setWinner(JButton tile) {
      tile.setForeground(Color.red);
      tile.setBackground(Color.BLACK);
      textlabel1.setText(currentPlayer + " es el ganador");
  }

    void setgato(JButton tile) {
        tile.setForeground(Color.red);
        tile.setBackground(Color.red);
        textlabel1.setText("Gato");
    }
}