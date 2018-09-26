package Bean;

public class Suggestion {
    public Suggestion(String user_id, String content,String time) {
        this.user_id = user_id;
        this.content = content;
        this.time =time;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    private String user_id;
    private String content;
    private String time;

}
