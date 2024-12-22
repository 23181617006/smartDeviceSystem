package DeviceForms;

import Devices.SmartCurtain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartCurtainForm extends JFrame {
    private JLabel statusCurtainLabel;  // Perdenin durumunu göstermek için kullanılan etiket
    private JButton turnOffButton;     // Perde sistemini kapatma düğmesi
    private JButton turnOnButton;      // Perde sistemini açma düğmesi
    private JSlider openPercentageSlider; // Perde açıklık oranını ayarlamak için kaydırma çubuğu
    private JPanel mainPanel;          // Ana panel

    private SmartCurtain smartCurtain; // SmartCurtain nesnesi

    public SmartCurtainForm(SmartCurtain smartCurtain) {
        this.smartCurtain = smartCurtain;

        setTitle("Smart Curtain Control");
        setContentPane(mainPanel); // Ana paneli ayarla
        setSize(400, 300);         // Pencere boyutlandırması
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencere kapanma ayarını yap
        setVisible(true);

        // Başlangıç durumu
        updateStatus();

        // "Turn On" düğmesi işlemleri
        turnOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!smartCurtain.getStatus()) {
                    smartCurtain.turnOn(); // Perdeyi aç
                    JOptionPane.showMessageDialog(null, "Curtain successfully turned on!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus(); // Durum güncellemesi
                } else {
                    JOptionPane.showMessageDialog(null, "Curtain is already turned on.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // "Turn Off" düğmesi işlemleri
        turnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (smartCurtain.getStatus()) {
                    smartCurtain.turnOff(); // Perdeyi kapat
                    JOptionPane.showMessageDialog(null, "Curtain successfully turned off!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus(); // Durum güncellemesi
                } else {
                    JOptionPane.showMessageDialog(null, "Curtain is already turned off.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Perde açıklık oranını kontrol eden slider
        openPercentageSlider.addChangeListener(e -> {
            if (smartCurtain.getStatus()) {
                int percentage = openPercentageSlider.getValue(); // Slider'dan gelen yüzde değeri
                smartCurtain.setOpenPercentage(percentage);      // Açıklık oranını güncelle
                updateStatus(); // Durum güncellemesi
            } else {
                openPercentageSlider.setValue(0); // Sistem kapalıysa slider sıfırlanır
                JOptionPane.showMessageDialog(null, "Please turn on the curtain system first!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Durum güncelleme metodu (açıklık yüzdesi ve sistem durumu)
    private void updateStatus() {
        String statusText = smartCurtain.getStatus() ? "Open" : "Closed"; // Açık mı kapalı mı?
        int percentage = smartCurtain.getOpenPercentage();               // Açıklık yüzdesi
        statusCurtainLabel.setText("Status: " + statusText + " | Open Percentage: %" + percentage);
    }
}