
package com.example.tugasbesarpbo.util;

import com.example.tugasbesarpbo.model.StrukturData;

public class DynamicArray<T> implements StrukturData<T> {
    // ini baru - Hapus kata kunci 'static' agar tiap instance memiliki memori sendiri
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public DynamicArray() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        validateInvariant();
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
        validateInvariant();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int position) {
        checkIndex(position);
        return (T) elements[position];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int position) {
        checkIndex(position);
        T removedElement = (T) elements[position];

        // ini baru - Perbaikan sintaks numMoved
        int numMoved = size - position - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, position + 1, elements, position, numMoved);
        }
        elements[--size] = null;
        validateInvariant();
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null ? elements[i] == null : value.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        validateInvariant();
    }

    public void insert(int position, T value) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posisi indeks tidak valid: " + position);
        }
        ensureCapacity();

        // ini baru - Perbaikan sintaks System.arraycopy
        System.arraycopy(elements, position, elements, position + 1, size - position);

        elements[position] = value;
        size++;
        validateInvariant();
    }

    public void set(int position, T value) {
        checkIndex(position);
        elements[position] = value;
        validateInvariant();
    }

    public int search(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null ? elements[i] == null : value.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        if (size >= elements.length) {
            // ini baru - Perbaikan perbanyakan kapasitas array
            int newCapacity = elements.length == 0 ? DEFAULT_CAPACITY : elements.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private void checkIndex(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Indeks: " + position + ", Ukuran: " + size);
        }
    }

    public void validateInvariant() {
        if (size < 0) {
            throw new IllegalStateException("Invarian rusak: Ukuran negatif.");
        }
        if (elements == null) {
            throw new IllegalStateException("Invarian rusak: Array null.");
        }
        if (size > elements.length) {
            throw new IllegalStateException("Invarian rusak: Ukuran melebihi kapasitas.");
        }
        for (int i = size; i < elements.length; i++) {
            if (elements[i] != null) {
                throw new IllegalStateException("Invarian rusak: Ada elemen di luar size.");
            }
        }
    }

    public int capacity() {
        return elements.length;
    }
}