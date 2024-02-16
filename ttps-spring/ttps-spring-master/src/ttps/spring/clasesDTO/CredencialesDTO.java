package ttps.spring.clasesDTO;

import java.sql.Date;

import ttps.spring.model.*;

public class CredencialesDTO {
	 private String token;
	    private int exp;
	    private String username;

	    public CredencialesDTO() {
	    }

	    public CredencialesDTO(String token, int exp, String username) {
	        this.token = token;
	        this.exp = exp;
	        this.username = username;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public int getExp() {
	        return exp;
	    }

	    public void setExp(int exp) {
	        this.exp = exp;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }
}


