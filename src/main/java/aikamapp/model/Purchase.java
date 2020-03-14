package aikamapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Purchase {
    private Long id;
    private Buyer buyer;
    private Good good;
    private LocalDate date;

    public Purchase(Long id, Buyer buyer, Good good, LocalDate date) {
        this.id = id;
        this.buyer = buyer;
        this.good = good;
        this.date = date;
    }

    public Purchase(Buyer buyer, Good good, LocalDate date) {
        this(null, buyer, good, date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", good=" + good +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase buy = (Purchase) o;
        return Objects.equals(id, buy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
