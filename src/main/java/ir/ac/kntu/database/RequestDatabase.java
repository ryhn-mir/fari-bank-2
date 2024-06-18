package ir.ac.kntu.database;

import ir.ac.kntu.request.Request;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RequestDatabase implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestDatabase that = (RequestDatabase) obj;
        return Objects.equals(requestList, that.requestList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestList);
    }

}

