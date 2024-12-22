package DeviceForms;

import Devices.WashingMachine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WashingMachineForm extends JFrame {
    private JLabel statusWashingMachineLabel; // Makinenin durumunu gösteren etiket
    private JButton stopButton; // Makineyi durdurma butonu
    private JButton startButton; // Makineyi başlatma butonu
    private JComboBox<String> programListComboBox; // Program seçim kutusu
    private JButton changeProgramButton; // Program değiştirme butonu
    private JPanel mainPanel; // Ana panel

    private WashingMachine washingMachine; // Çamaşır makinesi objesi

    public WashingMachineForm(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;

        // Form başlığı ve temel ayarlar
        setTitle("Washing Machine Control Panel - " + washingMachine.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);

        // Programları combobox'a ekle
        for (String programCode : washingMachine.getProgramDescription().keySet()) {
            programListComboBox.addItem(programCode + " - " + washingMachine.getProgramDescription().get(programCode));
        }

        // O anki durum güncelleniyor
        updateStatus();

        // Başlatma butonuna tıklama olayı
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (washingMachine.start()) {
                    JOptionPane.showMessageDialog(null, "Washing machine started!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Machine is already running or no program is selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateStatus();
            }
        });

        // Durdurma butonuna tıklama olayı
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (washingMachine.stop()) {
                    JOptionPane.showMessageDialog(null, "Washing machine stopped!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "The machine is already stopped!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateStatus();
            }
        });

        // Program değiştirme butonuna tıklama olayı
        changeProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProgram = (String) programListComboBox.getSelectedItem();
                if (selectedProgram != null) {
                    String programCode = selectedProgram.split(" - ")[0]; // Kod kısmını ayır
                    if (washingMachine.setProgram(programCode)) {
                        JOptionPane.showMessageDialog(null, "Program set to: " + programCode, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to set the selected program!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                updateStatus();
            }
        });
    }

    // Makinenin durumunu ve seçilen programı günceller
    private void updateStatus() {
        String runningStatus = washingMachine.isRunning() ? "Running" : "Stopped";
        String programStatus = washingMachine.getSelectedProgram() != null ? washingMachine.getSelectedProgram() : "No Program Selected";
        statusWashingMachineLabel.setText("Status: " + runningStatus + " | Program: " + programStatus);
    }
}