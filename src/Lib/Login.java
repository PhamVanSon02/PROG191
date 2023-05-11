package Lib;

import Main.Cafemain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    private JPanel MainPanel;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnExit;
    private JTextField txtUser;

    public Login(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cancel();
            }
        });
    }

    private void login() {
        String Username = txtUser.getText();
        String Password = String.valueOf(txtPass.getPassword());
        Cafemain r = null;
        User admin = new User("admin", "12345");
        User checkUser = new User(Username,Password);

        boolean login = false;
        if (admin.equals(checkUser)){
            r = new Cafemain(Username,this);
            login = true;
        }
        if(login){
            r.setVisible(true);
            this.setVisible(false);
        }else{
            showMess("Login failed");
        }
    }

    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this,mess);
    }

    private void Cancel() {
        int re = JOptionPane.showConfirmDialog(this,"Do you wanna exit?","Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if(re == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        Login c = new Login("Login Management");
        c.setVisible(true);
        c.setLocationRelativeTo(null);
    }

}