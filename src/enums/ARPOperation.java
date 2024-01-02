package enums;

public enum ARPOperation {
    Request(1),
    Reply(2),
    ;
    private int Identifier;
    ARPOperation(int Identifier) {
        this.Identifier = Identifier;
    }
}
