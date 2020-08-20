import org.springframework.stereotype.Service;

@Service
public class MessageListService {

    private List<String> messages;

    //Constructor
    public MessageListService(){
        this.messages = new ArrayList<String>();
    }
    
    //addMessage
    public void addMessage(String message){
        messages.add(message);
    }

    //getMessages
    public List<String> getMessages(){
        return new ArrayList<>(this.messages);
    }
}
