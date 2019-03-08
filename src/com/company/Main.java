package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main
{

    public static void main(String[] args) throws IOException
    {

        File[] files = new File("Example/Path/Structure").listFiles(); //array of child files within a parent file
        moveFiles(files); //send array to method that will move files with a certain extension to a new path

    }

    //method to retrieve files with a certain extension and move them to a new path
    public static void moveFiles(File[] files) throws IOException
    {

        String newFilePath; //String that holds the path that a file should be moved to

        //loops through all files within the array that was passed as a parameter
        for (File file : files)
        {

            //if the file is a directory (a folder), then recursively call this method again to look through child files
            if (file.isDirectory())
            {

                //if this directory contains no child files, then skip to the next file
                if(file.listFiles().length == 0)
                    continue;

                moveFiles(file.listFiles()); //recursive call to look through child files

            }

            //if the file is not a directory, then move the file to a new path if it has a certain extension
            else
            {

                //if a file contains a certain extension then move it
                if(file.getName().endsWith(".example0") || file.getName().endsWith(".example1"))
                {

                    newFilePath = "/New/Path/Structure" + file.getAbsoluteFile().toString().substring(0, file.getAbsoluteFile().toString().lastIndexOf("/") + 1); //the path the file will be moved to (including the file name you are moving)

                    //if the path you are moving the file to does not already exist, then create the path
                    if(new File(newFilePath).exists() == false)
                        new File(newFilePath).mkdirs(); //creates the new desired path within the file system

                    Files.move(new File(file.getAbsolutePath()).toPath(), new File(newFilePath + file.getName()).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING); //moves file from old path to new path

                    System.out.println(newFilePath + file.getName()); //print the new path to the console
                }
            }
        }
    }
}