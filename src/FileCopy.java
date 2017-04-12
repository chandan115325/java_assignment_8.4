
// a program for file copy.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;



public class FileCopy {
//static method to copy one file to other using FileInputStream and FileOutputStream
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	            
	        }
	        
	    } finally {
	        is.close();
	        os.close();
	    }
	}
//static method to copy one file to other using FileChannel 
	
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	}
	
	//main method to call above method to copy file
	
	public static void main(String[] args) throws InterruptedException, IOException {
        File source = new File("E:/Chandan/source.docx");
        File dest = new File("E:/Chandan/destination.docx");

        //copy file conventional way using Stream
        long start = System.nanoTime();
        copyFileUsingStream(source, dest);
        System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));
        
        //copy files using java.nio FileChannel
        source = new File("E:/Chandan/source.docx");
        dest = new File("E:/Chandan/destination.docx");
        start = System.nanoTime();
        copyFileUsingChannel(source, dest);
        System.out.println("Time taken by Channel Copy = "+(System.nanoTime()-start));
        
       
	}
}
