
package model.data;

public class EmptyException extends Exception{
    
    String location;

    public EmptyException(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "EmptyException (no Cards available in )" + location + ")";
    }
}
