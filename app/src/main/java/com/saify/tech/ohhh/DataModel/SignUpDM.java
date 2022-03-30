package com.saify.tech.ohhh.DataModel;

public class SignUpDM {
    private SignUpResult result;

    private String message;

    private String status;

    private Output output;

    public Output getOutput ()
    {
        return output;
    }

    public void setOutput (Output output)
    {
        this.output = output;
    }


    public SignUpResult getResult ()
    {
        return result;
    }

    public void setResult (SignUpResult result)
    {
        this.result = result;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
