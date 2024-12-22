package Devices;

import java.util.Map;

public interface RobotVacuumInterface {
    void startCleaning() throws CustomRobotVacuumException; // Süpürgeyi başlatır
    void stopCleaning(); // Süpürgeyi durdurur
    Map<String, String> listRooms(); // Mevcut odaları döndürür
    boolean selectRoom(String roomCode) throws CustomRobotVacuumException; // Oda seçimini yapar
    boolean isVacuumRunning(); // Süpürgenin çalışıp çalışmadığını kontrol eder
    String getSelectedRoom(); // Seçilen odanın adını döndürür
}
