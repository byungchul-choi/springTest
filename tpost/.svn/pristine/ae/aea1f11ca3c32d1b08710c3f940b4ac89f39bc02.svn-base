package tpost.SimpleTest.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import tpost.SimpleTest.common.DataField;


public class SortUtils {
	public static void fieldSort (Field[] fields) {
		Arrays.sort(fields, new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				DataField df1 = o1.getAnnotation(DataField.class);
				DataField df2 = o2.getAnnotation(DataField.class);

				if (df1 != null && df2 != null) {
					return df1.order() - df1.order();
				} else if (df1 != null && df2 == null) {
					return -1;
				} else if (df1 == null && df2 != null) {
					return 1;
				} 
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
}
