
public class ResultVO {
	
	String tag;
	String area; //�տ� 3�ڸ�
	String word; //area���� m���� + ���۰� ���� 5�ڸ� ex) E000900
	String full; //AREA + ���۰� ex)EM000900
	
	ResultVO() {
		
	}

	public ResultVO(String tag, String area, String word, String full) {
		super();
		this.tag = tag;
		this.area = area;
		this.word = word;
		this.full = full;
	}



	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getFull() {
		return full;
	}

	public void setFull(String full) {
		this.full = full;
	}
	
}
