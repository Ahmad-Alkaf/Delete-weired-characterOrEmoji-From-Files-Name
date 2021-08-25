package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static Stack<Status> statuses = new Stack<>();

    public static void main(String[] args) {
        // Here is the Folder Path to delete emoji to and its depth files
        File folder = new File("C:\\...");
        displayPath(folder);
    }

    public static void displayPath(File folder) {
        System.out.println("Folder:" + folder.getName() + "{");

        File[] allFiles = folder.listFiles();
        statuses.add(new Status());
        if (allFiles != null)
            for (File f : allFiles) {
                statuses.peek().noAllFiles();
                fixName(f, isAllAscii(f.getName()));
                if (!f.isFile()) {//if it's folder
                    displayPath(f);
                }
            }
        System.out.println("}");
        statuses.pop().display();
    }

    public static void fixName(File file, ArrayList<StringBuilder> emoji) {
        if (emoji.size() == 0)//if file name doesn't contain an emoji
            return;

        //VVV fixing code VVV
        statuses.peek().noFilesWithEmojis();
        String path = file.getPath();
        System.out.println("Name: " + file.getName() + "\nPath:" + path);
        for (StringBuilder s : emoji)
            path = path.replace(s.toString(), "+");//will replace emojis with '+' sign
        System.out.println("Emojis:" + emoji);
        File rename = new File(path);
        if (rename.exists()) {
            int i = 0;
            while (rename.exists()) {//if there is a file with the same name of the new name then the new name will add number 1 or 2 ...etc
                StringBuilder builder = new StringBuilder(path);
                builder = builder.insert(path.indexOf("+"),i);
                rename = new File(builder.toString());
                System.out.println("Trying to rename:"+rename.getName());
                i++;
            }
        }
        if (file.renameTo(rename)) {
            System.out.println("Successfully Rename: " + path);
            statuses.peek().noFilesSuccessfullyRenamed();
        } else {
            System.out.println("Error in Rename, path:" + path);
            statuses.peek().noFilesFailedRename();
        }
        System.out.println("");

    }

    public static ArrayList<StringBuilder> isAllAscii(String str) {
        ArrayList<StringBuilder> arrBuilder = new ArrayList<>();
        boolean isPreviousEmoji = false;
        for (char c : str.toCharArray()) {
            if (c > 6687) {//after that char became 'ᨡ' i.e, can't appear
                if (!isPreviousEmoji) {//emoji really is not char it's two char variables added to gather so here we will combine them to the same stringBuilder object
                    arrBuilder.add(new StringBuilder());
                    arrBuilder.get(arrBuilder.size() - 1).append(c);
                    isPreviousEmoji = true;
                } else
                    arrBuilder.get(arrBuilder.size() - 1).append(c);

            } else isPreviousEmoji = false;
        }
        return arrBuilder;
    }

}
