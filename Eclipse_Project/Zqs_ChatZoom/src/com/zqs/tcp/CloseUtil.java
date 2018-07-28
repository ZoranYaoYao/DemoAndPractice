package com.zqs.tcp;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {

	public static void closeAll(Closeable... io) {
		for (Closeable item : io) {
			try {
				item.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
