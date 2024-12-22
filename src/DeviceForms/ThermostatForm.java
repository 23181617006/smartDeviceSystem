package DeviceForms;

import Devices.Thermostat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThermostatForm extends JFrame {
    private JLabel statusThermostatLabel; // Termostatın durumunu gösterecek etiket
    private JButton turnOffButton; // Termostatı kapatma butonu
    private JButton turnOnButton; // Termostatı açma butonu
    private JSlider temperatureSlider; // Sıcaklık kontrolü için slider
    private JPanel mainPanel; // Ana panel

    private Thermostat thermostat; // Kontrol edilecek termostat nesnesi

    public ThermostatForm(Thermostat thermostat) {
        this.thermostat = thermostat;

        // Pencerenin başlığını ve temel ayarlarını yapılandır
        setTitle("Thermostat Kontrol Paneli - " + thermostat.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setContentPane(mainPanel);
        setVisible(true);

        // Slider için minimum, maksimum değerler ve başlangıç sıcaklığı
        temperatureSlider.setMinimum(15); // Minimum sıcaklık
        temperatureSlider.setMaximum(25); // Maksimum sıcaklık
        temperatureSlider.setValue((int) thermostat.getCurrentTemperature()); // Varsayılan sıcaklık
        updateStatus(); // Varsayılan durumu GUI ile eşitle

        // Açma butonu
        turnOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!thermostat.getStatus()) { // Termostat kapalıysa aç
                    thermostat.turnOn();
                    JOptionPane.showMessageDialog(null, "Thermostat turned on!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus(); // GUI'yi güncelle
                } else {
                    JOptionPane.showMessageDialog(null, "Thermostat is already on!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Kapatma butonu
        turnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (thermostat.getStatus()) { // Termostat açıksa kapat
                    thermostat.turnOff();
                    JOptionPane.showMessageDialog(null, "Thermostat turned off!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus(); // GUI'yi güncelle
                } else {
                    JOptionPane.showMessageDialog(null, "Thermostat is already off!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sıcaklık Slider'ına dinleyici ekle
        temperatureSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (thermostat.getStatus()) { // Termostat açıksa sıcaklığı değiştir
                    int sliderValue = temperatureSlider.getValue(); // Slider'ın anlık değerini al
                    thermostat.setTemperature(sliderValue); // Termostat sıcaklığını ayarla
                    updateStatus(); // GUI'yi güncelle
                } else {
                    // Termostat kapalıysa slider eski değere geri döner
                    temperatureSlider.setValue((int) thermostat.getCurrentTemperature());
                    JOptionPane.showMessageDialog(null, "Please turn on the thermostat before adjusting temperature.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Termostat durumunu (açık/kapalı ve sıcaklık değerini) etikette günceller
    private void updateStatus() {
        String statusText = thermostat.getStatus() ? "Açık" : "Kapalı"; // Açık/Kapalı durumu
        String temperatureText = thermostat.getStatus() ? thermostat.getCurrentTemperature() + " °C" : "N/A"; // Sıcaklık durumu
        statusThermostatLabel.setText("Durum: " + statusText + ", Sıcaklık: " + temperatureText);
        statusThermostatLabel.setForeground(thermostat.getStatus() ? Color.GREEN : Color.RED); // Duruma göre renk değiştir

        // Slider'ın aktif/pasif durumunu güncelle
        temperatureSlider.setEnabled(thermostat.getStatus());
    }
}