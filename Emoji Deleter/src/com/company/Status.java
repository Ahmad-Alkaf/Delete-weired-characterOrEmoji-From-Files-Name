package com.company;

public class Status {
    private int noAllFiles,noFilesWithEmojis,noFilesSuccessfullyRenamed,noFilesFailedRename;
    public Status(){
        noAllFiles=0;
        noFilesWithEmojis=0;
        noFilesFailedRename=0;
        noFilesSuccessfullyRenamed=0;
    }
    public void noAllFiles(){
        noAllFiles++;
    }
    public void noFilesWithEmojis(){
        noFilesWithEmojis++;
    }
    public void noFilesSuccessfullyRenamed(){
        noFilesSuccessfullyRenamed++;
    }
    public void noFilesFailedRename(){
        noFilesFailedRename++;
    }
    public void display(){

        System.out.println("Status:\n\tNum Of All Files:"+noAllFiles);
        System.out.println("\tNum Of Files With Emojis:"+noFilesWithEmojis);
        if(noFilesWithEmojis!=0) {//we don't need to show below info if there is no file with emoji
            System.out.println("\tNum Of Files Successfully Renamed:" + noFilesSuccessfullyRenamed);
            System.out.println("\tNum Of Files Failed To Rename:" + noFilesFailedRename);
            System.out.println("");
        }
    }

}
