/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamWork;
/**
 *
 * @author Haiping Zhu
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class find {
    public String read(String path) {
        File file02 = new File(path);
        FileInputStream is = null;
        StringBuilder stringBuilder = null;
        try {
            if (file02.length() != 0) {

                is = new FileInputStream(file02);
                InputStreamReader streamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    // stringBuilder.append(line);
                    stringBuilder.append(line+" ");
                }
                reader.close();
                is.close();
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);

    }

     public static void main(String[]args) throws FileNotFoundException {
        Scanner s= new Scanner(new BufferedReader(new  FileReader("shortwords.txt")));
        HashMap <String,Integer> Dict1= new HashMap<>();
        HashMap <String,Integer> Dict2= new HashMap<>();
         
        while(s.hasNext()){
            Dict1.put(s.next(),1);
        }
         
         String path = "Frankenstein.txt";
         find a= new find();
         String b = a.read(path);
         String [] words = b.split("[ \\.\\?\\,\\:\\?\\!\\*\\#\\;\\-\\s]+");
         int len=words.length;
        
         Integer count;
        
        for(int i=0;i<len;i++){
            if (len==0)
                break;
            if( Dict1.containsKey(words[i])  ){
              
     /*caes1*/  if(i==0 & i+1<len){       //the very first word if the string& not the last word
                    count=Dict2.get(words[i+1]);
                    if (count==null)
                        Dict2.put(words[i+1], 1);
                    else
                        Dict2.put(words[i+1], count+1);
                }
                
     /*caes2*/  if(i==0 & i+1>=len)      //only one word in the entire string
                    continue;
                
     /*caes3*/  if(i>0 & i+1<len){       //in the midddle, get the former and the later
                    
                    count=Dict2.get(words[i+1]);//i+1
                    if (count==null)
                        Dict2.put(words[i+1], 1);
                    else
                        Dict2.put(words[i+1], count+1);
                    
                    count=Dict2.get(words[i-1]);//i-1
                    if (count==null)
                        Dict2.put(words[i-1], 1);
                    else
                        Dict2.put(words[i-1], count+1);
                }
                
     /*caes4*/  if(i>0 & i+1>=len) {  //the very last word in the string 
                    count=Dict2.get(words[i-1]);//i-1
                    if (count==null)
                        Dict2.put(words[i-1], 1);
                    else
                        Dict2.put(words[i-1], count+1);
                }
                
            }    
        }
       
        
        Set<String> keySet = Dict2.keySet();
        for (String key: keySet) {
            if(Dict2.get(key)>=50){
            System.out.println(key+"=>"+ Dict2.get(key)); }           
        }
         

    }
}
