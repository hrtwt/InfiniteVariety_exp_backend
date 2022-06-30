package jp.kusumotolab.InfiniteVarietyExp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "results")
@Getter
@Setter
public class ResultEntity extends CommonEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "pair_id")
  private Integer pairId;

  @Column(name = "user_name")
  private String user;

  @Column(name = "judge")
  private String judge;
}
