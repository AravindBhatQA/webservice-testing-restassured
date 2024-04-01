package POJO.Booker;

public class BookingDatesPayload {
    String checkin;
    String checkout;

    public BookingDatesPayload(String checkIn, String checkOut) {
        this.checkin = checkIn;
        this.checkout = checkOut;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}
