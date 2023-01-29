package guicalculator;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.*;

/**
 *
 * @author Usman_Aslam
*/

class Stack {
    int top;
    String[] l = new String[20];
    Stack()
    {
        top = -1;
       
    }
    void push(String d)
    {
        if(top < 9)
        {
            top = top + 1;
            l[top] = d;
        }
        else
            System.out.println("Stack OverFlow...!!!!!\n");
    }
    String pop()
    {
        if(top >= 0)
            return l[top--];
        else
            return "#";
    }
    String peek()
    {
        return l[top];
    }
    int getTop()
    {
        return top;
    }
    void display()
    {
        for(int i=0; i<=top; i++)
            System.out.println(l[i]);
    }
}
class Expression extends Stack {
    String s;
    Expression(String ss)
    {
        s = ss;
    }
    int precedence(String cc)
    {
        switch(cc)
        {
            case"*":
            case"/":
                return 2;
            case"+":
            case"-":
                return 1;
            default:
                return 0;
        }
    }
    double solve_the_Expression()
    {
        
        String last_val;
        while(getTop() > 0)
        {
            //System.out.println("Top" + getTop());
            last_val = pop();
            switch(pop())
            {
                case"+":
                    push((Double.parseDouble(pop()) + Double.parseDouble(last_val))+"");
                    break;
                case"-":
                    push((Double.parseDouble(pop()) - Double.parseDouble(last_val))+"");
                    break;
                case"*":
                    push((Double.parseDouble(pop()) * Double.parseDouble(last_val))+"");
                    break;
                case"/":
                    push((Double.parseDouble(pop()) / Double.parseDouble(last_val))+"");
                    break;            
            }
        }
        return  Double.parseDouble(pop());
    }
    boolean isOperator(char op)
    {
        switch(op)
        {
            case'+':
            case'-':
            case'*':
            case'/':
                return true;
            default:
                return false;
        }
    }
    boolean isDigit(String d)
    {
        if(d.charAt(0)>= '0' && d.charAt(0) <= '9')
            return true;
        else 
            return false;
        
    }

    void parse_the_Expression()
    {
        char ch;
        String last_val, last_op;
        int len = s.length();
        
        for(int i = 0; i<len; i++)
        {
            ch = s.charAt(i);
            if(isDigit(""+ch))
            {
                 //System.out.println("character: " + ch);
                last_val = pop();
                if(isDigit(last_val))
                {
                    push(last_val+ch);
                    //System.out.println("peak: " + peek());
                }
                else if(last_val.equals("#"))
                    push(""+ch);
                else
                {
                    push(last_val);
                    push(""+ch);
                }
                
               
            }
            else if(isOperator(ch))
            {
                //System.out.println("Operator: " + ch);
                if(getTop() == 0)
                    push(""+ch);
                else
                {
                    last_val = pop();
                    last_op = pop();
                    
                    if(precedence(""+ch) > precedence(last_op))
                    {
                        push(last_op);
                        push(last_val);
                    }
                    else
                    {
                        switch(last_op)
                        {
                            case"+":
                                push((Double.parseDouble(pop()) + Double.parseDouble(last_val))+"");
                                break;
                            case"-":
                                push((Double.parseDouble(pop()) - Double.parseDouble(last_val))+"");
                                break;
                            case"*":
                                push((Double.parseDouble(pop()) * Double.parseDouble(last_val))+"");
                                break;
                            case"/":
                                push((Double.parseDouble(pop()) / Double.parseDouble(last_val))+"");
                                break;
                        }
                    }
                    push(""+ch);
  
                }
            }
            else if(ch == ' ')
                System.out.println("Space ...");
            else
            {
                System.out.println("Invalid Expression...!!!\n");
                break;
            }
            
        }
    }
}
public class GuiCalculator  implements ActionListener{    
 
    static  JTextField inputBox = new JTextField(13);
 
    public static void main(String[] args) {
        createWindow();
    }
    private static void createWindow() {          
      JFrame frame = new JFrame("Calculator");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      createUI(frame);
      frame.setSize(200, 240);            
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
    private static void createUI(JFrame win) {
        
        JPanel p = new JPanel();
        GuiCalculator c = new GuiCalculator();
        
        JButton butt0 = new JButton(" 0 ");JButton butt1 = new JButton(" 1 ");
        JButton butt2 = new JButton(" 2 ");JButton butt3 = new JButton(" 3 ");
        JButton butt4 = new JButton(" 4 ");JButton butt5 = new JButton(" 5 " );
        JButton butt6 = new JButton(" 6 ");JButton butt7 = new JButton(" 7 ");
        JButton butt8 = new JButton(" 8 ");JButton butt9 = new JButton(" 9 ");
        JButton buttAdd = new JButton(" + ");JButton buttSub = new JButton(" - ");
        JButton buttMul = new JButton(" * ");JButton buttDiv = new JButton(" / ");
        JButton buttCl = new JButton(" C ");JButton buttEq = new JButton(" = ");
    
        p.add(inputBox);p.add(butt1);p.add(butt2);
        p.add(butt3);p.add(butt4);p.add(butt5);
        p.add(butt6);p.add(butt7);p.add(butt8);
        p.add(butt9);p.add(buttCl);p.add(butt0);
        p.add(buttEq);p.add(buttAdd);p.add(buttSub);
        p.add(buttMul);p.add(buttDiv);
        
        
        butt0.addActionListener(c);butt1.addActionListener(c);
        butt2.addActionListener(c);butt3.addActionListener(c);
        butt4.addActionListener(c);butt5.addActionListener(c);
        butt6.addActionListener(c);butt7.addActionListener(c);
        butt8.addActionListener(c);butt9.addActionListener(c);
        buttAdd.addActionListener(c);buttSub.addActionListener(c);
        buttMul.addActionListener(c);buttDiv.addActionListener(c);
        buttCl.addActionListener(c);buttEq.addActionListener(c);
        
        win.add(p);
            
    }
    @Override
    public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      
        switch (command.charAt(1)) {
            case 'C':
                inputBox.setText("");
                break;
            case '=':
                inputBox.setText(evaluate(inputBox.getText()));
                break;
            default:
                inputBox.setText(inputBox.getText() + command);
                break;
        }
   }
 
    public static String evaluate(String expression) {
      System.out.println("Expression = "+expression);
      Expression exp = new Expression(expression);
      
      
      double result = 0;

      exp.parse_the_Expression();
      exp.display();
      result = exp.solve_the_Expression();
      return expression + "=" +result;
      
   }    
    
}
