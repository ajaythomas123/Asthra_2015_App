package com.tiramisu.asthraappmk2;

/**
 * Created by ASUS on 29-01-2015.
 */

//Objects of this class will store the details of each event.
public class EventDetails {
    String eventId;
    String eventName;
    String eventDescription;
    String eventRule;
    String eventContact;
    String eventBranch;
    String eventPrize;
    int eventDay;
    String eventTime;
    int eventPosterId;
    Boolean eventSpot;
    Boolean eventTeam;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventRule() {
        return eventRule;
    }

    public void setEventRule(String eventRule) {
        this.eventRule = eventRule;
    }

    public String getEventContact() {
        return eventContact;
    }

    public void setEventContact(String eventContact) {
        this.eventContact = eventContact;
    }

    public String getEventPrize(){
        return eventPrize;
    }

    public void setEventPrize(String eventPrize) {
        this.eventPrize = eventPrize;
    }

    public String getEventBranch() {
        return eventBranch;
    }

    public void setEventBranch(String eventBranch) {
        this.eventBranch = eventBranch;
    }

    public int getEventDay() {
        return eventDay;
    }

    public void setEventDay(int eventDay) {
        this.eventDay = eventDay;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Boolean getEventSpot() {
        return eventSpot;
    }

    public void setEventSpot(Boolean eventSpot) {
        this.eventSpot = eventSpot;
    }

    public Boolean getEventTeam() {
        return eventTeam;
    }

    public void setEventTeam(Boolean eventTeam) {
        this.eventTeam = eventTeam;
    }

    public int getEventPosterId() {
        return eventPosterId;
    }

    public void setEventPosterId(int eventPosterId) {
        this.eventPosterId = eventPosterId;
    }
}
