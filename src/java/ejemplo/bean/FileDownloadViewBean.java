/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Task;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

/**
 *
 * @author inftel20
 */
@Component
public class FileDownloadViewBean {

    

    private StreamedContent file = new DefaultStreamedContent();
    
    /**
     * Creates a new instance of FileDownloadView
     */
    
     
    public FileDownloadViewBean() {  

    }


    public StreamedContent fileMon(Task tarea){
        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(tarea.getFichero());

        return new DefaultStreamedContent(stream, "image/jpg", tarea.getNombre_fichero());
    }


}
