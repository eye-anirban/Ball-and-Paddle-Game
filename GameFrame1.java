//Ball and Paddle Game
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Ball
{
int x,y,dx,dy;
int top , left , right , bottom ;
public Ball(int a,int b)
{
    x=a;
    y=b;
    dx=(int)(7*Math.random()) + 3;
    dy=(int)(7*Math.random()) + 3;
    top = left = 100 ;
    right = bottom = 500 ;
}
public void draw(Graphics g)
{
    Graphics2D  g2D = (Graphics2D)g;
    g2D.setColor(Color.black);
    g2D.drawRect(100,100,400,400);

    g2D.setColor(Color.red);
    g2D.fillOval(x,y,20,20);
}

public void move( )
{
    x = x + dx ;
    y = y + dy ;
    if (x<=left || x+20>=right)
      {  dx = - dx ; }
    if (y<=top || y+20>=bottom)
      {  dy = - dy ; }
 }
 public Rectangle getObject()
{
Rectangle r=new Rectangle (x,y,20,20);
return r; 
}
public void changeDirection()
{
dy=-dy;
}
public boolean hitBottom()
{
    if(y+20>=bottom)
      { return true ; }
     return false ; 
}
}
 class Paddle
{
int x,y;
int left,right ;
public Paddle(int a,int b)
{
x=a;
y=b;
left =100; right = 500 ;
}
public  void draw(Graphics g)
{
Graphics2D  g2D = (Graphics2D)g;
   
    g2D.setColor(Color.black);
    g2D.fillRect(x,y,70,10);
}
public void moveLeft( )
{
 if(x<=left)
     return;
 x = x-10 ;
}
public void moveRight( )
{
 if(x+70>=right)
   return;
 x = x+10 ;
}
public Rectangle getObject()
{
Rectangle r=new Rectangle (x,y,70,10);
return r; 
}



}
 class GamePanel extends JPanel implements ActionListener,KeyListener
{
  Ball ball1 ;
  Paddle paddle;
  Timer t;
  JLabel scoreDisplay , gameOver;
  int score ;
 public GamePanel( )
{
    setSize(600,600);
    setBackground(Color.lightGray);
    setLayout(null);
    ball1 = new Ball(250,150) ;
    paddle = new Paddle(260,450);
    score = 0 ;
    scoreDisplay= new JLabel(" ");
    scoreDisplay.setFont(new Font("arial",Font.BOLD,14));
    scoreDisplay.setForeground(Color.black);
    scoreDisplay.setText("Score = "+score);
    scoreDisplay.setBounds(220,30,150,60);
    add(scoreDisplay) ;
    
    gameOver= new JLabel(" ");
    gameOver.setFont(new Font("arial",Font.BOLD,18));
    gameOver.setForeground(Color.blue);
    gameOver.setText("Game Running");
    gameOver.setBounds(220,501,150,60);
    add(gameOver) ;
    
    t = new Timer(45,this) ;
    t.start( ) ;
    addKeyListener(this) ;
    setFocusable(true);
    requestFocus();
    
}
public boolean collide( )
{
Rectangle r1=ball1.getObject();
Rectangle r2=paddle.getObject();
return r1.intersects(r2);
}
 
public void paint(Graphics g)
{
super.paint(g) ;
if(ball1.hitBottom())
{
    gameOver.setForeground(Color.red);
    gameOver.setText("GAME OVER!");
    scoreDisplay.setText("Final Score: "+score);
    t.stop();
}
if(collide( ) )
    { ball1.changeDirection() ;
      score ++;
      scoreDisplay.setText("Score = "+score);}
ball1.move( );
ball1.draw(g) ;
paddle.draw(g) ;
}
public void actionPerformed(ActionEvent ae)
{
 repaint( );   
}
public void keyReleased(KeyEvent e)
{
}
public void keyTyped(KeyEvent e)
{
}
public void keyPressed(KeyEvent e)
{
    int x = e.getKeyCode( );
    if(x==KeyEvent.VK_RIGHT)
        {
            paddle.moveRight();
        }
        else if(x==KeyEvent.VK_LEFT)
        {
            paddle.moveLeft();
        }
}
}

public class GameFrame1 extends JFrame
{
    public GameFrame1( )
    {
        setSize(600,600);
        setLayout(null);
        add( new GamePanel( ) );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
public static void main(String args[])
{
    new GameFrame1( ) ;
}
}
