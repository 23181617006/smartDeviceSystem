package Devices;

import java.util.HashMap;
import java.util.Map;

public class WashingMachine extends SmartDevice {

    private boolean isRunning; // Makine çalışıyor mu?
    private String selectedProgram; // Seçilen program kodu
    private final Map<String, String> programDescription; // Program kodları ve açıklamaları

    // Constructor
    public WashingMachine(String name, boolean status) {
        super(name, status);
        this.isRunning = status;
        this.selectedProgram = null;
        this.programDescription = new HashMap<>();

        // Program kodları ve açıklamaları
        this.programDescription.put("A", "Ön yıkamalı pamuklu (50°-95°)");
        this.programDescription.put("D", "Ön yıkamalı sentetik (30°-60°)");
        this.programDescription.put("B", "Pamuklu (30°-95°)");
        this.programDescription.put("F", "Sentetik (30°-60°)");
        this.programDescription.put("X", "Pamuklu kısa (30°-50°)");
        this.programDescription.put("G", "Sentetik kısa (Soğuk-30°)");
        this.programDescription.put("M", "Pamuklu renkli (Soğuk-40°)");
        this.programDescription.put("J", "Yünlü (Soğuk-40°)");
        this.programDescription.put("C", "Pamuklu sıkma");
        this.programDescription.put("H", "Sentetik/Yünlü sıkma");
    }

    // Çamaşır makinesinin çalışıp çalışmadığını kontrol eder
    public boolean isRunning() {
        return isRunning;
    }

    // Seçilen programı döner
    public String getSelectedProgram() {
        return selectedProgram;
    }

    // Tüm programları döner
    public Map<String, String> getProgramDescription() {
        return programDescription;
    }

    // Program seçimi ve doğrulama
    public boolean setProgram(String programCode) {
        if (programDescription.containsKey(programCode)) {
            selectedProgram = programCode;
            return true; // Geçerli program seçildi
        }
        return false; // Hatalı program kodu
    }

    // Makineyi başlatır
    public boolean start() {
        if (!isRunning && selectedProgram != null) {
            isRunning = true;
            return true; // Başlatıldı
        }
        return false; // Zaten çalışıyor ya da program seçilmemiş
    }

    // Makineyi durdurur
    public boolean stop() {
        if (isRunning) {
            isRunning = false;
            return true; // Durduruldu
        }
        return false; // Zaten durmuş
    }

    // Yıkama işlemini simüle eden bir metot (örnek bir bekleme mantığı eklenebilir)
    public void completeProgram() {
        if (isRunning) {
            // İşlem bittiğinde durdurur
            stop();
        }
    }
}