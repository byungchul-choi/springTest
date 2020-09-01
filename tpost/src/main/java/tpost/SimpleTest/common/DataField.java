package tpost.SimpleTest.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataField {
	
	int order();  // 평전문으로 전문 데이터 생성 시 순서 구분을 위한 값
	DataType type() default DataType.CHAR;  // 데이터 타입 필드 ()
	int length() default 0;  // 평전문으로 전문 데이터 생성 시 필드의 길이값을 표기 (CHAR 타입 사용, 상수형(INT,SHORT)는 고정되어 있는 값으로 처리)
	String refLength() default "";  // 길이값 필드에 따라 길이값이 달라지는 경우 기준이 되는 필드명을 기입
	
}
