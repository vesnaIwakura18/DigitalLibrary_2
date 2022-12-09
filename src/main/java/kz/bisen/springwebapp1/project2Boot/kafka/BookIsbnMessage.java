package kz.bisen.springwebapp1.project2Boot.kafka;

import lombok.Data;

import java.util.List;

@Data
public class BookIsbnMessage {
    private List<String> isbns;

    private int amount;

    private String partition;

    public BookIsbnMessage(List<String> isbns, int amount, String partition) {
        this.isbns = isbns;
        this.amount = amount;
        this.partition = partition;
    }

    public BookIsbnMessage() {}

    public static BookIsbnMessage of(List<String> isbns, int amount) {
        return new BookIsbnMessage(isbns, amount, "library-B-group");
    }

    public List<String> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<String> isbns) {
        this.isbns = isbns;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    @Override
    public String toString() {
        return "BookIsbnMessage{" +
                "isbn=" + isbns +
                ", amount=" + amount +
                '}';
    }
}