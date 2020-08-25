package model;

public class OrderModel {

    String message;
    int statusCode;
    String fullfillMent;
    String contentType;

    public OrderModel(String message, int statusCode, String fullfillMent, String contentType) {
        this.message = message;
        this.statusCode = statusCode;
        this.fullfillMent = fullfillMent;
        this.contentType = contentType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getFullfillMent() {
        return fullfillMent;
    }

    public void setFullfillMent(String fullfillMent) {
        this.fullfillMent = fullfillMent;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


}
