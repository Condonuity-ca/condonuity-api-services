package tech.torbay.projectservice.exception.handler;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tech.torbay.projectservice.constants.Constants.StatusCode;
import tech.torbay.projectservice.statusmessage.ResponseMessage;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	   ResponseMessage responseMessage = new ResponseMessage(
       		StatusCode.NOT_FOUND.getValue(),
       		"Bad Request Error - handleHttpMessageNotReadable",
       		ex.getLocalizedMessage());
       logger.info("BAD_REQUEST");
       return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }
   
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                 HttpHeaders headers, HttpStatus status, WebRequest request) {
          String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
          
          ResponseMessage responseMessage = new ResponseMessage(
  	       		StatusCode.BAD_REQUEST.getValue(),
  	       		"Bad Request Error - handleMethodArgumentNotValid",
  	       	errorMessage);
          logger.info("BAD_REQUEST");
          return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }
   
   @Override
   protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
       
       ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.NOT_FOUND.getValue(),
	       		"Page Not Found - handleHttpRequestMethodNotSupported",
	       	ex.getLocalizedMessage());
       logger.info("NOT_FOUND");
       return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }

   @Override
   protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
   	return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
   }

   @Override
   protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
   	return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
   }

   @Override
   protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
   		HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
	   ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.BAD_REQUEST.getValue(),
	       		"Bad Request Error - handleMissingPathVariable",
	       	ex.getLocalizedMessage());
      logger.info("BAD_REQUEST");
      return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }

   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
	   ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.BAD_REQUEST.getValue(),
	       		"Bad Request Error - handleMissingServletRequestParameter",
	       	ex.getLocalizedMessage());
      logger.info("BAD_REQUEST");
      return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }

   @Override
   protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
	   ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.BAD_REQUEST.getValue(),
	       		"Bad Request Error - handleServletRequestBindingException",
	       	ex.getLocalizedMessage());
      logger.info("BAD_REQUEST");
      return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }

   @Override
   protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers,
   		HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
   	return super.handleConversionNotSupported(ex, headers, status, request);
   }

   @Override
   protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
   		WebRequest request) {
   	// TODO Auto-generated method stub
   	return super.handleTypeMismatch(ex, headers, status, request);
   }

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers,
   		HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
	   ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.BAD_REQUEST.getValue(),
	       		"Bad Request Error - handleHttpMessageNotWritable",
	       	ex.getLocalizedMessage());
     logger.info("BAD_REQUEST");
     return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }

   @Override
   protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
   	return super.handleMissingServletRequestPart(ex, headers, status, request);
   }

   @Override
   protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
   		WebRequest request) {
   	// TODO Auto-generated method stub
	   ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.BAD_REQUEST.getValue(),
	       		"Bad Request Error - handleBindException",
	       	ex.getLocalizedMessage());
	   logger.info("BAD_REQUEST");
	   return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }

   @Override
   protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
   		HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
   	return super.handleNoHandlerFoundException(ex, headers, status, request);
   }

   @Override
   protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
   		HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
   	// TODO Auto-generated method stub
   	return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
   }

   @Override
   protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
   		HttpStatus status, WebRequest request) {
   	// TODO Auto-generated method stub
//   	return super.handleExceptionInternal(ex, body, headers, status, request);
	   ResponseMessage responseMessage = new ResponseMessage(
	       		StatusCode.SERVER_ERROR.getValue(),
	       		"Internal Server Error",
	       	ex.getLocalizedMessage());
	   logger.info("SERVER_ERROR");
	   return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
   }
   
   
   
   
}