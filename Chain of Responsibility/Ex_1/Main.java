import java.util.*;

public class Main {
  public static void main(String[] args) {
    Request req = new Request("I have a problem with software", 5);
    Request req1 = new Request("I have a problem with hardware", 1);
    Request req2 = new Request("I have a problem with internet", -8);

    System.out.println(req.getId() + " " + req1.getId() + " " + req2.getId());
    System.out.println(req.getDescription() + " " + req1.getDescription() + " " + req2.getDescription());
    System.out.println(req.getPriority() + " " + req1.getPriority() + " " + req2.getPriority());

    InternetIssueHandler netIssue = new InternetIssueHandler();
    SoftwareIssueHandler softIssue = new SoftwareIssueHandler(netIssue);
    HardwareIssueHandler hardIssue = new HardwareIssueHandler(softIssue);

    hardIssue.handle(req);
    hardIssue.handle(req1);
    hardIssue.handle(req2);
  }
}

class Request{
  private static int ids = 0;
  private int id;
  private String description;
  private int priority;

  public Request(String description, int priority){
    this.description = description;
    this.id = ids++; 
    if(priority > 2 || priority < -1){
      this.priority = 2;
    }
    else{
      this.priority = priority;
    }
  }

  public int getId(){
    return this.id;
  } 

  public String getDescription(){
    return this.description;
  }

  public int getPriority(){
    return this.priority;
  }
} 

interface Handler{
  public void handle(Request request);
}

abstract class BasicHandler implements Handler{
  protected Handler nextHandler;

  abstract public void handle(Request request);
  abstract public void setNextHandler(Handler handler);
}

class HardwareIssueHandler extends BasicHandler{
  
  public HardwareIssueHandler(){
  }

  public HardwareIssueHandler(Handler setValue){
    super.nextHandler = setValue;
  }

  @Override
  public void handle(Request request){
    String[] words = request.getDescription().split("\\s+");
    for(String word : words){
      if(word.equals("hardware")){
        System.out.println("It is a hardware problem");
        return;
      }
    }
    if(super.nextHandler != null){
      super.nextHandler.handle(request);
    }
  }

  public void setNextHandler(Handler setValue){
    super.nextHandler = setValue;
  }
}

class SoftwareIssueHandler extends BasicHandler{
  
  public SoftwareIssueHandler(){
  }

  public SoftwareIssueHandler(Handler setValue){
    super.nextHandler = setValue;
  }

  @Override
  public void handle(Request request){
    String[] words = request.getDescription().split("\\s+");
    for(String word : words){
      if(word.equals("software")){
        System.out.println("It is a software problem");
        return;
      }
    }
    if(super.nextHandler != null){
      super.nextHandler.handle(request);
    }
  }

  public void setNextHandler(Handler setValue){
    super.nextHandler = setValue;
  }
}

class InternetIssueHandler extends BasicHandler{
  
  public InternetIssueHandler(){
  }

  public InternetIssueHandler(Handler setValue){
    super.nextHandler = setValue;
  }

  @Override
  public void handle(Request request){
    String[] words = request.getDescription().split("\\s+");
    for(String word : words){
      if(word.equals("internet")){
        System.out.println("It is a internet problem");
        return;
      }
    }
    if(super.nextHandler != null){
      super.nextHandler.handle(request);
    }
  }

  public void setNextHandler(Handler setValue){
    super.nextHandler = setValue;
  }
}
