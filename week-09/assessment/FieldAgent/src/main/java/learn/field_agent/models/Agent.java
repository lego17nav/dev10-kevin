package learn.field_agent.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agent {


    private int agentId;

    @NotBlank(message = "Agent First Name can't be null")
    @Size(max = 25, message = "Characters can't exceed 25")
    private String firstName;

    @NotBlank(message = "Agent Middle Name can't be null")
    @Size(max = 25, message = "Characters can't exceed 25")
    private String middleName;

    @NotBlank(message = "Agent Last Name can't be null")
    @Size(max = 25, message = "Characters can't exceed 25")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;


    @Min(value = 36, message = "Height must be above 36")
    @Max(value = 96, message = "Height must be below 86")
    private int height;


    private List<AgentAgency> agencies = new ArrayList<>();

    public List<Alias> getAlias() {
        return alias;
    }

    public void setAlias(List<Alias> alias) {
        this.alias = alias;
    }

    private List<Alias> alias = new ArrayList<>();

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<AgentAgency> getAgencies() {
        return new ArrayList<>(agencies);
    }

    public void setAgencies(List<AgentAgency> agencies) {
        this.agencies = agencies;
    }
}
