
public class ExcelVO {
	
	String tag;
	String buffer;
	
	ExcelVO(){
		
	}
	
	public ExcelVO(String tag, String buffer) {
		super();
		this.tag = tag;
		this.buffer = buffer;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}
	
}
