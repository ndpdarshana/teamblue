package uow.itpm.teamblue.model;

import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private static String algorithm = "MD5";
    @Transient
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAuthenticated(String password){
        String hashedPw = getHashed(password);
        if(this.password.equals(password)){
            return true;
        }
        return false;
    }

    public void setPassword(String password) {
        this.password = getHashed(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String getHashed(String text){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] messageBytes = messageDigest.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageBytes);
            String hashtext = number.toString(16);
            while(hashtext.length() < 32){
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return "{" +
                    "'id':"+this.id+"," +
                    "'username':'"+this.username+"'," +
                    "'email':'"+this.email+"'," +
                    "'firstName':'"+this.firstName+"'," +
                    "'lastName':'"+this.lastName+"'," +
                "}";
    }
}
