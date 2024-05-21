package ir.ac.kntu.DataBase;

import ir.ac.kntu.Request;

import java.util.LinkedList;
import java.util.List;

public class AnswerRequestDatabase {
    private List<Request> answer;

    public AnswerRequestDatabase() {
        answer = new LinkedList<>();
    }
    public void add(Request request) {
        answer.add(request);
    }
    public void remove(Request request) {
        answer.remove(request);
    }
}
