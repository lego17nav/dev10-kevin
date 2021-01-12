package learn.hotel.models;

import java.math.BigDecimal;

public class Host {

    //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate

    private String id;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private States state;
    private String pCode;
    private BigDecimal regRate;
    private BigDecimal weekERate;

    public Host() {

    }

    public Host(String id, String lastName, String email, String phone, String address, String city, States state,
                String pCode, BigDecimal regRate, BigDecimal weekERate) {

        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pCode = pCode;
        this.regRate = regRate;
        this.weekERate = weekERate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public BigDecimal getRegRate() {
        return regRate;
    }

    public void setRegRate(BigDecimal regRate) {
        this.regRate = regRate;
    }

    public BigDecimal getWeekERate() {
        return weekERate;
    }

    public void setWeekERate(BigDecimal weekERate) {
        this.weekERate = weekERate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Host host = (Host) o;
        return id.equals(host.id) && lastName.equals(host.getLastName()) &&
                email.equals(host.getEmail()) && phone.equals(host.phone) &&
                address.equals(host.address) && city.equals(city) && state.equals(host.state) &&
                pCode.equals(host.pCode) && regRate.equals(host.regRate) && weekERate.equals(host.weekERate);
    }

}
