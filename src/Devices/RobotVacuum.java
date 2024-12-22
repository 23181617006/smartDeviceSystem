package Devices;

import java.util.Map;
import java.util.HashMap;

public class RobotVacuum extends SmartDevice implements RobotVacuumInterface {
    private boolean isRunning; // Süpürgenin çalışıp çalışmadığını belirtir
    private String selectedRoom; // Seçilen odanın adını tutar
    private final Map<String, String> roomOptions; // Mevcut odaların kodları ve adları

    // Constructor
    public RobotVacuum(String name, boolean status) {
        super(name, status);
        this.isRunning = status;
        this.selectedRoom = null;

        // Oda seçeneklerini başlat
        this.roomOptions = new HashMap<>();
        this.roomOptions.put("L", "Oturma Odası");
        this.roomOptions.put("K", "Mutfak");
        this.roomOptions.put("B", "Yatak Odası");
        this.roomOptions.put("Y", "Çalışma Odası");
    }

    @Override
    public void startCleaning() throws CustomRobotVacuumException {
        if (isRunning) {
            throw new CustomRobotVacuumException("Süpürge zaten çalışıyor.");
        }
        if (selectedRoom == null) {
            throw new CustomRobotVacuumException("Temizlenecek bir oda seçilmedi!");
        }
        isRunning = true; // Süpürge çalışmaya başlar
    }

    @Override
    public void stopCleaning() {
        if (isRunning) {
            isRunning = false; // Süpürge durdurulur
        }
    }

    @Override
    public Map<String, String> listRooms() {
        return new HashMap<>(roomOptions); // Mevcut oda listesini döndürür
    }

    @Override
    public boolean selectRoom(String roomCode) throws CustomRobotVacuumException {
        if (!roomOptions.containsKey(roomCode)) {
            throw new CustomRobotVacuumException("Geçersiz oda seçimi!");
        }
        if (isRunning) {
            throw new CustomRobotVacuumException("Süpürge çalışırken oda değiştirilemez.");
        }
        this.selectedRoom = roomOptions.get(roomCode); // Oda seçilir
        return true;
    }

    @Override
    public boolean isVacuumRunning() {
        return isRunning; // Süpürgenin çalışıp çalışmadığını döndürür
    }

    @Override
    public String getSelectedRoom() {
        return selectedRoom; // Seçilen odanın adını döndürür
    }
}
