package dao;

import model.Event;

import java.util.ArrayList;

public class JSONDataCache {

  private ArrayList<String> maleNames;
  private ArrayList<String> femaleNames;
  private ArrayList<Event> events;
  private ArrayList<String> lastNames;


  public static void setInstance(JSONDataCache instance) {
    JSONDataCache.instance=instance;
  }

  private static JSONDataCache instance;

  public static JSONDataCache getInstance() {

    if(instance == null) {

      instance = new JSONDataCache();
    }

    return instance;
  }

  private JSONDataCache(){}

  public ArrayList<String> getLastNames() {
    return lastNames;
  }

  public void setLastNames(ArrayList<String> lastNames) {
    this.lastNames=lastNames;
  }

  public ArrayList<String> getMaleNames() {
    return maleNames;
  }

  public void setMaleNames(ArrayList<String> maleNames) {
    this.maleNames=maleNames;
  }

  public ArrayList<String> getFemaleNames() {
    return femaleNames;
  }

  public void setFemaleNames(ArrayList<String> femaleNames) {
    this.femaleNames=femaleNames;
  }

  public ArrayList<Event> getEvents() {
    return events;
  }

  public void setEvents(ArrayList<Event> events) {
    this.events=events;
  }

}
