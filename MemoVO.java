
public class MemoVO {
	
	String tag;
	String bufferFirst;
	String bufferLast;
	String bufferRange;
	String name;
	
	MemoVO(){
		
	}

	public MemoVO(String tag, String bufferFirst, String bufferLast, String bufferRange, String name) {
		super();
		this.tag = tag;
		this.bufferFirst = bufferFirst;
		this.bufferLast = bufferLast;
		this.bufferRange = bufferRange;
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBufferFirst() {
		return bufferFirst;
	}

	public void setBufferFirst(String bufferFirst) {
		this.bufferFirst = bufferFirst;
	}

	public String getBufferLast() {
		return bufferLast;
	}

	public void setBufferLast(String bufferLast) {
		this.bufferLast = bufferLast;
	}

	public String getBufferRange() {
		return bufferRange;
	}

	public void setBufferRange(String bufferRange) {
		this.bufferRange = bufferRange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
