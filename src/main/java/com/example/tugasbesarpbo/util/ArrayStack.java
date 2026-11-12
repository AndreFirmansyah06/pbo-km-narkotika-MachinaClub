package com.example.tugasbesarpbo.util;
import com.example.tugasbesarpbo.model.StrukturData;
/**
 * Stack berbasis array (struktur SEKUNDER, dipakai untuk audit/riwayat).
 *
 * Pemetaan ke kontrak StrukturData:
 *  add(v)      -> push(v)
 *  get(pos)    -> pos 0 = elemen teratas (terbaru), pos 1 = di bawahnya, dst. (non-destruktif)
 *  remove(pos) -> hanya pos 0 (= pop). Selain itu melanggar LIFO -> UnsupportedOperationException.
 */
public class ArrayStack<T> implements StrukturData<T> {
    private Object[] data;
    private int top; // jumlah elemen = indeks slot kosong berikutnya
    private int langkahTerakhir;

    public ArrayStack() { data = new Object[10]; }

    public void push(T v) {
        if (top == data.length) {
            Object[] baru = new Object[data.length * 2];
            for (int i = 0; i < top; i++) baru[i] = data[i];
            data = baru;
        }
        data[top++] = v;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack kosong");
        T x = (T) data[--top];
        data[top] = null;
        return x;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack kosong");
        return (T) data[top - 1];
    }

    /** Cari tanpa mengubah urutan akhir: pop ke stack bantu, lalu restore. */
//    public T searchPreserve(Kriteria<T> k) {
//        ArrayStack<T> bantu = new ArrayStack<>();
//        T hasil = null;
//        langkahTerakhir = 0;
//        while (!isEmpty()) {
//            T x = pop();
//            bantu.push(x);
//            langkahTerakhir++;
//            if (k.cocok(x)) { hasil = x; break; }
//        }
//        restore(bantu);
//        return hasil;
//    }

    private void restore(ArrayStack<T> bantu) {
        while (!bantu.isEmpty()) push(bantu.pop());
    }

    public int getLangkahTerakhir() { return langkahTerakhir; }

    // ---- kontrak umum ----
    @Override public void add(T v) { push(v); }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int pos) {
        if (pos < 0 || pos >= top) throw new IndexOutOfBoundsException("Posisi " + pos);
        return (T) data[top - 1 - pos];
    }

    @Override
    public T remove(int pos) {
        if (pos != 0) throw new UnsupportedOperationException("Stack hanya boleh menghapus elemen teratas");
        return pop();
    }

    @Override public int size() { return top; }
    @Override public boolean isEmpty() { return top == 0; }

    @Override
    public boolean contains(T v) {
        for (int i = 0; i < top; i++) if (data[i] != null && data[i].equals(v)) return true;
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < top; i++) data[i] = null;
        top = 0;
    }

    public void validateInvariant() {
        if (top < 0 || top > data.length) throw new IllegalStateException("top di luar kapasitas");
        for (int i = top; i < data.length; i++)
            if (data[i] != null) throw new IllegalStateException("slot di atas top tidak null: " + i);
    }
}
