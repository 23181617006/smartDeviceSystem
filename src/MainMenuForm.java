import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Cihaz sınıflarını import edelim
import DeviceForms.*;
import DeviceForms.RobotVacuumForm.RobotVacuumForm;
import Devices.*;

public class MainMenuForm {

    //Mainform classında kullanılan butonlar ve değişkenlerin tanımlanması

    private JButton addDeviceButton;
    private JButton removeDeviceButton;
    private JTable table1;
    private JPanel mainPanel;
    private JLabel lblDateTime;

    private final User currentUser; // Şu anda giriş yapan kullanıcı

    public MainMenuForm(User user) {
        this.currentUser = user;

        //formun düzenlenmesi

        JFrame frame = new JFrame("Ana Menü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(600, 400);
        frame.setVisible(true);

        // üstte gözükecek tarih ve zaman

        Timer timer = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            lblDateTime.setText(now.format(formatter));
        });
        timer.start();


        // Kullanıcının cihazlarını JTable'a dolduralması
        updateDeviceTable();

        // Cihaz ekleme butonuna aksiyon ekleyelim
        addDeviceButton.addActionListener(e -> handleAddDevice());
        // Cihaz kaldırma butonuna aksiyon ekleyelim
        removeDeviceButton.addActionListener(e -> handleRemoveDevice());
    }

    // Tabloyu cihazlarla doldur
    private void updateDeviceTable() {
        String[] columnNames = {"Cihaz Adı", "İşlemler"}; // Veritabanı sütun isimleri
        List<SmartDevice> devices = currentUser.getDevices();

        Object[][] rowData = new Object[devices.size()][2];

        int i = 0;
        for (SmartDevice device : devices) {
            rowData[i][0] = device.getName(); // Cihaz adı
            rowData[i][1] = "İşlemler"; // İşlemler butonu
            i++;
        }

        table1.setModel(new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Sadece "İşlemler" hücresini düzenlenebilir yapıyoruz (buton)
            }
        });

        // İşlemler sütunu için özel renderer ve editor
        table1.getColumn("İşlemler").setCellRenderer(new ButtonRenderer());
        table1.getColumn("İşlemler").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    // "Cihaz Ekle" butonuna tıklanınca
    private void handleAddDevice() {
        String[] availableDevices = {"Light", "Dishwasher", "Thermostat", "DoorLock", "Fridge", "WashingMachine", "SmartCurtain", "Television", "RobotVacuum"};

        String selectedDeviceType = (String) JOptionPane.showInputDialog(
                null,
                "Cihaz Türü Seçiniz:",
                "Cihaz Ekleme",
                JOptionPane.QUESTION_MESSAGE,
                null,
                availableDevices,
                availableDevices[0]
        );

        if (selectedDeviceType != null) {
            String deviceName = JOptionPane.showInputDialog("Cihaz İsmini Giriniz:");
            if (deviceName != null && !deviceName.isBlank()) {
                SmartDevice newDevice = createDevice(selectedDeviceType, deviceName);

                if (newDevice != null) {
                    currentUser.addDevice(newDevice);
                    updateDeviceTable(); // Tabloyu yeniden güncelle
                }
            } else {
                JOptionPane.showMessageDialog(null, "Geçersiz cihaz adı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // "Cihaz Kaldır" butonuna tıklanınca
    private void handleRemoveDevice() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            String deviceName = table1.getValueAt(selectedRow, 0).toString();
            SmartDevice deviceToRemove = currentUser.getDevices().stream()
                    .filter(device -> device.getName().equals(deviceName))
                    .findFirst()
                    .orElse(null);

            if (deviceToRemove != null) {
                currentUser.removeDevice(deviceToRemove);
                updateDeviceTable(); // Tabloyu güncelle
            }
        } else {
            JOptionPane.showMessageDialog(null, "Kaldırmak için bir cihaz seçmelisiniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }

    // İşlemler butonu için cihaz türüne göre form aç
    private void handleDeviceDetails(int row) {
        String deviceName = table1.getValueAt(row, 0).toString();
        SmartDevice selectedDevice = currentUser.getDevices().stream()
                .filter(device -> device.getName().equals(deviceName))
                .findFirst()
                .orElse(null);

        if (selectedDevice != null) {
            if (selectedDevice instanceof Light) {
                new LightForm((Light) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof Dishwasher) {
                new DishwasherForm((Dishwasher) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof Thermostat) {
                new ThermostatForm((Thermostat) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof DoorLock) {
                new DoorLockForm((DoorLock) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof Fridge) {
                new FridgeForm((Fridge) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof WashingMachine) {
                new WashingMachineForm((WashingMachine) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof SmartCurtain) {
                new SmartCurtainForm((SmartCurtain) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof Television) {
                new TelevisionForm((Television) selectedDevice).setVisible(true);
            } else if (selectedDevice instanceof RobotVacuum) {
                new RobotVacuumForm((RobotVacuum) selectedDevice).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Bu cihaza özel bir işlem bulunamadı.", "Bilinmeyen Cihaz", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Dinamik cihaz nesnesi oluştur
    private SmartDevice createDevice(String type, String name) {
        switch (type) {
            case "Light":
                return new Light(name, false);
            case "Dishwasher":
                return new Dishwasher(name, false);
            case "Thermostat":
                return new Thermostat(name, false);
            case "DoorLock":
                return new DoorLock(name, false);
            case "Fridge":
                return new Fridge(name, false);
            case "WashingMachine":
                return new WashingMachine(name, false);
            case "SmartCurtain":
                return new SmartCurtain(name, false);
            case "Television":
                return new Television(name, false);
            case "RobotVacuum":
                return new RobotVacuum(name, false);
            default:
                return null;
        }
    }

    // Tabloya buton eklemek için renderer
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Tabloya buton işlevsellik kazandırmak için editor
    class ButtonEditor extends DefaultCellEditor {
        private final JButton button = new JButton();
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped(); // Düzenlemeyi sonlandır
                handleDeviceDetails(row); // Satıra göre işlem yap
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row; // Hangi satırda tıklanıldığını al
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }
}