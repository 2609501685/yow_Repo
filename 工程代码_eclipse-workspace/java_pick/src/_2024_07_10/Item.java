package _2024_07_10;

public class Item {
	private String itemName;
	private int currentNumber;
	private int totalNumber;
	
	public Item(String itemName, int totalNumber) {
		this(itemName, 0, totalNumber);
	}
	
	public Item(String itemName, int currentNumber, int totalNumber) {
		this.itemName = itemName;
		this.currentNumber = currentNumber;
		this.totalNumber = totalNumber;
	}
	
	public void add() {
		if (currentNumber >= totalNumber) return;
		currentNumber++;
	}
	
	public void reduce() {
		if (currentNumber == 0) return;
		currentNumber--;
	}
	
	public void setValues(String itemName, int currentNumber, int totalNumber) {
		this.itemName = itemName;
		this.currentNumber = currentNumber;
		this.totalNumber = totalNumber;
	}
	
	@Override
	public String toString() {
		return itemName + " " + currentNumber + "/" + totalNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}
	
	
	
}
