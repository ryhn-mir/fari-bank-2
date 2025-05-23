package ir.ac.kntu.database;

import ir.ac.kntu.Constant;
import ir.ac.kntu.request.Request;

import java.util.List;

public class AnswerRequestDatabase {
    private List<Request> answer;

    public AnswerRequestDatabase(List<Request> answer) {
        this.answer = answer;
    }

    public void add(Request request) {
        answer.add(request);
    }

    public void remove(Request request) {
        answer.remove(request);
    }

    public List<Request> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Request> answer) {
        this.answer = answer;
    }

    public void printAnswerRequest() {
        int count = 1;
        for (Request request : answer) {
            System.out.println(Constant.PURPLE + count + "." + "requestOption" + request.getRequestOption() + " cellNumber" + request.getCellNumber());
            count++;
        }
    }
}
