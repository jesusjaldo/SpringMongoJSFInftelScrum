/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.service;

import ejemplo.collection.Projects;
import ejemplo.collection.Users;
import ejemplo.repository.ProjectsRepository;
import ejemplo.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ProjectsService  {

    @Autowired
    private ProjectsRepository repository;
    
   @Autowired
    private MongoOperations mongoOp;    

    public ProjectsRepository getRepository() {
        return repository;
    }


    public void createProjects(Projects p) {
        repository.save(p);
    }
    
    public List<Projects> findAllProjects() {
        return repository.findAll();
    }
    
    public void deleteProjects (Projects p) {
        repository.delete(p);
    }
    
    public void editProjects (Projects p) {
        repository.save(p);
    }
}