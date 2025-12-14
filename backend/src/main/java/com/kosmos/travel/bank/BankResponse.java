package com.kosmos.travel.bank;

public class BankResponse {
    private boolean valid;
    private String message;
    private String token;

    public BankResponse() {}

    public BankResponse(boolean valid, String message, String token) {
        this.valid = valid;
        this.message = message;
        this.token = token;
    }

    // getters / setters
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
