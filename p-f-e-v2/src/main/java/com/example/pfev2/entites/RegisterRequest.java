package com.example.pfev2.entites;


public class RegisterRequest {
    private String nom;
    private String email;
    private String password;
    public RegisterRequest(String nom, String email, String password) {}
    public String getNom() {
        return nom;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
public void setEmail(String email) {
        this.email = email;
}
public void setPassword(String password) {
        this.password = password;
}

}