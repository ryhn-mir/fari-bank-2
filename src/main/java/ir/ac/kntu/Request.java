package ir.ac.kntu;

public class Request {
    private String request;
    private RequestOption requestOption;
    private String answer;
    private String cellNumber;
    private RequestState requestState;

    public Request(String request, RequestOption requestOption, String cellNumber) {
        this.request = request;
        this.requestOption = requestOption;
        this.cellNumber = cellNumber;
        this.answer = "";
        this.requestState = RequestState.SUBMIT;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public RequestOption getRequestOption() {
        return requestOption;
    }

    public void setRequestOption(RequestOption requestOption) {
        this.requestOption = requestOption;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    @Override
    public String toString() {
        if (answer.isEmpty()) {
            return "Request{" +
                    "request='" + request + '\'' +
                    ", requestOption=" + requestOption +
                    '}';
        }
        return "Request{" +
                "request='" + request + '\'' +
                ", requestOption=" + requestOption +
                ", answer='" + answer + '\'' +
                '}';
    }
}
