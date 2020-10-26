/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import java.util.Date;

/**
 *
 * @author JAY
 */
public class UserDocumentsList {
    private String documentName;
    private long documentSize;
    private Date lastModifiedDate;
    
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public long getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(long documentSize) {
        this.documentSize = documentSize;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "UserDocumentsList{" + "documentName=" + documentName + ", documentSize=" + documentSize + ", lastModifiedDate=" + lastModifiedDate + '}';
    }

}
