package learn.field_agent.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agent {

    private int agentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
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
