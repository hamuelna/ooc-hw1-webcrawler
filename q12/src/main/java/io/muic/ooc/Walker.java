package io.muic.ooc;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Walker extends DirectoryWalker {
    private int nFiles = 0;
    private int nDirectory = 0;
    private List results = new ArrayList();
    Map<String, Integer> uniqueExtCounter;
    public Walker(File startDirectory){
        super();
        try {
            walk(startDirectory, results);
        } catch (IOException e) {
            e.printStackTrace();
        }
        uniqueExtCounter = new HashMap<String, Integer>();
        for(Object e: results){
            File elt = (File) e;
            String fileExt = FilenameUtils.getExtension(elt.getName());
            if (fileExt.length() > 0){
                uniqueExtCounter.put(fileExt, uniqueExtCounter.getOrDefault(fileExt, 0) + 1);
            }
        }
    }
    protected void handleFile(File file, int depth, Collection results) {
        nFiles += 1;
        results.add(file);
    }
    protected void handleDirectoryStart(File file, int depth, Collection results) {
        nDirectory +=1;
    }


    public int getTotalNumFiles() {return nFiles;}
    public int getTotalNumDirectory() {return nDirectory;}
    public int getTotalUniqueExt(){return uniqueExtCounter.keySet().size();}
    public Set<String> getUniqueExt() {return uniqueExtCounter.keySet();}
    public int getTotalNumFileNumExt(String ext){
        return uniqueExtCounter.getOrDefault(ext, 0);
    }



}
