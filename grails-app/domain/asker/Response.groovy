package asker

class Response {
	
	String response
	
	Question question
	User responseUser	
	
	Integer pontos
	
	static hasMany = [User]
	static belongsTo = [Question]
	
    static constraints = {
    	response(blank:false, size:1..255)
    	question(nullable:false)
    	responseUser(nullable:false)
    	pontos(nullable:true)
    }
    
    
    String toString(){
    	
    	return "responsta do ${responseUser} + para a pergunta ${question} - ${response}"
    }	
    
}
