public class AVLTree {
	public AVLNode kok = null;
	public int size = 0;
	public AVLTree() {
		kok = null;
		size = 0;
	}
	public void Ekle(String key) {
		kok = Ekle(key.toLowerCase(), key, kok);
	}
	public AVLNode Ekle(String key, String veri, AVLNode node) {
		if (node == null) {
			node = new AVLNode(key, veri);
			size++;
			return node;
		} else {
			if (key.compareTo(node.getKey()) < 0) {
				node.setSolDugum(Ekle(key, veri, node.getSolDugum()));
				if (height(node.getSolDugum()) - height(node.getSagDugum()) == 2) {
					if (key.compareTo((node.getSolDugum()).getKey()) < 0)
						node = SolDon(node);
					else
						node = SolSol(node);
				}
			} else {
				if (key.compareTo(node.getKey()) > 0) {
					node.setSagDugum(Ekle(key, veri, node.getSagDugum()));

					if (height(node.getSagDugum()) - height(node.getSolDugum()) == 2) {
						if (key.compareTo((node.getSagDugum()).getKey()) > 0)
							node = SagDon(node);
						else
							node = SagSag(node);
					}
				}
			}
		}
		node.setHeight(max(height(node.getSolDugum()), height(node.getSagDugum())) + 1);
		return node;
	}
	public AVLNode kok() {
		return kok;
	}
	public int size() {
		return size;
	}
	public int height() {
		return height(kok);
	}
	public int height(AVLNode node) {
		if (node == null)
			return -1;
		return node.getHeight();
	}
	public boolean isEmpty() {
		return (size == 0);
	}
	public AVLNode SolDon(AVLNode node) {
		AVLNode newNode = node.getSolDugum();
		node.setSolDugum(newNode.getSagDugum());
		newNode.setSagDugum(node);
		node.setHeight(max(height(node.getSolDugum()), height(node.getSagDugum())) + 1);
		newNode.setHeight(max(height(newNode.getSolDugum()), node.getHeight()) + 1);
		return newNode;
	}public AVLNode SagDon(AVLNode node) {
		AVLNode newNode = node.getSagDugum();
		node.setSagDugum(newNode.getSolDugum());
		newNode.setSolDugum(node);
		node.setHeight(max(height(node.getSolDugum()), height(node.getSagDugum())) + 1);
		newNode.setHeight(max(height(newNode.getSagDugum()), node.getHeight()) + 1);
		return newNode;
	}public AVLNode SolSol(AVLNode node) {
		node.setSolDugum(SagDon(node.getSolDugum()));
		return SolDon(node);
	}public AVLNode SagSag(AVLNode node) {
		node.setSagDugum(SolDon(node.getSagDugum()));
		return SagDon(node);
	}public static int max(int first, int second) {
		if (first >= second)
			return first;
		else
			return second;
	}
	public AVLNode AtaBul(AVLNode node, String aranan) {
		if (aranan.equals(kok.veri)) {
			System.out.println("null");
		} else if (kok.solDugum.equals(aranan)) {
			System.out.println("null");
		} else if (kok.sagDugum.equals(aranan)) {
		} else {
			while (true) {	
				if (aranan.compareTo(node.getKey()) < 0) {
					AVLNode n2 = node;
					node = node.solDugum;
					if (node.solDugum != null) {
						if (node.solDugum.getKey().equals(aranan)) {
							System.out.print(n2.getKey());
						}else {
							if (aranan.compareTo(node.getKey()) < 0) {
								node = node.solDugum;
								System.out.print(n2.getKey());
								AtaBul(node, aranan);
							}else {
								node = node.sagDugum;
								System.out.print(n2.getKey());
								AtaBul(node, aranan);
							}}}
				}else if (aranan.compareTo(node.getKey()) > 0) {
					AVLNode n2 = node;
					node = node.sagDugum;
					if (node.sagDugum != null) {
						if (node.sagDugum.getKey().equals(aranan)) {
							System.out.print(n2.getKey());
						} else {
							if (aranan.compareTo(node.getKey()) > 0) {
								node = node.sagDugum;
								System.out.println(n2.getKey());
								AtaBul(node, aranan);
							} else {
								node = node.solDugum;
								System.out.println(n2.getKey());
								AtaBul(node, aranan);
							}}}}
				else {
					break;
				}}}
		return kok;
	}

	public void Sil(String key) {
		AVLNode ataDugum = null;
		AVLNode aranan = null;
		AVLNode dugum = kok;
		while (aranan == null && dugum != null) {
			if (key.compareTo(dugum.getKey()) < 0) {
				ataDugum = dugum;
				dugum = dugum.getSolDugum();
			} else if (key.compareTo(dugum.getKey()) > 0) {
				ataDugum = dugum;
				dugum = dugum.getSagDugum();
			} else {
				aranan = dugum;
			}}if (aranan == null) 
			return;
		AVLNode degistir = null;
		if (aranan.getSolDugum() != null || aranan.getSagDugum() != null) {degistir = getDegistir(aranan.getSolDugum(), aranan, aranan);
		} else {
			if (aranan.getSolDugum() != null)
				degistir = aranan.getSolDugum();
			else
				degistir = aranan.getSagDugum();
		}if (degistir != null) { degistir.setSolDugum(aranan.getSolDugum());
			degistir.setSagDugum(aranan.getSagDugum());
			updateBalance(degistir);
		}if (kok == aranan) {
			kok = degistir;
		} else {
			if (ataDugum.getSolDugum() == aranan)
				ataDugum.setSolDugum(degistir);
			else
				ataDugum.setSagDugum(degistir);
		}size--;
	}public AVLNode getDegistir(AVLNode dugum, AVLNode ata, AVLNode aranan) {
		if (dugum == null)
			return ata;
		AVLNode degistir = getDegistir(dugum.getSagDugum(), dugum, aranan);
		if (ata.getSagDugum() == degistir) {
			ata.setSagDugum(null);
			if (ata.getSolDugum() == null && ata != aranan)
				ata.setHeight(ata.getHeight() - 1);
		} else {
			updateBalance(ata);
		}return degistir;
	}public void updateBalance(AVLNode node) {
		int iLeftHeight = -1;
		int iRightHeight = -1;
		if (node.getSolDugum() != null)
			iLeftHeight = node.getSolDugum().getHeight();
		if (node.getSagDugum() != null)
			iRightHeight = node.getSagDugum().getHeight();
		node.setHeight(max(iLeftHeight, iRightHeight) + 1);
	}public void yazdýr() {
		AVLNode nesne = kok;
		if (nesne == null) {
			System.out.println("Aðaç yokk");
		} else {
			System.out.print("kök -> " + kok().getKey());
			yazdirmaislemi(nesne);
		}}public void yazdirmaislemi(AVLNode gelen) {
		if (gelen.getSolDugum() != null) {
			System.out.print(" Sol -> " + gelen.getSolDugum().getKey());
			yazdirmaislemi(gelen.getSolDugum());
		}if (gelen.getSagDugum() != null) {
			System.out.print(" Sag -> " + gelen.getSagDugum().getKey());
			yazdirmaislemi(gelen.getSagDugum());
}}}
