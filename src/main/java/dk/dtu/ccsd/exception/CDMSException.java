package dk.dtu.ccsd.exception;

/**
 * Created by xiuli on 3/4/15.
 */

public class CDMSException extends Exception
{

    private static final long serialVersionUID = 1997753363232807009L;

    public CDMSException()
    {
    }

    public CDMSException(String message)
    {
        super(message);
    }

    public CDMSException(Throwable cause)
    {
        super(cause);
    }

    public CDMSException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CDMSException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}