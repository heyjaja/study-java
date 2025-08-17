package basic.oop;

public class MusicPlayer {
    int volume;
    boolean isOn;

    public void volumeUp(){
        volume++;
        System.out.println("음악 플레이어 볼륨: " + volume);
    }

    public void volumeDown(){
        volume--;
        System.out.println("음악 플레이어 볼륨: " + volume);
    }

    public void on(){
        isOn = true;
        System.out.println("음악 플레이어를 시작합니다.");
    }

    public void off(){
        isOn = false;
        System.out.println("음악 플레이어를 종료합니다.");
    }

    public void showStatus() {
        System.out.println("음악 플레이어 상태 확인");
        System.out.println(isOn ? "음악 플레이어 ON, 볼륨: " + volume : "음악 플레이어 OFF");
    }
}

