import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


public class Connect4  extends JFrame implements ActionListener{
    private Container pane;
    private JButton Surrender;
    private JRadioButton Mode;
    private JButton Start;
    private JButton Go;
    private JButton Instructions;
    private JTextField Player1;
    private JTextField Player2;
    private JTextField Turn;
    private JTextField Name;
    private double[] circlex={.05,.17,.29,.41,.53,.65,.77};
    private double[] circley={.9,.8,.7,.6,.5,.4,.3};
    private double circlesize=.05;
    private String [][] Connected;
    private int cNumber;
    private JComboBox<String> mode;
    private String slotOptions[][];
    private Color Background1;
    private Color framec;



    public void slotOptions(){
	slotOptions=new String [4][];
	slotOptions[0]= new String [7];
	slotOptions[1]= new String [7];
	slotOptions[2]= new String [9];
	slotOptions[3]= new String [11];
	
    }
  
    public void setVariables(){
	slotOptions();
	Background1 = new Color(242, 229, 255);
	framec = new Color(125, 217, 254);
    }

    public static void main(String[] args) {
	Connect4 Test = new Connect4();
	Test.setVisible(true);
    }
    public int sizex(double newx){
	Toolkit tk = Toolkit.getDefaultToolkit();  
	int x = ((int) tk.getScreenSize().getWidth());  
	return (int)(newx*x);

    }

     public int sizey(double newy){
	 Toolkit tk = Toolkit.getDefaultToolkit();  
	int y = ((int) tk.getScreenSize().getHeight());
	return (int)(y*newy);

    }


    public void createBoard(int x){



    }

    public void IntroScreen(){
	pane = this.getContentPane();
	Toolkit tk = Toolkit.getDefaultToolkit();  
	Go = new JButton("Go");
	Go.setBounds(sizex(.85),sizey(.1),sizex(.1),sizey(.1));
	Go.setBorderPainted(false);
	Go.setOpaque(true);
	Go.setForeground(Color.BLACK);
	Go.setBackground(framec);
	Go.setEnabled(true);
	Go.addActionListener(this);
	Go.setVisible(true);
	pane.add(Go);
	Player1= new JTextField("Insert Name of Player 1");
	Player1.setBounds(sizex(.1),sizey(.1),sizex(.2),sizey(.025));
	Player1.setOpaque(true);
	Player1.setForeground(Color.BLACK);
	Player1.setBackground(framec);
	Player1.setEnabled(true);
	Player1.addActionListener(this);
	Player1.setVisible(true);
	pane.add(Player1);
	Player2= new JTextField("Insert Name of Player 2");
	Player2.setBounds(sizex(.1),sizey(.2),sizex(.2),sizey(.025));
	Player2.setOpaque(true);
	Player2.setForeground(Color.BLACK);
	Player2.setBackground(framec);
	Player2.setEnabled(true);
	Player2.addActionListener(this);
	Player2.setVisible(true);
	String[] options = new String[] {"Connect-4","Connect-5","Connect-6","Connect-7"};
	mode = new JComboBox<>(options);
	mode.setBounds(sizex(.4),sizey(.1),sizex(.2),sizey(.025));
	mode.setOpaque(true);
	mode.setForeground(Color.BLACK);
	mode.setBackground(framec);
	mode.setEnabled(true);
	mode.addActionListener(this);
	mode.setVisible(true);
	pane.add(mode);
	pane.add(Player1);
	pane.add(Player2);
	Go.repaint();
	mode.repaint();
	Player1.repaint();
	Player2.repaint();

    }

    public void  createTB(){
    }

    public void  createGB(){
    }
    
    public Connect4(){
	Start();
    }
    public void paint(Graphics g){



    }  
  
		

    
    public void  Start(){
	pane = this.getContentPane();
	Toolkit tk = Toolkit.getDefaultToolkit();
	this.setSize(sizex(.99),sizey(.99));
						   
	this.setLayout(null);
	this.setTitle("Connect-4");
	this.setLocationRelativeTo(null);
	this.setBackground(Background1);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	Start = new JButton("Start");
	Start.setBounds(sizex(.4),sizey(.4),sizex(.1),sizey(.1));
	Start.setBorderPainted(false);
	Start.setOpaque(true);
	Start.setForeground(Color.BLACK);
	Start.setBackground(framec);
	Start.setEnabled(true);
	Start.addActionListener(this);
	pane.add(Start);
    }

  
 
    public void actionPerformed(ActionEvent e){
	pane = this.getContentPane();
	String s = e.getActionCommand();
	if(e.getSource() == Start){
	    Start.setText("");
	    Start.setBackground(Background1);
	    IntroScreen();
	    Start.setEnabled(false);
	}
	if(e.getSource() == mode){
	    if (((String)mode.getSelectedItem()).equals("Connect-4")){
		cNumber=4;
	    }
	    if (((String)mode.getSelectedItem()).equals("Connect-5")){
		cNumber=5;
	    }
	    if (((String)mode.getSelectedItem()).equals("Connect-6")){
		cNumber=6;
	    }
	    if (((String)mode.getSelectedItem()).equals("Connect-7")){
		cNumber=7;
	    }
	}
	if(e.getSource() == Go){
	    createTB();
	    createGB();
	    createBoard(cNumber);	    
	    Go.setText("");
	    Go.setBackground(Background1);
	    Player1.setEditable(false);
	    Player2.setEditable(false);
	    mode.removeAllItems();
	    mode = new JComboBox<>(slotOptions[cNumber-4]);
	    mode.repaint();
	    repaint();
	    Go.setEnabled(false);		    
	}
	
    }
    //checks if a player has won after every move
    public boolean Connected(int row,int col,String color){
	return (checkVertical(row,col,color)   ||
	        checkHorizontal(row,col,color) ||
	        checkDiagonal1(row,col,color)  ||
	        checkDiagonal2(row,col,color));
     }
    public boolean checkVertical(int row,int col, String color){
	try{
	    int sum = 0;
	    boolean checker = true;
	    while (checker && sum<cNumber){
		if (!(Connected[row+1][col].equals(color))){
		    checker=false;
	    }
		else{
		    sum++;;
		}
	    }
	    return (checker && sum==cNumber);
	}catch(ArrayIndexOutOfBoundsException e){
	    return false;
	}
	catch(IndexOutOfBoundsException e){
	    return false;
	}
    }
    public boolean checkHorizontal(int row,int col, String color){
	try{
	    int sumRight =0;
	    int sumLeft=0;
	    boolean checkerRight = true;
	    boolean checkerLeft = true;
	    while (checkerRight){
			if (!(Connected[row][col+1].equals(color))){
		    checkerRight=false;
	    }
		else{
		    sumRight++;;
		}
	    }
	    while (checkerLeft){
			if (!(Connected[row][col-1].equals(color))){
		    checkerLeft=false;
	    }
		else{
		    sumLeft++;;
		}
	    }
	    return (sumRight + sumLeft >= cNumber);
	}
	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
    }
    // checks top left to bottom right
     public boolean checkDiagonal1(int row,int col, String color){
	try{
	    int sumRight =0;
	    int sumLeft=0;
	    boolean checkerRight = true;
	    boolean checkerLeft = true;
	    while (checkerRight){
			if (!(Connected[row+1][col+1].equals(color))){
		    checkerRight=false;
	    }
		else{
		    sumRight++;;
		}
	    }
	    while (checkerLeft){
			if (!(Connected[row-1][col-1].equals(color))){
		    checkerLeft=false;
	    }
		else{
		    sumLeft++;;
		}
	    }
	    return (sumRight + sumLeft >= cNumber);
	}
	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
     }
    //checks bottom left to top right 
     public boolean checkDiagonal2(int row,int col, String color){
	try{
	    int sumRight =0;
	    int sumLeft=0;
	    boolean checkerRight = true;
	    boolean checkerLeft = true;
	    while (checkerRight){
			if (!(Connected[row+1][col-1].equals(color))){
		    checkerRight=false;
	    }
		else{
		    sumRight++;;
		}
	    }
	    while (checkerLeft){
			if (!(Connected[row-1][col+1].equals(color))){
		    checkerLeft=false;
	    }
		else{
		    sumLeft++;;
		}
	    }
	    return (sumRight + sumLeft >= cNumber);
	}
	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
     }

}
