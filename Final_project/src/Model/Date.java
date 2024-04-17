package Model;


public class Date {

  /**
   * Represents a date with day, month, and year
   */
  private int day;
  private int month;
  private int year;

  public Date(int month, int day, int year) {
    this.month = month;
    this.day = day;
    this.year = year;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return String.format("%02d/%02d/%04d", month, day, year);
  }



}
