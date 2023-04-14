package chap6;

public class Message {

    String text;

    private Message(){

    }

    private Message(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public static Message newMessage(String text){

        Message newMessage = new Message(text);

        return newMessage;
    }

}
