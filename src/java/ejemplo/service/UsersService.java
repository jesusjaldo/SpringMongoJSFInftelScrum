/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.service;

import ejemplo.collection.Users;
import ejemplo.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;
    
   @Autowired
    private MongoOperations mongoOp;    

    public UsersRepository getRepository() {
        return repository;
    }
    
    

    public Users findByEmail(String email) {
        
//        Query a = new Query();
//        a.addCriteria(Criteria.where("email").is(email));
////      ApplicationContext ac = new GenericXmlApplicationContext("SpringConfig.xml");
////      MongoOperations mongoO = (MongoOperations) ac.getBean("mongoTemplate");
//        Users u = mongoOp.findOne(a, Users.class);
        return repository.findUsersByEmail(email);
    }
    
    public List<Users> findEmails(){
        return repository.findEmails();   
    }

    public List<Users> findUsersByName(String name){
        return repository.findUsersByName(name);
    }

    public void createUser(Users u) {
        repository.save(u);
    }
    
    public List<Users> findAllUsers() {
        return repository.findAll();
    }
    
    public void deleteUser (Users u) {
        repository.delete(u);
    }
    
    public void editUser (Users u) {
        repository.save(u);
    }
}
