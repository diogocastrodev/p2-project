import abstracts.AbsDevice;

public class ExceptionEx extends Exception{

    private AbsDevice device;

    public ExceptionEx(String message, AbsDevice device){
        super(message);
        this.device = device;
    }

    @Override
    public String toString(){
        return super.toString() + " " + device.toString();
    }
}
