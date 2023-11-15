import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    
		Weather weatherObject = new Weather();
		
		weatherObject.showWeatherList();
		System.out.println(" ");
		
		DailyWeather dWeatherObject = new DailyWeather();
		MeteorWeather mWeatherObject = new MeteorWeather();
		Adapter adapter = new Adapter();
		
		dWeatherObject.getData();
		weatherObject.setWeather(dWeatherObject.getWeatherIndex());
		System.out.println(weatherObject.getWeather());
		System.out.println(" ");
		
		mWeatherObject.getDatas();
		
		adapter.setAdaptee(mWeatherObject);
		adapter.parametersToWeatherIndex();
		
		weatherObject.setWeather(adapter.getWeatherIndex());
		System.out.println(weatherObject.getWeather());
	}
}

class Weather{
    
    private String[] sampleList = {"Sunny", "Cloudy", "Rain", "Storm", "Foggy", "Snow", "Strong Wind"};
    private List<String> weatherList = new ArrayList<>(Arrays.asList(sampleList));
    private String currentWeather;
    
    public void setWeather(int setIndex){
        this.currentWeather = weatherList.get(setIndex);
    }
    
    public String getWeather(){
        return this.currentWeather;
    }
    
    public void showWeatherList(){
        for(int i = 0; i < weatherList.size(); i++){
            System.out.println(weatherList.get(i));
        }
    }
    
    public void addWeather(String setValue){
        this.weatherList.add(setValue);
    }
    
    public void removeWeather(String setValue){
        this.weatherList.remove(setValue);
    }
    
    public void cleanWeather(){
        this.weatherList = new ArrayList<>();
    }
}

class DailyWeather{
    
    private int weatherIndex;
    
    public int getWeatherIndex(){
        return this.weatherIndex;
    }
    
    public void getData(){
        this.weatherIndex = (int)(Math.random() * 7);
    }
}

class MeteorWeather{
    
    private int temperature;
    private int pressure;
    private int humidity;
    private int precipitation;
    private int wind;
    private int cloudiness;
    
    public int getTemperature(){
        return this.temperature;
    }
    
    public int getPressure(){
        return this.pressure;
    }
    
    public int getHumidity(){
        return this.humidity;
    }
    
    public int getPrecipitation(){
        return this.precipitation;
    }
    
    public int getWind(){
        return this.wind;
    }
    
    public int getCloudiness(){
        return this.cloudiness;
    }
    
    public void getDatas(){
        this.temperature = (int)(Math.random() * 81) - 40;
        this.pressure = (int)(Math.random() * 45) + 990;
        this.humidity = (int)(Math.random() * 101);
        this.precipitation = (int)(Math.random() * 101);
        this.cloudiness = (int)(Math.random() * 101);
        this.wind = (int)(Math.random() * 40);
    }
}

interface AdapterProtocol{
    
    void parametersToWeatherIndex();
    void setAdaptee(MeteorWeather setValue);
    int getWeatherIndex();
}

class Adapter implements AdapterProtocol{
    
    private MeteorWeather mWeatherAdaptee;
    private int weatherIndex;
    
    public void parametersToWeatherIndex(){
        this.weatherIndex = (mWeatherAdaptee.getCloudiness() + mWeatherAdaptee.getWind()
        + mWeatherAdaptee.getTemperature() + mWeatherAdaptee.getHumidity() 
        + mWeatherAdaptee.getPrecipitation() + mWeatherAdaptee.getPressure()) % 7;
    }
    
    public void setAdaptee(MeteorWeather setValue){
        this.mWeatherAdaptee = setValue;
    }
    
    public int getWeatherIndex(){
        return this.weatherIndex;
    }
}