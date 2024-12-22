package DeviceForms;

import Devices.Television;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelevisionForm extends JFrame{
    private JLabel statusTelevisionLabel;
    private JButton turnOffButton;
    private JButton turnOnButton;
    private JSlider volumeSlider;
    private JComboBox<String> channelComboBox;
    private JButton changeChannelButton;
    private JPanel mainPanel;


    private Television television;


    public TelevisionForm(Television television) {


        this.television = television;

        setTitle("Televizyon Kontrol Paneli - " + television.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);

        updateStatus();

        turnOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!television.getStatus()) {
                    JOptionPane.showMessageDialog(null, "Television turned on!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    television.turnOn();
                    updateStatus();
                } else {
                    JOptionPane.showMessageDialog(null, "Television is already on!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        turnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (television.getStatus()) {
                    JOptionPane.showMessageDialog(null, "Television turned off!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    television.turnOn();
                    updateStatus();
                } else {
                    JOptionPane.showMessageDialog(null, "Television is already closed!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        // Ses Seviyesi Ayarı (Volume Slider)
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (television.isOn()) {
                    int volume = volumeSlider.getValue();
                    television.setVolume(volume);
                    updateStatus();
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen önce televizyonu açın!", "Hata", JOptionPane.ERROR_MESSAGE);
                    volumeSlider.setValue(television.getVolumeLevel()); // Eski değere geri döner
                }
            }
        });

        // Kanal ComboBox'ını başlat
        for (String channel : television.getChannelList()) {
            channelComboBox.addItem(channel);
        }

        // Kanal Değiştirme Butonu
        changeChannelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (television.isOn()) {
                    int selectedIndex = channelComboBox.getSelectedIndex();
                    television.changeChannel(selectedIndex);
                    JOptionPane.showMessageDialog(null, "Kanal değiştirildi: " + television.getCurrentChannel(), "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus();
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen önce televizyonu açın!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Televizyon durumunu güncelle
    private void updateStatus() {
        String statusText = television.isOn() ? "Açık" : "Kapalı";
        String currentChannel = television.isOn() ? television.getCurrentChannel() : "N/A";
        int volume = television.getVolumeLevel();

        statusTelevisionLabel.setText("Durum: " + statusText + ", Kanal: " + currentChannel + ", Ses: " + volume);

        // Bileşenlerin durumlarını güncelle
        volumeSlider.setEnabled(television.isOn());
        changeChannelButton.setEnabled(television.isOn());
        channelComboBox.setEnabled(television.isOn());
    }
}