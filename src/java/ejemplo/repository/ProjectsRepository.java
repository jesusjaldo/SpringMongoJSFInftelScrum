/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.repository;

 
import ejemplo.collection.Projects;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
 
 
@Repository
public interface ProjectsRepository extends MongoRepository<Projects, String>{
    
       
    @Query("{'usuarios': '?0'}")
    public List<Projects> getlistaProyectos(String email);
    
    public List<Projects> findByUsuarios(String email);    


}