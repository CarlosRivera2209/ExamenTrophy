
package examen2_lab2;

class Entry {
    String username;
    long position;
    Entry next;

    public Entry(String username, long position) {
        this.username = username;
        this.position = position;
        this.next = null;
    }
}
