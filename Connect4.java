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
    //Turn;Reset;Instructions
    private Container pane;
    private JButton Start;
    private JButton Drop;
    private JButton Go;
    private JButton Reset;
    private JButton Surrender;
    private JButton Instructions;
    private JTextField Player1;
    private JTextField Player2;
    private JTextField Turn;
    private JTextField Title;
    private double[] circlex={.39,.42,.45,.48,.51,.54,.57};
    private double[] circley={.55,.6,.65,.7,.75,.8};
    private double ypos;
    private double initial;
    private double yInc;
    private double xInc;
    private int cNumber;
    private int count;
    private int columnSelected;
    private int column;
    private JComboBox<String> mode;
    private JComboBox<String> set;
    private String [][] Connected;
    private String slotOptions[][];
    private String [] options;
    private String color;
    private Color Background1;
    private Color framec;
    private boolean modeActive;
    private boolean turn;
    private boolean redraw;
    private boolean paintit;
    private boolean recentz;
    private boolean dropCircle;
    private boolean[][] draw;


    public void slotOptions(){
	slotOptions=new String [4][];
	slotOptions[0]= new String [7];
	slotOptions[1]= new String [7];
	slotOptions[2]= new String [9];
	slotOptions[3]= new String [11];
	for (int i =0 ; i < 3 ; i++){
	    for (int x =0 ; x < slotOptions[i].length ; x++){	       
		slotOptions[i][x]="Column"+" "+(""+(x+1));
	    }

	}
	
    }
  
    public void setVariables(){
	draw=new boolean [6][7];
	yInc=.05;
	xInc=.3;
	Connected=new String [6][7];
	columnSelected=1;
	cNumber=4;
	initial=circley[0];
	paintit=false;
	slotOptions();
	Background1 = new Color(242, 229, 255);
	framec = new Color(125, 217, 254);
	options = new String[] {"Connect-4","Connect-5","Connect-6","Connect-7"};

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

    public int size(int x,double y){
	return (int)(x*y);

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
	set = new JComboBox<>(slotOptions[cNumber-4]);
	set.setBounds(sizex(.4),sizey(.2),sizex(.2),sizey(.025));
	set.setOpaque(true);
	set.setForeground(Color.BLACK);
	set.setBackground(framec);
	set.setEnabled(true);
	set.addActionListener(this);
	set.setVisible(true);
	pane.add(set);
	set.repaint();
	Title= new JTextField("Connect-N", SwingConstants.CENTER);
	Title.setHorizontalAlignment(SwingConstants.CENTER);
	Title.setBounds(sizex(0),sizey(.3),sizex(1),sizey(.025));
	Title.setOpaque(true);
	Title.setForeground(Color.BLACK);
	Title.setBackground(framec);
	Title.setEnabled(false);
	Title.setVisible(true);
	pane.add(Title);
	Title.repaint();
    }

    public void  createGB(){
	Drop = new JButton("Set");
	Drop.setBounds(sizex(.7),sizey(.1),sizex(.2),sizey(.025));
	Drop.setBorderPainted(false);
	Drop.setOpaque(true);
	Drop.setForeground(Color.BLACK);
	Drop.setBackground(framec);
	Drop.setEnabled(true);
	Drop.addActionListener(this);
	Drop.setVisible(true);
	pane.add(Drop);
	Drop.repaint();	
    }
    
    public Connect4(){
	setVariables();
	Start();	
    }

     public void circlem(Graphics g){
	Toolkit tk = Toolkit.getDefaultToolkit();
	int ry=((int) tk.getScreenSize().getHeight());
	int ry2=((int) (tk.getScreenSize().getHeight()*.25));
	int x = ((int) tk.getScreenSize().getWidth());  
	int y = ((int) (tk.getScreenSize().getHeight()*.15));
	int recty= ((int) (tk.getScreenSize().getHeight()*.015));
	g.setColor(Color.RED);
	int xSize = ((int) tk.getScreenSize().getWidth());  
	int ySize = ((int) tk.getScreenSize().getHeight());
	int cx= (int)(xSize*.6);
	int cy=(int) (ySize*.9);
    }
    
    public void paint(Graphics g){
	if (paintit){
	    Go.setText("");
	    Go.setBounds(0,0,0,0);
	    Go.setBackground(Background1);
	    g.setColor(Color.BLACK);
	    /*/ for (int a =0; a<circlex.length; a++){
		for (int i =0; i<circley.length; i++){
		    g.fillOval(sizex(circlex[a]),sizey(circley[i]),sizex(.025),sizey(.03));		    
		}
		}/*/
	    for (int a =Connected.length-1; a>-1; a--){
		for (int i =0; i<Connected[a].length; i++){
		    if (Connected[a][i].equals("_")){
			g.setColor(Color.BLACK);
			g.fillOval(sizex(circlex[i]),sizey(circley[a]),sizex(.025),sizey(.03));

		    }
		    if (!(Connected[a][i].equals("_")) && Connected[a][i].equals("Yellow")){
			g.setColor(Color.YELLOW);
			g.fillOval(sizex(circlex[i]),sizey(circley[a]),sizex(.025),sizey(.03));
		    }
		    else if(!(Connected[a][i].equals("_")) && Connected[a][i].equals("Red")){
			g.setColor(Color.RED);
			g.fillOval(sizex(circlex[i]),sizey(circley[a]),sizex(.025),sizey(.03));
		    }
		    /*/   if (draw[a][i]==true){
			System.out.println(a+","+i);
			g.fillOval(sizex(circlex[i]),sizey(circley[a]),sizex(.025),sizey(.03));
		    }
		    /*/
		}
	    }
	}
	
	//	dC(g);
	
	//	draw(g);

    }

    public void dC(Graphics g){
	if (dropCircle){
	    if (turn){
		g.setColor(Color.RED);
	    }
	    else{
		g.setColor(Color.YELLOW);
	    }
	    g.fillOval(sizex(circlex[columnSelected-1]),sizey(initial),sizex(.025),sizey(.03));
		    
	}
    }
	
    public void draw(Graphics g){
	g.setColor(Color.BLACK);
	if (redraw){
	    for (int a =0; a<Connected.length; a++){
	    	for (int i =0; i<Connected[a].length; i++){
		    if (Connected[a][i]!=null && Connected[a][i].equals("Yellow")){
			g.setColor(Color.YELLOW);
		    }
		    else if(Connected[a][i]!=null && Connected[a][i].equals("Red")){
			g.setColor(Color.RED);
		    }
		    if (draw[a][i]==true){
			g.fillOval(sizex(circlex[a]),sizey(circley[i]),sizex(.025),sizey(.03));
		    }
	    	}
	    }
	    



	}

    }

    public static String printer(String[][] array){
	String gatherer="";
	for(int i=0;i<array.length;i++){
	    for(int x=0;x<array[i].length;x++){
		gatherer+=array[i][x] = " ";
	    }
	    gatherer+="\n";
	}
	return gatherer;
    }
    
    
    public void  Start(){
	Color myColour = new Color (0, 0, 0,127);
	pane = this.getContentPane();
	Toolkit tk = Toolkit.getDefaultToolkit();
	this.setSize(sizex(.99),sizey(.99));	
	this.setLayout(null);
	this.setTitle("Connect-4");
	this.setLocationRelativeTo(null);
	this.setBackground(Background1);
	pane.setBackground(Background1);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	Start = new JButton("Start");
	Start.setBounds(sizex(.4),sizey(.4),sizex(.1),sizey(.1));
	Start.setBorderPainted(false);
	Start.setOpaque(true);
	Start.setContentAreaFilled(true);
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
		draw=new boolean [6][7];
		Connected=new String [6][7];
		
	    }
	    if (((String)mode.getSelectedItem()).equals("Connect-5")){
		cNumber=5;
		draw=new boolean [6][7];
		Connected=new String [6][7];
	    }
	    if (((String)mode.getSelectedItem()).equals("Connect-6")){
		cNumber=6;
		draw=new boolean [8][9];
		Connected=new String [8][9];
	    }
	    if (((String)mode.getSelectedItem()).equals("Connect-7")){
		cNumber=7;
		draw=new boolean [10][11];
		Connected=new String [10][11];
	    }
	}
	if(e.getSource() == Go){    
	    turn=true;
	    Player1.setEditable(false);
	    Player2.setEditable(false);
	    int num=cNumber-4;
	    // mode.removeAllItems();
	    // mode.setVisible(false);
	    //pane.remove(mode);
	    //pane.revalidate(); // to invoke the layout manager
	    //pane.repaint();
	    // mode.addItem("item text");
	    //String [] list= slotOptions[0];
	    //mode.setModel(new DefaultComboBoxModel(list));
	    createTB();
	    createGB();
	    
	    paintit=true;
	    // pane.repaint();
	    repaint();
	    editConnect();
	    Go.setEnabled(false);
	}
	if (e.getSource()==set){
	    String n=new String ((String)set.getSelectedItem());
	    columnSelected=Integer.parseInt(n.substring(n.length()-1));
	    initial=circley[circley.length-1];
	   
	}
	
	if (e.getSource()==Drop){
	    //dropCircle=true;
	    //repaint();
	    //move();
	    //  System.out.println(printer(Connected));
	    if( Connected[0][columnSelected-1].equals("_")){
	    String gatherer="";
	    for(int i=0;i<Connected.length;i++){
		for(int x=0;x<Connected[i].length;x++){
		    gatherer+=Connected[i][x]+" ";
		}
		gatherer+="\n";
	    }
	    System.out.println( gatherer);
	    setConnect();
	    if (ypos<1){
		turn=!turn;
	    }
	    else{
		turn=!turn;
		Player1.setText("no");
		Player1.repaint();	    
	    }
	    String l="";
	    for(int i=0;i<Connected.length;i++){
		for(int x=0;x<Connected[i].length;x++){
		    l+=Connected[i][x]+" ";
		}
		l+="\n";
	    }
	    String m="";
	    for(int i=0;i<draw.length;i++){
		for(int x=0;x<draw[i].length;x++){
		    m+=draw[i][x]+" ";
		}
		m+="\n";
	    }
	    System.out.println(m);
	    redraw=true;
	    repaint();
		}
	}
	
	
    }

    
    public boolean setConnect(){	
	if (turn){
	    color="Yellow";
	}
	else{
	    color="Red";
	}

	for (int x = Connected.length-1 ; x > -1 ; x--){
	    if ((Connected[x][columnSelected-1]).equals("_")){
		Connected[x][columnSelected-1]=color;
		draw[x][columnSelected-1]=true;
		ypos=columnSelected-1;
		
		return true;
		
	    }
	    
	}
	return false;
    }
    
	
	
		



    public void move(){
	Timer timer = new Timer(500, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		    if (ypos<1.0){
			
			((Timer)e.getSource()).stop();
		    }
		    if (initial <(circley[((int)ypos)])){
			initial+=yInc;
		    } else {
			((Timer)e.getSource()).stop();
		    }
		    repaint();
		    
		}
	    });
	timer.start();
	initial=circley[0];
    }

    public void  editConnect(){
	for (int i= 0; i < Connected.length ; i++){
	    for (int x= 0; x < Connected[i].length ; x++){
		Connected[i][x]="_";
		
	    }
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
