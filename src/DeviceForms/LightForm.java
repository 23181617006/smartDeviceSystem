package DeviceForms;

import Devices.Light;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LightForm extends JFrame {
    private JLabel statusLightLabel;
    private JButton turnOnButton;
    private JButton turnOffButton;
    private JSlider brightnessSlider;
    private JSlider redValueSlider;
    private JSlider greenValueSlider;
    private JSlider blueValueSlider;
    private JPanel mainPanel;


    private Light light;

    public LightForm(Light light) {

        this.light = light;
        setTitle("Fridge Kontrol Paneli - " + light.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);



        turnOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!light.getStatus()) {
                    JOptionPane.showMessageDialog(null, "Light turned on!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    light.turnOn();
                    updateStatus();
                } else {
                    JOptionPane.showMessageDialog(null, "Light is already on!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        turnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (light.getStatus()) {
                    JOptionPane.showMessageDialog(null, "Light turned off!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    light.turnOff();
                    updateStatus();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Light is already off!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        brightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int brightness = brightnessSlider.getValue();
                light.setBrightness(brightness);
                updateStatus();
            }
        });


        // Kırmızı Renk Slider'ı
        redValueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateColor();
            }
        });

        // Yeşil Renk Slider'ı
        greenValueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateColor();
            }
        });

        // Mavi Renk Slider'ı
        blueValueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateColor();
            }
        });
    }


    // Işığın açık/kapalı durumunu ve parlaklığı etikette gösterir
    private void updateStatus() {
        String statusText = light.getStatus() ? "Açık" : "Kapalı";
        statusLightLabel.setText("Durum: " + statusText + ", Parlaklık: " + light.getBrightness());
        statusLightLabel.setForeground(light.getStatus() ? Color.GREEN : Color.RED);
    }

    // RGB renk kontrollerini günceller
    private void updateColor() {
        int red = redValueSlider.getValue();
        int green = greenValueSlider.getValue();
        int blue = blueValueSlider.getValue();
        light.setRGBColor(red, green, blue);

        // RGB Güncellemesi (Arka plan örneği)
        mainPanel.setBackground(new Color(red, green, blue));
    }
}

