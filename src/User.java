import Devices.SmartDevice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    //cihazlar, username ve password değişkenleri tanımlandi.

    private String username;
    private String password;
    private List<SmartDevice> devices;

    //constructorı oluşturuldu.

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.devices = new ArrayList<>(); // Varsayılan olarak boş bir cihaz listesi
    }

    public List<SmartDevice> getDevices() {
        return devices;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void removeDevice(SmartDevice device) {
        devices.remove(device);
    }

    // Kullanıcıyı dosyaya kaydetme
    public void saveToFile(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // true ile append modunda yazıyoruz
            writer.write(this.username + "," + this.password);
            writer.newLine();
        }
    }

    // Dosyadan kullanıcı doğrulama
    public static boolean authenticateFromFile(String username, String password, File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String fileUsername = parts[0];
                    String filePassword = parts[1];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}