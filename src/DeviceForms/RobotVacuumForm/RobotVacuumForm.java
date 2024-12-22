package DeviceForms.RobotVacuumForm;

import Devices.RobotVacuum;
import Devices.CustomRobotVacuumException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RobotVacuumForm extends JFrame {
    private JLabel statusLRobotVacuumabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton selectRoomButton;
    private JPanel mainPanel;


    private RobotVacuum vacuum;

    public RobotVacuumForm(RobotVacuum vacuum) {
        this.vacuum = vacuum;

        setTitle("Robot Süpürge Kontrol Paneli - " + vacuum.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);

        // Başlat butonu
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vacuum.startCleaning();
                    updateStatus();
                } catch (CustomRobotVacuumException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Durdur butonu
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vacuum.stopCleaning();
                updateStatus();
            }
        });

        // Oda seçimi butonu
        selectRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] roomKeys = vacuum.listRooms().keySet().toArray();
                String selectedKey = (String) JOptionPane.showInputDialog(
                        null,
                        "Hangi odayı temizlemek istiyorsunuz?",
                        "Oda Seçimi",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roomKeys,
                        roomKeys[0]
                );

                if (selectedKey != null) {
                    try {
                        vacuum.selectRoom(selectedKey);
                        updateStatus();
                    } catch (CustomRobotVacuumException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void updateStatus() {
        String status = vacuum.isVacuumRunning() ? "Çalışıyor" : "Durmuş";
        String room = vacuum.getSelectedRoom() != null ? vacuum.getSelectedRoom() : "Seçilmedi";
        statusLRobotVacuumabel.setText("Durum: " + status + " | Oda: " + room);
        statusLRobotVacuumabel.setForeground(vacuum.isVacuumRunning() ? Color.GREEN : Color.RED);
    }
}
