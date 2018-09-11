package tool;
import java.io.File;

public class FileTool {	
	public static String root;
	
	//获取完整路径的文件名�?  的路径（排除掉文件名�?,�?后是“\�?
	public static String getDictionary(String fullFileName){
		int index=fullFileName.lastIndexOf("\\");
		return fullFileName.substring(0,index);
	}		
	//获取完整路径中的文件�?
	public static String getFileName(String fullFileName){
		int index=fullFileName.lastIndexOf("\\");
		return fullFileName.substring(index+1);
	}
	//根据文件名获得文件（不带路径）的扩展�?
	public static String getExtendedFileName(String fileName){
		int index=fileName.lastIndexOf(".");
		return fileName.substring(index+1);
	}
	//生成随机文件名，文件名为当前时间的整数�?�，扩展名不�?
	public static String getRandomFileNameByCurrentTime(String fileName){
		String randomFileName=System.currentTimeMillis() +"."+ getExtendedFileName(fileName);
		return randomFileName;
	}
	//根据完整路径生成随机文件名，文件名为当前时间的整数�?�，扩展名不变，路径不变
	public static String getRandomFileNameFromFullFileNameByCurrentTime(String fullFileName){
		String randomFileName=System.currentTimeMillis() +"."+ getExtendedFileName(getFileName(fullFileName));
		return getDictionary(fullFileName)+randomFileName;
	}	
	
	//删除文件
	public static boolean deleteFile(File file)   
	{   
	    boolean result = false;   
	    int tryCount = 0;   
	    while(!result && tryCount++ <10)   
	    {   		      
		    result = file.delete(); 
		    System.gc(); //JVM 进行垃圾回收
	    }   
	    return result;   
	} 	

	//删除文件�?
	private static boolean deleteDictionary(File file) {
		boolean success = true;
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文�?
				if (file.delete() == false) // delete()方法 你应该知�? 是删除的意�??;
					return false;
			} else if (file.isDirectory()) { // 否则如果它是�?个目�?
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					if (deleteDictionary(files[i]) == false) // 把每个文件夹  用这个方法进行�?�归调用																	
						return false;
				}
				if (file.delete() == false)// 删除文件�?
					return false;
			}
		}
		return success;
	}
	
	
}
