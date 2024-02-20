for (int i = 0; i < array1.size(); i++) {
	for (int j = array2.size() - 1; j > i; j--) {
		if (array1.get(j).equalsIgnoreCase(array2.get(i))) {
			array1.remove(j);
		}
	}
}