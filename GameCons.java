import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
 
import javax.swing.*;
 
public class GameCons extends JFrame {
 
     private JPanel p = new JPanel();
     private JPanel bp = new JPanel();
     private JButton go = new JButton("go");
     private JButton[][] buttons = new JButton[20][20];
     private int[][] neighbors = null;
     private int live;
     private int tenCount;
     private int colCount;
    
     GameCons(){
     setSize(750,800);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setResizable(false);
     buildPanel();
     buildButtonPanel();
     setLayout(new GridLayout(2,1));
     add(p);
     add(bp);
     setVisible(true);
     }
    
     public void preset(){
          Scanner s = null;
          try {
               FileReader file = new FileReader("life100 (1).txt");
                s = new Scanner(file);
 
          } catch  (FileNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
         
          while(s.hasNextInt()){
               int a = s.nextInt() - 1;
               int b = s.nextInt() - 1;
               buttons[a][b].setEnabled(false);
               buttons[a][b].setBackground(Color.BLUE);
            buttons[a][b].setContentAreaFilled(false);
            buttons[a][b].setOpaque(true);
               s.nextLine();
          }
         
         
     }
    
     public void buildPanel(){
          p.setLayout(new GridLayout(20,20));
          for(int rows = 0; rows < 20; rows++){
               for(int col = 0; col < 20; col++){
                    buttons[rows][col] = new JButton();
                    p.add(buttons[rows][col]);
               }
          }
         
     }
    
     public void buildButtonPanel(){
          bp.add(go);
          go.addActionListener(new OneTwoThree());
     }
    
     public class OneTwoThree implements ActionListener{
          public void actionPerformed(ActionEvent ae) {
               neighbors = new int[20][20];
                if (ae.getSource().equals(go)) {
                cornerCount();
                sideCount();
                middleCount();
            for(int i = 0; i < 20; i++){
                 for(int k = 0; k < 20; k++){
                      if(neighbors[i][k] < 2){
                            buttons[i][k].setEnabled(true);
                             buttons[i][k].setBackground(Color.GRAY);
                          buttons[i][k].setContentAreaFilled(true);
                          buttons[i][k].setOpaque(false);
                      }
                      if(neighbors[i][k] == 3 && buttons[i][k].isEnabled()==true){
                           buttons[i][k].setEnabled(false);
                             buttons[i][k].setBackground(Color.BLUE);
                          buttons[i][k].setContentAreaFilled(false);
                          buttons[i][k].setOpaque(true);
                      }
                      if(neighbors[i][k] >= 4 && buttons[i][k].isEnabled()==false){
                            buttons[i][k].setEnabled(true);
                             buttons[i][k].setBackground(Color.GRAY);
                          buttons[i][k].setContentAreaFilled(true);
                          buttons[i][k].setOpaque(false);
                      }
                 }
            }
            stats();
          }
 
     }
     }
    
     public void cornerCount(){
          //for buttons[0][0]
          if(buttons[0][1].isEnabled() == false){
               neighbors[0][0]++;
                    }
          if(buttons[1][0].isEnabled()==false){
               neighbors[0][0]++;
                    }
          if(buttons[1][1].isEnabled()==false){
               neighbors[0][0]++;
                    }
         
          //for buttons[0][19]
          if(buttons[0][18].isEnabled()==false){
               neighbors[0][19]++;
                    }
          if(buttons[18][1].isEnabled()==false){
               neighbors[0][19]++;
                    }
          if(buttons[19][1].isEnabled()==false){
               neighbors[0][19]++;
                    }
         
          //for buttons[19][19]
          if(buttons[19][18].isEnabled()==false){
               neighbors[19][19]++;
          }
          if(buttons[18][18].isEnabled()==false){
               neighbors[19][19]++;
          }
          if(buttons[18][19].isEnabled()==false){
               neighbors[19][19]++;
          }
         
          //for buttons[19][0]
          if(buttons[19][1].isEnabled()==false){
               neighbors[19][0]++;
          }
          if(buttons[18][1].isEnabled()==false){
               neighbors[19][0]++;
          }
          if(buttons[18][0].isEnabled()==false){
               neighbors[19][0]++;
          }
          }
     public void sideCount(){
     //for row 0
          for(int col = 1; col < 19; col++){
               if(buttons[0][col-1].isEnabled() == false){
                    neighbors[0][col]++;
               }
               if(buttons[0][col+1].isEnabled()==false){
                    neighbors[0][col]++;
               }
               if(buttons[1][col].isEnabled()==false){
                    neighbors[0][col]++;
               }
               if(buttons[1][col-1].isEnabled()==false){
                    neighbors[0][col]++;
               }
               if(buttons[1][col+1].isEnabled()==false){
                    neighbors[0][col]++;
               }
          }
     //for row 19
          for(int col1 = 1; col1 < 19; col1++){
               if(buttons[19][col1-1].isEnabled()==false){
                    neighbors[19][col1]++;
               }
               if(buttons[19][col1+1].isEnabled()==false){
                    neighbors[19][col1]++;
               }
               if(buttons[18][col1].isEnabled()==false){
                    neighbors[19][col1]++;
               }
               if(buttons[18][col1-1].isEnabled()==false){
                    neighbors[19][col1]++;
               }
               if(buttons[18][col1+1].isEnabled()==false){
                    neighbors[19][col1]++;
               }
          }
         
          //for column 0
          for(int row1 = 1; row1 < 19; row1++){
               if(buttons[row1-1][0].isEnabled()==false){
                    neighbors[row1][0]++;
               }
               if(buttons[row1+1][0].isEnabled()==false){
                    neighbors[row1][0]++;
               }
               if(buttons[row1][1].isEnabled()==false){
                    neighbors[row1][0]++;
               }
               if(buttons[row1+1][1].isEnabled()==false){
                    neighbors[row1][0]++;
               }
               if(buttons[row1-1][1].isEnabled()==false){
                    neighbors[row1][0]++;
               }
          }
         
          //for column 19
          for(int row = 1; row < 19; row++){
               if(buttons[row-1][19].isEnabled()==false){
                    neighbors[row][19]++;
               }
               if(buttons[row+1][19].isEnabled()==false){
                    neighbors[row][19]++;
               }
               if(buttons[row][18].isEnabled()==false){
                    neighbors[row][19]++;
               }
               if(buttons[row+1][18].isEnabled()==false){
                    neighbors[row][19]++;
               }
               if(buttons[row-1][18].isEnabled()==false){
                    neighbors[row][19]++;
               }
          }
     }
    
     public void middleCount(){
     for(int rows = 1; rows < 19; rows++){
          for(int cols = 1; cols < 19; cols++){
          if(buttons[rows - 1][cols].isEnabled() == false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows + 1][cols].isEnabled()==false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows][cols-1].isEnabled() == false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows][cols+1].isEnabled() == false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows-1][cols-1].isEnabled() == false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows+1][cols+1].isEnabled()==false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows-1][cols+1].isEnabled()==false){
               neighbors[rows][cols]++;
          }
          if(buttons[rows+1][cols-1].isEnabled()==false){
               neighbors[rows][cols]++;
          }
 
          }
     }
     }
     public void stats(){
          colCount = 0;
          live = 0;
          tenCount = 0;
          for(int i = 0; i < 20; i++){
               for(int k = 0; k < 20; k++){
                    if(buttons[i][k].isEnabled()==false){
                         live++;
                    }
               }
          }
          System.out.println("number of live cells is " + live);
          for(int i = 0 ; i < 20; i++){
               if(buttons[10][i].isEnabled()==false){
                    tenCount++;
               }
          }
          System.out.println("number of live cells in row 10 is " + tenCount);
         
          for(int i = 0 ; i < 20; i++){
               if(buttons[i][10].isEnabled()==false){
                    colCount++;
               }
          }
          System.out.println("number of live cells in column 10 is " + colCount);
     }
    
     }
