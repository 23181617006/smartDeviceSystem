package DeviceForms;

import Devices.Fridge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FridgeForm extends JFrame {
    private JLabel statusLabel;
    private JButton setUpperTempButton;
    private JButton showUpperTemperatureButton;
    private JButton showLoverTemperatureButton;
    private JButton setLowerTempButton;
    private JButton getAverageTempButton;
    private JTextField upperTempTF;
    private JTextField lowerTempTF;
    private JPanel mainPanel;


    private Fridge fridge;


    public FridgeForm(Fridge fridge) {

        this.fridge = fridge;
        setTitle("Fridge Kontrol Paneli - " + fridge.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);





        setUpperTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                double temp = Double.parseDouble(upperTempTF.getText());
                fridge.setUpperCompartmentTemperature(temp);
                JOptionPane.showMessageDialog(null, "Üst bölme sıcaklığı başarıyla ayarlandı: " + temp + " °C");
                updateStatusLabel();
                }
                catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Hatalı giriş! Lütfen bir sayı giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
                catch (IllegalArgumentException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        setLowerTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double temp = Double.parseDouble(lowerTempTF.getText());
                    fridge.setUpperCompartmentTemperature(temp);
                    JOptionPane.showMessageDialog(null, "Alt bölme sıcaklığı başarıyla ayarlandı: " + temp + " °C");
                    updateStatusLabel();

                }
                catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Hatalı giriş! Lütfen bir sayı giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
                catch (IllegalArgumentException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        getAverageTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               double temp = fridge.getAverageTemperature();
               JOptionPane.showMessageDialog(null, "Average temperature is : " +temp+ " °C");
                updateStatusLabel();
            }
        });


        showLoverTemperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = fridge.getLowerCompartmentTemperature();
                JOptionPane.showMessageDialog(null, "Lower temperature is : " +temp+ " °C");
                updateStatusLabel();
            }
        });


        showUpperTemperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = fridge.getUpperCompartmentTemperature();
                JOptionPane.showMessageDialog(null, "Upper temperature is : " +temp+ " °C");
                updateStatusLabel();
            }
        });


    }

    // Fridge bilgilerini GUI'ye yükler ve durum etiketini günceller
    private void updateStatusLabel() {
        statusLabel.setText(
                        "Üst Bölme: " + fridge.getUpperCompartmentTemperature() + " °C, " +
                        "Alt Bölme: " + fridge.getLowerCompartmentTemperature() + " °C, " +
                        "Ortalama: " + fridge.getAverageTemperature() + " °C"
        );
    }
}
