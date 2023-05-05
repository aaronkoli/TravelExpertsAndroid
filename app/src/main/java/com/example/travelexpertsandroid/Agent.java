package com.example.travelexpertsandroid;

import java.io.Serializable;

public class Agent implements Serializable
{
    private int agentId;
    private String agtFirstName;
    private String agtMiddleInitial;
    private String agtLastName;
    private String agtEmail;
    private String agtBusPhone;
    private String agtPosition;
    private String agencyId;


    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtEmail, String agtBusPhone, String agtPosition, String agencyId)
    {
        this.setAgentId(agentId);
        this.setAgtFirstName(agtFirstName);
        this.setAgtMiddleInitial(agtMiddleInitial);
        this.setAgtLastName(agtLastName);
        this.setAgtEmail(agtEmail);
        this.setAgtBusPhone(agtBusPhone);
        this.setAgtPosition(agtPosition);
        this.setAgencyId(agencyId);
    }


    public int getAgentId()
    {
        return agentId;
    }

    public void setAgentId(int agentId)
    {
        this.agentId = agentId;
    }

    public String getAgtFirstName()
    {
        return agtFirstName;
    }

    public void setAgtFirstName(String agtFirstName)
    {
        this.agtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial()
    {
        return agtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial)
    {
        this.agtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName()
    {
        return agtLastName;
    }

    public void setAgtLastName(String agtLastName)
    {
        this.agtLastName = agtLastName;
    }

    public String getAgtEmail()
    {
        return agtEmail;
    }

    public void setAgtEmail(String agtEmail)
    {
        this.agtEmail = agtEmail;
    }

    public String getAgtBusPhone()
    {
        return agtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone)
    {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtPosition()
    {
        return agtPosition;
    }

    public void setAgtPosition(String agtPosition)
    {
        this.agtPosition = agtPosition;
    }

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }
}
