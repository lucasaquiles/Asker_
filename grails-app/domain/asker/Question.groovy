package asker

class Question {

	
	
	static belongsTo = [user:User]
	String question
	//Response response
	
	static hasMany = [response:Response]
	Date dateCreated

    static constraints = {
    	
    	user(nullable:false)
    	question(blank:false, size:1..255)
    	response(nullable:true)
    }
}
