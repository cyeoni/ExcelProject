public class Road {
	ILoad il;
	Road(){
		il = new LoadImpl();
	}
	public void doRoadExcel() {
		il.doRoadExcel();
	}
	public void doRoadMemo() {
		il.doRoadMemo();
	}
	public void doReadExcel() {
		il.doReadExcel();
	}
	public void doReadMemo() {
		il.doReadMemo();
	}
	public void doReadResult() {
		il.doReadResult();
	}
}