package Bank.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame implements ActionListener {
    JLabel lable2;
    String pin;
    JButton b1;

    BalanceEnquriy(String pin) {
this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);


        JLabel lable1 = new JLabel("your current balance is Rs ");
        lable1.setForeground(Color.WHITE);
        lable1.setFont(new Font("System", Font.BOLD, 16));
        lable1.setBounds(430, 180, 700, 35);
        l3.add(lable1);

        lable2 = new JLabel();
        lable2.setForeground(Color.WHITE);
        lable2.setFont(new Font("System", Font.BOLD, 16));
        lable2.setBounds(430, 220, 400, 35);
        l3.add(lable2);

        b1 = new JButton("Back");
        b1.setBounds(700, 406, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);
        int balance = 0;
        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '" + pin + "'");
            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lable2.setText("" + balance);


        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquriy("");
    }
}
