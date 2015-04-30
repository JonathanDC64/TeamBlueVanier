package application;

public class Value 
{
	private String FieldName;
	private String Value;
	public Value(String fieldName, String value) {
		super();
		FieldName = fieldName;
		Value = value;
	}
	public String getFieldName() {
		return FieldName;
	}
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
}
