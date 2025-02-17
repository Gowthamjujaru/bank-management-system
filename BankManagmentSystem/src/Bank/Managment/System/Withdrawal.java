package Bank.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {
    String pin;
    TextField textfield;
    JButton b1, b2;

    Withdrawal(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);


        JLabel lable1 = new JLabel("Maximum withdrawal is 10,000");
        lable1.setForeground(Color.WHITE);
        lable1.setFont(new Font("System", Font.BOLD, 16));
        lable1.setBounds(460, 180, 700, 35);
        l3.add(lable1);

        JLabel lable2 = new JLabel("enter your amount");
        lable2.setForeground(Color.WHITE);
        lable2.setFont(new Font("System", Font.BOLD, 16));
        lable2.setBounds(460, 220, 400, 35);
        l3.add(lable2);

        textfield = new TextField();
        textfield.setBackground(new Color(65, 125, 128));
        textfield.setForeground(Color.WHITE);
        textfield.setBounds(460, 260, 320, 25);
        textfield.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textfield);
        b1 = new JButton("WithDraw");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){



        try {
            String amount = textfield.getText();
            Date date = new Date();
            if (textfield.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "enter amout to withdraw");

            } else {
                Con c = new Con();
                ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '" + pin + "'");
                int balance = 0;
                while (resultSet.next()) {
                    if (resultSet.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance = -Integer.parseInt(resultSet.getString("amount"));

                    }
                }
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "sorry!insufficient balance");
                    return;
                }
                c.statement.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "' )");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                setVisible(false);
                new main_Class(pin);
            }
        } catch (Exception E) {


        }
        } else if (e.getSource()==b2) {
            setVisible(false);
            new main_Class(pin);

        }
    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}
