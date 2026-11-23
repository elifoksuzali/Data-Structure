# 📚 Veri Yapıları (Data Structures)

Veri Yapıları dersi kapsamında geliştirilmiş **Java** (ve bir miktar **C**) veri yapısı ve
algoritma uygulamalarının derlemesidir. Her klasör, bağımsız bir veri yapısı / algoritma
örneğini içerir. İçerik özetleri, projelere ait rapor dosyalarından (bkz. `docs/`) derlenmiştir.

> Not: Dosyalar birbirinden bağımsız alıştırmalardır; tek bir uygulama değildir.

---

## 📁 Klasör Yapısı

```
.
├── avl-tree/        # AVL (dengeli ikili arama) ağacı
│   ├── AVLAGAC.java
│   ├── AVLNode.java
│   ├── AVLTree.java
│   ├── AvlTree_teslim2.java   # alternatif AVL uygulaması
│   └── Dugum.java
│
├── linked-list/     # Bağlı listeler + geri al/yinele
│   ├── bagliListe.java         # çift yönlü (iki yönlü) bağlı liste
│   ├── Link.java
│   ├── undo-redo.java          # bağlı liste ile undo / redo
│   └── circular_linked_list.c  # dairesel bağlı liste (C)
│
├── fp-growth/       # FP-Growth sık öğe kümesi madenciliği
│   ├── FPGrowth.java
│   ├── FPtree.java
│   └── frequencyComparitorinHeaderTable.java
│
├── merge-sort/      # Birleştirmeli sıralama (Merge Sort)
│   ├── Merge.java
│   └── merge_sort_c.txt        # C dilindeki sürüm
│
├── misc/            # Yardımcı / giriş sınıfları
│   ├── Main.java
│   └── Menu.java
│
└── docs/            # Proje ve ödev raporları (detaylı anlatım)
    ├── Proje2_Rapor.pdf        # AVL ağacı
    ├── Odev1_Rapor.docx        # Bağlı liste + undo/redo
    └── Odev2_Rapor.pdf         # Sıralama ve arama algoritmaları
```

---

## 🌳 AVL Ağacı (`avl-tree/`)

Kendini dengeleyen ikili arama ağacı. Kullanıcıdan sonsuz adet girdi alınır, bir
`while(true)` döngüsü içinde `switch-case` ile komut işlenir (`ekle`, `sil`, `ata`).

- **`Dugum` (Node) sınıfı:** veriyi tutan `deger` (String), sol/sağ çocukları tutan
  `soldugum`/`sagdugum` ve dengeyi tutan `yukseklik` alanları. `yukseklikfarki` metodu
  **balans faktörünü** verir (pozitif → sağa, negatif → sola dallanma).
- **`Ekle()`** — özyinelemeli (recursive); uygun boş (`null`) konumu bulup ekler.
- **`Sil()`** — silinecek düğümün atasını (`parentibul`) ve yerine geçecek düğümü
  (`silinenyerine` → sağdaki en küçük eleman) bulup çıkarır.
- **`Duzenle()`** — her ekleme/silme sonrası dengeyi düzeltir. Dört rotasyon durumu:
  **LL, LR, RL, RR** (tek/çift rotasyonlar).
- **`Yazdir()` / `Ata()`** — ağacı özyinelemeli gezerek ekrana yazar.

---

## 🔗 Bağlı Listeler ve Undo/Redo (`linked-list/`)

Kullanıcıdan alınan girdileri **çift yönlü (iki yönlü) bağlı listede** saklayıp **geri al
(undo)** ve **yinele (redo)** işlemlerine olanak tanıyan program.

- İki liste kullanılır: **`liste`** (asıl veriler) ve **`liste2`** (undo ile çıkarılan
  elemanların redo için saklandığı yer).
- **`basaEkle` / `sonaEkle`** — listenin başına/sonuna düğüm ekler (liste boş mu kontrolüyle).
- **`undo`** — son elemanı asıl listeden çıkarıp `liste2`'ye aktarır ve döndürür.
- **`redo`** — `liste2`'den alıp asıl listeye geri ekler.
- **`Listele`** — tüm listeyi gezerek ekrana yazar.
- Ayrıca `circular_linked_list.c` ile **dairesel bağlı liste** (C) örneği.

---

## ⛏️ FP-Growth (`fp-growth/`)

Birliktelik kuralı madenciliğinde **sık öğe kümelerini (frequent itemsets)** bir **FP-ağacı**
üzerinde bulan algoritma. `FPtree` düğüm yapısını, `frequencyComparitorinHeaderTable` ise
header tablosundaki öğeleri frekansa göre sıralamayı sağlar.

---

## 🔀 Sıralama ve Arama Algoritmaları (`merge-sort/` + `docs/Odev2`)

`merge-sort/` birleştirmeli sıralamanın Java ve C sürümlerini içerir. İlgili rapor
(`docs/Odev2_Rapor.pdf`) ayrıca şu algoritmaları adım adım örneklerle açıklar:

| Algoritma | En iyi | En kötü | Tür |
|-----------|--------|---------|-----|
| Insertion Sort (Araya Sokma) | O(n) | O(n²) | Sıralama |
| Selection Sort (Seçmeli) | O(n²) | O(n²) | Sıralama |
| Heap Sort (Kümeleme) | O(n) | O(n log n) | Sıralama |
| Quick Sort (Hızlı) | O(n log n) | O(n²) | Sıralama |
| Merge Sort (Birleştirmeli) | O(n log n) | O(n log n) | Sıralama |
| Linear Search (Doğrusal) | O(1) | O(n) | Arama |
| Binary Search (İkili) | O(1) | O(log n) | Arama (sıralı veri) |

---

## 🚀 Derleme ve Çalıştırma

Her örnek kendi klasöründe bağımsızdır. Java:

```bash
cd avl-tree
javac *.java
java AVLTree      # ya da ilgili main içeren sınıf
```

C:

```bash
cd linked-list
gcc circular_linked_list.c -o circular
./circular
```

> Not: Bazı dosyalar farklı `package` tanımlarıyla yazılmış bağımsız alıştırmalardır; hepsini
> birlikte tek seferde derlemek yerine ilgili klasörde ayrı ayrı derleyin.

---
