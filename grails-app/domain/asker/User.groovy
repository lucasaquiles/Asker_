package asker

class User {
	String fullName
	String username
	String password
	Boolean status = true
	Double longi
	Double lat
	
	static hasMany = [questions:Question]
    static constraints = {
    	
    	longi(nullable:true, blank:true)
    	lat(nullable:true, blank:true)
    	
    }
    
    
    public String toString(){
    	
    	return fullName	
    }
}
