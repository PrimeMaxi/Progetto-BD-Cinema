package sample.helpers;

import java.sql.Date;
import java.util.Objects;

public class RangeDate {

  private Date from ;
  private Date to;
  private Integer id;

  public RangeDate(Date from, Date to, Integer id) {
    this.from = from;
    this.to = to;
    this.id = id;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getTo() {
    return to;
  }

  public void setTo(Date to) {
    this.to = to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RangeDate rangeDate = (RangeDate) o;
    return Objects.equals(from, rangeDate.from) && Objects.equals(to,
        rangeDate.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }
}
