package com.flyrish.hades.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class ObjectsTranscoder {
	private static Logger logger = Logger.getLogger(ObjectsTranscoder.class);
	public static <T> byte[] serialize(List<T> value) {  
		if(value==null){
			logger.error("serialize(List)Can't serialize null obj");
			throw new NullPointerException("Can't serialize null obj");
		}
		byte[]rv=null;
		ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;
        try{
        	bos=new ByteArrayOutputStream();
        	os=new ObjectOutputStream(bos);
        	for(Object obj:value){
        		os.writeObject(obj);
        	}
        	os.writeObject(null);
        	os.close();
        	bos.close();
        	rv=bos.toByteArray();
        	return rv;
        }catch(IOException ex){
        	logger.error("serialize(List<T>)",ex);
        	throw new IllegalArgumentException("Non-serializable object",ex);  
        }finally{
        	close(bos);
        	close(os);
        }
	}
	
	public static <T> byte[] serialize(T obj) {  
		if(obj==null){
			logger.error("serialize(List)Can't serialize null obj");
			throw new NullPointerException("Can't serialize null obj");
		}
		List<T> objList=new ArrayList<T>();
		objList.add(obj);
		return serialize(objList);
	}
	
	
	 public static <T> List<T> deserialize(byte[] in) {  
         List<T> list = new ArrayList<T>();  
         ByteArrayInputStream bis = null;  
         ObjectInputStream is = null;  
         try {  
             if(in != null) {  
                 bis=new ByteArrayInputStream(in);  
                 is=new ObjectInputStream(bis);  
                 while (true) {  
                     T obj =(T)is.readObject();  
                     if(obj == null){  
                         break;  
                     }else{  
                        list.add(obj);  
                     }  
                 }  
                 is.close();  
                 bis.close();  
             }  
         } catch (IOException e) {  
             logger.error("Caught IOException decoding %d bytes of data",e);  
         } catch (ClassNotFoundException e) {  
             logger.error("Caught CNFE decoding %d bytes of data",e);  
         } finally {  
             close(is);  
             close(bis);  
         }  
         return list;  
     }
	 public static <T> LinkedList<T> deserializeLink(byte[] in) {  
		 LinkedList<T> list = new LinkedList<T>();  
         ByteArrayInputStream bis = null;  
         ObjectInputStream is = null;  
         try {  
             if(in != null) {  
                 bis=new ByteArrayInputStream(in);  
                 is=new ObjectInputStream(bis);  
                 while (true) {  
                     T obj =(T)is.readObject();  
                     if(obj == null){  
                         break;  
                     }else{  
                        list.add(obj);  
                     }  
                 }  
                 is.close();  
                 bis.close();  
             }  
         } catch (IOException e) {  
             logger.error("Caught IOException decoding %d bytes of data",e);  
         } catch (ClassNotFoundException e) {  
             logger.error("Caught CNFE decoding %d bytes of data",e);  
         } finally {  
             close(is);  
             close(bis);  
         }  
         return list;  
     } 
	 public static <T> T deserializeObj(byte[] in) {  
		 List<T> list=deserialize(in);
		 if(list!=null&&list.size()>0)
			 return list.get(0);
		 else return null;
     }
	public static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                logger.error("Unable to close %s",e);  
            }  
        }  
    }  
}
