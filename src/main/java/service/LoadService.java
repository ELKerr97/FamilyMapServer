package service;

import result.LoadResult;

/**
 * LoadService takes a LoadRequest and returns a LoadResult after using a DAO to
 * perform database functions
 */
public class LoadService extends Service{

  /**
   * Constructor for LoadService
   */
  public LoadService() {}

  /**
   * Clear data form the database
   * Load the user, person, and event data from request body into database
   * @return LoadResult containing result body
   */
  public LoadResult load() {
    return null;
  }
}
