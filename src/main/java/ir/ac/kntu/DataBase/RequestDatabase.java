package ir.ac.kntu.DataBase;

import ir.ac.kntu.Request;

import java.util.LinkedList;
import java.util.List;

public class RequestDatabase {
    private List<Request> requestList;

    public RequestDatabase() {
        requestList = new LinkedList<>();
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public void addRequest(Request request) {
        requestList.add(request);
    }

    public void removeRequest(Request request) {
        requestList.remove(request);
    }

}

