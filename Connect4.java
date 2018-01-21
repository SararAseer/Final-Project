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
import java.util.concurrent.TimeUnit;


public class Connect4  extends JFrame implements ActionListener , KeyListener{
    private Image db;
    private Graphics dbg;
    private Container pane;
    private JButton Start;
    private JButton Drop;
    private JButton Go;
    private JButton Reset;
    private JButton Surrender;
    private JButton Instructions;
    private JButton check;
    private JTextField Player1;
    private JTextField Player2;
    private JTextField Turn;
    private JTextField Title;
    private JTextField set;
    private JLabel Company;
    private double[] circlex={.39,.42,.45,.48,.51,.54,.57};
    private double[] circley={.25,.3,.35,.4,.45,.5};
    private double ypos;
    private double initial;
    private double yInc;
    private double xInc;
    private double offset;
    private int cNumber;
    private int count;
    private int chosen;
    private int columnSelected;
    private int column;
    private JComboBox<String> mode;
    private String [][] Connected;
    private String [][] data;
    private String slotOptions[][];
    private String [] options;
    private String p1;
    private String p2;
    private String color;
    private Color Background1;
    private Color framec;
    private boolean modeActive;
    private boolean inProgress;
    private boolean turn;
    private boolean redraw;
    private boolean reset;
    private boolean Animation;
    private boolean paintit;
    private boolean won2;
    private boolean recentz;
    private boolean won;
    private boolean canDo;
    private boolean dropCircle;
    private boolean[][] draw;


    public void slotOptions(){
	slotOptions=new String [6][];
	slotOptions[0]= new String [7];
	slotOptions[1]= new String [8];
	slotOptions[2]= new String [9];
	slotOptions[3]= new String [10];
	slotOptions[4]= new String [11];
	slotOptions[5]= new String [12];
	for (int i =0 ; i < slotOptions.length ; i++){
	    for (int x =0 ; x < slotOptions[i].length ; x++){	       
		slotOptions[i][x]="Column"+" "+(""+(x+1));
	    }

	}
	
    }
  
    public void setVariables(){
	InstructionsText="To play this game first insert a number in the box below 'Reset'. This number should correspond to the column u want to put your piece into. After you do this press 'Set' to put the piece into that column."+"\n"+"Following this the turn number will change and the other played will go. If at any point in any path (Vertical/Horizontal/Diagnol) the connect number is reached the player will win the Game. Below is the"+"\n"+"signifance of the boxes:" +"\n" +"Chooser- New Game Connect Number"+"\n"+"Reset- New Game"+"\n"+"Insert Column Number- Column number you want to put the piece into"+"\n"+"Set-Drop Piece"+"\n"+"Instructions-Pop up Instructions"+"\n"+"Turn-Current player turn";
	canDo=true;
	Animation=true;
	draw=new boolean [6][7];
	yInc=.05;
	initial=0;
	offset=.3;
	xInc=.3;
	Connected=new String [6][7];
	cNumber=4;
	paintit=false;
	slotOptions();
	Background1 = new Color(242, 229, 255);
	framec = new Color(125, 217, 254);
	options = new String[] {"Connect-4","Connect-5","Connect-6","Connect-7","Connect-8","Connect-9"};

    }

    public static void main(String[] args) {
	Connect4 Test = new Connect4();
	Test.addComponentListener(new ComponentAdapter() {
		@Override
		public void componentResized(ComponentEvent e)
		{
		    Test.invalidate();
		    Test.validate();
		    Test.repaint();
		}
	    });
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

    public int sizeyr(double newy){
	Toolkit tk = Toolkit.getDefaultToolkit();  
	int y = ((int) tk.getScreenSize().getHeight());
	return (int)(y*newy*offset);

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
	set = new JTextField("Insert Column Number",2);
	set.setBounds(sizex(.4),sizey(.2),sizex(.2),sizey(.025));
	set.setOpaque(true);
	set.setForeground(Color.BLACK);
	set.setBackground(framec);
	set.setEnabled(true);
	set.addKeyListener(this);
	set.setVisible(true);
	pane.add(set);
	set.repaint();
	Turn= new JTextField("Turn");
	Turn.setBounds(sizex(.7),sizey(.2),sizex(.2),sizey(.025));
	Turn.setOpaque(true);
	Turn.setHorizontalAlignment(SwingConstants.CENTER);
	Turn.setForeground(Color.BLACK);
	Turn.setBackground(framec);
	Turn.setEditable(false);
	Turn.setVisible(true);
	pane.add(Turn);
	Turn.repaint();

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
	Reset = new JButton("Reset");
	Reset.setBounds(sizex(.4),sizey(.15),sizex(.2),sizey(.025));
	Reset.setBorderPainted(false);
	Reset.setOpaque(true);
	Reset.setForeground(Color.BLACK);
	Reset.setBackground(framec);
	Reset.setEnabled(true);
	Reset.addActionListener(this);
	Reset.setVisible(true);
	pane.add(Reset);
	Reset.repaint();
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
	db=createImage(getWidth(),getHeight());
	dbg=db.getGraphics();
	paintComponent(dbg);
	g.drawImage(db,0,sizey(.3),this);


    }
    
    public void paintComponent(Graphics g){
	
	if (reset){
	 
	    g.setColor(framec);
	    g.fillRect(sizex(0),sizeyr(.22),sizex(1),sizey(.025));
	    String m="Connect-N";
	    Font font= new Font("TimesRoman", Font.PLAIN, 16);
	    FontMetrics fm = g.getFontMetrics ( font );
	    int sw = fm.stringWidth ( m );
	    g.setFont ( font );
	    g.setColor ( Color.BLACK );
	    g.drawString ( m , ( this.getWidth() + sw ) / 2 - sw , sizeyr(.276));
 	    System.out.println(printer(Connected));
	    for (int a =Connected.length-1; a>-1; a--){
		for (int i =0; i<Connected[a].length; i++){
		    if (a==Connected.length-1){
			g.setColor(Color.BLACK);
			g.drawString("C:"+(i+1),sizex(circlex[i]+.0045) ,sizey(circley[circley.length-1]+yInc));
		    }
		    
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
		}

	    }
	    reset=false;
	}
	pane = this.getContentPane();
	if(won){
	    
	    this.setVisible(false);
	    Animations x = new Animations();
	    if (turn){
		x.setText(p1);
	    }
	    else{
		x.setText(p2);
	    }
	    x.setxy(circlex,circley);
	    x.Board(Connected);
	    x.move();
	    x.setVisible(true);
	    this.dispose();
	}
	if (!won){
	if (recentz){
	    for (int a =Connected.length-1; a>-1; a--){
		for (int i =0; i<Connected[a].length; i++){
		    if (Connected[a][i].equals("_")){
			g.setColor(Color.BLACK);
			g.fillOval(sizex(circlex[i]),sizey(circley[a]),sizex(.025),sizey(.03));

		    }
		}
	    }
	    paintit=true;
	    recentz=false;
	    
	}

	
	if (paintit){
	   
	    Go.setText("");
	    Go.setBounds(0,0,0,0);
	    Go.setBackground(Background1);
	    Go.repaint();
	    g.setColor(framec);
	    g.fillRect(sizex(0),sizeyr(.22),sizex(1),sizey(.025));
	    String m="Connect-N";
	    Font font= new Font("TimesRoman", Font.PLAIN, 16);
	    FontMetrics fm = g.getFontMetrics ( font );
	    int sw = fm.stringWidth ( m );
	    g.setFont ( font );
	    g.setColor ( Color.BLACK );
	    g.drawString ( m , ( this.getWidth() + sw ) / 2 - sw , sizeyr(.276));
	  
	    for (int a =Connected.length-1; a>-1; a--){
		for (int i =0; i<Connected[a].length; i++){
		    if (a==Connected.length-1){
			g.setColor(Color.BLACK);
			g.drawString("C:"+(i+1),sizex(circlex[i]+.0045) ,sizey(circley[circley.length-1]+yInc));
		    }
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
		}
		if (turn){
		    g.setColor(Color.YELLOW);
		}
		else{
		    g.setColor(Color.RED);
		}
	
	    }

	    if (dropCircle){
		if (turn){
		    g.setColor(Color.YELLOW);
		}
		else{
		    g.setColor(Color.RED);
		}
		g.fillOval(sizex(circlex[columnSelected-1]),sizey(initial),sizex(.025),sizey(.03));
		if(initial>(circley[((int)(ypos))])||ypos==0){
		    Drop.setForeground(Color.BLACK);
		    Drop.repaint();
		    Reset.setForeground(Color.BLACK);
		    Reset.repaint();
		}
	
	    }
	}
	
	
	//	draw(g);
	}

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
	dropCircle=false;
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
		gatherer+=array[i][x] + " ";
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
	this.setTitle("Byte Sized");
	this.setLocationRelativeTo(null);
	this.setBackground(Background1);
	pane.setBackground(Background1);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	if(!won2){
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
    }
    public void keyTyped(KeyEvent e) {
	char vChar = e.getKeyChar();
	if (!(Character.isDigit(vChar)|| (vChar == KeyEvent.VK_BACK_SPACE)|| (vChar == KeyEvent.VK_DELETE))){
	    e.consume();
	}
	if (set.getText().length()>=2){
	    e.consume();
	}
    }

    public void keyPressed(KeyEvent e) {
    }

    
    public void keyReleased(KeyEvent e) {
       
    }
  
 
    public void actionPerformed(ActionEvent e){
	pane = this.getContentPane();
	String s = e.getActionCommand();
	if(e.getSource() == Start){
	    Start.setText("");
	    Start.setBounds(0,0,0,0);	    
	    Start.setBackground(Background1);
	    Start.repaint();	  
	    IntroScreen();
	    
	    Start.setEnabled(false);
	}

	if(e.getSource() == Reset){
	    if(canDo){
        	if (((String)mode.getSelectedItem()).equals("Connect-4")){
		    cNumber=4;
		    draw=new boolean [6][7];
		    Connected=new String [6][7];
		    circlex=new double []{.39,.42,.45,.48,.51,.54,.57};
		    circley=new double []{.25,.3,.35,.4,.45,.5};


		}
		if (((String)mode.getSelectedItem()).equals("Connect-5")){
		    cNumber=5;
		    draw=new boolean [7][8];
		    Connected=new String [7][8];
		    circlex=new double []{.36,.39,.42,.45,.48,.51,.54,.57};
		    circley=new double []{.2,.25,.3,.35,.4,.45,.5};
		   
		    }
		    

		
		if (((String)mode.getSelectedItem()).equals("Connect-6")){
		    cNumber=6;
		    draw=new boolean [8][9];
		    Connected=new String [8][9];
		    circlex=new double []{.36,.39,.42,.45,.48,.51,.54,.57,.6};
		    circley=new double []{.2,.25,.3,.35,.4,.45,.5,.55};

		    }

		
		if (((String)mode.getSelectedItem()).equals("Connect-7")){
		    cNumber=7;
		    draw=new boolean [9][10];
		    Connected=new String [9][10];
		    circlex=new double []{.36,.39,.42,.45,.48,.51,.54,.57,.6,.63};
		    circley=new double []{.15,.2,.25,.3,.35,.4,.45,.5,.55};
		   
		    }

		if (((String)mode.getSelectedItem()).equals("Connect-8")){
		    cNumber=8;
		    draw=new boolean [10][11];
		    Connected=new String [10][11];
		    circlex=new double []{.33,.36,.39,.42,.45,.48,.51,.54,.57,.6,.63};
		    circley=new double []{.15,.2,.25,.3,.35,.4,.45,.5,.55,.6};

		}
		if (((String)mode.getSelectedItem()).equals("Connect-9")){
		    cNumber=9;
		    draw=new boolean [11][12];
		    Connected=new String [11][12];
		    circlex=new double []{.33,.36,.39,.42,.45,.48,.51,.54,.57,.6,.63,.66};
		    circley=new double []{.1,.15,.2,.25,.3,.35,.4,.45,.5,.55,.6};

		}
	
		turn=true;
		paintit=false;
		editConnect();
		System.out.println("hello");
		System.out.println(printer(Connected));
		reset=true;
		repaint();
	    }
	 
	}
		
	if(e.getSource() == mode){
	    if(!inProgress){
		if (((String)mode.getSelectedItem()).equals("Connect-4")){
		    cNumber=4;
		    draw=new boolean [6][7];
		    Connected=new String [6][7];
		    circlex=new double []{.39,.42,.45,.48,.51,.54,.57};
		    circley=new double []{.25,.3,.35,.4,.45,.5};;
		
		}
		if (((String)mode.getSelectedItem()).equals("Connect-5")){
		    cNumber=5;
		    draw=new boolean [7][8];
		    Connected=new String [7][8];
		    circlex=new double []{.36,.39,.42,.45,.48,.51,.54,.57};
		    circley=new double []{.2,.25,.3,.35,.4,.45,.5};
		}
		if (((String)mode.getSelectedItem()).equals("Connect-6")){
		    cNumber=6;
		    draw=new boolean [8][9];
		    Connected=new String [8][9];
		    circlex=new double []{.36,.39,.42,.45,.48,.51,.54,.57,.6};
		    circley=new double []{.2,.25,.3,.35,.4,.45,.5,.55};
		}
		if (((String)mode.getSelectedItem()).equals("Connect-7")){
		    cNumber=7;
		    draw=new boolean [9][10];
		    Connected=new String [9][10];
		    circlex=new double []{.36,.39,.42,.45,.48,.51,.54,.57,.6,.63};
		    circley=new double []{.15,.2,.25,.3,.35,.4,.45,.5,.55};
		}
		if (((String)mode.getSelectedItem()).equals("Connect-8")){
		    cNumber=8;
		    draw=new boolean [10][11];
		    Connected=new String [10][11];
		    circlex=new double []{.33,.36,.39,.42,.45,.48,.51,.54,.57,.6,.63};
		    circley=new double []{.15,.2,.25,.3,.35,.4,.45,.5,.55,.6};

		}
		if (((String)mode.getSelectedItem()).equals("Connect-9")){
		    cNumber=9;
		    draw=new boolean [11][12];
		    Connected=new String [11][12];
		    circlex=new double []{.33,.36,.39,.42,.45,.48,.51,.54,.57,.6,.63,.66};
		    circley=new double []{.1,.15,.2,.25,.3,.35,.4,.45,.5,.55,.6};

		}
	    }
	}
	
	if(e.getSource() == Go){    
	    turn=false;
	    inProgress=true;
	    p1=Player1.getText();
	    Title.setText("");
	    Title.setBackground(Background1);
	    p2=Player2.getText();
	    Player1.setEditable(false);
	    Player2.setEditable(false);
	    int num=cNumber-4;	   
	    createTB();
	    createGB();
	    recentz=true;
	    paintit=true;
	    repaint();
	    editConnect();
	    Go.setEnabled(false);
	}
	if (e.getSource()==set){
	  
	    insertion();
	    
	    
	}

	if (e.getSource()==Drop){
	    try{
		paintit=true;
		if (canDo){
		    insertion();
		    System.out.println(chosen);
		    columnSelected=chosen;
		    if( Connected[0][columnSelected-1].equals("_")){
			Drop.setForeground(Color.BLACK);
			Drop.repaint();
			Reset.setForeground(Color.BLACK);
			Reset.repaint();
			turn=!turn;
			if (turn){
			    Turn.setText(p1+"'s "+"Turn");
			    Turn.setBackground(Color.YELLOW);
			}
			else{
			    Turn.setText(p2+"'s "+ "Turn");
			    Turn.setBackground(Color.RED);
			}
			Turn.repaint();
			initial=circley[0]-yInc;
			System.out.println(initial);
			checkEmpty();
			dropCircle=true;
			canDo=false;
			move();
		    }
		}
	    }
	    catch(Exception m){
		set.setText("Use a valid number to play, :)");
	    } 
	}
	
	if(e.getSource()==check){
	    win();
	}
	
    }

    public void insertion(){
	try{
	    String n=new String ((String)set.getText());
	    chosen=Integer.parseInt(n);
	}
	catch(Exception e){
	    set.setText("This is not a number, try again buddy");
	}
    }

    public void win(){
	System.out.println(printer(Connected));
	data=Connected;
	System.out.println("Checking");
	if ( Connected((int)ypos,columnSelected-1,color)){
	    System.out.println("someone did win");
	    Drop .setEnabled(false);
	    won=true;
	    repaint();

	}
	System.out.println("Checked");
    }

 

    public void  checkEmpty(){
	for (int x = Connected.length-1 ; x > -1 ; x--){
	    if ((Connected[x][columnSelected-1]).equals("_")){
		ypos=x;
		break;
	    }
	    
	}
       

    }

    public void setWin(){
	won2=true;
	
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
		return true;
		
	    }
	    
	}
	return false;
    }
    
	
	
		



    public boolean move(){
	System.out.println(ypos);
	System.out.println(printer(Connected));
	Timer timer = new Timer(50, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
	
		    if (initial <(circley[((int)(ypos))]-.01)){
			initial+=yInc;
		    } else {
			((Timer)e.getSource()).stop();
			setConnect();
			win();
			if (turn){
			    Turn.setText(p2+"'s "+"Turn");
			    Turn.setBackground(Color.RED);
			}
			else{
			    Turn.setText(p1+"'s "+ "Turn");
			    Turn.setBackground(Color.YELLOW);
			}
			Turn.repaint();
			canDo=true;
			win();
			
			
		    }
		    repaint();
		    
		}
	    });
	timer.start();
	initial=circley[0]-yInc;
	return true;
	
    }

    public void  editConnect(){
	for (int i= 0; i < Connected.length ; i++){
	    for (int x= 0; x < Connected[i].length ; x++){
		Connected[i][x]="_";
		
	    }
	}
    }
    
    
    
    public boolean Connected(int row,int col,String color){
	return (checkVertical(row,col,color)   ||
	        checkHorizontal(row,col,color) ||
	        checkDiagonal1(row,col,color)  ||
	        checkDiagonal2(row,col,color));
     }
         public boolean checkVertical(int row,int col, String color){
	try{
	    //System.out.println(data.length);
	    int rowUp=row;
	    int rowDown=row;
	    int sum=1;
	    boolean checkerUp = true;
	    boolean checkerDown = true;
	    while (checkerUp && rowUp<data.length-1){
		if (!(data[rowUp+1][col].equals(color))){
		    checkerUp=false;
		}
		else{
		    sum++;
		    rowUp++;
		    System.out.println(rowUp);
		}
	    }
	    while (checkerDown && rowDown>0){
		if (!(data[rowDown-1][col].equals(color))){
		    checkerDown=false;
		}
		else{
		    sum++;
		    rowDown--;
		}
	    }
	    // System.out.println(sum);
	    //System.out.println(cNumber);
	    //System.out.println(sum>=cNumber);
	    return sum >= cNumber;
	}
	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
    }
    public boolean checkHorizontal(int row,int col, String color){
	try{
	    int rightCol=col;
	    int leftCol=col;
	    int sum=1;
	    boolean checkerRight = true;
	    boolean checkerLeft = true;
	    while (checkerRight && rightCol<data[0].length-1){
		if (!(data[row][rightCol+1].equals(color))){
		    checkerRight=false;
		}
		else{
		    sum++;
		    rightCol++;
		}
	    }
	    while (checkerLeft && leftCol>0){
		if (!(data[row][leftCol-1].equals(color))){
		    checkerLeft=false;
		}
		else{
		    sum++;
		    leftCol--;
		}
	    }
	    return sum >= cNumber;
	}
	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
    }
    //checks top left to bottom right
    public boolean checkDiagonal1(int row,int col, String color){
	try{
	    int rightCol=col;
	    int leftCol=col;
	    int rowUp=row;
	    int rowDown=row;
	    int sum=1;
	    boolean checkerRight = true;
	    boolean checkerLeft = true;
	    while (checkerRight && rightCol<data[0].length-1 && rowUp<data.length-1){
		if (!(data[rowUp+1][rightCol+1].equals(color))){

		    checkerRight=false;
		}
		else{
		    //System.out.println("right;" + sum);
		    sum++;
		    rightCol++;
		    rowUp++;
		}
	    }
	    while (checkerLeft && leftCol>0 &&  rowDown>0){
		if (!(data[rowDown-1][leftCol-1].equals(color))){
		    checkerLeft=false;
		}
		else{
		    //System.out.println("left;" + sum);
		    sum++;
		    leftCol--;
		    rowDown--;
		}
	    }
	    return sum >= cNumber;
	}
	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
    }
    //checks bottom left to top right 
        public boolean checkDiagonal2(int row,int col, String color){
	try{
	    int rightCol=col;
	    int leftCol=col;
	    int rowUp=row;
	    int rowDown=row;
	    int sum=1;
	    boolean checkerRight = true;
	    boolean checkerLeft = true;
	    while (checkerLeft && leftCol>0 && rowUp<data.length-1){ //&& leftCol>0 &&  rowDown>0){
		if (!(data[rowUp+1][leftCol-1].equals(color))){
		    checkerLeft=false;
		}
		else{
		    //System.out.println("left;" + sum);
		    sum++;
		    leftCol--;
		    rowUp++;
		}
	    }
	    while (checkerRight && rightCol<data[0].length-1 && rowDown>0){ //&& rightCol<data[0].length-1 && rowUp<data.length-1){
		if (!(data[rowDown-1][rightCol+1].equals(color))){
		    checkerRight=false;
		}
		else{
		    //System.out.println("right;" + sum);
		    sum++;
		    rightCol++;
		    rowDown--;
		}
	    }
	    return sum >= cNumber;
	}

	catch(ArrayIndexOutOfBoundsException e){}
	catch(IndexOutOfBoundsException e){}
	return false;
    }
}
