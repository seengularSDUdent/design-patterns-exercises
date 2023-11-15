public class Main
{
	public static void main(String[] args) {
		NotificationSystem noty = new Notification();
		noty.send();
		
		noty = new NotificationDecorator(noty);
		noty.send();
		
		System.out.println(((NotificationDecorator)noty).getWrap());
		
		AllowedNotifications allow = new AllowedNotifications(false, false, true);
		
        User user0 = new User("user0");
        user0.setAllowed(allow);
        user0.launch().send();
	}
}

interface NotificationSystem{
    
    public void send();
}

class Notification implements NotificationSystem{
    
    public void send(){
        System.out.println("sent!");
    }
}

class NotificationDecorator implements NotificationSystem{
    
    NotificationSystem wrappee;
    
    public NotificationDecorator(NotificationSystem wrap){
        this.wrappee = wrap;
    }
    
    public void send(){
        this.wrappee.send();
        System.out.println("sent decorator!");
    } 
    
    public NotificationSystem getWrap(){
        return wrappee;
    }
}

class SmsNotification extends NotificationDecorator{
    
    public SmsNotification(NotificationSystem wrap){
        super(wrap);
    }
    
    public void send(){
        super.send();
        System.out.println("sent sms decorator!");
    }
}

class PushNotification extends NotificationDecorator{
    
    public PushNotification(NotificationSystem wrap){
        super(wrap);
    }
    
    public void send(){
        super.send();
        System.out.println("sent push decorator!");
    }
}

class EmailNotification extends NotificationDecorator{
    
    public EmailNotification(NotificationSystem wrap){
        super(wrap);
    }
    
    public void send(){
        super.send();
        System.out.println("sent email decorator!");
    }
}

class AllowedNotifications{
    
    boolean sms;
    boolean push;
    boolean email;
    boolean[] container = new boolean[3];
    
    public AllowedNotifications(boolean sms, boolean push, boolean email){
        this.sms = sms;
        this.push = push;
        this.email = email;
        
        container[0] = sms;
        container[1] = push;
        container[2] = email;
    }
    
    public boolean[] getContainer(){
        return container;
    }
}

class User{
    
    String name;
    AllowedNotifications allowed;
    NotificationSystem noty = new NotificationDecorator(new Notification());
    
    public User(String name){
        this.name = name;
    }
    
    public void setAllowed(AllowedNotifications setValue){
        this.allowed = setValue;
    }
    
    public NotificationSystem launch(){
        if((allowed.getContainer())[0]){
            this.noty = new SmsNotification(this.noty);
        }
        if(allowed.getContainer()[1]){
            this.noty = new PushNotification(this.noty);
        }
        if(allowed.getContainer()[2]){
            this.noty = new EmailNotification(this.noty);
        }
        return this.noty;
    }
}