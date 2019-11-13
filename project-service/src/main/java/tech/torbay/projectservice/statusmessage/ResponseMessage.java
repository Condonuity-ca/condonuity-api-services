package tech.torbay.projectservice.statusmessage;

import java.util.List;

public class ResponseMessage
{
	
	// status Code
	// 0 - success with message
	// 1 - failed with message
	// 2 - error with exception message
    public ResponseMessage(int statusCode, String statusMessage, String responseMessage) {
        super();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.responseMessage = responseMessage;
    }
 
    
    // internal error code
    private int statusCode;
   	// nature of error
    private String statusMessage;
  //General error message about nature of error
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
