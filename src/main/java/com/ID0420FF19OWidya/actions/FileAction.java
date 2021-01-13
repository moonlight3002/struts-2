package com.ID0420FF19OWidya.actions;

import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
import com.ID0420FF19OWidya.util.convertFiletoString;
import com.ID0420FF19OWidya.dao.ProfileDao;
import com.ID0420FF19OWidya.util.uploadFile;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import com.opensymphony.xwork2.Preparable;
import com.ID0420FF19OWidya.models.User;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport implements ModelDriven<User>, Preparable
{
    private static final long serialVersionUID = 1L;
    private User user;
    private InputStream inputStream;
    private String base64;
    private String directory;
    private File picture;
    private String pictureFileName;
    private String pictureContentType;
    
    public FileAction() {
        this.directory = "";
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public String getBase64() {
        return this.base64;
    }
    
    public void setBase64(final String base64) {
        this.base64 = base64;
    }
    
    public File getPicture() {
        return this.picture;
    }
    
    public void setPicture(final File picture) {
        this.picture = picture;
    }
    
    public String getPictureFileName() {
        return this.pictureFileName;
    }
    
    public void setPictureFileName(final String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }
    
    public String getPictureContentType() {
        return this.pictureContentType;
    }
    
    public void setPictureContentType(final String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }
    
    public InputStream getInputStream() {
        return this.inputStream;
    }
    
    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public String getFile() {
        System.out.println("getFile");
        System.out.println(this.user);
        File f = null;
        if (this.user.getProfilePic() != null) {
            if (!this.user.getProfilePic().isEmpty()) {
                f = new File(String.valueOf(this.directory) + this.user.getUserID() + "/" + this.user.getProfilePic());
            }
        }
        else if (!this.user.getHeaderPic().isEmpty()) {
            f = new File(String.valueOf(this.directory) + this.user.getUserID() + "/" + this.user.getHeaderPic());
        }
        if (f.exists() && f != null) {
            try {
                System.out.println(this.user.getProfilePic());
                this.inputStream = new FileInputStream(f);
                if (this.inputStream != null) {
                    return "success";
                }
                return "none";
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "none";
    }
    
    public String uploadProfilePicture() {
        System.out.println(this.user);
        System.out.println("File name" + this.pictureFileName);
        final String path = String.valueOf(this.directory) + this.user.getUserID();
        final String filename = "profile_" + this.user.getUserID() + "_" + System.currentTimeMillis() + ".jpg";
        this.user.setProfilePic(filename);
        final boolean isUploadSuccess = uploadFile.upload(this.picture, filename, path);
        if (isUploadSuccess) {
            final ProfileDao dao = new ProfileDao();
            final int rowsAffected = dao.updateUserData(filename, "profile_picture", this.user);
            try {
                this.base64 = convertFiletoString.getBase64(this.user, this.directory);
                this.user.setProfilePicBase64(this.base64);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(rowsAffected);
            return "success";
        }
        return "error";
    }
    
    public String uploadHeaderPicture() {
        System.out.println(this.user);
        System.out.println("File name" + this.pictureFileName);
        final String path = String.valueOf(this.directory) + this.user.getUserID();
        final String filename = "header_" + this.user.getUserID() + "_" + System.currentTimeMillis() + ".jpg";
        this.user.setHeaderPic(filename);
        final boolean isUploadSuccess = uploadFile.upload(this.picture, filename, path);
        if (isUploadSuccess) {
            final ProfileDao dao = new ProfileDao();
            final int rowsAffected = dao.updateUserData(filename, "header_picture", this.user);
            try {
                this.base64 = convertFiletoString.getBase64(this.user, this.directory);
                this.user.setHeaderPicBase64(this.base64);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(rowsAffected);
            return "success";
        }
        return "error";
    }
    
    public User getModel() {
        System.out.println("-fileaction get Model");
        this.user = new User();
        final User sessionuser = (User) ActionContext.getContext().getSession().get("userData");
        if (!sessionuser.getRole().equals("admin")) {
            this.user.setUserID(sessionuser.getUserID());
        }
        return this.user;
    }
    
    public void prepare() throws Exception {
        System.out.println(this.getText("path.directory"));
        this.directory = this.getText("path.directory");
    }
}