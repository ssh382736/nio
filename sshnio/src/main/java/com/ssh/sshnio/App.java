package com.ssh.sshnio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	File file=new File("H:/2222/ssh.txt");
    	RandomAccessFile randomAccessFile=null;
    	FileChannel channel=null;
    	try {
    		//读取文件
    		randomAccessFile=new RandomAccessFile(file, "rw");
    		channel=randomAccessFile.getChannel();
    		//汉字占两个字节,标点也是，英文一个字节
			ByteBuffer buf=ByteBuffer.allocate(48);
			int bytesRead = channel.read(buf);
			System.out.println(new String(buf.array(),"GBK"));
			
			//写入文件
			String newData="this is new data"+System.currentTimeMillis()+"\n";
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			while(buf.hasRemaining()){
				channel.write(buf);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				channel.close();
				randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
