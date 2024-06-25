package practice.Test;

import java.util.Date;

import org.apache.poi.ss.formula.functions.Replace;

public class CaptureTimeStamp {
public static void main(String[] args) {
	String time=new Date().toString().replace(" "," _").replace(":","_");
	System.out.println(time);
}
}
