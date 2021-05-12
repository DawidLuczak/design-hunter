package sample;

public interface HelpfulInterface {
	
	static void swapValues(Object value1, Object value2){
		assert value1 != null && value2 != null;
		if (value1.getClass().equals(value2.getClass())){
			Object var = value1;
			value1 = value2;
			value2 = var;
		}
	}
}
