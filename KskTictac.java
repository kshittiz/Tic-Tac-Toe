import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Random;
class KskTictac extends JFrame
{
 int pm=-1,k=0,kk=0,c=0,cc=0;//setting player mode and checking count
 int xc=0,zc=0;//counts cross and zero
 Random computer = new Random();
 int stop=0;String pat[]={"012","345","678","036","147","258","048","246"};
 int kl[]={0,0,0,0,0,0,0,0,0}; 
 int xpos[]=new int[5];
 int zpos[]=new int[5];
 int xdif[]=new int[15];
 int zdif[]=new int[15];
 JPanel jp[]=new JPanel[4];
 JTextField tf; String po=null;
 JButton Pagain;int clear=0;
 Font fo=new Font("serif",Font.BOLD,15);
 Border bo=BorderFactory.createTitledBorder("Instructions");
 Border bo1=BorderFactory.createTitledBorder("");
 Button b[]=new Button[9];
 JButton b1[]=new JButton[4];
 JLabel jl,jl1;
 int cplay=computer.nextInt(9);
 KskTictac(String s)
 {
  super(s);
  tf=new JTextField();
  String txt="<html><FONT COLOR=BLUE>Designed By.......</FONT>"+"<FONT COLOR=RED>KsK</FONT></html>";
  String txt1="<html><FONT COLOR=BLUE>Choose Playing Mode, Than Symbol :  </FONT>"+"<FONT COLOR=RED>X(cross)\t\t</FONT>"+"<FONT COLOR=BROWN>OR\t\t</FONT>"+"<FONT COLOR=GREEN>0(zero)</FONT></html>";
  jp[0]=new JPanel();jp[1]=new JPanel();jp[2]=new JPanel();jp[3]=new JPanel();//defining Jpanels
  jl=new JLabel(txt,JLabel.CENTER);jl.setBorder(bo1);
  jl1=new JLabel(txt1,JLabel.CENTER);jl1.setBorder(bo);
   jl1.setFont(fo);
  jl.setFont(fo); 
  Pagain=new JButton("New Game"); 
  add(jl1,BorderLayout.NORTH);//adding label jl1
  GridLayout gl=new GridLayout(3,3);
  GridLayout gl1=new GridLayout(3,1);
   GridLayout gl2=new GridLayout(2,1);
  jp[3].setLayout(gl1);
  jp[1].setLayout(gl2);
  jp[2].setLayout(gl2);
  jp[0].setLayout(gl);
  jp[3].add(jl);
  jp[3].add(tf);
  jp[3].add(Pagain);
  
  add(jp[3],BorderLayout.SOUTH);//adding label jl and textfield
  for(int i=0;i<9;i++)
    {
   b[i]=new Button("");
   b[i].setBackground(Color.WHITE);
   b[i].addActionListener(new Gamelogic(this)); 
   jp[0].add(b[i]);
    }
   add(jp[0],BorderLayout.CENTER);
   
   b1[0]=new JButton("ONE PLAYER");b1[0].setBackground(Color.YELLOW);b1[0].addActionListener(new Gamelogic(this));
   b1[1]=new JButton("TWO PLAYER");b1[1].setBackground(Color.YELLOW);b1[1].addActionListener(new Gamelogic(this));
   b1[2]=new JButton("X");b1[2].addActionListener(new Gamelogic(this));
   b1[3]=new JButton("O");b1[3].addActionListener(new Gamelogic(this));
   Pagain.setBackground(Color.GRAY);Pagain.addActionListener(new Gamelogic(this));
   
   jp[1].add(b1[0]);jp[1].add(b1[1]);
   add(jp[1],BorderLayout.WEST);
   jp[2].add(b1[2]);jp[2].add(b1[3]);
   add(jp[2],BorderLayout.EAST);
   
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setSize(500,400);
  setVisible(true); 
 }
  public static void main(String... s)
 {
  new KskTictac("TIC-TAC-TOE");
 }
}

class Gamelogic implements ActionListener
{
 KskTictac t; Font f=new Font("Arial",Font.BOLD,40);int ai;
 int k;String temp[]=new String[15];String temp1,tempc1,tempc2;
 
 Gamelogic(KskTictac t)
  {
    this.t=t;
  }
 
 public static String sort(String s)
 {
  String te=""; 
  int x[]=new int[s.length()];int swap;
    for(int i=0;i<s.length();i++)
     x[i]=Integer.parseInt(""+s.charAt(i));
  for (int c = 0; c < ( s.length() - 1 ); c++) {
      for (int d = 0; d < (s.length() - c - 1); d++) {
        if (x[d] > x[d+1]) 
        {
          swap       = x[d];
          x[d]   = x[d+1];
          x[d+1] = swap;
        }
      }
    }
  for(int c=0;c<s.length();c++)
  te=te+x[c];
  return te;
 }

 public void actionPerformed(ActionEvent e)
 { 
   if(e.getSource()==t.b1[1])
    {
     if(t.kk==0)
      {
     t.pm=0;
      t.tf.setText("SWITCHED TO MULTI-PLAYER");t.kk=1;
      }   
    }
   if(e.getSource()==t.b1[0])
    {
      if(t.kk==0)
       {
        t.pm=1;
        t.tf.setText("SWITCHED TO SINGLE-PLAYER");t.kk=1;
       }
    }
   if(e.getSource()==t.b1[2])
    {
      if(t.k==0 && t.pm!=-1)
       {
        t.po="X";t.tf.setText("YOU CHOSE SYMBOL "+t.po);
       t.k=1;
       }
      if(t.pm==-1)
       t.tf.setText("CHOOSE PLAYING MODE FIRST!");
     }
    if(e.getSource()==t.b1[3])
    {
      if(t.k==0 && t.pm!=-1)
       {
        t.po="O";t.tf.setText("YOU CHOSE SYMBOL "+t.po);
       t.k=1;
       }
      if(t.pm==-1)
       t.tf.setText("CHOOSE PLAYING MODE FIRST!");
     }
    if(e.getSource()==t.Pagain)
   {
    for(int i=0;i<9;i++)
     {t.b[i].setLabel(null);t.b[i].setBackground(Color.WHITE);t.kl[i]=0;}
       t.kk=0;t.k=0;t.xc=0;t.zc=0;t.c=0;t.cc=0;t.pm=-1;t.stop=0;t.tf.setText(null);
   }
 //-------------------starting GAME GRID--------------------------------------
  //------------------designing Multiplayer mode------------------------------ 
   if(t.pm==0 && t.k==1)//starting multiplayer
 {  
    if(e.getSource()==t.b[0]) 
    {
      if(t.kl[0]==0)
     { 
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
      if(t.po=="X"  && t.c%2==0)
        {
         t.xpos[t.xc]=0;
         t.b[0].setFont(f);
         t.b[0].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O"  && t.c%2==0)
        {
          t.zpos[t.zc]=0;
          t.b[0].setFont(f);
         t.b[0].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="X" && t.c%2!=0)
        {
         t.zpos[t.zc]=0;
         t.b[0].setFont(f);
         t.b[0].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.xpos[t.xc]=0;
          t.b[0].setFont(f);
         t.b[0].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
       
       t.kl[0]++;
      }
    }
       if(e.getSource()==t.b[1]) 

    {
       if(t.kl[1]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
     if(t.po=="X" && t.c%2==0)
        {
         t.xpos[t.xc]=1;
         t.b[1].setFont(f);
         t.b[1].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        {
          t.b[1].setFont(f);t.zpos[t.zc]=1;
         t.b[1].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="X" && t.c%2!=0)
        {
         t.b[1].setFont(f);t.zpos[t.zc]=1;
         t.b[1].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[1].setFont(f);t.xpos[t.xc]=1;
         t.b[1].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
      t.kl[1]++;
      }

    }
      if(e.getSource()==t.b[2]) 

    {
      if(t.kl[2]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
      if(t.po=="X" && t.c%2==0)
        {
         t.b[2].setFont(f);t.xpos[t.xc]=2;
         t.b[2].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        {
          t.b[2].setFont(f);t.zpos[t.zc]=2;
         t.b[2].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="X" && t.c%2!=0)
        {
         t.b[2].setFont(f);t.zpos[t.zc]=2;
         t.b[2].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[2].setFont(f);t.xpos[t.xc]=2;
         t.b[2].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
      t.kl[2]++;
     }

    }
     if(e.getSource()==t.b[3]) 
    {
      if(t.kl[3]==0)
     {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
      if(t.po=="X" && t.c%2==0)
        {
         t.b[3].setFont(f);t.xpos[t.xc]=3;
         t.b[3].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        { 
          t.b[3].setFont(f);t.zpos[t.zc]=3;
         t.b[3].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="X" && t.c%2!=0)
        {
         t.b[3].setFont(f);t.zpos[t.zc]=3;
         t.b[3].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[3].setFont(f);t.xpos[t.xc]=3;
         t.b[3].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
        t.kl[3]++;
       }

    }
       if(e.getSource()==t.b[4]) 

    {
       if(t.kl[4]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
     if(t.po=="X" && t.c%2==0)
        {
         t.b[4].setFont(f);t.xpos[t.xc]=4;
         t.b[4].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        { 
          t.b[4].setFont(f);t.zpos[t.zc]=4;
         t.b[4].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="X" && t.c%2!=0)
        {
         t.b[4].setFont(f);t.zpos[t.zc]=4;
         t.b[4].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[4].setFont(f);t.xpos[t.xc]=4;
         t.b[4].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
      t.kl[4]++;
      }
    }
      if(e.getSource()==t.b[5]) 

    {
      if(t.kl[5]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
     if(t.po=="X" && t.c%2==0)
        {
         t.b[5].setFont(f);t.xpos[t.xc]=5;
         t.b[5].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        {
          t.b[5].setFont(f);t.zpos[t.zc]=5;
         t.b[5].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="X" && t.c%2!=0)
        {
         t.b[5].setFont(f);t.zpos[t.zc]=5;
         t.b[5].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[5].setFont(f);t.xpos[t.xc]=5;
         t.b[5].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
       t.kl[5]++;
      }
    }
      if(e.getSource()==t.b[6]) 
    {
       if(t.kl[6]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
      if(t.po=="X" && t.c%2==0)
        {
         t.b[6].setFont(f);t.xpos[t.xc]=6;
         t.b[6].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        {
          t.b[6].setFont(f);t.zpos[t.zc]=6;
         t.b[6].setLabel("O");t.zc=t.zc+1;
        }
       if(t.po=="X" && t.c%2!=0)
        {
         t.b[6].setFont(f);t.zpos[t.zc]=6;
         t.b[6].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[6].setFont(f);t.xpos[t.xc]=6;
         t.b[6].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
      t.kl[6]++;
      }

    }
       if(e.getSource()==t.b[7]) 

    {
       if(t.kl[7]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
     if(t.po=="X" && t.c%2==0)
        {
         t.b[7].setFont(f);t.xpos[t.xc]=7;
         t.b[7].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        {
          t.b[7].setFont(f);t.zpos[t.zc]=7;
         t.b[7].setLabel("O");t.zc=t.zc+1;
        }
       if(t.po=="X" && t.c%2!=0)
        {
         t.b[7].setFont(f);t.zpos[t.zc]=7;
         t.b[7].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[7].setFont(f);t.xpos[t.xc]=7;
         t.b[7].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
      t.kl[7]++;
       }
    }
      if(e.getSource()==t.b[8]) 

    {
       if(t.kl[8]==0)
      {
      if(t.po==null)
       t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
     if(t.po=="X" && t.c%2==0)
        {
         t.b[8].setFont(f);t.xpos[t.xc]=8;
         t.b[8].setLabel("X");t.xc=t.xc+1;
        }
      if(t.po=="O" && t.c%2==0)
        {
          t.b[8].setFont(f);t.zpos[t.zc]=8;
         t.b[8].setLabel("O");t.zc=t.zc+1;
        }
       if(t.po=="X" && t.c%2!=0)
        {
         t.b[8].setFont(f);t.zpos[t.zc]=8;
         t.b[8].setLabel("O");t.zc=t.zc+1;
        }
      if(t.po=="O" && t.c%2!=0)
        {
          t.b[8].setFont(f);t.xpos[t.xc]=8;
         t.b[8].setLabel("X");t.xc=t.xc+1;
        }
     if(t.po!=null) 
      t.c++;
      t.kl[8]++;
      }

    } 
    if(t.stop==0)
    { 
      if(t.xc>=3 || t.zc>=3)
      {
        k=0;
        for(int i=0;i<t.xc-1;i++)
        {
          for(int j=i+1;j<t.xc;j++)
            {
              t.xdif[k]=Math.abs(t.xpos[i]-t.xpos[j]);  
              temp[k]=String.valueOf(t.xpos[i])+String.valueOf(t.xpos[j]);//System.out.println(temp[k]);
              k++;  
             }
        }//closing for
       
       for(int i=0;i<k-1;i++)
        {
          for(int j=i+1;j<k;j++)
          {
           if(t.xdif[i]==t.xdif[j])
            {
               if((temp[j].charAt(0)!=temp[i].charAt(0)) && (temp[j].charAt(0)!=temp[i].charAt(1)))
                 temp1=temp[i]+String.valueOf(temp[j].charAt(0));
                  else
                 temp1=temp[i]+String.valueOf(temp[j].charAt(1));
                 //System.out.println(temp1);
                  temp1=sort(temp1);//sorting the mapped string of buttons
                 // System.out.println(temp1);
              
            if(temp1.equals("012")==true || temp1.equals("345")==true || temp1.equals("678")==true || temp1.equals("036")==true
                || temp1.equals("147")==true || temp1.equals("258")==true  || temp1.equals("048")==true || temp1.equals("246")==true)
              {
               t.b[Integer.parseInt(""+temp1.charAt(0))].setBackground(Color.RED);
                t.b[Integer.parseInt(""+temp1.charAt(1))].setBackground(Color.RED);     
                 t.b[Integer.parseInt(""+temp1.charAt(2))].setBackground(Color.RED);
                   t.tf.setText("CROSS WINS!");
                   t.kk=0;t.k=0;
                   t.stop=1;
               }
               temp1="";
            }
          }
     
        }//closing for 
         temp1="";
        //-------------for matching zeros pattern---------------------------------------------------------------------   
       if(t.stop==0)
      {
         k=0;
        for(int i=0;i<t.zc-1;i++)
        {
          for(int j=i+1;j<t.zc;j++)
            {
              t.zdif[k]=Math.abs(t.zpos[i]-t.zpos[j]);  
              temp[k]=String.valueOf(t.zpos[i])+String.valueOf(t.zpos[j]);//System.out.println(temp[k]);
              k++;  
             }
        }//closing for
       
       for(int i=0;i<k-1;i++)
        {
          for(int j=i+1;j<k;j++)
          {
           if(t.zdif[i]==t.zdif[j])
            {
               if((temp[j].charAt(0)!=temp[i].charAt(0)) && (temp[j].charAt(0)!=temp[i].charAt(1)))
                 temp1=temp[i]+String.valueOf(temp[j].charAt(0));
                  else
                 temp1=temp[i]+String.valueOf(temp[j].charAt(1));
                // System.out.println(temp1);
                  temp1=sort(temp1);
                 // System.out.println(temp1);
              
              if(temp1.equals("012")==true || temp1.equals("345")==true || temp1.equals("678")==true || temp1.equals("036")==true
                || temp1.equals("147")==true || temp1.equals("258")==true  || temp1.equals("048")==true || temp1.equals("246")==true)
              {
               t.b[Integer.parseInt(""+temp1.charAt(0))].setBackground(Color.RED);
                t.b[Integer.parseInt(""+temp1.charAt(1))].setBackground(Color.RED);     
                 t.b[Integer.parseInt(""+temp1.charAt(2))].setBackground(Color.RED);
                   t.tf.setText("ZERO WINS!");
                   
                   t.stop=1;
                         
               }
               temp1="";
            }
          }
     
        }//closing for 
      }
       }
      
    }//closing stop
  }//closing multiplayer
 //-------------------Designing Single Player mode----------------------------------------------------------------------------
   if(t.pm==1 && t.k==1)//starting single player
  {
    
   
      if(e.getSource()==t.b[0] && t.cc%2==0)
      {
        if(t.kl[0]==0)
       {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[0].setFont(f);t.xpos[t.xc]=0;
         t.b[0].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[0].setFont(f);t.zpos[t.zc]=0;
           t.b[0].setLabel("O");t.zc=t.zc+1;
          }
           t.kl[0]=1;if(t.cc<8){t.cc++;}
       }
         //System.out.print(t.cc);
      }
      
      if(e.getSource()==t.b[1] && t.cc%2==0)
      {
         if(t.kl[1]==0)
       {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[1].setFont(f);t.xpos[t.xc]=1;
         t.b[1].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[1].setFont(f);t.zpos[t.zc]=1;
           t.b[1].setLabel("O");t.zc=t.zc+1;
          }
          t.kl[1]=1; if(t.cc<8){t.cc++;}
       }
       //System.out.print(t.cc);
     }
        
       if(e.getSource()==t.b[2] && t.cc%2==0)
      {
        if(t.kl[2]==0)
       {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[2].setFont(f);t.xpos[t.xc]=2;
         t.b[2].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[2].setFont(f);t.zpos[t.zc]=2;
           t.b[2].setLabel("O");t.zc=t.zc+1;
          }
          t.kl[2]=1; if(t.cc<8){t.cc++;}
       }
      //System.out.print(t.cc);
      }
    
         if(e.getSource()==t.b[3] && t.cc%2==0)
      {
        if(t.kl[3]==0)
        {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[3].setFont(f);t.xpos[t.xc]=3;
         t.b[3].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[3].setFont(f);t.zpos[t.zc]=3;
           t.b[3].setLabel("O");t.zc=t.zc+1;
          }
          t.kl[3]=1;if(t.cc<8){t.cc++;}
       }
       //System.out.print(t.cc);
     }


          if(e.getSource()==t.b[4] && t.cc%2==0)
      {
        if(t.kl[4]==0)
        {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[4].setFont(f);t.xpos[t.xc]=4;
         t.b[4].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[4].setFont(f);t.zpos[t.zc]=4;
           t.b[4].setLabel("O");t.zc=t.zc+1;
          }
           t.kl[4]=1;if(t.cc<8){t.cc++;}
        }
        //System.out.print(t.cc);
       }
 

           if(e.getSource()==t.b[5] && t.cc%2==0)
      {
         if(t.kl[5]==0)
       {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[5].setFont(f);t.xpos[t.xc]=5;
         t.b[5].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[5].setFont(f);t.zpos[t.zc]=5;
           t.b[5].setLabel("O");t.zc=t.zc+1;
          }
           t.kl[5]=1;if(t.cc<8){t.cc++;}
       }
         //System.out.print(t.cc);
     }


          if(e.getSource()==t.b[6] && t.cc%2==0)
      {
        if(t.kl[6]==0)
        {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[6].setFont(f);t.xpos[t.xc]=6;
         t.b[6].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[6].setFont(f);t.zpos[t.zc]=6;
           t.b[6].setLabel("O");t.zc=t.zc+1;
          }
          t.kl[6]=1; if(t.cc<8){t.cc++;}
        }
       //System.out.print(t.cc);
      }
            
        
         if(e.getSource()==t.b[7] && t.cc%2==0)
      {
         if(t.kl[7]==0)
         {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[7].setFont(f);t.xpos[t.xc]=7;
         t.b[7].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[7].setFont(f);t.zpos[t.zc]=7;
           t.b[7].setLabel("O");t.zc=t.zc+1;
          }
           t.kl[7]=1; if(t.cc<8){t.cc++;}
         }
        //System.out.print(t.cc);
      }
        

         if(e.getSource()==t.b[8] && t.cc%2==0)
       {
         if(t.kl[8]==0)
         {
         if(t.po==null)
         t.tf.setText("CHOOSE YOUR SYMBOL FIRST");
         if(t.po=="X")
           {
         t.b[8].setFont(f);t.xpos[t.xc]=8;
         t.b[8].setLabel("X");t.xc=t.xc+1;
           }
         if(t.po=="O")
          {
           t.b[8].setFont(f);t.zpos[t.zc]=8;
           t.b[8].setLabel("O");t.zc=t.zc+1;
          }
          t.kl[8]=1;if(t.cc<8){t.cc++;}
        }
       //System.out.print(t.cc);
       }
      

        if(t.cc%2!=0)
     {
        
          
          if(t.po=="X")
         {
          
           if(t.xc>1)
          {//starting artificial intelligence
             tempc1=""+t.xpos[t.xc-1];tempc2=""+t.xpos[t.xc-2];//System.out.print(tempc1);System.out.print(tempc2);
             for(int i=0;i<8;i++)
              { 
               if(t.pat[i].indexOf(tempc1)!=-1 && t.pat[i].indexOf(tempc2)!=-1)
                {
      
                       if((t.pat[i].indexOf(tempc1)==0 && t.pat[i].indexOf(tempc2)==1) || (t.pat[i].indexOf(tempc1)==1 && t.pat[i].indexOf(tempc2)==0))
                       t.cplay=Integer.parseInt(""+t.pat[i].charAt(2));
                       if((t.pat[i].indexOf(tempc1)==1 && t.pat[i].indexOf(tempc2)==2) || (t.pat[i].indexOf(tempc1)==2 && t.pat[i].indexOf(tempc2)==1))
                       t.cplay=Integer.parseInt(""+t.pat[i].charAt(0));
                       if((t.pat[i].indexOf(tempc1)==0 && t.pat[i].indexOf(tempc2)==2) || (t.pat[i].indexOf(tempc1)==2 && t.pat[i].indexOf(tempc2)==0))
                       t.cplay=Integer.parseInt(""+t.pat[i].charAt(1));
                 }
              }
            
           }//closing artificial intelligence
            
         if(t.kl[t.cplay]!=0)
       {
      while(t.kl[t.cplay]!=0)
          t.cplay=t.computer.nextInt(9);
       }
          t.b[t.cplay].setFont(f);
         t.zpos[t.zc]=t.cplay;
         t.b[t.cplay].setLabel("O");
         t.zc=t.zc+1;
          t.kl[t.cplay]=1;
         }

         if(t.po=="O")
          {
            if(t.zc>1)
          {// starting artificial intelligence
             tempc1=""+t.zpos[t.zc-1];tempc2=""+t.zpos[t.zc-2];//System.out.print(tempc1);System.out.print(tempc2);
             for(int i=0;i<8;i++)
              { 
               if(t.pat[i].indexOf(tempc1)!=-1 && t.pat[i].indexOf(tempc2)!=-1)
                {
      
                       if((t.pat[i].indexOf(tempc1)==0 && t.pat[i].indexOf(tempc2)==1) || (t.pat[i].indexOf(tempc1)==1 && t.pat[i].indexOf(tempc2)==0))
                       t.cplay=Integer.parseInt(""+t.pat[i].charAt(2));
                       if((t.pat[i].indexOf(tempc1)==1 && t.pat[i].indexOf(tempc2)==2) || (t.pat[i].indexOf(tempc1)==2 && t.pat[i].indexOf(tempc2)==1))
                       t.cplay=Integer.parseInt(""+t.pat[i].charAt(0));
                       if((t.pat[i].indexOf(tempc1)==0 && t.pat[i].indexOf(tempc2)==2) || (t.pat[i].indexOf(tempc1)==2 && t.pat[i].indexOf(tempc2)==0))
                       t.cplay=Integer.parseInt(""+t.pat[i].charAt(1));
                 }
              }
            
           }//closing artificial intelligence
              
         if(t.kl[t.cplay]!=0)
       {
      while(t.kl[t.cplay]!=0)
          t.cplay=t.computer.nextInt(9);
       }
           t.b[t.cplay].setFont(f);t.xpos[t.xc]=t.cplay;
           t.b[t.cplay].setLabel("X");t.xc=t.xc+1;
           t.kl[t.cplay]=1;
          }
        t.cc++;
     }


        
      
      
      if(t.xc>=3 || t.zc>=3)
      {
        if(t.stop==0)
       {
        k=0;
        for(int i=0;i<t.xc-1;i++)
        {
          for(int j=i+1;j<t.xc;j++)
            {
              t.xdif[k]=Math.abs(t.xpos[i]-t.xpos[j]);  
              temp[k]=String.valueOf(t.xpos[i])+String.valueOf(t.xpos[j]);//System.out.println(temp[k]);
              k++;  
             }
        }//closing for
       
       for(int i=0;i<k-1;i++)
        {
          for(int j=i+1;j<k;j++)
          {
           if(t.xdif[i]==t.xdif[j])
            {  
                
               if((temp[j].charAt(0)!=temp[i].charAt(0)) && (temp[j].charAt(0)!=temp[i].charAt(1)))
                 temp1=temp[i]+String.valueOf(temp[j].charAt(0));
                  else
                 temp1=temp[i]+String.valueOf(temp[j].charAt(1));
                 //System.out.println(temp1);
                  temp1=sort(temp1);//sorting the mapped string of buttons
                 // System.out.println(temp1);
              
             if(temp1.equals("012")==true || temp1.equals("345")==true || temp1.equals("678")==true || temp1.equals("036")==true
                || temp1.equals("147")==true || temp1.equals("258")==true  || temp1.equals("048")==true || temp1.equals("246")==true)
              {
               t.b[Integer.parseInt(""+temp1.charAt(0))].setBackground(Color.RED);
                t.b[Integer.parseInt(""+temp1.charAt(1))].setBackground(Color.RED);     
                 t.b[Integer.parseInt(""+temp1.charAt(2))].setBackground(Color.RED);
                   t.tf.setText("CROSS WINS!");
                   t.kk=0;t.k=0;
                   t.stop=1;
               }
               temp1="";
            }
          }
     
        }//closing for 
       }
         temp1="";
        //-------------for matching zeros pattern---------------------------------------------------------------------   
       
        if(t.stop==0)
       {  
          k=0;
        for(int i=0;i<t.zc-1;i++)
        {
          for(int j=i+1;j<t.zc;j++)
            {
              t.zdif[k]=Math.abs(t.zpos[i]-t.zpos[j]);  
              temp[k]=String.valueOf(t.zpos[i])+String.valueOf(t.zpos[j]);//System.out.println(temp[k]);
              k++;  
             }
        }//closing for
       
       for(int i=0;i<k-1;i++)
        {
          for(int j=i+1;j<k;j++)
          {
           if(t.zdif[i]==t.zdif[j])
            {
               if((temp[j].charAt(0)!=temp[i].charAt(0)) && (temp[j].charAt(0)!=temp[i].charAt(1)))
                 temp1=temp[i]+String.valueOf(temp[j].charAt(0));
                  else
                 temp1=temp[i]+String.valueOf(temp[j].charAt(1));
                // System.out.println(temp1);
                  temp1=sort(temp1);
                 // System.out.println(temp1);
              
              if(temp1.equals("012")==true || temp1.equals("345")==true || temp1.equals("678")==true || temp1.equals("036")==true
                || temp1.equals("147")==true || temp1.equals("258")==true  || temp1.equals("048")==true || temp1.equals("246")==true)
              {
               t.b[Integer.parseInt(""+temp1.charAt(0))].setBackground(Color.RED);
                t.b[Integer.parseInt(""+temp1.charAt(1))].setBackground(Color.RED);     
                 t.b[Integer.parseInt(""+temp1.charAt(2))].setBackground(Color.RED);
                   t.tf.setText("ZERO WINS!");
                   
                   t.stop=1;
                         
               }
               temp1="";
            }
          }
     
        }//closing for 
       
      }//closing stop
        
    }//closing if
  }//closing single player mode
     
     
 }//closing action performed

}
    
 
