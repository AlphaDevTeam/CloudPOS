
package com.AlphaDevs.cloud.web.Entities;

import javax.persistence.MappedSuperclass;


/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */
@MappedSuperclass
public abstract class DocumentObject {
    
    private DocumentObject relatedDocument;

    public DocumentObject getRelatedDocument() {
        return relatedDocument;
    }

    public void setRelatedDocument(DocumentObject relatedDocument) {
        this.relatedDocument = relatedDocument;
    }
    
    
}
