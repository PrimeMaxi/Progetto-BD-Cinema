package exceptions;

public class ConnectionException extends Exception
{
    private String message;
    public ConnectionException(String message)
    {
        super(message);
    }

}
