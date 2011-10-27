package asker
import grails.converters.*
import grails.converters.deep.*
import grails.converters.deep.JSON


class QuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [questionInstanceList: Question.list(params), questionInstanceTotal: Question.count()]
    }

   
    def create = {
        def questionInstance = new Question()
        questionInstance.properties = params
        return [questionInstance: questionInstance]
    }

    def save = {
        def questionInstance = new Question(params)
        if (questionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])}"
            redirect(action: "show", id: questionInstance.id)
        }
        else {
            render(view: "create", model: [questionInstance: questionInstance])
        }
    }

    def show = {
        def questionInstance = Question.get(params.id)
        if (!questionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])}"
            redirect(action: "list")
        }
        else {
            [questionInstance: questionInstance]
        }
    }
    
    def sendQuestion = {
    

    	def question = new Question(user:User.get(params.uid), question:params.question)
    	
    	if(question.save(flush:true)){
    	
    		render "1"
    	}else{
    	
    		render "0"
    	}
    }
    
    def getQuestion = {
    	
    	def q = Question.get(params.id)
    	
    	if(q){
    		
    		withFormat{
	    	
			json {
				render(contentType:"text/json"){
					
					question(question:q.question, user:q.user, dateCreated:q.dateCreated, response:q.response){
					 	
					} 	
				}
			}
		}
    	}
    }
    
    def searchFor = {
    
    	def searchedQuestions = Question.findAllByQuestionLike("%"+params.title+"%")
    	
    	if(searchedQuestions){

    		withFormat{

    			json{ render searchedQuestions as JSON}
    		}
    	}else{
    		render "sem resultados para ${params.title}"
    	}
    } 
    
    def test = {
    	
    	def u = User.get(params.uid) 
    		if(u){
    			
    			//def questions = Question.findByUser(user)
    				
	    			def questions =  Question.findAll("from Question where user_id = ?", u.id)
	    			
	    			if(questions){ 
		    			withFormat{
		    				json{ 
		    					render(contentType:"text/json"){
				    				
							    		user(user:u){
							    		
							    			for(q in u.questions){
							    				
							    				question(question:q)
							    			}
							    		}
				    			}
				    		}
		    			}
	    			}
	    				
    		}
    }
    
    def loadQuestionsForUser = {
    
    		def u = User.get(params.uid) 
    		if(u){
    			
    			//def questions = Question.findByUser(user)
    			
	    			def questions =  Question.findAll("from Question where user_id = ?", u.id)
	    			
	    			/* 
	    			if(questions){
	    			
	    				render(contentType:"text/json"){
		    				withFormat{
				    			
					    		json { render questions as JSON }
				    		}
			    		}
	    			}
	    			*/	
	    			
	    			if(questions){ 
		    			withFormat{
		    			
		    			
		    				json{ render questions as JSON}
//		    				json{ 
//		    					render(contentType:"text/json"){
//				    				
//							    		/* user(user:u){ 
//							    			for(q in u.questions){
//							    				
//							    				question(question:q){
//							    					
//							    					for(r in q.response){
//							    					
//							    						response(response:r)
//							    					}
//							    				}
//							    			}
//							    		}
//							    		*/
//							    		
//							    			
////								    		questions(questions:q){
////								    			
////								    			
////								    		}

//								questions
//				    			}
//				    		}
		    			}
	    			}
    		}
    }
    

    
    def loadQuestion = {
    
    	
    	def question = Question.get(params.id)
    	
    	
    	if(question){
    		//render "Oopa, ${question.user.fullName}"
    		withFormat{
    			
    			all question:question, user:question?.user.fullName
	    		json { render question as JSON }
    		}
    	}else{
    	
    	
    		
    		render "Oopa"
    	}
    }
    
	 def loadQuestions = {

		def questions = Question.list()

		if(questions){
			
		render(contentType:"text/json"){
		
			withFormat{
			  
			  // all questions:questions, user:questions?.user.fullName	
			   json { render questions as JSON }
			}	
		}	
	}		
		
	}
    def edit = {
        def questionInstance = Question.get(params.id)
        if (!questionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [questionInstance: questionInstance]
        }
    }

    def update = {
        def questionInstance = Question.get(params.id)
        if (questionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (questionInstance.version > version) {
                    
                    questionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'question.label', default: 'Question')] as Object[], "Another user has updated this Question while you were editing")
                    render(view: "edit", model: [questionInstance: questionInstance])
                    return
                }
            }
            questionInstance.properties = params
            if (!questionInstance.hasErrors() && questionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])}"
                redirect(action: "show", id: questionInstance.id)
            }
            else {
                render(view: "edit", model: [questionInstance: questionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def questionInstance = Question.get(params.id)
        if (questionInstance) {
            try {
                questionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'question.label', default: 'Question'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])}"
            redirect(action: "list")
        }
    }
}
