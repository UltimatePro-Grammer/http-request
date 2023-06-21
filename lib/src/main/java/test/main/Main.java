package test.main;

import com.github.kevinsawicki.http.HttpRequest;

public class Main {
    public static void main(String[] args) {
        int responseLength = HttpRequest.get("http://google.com").body().length();
        System.out.println("Got response of len "+responseLength);
    }
}
