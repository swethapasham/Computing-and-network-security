package p2p.model;

public enum MessageStatus {

	CREATED("CREATED"),
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    FAILED("FAILED");
	
     
    private String status;
     
    private MessageStatus(final String status){
        this.status = status;
    }
     
    public String getStatus(){
        return this.status;
    }
 
    @Override
    public String toString(){
        return this.status;
    }
 
 
    public String getName(){
        return this.name();
    }
}
