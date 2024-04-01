package POJO.Booker;

public class CreateBookingPayload {
    String firstname;
    String lastname;
    int totalprice;
    boolean depositpaid;
    BookingDatesPayload bookingdates;
    String additionalneeds;

    public CreateBookingPayload(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingDatesPayload bookingDates, String additionalNeeds) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.totalprice = totalPrice;
        this.depositpaid = depositPaid;
        this.bookingdates = bookingDates;
        this.additionalneeds = additionalNeeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public BookingDatesPayload getBookingdates() {
        return bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }
}
