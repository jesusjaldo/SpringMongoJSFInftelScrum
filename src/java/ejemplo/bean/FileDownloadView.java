/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejb.FicherosScrumFacade;
import ejb.TareaScrumFacade;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.FicherosScrum;
import model.TareaScrum;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author inftel20
 */
@ManagedBean
public class FileDownloadView {
    @EJB
    private FicherosScrumFacade ficherosScrumFacade;
    

    private StreamedContent file = new DefaultStreamedContent();
    
    /**
     * Creates a new instance of FileDownloadView
     */
    
     
    public FileDownloadView() {  

    }

    public StreamedContent getFile() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String get = currentInstance.getExternalContext().getRequestParameterMap().get("idFichero");
        FicherosScrum find = ficherosScrumFacade.find(Long.valueOf(get));
        
        InputStream input = new ByteArrayInputStream(find.getFichero());
        
        
        
        return new DefaultStreamedContent(input, "image/jpg", find.getExt());
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public StreamedContent fileMon(TareaScrum tarea){
        
        String ext = FilenameUtils.getExtension(tarea.getIdFichero().getExt());
        InputStream input = new ByteArrayInputStream(tarea.getIdFichero().getFichero());

        return new DefaultStreamedContent(input, "image/jpg", tarea.getIdFichero().getExt());
    }


}
