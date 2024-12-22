package DeviceForms;

import Devices.DoorLock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoorLockForm extends JFrame{
    private JLabel doorLockLabel;
    private JLabel statusPWLabel;
    private JButton getPWButton;
    private JButton setPWButton;
    private JPasswordField setPWField;
    private JButton lockButton;
    private JButton unlockButton;
    private JPanel mainPanel;



    private DoorLock doorLock;


    public DoorLockForm(DoorLock doorLock) {

        this.doorLock = doorLock;

        // Form ayarları
        setTitle("Doorlock Kontrol Paneli - " + doorLock.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400, 300);
        setVisible(true);
        pack();


        loadDoorLockDetails();




        setPWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(setPWField.getPassword());

                if (password != null){
                    JOptionPane.showMessageDialog(null , "Password set successfully!" , "Success" , JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null , "Password cannot be empty!" , "Error" , JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        getPWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, doorLock.getPassword() , "Password" , JOptionPane.INFORMATION_MESSAGE);
            }
        });


        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doorLock.turnOn();
                JOptionPane.showMessageDialog(null, "Doorlock is locked!" , "Success" , JOptionPane.INFORMATION_MESSAGE);
            }
        });


        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doorLock.turnOff();
                JOptionPane.showMessageDialog(null, "Doorlock is unlocked!" , "Success" , JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }



    // Kapı kilidi başlangıç bilgilerini GUI'ye yazdır
    private void loadDoorLockDetails() {
        doorLockLabel.setText("Door Lock: " + doorLock.getName());
        updateStatusLabel();
    }

    // Şifre ve durum bilgilerini dinamik olarak güncelle
    private void updateStatusLabel() {
        String status = doorLock.isRunning() ? "Status: UNLOCKED" : "Status: LOCKED";
        statusPWLabel.setText(status);
    }
}
