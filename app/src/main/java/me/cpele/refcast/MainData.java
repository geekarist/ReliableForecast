package me.cpele.refcast;

@SuppressWarnings("unused")
public class MainData {

    private final String mTemperature;

    MainData(String temperature) {

        mTemperature = temperature;
    }

    public String getTemperature() {
        return mTemperature;
    }
}
