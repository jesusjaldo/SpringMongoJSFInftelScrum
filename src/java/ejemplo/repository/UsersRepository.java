/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.repository;

 
import ejemplo.collection.Users;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
 
 
@Repository
public interface UsersRepository extends MongoRepository<Users, String>{
    @Query("{'email': ?0 }")
    public Users findUsersByEmail(String email);
    
    
    @Query("{'firstname':{$regex: '.*?0.*' }}")
    public List<Users> findUsersByName(String name);
}