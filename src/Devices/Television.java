package Devices;

import java.util.ArrayList;
import java.util.List;

public class Television extends SmartDevice {
    private boolean isOn; // Televizyonun açık/kapalı durumu
    private final List<String> channelList; // Kanal listesi
    private String currentChannel; // Şu anki kanal (aktif kanal)
    private int volumeLevel; // Ses seviyesi (0-100 arasında)

    // Constructor: Yeni bir televizyon objesi oluşturur
    public Television(String name, boolean status) {
        super(name, status);
        this.isOn = status;
        this.currentChannel = null; // Varsayılan olarak hiç bir kanal ayarlanmaz
        this.volumeLevel = 50; // Varsayılan ses seviyesi

        // Kanal listesini oluştur
        this.channelList = new ArrayList<>();
        channelList.add("TRT 1");
        channelList.add("ATV");
        channelList.add("Show TV");
        channelList.add("Kanal D");
        channelList.add("Star TV");
        channelList.add("FOX TV");
        channelList.add("TV8");
        channelList.add("Kanal 7");
        channelList.add("NTV");
        channelList.add("HaberTürk");
    }

    // GETTER VE SETTER METOTLAR:

    // Televizyonun açık/kapalı durumunu kontrol et
    public boolean isOn() {
        return this.isOn;
    }

    // Mevcut ses seviyesini al
    public int getVolumeLevel() {
        return volumeLevel;
    }

    // Mevcut seçili kanal
    public String getCurrentChannel() {
        return currentChannel == null ? "Kanal seçilmedi" : currentChannel;
    }

    // Tüm kanal listesini al
    public List<String> getChannelList() {
        return channelList;
    }

    // TELEVİZYONUN TEMEL FONKSİYONLARI:

    // Televizyonu açar
    public void turnOn() {
        this.isOn = true;
    }

    // Televizyonu kapatır
    public void turnOff() {
        this.isOn = false;
        this.currentChannel = null; // Kapandığında kanal sıfırlanır
    }


    // Ses seviyesini belirler
    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volumeLevel = volume;
        } else {
            throw new IllegalArgumentException("Ses seviyesi 0-100 arasında olmalıdır.");
        }
    }

    // Kanal değiştirir
    public void changeChannel(int channelIndex) {
        if (channelIndex >= 0 && channelIndex < channelList.size()) {
            this.currentChannel = channelList.get(channelIndex);
        } else {
            throw new IllegalArgumentException("Geçersiz kanal indexi.");
        }
    }
}