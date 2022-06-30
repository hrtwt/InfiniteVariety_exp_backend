package jp.kusumotolab.InfiniteVarietyExp.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class CommonEntity {

  @Column(name = "insert_date", updatable = false)
  @CreationTimestamp
  private Timestamp insertDate;

  @Column(name = "update_date")
  @UpdateTimestamp
  private Timestamp updateDate;

  public void setInsertDate(final long fastTime) {
    this.insertDate = new Timestamp(fastTime);
  }

  public void setUpdateDate(final long fastTime) {
    this.updateDate = new Timestamp(fastTime);
  }
}
