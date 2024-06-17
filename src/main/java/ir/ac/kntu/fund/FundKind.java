package ir.ac.kntu.fund;

public enum FundKind {
    SAVING("saving"),
    REMAINING("remaining"),
    PROFIT("profit");
    private final String kind;

    private FundKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }
}
