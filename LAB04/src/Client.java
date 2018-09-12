import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame implements ActionListener{
    private JTextField jtf=new JTextField();
    private JTextArea jta=new JTextArea();
    private Writer outputToServer;
    private DataInputStream inputFromServer;
    private State state=State.start;
    private boolean process=true;
    public static void main(String[] args){
        new Client();
    }
    public Client() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Enter information that you need to enter : "), BorderLayout.WEST);
        p.add(jtf, BorderLayout.CENTER);
        jtf.setHorizontalAlignment(JTextField.RIGHT);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(p, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);

        jtf.addActionListener(this);
        jta.append("welcome,please enter number from 1 to 4 to choose the service you want:"
                +"\n"+"1 means signIn,2 means signUp,3 means openLesson,4 means chooseLesson,others will"+
                "\n"+" be useless");
        setTitle("Client");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            inputFromServer = new DataInputStream(socket.getInputStream());
            outputToServer = new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            jta.append(e.toString());
        }
    }
    public void actionPerformed(ActionEvent e){
        String actionCommand=e.getActionCommand();
        if(e.getSource() instanceof JTextField){
            try {
                String enter=jtf.getText().trim();
                if(state==State.start){
                    if(enter.equals("1")){
                        state=State.signIn;
                    }
                    else if(enter.equals("2")){
                        state=State.signUp;
                    }
                    else if(enter.equals("3")){
                        state=State.openLesson;
                    }
                    else if(enter.equals("4")){
                        state=State.chooseLesson;
                    }
                    else{
                        state=State.start;
                    }
                }
                if(state==State.signIn){
                    jta.append("Please enter your username : ");
                    if(process) {

                    }
                    else{
                        jta.append("Please enter your password");
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}