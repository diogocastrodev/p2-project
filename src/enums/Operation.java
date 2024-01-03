package enums;

public enum Operation {
    Request(1),
    Reply(2),
    ;
    private int Identifier;
    Operation(int Identifier) {
        this.Identifier = Identifier;
    }
}
