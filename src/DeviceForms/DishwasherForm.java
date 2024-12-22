package DeviceForms;

import Devices.Dishwasher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DishwasherForm extends JFrame {
    private JLabel dishwasherNameLabel;
    private JLabel statusLabel;
    private JComboBox<String> programsComboBox;
    private JButton turnOnDW;
    private JButton turnOffDW;
    private JButton checkProgramButton;
    private JPanel mainPanel;

    private Dishwasher dishwasher; // Formun bağlı olduğu cihaz

    public DishwasherForm(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;

        // Form ayarları
        setTitle("Dishwasher Kontrol Paneli - " + dishwasher.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400, 300);

        // Başlangıçta cihaz bilgilerini GUI'ye yükle
        loadDishwasherDetails();

        // AÇ/KAPA buton işlevleri
        turnOnDW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dishwasher.isRunning()) {
                    dishwasher.turnOn();
                    updateStatusLabel();
                    JOptionPane.showMessageDialog(null, "Dishwasher is turned ON!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Dishwasher is already running!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        turnOffDW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dishwasher.isRunning()) {
                    dishwasher.turnOff();
                    updateStatusLabel();
                    JOptionPane.showMessageDialog(null, "Dishwasher is turned OFF!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Dishwasher is already OFF!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Program seçimi dinamiği
        programsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProgram = (String) programsComboBox.getSelectedItem();
                if (selectedProgram != null) {
                    dishwasher.selectProgram(selectedProgram);
                    JOptionPane.showMessageDialog(null, "Selected program: " + selectedProgram, "Program Selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Mevcut program bilgisi kontrol butonu
        checkProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = dishwasher.isRunning()
                        ? "Current program: " + dishwasher.getCurrentProgram()
                        : "Dishwasher is OFF.";
                JOptionPane.showMessageDialog(null, message, "Program Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setVisible(true);
    }

    // Formda cihaz bilgilerini yükleme
    private void loadDishwasherDetails() {
        dishwasherNameLabel.setText("Dishwasher: " + dishwasher.getName());
        updateStatusLabel();

        // Program listesini programa yükle
        programsComboBox.removeAllItems();
        for (String program : dishwasher.getPrograms().keySet()) {
            programsComboBox.addItem(program);
        }
    }

    // Durum bilgisini dinamik olarak güncelleme
    private void updateStatusLabel() {
        String status = dishwasher.isRunning()
                ? "Status: Running - " + dishwasher.getCurrentProgram()
                : "Status: OFF";
        statusLabel.setText(status);
    }
}