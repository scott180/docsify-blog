package com.sudytech;

import java.io.File;

public class DeleteFile {
	static int num = 0;
	static String suffix = ".java";
	public static void main(String[] args) {
		String dir = "E:\\workplaces\\idsplus2.0\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ids-imp";
		deleteJavaFile(dir);
		System.out.println("总数： "+num);
	}

	private static void deleteJavaFile(String dir) {
		// TODO Auto-generated method stub
		if(dir==null || "".equals(dir)) return;
			
		File file = new File(dir);
		if(file.exists()) {
			System.out.println(1111111);
			recursion(file);
		}
	}

	private static void recursion(File file) {
		File[] children = file.listFiles();
		if(children!=null && children.length>0) {
			for(File child : children) {
				if(child.isDirectory()) {
					recursion(child);
				}else {
					String name = child.getName();
					if(name.endsWith(suffix)) {
						System.out.println(name);
						num++;
						child.delete();
					}
				}
			}
		}
	}
}
