package demo.interview.interviewdemo.model.reqres;

public class SuccessfulRegistration {
    public int id;
    public String token;

    public SuccessfulRegistration(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
