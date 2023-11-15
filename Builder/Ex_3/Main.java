import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    
		DataProcessor textDataProcessor = new TextDataProcessor();
		DataProcessor audioDataProcessor = new AudioDataProcessor();
		DataProcessor videoDataProcessor = new VideoDataProcessor();
		
		Data textObject = new Data("Text", new Text());
		Data audioObject = new Data("Audio", new Audio());
		Data videoObject = new Data("Video", new Video());
		
		System.out.println(textObject.getType() + " " + textObject.getContent());
		System.out.println(audioObject.getType() + " " + textObject.getContent());
		System.out.println(videoObject.getType() + " " + videoObject.getContent());
		
		DataProcessorCreator creator = new DataProcessorCreator();
		
		creator.setProcessor(textDataProcessor);
	}
}

class Data{
    String Type;
    Object Content;
    
    public Data(String type, Object content){
        this.Type = type;
        this.Content = content;
    }
    
    public String getType(){
        return this.Type;
    }
    
    public Object getContent(){
        return this.Content;
    }
}

abstract class DataProcessor{
    abstract void createDataProcessor(Data data);
}

class TextDataProcessor extends DataProcessor{
    
    Data currentData;
    
    public void createDataProcessor(Data data){
        this.currentData = data;
    }
}

class AudioDataProcessor extends DataProcessor{
     
    Data currentData;
    
    public void createDataProcessor(Data data){
        this.currentData = data;
    }
}

class VideoDataProcessor extends DataProcessor{
    
    Data currentData;
    
    public void createDataProcessor(Data data){
        this.currentData = data;
    }
}

class DataProcessorCreator{
    
    DataProcessor currentDataProcessor;
    
    public void setProcessor(DataProcessor setValue){
        this.currentDataProcessor = setValue;
    }
    
    public void processData(Data data){
        this.currentDataProcessor.createDataProcessor(data);
    }
}

class Text{
    
}

class Audio{
    
}

class Video{
    
}