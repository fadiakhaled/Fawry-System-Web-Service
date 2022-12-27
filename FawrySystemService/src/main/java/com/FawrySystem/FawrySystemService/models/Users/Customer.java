
package com.FawrySystem.FawrySystemService.models.Users;

public class Customer extends User {
    private double wallet;
    public Customer(){}
    public Customer(String username, String email, String password)
    {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setWallet(0.0);

    }


    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    public double getWallet() {
        return wallet;
    }
}
