package com.polaris.lesscode.enums;

public enum StorageFieldType {

	STRING,
	
	TEXT,
	
	LONG_TEXT,
	
	DATE,
	
	LONG,
	
	INT,
	
	FLOAT,

	DOUBLE,
	
	ARRAY,

	CUSTOM
	;

	public static String getFieldSchemaType(StorageFieldType fieldType){
		switch (fieldType){
			case STRING:
				return "string";
			case TEXT:
				return "string";
			case LONG_TEXT:
				return "string";
			case DATE:
				return "object";
			case LONG:
				return "number";
			case INT:
				return "integer";
			case FLOAT:
				return "number";
			case ARRAY:
				return "array";
			case CUSTOM:
				return "custom";
			default:
				return "object";
		}
	}
	
	public static Object parseValue(StorageFieldType fieldType,String value) {
	    switch (fieldType){
            case LONG:
                return Long.valueOf(value);
            case INT:
                return Integer.valueOf(value);
            case FLOAT:
                return Float.valueOf(value);
            default:
                return value;
        }
	}

}
